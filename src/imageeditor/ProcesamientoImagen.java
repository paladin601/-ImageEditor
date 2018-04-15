package imageeditor;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProcesamientoImagen {

    // Imagen actual que se ha cargado
    private BufferedImage imageActual;
    private BufferedImage imageCopy;
    private int maxColores = 255;
    private int Umbral;
    private String formato;
    private String formatoinit;

    // Método que devuelve una imagen abierta desde archivo
    // Retorna un objeto BufferedImagen
    public boolean imagenCargada(){
        return (imageActual != null);
    }
    
    public BufferedImage abrirImagen() {
        // Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp = null;
        // Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector = new JFileChooser();
        // Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        // Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("bmp , pbm , pgm y ppm", "pbm", "pgm", "ppm","bmp");
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
                // Asignamos a la variable bmp la imagen leida
                if (ext.equals(".bmp")) {
                    bmp = ImageIO.read(imagenSeleccionada);
                    formato = "bmp";
                } else {
                    formato=ext.equals(".pbm")?"P1":(ext.equals(".pgm")?"P2":"P3");
                    bmp = leerNETPBM(imagenSeleccionada);
                }
                formatoinit=formato;
                // Asignamos la imagen cargada a la propiedad imageActual
                imageActual = bmp;
                imageCopy   = copiarImagen(bmp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        // Retornamos la imagen
        return bmp;
    }

    public BufferedImage escalaGrises() {
        int   mediaPixel, colorSRGB;
        Color colorAux;
        //formato pgm
        formato = "P2";
        // Recorremos pixel a pixel
        for (int i = 0; i < imageActual.getWidth(); i++) {
            for (int j = 0; j < imageActual.getHeight(); j++) {
                // Almacenamos color del pixel
                colorAux = new Color(this.imageActual.getRGB(i, j));
                // Calculamos la media de los tres colores RGB
                mediaPixel = (int) ((colorAux.getRed() + colorAux.getGreen() + colorAux.getBlue()) / 3);
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
        //cuadro para cargar imagen
        selector.setDialogTitle("Guardar Como");
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("bmp , pbm , pgm y ppm", "pbm", "pgm", "ppm", "bmp");
        selector.setFileFilter(filtroImagen);
        int flag = selector.showSaveDialog(null);
        //verificar formato compatible
        if (flag == JFileChooser.APPROVE_OPTION) {
            try {
                String nombre = selector.getSelectedFile().getAbsolutePath();
                if(nombre.contains(".")){
                   nombre = nombre.substring(0, nombre.lastIndexOf("."));//le quito la extension que tenía
                }
                if(formato.equals("bmp")){
                   nombre = nombre.concat("."+formato);// concateno la extension
                   ImageIO.write(imageActual, "BMP" , new File(nombre));
                }else{
                    Map<String, String> extension = new HashMap<>();
                    extension.put("P1", ".pbm");
                    extension.put("P2", ".pgm");
                    extension.put("P3", ".ppm");
                    nombre = nombre.concat(extension.get(formato));//concateno la extension
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
                            if(formato.equals("P2")){
                                writer.println(co.getRed());
                            }else{//color
                                if(formato.equals("P1")){//binario
                                    writer.println(co.getRed()/255);
                                }else{
                                    writer.println(co.getRed() +" "+ co.getGreen()+" "+co.getBlue());
                                }  
                            }
                        }
                    }
                }

            } catch (IOException e) {
            }
        }
        return imageActual;
    }

    public BufferedImage RemoverFiltros() {
        imageActual = copiarImagen(imageCopy);
        formato=formatoinit;
        return imageActual;
    }
    
    public BufferedImage aplicarConvolucion(String conv_path){
        Convolucion conv = cargarConvolucion(conv_path);
        int newWidth = imageActual.getWidth() + imageActual.getHeight();
        BufferedImage out = new BufferedImage(imageActual.getWidth(), imageActual.getHeight(), imageActual.getType());
        
        Colorsin co = new Colorsin();
        for (int yy = 0; yy < out.getHeight(); yy++) {
            for (int xx = 0; xx < out.getWidth(); xx++) {
                float num = 0;
                for (int cy = yy - conv.pivoty; cy < conv.heigth; cy++) {//entramos loop convolucion
                    for (int cx = xx - conv.pivotx; cx < conv.width; cx++) {
                        try{
                            co.assignRGB(imageActual.getRGB(cx, cx));
                            num++;
                        }catch(Exception ex){
                            
                        }
                    }
                }//Salimos loop convolucion 
                
            }
        }
        return out;
        
    }
    public Convolucion cargarConvolucion (String conv_name){
        
        Convolucion out = null; 
        try(Scanner in = new Scanner(conv_name)){
            String line = in.nextLine();
            String[] split = line.split(" ");
            out = new Convolucion(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
            for(int yy = 0; yy < out.heigth; yy ++){
                split = in.nextLine().split(" ");
                for (int xx = 0; xx < out.width; xx++) {
                    out.conv[yy][xx] = Integer.parseInt(split[xx]);
                    System.out.println(split[xx]);
                }
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return out; 
    }
    
    public BufferedImage operarImagenes(BufferedImage other, int alpha){
        BufferedImage out = new BufferedImage(imageActual.getWidth(), imageActual.getHeight(), imageActual.getType());
        
        return out;
    }
//    public BufferedImage rotarLibre(){
//        //BufferedImage out = new BufferedImage(imageA);
//        
//    }

    public void CambiarUmbral(int u){
       Umbral=u;
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
        String  line;
        int width= -1,height= -1,imgType= -1;
        int[] pixelType={ BufferedImage.TYPE_BYTE_BINARY, BufferedImage.TYPE_USHORT_GRAY,BufferedImage.TYPE_INT_RGB };
        maxColores = 255;
        try (Scanner in = new Scanner(path)) {
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
                formato = ""+line;
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
                break;
            }
            if ((imgType == -1) || (width == -1) || (height == -1)) {
                throw new Exception("Imagen en formato maluco");
            }
            imgType--;
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
            int   readLength = (pixelType[imgType] == BufferedImage.TYPE_INT_RGB) ? 3: 1;
            int[] pixel = new int[readLength];
            int   pp = 0, ii = 0,aa = 0;
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
                        } else {
                            int extColor = pixel[0];
                            if(pixelType[imgType]== BufferedImage.TYPE_BYTE_BINARY){
                                extColor = extColor * 255;
                                out.setRGB(ii, pp, new Color(extColor, extColor , extColor).getRGB());
                            }else{
                                out.setRGB(ii, pp, new Color(extColor, extColor , extColor).getRGB());
                            }
                        }
                        ii++;
                        if (ii == width) {
                            ii = 0;
                            pp++;
                            if (pp > height) {
                                throw new Exception("Imagen mala brou");                              
                            }
                        }
                    }
                }
            }
        }
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
    // Se puede combinar con las keys de ContarColores para hacer algo
    
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

    public String ComprimirRLE(String x){ 
        String aux; 
        aux = "";
        int contador; 
        for (int i = 0; i < x.length(); i++) { 
            contador = 0; 
            char caracter = x.charAt(i); 
            while (i < x.length()&&caracter == x.charAt(i+1)) { //cuento caracteres iguales
                contador++; 
                i++; 
            }  
            aux += String.valueOf(contador) + String.valueOf(caracter);//agrego al string
        } 
        return aux; //retorno el string comprimido
    }
    
    public BufferedImage copiarImagen(BufferedImage in){
        BufferedImage aux= new BufferedImage(in.getWidth(),in.getHeight(),in.getType());
        for (int i = 0; i < in.getWidth(); i++) {
            for (int j = 0; j < in.getHeight(); j++) {
                aux.setRGB(i, j, in.getRGB(i,j));
            }
        }
        return aux;
    }
    
    public interface IntOperation{
        public int op(int a, int b);
    }
    
    public class Colorsin{
        int[] color;
        public Colorsin(int red, int green, int blue){
            color = new int[4];
            color[0] = red;
            color[1] = green;
            color[2] = blue;
        }
        public Colorsin(Colorsin co){
            color = new int[4];
            System.arraycopy(co.color, 0, this.color, 0, 3);
        }
        public Colorsin(){
            color = new int[4];
            color[0] =0;
            color[1]=0;
            color[2]=0;
        }
        
        public Color toColor(){
            return new Color(color[0], color[1], color[2]);
        }
        
        public int toRGB(){
            return toColor().getRGB();
        }
        
        public void assignRGB(int rgb){
            color[2]= rgb & 0xFF;
            color[1]= (rgb << 8) & 0xFF;
            color[0]= (rgb << 16) & 0xFF;
        }
        public Colorsin operate(IntOperation operation,Colorsin co){
            return new Colorsin(operation.op(this.color[0],co.color[0]), operation.op(this.color[1],co.color[1]), operation.op(this.color[2],co.color[2]));
        }
        public Colorsin sumarConstante(int cons){
            Colorsin out = new Colorsin(this);
            for (int ii = 0; ii < 4; ii++) {
                out.color[ii]+=cons;
            }
            return out;
        }
        
        public Colorsin multiplicarConstante(float cons){
            Colorsin out = new Colorsin(this);
            for (int ii = 0; ii < 4; ii++) {
                out.color[ii] *= cons;
            }
            return out;
        }
        
        public Colorsin clamp(){
            for (int ii = 0; ii < 4; ii++) {
                int aux = color[ii];
                aux = (aux > 255) ? 255: aux;
                aux = (aux < 0) ? 0: aux;
                color[ii] = aux;
            }
            return this;
        }
    }
    
    public class Convolucion{
        float[][] conv;
        int pivotx, width;
        int pivoty, heigth; 
        float sum;
        public Convolucion(int _width, int _heigth, int px, int py){
            conv = new float[_heigth][_width];
            width = _width;
            heigth = _heigth;
            pivotx = px;
            pivoty = py;
        }
        public float getSum(){
            this.sum = 0;
            for (int yy = 0; yy < heigth; yy++) {
                for (int xx = 0; xx < width; xx++) {
                    this.sum += conv[yy][xx];
                }
            }
            return this.sum;
        }
    }
    
    public Color PromColor(Color aux1,Color aux2){
        Color aux=new Color((aux1.getRed()+aux2.getRed())/2 , (aux1.getGreen()+aux2.getGreen())/2, (aux1.getBlue()+aux2.getBlue())/2 );
        return aux;
    }
    
    public BufferedImage ZoomIn(){
        int height,width,heightaux,widthaux;
        Color colorAux1,colorAux2;
        height=imageActual.getHeight();
        width=imageActual.getWidth();
        heightaux=height *2;
        widthaux=width *2;
        BufferedImage aux=new BufferedImage(widthaux,heightaux,imageActual.getType());
        int i , j,imod,jmod,k,l;
        k=0;
        l=0;
        for(i=0;i<widthaux;i++){
            imod=i%2;
            for(j=0;j<heightaux;j++){
                jmod=j%2;
                if(imod==0 && jmod==0){
                    aux.setRGB(i, j, imageActual.getRGB(k,l));
                    l++;
                    if(l==height){
                        l=0;
                        k++;
                    }
                }else{
                    if(j==heightaux-1 && imod==0){
                       aux.setRGB(i, j, aux.getRGB(i,j-1)); 
                    }else{
                        if(i==widthaux-1 && jmod==0){
                            aux.setRGB(i, j, aux.getRGB(i-1,j));
                        }else{
                            aux.setRGB(i,j,-1);
                        }
                    }
                }
            }
        }
        for(i=0;i<widthaux;i++){
            imod=i%2;
            for(j=0;j<heightaux;j++){
                jmod=j%2;
                if(jmod!=0 && j!=heightaux-1 && imod==0){
                    colorAux1 = new Color(aux.getRGB(i, j-1));
                    colorAux2 = new Color(aux.getRGB(i, j+1));
                    aux.setRGB(i,j, PromColor(colorAux1,colorAux2).getRGB());
                }else{
                    if(jmod==0 && imod!=0 && i!=widthaux-1){
                        colorAux1 = new Color(aux.getRGB(i-1, j));
                        colorAux2 = new Color(aux.getRGB(i+1, j));
                        aux.setRGB(i,j, PromColor(colorAux1,colorAux2).getRGB());
                    }else{
                        if(imod!=0 && jmod!=0){
                            colorAux1 = new Color(aux.getRGB(i, j-1));
                            colorAux2 = new Color(aux.getRGB(i-1, j));
                            aux.setRGB(i,j, PromColor(colorAux1,colorAux2).getRGB());
                        }
                    }
                }
            }
        }
        imageActual=aux;
        return imageActual;
    }
    
}
