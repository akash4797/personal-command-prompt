
package myapp;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyApp extends JFrame implements KeyListener{
    
    JScrollPane sp,sp1;
    Container c;
    JTextArea txt1;
    JTextField txt;
    Dimension toolkit;
    JLabel lbl;
    JPasswordField ptxt;
    ArrayList<String> commands= new ArrayList();
    BufferedReader reader;
    BufferedWriter bw;
    FileWriter writer;
    Font f;
    
    ImageIcon img;
    
    ArrayList<String> rem = new ArrayList();
    
   static String check="start";
    
    MyApp(){
        

    initcomponents();
    
    }

    
    public final void initcomponents(){
    
     toolkit = Toolkit.getDefaultToolkit().getScreenSize();
     
    this.setBounds((int)(toolkit.getWidth()/2)-300,(int)(toolkit.getHeight()/2)-160,600,320);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setTitle("Personal Comand Prompt");
    
    img = new ImageIcon(getClass().getResource("/img/favicon.gif"));
    
    
    this.setIconImage(img.getImage());
    
    UI();
    
    
    
    }
    
    public static void start(){
    
    MyApp frame = new MyApp();
    frame.setVisible(true);
    
    }
    
    public void UI(){
    
        f = new Font("Consolas",Font.BOLD,14);
        
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.BLACK);
   
        txt = new JTextField();
        txt.setBounds(140,250,this.getWidth()-6,30);
        txt.setBorder(null);
        txt.setCaretColor(Color.WHITE);
        txt.setFont(f);
        txt.setForeground(Color.WHITE);
        txt.setBackground(Color.BLACK);
        c.add(txt);
        
        ptxt = new JPasswordField();
        ptxt.setEchoChar('*');
        ptxt.setBounds(txt.getX(),txt.getY(),txt.getWidth(),txt.getHeight());
        ptxt.setBorder(null);
        ptxt.setCaretColor(Color.WHITE);
        ptxt.setFont(f);
        ptxt.setForeground(Color.WHITE);
        ptxt.setBackground(Color.BLACK);
        c.add(ptxt);
        
        ptxt.setVisible(false);
        
        txt1 = new JTextArea();
        txt1.setFont(f);
        txt1.setForeground(Color.WHITE);
        txt1.setBackground(Color.BLACK);
        txt1.setEditable(false);
        
        txt1.append("Log.console is ready.\n\n");
        
        sp1 = new JScrollPane(txt1);
        sp1.setBounds(0,0,this.getWidth()-6,240);
        sp1.getHorizontalScrollBar().setPreferredSize(new Dimension(0,10));
        sp1.getVerticalScrollBar().setPreferredSize(new Dimension(10,0));
        sp1.setBackground(Color.BLACK);
        c.add(sp1);
        
        sp1.setBorder(null);
        
        sp1.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent ae) -> {
            ae.getAdjustable().setValue(ae.getAdjustable().getMaximum());
        });
      
        lbl = new JLabel("Write Commands:");
        lbl.setFont(f);
        lbl.setForeground(Color.WHITE);
        lbl.setBounds(0,txt.getY()+2,170,25);
        c.add(lbl);
        
        loadCommands();
        
        listener();
        
    }
    
    public void listener(){
    
        
        
    txt.addKeyListener(this);
    ptxt.addKeyListener(this);
    
    this.addWindowListener(new WindowAdapter(){
        @Override
        public void windowOpened( WindowEvent e ){
        txt.requestFocus();
    }
    
    });
    
    
    
    
    }
    
    public String langChecker(String n,String w){

        String ass=w;
        
        
    if(w.equals("start")){
        
            if(n.equals(commands.get(0))){
            
                     txt1.append("UserName:/> "+n+"\n");
                    txt.setVisible(false);
                    ptxt.setVisible(true);
                    ptxt.requestFocus();
                    ass = "pass";
            
            }else if(n.equals(commands.get(7))){
            
            txt1.append("Access info:/> Not accessed yet\n");
            
            }else if(n.equals(commands.get(3))){
            
                 txt1.append("This Command app was created by Shariar Akash\n");
            
            }else if(n.equals(commands.get(4))){
                               Date currentDate = new Date();    
                    String dateToStr = DateFormat.getInstance().format(currentDate);  
                    txt1.append(dateToStr+"\n");
            
            }else if(n.equals(commands.get(9))){
            
                txt1.setText("Log.console is ready.\n\n");
            
            }else if(n.equals("")){
            }else{
            txt1.append("Unrecognized KeyWords\n");
            }

    
    }

    
    if(w.equals("pass")){
        
    if(n.equals(commands.get(1))){
    
    txt1.append("Access Granted\n");
    txt1.append("Welcome Shariar Akash\n");
    ptxt.setVisible(false);
    txt.setVisible(true);
    txt.requestFocus();
    ass="enter";
    
    }else{
    
        txt1.append("Access Denied\n");
        txt1.append("Please try again by typing username again\n");
        ptxt.setVisible(false);
        txt.setVisible(true);
        txt.requestFocus();
        ass = "start";
        
    }
    
    
    }
    
    if(w.equals("enter")){

        if(n.equals(commands.get(7))){
        
            txt1.append("Access info:> Accessed\n");
        
        }else if(n.equals(commands.get(2))){
        
         txt1.append(commands.get(15)+"\n");
         txt1.append(commands.get(16)+"\n");
         txt1.append(commands.get(17)+"\n");
         txt1.append(commands.get(18)+"\n");
         
        }else if(n.equals(commands.get(3))){
       
            txt1.append("This Command app was created by Shariar Akash\n");
        }else if(n.equals(commands.get(10))){
        
                 txt1.append("Logged out\n");
                  ass="start";
        
        }else if(n.equals(commands.get(11))){
                            try{
                    
                    txt1.append("Showing help desk text file...\n");
                    txt1.append("Doc openned successfully\n");
                    File file = new File("src/helpDesk/help.txt");
                    Desktop.getDesktop().open(file);
                    
                    }catch(IOException ae){
                    
                    txt1.append("EROR: "+ ae+ "\n");
                        
                    }
  
        
        }else if(n.equals(commands.get(6))){
        
                                try{
                    
                    txt1.append("Openning personal folder...\n");
                    txt1.append("Folder openned successfully\n");
                    File file = new File("src/.personal");
                    Desktop.getDesktop().open(file);
                    
                    }catch(IOException ae){
                    
                    txt1.append("EROR: "+ ae+ "\n");
                        
                    }
  
        
        }else if(n.equals(commands.get(4))){
        
                                Date currentDate = new Date();    
                    String dateToStr = DateFormat.getInstance().format(currentDate);  
                    txt1.append(dateToStr+"\n");
        
        }else if(n.equals(commands.get(8))){
        
        txt1.append("Nothing is running\n");
        
        }else if(n.equals(commands.get(5))){
        
                                txt1.append("Calculator Enabled\n");
                    ass="calc";
        
        }else if(n.equals(commands.get(12))){
        
                    txt1.append("Type to remember\n");
                    ass="rem";
        
        }else if(n.equals(commands.get(13))){
        
                            if(!rem.isEmpty()){
                         txt1.append("Remember Result: \n");
                         rem.forEach((_item) -> {
                        txt1.append(_item+"\n");
                    });
                    
                     
                     }else{
                     
                     txt1.append("Nothing added to remember\n");

                     
                     }
                     
        
        }else if(n.equals(commands.get(14))){
        
                                if(!rem.isEmpty()){
                        
                       rem.removeAll(rem);
                       txt1.append("Cleared all remembered data\n");
                       
                     }else{
                     
                     txt1.append("Nothing added to remember\n");

                     
                     }
 
        }else if(n.equals(commands.get(9))){
        
        txt1.setText("Log.console is ready.\n\n");
            
        }else if(n.equals(commands.get(19))){
              
                txt1.append("Training mode is on\n");
                txt1.append("1."+commands.get(2)+"---Showing personal info\n");
                txt1.append("2."+commands.get(3)+"---Showing App info\n");
                txt1.append("3."+commands.get(4)+"---Showing current Time\n");
                txt1.append("4."+commands.get(5)+"---Calculator mode is turned on\n");
                txt1.append("5."+commands.get(6)+"---Showing personal folder\n");
                txt1.append("6."+commands.get(7)+"---Showing Login accessed or not\n");
                txt1.append("7."+commands.get(8)+"---Showing current running action\n");
                txt1.append("8."+commands.get(9)+"---Clear the log option\n");
                txt1.append("9."+commands.get(10)+"---logout from the access\n");
                txt1.append("10."+commands.get(11)+"---Showing help desk\n");
                txt1.append("11."+commands.get(12)+"---Remember mode is turned on\n");
                txt1.append("12."+commands.get(13)+"---Showing all remmembered items\n");
                txt1.append("13."+commands.get(14)+"---Remove all remembered items\n");
                txt1.append("14."+commands.get(19)+"---training mode is turned on\n");
                ass="train";
                
        }else if(n.equals("")){
        }else{
        
        txt1.append("Unrecognized KeyWords\n");
            
        }
    
    }
    
    if(w.equals("train")){
        
            char[] charA = n.toCharArray();
        char[] c = new char[charA.length-2]; 
        
   if(n.startsWith("1,")){

        int j=0;
       for(int i=2;i<charA.length;i++){
           c[j]=charA[i];
           j++;
       }
       
                try {
                    writer = new FileWriter("src/helpDesk/comands.txt");
                    bw = new BufferedWriter(writer);
                    bw.write("akash4797\n" +
"rockon1997\n" +
String.valueOf(c) +
"appInfo\n" +
"getTime\n" +
"calc\n" +
"perFolder\n" +
"loginInfo\n" +
"getAction\n" +
"clear\n" +
"logout\n" +
"getHelp\n" +
"rem\n" +
"showRem\n" +
"removeAllRem\n" +
"Name:Hossain Shariar Akash\n" +
"Phone:01533891348\n" +
"Email:akashrssky@gmail.com\n" +
"Nick Name: Akash\n" +
"train");
                    
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MyApp.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MyApp.class.getName()).log(Level.SEVERE, null, ex);
                }
       
       txt1.append(String.valueOf(c)+"\n");
   
   }
    
    }
    
    if(w.equals("rem")){
    
            switch (n) {
                case "closeRem":
                    txt1.append("Remembering closed\n");
                    txt1.append("Try showRem\n");
                    ass="enter";
                    break;
                case "getAction":
                    txt1.append("Remember is running\n");
                    break;
                default:
                    Date currentDate = new Date();
                    String dateToStr = DateFormat.getInstance().format(currentDate);
                    rem.add(n+" --- "+dateToStr);
                    txt1.append("Remembered\n");
                    break;
            }


    }
    
  if(w.equals("calc")){
            switch (n) {
                
                case "loginInfo":
                    txt1.append("Access info:> Accessed\n");
                    break;
                case "myInfo":
                    txt1.append("Name:Hossain Shariar Akash\nPhone:01533892348\nEmail:akashrssky@gmail.com\nNick Name:Akash\n");
                    break;
                case "appInfo":
                    txt1.append("This Command app was created by Shariar Akash\n");
                    break;
                case "closeCalc":
                    txt1.append("Calculator Disabled\n");
                    ass="enter";
                    break;
                case "getTime":
                    Date currentDate = new Date();    
                    String dateToStr = DateFormat.getInstance().format(currentDate);  
                    txt1.append(dateToStr+"\n");
                    break;
                case "getAction":
                    txt1.append("Calculator is running\n");
                    break;
                case "clear":
                    txt1.setText("Log.console is ready.\n\n");
                    break;
                case "logout":
                    txt1.append("Logged out\n");
                    ass="start";
                    break;
                case "":
                    break;
                default:
                    try {
                        ScriptEngineManager mgr = new ScriptEngineManager();
                        ScriptEngine engine = mgr.getEngineByName("JavaScript");
                        txt1.append("Ans: "+engine.eval(n)+"\n");
                        
                    } catch (ScriptException ex) {
                        txt1.append("Unrecognized KeyWords\n");            }
                    break;
            }
        
    }
    
    return ass;
   }

    
    public static void main(String[] args)  {

        
        start();
        
 }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
           if(ke.getSource()==txt){
   
           
    if(ke.getKeyCode()== KeyEvent.VK_ENTER){


    if(check.equals("rem")){
    
            
    String x = txt.getText();
    check = langChecker(x ,check);
    
    txt.setText("");
    txt.setCaretPosition(0);
    
        
    }else{
    
    String x = txt.getText();
    check = langChecker(x.replaceAll("\\s", ""),check);
    
    txt.setText("");
    txt.setCaretPosition(0);
    
    }
    
    }
    
   
   }
           
    if(ke.getSource()==ptxt){
   
           
    if(ke.getKeyCode()== KeyEvent.VK_ENTER){

        String x = ptxt.getText();
    check = langChecker(x.replaceAll("\\s", ""),check);
    
    ptxt.setText("");
    ptxt.setCaretPosition(0);

    
    }
    
   
   }       
           
        
    }
    
    public void loadCommands(){
    
            
        try{
            String tempCom;
            reader = new BufferedReader(new FileReader("src/helpDesk/comands.txt"));
            
            while((tempCom=reader.readLine())!=null){
            
                commands.add(tempCom);
            
            }
        
        }catch(FileNotFoundException e){
        
        txt1.append("Command file doesn't exist");
        
        } catch (IOException ex) {
            
            txt1.append(ex.toString());
        }
        
        
    
    }
}
