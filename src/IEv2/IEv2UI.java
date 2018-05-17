/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IEv2;

import java.awt.Graphics2D;
import java.io.File;
import java.util.Stack;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_imgproc;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.indexer.IntRawIndexer;
import org.bytedeco.javacpp.indexer.UByteRawIndexer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacv.Java2DFrameConverter;
import static org.bytedeco.javacv.Java2DFrameUtils.deepCopy;
import org.bytedeco.javacv.OpenCVFrameConverter;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvConvertImage;
/**
 *
 * @author FliaMejias
 */
public class IEv2UI extends javax.swing.JPanel {
    static Stack<Mat> cntrlz = new Stack<>();
    static Stack<Mat> cntrly = new Stack<>();
    Mat original;
    Mat copy;
    static int Contz=0;
    static int Conty=0;
    static int Redux=1;
    static final int maxCntrl = 2;
    String dir = "";
    private static final OpenCVFrameConverter.ToMat       matConv = new OpenCVFrameConverter.ToMat();
    private static final Java2DFrameConverter             biConv  = new Java2DFrameConverter();

    /*
     * Creates new form IEv2UI
     */
    public IEv2UI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabbedTareas = new javax.swing.JTabbedPane();
        T1 = new javax.swing.JPanel();
        FilterFirstTarea = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        T2 = new javax.swing.JPanel();
        T3 = new javax.swing.JPanel();
        Cuantizacion = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        FiltroMorfologico = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        UmbralAutomatico = new javax.swing.JComboBox<>();
        CustomMorfologico = new java.awt.TextArea();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        BitsRedux = new javax.swing.JSpinner();
        T4 = new javax.swing.JPanel();
        TabbedHistogramas = new javax.swing.JTabbedPane();
        HistogramaR = new java.awt.Canvas();
        HistogramaG = new java.awt.Canvas();
        HistogramaB = new java.awt.Canvas();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        CargarImagen = new javax.swing.JButton();
        GuardarImagen = new javax.swing.JButton();
        canvas4 = new java.awt.Canvas();
        ContainerImage = new javax.swing.JScrollPane();
        ImageDisplay = new javax.swing.JLabel();
        ctrlz = new javax.swing.JButton();
        Ctrly = new javax.swing.JButton();

        setBackground(new java.awt.Color(142, 174, 189));

        TabbedTareas.setBackground(new java.awt.Color(0, 0, 0));

        T1.setBackground(new java.awt.Color(48, 65, 82));
        T1.setAlignmentX(0.0F);
        T1.setAlignmentY(0.0F);

        FilterFirstTarea.setMaximumRowCount(3);
        FilterFirstTarea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco y Negro", "Escala de Grises", "Negativo" }));
        FilterFirstTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterFirstTareaActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Filtros");

