/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.Group;
import model.Message;
import model.User;

/**
 *
 * @author Chuong
 */
public class HomeForm extends javax.swing.JFrame implements Runnable{

    /**
     * Creates new form HomeForm
     */
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private User user;
    private ArrayList<Group>listChat1;
    private ArrayList<Group>listGroup;
    private ArrayList<User>listFriend1;
    private ArrayList<Message>listMessage;
    public HomeForm(Socket socket, User user) {
        initComponents();
        this.socket = socket;
        this.user = user;
        txtNameUser.setText(user.getName());
        txtAddressUser.setText(user.getAddress());
        this.listFriend1 = (ArrayList<User>) user.getListFriend();
        for( Group group : user.getListGroup()){
            if( group.getListMembers().size() > 2 ){
                this.listGroup = (ArrayList<Group>)user.getListGroup();
            }else{
                this.listChat1 = (ArrayList<Group>)user.getListGroup();
            }
        }
        this.listGroup = (ArrayList<Group>)user.getListGroup();
        System.out.println(listGroup.size());
        System.out.println(listChat1.size());
//        System.out.println(listGroup.size());
//        setTableListChat();
        setTableListFriend();
        setTabletblListGroup();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        lstChatSimple = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listChat = new javax.swing.JTable();
        lstGroupChatSimple = new javax.swing.JPanel();
        btnCreateGroup = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblListGroup = new javax.swing.JTable();
        lstFriend = new javax.swing.JPanel();
        btnAddFriend = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listFriend = new javax.swing.JTable();
        txtNameUser = new javax.swing.JLabel();
        txtAddressUser = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAShowMessage = new javax.swing.JTextArea();
        edtMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNameReceiver = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        btnIcon = new javax.swing.JLabel();
        btnImage = new javax.swing.JLabel();
        btnFile = new javax.swing.JLabel();
        edtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        setBackground(new java.awt.Color(204, 255, 255));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lstChatSimple.setBackground(new java.awt.Color(255, 255, 255));

        listChat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(listChat);

        javax.swing.GroupLayout lstChatSimpleLayout = new javax.swing.GroupLayout(lstChatSimple);
        lstChatSimple.setLayout(lstChatSimpleLayout);
        lstChatSimpleLayout.setHorizontalGroup(
            lstChatSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
        );
        lstChatSimpleLayout.setVerticalGroup(
            lstChatSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Chat", lstChatSimple);

        lstGroupChatSimple.setBackground(new java.awt.Color(255, 255, 255));

        btnCreateGroup.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCreateGroup.setText("+ new group");
        btnCreateGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateGroupActionPerformed(evt);
            }
        });

        tblListGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblListGroup);

        javax.swing.GroupLayout lstGroupChatSimpleLayout = new javax.swing.GroupLayout(lstGroupChatSimple);
        lstGroupChatSimple.setLayout(lstGroupChatSimpleLayout);
        lstGroupChatSimpleLayout.setHorizontalGroup(
            lstGroupChatSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lstGroupChatSimpleLayout.createSequentialGroup()
                .addComponent(btnCreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        lstGroupChatSimpleLayout.setVerticalGroup(
            lstGroupChatSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lstGroupChatSimpleLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnCreateGroup))
        );

        jTabbedPane1.addTab("Group", lstGroupChatSimple);

        lstFriend.setBackground(new java.awt.Color(255, 255, 255));

        btnAddFriend.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddFriend.setText("+ add friend");
        btnAddFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFriendActionPerformed(evt);
            }
        });

        listFriend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        listFriend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listFriendMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listFriend);

        javax.swing.GroupLayout lstFriendLayout = new javax.swing.GroupLayout(lstFriend);
        lstFriend.setLayout(lstFriendLayout);
        lstFriendLayout.setHorizontalGroup(
            lstFriendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddFriend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(lstFriendLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        lstFriendLayout.setVerticalGroup(
            lstFriendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lstFriendLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddFriend)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Friend", lstFriend);

        txtNameUser.setBackground(new java.awt.Color(255, 255, 255));
        txtNameUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNameUser.setText("Name user");

        txtAddressUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAddressUser.setText("Address");

        txtAShowMessage.setColumns(20);
        txtAShowMessage.setRows(5);
        jScrollPane1.setViewportView(txtAShowMessage);

        edtMessage.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        edtMessage.setForeground(new java.awt.Color(153, 153, 153));
        edtMessage.setText("Message");
        edtMessage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edtMessageFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                edtMessageFocusLost(evt);
            }
        });
        edtMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtMessageActionPerformed(evt);
            }
        });

        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_send_message.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtNameReceiver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNameReceiver.setText("Name receiver");

        txtStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtStatus.setText("Online");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtStatus))
                    .addComponent(txtNameReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNameReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnIcon.setBackground(new java.awt.Color(255, 255, 255));
        btnIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_nhan_dan.png"))); // NOI18N

        btnImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_image.png"))); // NOI18N

        btnFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_send_file.png"))); // NOI18N

        edtSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edtSearch.setForeground(new java.awt.Color(153, 153, 153));
        edtSearch.setText("Search");
        edtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edtSearchFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                edtSearchFocusGained(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_tim_kiem.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_logout.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtAddressUser, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                        .addGap(104, 104, 104))
                                    .addComponent(txtNameUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFile, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtAddressUser, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edtSearch)
                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(btnFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edtMessage, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtMessageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtMessageFocusGained
        // TODO add your handling code here:
        if(edtMessage.getText().equals("Message")){
            edtMessage.setText("");
            edtMessage.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_edtMessageFocusGained

    private void edtMessageFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtMessageFocusLost
        // TODO add your handling code here:
         if(edtMessage.getText().equals("")){
            edtMessage.setText("Message");
            edtMessage.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_edtMessageFocusLost

    private void edtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtSearchFocusGained
        // TODO add your handling code here:
        if(edtSearch.getText().equals("Search")){
            edtSearch.setText("");
            edtSearch.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_edtSearchFocusGained

    private void edtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtSearchFocusLost
        // TODO add your handling code here:
        if(edtSearch.getText().equals("")){
            edtSearch.setText("Search");
            edtSearch.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_edtSearchFocusLost

    private void edtMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtMessageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtMessageActionPerformed

    private void btnCreateGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateGroupActionPerformed
        // TODO add your handling code here:
        (new AddGroupForm()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCreateGroupActionPerformed

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        try {
            // TODO add your handling code here:
            (new LoginForm(oos,ois)).setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnAddFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFriendActionPerformed
        // TODO add your handling code here:
        (new AddFriendForm()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddFriendActionPerformed

    private void listFriendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listFriendMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setRowCount(0);
        dtm.setColumnIdentifiers(new String[]{"Nick Name"});
        if (listFriend1 != null) {
            for (User friend : listFriend1) {
                dtm.addRow(new String[]{friend.getName()});
            }
        }
        listFriend.setModel(dtm);
    }//GEN-LAST:event_listFriendMouseClicked
//    public void setTableListChat(){
//        DefaultTableModel dtm = new DefaultTableModel();
//        dtm.setRowCount(0);
//        if (listChat1 != null) {
//            for (Group group : listChat1) {
//                dtm.addRow(new String[]{group.getNameGroup()});
//            }
//        }
//        listChat.setModel(dtm);
//    }
    
    public void setTableListFriend(){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setRowCount(0);
        dtm.setColumnIdentifiers(new String[]{"Nick Name"});
        if (listFriend1 != null) {
            for (User friend : listFriend1) {
                dtm.addRow(new String[]{friend.getName()});
            }
        }
        listFriend.setModel(dtm);
    }
    public void setTabletblListGroup(){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setRowCount(0);
        dtm.setColumnIdentifiers(new String[]{"Group Name"});
        if (listGroup != null) {
            for (Group group : listGroup) {
                dtm.addRow(new String[]{group.getNameGroup()});
            }
        }
        tblListGroup.setModel(dtm);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFriend;
    private javax.swing.JButton btnCreateGroup;
    private javax.swing.JLabel btnFile;
    private javax.swing.JLabel btnIcon;
    private javax.swing.JLabel btnImage;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnSend;
    private javax.swing.JTextField edtMessage;
    private javax.swing.JTextField edtSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable listChat;
    private javax.swing.JTable listFriend;
    private javax.swing.JPanel lstChatSimple;
    private javax.swing.JPanel lstFriend;
    private javax.swing.JPanel lstGroupChatSimple;
    private javax.swing.JTable tblListGroup;
    private javax.swing.JTextArea txtAShowMessage;
    private javax.swing.JLabel txtAddressUser;
    private javax.swing.JLabel txtNameReceiver;
    private javax.swing.JLabel txtNameUser;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}