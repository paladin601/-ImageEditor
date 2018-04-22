
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
    Colorsin     asum, qsum;
    float        sum;

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
        this.sum = 0;

        for (int yy = 0; yy < heigth; yy++) {
            for (int xx = 0; xx < width; xx++) {
                this.sum += conv[yy][xx];
            }
        }

        return this.sum;
    }

    public Colorsin getAbsorbedSum() {
        this.asum = new Colorsin();

        for (int yy = 0; yy < heigth; yy++) {
            for (int xx = 0; xx < width; xx++) {
                
                 asum = asum.operate( (x,y) -> {return x+y;} , colores[yy][xx]);
            }
        }

        return asum;
    }

    public Colorsin getConvedValues() {
        this.qsum = new Colorsin();
        for (int yy = 0; yy < heigth; yy++) {
            for (int xx = 0; xx < width; xx++) {
                try{
                    qsum = qsum.operate( (x,y) -> {return x+y;} , colores[yy][xx].multiplicarConstante(conv[yy][xx]));
                }catch(Exception ex){
                    
                }
            }
        }

        return qsum;
    }

    public Convolucion clamp(){
        for (int yy = 0; yy < colores.length; yy++) {
            for (int xx = 0; xx < colores.length; yy++) {
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
                    colores[convy][convx] = new Colorsin();
                }
            }
        }    // Salimos loop convolucion

        return this;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Colorsin media() {
        Colorsin out = new Colorsin();
        int[][]  co  = new int[3][colores.length * colores[0].length];

        for (int auxii = 0, yy = 0; yy < colores.length; yy++, auxii++) {
            for (int xx = 0; xx < colores[0].length; xx++) {
                for (int cc = 0; cc < 3; cc++) {
                    co[cc][auxii] = colores[yy][xx].color[cc];
                }
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

        return out;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
