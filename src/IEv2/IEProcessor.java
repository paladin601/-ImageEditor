/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IEv2;
import IEv2.IEv2UI.structurantShit;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.bytedeco.javacpp.helper.opencv_core;
import static org.bytedeco.javacpp.opencv_core.CV_8U;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_imgcodecs;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.CV_THRESH_BINARY;
import static org.bytedeco.javacpp.opencv_imgproc.CV_THRESH_OTSU;
import static org.bytedeco.javacpp.opencv_imgproc.THRESH_OTSU;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.threshold;

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
        
        public static Mat GRAYSCALE(Mat img){
            Mat gray=new Mat(img.rows(), img.cols(), CV_8U);
            cvtColor(img,gray,CV_BGR2GRAY);
            return gray;
        }
        
        
        public static Mat OTSU(Mat img){
            Mat gray=GRAYSCALE(img);
            Mat out=new Mat(img.rows(), img.cols(), CV_8U);
            threshold(gray,out,0,255, THRESH_OTSU);
            return out;
        }
              
        public static Mat OTSU_UCV(Mat img){
            Mat aux,dst = new Mat(img.rows(), img.cols(), CV_8U);
            BufferedImage image;
            aux=GRAYSCALE(img);
            image=IEv2UI.toBufferedImage(img);
            int a=UmbralUCV(image) ;
            threshold( aux, dst, a, 255,0);
            return dst;
        } 
                
        private static int UmbralUCV(BufferedImage img) {
            int TPixel,A,B,Umbral;
            int[] histogram = new int[256];
            float sum,sumB,UmbralMax;
            UmbralMax = 0;
            Umbral = 0;
            TPixel = img.getHeight() * img.getWidth();
            sum = 0;
            sumB = 0; 
            A= 0;
            B = 0;
            for(int i=0; i<histogram.length; i++){
              histogram[i] = 0;  
            } 
            for(int i=0; i<img.getWidth(); i++) {
                for(int j=0; j<img.getHeight(); j++) {
                    int red = new Color(img.getRGB (i, j)).getRed();
                    histogram[red]++;
                }
            }
            for(int i=0; i<256; i++){
                sum += i * histogram[i];
            }

            for(int i=0 ; i<256 ; i++) {
                A += histogram[i];
                if(A == 0){
                    continue;
                }
                B = TPixel - A;
                if(B == 0){
                    break;
                }
                sumB += (float) (i * histogram[i]);
                float FB,FF; 
                FB= sumB / A;
                FF = (sum - sumB) / B;
                float UmbralM = (float) A * (float) B * (FB - FF) * (FB - FF);
                if(UmbralM > UmbralMax) {
                    UmbralMax = UmbralM;
                    Umbral = i;
                }
            }

            return Umbral;

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

    private static void cvThreshold(Mat gray, Mat out, int i, int i0, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
