/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageeditor;

import javax.swing.ImageIcon;

/**
 *
 * @author Leonardo
 */
public class ImageEditor extends javax.swing.JFrame {
    ProcesamientoImagen ObjProcesamiento=new ProcesamientoImagen();
    
    public ImageEditor() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        Close = new javax.swing.JButton();
        Umbral = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        UniqueColor = new javax.swing.JTextField();
        UmbralNumber = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Brillo = new javax.swing.JSlider();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        Contraste = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ContrasteNumber = new javax.swing.JSpinner();
        BrilloNumber = new javax.swing.JSpinner();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        UploadImage = new javax.swing.JMenuItem();
        SaveAs = new javax.swing.JMenuItem();
        Filter = new javax.swing.JMenu();
        GrayScale = new javax.swing.JMenuItem();
        BlackAndWhite = new javax.swing.JMenuItem();
        Negative = new javax.swing.JMenuItem();
        RemoveFilters = new javax.swing.JMenuItem();
        Rotate = new javax.swing.JMenu();
        Right = new javax.swing.JMenuItem();
        Left = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ZoomIn = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        Sobel = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jLabel1);

        Close.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Close.setText("Cerrar");
        Close.setFocusable(false);
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        Umbral.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        Umbral.setMajorTickSpacing(255);
        Umbral.setMaximum(255);
        Umbral.setPaintLabels(true);
        Umbral.setPaintTicks(true);
        Umbral.setValue(150);
        Umbral.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Umbral.setFocusable(false);
        Umbral.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                UmbralStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel2.setText("Colores Unicos");

        UniqueColor.setEditable(false);
        UniqueColor.setBackground(new java.awt.Color(255, 255, 255));
        UniqueColor.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        UniqueColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UniqueColor.setFocusable(false);

        UmbralNumber.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        UmbralNumber.setModel(new javax.swing.SpinnerNumberModel(150, 0, 255, 1));
        UmbralNumber.setToolTipText("");
        UmbralNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        UmbralNumber.setFocusable(false);
        UmbralNumber.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                UmbralNumberStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setText("Umbral");

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        Brillo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Brillo.setMajorTickSpacing(255);
        Brillo.setMaximum(255);
        Brillo.setPaintLabels(true);
        Brillo.setPaintTicks(true);
        Brillo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Contraste.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Contraste.setMajorTickSpacing(255);
        Contraste.setMaximum(255);
        Contraste.setPaintLabels(true);
        Contraste.setPaintTicks(true);
        Contraste.setValue(150);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel4.setText("Brillo");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel5.setText("Contraste");

        ContrasteNumber.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        ContrasteNumber.setModel(new javax.swing.SpinnerNumberModel(150, 0, 255, 1));
        ContrasteNumber.setToolTipText("");
        ContrasteNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ContrasteNumber.setFocusable(false);
        ContrasteNumber.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ContrasteNumberStateChanged(evt);
            }
        });

        BrilloNumber.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        BrilloNumber.setModel(new javax.swing.SpinnerNumberModel(150, 0, 255, 1));
        BrilloNumber.setToolTipText("");
        BrilloNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BrilloNumber.setFocusable(false);
        BrilloNumber.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                BrilloNumberStateChanged(evt);
            }
        });

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        UploadImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        UploadImage.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        UploadImage.setText("Cargar Imagen");
        UploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadImageActionPerformed(evt);
            }
        });
        jMenu1.add(UploadImage);

        SaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        SaveAs.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        SaveAs.setText("Guardar Imagen como...");
        SaveAs.setEnabled(false);
        SaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAsActionPerformed(evt);
            }
        });
        jMenu1.add(SaveAs);

        Filter.setText("Filtros");
        Filter.setEnabled(false);
        Filter.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        GrayScale.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        GrayScale.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        GrayScale.setText("Escala de Grises");
        GrayScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GrayScaleActionPerformed(evt);
            }
        });
        Filter.add(GrayScale);
        GrayScale.getAccessibleContext().setAccessibleDescription("");

        BlackAndWhite.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        BlackAndWhite.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        BlackAndWhite.setText("Blanco y Negro");
        BlackAndWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlackAndWhiteActionPerformed(evt);
            }
        });
        Filter.add(BlackAndWhite);

        Negative.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        Negative.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Negative.setText("Negativo");
        Negative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NegativeActionPerformed(evt);
            }
        });
        Filter.add(Negative);

        RemoveFilters.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        RemoveFilters.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        RemoveFilters.setText("Deshacer Filtros");
        RemoveFilters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveFiltersActionPerformed(evt);
            }
        });
        Filter.add(RemoveFilters);

        jMenu1.add(Filter);

        Rotate.setText("Rotar 90 grados");
        Rotate.setEnabled(false);
        Rotate.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        Right.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        Right.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Right.setText("Derecha");
        Right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightActionPerformed(evt);
            }
        });
        Rotate.add(Right);

        Left.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        Left.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Left.setText("Izquierda");
        Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeftActionPerformed(evt);
            }
        });
        Rotate.add(Left);

        jMenu1.add(Rotate);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Tarea2");

        ZoomIn.setText("Zoom In x2");
        ZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomInActionPerformed(evt);
            }
        });
        jMenu2.add(ZoomIn);

        jMenuItem1.setText("Zoom Out x2");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        Sobel.setText("Sobel");
        Sobel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SobelActionPerformed(evt);
            }
        });
        jMenu2.add(Sobel);

        jMenuItem2.setText("Brillo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Contraste");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Robert");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Prewit");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Laplaciano del Gaussiano");
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Filtro promedio");
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Filtro mediana");
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(218, 218, 218))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(UniqueColor, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Umbral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(UmbralNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Brillo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(BrilloNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Contraste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ContrasteNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))))
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Close)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UniqueColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Close)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Umbral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UmbralNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Brillo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BrilloNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addComponent(jLabel5)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Contraste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ContrasteNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void GrayScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GrayScaleActionPerformed
        jLabel1.setIcon(new ImageIcon(ObjProcesamiento.escalaGrises(true)));
        UniqueColor.setText(ObjProcesamiento.ContarColores().toString());
    }//GEN-LAST:event_GrayScaleActionPerformed

    private void UploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadImageActionPerformed
        jLabel1.setIcon(new ImageIcon(ObjProcesamiento.abrirImagen()));
        if(ObjProcesamiento.imagenCargada()){
            SaveAs.setEnabled(true);
            Filter.setEnabled(true);
            Rotate.setEnabled(true);
            Umbral.setValue(150);
            UmbralNumber.setValue(150);
            ObjProcesamiento.CambiarUmbral(150);
            UniqueColor.setText(ObjProcesamiento.ContarColores().toString());
        }
    }//GEN-LAST:event_UploadImageActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        System.exit(1);
    }//GEN-LAST:event_CloseActionPerformed

    private void SaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAsActionPerformed
        jLabel1.setIcon(new ImageIcon(ObjProcesamiento.guardarImagen()));
    }//GEN-LAST:event_SaveAsActionPerformed

    private void RemoveFiltersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveFiltersActionPerformed
        jLabel1.setIcon(new ImageIcon(ObjProcesamiento.RemoverFiltros()));
        UniqueColor.setText(ObjProcesamiento.ContarColores().toString());
    }//GEN-LAST:event_RemoveFiltersActionPerformed

    private void BlackAndWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BlackAndWhiteActionPerformed
        jLabel1.setIcon(new ImageIcon(ObjProcesamiento.BlancoYNegro(true)));
        UniqueColor.setText(ObjProcesamiento.ContarColores().toString());
    }//GEN-LAST:event_BlackAndWhiteActionPerformed

    private void NegativeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NegativeActionPerformed
       jLabel1.setIcon(new ImageIcon(ObjProcesamiento.Negativo()));
       UniqueColor.setText(ObjProcesamiento.ContarColores().toString());
    }//GEN-LAST:event_NegativeActionPerformed

    private void RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightActionPerformed
        jLabel1.setIcon(new ImageIcon(ObjProcesamiento.RotarDerecha()));
    }//GEN-LAST:event_RightActionPerformed

    private void LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftActionPerformed
        jLabel1.setIcon(new ImageIcon(ObjProcesamiento.RotarIzquierda()));
    }//GEN-LAST:event_LeftActionPerformed

    private void UmbralStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_UmbralStateChanged
        int a = Umbral.getValue();
        UmbralNumber.setValue(a);
        ObjProcesamiento.CambiarUmbral(a);
        jLabel1.setIcon(new ImageIcon(ObjProcesamiento.BlancoYNegro(false)));
    }//GEN-LAST:event_UmbralStateChanged

    private void UmbralNumberStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_UmbralNumberStateChanged
        int a = (Integer) UmbralNumber.getValue();
        Umbral.setValue(a);
        ObjProcesamiento.CambiarUmbral(a);
        jLabel1.setIcon(new ImageIcon(ObjProcesamiento.BlancoYNegro(false)));
    }//GEN-LAST:event_UmbralNumberStateChanged

    private void ZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZoomInActionPerformed
        jLabel1.setIcon(new ImageIcon(ObjProcesamiento.ZoomIn()));
    }//GEN-LAST:event_ZoomInActionPerformed

    private void SobelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SobelActionPerformed
        // TODO add your handling code here:ImageIcon(ObjProcesamiento.));
         jLabel1.setIcon(new ImageIcon(ObjProcesamiento.aplicarConvolucion("conv\\sobelhorinzontal.txt")));
    }//GEN-LAST:event_SobelActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       jLabel1.setIcon(new ImageIcon(ObjProcesamiento.ZoomOut()));
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void ContrasteNumberStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ContrasteNumberStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ContrasteNumberStateChanged

    private void BrilloNumberStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_BrilloNumberStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_BrilloNumberStateChanged

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImageEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ImageEditor().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem BlackAndWhite;
    private javax.swing.JSlider Brillo;
    private javax.swing.JSpinner BrilloNumber;
    private javax.swing.JButton Close;
    private javax.swing.JSlider Contraste;
    private javax.swing.JSpinner ContrasteNumber;
    private javax.swing.JMenu Filter;
    private javax.swing.JMenuItem GrayScale;
    private javax.swing.JMenuItem Left;
    private javax.swing.JMenuItem Negative;
    private javax.swing.JMenuItem RemoveFilters;
    private javax.swing.JMenuItem Right;
    private javax.swing.JMenu Rotate;
    private javax.swing.JMenuItem SaveAs;
    private javax.swing.JMenuItem Sobel;
    private javax.swing.JSlider Umbral;
    private javax.swing.JSpinner UmbralNumber;
    private javax.swing.JTextField UniqueColor;
    private javax.swing.JMenuItem UploadImage;
    private javax.swing.JMenuItem ZoomIn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
