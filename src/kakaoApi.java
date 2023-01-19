import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.io.File;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class kakaoApi extends JDialog {
	File file = new File("D://kakao");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kakaoApi dialog = new kakaoApi();
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
	public kakaoApi() {
		setBounds(100, 100, 1208, 714);
		getContentPane().setLayout(new GridLayout(0, 20, 0, 0));
		File flielist[]=file.listFiles();
		for(int i =0; i<600;i++) {
			ImageIcon image = new ImageIcon(flielist[i].getPath());
			JLabel lbl = new JLabel(image);
			getContentPane().add(lbl);
		}

	}

}
