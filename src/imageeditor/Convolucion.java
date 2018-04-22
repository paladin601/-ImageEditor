
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package imageeditor;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author FliaMejias
 */
public class Convolucion {
    float[][]    conv;
    Colorsin[][] colores;
    int notNullcolors;
    int          pivotx, width;
    int          pivoty, heigth;
    Colorsin     absorbedSum, appliedConvolutionSum;
    float        convolutionSum;

    public Convolucion(int _width, int _heigth, int px, int py) {
        conv    = new float[_heigth][_width];
        width   = _width;
        heigth  = _heigth;
        pivotx  = px;
        pivoty  = py;
        colores = new Colorsin[_heigth][_width];
        
    }
    public Convolucion(String conv_name){
        System.out.println(conv_name);
        try(Scanner in = new Scanner(new File(conv_name))){
            String line = in.nextLine();
            String[] split = line.split(" ");
            
            width = Integer.parseInt(split[0]);
            heigth = Integer.parseInt(split[1]);
            pivotx = Integer.parseInt(split[2]);
            pivoty = Integer.parseInt(split[3]);
            conv = new float[heigth][width];
            colores = new Colorsin[heigth][width];
            for(int yy = 0; yy < heigth; yy ++){
                split = in.nextLine().split(" ");
                for (int xx = 0; xx < width; xx++) {
                    conv[yy][xx] = Float.parseFloat(split[xx]);                    
                }
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("NO cargoOOOooOoOoOooO");
        } 
    }
    
    @Override
    public String toString() {
        String out = "";

        out = out + "(width, " + width + ") (heigth, " + heigth + ") (pivotx, " + pivotx + ") (pivoty," + pivoty
              + ")/n";

        return out;
    }

    public float getConvSum() {
        this.convolutionSum = 0;

        for (int yy = 0; yy < heigth; yy++) {
            for (int xx = 0; xx < width; xx++) {
                if(colores[yy][xx] == null) continue;
                this.convolutionSum += conv[yy][xx];
            }
        }

        return this.convolutionSum;
    }

    public Colorsin getAbsorbedSum() {
        this.absorbedSum = new Colorsin();

        for (int yy = 0; yy < heigth; yy++) {
            for (int xx = 0; xx < width; xx++) {
                if(colores[yy][xx] == null) continue;
                 absorbedSum = absorbedSum.operate( (x,y) -> {return x+y;} , colores[yy][xx]);
            }
        }

        return absorbedSum;
    }

    public Colorsin getConvedValues() {
        this.appliedConvolutionSum = new Colorsin();
        for (int yy = 0; yy < heigth; yy++) {
            for (int xx = 0; xx < width; xx++) {
                if(colores[yy][xx] == null) continue;
                appliedConvolutionSum = appliedConvolutionSum.operate( (x,y) -> {return x + y;} , colores[yy][xx].multiplicarConstante(conv[yy][xx]));
            }
        }

        return appliedConvolutionSum;
    }

    public Convolucion clamp(){
        for (int yy = 0; yy < heigth; yy++) {
            for (int xx = 0; xx < width; yy++) {
                if(colores[yy][xx] == null) continue;
                colores[yy][xx] = colores[yy][xx].clamp();
            }
        }

        return this;
    }

    public Convolucion absorb(BufferedImage in, int xx, int yy) {
        this.notNullcolors = 0;
        for (int cy = yy - pivoty, convy = 0; convy < heigth; cy++, convy++) {    // entramos loop convolucion
            for (int cx = xx - pivotx, convx = 0; convx < width; cx++, convx++) {
                try {
                    colores[convy][convx] = new Colorsin(in.getRGB(cx, cy));
                    this.notNullcolors++;
                } catch (Exception ex) {
                    colores[convy][convx] = null;
                }
            }
        }    // Salimos loop convolucion

        return this;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Colorsin mediana() {
        Colorsin out = new Colorsin();
        int[][]  co  = new int[3][this.notNullcolors];

        for (int auxii = 0, yy = 0; yy < heigth; yy++) {
            for (int xx = 0; xx < width; xx++) {
                if(colores[yy][xx] == null) continue;
            
                for (int cc = 0; cc < 3; cc++) {
                    co[cc][auxii] = colores[yy][xx].color[cc];
                }
                
                auxii++;
            }
        }

        for (int cc = 0; cc < 3; cc++) {
            Arrays.sort(co[cc]);

            if (co[cc].length % 2 == 0) {
                int lo = co[cc].length / 2,
                    hi = lo + 1;

                out.color[cc] = ((co[cc][lo] + co[cc][hi])) / 2;
            } else {
                int pos = (int) co[cc].length / 2;

                out.color[cc] = co[cc][pos];
            }
        }
    
//////////////////////////////////////////////////////////////
        /*
la implementacion de arriba sirve, pero ahora estoy poniendo los colores 
//invalidos con null y no quiero que sean tomados en cuenta en la media

       
       // despues compacto esto
    ArrayList<Integer> r,g,b;
    r = new ArrayList<>();
    g = new ArrayList<>();
    b = new ArrayList<>();

    for(int yy = 0; yy < heigth; yy++){
        for (int xx = 0; xx < width; xx++) {
            Colorsin co = colores[yy][xx];
            if(co == null) continue;
            r.add(co.color[0]);
            g.add(co.color[1]);
            b.add(co.color[2]);

        }
    }
    
    r.sort((x,y) -> {return x - y;});
    g.sort((x,y) -> {return x - y;});
    b.sort((x,y) -> {return x - y;});

    int red = (r.size() % 2 == 0)? (r.get(r.size()/2) + r.get((r.size()/2)+1))/2 : r.get(r.size()/2);
    int green = (g.size() % 2 == 0)? (g.get(r.size()/2) + g.get((g.size()/2)+1))/2 : g.get(r.size()/2);
    int blue = (b.size() % 2 == 0)? (b.get(b.size()/2) + b.get((r.size()/2)+1))/2 : b.get(r.size()/2);

    //me acabo de dar cuenta que con declarar el array con length notNullColor lo pude haber hecho
    //comento esto y lo hago de nuevo....
    
    */
        //return new Colorsin(red, green, blue);
        return out;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
