/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IEv2;
import IEv2.IEv2UI.structurantShit;
import java.awt.image.BufferedImage;
import org.bytedeco.javacpp.helper.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_imgcodecs;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;


/**
 *
 * @author FliaMejias
 */
public class IEProcessor {
    
    public static Mat erode(Mat img, structurantShit SS){
//        erode(opencv_core.Mat src, opencv_core.Mat dst, opencv_core.Mat kernel, opencv_core.Point anchor, int iterations, int borderType, opencv_core.Scalar borderValue)
//\brief Erodes an image by using a specific structuring element.
        Mat out = new Mat(img.size(), img.type());
        opencv_imgproc.erode(img, out, SS.kernel);
        return out;
    }
    
        public static Mat dilate(Mat img, structurantShit SS){
//        erode(opencv_core.Mat src, opencv_core.Mat dst, opencv_core.Mat kernel, opencv_core.Point anchor, int iterations, int borderType, opencv_core.Scalar borderValue)
//\brief Erodes an image by using a specific structuring element.
        Mat out = new Mat(img.size(), img.type());
        opencv_imgproc.dilate(img, out, SS.kernel);
        return out;
    }
    
        public static Mat open(Mat img, structurantShit SS){
            Mat out = new Mat(img.size(), img.type());
            out = erode(dilate(img, SS), SS);
            return out;
        }
        
        public static BufferedImage ReduxBits (BufferedImage aux,int a){
            int colorAux;
            
            for (int i = 0; i < aux.getWidth(); i++) {
                for (int j = 0; j < aux.getHeight(); j++) {
                    // Almacenamos color del pixel
                    colorAux = aux.getRGB(i, j);
                    
                    colorAux = (colorAux <<  (8*a) );
                    colorAux = (colorAux >>  (8*a) );
                    
                    // Asignamos el nuevo valor al BufferedImage
                    aux.setRGB(i, j, colorAux);
                }
            }
            return aux;
        }
        
        public static Mat close(Mat img, structurantShit SS){
            Mat out = new Mat(img.size(), img.type());
            out = dilate(erode(img, SS), SS);
            return out;
        }
//    private static Mat getKernelFromShape(int elementSize, int elementShape) {
//        return Imgproc.getStructuringElement(elementShape, new
//        Size(elementSize*2+1, elementSize*2+1), new Point(elementSize,
//        elementSize) );
//    }
//    public Mat dilation(){
//
//    }
//    
//    public Mat aperture(){
//        
//    }
//    
//    public Mat closure(){
//        
//    }
    
}