        javax.swing.GroupLayout T1Layout = new javax.swing.GroupLayout(T1);
        T1.setLayout(T1Layout);
        T1Layout.setHorizontalGroup(
            T1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(T1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(FilterFirstTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        T1Layout.setVerticalGroup(
            T1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(T1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(T1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilterFirstTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(218, Short.MAX_VALUE))
        );

        TabbedTareas.addTab("T1", T1);
        T1.getAccessibleContext().setAccessibleName("T1");

        T2.setBackground(new java.awt.Color(48, 65, 82));
        T2.setAlignmentX(0.0F);
        T2.setAlignmentY(0.0F);

        javax.swing.GroupLayout T2Layout = new javax.swing.GroupLayout(T2);
        T2.setLayout(T2Layout);
        T2Layout.setHorizontalGroup(
            T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );
        T2Layout.setVerticalGroup(
            T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        TabbedTareas.addTab("T2", T2);

        T3.setBackground(new java.awt.Color(48, 65, 82));
        T3.setAlignmentX(0.0F);
        T3.setAlignmentY(0.0F);

        Cuantizacion.setMaximumRowCount(3);
        Cuantizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paleta", "Redux bits", "K-mean" }));
        Cuantizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CuantizacionActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cuantización");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Umbral automático");

        FiltroMorfologico.setMaximumRowCount(3);
        FiltroMorfologico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Erosión", "Dilatación", "Apertura", "Cierre" }));
        FiltroMorfologico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltroMorfologicoActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Filtro Morfologíco");

        UmbralAutomatico.setMaximumRowCount(3);
        UmbralAutomatico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OTSU", "OTSUCV", "OTRO METODO" }));
        UmbralAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UmbralAutomaticoActionPerformed(evt);
            }
        });

        CustomMorfologico.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jSeparator4.setBackground(new java.awt.Color(207, 103, 102));

        jSeparator5.setBackground(new java.awt.Color(207, 103, 102));

        jSeparator6.setBackground(new java.awt.Color(207, 103, 102));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Custom");

        BitsRedux.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));

        javax.swing.GroupLayout T3Layout = new javax.swing.GroupLayout(T3);
        T3.setLayout(T3Layout);
        T3Layout.setHorizontalGroup(
            T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(T3Layout.createSequentialGroup()
                .addGroup(T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(T3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator6)
                            .addComponent(CustomMorfologico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(T3Layout.createSequentialGroup()
                        .addGroup(T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(T3Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(T3Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(50, 50, 50)
                                        .addComponent(FiltroMorfologico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(T3Layout.createSequentialGroup()
                                        .addGroup(T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, 18)
                                        .addGroup(T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(UmbralAutomatico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, T3Layout.createSequentialGroup()
                                                .addComponent(Cuantizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1)))
                                        .addGap(27, 27, 27)
                                        .addComponent(BitsRedux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(T3Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel4)))
                        .addGap(0, 51, Short.MAX_VALUE)))
                .addContainerGap())
        );
        T3Layout.setVerticalGroup(
            T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(T3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cuantizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(BitsRedux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(UmbralAutomatico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(FiltroMorfologico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CustomMorfologico, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedTareas.addTab("T3", T3);

        T4.setBackground(new java.awt.Color(48, 65, 82));
        T4.setAlignmentX(0.0F);
        T4.setAlignmentY(0.0F);
        T4.setEnabled(false);

        javax.swing.GroupLayout T4Layout = new javax.swing.GroupLayout(T4);
        T4.setLayout(T4Layout);
        T4Layout.setHorizontalGroup(
            T4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );
        T4Layout.setVerticalGroup(
            T4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        TabbedTareas.addTab("T4", T4);
        T4.getAccessibleContext().setAccessibleName("T4");

        TabbedHistogramas.setBackground(new java.awt.Color(3, 20, 36));
        TabbedHistogramas.addTab("R", HistogramaR);
        TabbedHistogramas.addTab("G", HistogramaG);
        TabbedHistogramas.addTab("B", HistogramaB);

        jSeparator1.setBackground(new java.awt.Color(207, 103, 102));

        jSeparator2.setBackground(new java.awt.Color(207, 103, 102));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setBackground(new java.awt.Color(207, 103, 102));

        CargarImagen.setText("Cargar");
        CargarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarImagenActionPerformed(evt);
            }
        });

        GuardarImagen.setText("Guardar");
        GuardarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarImagenActionPerformed(evt);
            }
        });

        ContainerImage.setBackground(new java.awt.Color(255, 255, 255));
        ContainerImage.setToolTipText("");

        ImageDisplay.setBackground(new java.awt.Color(255, 255, 255));
        ImageDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImageDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ImageDisplay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ContainerImage.setViewportView(ImageDisplay);

        ctrlz.setText("Deshacer");
        ctrlz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctrlzActionPerformed(evt);
            }
        });

        Ctrly.setText("Rehacer");
        Ctrly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CtrlyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TabbedHistogramas)
                    .addComponent(TabbedTareas)
                    .addComponent(jSeparator1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(Ctrly)
                                .addGap(18, 18, 18)
                                .addComponent(ctrlz)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 373, Short.MAX_VALUE)
                                .addComponent(CargarImagen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GuardarImagen)
                                .addGap(22, 22, 22))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(ContainerImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(canvas4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabbedTareas, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabbedHistogramas)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(449, 449, 449)
                                .addComponent(canvas4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ContainerImage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CargarImagen)
                            .addComponent(GuardarImagen)
                            .addComponent(ctrlz)
                            .addComponent(Ctrly))
                        .addGap(5, 5, 5))
                    .addComponent(jSeparator2))
                .addContainerGap())
        );

        TabbedTareas.getAccessibleContext().setAccessibleName("TabbedTareas");
        TabbedHistogramas.getAccessibleContext().setAccessibleName("TabbedHistograma");
    }// </editor-fold>//GEN-END:initComponents

    private void CuantizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CuantizacionActionPerformed
        // TODO add your handling code here:
        String content = Cuantizacion.getSelectedItem().toString();
        //probando
        Contz=pushToStack(cntrlz,copy,Contz);
        switch (content.toUpperCase()) {
            case "PALETA":

                break;
            case "REDUX BITS":
                BitsRedux.setEnabled(true);
                Redux=1;
                BitsRedux.setValue(Redux);
                copy =IEProcessor.ReduxBits(copy, Redux);
                display(copy);
                break;
            case "K-MEAN": // No Funciona mosca
                //Mat samples=copy.clone();
                Mat samples = new Mat(copy.rows(),copy.cols(),CV_32F);
                copy.convertTo(samples, CV_32F);

                int cluster_count = 2;// K 
                int attempts = 10;
                TermCriteria termCriteria = new TermCriteria(CV_TERMCRIT_ITER|CV_TERMCRIT_EPS, 10, 1.0);

                /*cvReshape(src, src.asCvMat(), 1, src.height() * src.width());
                IplImage samples = cvCreateImage(cvGetSize(src), src.depth(), 1);
                cvConvertImage(src, samples, CV_32F);

                IplImage labels = cvCreateImage(new CvSize(samples.height()), 1, CV_8U);
                IplImage centers = cvCreateImage(new CvSize(cluster_count), 1, CV_32F);
                */
                Mat labels = new Mat(samples.rows(), samples.cols(), CV_8U);
                Mat centers = new Mat(cluster_count, 1, CV_32F);
                try{
                kmeans(samples, cluster_count, labels, termCriteria,attempts, KMEANS_RANDOM_CENTERS, centers);
                display(samples);
                }catch(Exception excepcion){
                    System.out.println("ERROR :" + excepcion);
                    int i=0;
                    i++;
                }

                
                break;
            case "":
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_CuantizacionActionPerformed

    
   public structurantShit extractCustomStructurantElement(){
       int anchorx = 2, anchory = 2, width = 5, heigth = 5;
       int structurantType = opencv_imgproc.MORPH_ELLIPSE;
       Mat out;
       String structurantTxt = this.CustomMorfologico.getText();
       String[] lines = structurantTxt.split("\n");
       for (int ii = 0; ii < lines.length; ii++) {
           lines[ii] = lines[ii].trim();
       }
       
       
       String[] params = (lines.length == 0) ? null : lines[0].split(" ");
       if(lines.length <= 1){// En caso de que escoja algun predefinido------------------------------------------------------
           
            if(lines.length == 1){
                if(params.length > 0) {// Si quiero darle size y poner pivot del elemento estructurante

                    if(params.length == 5){
                         width = Integer.parseInt(params[1]);
                         heigth = Integer.parseInt(params[2]);
                         anchorx = Integer.parseInt(params[3]);
                         anchory = Integer.parseInt(params[4]);           
                    }else if (params[0].equals("")){
                        System.out.println("faltan parametros, se toma el elemento estructurante default");
                        params[0] = String.valueOf(structurantType);
                    }
                   
                    switch(Integer.parseInt(params[0])){
                        case 0:
                            structurantType = opencv_imgproc.MORPH_CROSS;
                            System.out.println("MORPH_CROSS");
                            break;
                        case 1:
                            structurantType = opencv_imgproc.MORPH_RECT;
                            System.out.println("MORPH_RECT");

                            break;
                        case 2:
                            structurantType = opencv_imgproc.MORPH_ELLIPSE;
                           System.out.println("MORPH_ELLIPSE");

                           break;
                           default:
                               System.out.println("Bad input, se pone por default un elemento\n"
                                       + "estructurante eliptico 5 x 5 ");
                   }
                }//******************************************************************************************
            }
           out = opencv_imgproc.getStructuringElement(structurantType, new opencv_core.Size( width, heigth ),
                                       new opencv_core.Point( anchorx, anchory ) );
           
            UByteRawIndexer ix = out.createIndexer();
           return new structurantShit(anchorx, anchory, width, heigth, out);
       
       }else{// en caso de que se cree su propio elemento estructurante -------------------------------------------------
            structurantType = opencv_imgproc.CV_SHAPE_CUSTOM;
            
            for (int ii = 0; ii < lines.length; ii++) {//limpiar vaina
               lines[ii] = lines[ii].trim();
           }
            
           if(params.length == 4){
               
                width = Integer.parseInt(params[0]);
                heigth = Integer.parseInt(params[1]);
                anchorx = Integer.parseInt(params[2]);
                anchory = Integer.parseInt(params[3]);
                
                out = new Mat(heigth, width, 0);
                out.asByteBuffer();
                UByteRawIndexer ix = out.createIndexer();
                
               for (int yy = 0; yy < out.rows(); yy++)  {
                    
                    String[] input; //el split 0 es el de los params
                    input = lines[yy+1].split(" ");
                    
                     for (int xx = 0; xx < out.cols(); xx++) {  
                        ix.put(yy, xx, Integer.parseInt(input[xx]));
                    }
               }
                return new structurantShit(anchorx, anchory, width, heigth, out);
            }
            else{
                System.out.println("Bad input");
            }

           
           

       }
       return null;
   }
    
    
    private void FiltroMorfologicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltroMorfologicoActionPerformed
         // TODO add your handling code here:
        
        String content = this.FiltroMorfologico.getSelectedItem().toString();
        structurantShit structurantElement = extractCustomStructurantElement();
             
        Contz=pushToStack(cntrlz, this.copy,Contz);
        switch (content.toUpperCase()) {
            case "EROSIÓN":
                this.copy = IEProcessor.erode(copy, structurantElement);
                break;
            case "DILATACIÓN":
                this.copy = IEProcessor.dilate(copy, structurantElement);
                break;
            case "APERTURA":
                this.copy = IEProcessor.open(copy, structurantElement);
                break;
            case "CIERRE":
                this.copy = IEProcessor.close(copy, structurantElement);
                break;
            default:
                throw new AssertionError();
        }
        display(this.copy);
    }//GEN-LAST:event_FiltroMorfologicoActionPerformed

    private void UmbralAutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UmbralAutomaticoActionPerformed
        // TODO add your handling code here:
        String content = this.UmbralAutomatico.getSelectedItem().toString();
        Contz=pushToStack(cntrlz, this.copy,Contz);
        switch (content.toUpperCase()) {
            case "OTSU":
                copy=IEProcessor.OTSU(copy);
                display(copy);
                break;
            case "OTSUCV":
                copy=IEProcessor.OTSU_UCV(copy);
                display(copy);
                break;
            case "OTRO METODO":
                copy=IEProcessor.OTHER(copy,IEProcessor.UmbralProm(toBufferedImage(copy)));
                display(copy);
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_UmbralAutomaticoActionPerformed

    private void CargarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarImagenActionPerformed
       // TODO add your handling code here:
        JFileChooser fChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fChooser.setFileFilter(new FileNameExtensionFilter("bmp", "bmp"));        
        fChooser.setFileFilter(new FileNameExtensionFilter("png", "png"));
        fChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
        if (fChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File fSelected = fChooser.getSelectedFile();
            
            //Cargar
            this.original = opencv_imgcodecs.imread((fSelected.getAbsolutePath()));
            this.copy = this.original.clone();
            Contz=0;
            Conty=0;
            display(this.copy);
        }  
    }//GEN-LAST:event_CargarImagenActionPerformed

    public void display(Mat img){
        this.copy = img;
        ImageDisplay.setIcon(new ImageIcon(toBufferedImage(img)));
    }
    
    public synchronized static BufferedImage toBufferedImage(Mat src) {
        return deepCopy(biConv.getBufferedImage(matConv.convert(src).clone()));
    }
    
    public synchronized static Mat toMat(BufferedImage src){
        return matConv.convertToMat(biConv.convert(src)).clone();
    }
    
    private void GuardarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarImagenActionPerformed
        // TODO add your handling code here:
        JFileChooser fChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fChooser.setFileFilter(new FileNameExtensionFilter("bmp", "bmp"));        
        fChooser.setFileFilter(new FileNameExtensionFilter("png", "png"));
        fChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));

        if( fChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            
            
            File fSelected = fChooser.getSelectedFile();
            
            String name = fSelected.getAbsolutePath();
            if(!name.endsWith(".jpg") || !name.endsWith(".bmp") || !name.endsWith(".png")){
                name = name + "." + fChooser.getFileFilter().getDescription();
            }
            //Guardar imagen
            opencv_imgcodecs.imwrite(name, this.copy);
            
        }
    }//GEN-LAST:event_GuardarImagenActionPerformed

    private void FilterFirstTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterFirstTareaActionPerformed
        String content = this.FilterFirstTarea.getSelectedItem().toString();
        
        Contz=pushToStack(cntrlz,copy,Contz);
        switch (content.toUpperCase()) {
            case "ESCALA DE GRISES":
                this.copy=IEFirstTarea.SelectFilter(copy, 1);
                break;
            case "BLANCO Y NEGRO":
                this.copy=IEFirstTarea.SelectFilter(copy, 2);
                break;
            case "NEGATIVO":
                this.copy=IEFirstTarea.SelectFilter(copy, 3);
                break;
            default:
                throw new AssertionError();
        }
        display(this.copy);
    }//GEN-LAST:event_FilterFirstTareaActionPerformed

    private void ctrlzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctrlzActionPerformed
        Contz=Contz-1;
        if(!cntrlz.isEmpty() && Contz>-1){
            Conty=pushToStack(cntrly,copy,Conty);
            this.copy=ObtainImage(cntrlz,Contz);
            display(copy);
        }else{
            Contz=0;
        }
    }//GEN-LAST:event_ctrlzActionPerformed

    private void CtrlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CtrlyActionPerformed
                Conty=Conty-1;
        if(!cntrly.isEmpty() && Conty>-1){
            Contz++;
            this.copy=ObtainImagey(cntrly,Conty);
            display(copy);
        }else{
            Conty=0;
        }
    }//GEN-LAST:event_CtrlyActionPerformed

    public static Mat ObtainImage(Stack<Mat> stack,int a){
        return stack.get(a);
    }
    public static Mat ObtainImagey(Stack<Mat> stack,int a){
        Mat aux=stack.get(a);
        stack.removeElementAt(stack.size()-1);
        return aux;
    }
    
     private void BitsReduxStateChanged(javax.swing.event.ChangeEvent evt) {                                       
        Contz=pushToStack(cntrlz,copy,Contz);
        Redux = (Integer) BitsRedux.getValue();
        copy =IEProcessor.ReduxBits(copy, Redux);
        display(copy);
    } 
    
