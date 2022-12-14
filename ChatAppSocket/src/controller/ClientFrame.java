/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager; 
import javax.swing.UnsupportedLookAndFeelException;
import model.User;
import view.ClientPanel;
import view.LoginPanel;
import view.PrivateChat;
import view.RoomPanel;
import view.SignUpPanel;
import view.WelcomePanel;
/**
 *
 * @author Hi
 */
public class ClientFrame extends JFrame implements Runnable {
    
    
    String serverHost;
    public static final String NICKNAME_EXIST = "This nickname is already login in another place! Please using another nickname";
    public static final String NICKNAME_VALID = "This nickname is OK";
    public static final String NICKNAME_INVALID = "Nickname or password is incorrect";
    public static final String SIGNUP_SUCCESS = "Sign up successful!";
    public static final String ACCOUNT_EXIST = "This nickname has been used! Please use another nickname!";
    public static final String PASSWORD_CONFIRM = "Passwords don't match!";
    
    
    User user;
    String name;
    String room;
    Socket socketOfClient;
    BufferedWriter bw;
    BufferedReader br;
    
    JPanel mainPanel;
    LoginPanel loginPanel;
    ClientPanel clientPanel;
    WelcomePanel welcomePanel;
    SignUpPanel signUpPanel;
    RoomPanel roomPanel;
    
    Thread clientThread;
    boolean isRunning;
    
    JMenuBar menuBar;
    JMenu menuShareFile;
    JMenuItem itemSendFile;
    JMenu menuAccount;
    JMenuItem itemLeaveRoom, itemLogout, itemChangePass;
    
    SendFileFrame sendFileFrame;
    
    StringTokenizer tokenizer;
    String myDownloadFolder;
    
    Socket socketOfSender, socketOfReceiver;
    
    DefaultListModel<String> listModel, listModelThisRoom, listModel_rp;
        
    boolean isConnectToServer;
    
    int timeClicked = 0;    ///bi???n n??y ????? ki???m tra xem ng?????i d??ng v???a click hay v???a double-click v??o jList.
    //Thu???t to??n ki???m tra r???t ????n gi???n: khi click th?? t??ng bi???n n??y l??n 1 v?? ?????m 500ms, n???u trong 500ms ???? 
    //ng?????i d??ng click ti???p th?? ???? l?? double-click, n???u ko th?? ch??? l?? click
    
    Hashtable<String, PrivateChat> listReceiver;
    
    public ClientFrame(String name) {
        this.name = name;
        socketOfClient = null;
        bw = null;
        br = null;
        serverHost="localhost";
        isRunning = true;
        listModel = new DefaultListModel<>();
        listModelThisRoom = new DefaultListModel<>();
        listModel_rp = new DefaultListModel<>();
        isConnectToServer = false;
        listReceiver = new Hashtable<>();
    }
    
    
    
