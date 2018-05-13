/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IEv2;
import IEv2.IEv2UI.structurantShit;
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
    
    public static Mat erotion(Mat img, structurantShit SS){
//        erode(opencv_core.Mat src, opencv_core.Mat dst, opencv_core.Mat kernel, opencv_core.Point anchor, int iterations, int borderType, opencv_core.Scalar borderValue)
//\brief Erodes an image by using a specific structuring element.
        Mat out = new Mat(img.size(), img.type());
        opencv_imgproc.erode(img, out, SS.kernel);
        return out;
    }
    
//        public static Mat dilation(Mat img, int elementSize, int elementShape){
////        erode(opencv_core.Mat src, opencv_core.Mat dst, opencv_core.Mat kernel, opencv_core.Point anchor, int iterations, int borderType, opencv_core.Scalar borderValue)
////\brief Erodes an image by using a specific structuring element.
//        Mat out = null;
//        img.copyTo(out);
//        
////        Mat element = getKernelFromShape(elementSize, elementShape);
//        opencv_imgproc.dilate(img, out, element);
//        return out;
//    }
    
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
