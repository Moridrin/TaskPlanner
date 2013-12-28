/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.*;
import java.util.*;
import connections.MySQL;
import forminterface.ParentFormInterface;
import gui.mysql.ConnectionSettingsFrame;

/**
 *
 * @author jeroen
 */
public class MainFrame extends javax.swing.JFrame implements ParentFormInterface {

    private MySQL connectionSettings;
    ConnectionSettingsFrame connectionSettingsFrame;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }
    
    private void loadSettings(){
        File f = new File("ServerSettings.ser");
        if (f.exists()) {
            try {
                FileInputStream file = new FileInputStream("ServerSettings.ser");
                ObjectInputStream stream = new ObjectInputStream(file);
                connectionSettings = (MySQL) stream.readObject();
                stream.close();
                file.close();
                jMenuItemConnect.setEnabled(false);
                jMenuItemDisconnect.setEnabled(true);
            } catch (IOException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException exception) {
                System.out.println("Employee class not found");
                exception.printStackTrace();
            }
        }
    }

    private void updateSettings(MySQL connectionSettings) {
        this.connectionSettings = connectionSettings;
        if (connectionSettingsFrame != null) {
            connectionSettingsFrame.dispose();
        }
        if (connectionSettings != null) {
            try {
                FileOutputStream fileOut = new FileOutputStream("ServerSettings.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(connectionSettings);
                out.close();
                fileOut.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
            jMenuItemConnect.setEnabled(false);
            jMenuItemDisconnect.setEnabled(true);
        } else {
            File file = new File("ServerSettings.ser");
            file.delete();
            jMenuItemConnect.setEnabled(true);
            jMenuItemDisconnect.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar = new javax.swing.JMenuBar();
        jMenuServer = new javax.swing.JMenu();
        jMenuItemConnect = new javax.swing.JMenuItem();
        jMenuItemDisconnect = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuServer.setText("Server");
        jMenuServer.setToolTipText("");

        jMenuItemConnect.setText("Connect");
        jMenuItemConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConnectActionPerformed(evt);
            }
        });
        jMenuServer.add(jMenuItemConnect);

        jMenuItemDisconnect.setText("Disconnect");
        jMenuItemDisconnect.setEnabled(false);
        jMenuItemDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDisconnectActionPerformed(evt);
            }
        });
        jMenuServer.add(jMenuItemDisconnect);

        jMenuBar.add(jMenuServer);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConnectActionPerformed
        connectionSettingsFrame = new gui.mysql.ConnectionSettingsFrame();
        connectionSettingsFrame.setVisible(true);
        connectionSettingsFrame.setParent(this);
    }//GEN-LAST:event_jMenuItemConnectActionPerformed

    private void jMenuItemDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDisconnectActionPerformed
        updateSettings(null);
    }//GEN-LAST:event_jMenuItemDisconnectActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItemConnect;
    private javax.swing.JMenuItem jMenuItemDisconnect;
    private javax.swing.JMenu jMenuServer;
    // End of variables declaration//GEN-END:variables

    @Override
    public void UpdateForm(String function, ArrayList<Object> args) {
        if (function.equals("updateSettings")) {
            MySQL arg = (MySQL) args.get(0);
            updateSettings(arg);
        }
    }
}
