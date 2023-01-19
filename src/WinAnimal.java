import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class WinAnimal extends JDialog {
   private JLabel lblPic;
   private JSpinner spinner;
private JButton btnRun;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               WinAnimal dialog = new WinAnimal();
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
   public WinAnimal() {
      setTitle("�⵵ => ���� ���̱�");
      setBounds(100, 100, 352, 363);
      getContentPane().setLayout(null);
      
      JLabel lblYear = new JLabel("����:");
      lblYear.setBounds(12, 14, 57, 15);
      getContentPane().add(lblYear);
      
      btnRun = new JButton("����");
      btnRun.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showAnimal();
         }
      });
      btnRun.setBounds(226, 10, 97, 23);
      getContentPane().add(btnRun);
      
      lblPic = new JLabel("");
      lblPic.setBounds(61, 64, 200, 250);
      getContentPane().add(lblPic);
      
      spinner = new JSpinner();
      spinner.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            showAnimal();
         }
      });
      spinner.setModel(new SpinnerNumberModel(2023, 1, 2200, 1));
      spinner.setBounds(39, 11, 145, 22);
      getContentPane().add(spinner);
      
   }

   protected void showAnimal() {
      String Animal[]= {"������","��","��","����","��","��","ȣ����","�䳢","��","��","��","��"};
      
      String sibgan[] = {"��","��","��","��","��","��","��","��","��","��"};
      String cheongan[] = {"��","��","��","��","��","��","��","��","��","��","��","��"};
      
      int year =  (int)spinner.getValue();
      String sixagenary = sibgan[((year%60)%10)]+cheongan[((year%60)%12)]; // 60�� �ֱ� �ʰ� 10�� õ�� 12�� 
      
      ImageIcon img = new ImageIcon("src/img/" + Animal[year%12] + ".png");
      Image image = img.getImage();
      image = image.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
      ImageIcon pic = new ImageIcon(image);
      lblPic.setIcon(pic);
      btnRun.setText(sixagenary+"��");
   }
}