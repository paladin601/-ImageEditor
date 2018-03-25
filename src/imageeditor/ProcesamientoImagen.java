package imageeditor;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProcesamientoImagen {

    // Imagen actual que se ha cargado
    private BufferedImage imageActual;
    private BufferedImage imageCopy;
    private int Bits;
    private int maxColores = 255;
    private int Umbral=150;
    private String formato;

    // Método que devuelve una imagen abierta desde archivo
    // Retorna un objeto BufferedImagen
    public BufferedImage abrirImagen() {

        // Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp = null;

        // Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector = new JFileChooser();

        // Le damos un título
        selector.setDialogTitle("Seleccione una imagen");

        // Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("Formato", "pbm", "pgm", "ppm",
                                                   "bmp");

        selector.setFileFilter(filtroImagen);

        // Abrimos el cuadro de diálog
        int flag = selector.showOpenDialog(null);

        // Comprobamos que pulse en aceptar
        if (flag == JFileChooser.APPROVE_OPTION) {
            try {

                // Devuelve el fichero seleccionado
                File   imagenSeleccionada = selector.getSelectedFile();
                String ext= selector.getSelectedFile().getAbsolutePath();

                ext = ext.substring(ext.lastIndexOf("."));
                System.out.println(ext);

                // Asignamos a la variable bmp la imagen leida
                if (ext.equals(".bmp")) {
                    
                    bmp = ImageIO.read(imagenSeleccionada);
                    imageCopy = ImageIO.read(imagenSeleccionada);
                    formato = "bmp";
                } else {
                    bmp = leerNETPBM(imagenSeleccionada);
                }
                
                        // Asignamos la imagen cargada a la propiedad imageActual
                imageActual = bmp;
                imageCopy   = copiarImagen(bmp);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }



        // Bits         = (imageActual.getColorModel().getPixelSize()) / 3;
        // NumberColors = (int) Math.pow(2, Bits);
        // Umbral       = (NumberColors / 2) - 1;
        // Retornamos el valor
        return bmp;
    }

    public BufferedImage escalaGrises() {

        // Variables que almacenarán los píxeles
        int   mediaPixel, colorSRGB;
        Color colorAux;
        formato = "P2";
        // Recorremos la imagen píxel a píxel
        for (int i = 0; i < imageActual.getWidth(); i++) {
            for (int j = 0; j < imageActual.getHeight(); j++) {

                // Almacenamos el color del píxel
                colorAux = new Color(this.imageActual.getRGB(i, j));

                // Calculamos la media de los tres canales (rojo, verde, azul)
                mediaPixel = (int) ((colorAux.getRed() + colorAux.getGreen() + colorAux.getBlue()) / 3);

                // Cambiamos a formato sRGB
                colorSRGB = (mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;

                // Asignamos el nuevo valor al BufferedImage
                imageActual.setRGB(i, j, colorSRGB);
            }
        }

        // Retornamos la imagen
        return imageActual;
    }

    public BufferedImage guardarImagen() {
        JFileChooser selector = new JFileChooser();

        selector.setDialogTitle("Guardar Como");

        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("Escoge pues", "pbm", "pgm", "ppm", "bmp");

        selector.setFileFilter(filtroImagen);

        int flag = selector.showSaveDialog(null);

        if (flag == JFileChooser.APPROVE_OPTION) {
            System.out.println("formato inicial" + formato);
            try {
                if(formato.equals("bmp")){
                    ImageIO.write(imageActual, "", selector.getSelectedFile());
                }else{
                   String nombre = selector.getSelectedFile().getAbsolutePath();
                    Map<String, String> extension = new HashMap<>();
                    extension.put("P1", ".pbm");
                    extension.put("P2", ".pgm");
                    extension.put("P3", ".ppm");
                    if(nombre.contains(".")){
                        nombre = nombre.substring(0, nombre.lastIndexOf("."));//le quito la extension que tenía
                        
                    }
                    nombre = nombre.concat(extension.get(formato));
                   
                    PrintWriter writer = new PrintWriter(nombre, "US-ASCII");
                    
                    writer.println(formato);
                    writer.println(imageActual.getWidth()+" "+ imageActual.getHeight());
                    
                    if(!formato.equals("P1")){
                        writer.println(maxColores);
                    }
                    int yLimit = imageActual.getHeight();
                    int xLimit = imageActual.getWidth();
                    for (int y = 0; y < yLimit; y++) {
                        for (int x = 0; x < xLimit ; x++) {
                            Color co = new Color(imageActual.getRGB(x, y));
                            if(formato.equals("P3")){//color
                                writer.println(co.getRed() +" "+ co.getGreen()+" "+co.getBlue());
                            }else{
                                writer.println(co.getRed());
                            }
                            
                        }
                    }
                }

            } catch (IOException e) {}
        }

        return imageActual;
    }

    public BufferedImage RemoverFiltros() {

        /* FUNCIONA UNA SOLA VEZ */
        imageActual = copiarImagen(imageCopy);
        
        return imageActual;
    }

    public BufferedImage BlancoYNegro() {    // Ta mal
        int   Red;
        Color colorAux;
        imageActual = escalaGrises();
        formato = "P1";
        // Recorremos la imagen píxel a píxel
        for (int i = 0; i < imageActual.getWidth(); i++) {
            for (int j = 0; j < imageActual.getHeight(); j++) {
                // Almacenamos el color del píxel
                colorAux = new Color(this.imageActual.getRGB(i, j));
                int rgb = this.imageActual.getRGB(i, j);
                Red= colorAux.getRed();
                Red= (Red > Umbral)? 255: 0;
                imageActual.setRGB(i, j, new Color(Red, Red, Red).getRGB());
            }
        }
        // Retornamos la imagen
        return imageActual;
    }

    public BufferedImage Negativo(){
        for (int y = 0; y < imageActual.getHeight(); y++) {
            for (int x = 0; x < imageActual.getWidth(); x++) {
                Color co = new Color(imageActual.getRGB(x, y));
                int Red = maxColores - co.getRed()
                        ,Green = maxColores - co.getGreen()
                        ,Blue = maxColores - co.getBlue();
                imageActual.setRGB(x, y, new Color(Red, Green, Blue).getRGB());
            }
        }
        return imageActual;
    }
    
    public BufferedImage leerNETPBM(File path) throws IOException, Exception {
        BufferedImage out;
        String        line;
        int           width     = -1,
                      height    = -1,
                      imgType   = -1;
        int[]         pixelType = { BufferedImage.TYPE_BYTE_BINARY, BufferedImage.TYPE_USHORT_GRAY,
                                    BufferedImage.TYPE_INT_RGB };
        maxColores = 255;
        try (Scanner in = new Scanner(path)) {

            // borrar este while después porque debería estar en otro lado
            String[] split;

            // leo magic number
            while ((line = in.nextLine()) != null) {
                line = line.trim();

                if (line.charAt(0) == '#') {
                    continue;
                }

                if (line.charAt(0) == 'P') {
                    imgType = Integer.parseInt(line.substring(1, 2));
                }
                formato = ""+line;//hago una suma con vacio porque no estoy claro de si asigno copia o referencia
                //System.out.println("formato " + line);
                System.out.println("hola formato ");
                break;
            }

            // leo width y height
            while ((line = in.nextLine()) != null) {
                line = line.trim();

                if (line.charAt(0) == '#') {
                    continue;
                }

                split  = line.split(" ");
                width  = Integer.parseInt(split[0]);
                height = Integer.parseInt(split[1]);
                System.out.println("hola width ");
                break;
            }

            if ((imgType == -1) || (width == -1) || (height == -1)) {
                throw new Exception("Imagen en formato maluco");
            }

            imgType--;    // No tocar, es para convertir los P1, P2,P3 de los formatos en 0,1,2

            // leo max color si no es binary img
            if (pixelType[imgType] != BufferedImage.TYPE_BYTE_BINARY) {
                while ((line = in.nextLine()) != null) {
                    line = line.trim();

                    if (line.charAt(0) == '#') {
                        continue;
                    }

                    maxColores = Integer.parseInt(line);

                    break;
                }
            }

            out = new BufferedImage(width, height, pixelType[imgType]);

            int   readLength = (pixelType[imgType] == BufferedImage.TYPE_INT_RGB)
                               ? 3
                               : 1;
            int[] pixel      = new int[readLength];
            int   pp         = 0,
                  ii         = 0,
                  aa         = 0;

            while (in.hasNextLine()) {
                line = in.nextLine().trim();

                if (line.charAt(0) == '#') {
                    continue;
                }

                split = line.split(" ");

                for (String string : split) {

                    // la cond hace que agarre tripletas o de uno en uno
                    pixel[aa] = Integer.parseInt(string);
                    aa++;

                    if (aa >= readLength) {    // pongo pixel
                        aa = 0;

                        //hago que ocupe todos los colores
                        if (pixelType[imgType] == BufferedImage.TYPE_INT_RGB) {
                            out.setRGB(ii, pp, new Color((pixel[0]), (pixel[1]), (pixel[2])).getRGB());

                            //out.setRGB(ii, pp, new Color((pixel[0]/maxColores)*255, (pixel[1]/maxColores)*255, (pixel[2]/maxColores)*255).getRGB());
                        } else {
                            int extColor = (pixel[0]);///maxColores) * 255;
                            if(pixelType[imgType]== BufferedImage.TYPE_BYTE_BINARY){
                                extColor = pixel[0] * 255;
                                out.setRGB(ii, pp, new Color(extColor, extColor , extColor).getRGB());
                            }else{
                                out.setRGB(ii, pp, new Color(extColor, extColor , extColor).getRGB());
                            }
                                

                        }

                        ii++;

                        if (ii >= width) {
                            ii = 0;
                            pp++;

                            if (pp > height) {
                                System.out.println("hola lectura "+ii+" "+pp);
                                throw new Exception("Imagen mala brou");
                                
                            }
                        }
                    }
                }
            }
        }

        System.out.println(width + " / " + height + " / type" + imgType);

        return out;
    }

    public Integer ContarColores() {
        Map<Integer, Integer> out = new HashMap<>();
        int size;
        size = 0;
        for (int ii = 0; ii < imageActual.getWidth(); ii++) {
            for (int jj = 0; jj < imageActual.getHeight(); jj++) {
                int colorsito = imageActual.getRGB(ii, jj);
                
                if (out.containsKey(colorsito)) {
                    out.replace(colorsito, out.get(colorsito) + 1);
                } else {
                    out.put(colorsito, 1);
                    size++;
                }
            }
        }
        return size;
    }
    s
    // Se puede combinar con las keys de ContarColores para hacer algo
    public int[] extraerRGB(int color) {
        return new int[] {    // rgba

            // #FF son los primeros 8 bits a la derecha, de resto solo voy moviendo bits para tomar los que quiero
            (color >> 16) & 0xF, (color >> 8) & 0xFF, (color) & 0xFF, (color >> 24) & 0xFF
        };
    }

    public BufferedImage RotarIzquierda() {
        BufferedImage out = new BufferedImage(imageActual.getHeight(), imageActual.getWidth(), imageActual.getType());

        for (int x = 0, yo = out.getHeight() - 1; x < imageActual.getWidth(); x++, yo--) {
            for (int y = 0, xo = 0; y < imageActual.getHeight(); y++, xo++) {
                out.setRGB(xo, yo, imageActual.getRGB(x, y));
            }
        }
        imageActual= out;
        return out;
    }

    public BufferedImage RotarDerecha() {
        BufferedImage out = new BufferedImage(imageActual.getHeight(), imageActual.getWidth(), imageActual.getType());

        for (int y = 0, xo = out.getWidth() - 1; y < imageActual.getHeight(); y++, xo--) {
            for (int x = 0, yo = 0; x < imageActual.getWidth(); x++, yo++) {
                out.setRGB(xo, yo, imageActual.getRGB(x, y));
            }
        }
        imageActual = out;
        return out;
    }

    public float Promedio(Number... args) {
        Number sum = 0;

        for (Number arg : args) {
            sum = sum.floatValue() + arg.floatValue();
        }

        return sum.floatValue() / args.length;
    }

    /*public float getRLE(){
        int lastSeen, count;
        for (int y = 0; y < imageActual.getHeight(); y++) {
            for (int x = 0; x < imageActual.getWidth(); x++) {
                
                
            }
        }
    }*/
    
    public BufferedImage copiarImagen(BufferedImage in){
        return new BufferedImage(
         in.getColorModel()
        ,in.getRaster()
        ,in.getColorModel().isAlphaPremultiplied()
        ,null
        );
    }
}
