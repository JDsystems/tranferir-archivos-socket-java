/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketarchivos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.DefaultListModel;


public class Servidor extends javax.swing.JFrame {
public static Servidor ser;
    DefaultListModel modelo = new DefaultListModel();
    BufferedInputStream bis;
    BufferedOutputStream bos;
    ServerSocket servidor;
    byte[] bytes;
    Socket conexion;
    File carpeta, direccion;
    String[] vector, tamaño;
    String file, path;
    int i = 0, in;

    public Servidor() {
        initComponents();
        iniciarConexiones();
    }

    @SuppressWarnings("unchecked")

    void iniciarConexiones() {
        carpeta = new File("archivos_recibidos");
        vector = carpeta.list();

        while (i < vector.length) {
            modelo.addElement(vector[i]);
            jList1.setModel(modelo);
            i++;
        }
        jTextField1.setText("Total Archivos: " + vector.length);

        try {
            servidor = new ServerSocket(5000);
            this.setVisible(true);
            System.out.println("Esperando conexion....");
            while (true) {

                conexion = servidor.accept();
                System.out.println("cliente conectado");
                bytes = new byte[40];
                bis = new BufferedInputStream(conexion.getInputStream());
                DataInputStream dis = new DataInputStream(conexion.getInputStream());
                file = dis.readUTF();
                file = file.substring(file.indexOf('\\') + 1, file.length());
                File miDir = new File(".");
                path = miDir.getCanonicalPath() + "\\archivos_recibidos\\" + file;
                modelo.add(vector.length, file);
                jList1.setModel(modelo);
                direccion = new File("archivos_recibidos");
                tamaño = direccion.list();
                int num = tamaño.length + 1;
                jTextField1.setText("total Archivos: " + num);

                bos = new BufferedOutputStream(new FileOutputStream(path));
                while ((in = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0, in);

                }
                bos.close();
                dis.close();
            }

        } catch (Exception e) {
            System.err.println("exepcion server" + e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jList1);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Servidor().setVisible(true);
//            }
//        });
ser = new Servidor();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
