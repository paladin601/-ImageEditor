 
package imageeditor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Luis
 */
public class ProcesamientoImagen {
    
    //Imagen actual que se ha cargado
    private BufferedImage imageActual;
    private BufferedImage imageCopy;
    private int Bits;
    private int NumberColors;
    private int Umbral;
    
    
    //Método que devuelve una imagen abierta desde archivo
    //Retorna un objeto BufferedImagen
    public BufferedImage abrirImagen(){
        //Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp=null;
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector=new JFileChooser();
        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP", "jpg", "gif", "bmp");
        selector.setFileFilter(filtroImagen);
        //Abrimos el cuadro de diálog
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION){
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada=selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);
                imageCopy=ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }
                 
        }
        //Asignamos la imagen cargada a la propiedad imageActual
        imageActual=bmp;
        Bits=(imageActual.getColorModel().getPixelSize())/3;
        NumberColors=(int) Math.pow(2,Bits);
        Umbral=(NumberColors/2)-1;
        //Retornamos el valor
        return bmp;
    }
    
    public BufferedImage escalaGrises(){
        //Variables que almacenarán los píxeles
        int mediaPixel,colorSRGB;
        Color colorAux;
        //Recorremos la imagen píxel a píxel
        for( int i = 0; i < imageActual.getWidth(); i++ ){
            for( int j = 0; j < imageActual.getHeight(); j++ ){
                //Almacenamos el color del píxel
                colorAux=new Color(this.imageActual.getRGB(i, j));
                //Calculamos la media de los tres canales (rojo, verde, azul)
                mediaPixel=(int)((colorAux.getRed()+colorAux.getGreen()+colorAux.getBlue())/3);
                //Cambiamos a formato sRGB
                colorSRGB=(mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;
                //Asignamos el nuevo valor al BufferedImage
                imageActual.setRGB(i, j,colorSRGB);
            }
        }
        //Retornamos la imagen
        return imageActual;
    }
    
    public BufferedImage guardarImagen(){
        JFileChooser selector=new JFileChooser();
        selector.setDialogTitle("Guardar Como");
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP", "jpg", "gif", "bmp");
        selector.setFileFilter(filtroImagen);
        int flag=selector.showSaveDialog(null);
           if(flag==JFileChooser.APPROVE_OPTION){
            try {
                ImageIO.write(imageActual, "", selector.getSelectedFile());
            }catch(Exception e){
                 
            }
        }
        return imageActual;
    }

    public BufferedImage RemoverFiltros() {
        /*FUNCIONA UNA SOLA VEZ*/
        imageActual=imageCopy;
        return imageActual;
    }

    public BufferedImage BlancoYNegro() {
        int Red,Green,Blue;
        Color colorAux;
        //Recorremos la imagen píxel a píxel
        for( int i = 0; i < imageActual.getWidth(); i++ ){
            for( int j = 0; j < imageActual.getHeight(); j++ ){
                //Almacenamos el color del píxel
                colorAux=new Color(this.imageActual.getRGB(i, j));
                Red=colorAux.getRed();
                Green=colorAux.getGreen();
                Blue=colorAux.getBlue();
                if(Red > Umbral){
                    Red=(NumberColors - 1);
                }else{
                    Red=0;
                }
                if(Green > Umbral){
                    Green=(NumberColors - 1);
                }else{
                    Green=0;
                }
                if(Blue > Umbral){
                    Blue=(NumberColors - 1);
                }else{
                    Blue=0;
                }
     /*           if((Red == 255 && Green==255 && Blue==0) || (Red == 0 && Green==255 && Blue==255) || (Red == 255 && Green==0 && Blue==255) ){
                    Red=255;
                    Green=255;
                    Blue=255;
                }else{
                    Red=0;
                    Green=0;
                    Blue=0;
                }*/
                imageActual.setRGB(i,j,new Color(Red,Green,Blue).getRGB());
            }
        }
        //Retornamos la imagen
        return imageActual;
    }
}
