/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package IEv2;

import org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U;
import static org.bytedeco.javacpp.opencv_core.cvCreateImage;
import static org.bytedeco.javacpp.opencv_core.cvGetSize;
import static org.bytedeco.javacpp.opencv_core.cvNot;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.THRESH_OTSU;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.cvThreshold;

public class IEFirstTarea {
    
    public static Mat SelectFilter(Mat data,int a){
        IplImage aux=new IplImage(data);
        Mat aux1;
        switch(a){
            case 1:
                 aux=GrayScale(aux);
            break;
            case 2:
                aux=BlackAndWhite(aux);
            break;
            case 3:
                aux=Negative(aux);
            break;
        }
        aux1=new Mat(aux);
        return aux1;
    }
    
    public static IplImage Negative(IplImage data){
        IplImage Negative=cvCreateImage(cvGetSize(data),IPL_DEPTH_8U,3);
        cvNot(data,Negative);
        return Negative;
    }
      
    public static IplImage BlackAndWhite(IplImage data){
        IplImage Gray,Black;
        Gray = GrayScale(data);
        Black = cvCreateImage(cvGetSize(data),IPL_DEPTH_8U,1);
        cvThreshold(Gray, Black, 0, 255, THRESH_OTSU);
        return Black;
    }
    
    public static IplImage GrayScale(IplImage data){
        IplImage Gray=cvCreateImage(cvGetSize(data),IPL_DEPTH_8U,1);
        cvCvtColor(data,Gray,CV_BGR2GRAY);
        return Gray;
    }
}
