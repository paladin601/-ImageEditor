package imageeditor;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.image.AffineTransformOp;
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
    private static BufferedImage imageActual;
    private static BufferedImage imageCopy;
    private static BufferedImage imageAnt;
    private int maxColores = 255;
    private int Umbral;
    private String formato;
    private String formatoinit;
    private int brillo =0;
    private int contraste=0;

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
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("bmp ,jpg, png pbm , pgm y ppm","jpg","png", "pbm", "pgm", "ppm","bmp");
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
                if (ext.equals(".bmp") || ext.equals(".png") || ext.equals(".jpg")) {
                    bmp = ImageIO.read(imagenSeleccionada);
                    formato = ext;
                    BufferedImage source = new BufferedImage(bmp.getWidth(),bmp.getHeight(), BufferedImage.TYPE_INT_RGB);
                    source.getGraphics().drawImage(bmp, 0, 0, null);
                    bmp=source;
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

    public BufferedImage escalaGrises(boolean a) {
        int   mediaPixel, colorSRGB;
        Color colorAux;
        BufferedImage aux= new BufferedImage(imageActual.getWidth(),imageActual.getHeight(),imageActual.getType());
        // Recorremos pixel a pixel
        for (int i = 0; i < imageActual.getWidth(); i++) {
            for (int j = 0; j < imageActual.getHeight(); j++) {
                // Almacenamos color del pixel
                colorAux = new Color(this.imageActual.getRGB(i, j));
                // Calculamos la media de los tres colores RGB
                mediaPixel = (int) ((colorAux.getRed() + colorAux.getGreen() + colorAux.getBlue()) / 3);
                colorSRGB = (mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;
                // Asignamos el nuevo valor al BufferedImage
                aux.setRGB(i, j, colorSRGB);
            }
        }
        // verificamos si es solo un cambio visual en la gui
        if(a==true){
          formato = "P2";
          imageActual=aux;
        }
        return aux;
    }

    public BufferedImage guardarImagen() {
        JFileChooser selector = new JFileChooser();
        //cuadro para cargar imagen
        selector.setDialogTitle("Guardar Como");
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("jpg, png ,bmp , pbm , pgm y ppm", "jpg","png" ,"pbm", "pgm", "ppm", "bmp");
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
        Convolucion conv = new Convolucion(conv_path);
        BufferedImage out = new BufferedImage(imageActual.getWidth(), imageActual.getHeight(), imageActual.getType());
        for (int yy = 0; yy < out.getHeight(); yy++) {
            for (int xx = 0; xx < out.getWidth(); xx++) {
                
                conv = conv.absorb(imageActual, xx, yy);     
                Colorsin co = new Colorsin(conv.getConvedValues());
                co = co.multiplicarConstante(1/conv.getConvSum());
                co = co.clamp();
                out.setRGB(xx, yy, co.toRGB());
                
            }
        }
        imageActual=out;
        return out;
    }
    public BufferedImage aplicarMediana(int sizeConvx, int sizeConvy, int pivotx, int pivoty){
        BufferedImage out = new BufferedImage(imageActual.getWidth(), imageActual.getHeight(), imageActual.getType());
        Convolucion conv = new Convolucion(sizeConvx,  sizeConvy,  pivotx,  pivoty);
        for (int yy = 0; yy < out.getHeight(); yy++) {
            for (int xx = 0; xx < out.getWidth(); xx++) {
                conv = conv.absorb(imageActual, xx, yy);     
                Colorsin co = new Colorsin(conv.mediana());
                out.setRGB(xx, yy, co.toRGB());
                
            }
        }
        imageActual=out;
        return out;
    }
    
    /*
    public BufferedImage[] histogramas(int px){
        BufferedImage[] out = new BufferedImage[3];
        int pixelHor = 3, maxAlto = 500, paddingVertical = 10, paddingHorizontal = 20, altoLinea = 5;
        int initHistoY = paddingVertical/2 + altoLinea;
        int finHistoy = padding/2;
        
        for(int jj = 0; jj < 3; jj++){
            /*
            el ancho de las barras del histograma es pixHor
            PEROOOO 
            el alto de cada barra será frecuencia / frecuenciaMaxima * maxAlto
            
            //el +10 es para darle un chance por arriba y por debajo de la vaina
            
            
            out[0] = new BufferedImage((pixelHor *  256), maxAlto + paddingVertical, imageActual.getType());
            
            for (int ii = 0; ii < ; ii++) {
                
            }
            
            
            for(int cc = 0; cc < 255; cc++){
                
            }
        }
    }
    */
    
    public BufferedImage filtroMedia(int sizex, int sizey, int pivotx, int pivoty){
        BufferedImage out = new BufferedImage(imageActual.getWidth(), imageActual.getHeight(), imageActual.getType());
        Convolucion conv = new Convolucion(sizex, sizey, pivotx, pivoty);//fill con 1s
        
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
    
    public BufferedImage BlancoYNegro(boolean a) {    // Ta mal
        int   Red;
        Color colorAux;
        BufferedImage aux;
        aux= escalaGrises(false);
        // Recorremos la imagen píxel a píxel
        for (int i = 0; i < aux.getWidth(); i++) {
            for (int j = 0; j < aux.getHeight(); j++) {
                // Almacenamos el color del píxel
                colorAux = new Color(aux.getRGB(i, j));
                Red= colorAux.getRed();
                Red= (Red > Umbral)? 255: 0;
                aux.setRGB(i, j, new Color(Red, Red, Red).getRGB());
            }
        }
        // verificamos si hay que guardar la imagen o solo fue un cambio visual en la gui
        if(a==true){
            formato = "P1";
            imageActual=aux;
        }
        return aux;
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
        System.out.println(path);
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
    
    public BufferedImage ZoomOut(){
        int height,width,heightaux,widthaux;
        height=imageActual.getHeight();
        width=imageActual.getWidth();
        heightaux=height /2;
        widthaux=width /2;
        BufferedImage aux=new BufferedImage(widthaux,heightaux,imageActual.getType());
        int i , j,imod,jmod;
        for(i=0;i<widthaux;i++){
            imod=i*2;
            for(j=0;j<heightaux;j++){
               jmod=j*2;
               aux.setRGB(i, j, imageActual.getRGB(imod,jmod));
            }
        }
        imageActual=aux;
        return imageActual;
    }
   
    public BufferedImage BrilloPN(boolean a){
        if(brillo>0){
            return BrilloPositive(a);
        }else{
            if(brillo<0){
                return BrilloNegative(a);
            }
        }
        return imageActual;
    }    
   
    public void CambiarBrillo(int u){
       brillo=u;
    }
   
    public BufferedImage BrilloPositive(boolean a) {
        Color colorAux;
        int red,green,blue;
        BufferedImage aux= new BufferedImage(imageActual.getWidth(),imageActual.getHeight(),imageActual.getType());
        // Recorremos pixel a pixel
        for (int i = 0; i < imageActual.getWidth(); i++) {
            for (int j = 0; j < imageActual.getHeight(); j++) {
                // Almacenamos color del pixel
                colorAux = new Color(this.imageActual.getRGB(i, j));
                // Calculamos la media de los tres colores RGB
                red=colorAux.getRed()+brillo;
                green=colorAux.getGreen()+brillo;
                blue=colorAux.getBlue()+brillo;
                red=(red>255)?255:red;
                green=(green>255)?255:green;
                blue=(blue>255)?255:blue;
                // Asignamos el nuevo valor al BufferedImage
                aux.setRGB(i, j, new Color(red,green,blue).getRGB());
            }
        }
        // verificamos si es solo un cambio visual en la gui
        if(a==true){
          imageActual=aux;
        }
        return aux;
    }
    
    public BufferedImage BrilloNegative(boolean a) {
        Color colorAux;
        int red,green,blue;
        BufferedImage aux= new BufferedImage(imageActual.getWidth(),imageActual.getHeight(),imageActual.getType());
        // Recorremos pixel a pixel
        for (int i = 0; i < imageActual.getWidth(); i++) {
            for (int j = 0; j < imageActual.getHeight(); j++) {
                // Almacenamos color del pixel
                colorAux = new Color(this.imageActual.getRGB(i, j));
                // Calculamos la media de los tres colores RGB
                red=colorAux.getRed()+brillo;
                green=colorAux.getGreen()+brillo;
                blue=colorAux.getBlue()+brillo;
                red=(red<0)?0:red;
                green=(green<0)?0:green;
                blue=(blue<0)?0:blue;
                // Asignamos el nuevo valor al BufferedImage
                aux.setRGB(i, j, new Color(red,green,blue).getRGB());
            }
        }
        // verificamos si es solo un cambio visual en la gui
        if(a==true){
          imageActual=aux;
        }
        return aux;
    }
    
        public BufferedImage Brillo(boolean a) {
        Color colorAux;
        Colorsin auxColorsin = new Colorsin();
        int red,green,blue;
        BufferedImage aux= new BufferedImage(imageActual.getWidth(),imageActual.getHeight(),imageActual.getType());
        // Recorremos pixel a pixel
        for (int i = 0; i < imageActual.getWidth(); i++) {
            for (int j = 0; j < imageActual.getHeight(); j++) {
                // Almacenamos color del pixel
                 
                colorAux = new Color(this.imageActual.getRGB(i, j));
                // Calculamos la media de los tres colores RGB
                red=colorAux.getRed()+brillo;
                green=colorAux.getGreen()+brillo;
                blue=colorAux.getBlue()+brillo;
                red=(red<0)?0:red;
                green=(green<0)?0:green;
                blue=(blue<0)?0:blue;
                // Asignamos el nuevo valor al BufferedImage
                aux.setRGB(i, j, new Color(red,green,blue).getRGB());
            }
        }
        // verificamos si es solo un cambio visual en la gui
        if(a==true){
          imageActual=aux;
        }
        return aux;
    }
    
    public BufferedImage ContrastePN(boolean a){
        if(contraste>1){
            return ContrastePositive(a);
        }else{
            if(contraste<0){
                return ContrasteNegative(a);
            }
        }
        return imageActual;
    }   
    
    public void CambiarContraste(int u){
       contraste=u;
    }
    
    public BufferedImage ContrastePositive(boolean a) {
        Color colorAux;
        int red,green,blue;
        BufferedImage aux= new BufferedImage(imageActual.getWidth(),imageActual.getHeight(),imageActual.getType());
        // Recorremos pixel a pixel
        for (int i = 0; i < imageActual.getWidth(); i++) {
            for (int j = 0; j < imageActual.getHeight(); j++) {
                // Almacenamos color del pixel
                colorAux = new Color(this.imageActual.getRGB(i, j));
                // Calculamos la media de los tres colores RGB
                red=colorAux.getRed()/contraste;
                green=colorAux.getGreen()/contraste;
                blue=colorAux.getBlue()/contraste;
                // Asignamos el nuevo valor al BufferedImage
                aux.setRGB(i, j, new Color(red,green,blue).getRGB());
            }
        }
        // verificamos si es solo un cambio visual en la gui
        if(a==true){
          imageActual=aux;
        }
        return aux;
    }
    
    public BufferedImage ContrasteNegative(boolean a) {
        Color colorAux;
        int red,green,blue;
        BufferedImage aux= new BufferedImage(imageActual.getWidth(),imageActual.getHeight(),imageActual.getType());
        // Recorremos pixel a pixel
        for (int i = 0; i < imageActual.getWidth(); i++) {
            for (int j = 0; j < imageActual.getHeight(); j++) {
                // Almacenamos color del pixel
                colorAux = new Color(this.imageActual.getRGB(i, j));
                // Calculamos la media de los tres colores RGB
                red=colorAux.getRed()*contraste*-1;
                green=colorAux.getGreen()*contraste*-1;
                blue=colorAux.getBlue()*contraste*-1;
                red=(red>255)?255:red;
                green=(green>255)?255:green;
                blue=(blue>255)?255:blue;
                // Asignamos el nuevo valor al BufferedImage
                aux.setRGB(i, j, new Color(red,green,blue).getRGB());
            }
        }
        // verificamos si es solo un cambio visual en la gui
        if(a==true){
          imageActual=aux;
        }
        return aux;
    }
    
    public static BufferedImage rotacionImagen(double grados,boolean a) {
        BufferedImage aux;
        //utilizo la copia porque el movimiento es relativo a la imagen que le paso se toma el centro si se usa la actual y se actualiza la actual explota jaja
        ImageTransform imgtrans = new ImageTransform(imageActual.getHeight(), imageActual.getWidth());
        imgtrans.cambiarGrados(grados);
        imgtrans.findRotacion();
        AffineTransformOp aux2 = new AffineTransformOp(imgtrans.getRotacion(), AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        aux = aux2.createCompatibleDestImage(imageActual, imageActual.getColorModel());
        aux=aux2.filter(imageActual, aux);
        if(a==true){
          imageActual=aux;
        }
        return aux;
    }
    
}
