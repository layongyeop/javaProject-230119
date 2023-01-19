import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.SwingConstants;

public class WinCalcDynamic extends JDialog {
   private JTextField tfResult;
   protected boolean bPoint = false;
   protected boolean bOp = false;
   private int count = 0;
   int prevIndex;
   boolean bFirst = true;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               WinCalcDynamic dialog = new WinCalcDynamic();
               dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
               dialog.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the dialog.
    */
   public WinCalcDynamic() {
      setTitle("����");
      setBounds(100, 100, 318, 466);
      
      tfResult = new JTextField();
      tfResult.setHorizontalAlignment(SwingConstants.RIGHT);
      tfResult.setFont(new Font("����", Font.BOLD, 20));
      getContentPane().add(tfResult, BorderLayout.NORTH);
      tfResult.setColumns(10);
      
      JPanel panel = new JPanel();
      getContentPane().add(panel, BorderLayout.CENTER);
      panel.setLayout(new GridLayout(0, 4, 0, 0));
      
      String symbol[] = {
            "(",")","C","��",
            "1/x","x��","��x","/",
            "7","8","9","*",
            "4","5","6","-",
            "1","2","3","+",
            "+/-","0",".","="
            };
      for(int i=0;i<symbol.length;i++) {
         JButton btn = new JButton(symbol[i]);
         btn.setFont(new Font("����", Font.BOLD, 16));
         if(symbol[i].charAt(symbol[i].length()-1) >= '0' && symbol[i].charAt(symbol[i].length()-1) <= '9')
            btn.setBackground(Color.WHITE);
         else if(symbol[i].equals("="))
            btn.setBackground(Color.CYAN);
         else
            btn.setBackground(Color.GRAY);         
         panel.add(btn);         
         btn.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
               JButton btn1 = (JButton)e.getSource();
               //System.out.println("click : " + btn1.getText());
               
               String btnText = btn1.getText();
               String strExp = tfResult.getText();
               double dValue = 0;
               switch(btnText) {
               case "+/-":
            	   if(bFirst) {
            		   if(strExp.charAt(0) == '-') {
            			   strExp = strExp.substring(1);
            		   		count--;
            		   }
                       else {
                    	   strExp = "-" + strExp;
                           count ++;
                       }
                       tfResult.setText(strExp);
            		   
            	   }
            	   else if(bOp) {
            		   String strPrev = strExp.substring(0,prevIndex+1);
            		   String temp = strExp.substring(prevIndex+1);
            		   System.out.println(temp);
            		  
            			   
            		   
            		   if(temp.charAt(0) == '-') {
                           temp = temp.substring(1);
            		   		count--;
            		   }
                       else {
                           temp = "-" + temp;
                           count ++;
                       }
            		   tfResult.setText(strPrev+temp);
            	   }
            	  
            		   
                  
                  break;
               case ".":
                  if(bPoint==false) {  // �Ҽ����� �� �����־��ٸ� ���, �ƴϸ� �� ���
                     bPoint = true;
                     strExp = strExp + btnText;
                     tfResult.setText(strExp);
                  }                  
                  break;
               case "��x":
                  dValue = Math.sqrt(Double.parseDouble(strExp));
                  tfResult.setText(Double.toString(dValue));
                  break;
               case "x��":
                  dValue = Double.parseDouble(strExp) * Double.parseDouble(strExp);
                  tfResult.setText(Double.toString(dValue));
                  break;
               case "1/x":
                  dValue = 1 / Double.parseDouble(strExp);
                  tfResult.setText(Double.toString(dValue));
                  break;
               case "%":
                  dValue = Double.parseDouble(strExp) * 0.01;
                  tfResult.setText(Double.toString(dValue));
                  break;
               case "��":
                  count--;
                  strExp = strExp.substring(0,strExp.length()-1);
                  tfResult.setText(strExp);
                  break;
               case "C":
                  count=0;
                  bPoint = false;
                  strExp = "";
                  tfResult.setText(strExp);
                  break;      
               case "=":
                  count=0;
                  StringTokenizer st = new StringTokenizer(strExp, " ");
                  while(st.hasMoreTokens()) {
                     System.out.println(st.nextToken());
                  }
                  break;
               case "+":
               case "-":
               case "*":
               case "/":
               case "(":
               case ")":
            	   bFirst = false;
            	  prevIndex = count;
                  strExp = strExp + " " + btnText;
                  count = count + 2;
                  tfResult.setText(strExp);
                  bOp = false;  //�����ڸ� false, ���ڸ� true
                  break;
               default:
                  bPoint = false;
                  if(bOp == false) {
                	  if(bFirst ) {
                		  strExp = strExp + btnText;
                		  count ++;
                	  }
                	 else {
                     strExp = strExp + " " + btnText;
                     count = count + 2;
                     }
                  }
                  else {
                     strExp = strExp + btnText;
                     count = count + 1;
                  }
                  bOp = true;
                  tfResult.setText(strExp);
                  System.out.println(count);
               }
               
            }
         });
      }
   }

}