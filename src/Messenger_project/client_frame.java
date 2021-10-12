package Messenger_project;

import java.net.*;
import java.io.*;
import java.util.*;

public class client_frame extends javax.swing.JFrame 
{
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
 
    
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    
    
    public void userRemove(String data) 
    {
         msg_area.append(data + " is now offline.\n");
    }
    
    
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
    }
    
    //--------------------------//
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            msg_area.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            msg_area.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            msg_area.append("Failed to disconnect. \n");
        }
        isConnected = false;
        

    }
    
    public client_frame() 
    {
        initComponents();
    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                        msg_area.append(data[0] + ": " + data[1] + "\n");
                        msg_area.setCaretPosition(msg_area.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        msg_area.removeAll();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }
    
    
    public void clientConnect(){
         if (isConnected == false) 
        {
            username = "fahim";
            

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                msg_area.append("Cannot Connect! Try Again. \n");
                
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            msg_area.append("You are already connected. \n");
        }
    }
    
    public void serverDisconnect(){
        
    }
    
    
    

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User");
        setBackground(new java.awt.Color(102, 255, 102));
        setName("client"); // NOI18N
        setResizable(false);

        msg_area.setBackground(new java.awt.Color(153, 255, 255));
        msg_area.setColumns(20);
        msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        msg.setBackground(new java.awt.Color(153, 255, 255));

        b_send.setBackground(new java.awt.Color(255, 0, 51));
        b_send.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        b_send.setForeground(new java.awt.Color(240, 240, 240));
        b_send.setText("SEND");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_send, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
 
    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((msg.getText()).equals(nothing)) {
            msg.setText("");
            msg.requestFocus();
        } else {
            try {
               writer.println(username + ":" + msg.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                msg_area.append("Message was not sent. \n");
            }
            msg.setText("");
            msg.requestFocus();
        }

        msg.setText("");
        msg.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_send;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField msg;
    private javax.swing.JTextArea msg_area;
    // End of variables declaration//GEN-END:variables
}