    Runnable counting = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            timeClicked = 0;
        }
    };
    
    private void labelRoomEvent() { // khi ng?????i d??ng v??o 1 ph??ng chat th?? s??? g???i ?????n server ????? th??m v?? ph??ng
        this.clientPanel.getTpMessage().setText("");
        this.sendToServer("CMD_ROOM|"+this.room);
        try {
            Thread.sleep(200);      //ch??? t?? cho n?? ????? l???i!
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.roomPanel.setVisible(false);
        this.clientPanel.setVisible(true);
        this.setTitle("\""+this.name+"\" - "+this.room);
        clientPanel.getLbRoom().setText(this.room);
    }
    
    private void leaveRoom() {// khi ng?????i d??ng r???i ph??ng s??? g???i ?????n s??? g???i ?????n server ????? xo?? ra kh???i ph??ng
        this.sendToServer("CMD_LEAVE_ROOM|"+this.room);
        try {
            Thread.sleep(200);      //ch??? t?? cho n?? ????? l???i!
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.roomPanel.setVisible(true);
        this.clientPanel.setVisible(false);
        //clear the textPane message:
        clientPanel.getTpMessage().setText("");
        this.setTitle("\""+this.name+"\"");
    }
    
    
    ////////////////////////Events////////////////////////////
    private String btOkEvent(User user) {//login
        
        this.serverHost = "localhost";
        
        if(user.getUserName().equals("") || user.getPassWord().equals("")) {
            return "Please fill up all fields";
        }
        if(!isConnectToServer) {
            isConnectToServer = true;   //ngh??a l?? khi ch???y file ClientFrame.java n??y th?? ch??? connect t???i server duy nh???t 1 l???n th??i,
                                        //sau ???? ko ph???i connect n???a v?? ???? c?? k???t n???i t???i server r???i. N???u c??? connect nhi???u l???n (do nh???p sai account)
                                        //th?? s??? t???o ra nhi???u socket k???t n???i t???i server m???i l???n b???m OK, sau ???? socket t??? ?? close d???n t???i vi???c l???i!
            this.connectToServer(serverHost); //t???o 1 socket k???t n???i t??i server
        }    
        this.sendToServer("CMD_CHECK_NAME|" +user.getUserName()+"|"+user.getPassWord());       //sau ???? g???i t??n ?????n ????? y??u c???u ????ng nh???p =  t??n ????
        
        //server ph???n h???i r???ng t??n v???a nh???p c?? h???p l??? hay ko:
        String response = this.recieveFromServer();
        if(response != null) {
            if (response.equals(NICKNAME_EXIST) || response.equals(NICKNAME_INVALID)) {
                return response;
            } else {

                //1 thread c???a client ??c t???o ra ????? giao ti???p v???i server, ch?? ?? r???ng nhi???m v??? c???a thread n??y ch??? ch??? server g???i tin t???i
                //v?? in kq l??n textarea, c??n vi???c g???i tin t???i server d??ng s??? ki???n c???a btSend
                clientThread = new Thread(this);
                clientThread.start();
                this.sendToServer("CMD_ROOM|"+this.room);     //y??u c???u ds c??c user ??ang online ????? c?? th??? chat private

                System.out.println("this is \""+name+"\"");
                //loginPanel.getBtOK().setText("OK");
            }
        } else System.out.println("[btOkEvent()] Server is not open yet, or already closed!");
        return null;
    }
    
    private String btSignUpEvent(User user,String confirmPass) {
        if(!user.getPassWord().equals(confirmPass)) {
            return PASSWORD_CONFIRM;
        } else {
            if(user.getName().equals("")|| user.getUserName().equals("")|| user.getAddress().equals("")||user.getPassWord().equals("")||confirmPass.equals("")) {
                return "Please fill up all fields";
            }
            if(!isConnectToServer) {
                isConnectToServer = true;   //ngh??a l?? khi ch???y file ClientFrame.java n??y th?? ch??? connect t???i server duy nh???t 1 l???n th??i,
                                            //sau ???? ko ph???i connect n???a v?? ???? c?? k???t n???i t???i server r???i. N???u c??? connect nhi???u l???n (do nh???p sai account)
                                            //th?? s??? t???o ra nhi???u socket k???t n???i t???i server m???i l???n b???m OK, sau ???? socket t??? ?? close d???n t???i vi???c l???i!
                this.connectToServer(serverHost); //t???o 1 socket k???t n???i t??i server
            }    
            this.sendToServer("CMD_SIGN_UP|" +user.getName()+"|"+user.getAddress()+"|"+user.getUserName()+"|"+user.getPassWord());       //sau ???? g???i t??n ?????n ????? y??u c???u ????ng nh???p =  t??n ????
        
            String response = this.recieveFromServer();
            if(response != null) {
                if(response.equals(NICKNAME_EXIST) || response.equals(ACCOUNT_EXIST)) {
                    return response;
                } else {
                    return "Success";
                }
            }
        }
        return "Error";
    }
            
    private void btSendEvent() {
        String message = clientPanel.getTaInput().getText().trim();
        if(message.equals("")) clientPanel.getTaInput().setText("");
        else {
            this.sendToServer("CMD_CHAT|" + message);       //g???i data t???i server
            this.btClearEvent();
        }
        //ch?? ?? r???ng vi???c ch??? server ph???n h???i th???c hi???n trong h??m run c???a thread ch??? ko ph???i ??? ????y
    }

    private void btClearEvent() {
        clientPanel.getTaInput().setText("");
    }

    private void btExitEvent() {
        try {
            isRunning = false;
            //this.disconnect();
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    private void openSendFileFrame() {
//        sendFileFrame = new SendFileFrame();
//        
//        //g???i t???t c??? th??ng tin c???a client n??y sang frame ????:
//        //sendFileFrame.name = this.name;
//        //sendFileFrame.socketOfClient = this.socketOfClient;
////        sendFileFrame.bw = this.bw;
////        sendFileFrame.br = this.br;
//        
//        sendFileFrame.setVisible(true);
//        sendFileFrame.setLocation(450, 250);
//        sendFileFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//    }
    
    ////////////////////////End of Events////////////////////////////   
    
    public void connectToServer(String hostAddress) {   //m???i l???n connect t???i server l?? kh???i t???o l???i thu???c t??nh socketOfClient
        try {
            socketOfClient = new Socket("192.168.0.106", 9999);
            bw = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            
        } catch (java.net.UnknownHostException e) {
            JOptionPane.showMessageDialog(this, "Host IP is not correct.\nPlease try again!", "Failed to connect to server", JOptionPane.ERROR_MESSAGE);
        } catch (java.net.ConnectException e) {
            JOptionPane.showMessageDialog(this, "Server is unreachable, maybe server is not open yet, or can't find this host.\nPlease try again!", "Failed to connect to server", JOptionPane.ERROR_MESSAGE);
        } catch(java.net.NoRouteToHostException e) {
            JOptionPane.showMessageDialog(this, "Can't find this host!\nPlease try again!", "Failed to connect to server", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void sendToServer(String line) {
        try {
            this.bw.write(line);
            this.bw.newLine();   //ph???i c?? newLine th?? m???i d??ng ??c h??m readLine()
            this.bw.flush();
        } catch (java.net.SocketException e) {
            JOptionPane.showMessageDialog(this, "Server is closed, can't send message!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (java.lang.NullPointerException e) {
            System.out.println("[sendToServer()] Server is not open yet, or already closed!");
        } catch (IOException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String recieveFromServer() {
        try {
            return this.br.readLine();  //ch?? ?? r???ng ch??? nh???n 1 d??ng t??? server g???i v??? th??i, n???u server g???i nhi???u d??ng th?? c??c d??ng sau ko ?????c
        } catch (java.lang.NullPointerException e) {
            System.out.println("[recieveFromServer()] Server is not open yet, or already closed!");
        } catch (IOException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void disconnect() {
        System.out.println("disconnect()");
        try {
            if(br!=null) this.br.close();
            if(bw!=null) this.bw.close();
            if(socketOfClient!=null) this.socketOfClient.close();
            System.out.println("trong khoi try catch");
        } catch (IOException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClientFrame client = new ClientFrame(null);
        client.setVisible(true);
    }

    @Override
    public void run() {
        String response;
        String sender, receiver, fileName, thePersonIamChattingWith, thePersonSendFile;
        String msg;
        String cmd, icon;
        PrivateChat pc;
        
        while(isRunning) {
            response = this.recieveFromServer();   //nh???n ph???n h???i t??? server sau khi ???? g???i data ??? tr??n
            tokenizer = new StringTokenizer(response, "|");
            cmd = tokenizer.nextToken(); 
            switch (cmd) {
                case "CMD_CHAT":    //gi??? s??? nh???n ???????c g??i tin: CMD_CHAT|anh tu: today is very cool!
                    sender = tokenizer.nextToken();
                    msg = response.substring(cmd.length()+sender.length()+2, response.length());
                    
                    if(sender.equals(this.name)) this.clientPanel.appendMessage(sender+": ", msg, Color.BLACK, new Color(0, 102, 204));
                    else this.clientPanel.appendMessage(sender+": ", msg, Color.MAGENTA, new Color(56, 224, 0));
                    
                    //ph???i l???ng nh???ng nh?? tr??n v?? tr??nh tr?????ng h???p tin nh???n c?? k?? t??? |, n???u c??? d??ng StringTokenizer v?? l???y k?? t??? '|' l??m c??i ph??n chia th?? tin nh???n ko th??? hi???n th??? k?? t??? | ??c
                    break;
                    
                case "CMD_PRIVATECHAT":     //khi server g???i message c???a sender, ?????ng ??? g??c nh??n c???a th???ng client n??y
                    //th?? th???ng g???i t???i ???? ch??nh l?? th???ng nh???n, v?? th???ng client n??y chat v???i th???ng g???i ????,
                    //n??n th???ng g???i ???? s??? nh???n tin t??? th???ng n??y
                    sender = tokenizer.nextToken();
                    msg = response.substring(cmd.length()+sender.length()+2, response.length());
                    
                    pc = listReceiver.get(sender);
                    
                    
                    if(pc == null) {
                        pc = new PrivateChat(name, sender, serverHost, bw, br);
                        pc.sender = name;
                        pc.receiver = sender;
                        pc.serverHost = this.serverHost;
                        pc.bw = ClientFrame.this.bw;
                        pc.br = ClientFrame.this.br;

                        pc.getLbReceiver().setText("Private chat with \""+pc.receiver+"\"");
                        pc.setTitle(pc.receiver);
                        pc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        pc.setVisible(true);    //n???u c??i pc ???? ??ang Visible r???i th?? l???nh n??y cho focus v??o c??i frame n??y

                        listReceiver.put(sender, pc);
                    } else {
                        pc.setVisible(true);
                    }
                    //pc.appendMessage(sender+": ", msg, Color.CYAN, Color.GREEN);
                    pc.appendMessage_Left(sender+": ", msg);
                    break;
                    
                case "CMD_ONLINE_USERS":
                    listModel.clear();
                    listModel_rp.clear();
                    while(tokenizer.hasMoreTokens()) {
                        cmd = tokenizer.nextToken();
                        listModel.addElement(cmd);
                        listModel_rp.addElement(cmd);
                    }
                    clientPanel.getOnlineList().setModel(listModel);
                    
                    listModel_rp.removeElement(this.name);
                    roomPanel.getOnlineList_rp().setModel(listModel_rp);
                    break;
                    
                case "CMD_ONLINE_THIS_ROOM":
                    listModelThisRoom.clear();
                    while(tokenizer.hasMoreTokens()) {
                        cmd = tokenizer.nextToken();
                        listModelThisRoom.addElement(cmd);
                    }
                    clientPanel.getOnlineListThisRoom().setModel(listModelThisRoom);
                    break;
                    
//                case "CMD_SERVERISBUSY":
//                    JOptionPane.showMessageDialog(this, "Server is busy, please try to send file later", "Info", JOptionPane.INFORMATION_MESSAGE);
//                    break;
                    
                case "CMD_FILEAVAILABLE":
                    System.out.println("file available");
                    fileName = tokenizer.nextToken();
                    thePersonIamChattingWith = tokenizer.nextToken();
                    thePersonSendFile = tokenizer.nextToken();
                    
                    pc = listReceiver.get(thePersonIamChattingWith);
                    
                    if(pc == null) {
                        sender = this.name;
                        receiver = thePersonIamChattingWith;
                        pc = new PrivateChat(sender, receiver, serverHost, bw, br);
                        
                        pc.getLbReceiver().setText("Private chat with \""+pc.receiver+"\"");
                        pc.setTitle(pc.receiver);
                        pc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        
                        listReceiver.put(receiver, pc);
                    }
                    
                    pc.setVisible(true);    //n???u c??i pc ???? ??ang Visible r???i th?? l???nh n??y cho focus v??o c??i frame n??y
                    pc.insertButton(thePersonSendFile, fileName);
                    break;
                    
                case "CMD_ICON":
                    icon = tokenizer.nextToken();
                    cmd = tokenizer.nextToken();    //cmd = sender
                    
                    if(cmd.equals(this.name)) this.clientPanel.appendMessage(cmd+": ", "\n  ", Color.BLACK, Color.BLACK);
                    else this.clientPanel.appendMessage(cmd+": ", "\n   ", Color.MAGENTA, Color.MAGENTA);
                    
                    switch (icon) {
                        case "LIKE":
                            this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/like2.png")));
                            break;
                            
                        case "DISLIKE":
                            this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/dislike.png")));
                            break;
                            
                        case "PAC_MAN":
                            this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/pacman.png")));
                            break;
                            
                        case "SMILE":
                            this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/smile.png")));
                            break;
                            
                        case "GRIN":
                            this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/grin.png")));
                            break;
                            
                        case "CRY":
                            this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/cry.png")));
                            break;
                            
                        default:
                            throw new AssertionError("The icon is invalid, or can't find icon!");
                    }
                    
                    break;
                    
                default:
                    if(!response.startsWith("CMD_")) {      //tr?????ng h???p response ch??? l?? 1 tin nh???n th??ng th?????ng
                        if(response.equals("Warnning: Server has been closed!")) {
                            this.clientPanel.appendMessage(response, Color.RED);
                        }
                        else this.clientPanel.appendMessage(response, new Color(153, 153, 153));
                    }
                    //do b??n server c?? h??m notifyToAllUsers(clientName+" has just entered!"); 
                    //h??m tr??n ko c?? ?????nh d???ng n??o c???, t???c l?? ko c?? CMD_ ??? ?????u message, n??n ta ch??? c???n in ra th??ng ??i???p server g???i t???i l?? ??c
                    
            }
        }
        System.out.println("Disconnected to server!");
    }


}
/*
Chat trong room: khi 1 th???ng ???n send th?? n???i dung tin nh???n ??c g???i ?????n server, sau ???? server g???i l???i tin ????
cho t???t c??? c??c th???ng trong room ????, bao g???m c??? th???ng g???i, sau ???? c??c th???ng nh???n ??c tin nh???n m???i hi???n th??? tin 
l??n textpane, ngh??a l?? th???ng g???i tin c??ng ph???i ch??? server ph???n h???i v??? m???i hi???n th??? tin nh???n n?? v???a g???i l??n
c??i textpane c???a n??.
Chat private gi???a 2 th???ng: sender hi???n th??? tin nh???n c???a n?? l??n textpane sau ???? m???i g???i tin t???i server, server ch???
g???i tin l???i th???ng nh???n th??i, ko g???i l???i cho sender n???a. Khi g???i tin th?? d??ng PrivateChat ????? g???i, nh??ng khi nh???n tin th??
d??ng ClientFrame ????? nh???n, sau ???? ClientFrame l???y c??i JFrame PrivateChatt??m t??? c??i t??n ?????a g???i trong listReceiver v?? 
hi???n th??? tin l??n c??i frame ????. Ch?? ?? l?? 1 ng?????i c?? th??? c?? nhi???u Frame PrivateChat ????? chat ri??ng v???i c??c ng?????i kh??c nhau,
do ???? c???n listReceiver ????? l??u c??c Frame t????ng ???ng v???i ng?????i ???? l???i
V???y listReceiver l?? danh s??ch c??c ?????i t?????ng ??ang chat ri??ng v???i ng?????i d??ng ????, v???i key l?? t??n c???a ng?????i chat v?? value l?? 
c??i Frame chat ???ng v???i ng?????i chat ????
*/