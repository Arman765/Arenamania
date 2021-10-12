package log.in;

import Messenger_project.client_frame;
import Messenger_project.server_frame;
import dx_ball.DXB_Gameplay;
import findthem.Finding;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class inGame extends javax.swing.JFrame {


    public inGame() {
        initComponents();
        setVisible(true);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/log/in/images/rsz_match.jpg"))); // NOI18N
        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(70, 50, 305, 280);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/log/in/images/rsz_message.jpg"))); // NOI18N
        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(430, 290, 300, 280);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/log/in/images/rsz_game_blockbusters_bi.jpg"))); // NOI18N
        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(790, 50, 300, 270);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/log/in/rsz_colorful-1570464270901-2494.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1160, 590);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1155, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        setVisible(false);
        Finding find = new Finding();
        find.setVisible(true);  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
        JFrame dx1 = new JFrame();
        DXB_Gameplay gameplay = null;
        try{
            gameplay = new DXB_Gameplay();
        }
        catch(Exception ex){}
        dx1.setBounds(10,10,1010,690);
        dx1.setTitle("DX Ball");
        dx1.setResizable(false);
        dx1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        dx1.add(gameplay);  
        dx1.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        server_frame sframe = new server_frame();
        sframe.serverStart();
        client_frame cframe = new client_frame();
        cframe.setVisible(true);
        cframe.clientConnect();
        setVisible(false);
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

   
    
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
