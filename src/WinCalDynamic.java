import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class WinCalDynamic extends JDialog {
	private JTextField tfResult;
	String tfNum [] = {};
	String sign[] = {};
	boolean point=true;
	boolean count=true;
	int prevIndex=-1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinCalDynamic dialog = new WinCalDynamic();
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
	public WinCalDynamic() {
		setBounds(100, 100, 392, 418);
		
		tfResult = new JTextField();
		tfResult.setHorizontalAlignment(SwingConstants.RIGHT);
		tfResult.setFont(new Font("Gulim", Font.PLAIN, 20));
		getContentPane().add(tfResult, BorderLayout.NORTH);
		tfResult.setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		String symbol[]= {"(",")","C","��",
							"1/x","x짼","��x","/",
							"7","8","9","*",
							"4","5","6","-",
							"1","2","3","+",
							"+/-","0",".","="};
					
		for(int i=0 ; i<symbol.length ;i++) {
			JButton btn = new JButton(symbol[i]);
			btn.setFont(new Font("GimpoGothic Regular", Font.PLAIN, 16));
			if(symbol[i].chars().allMatch(Character::isDigit)) {
				btn.setBackground(Color.white);
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						tfResult.setText(tfResult.getText()+e.getActionCommand());
						count=true;
						
					}
				});
			}
			else {
				if (symbol[i].equals("=")) {
				btn.setBackground(Color.cyan);
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						StringTokenizer st = new StringTokenizer(tfResult.getText(),"+-*/()");
						while(st.hasMoreTokens()) {
							System.out.println(st.nextToken());
						}
						
					}
				});
				}
				else {
					btn.setBackground(Color.LIGHT_GRAY);
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							double dValue=0;
							if(count) {
								switch (e.getActionCommand()) {
								
									case "C": 
										tfResult.setText(""); 
										break;
									case "��": 
										if(tfResult.getText().length()>0)
										tfResult.setText(tfResult.getText().substring(0,tfResult.getText().length()-1));
										break;
									case "." :
										if(point) {
											tfResult.setText(tfResult.getText()+"."); point=false;
										}
										break;
									case "1/x" : dValue = Double.parseDouble(tfResult.getText());
										tfResult.setText(Double.toString(1/dValue));
										break;
									case "x짼" : dValue = Double.parseDouble(tfResult.getText())*Double.parseDouble(tfResult.getText());
												tfResult.setText(Double.toString(dValue));
										break;
									case "��x" : dValue = Math.sqrt(Double.parseDouble(tfResult.getText()));
												tfResult.setText(Double.toString(dValue));
										break;
									case "+/-": 
											char operation[]= {'+','-','*','/','(',')'};
											int idx=0;
											boolean bExit=false;
											for(idx=tfResult.getText().length()-1;idx>=0;idx--) {
												for(int j=0;j<operation.length;j++) {
													if(tfResult.getText().charAt(idx)==operation[j]) {
														bExit=true;
														break;
													}
												}
												if(bExit) break;
											}
											String temp = tfResult.getText().substring(idx+1);
											if(temp.charAt(0)=='-') {
												temp = temp.substring(1);
											}
											else
												temp = '-'+temp;
	
											tfResult.setText(tfResult.getText().substring(0, idx+1)+ temp);
										break;
										
									case "+" :
									case "-" :
									case "*" :
									case "/" :
									case "(" :
									case ")" :
										
										tfResult.setText(tfResult.getText()+" "+e.getActionCommand());
										
										break;
										
									default :
										tfResult.setText(tfResult.getText()+e.getActionCommand());
								}
								if(!e.getActionCommand().equals(".")) {
									point=true;
								}
								if(e.getActionCommand().equals( "+/-" )) {
									count=true;
										
								}
								else count=false;
							}	
						}
					});
				}
				
			}
			panel.add(btn);
			
		}

	}
	
	

}

