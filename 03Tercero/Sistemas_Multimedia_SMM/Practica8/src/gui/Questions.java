package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import sm.cdlt.graphics.geoPoint;
import sm.cdlt.graphics.myRectangle;

/**
 * This class For contact with me visit https://www.sudano.net or send me a
 * email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 24-abr-2016
 */
public class Questions extends javax.swing.JDialog {

    /**
     * Tamaño maximo para las imagenes
     */
    private final int MAX_PIXEL_IMAGE = 4000;

    /**
     * Creates new form SizeImage
     */
    public Questions(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        // colocamos el cuadro de dialogo en el centro
        this.setLocation((int) this.getParent().getWidth() / 2 - (int) this.getWidth() / 2, (int) this.getParent().getHeight() / 2 - (int) this.getHeight() / 2);
        // Ponemos el tamaño predeterminado para la imagen
        this.jSpinnerWidth.setValue(VentanaPrincipal.MAX_VALUE_SPPINER);
        this.jSpinnerHeigth.setValue(VentanaPrincipal.MAX_VALUE_SPPINER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelButtons = new javax.swing.JPanel();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jLabelDescription = new javax.swing.JLabel();
        jPanelContent = new javax.swing.JPanel();
        jLabelWidth = new javax.swing.JLabel();
        jSpinnerWidth = new javax.swing.JSpinner();
        jLabelHeigth = new javax.swing.JLabel();
        jSpinnerHeigth = new javax.swing.JSpinner();
        jLabelIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(380, 220));
        setResizable(false);

        jPanelButtons.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanelButtons.setPreferredSize(new java.awt.Dimension(45, 45));
        jPanelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("interna/Bundle"); // NOI18N
        jButtonOk.setText(bundle.getString("BUTTON_OK")); // NOI18N
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonOk);

        jButtonCancel.setText(bundle.getString("BUTTON_CANCEL")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonCancel);

        getContentPane().add(jPanelButtons, java.awt.BorderLayout.SOUTH);

        jLabelDescription.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabelDescription.setText(bundle.getString("QUESTION_DESCRIPTION")); // NOI18N
        jLabelDescription.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 5, 5));
        jLabelDescription.setFocusable(false);
        getContentPane().add(jLabelDescription, java.awt.BorderLayout.NORTH);

        jPanelContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 40, 5));
        jPanelContent.setLayout(new java.awt.GridLayout(0, 2, 0, 10));

        jLabelWidth.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabelWidth.setText(bundle.getString("WIDTH")); // NOI18N
        jPanelContent.add(jLabelWidth);

        jSpinnerWidth.setOpaque(false);
        jSpinnerWidth.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jSpinnerWidthMouseWheelMoved(evt);
            }
        });
        jPanelContent.add(jSpinnerWidth);

        jLabelHeigth.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabelHeigth.setText(bundle.getString("HEIGTH")); // NOI18N
        jPanelContent.add(jLabelHeigth);

        jSpinnerHeigth.setOpaque(false);
        jSpinnerHeigth.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jSpinnerHeigthMouseWheelMoved(evt);
            }
        });
        jPanelContent.add(jSpinnerHeigth);

        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icon_pregunta.png"))); // NOI18N
        jLabelIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 30, 5, 5));
        jLabelIcon.setOpaque(true);
        getContentPane().add(jLabelIcon, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        VentanaPrincipal parent = (VentanaPrincipal) this.getParent();
        VentanaInterna ultima_ventana = (VentanaInterna) parent.getEscritorio().getSelectedFrame();
        // dimensión de la imagen
        Dimension dim;
        // ancho
        int w = (int) this.jSpinnerWidth.getValue();
        // alto
        int h = (int) this.jSpinnerHeigth.getValue();

        // tamaño de la imagen que queremos crear
        // @TODO aqui tenemos que controlar si el usuario pone un dato incorrecto
        // intentar lanzar una exception.
        if (w > 0 && h > 0) {
            dim = new Dimension(w, h);
        } else {
            dim = new Dimension(250, 250);
        }
        
        VentanaInterna vi = new VentanaInterna(parent, dim);
        if (ultima_ventana != null) {
            vi.setLocation(ultima_ventana.getLocation().x + 15, ultima_ventana.getLocation().y + 15);
        } else {
            vi.setLocation(15, 15);
        }
        parent.getEscritorio().add(vi);
        vi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    /**
     * @TODO @param evt
     */
    private void jSpinnerWidthMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jSpinnerWidthMouseWheelMoved
        if (evt.getPreciseWheelRotation() < 0) {
            if ((int) this.jSpinnerWidth.getValue() < this.MAX_PIXEL_IMAGE) {
                this.jSpinnerWidth.setValue((int) this.jSpinnerWidth.getValue() + 7);
            }
        } else if ((int) this.jSpinnerWidth.getValue() > 0) {
            this.jSpinnerWidth.setValue((int) this.jSpinnerWidth.getValue() - 7);
        }
    }//GEN-LAST:event_jSpinnerWidthMouseWheelMoved

    /**
     * @TODO @param evt
     */
    private void jSpinnerHeigthMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jSpinnerHeigthMouseWheelMoved
        if (evt.getPreciseWheelRotation() < 0) {
            if ((int) this.jSpinnerHeigth.getValue() < this.MAX_PIXEL_IMAGE) {
                this.jSpinnerHeigth.setValue((int) this.jSpinnerHeigth.getValue() + 7);
            }
        } else if ((int) this.jSpinnerHeigth.getValue() > 0) {
            this.jSpinnerHeigth.setValue((int) this.jSpinnerHeigth.getValue() - 7);
        }
    }//GEN-LAST:event_jSpinnerHeigthMouseWheelMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelHeigth;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelWidth;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JSpinner jSpinnerHeigth;
    private javax.swing.JSpinner jSpinnerWidth;
    // End of variables declaration//GEN-END:variables
}