public static int pushToStack(Stack<Mat> stack, Mat data,int Cont){
   int aux=stack.size();
    if(aux>Cont){
       for(int i=0;i<aux-Cont;i++){
           stack.removeElementAt(aux-1);
           aux=stack.size();
       }
       while(!cntrly.isEmpty()){
           cntrly.removeElementAt(cntrly.size()-1);
       }
       Conty=0;
    }
    stack.push(new Mat(data));
    Cont++;
    if(aux > maxCntrl){
        stack.removeElementAt(0);
        Cont--;
    }
    return Cont;
}

public class structurantShit{
    int anchorx, anchory, width, heigth;
    Mat kernel;

    public structurantShit(int anchorx, int anchory, int width, int heigth, Mat kernel) {
        this.anchorx = anchorx;
        this.anchory = anchory;
        this.width = width;
        this.heigth = heigth;
        this.kernel = kernel;
    }
    
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner BitsRedux;
    private javax.swing.JButton CargarImagen;
    private javax.swing.JScrollPane ContainerImage;
    private javax.swing.JButton Ctrly;
    private javax.swing.JComboBox<String> Cuantizacion;
    private java.awt.TextArea CustomMorfologico;
    private javax.swing.JComboBox<String> FilterFirstTarea;
    private javax.swing.JComboBox<String> FiltroMorfologico;
    private javax.swing.JButton GuardarImagen;
    private java.awt.Canvas HistogramaB;
    private java.awt.Canvas HistogramaG;
    private java.awt.Canvas HistogramaR;
    private javax.swing.JLabel ImageDisplay;
    private javax.swing.JPanel T1;
    private javax.swing.JPanel T2;
    private javax.swing.JPanel T3;
    private javax.swing.JPanel T4;
    private javax.swing.JTabbedPane TabbedHistogramas;
    private javax.swing.JTabbedPane TabbedTareas;
    private javax.swing.JComboBox<String> UmbralAutomatico;
    private java.awt.Canvas canvas4;
    private javax.swing.JButton ctrlz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    // End of variables declaration//GEN-END:variables
}
