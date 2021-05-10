package Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import mining.phx;
import mining.rex;
import mining.xmr;
import wallet.CFMwallet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frame1 {

	public static int chosenPanel = 0;
	public static final String site = "http://cryptofamily.tk/cfminer/version.html";
	public static final String img = "C:\\CF\\CFM\\img\\";
	public static File CFMwalletData = new File("C:\\CF\\CFMwallet\\CFMwallet.dat");
	public static JFrame frame;
	public static JPanel panel, panel1, panel2, panel3, panel4, panel5, panel5_1, panel5_2, panel5_3, panel5_4, panel6, panel7;
	private static JLabel logo = null;// logo
	  
	private static ImageIcon mining_logo = new ImageIcon(img + "FTpng.png");
	
	private static JLabel ETH, ETC, RVN, XMR;
	
	private static JTextField pool, port, walletaddress, minerName;
	private static JComboBox crypto;
	
	private static JPasswordField passwordField;
	private static JPasswordField passwordField_1;
	
	private static JPasswordField passwordField1;
	private static boolean Unlocking = false;
	
	private static JEditorPane FTbalance1 ;
	private static JEditorPane FTbalance2 ;
	private static JEditorPane BNBbalance;
	private static JEditorPane ETHbalance;
	private static JEditorPane stats;
	
	private static JTextPane staking = new JTextPane();
	private static JTextPane unstaking = new JTextPane();
	private static boolean stakeInUse = false;
	static boolean UnStakeInUse = false;
	
	private static JLabel TransactionFee;
	private static JComboBox transferCrypto;
	private static JTextField Address;
	private static JTextField Amount;
	private static JEditorPane result;
	
	static Border red = BorderFactory.createLineBorder(Color.RED);
	static Border green = BorderFactory.createLineBorder(Color.GREEN);
	static Border yellow = BorderFactory.createLineBorder(Color.YELLOW);
	static Border black = BorderFactory.createLineBorder(Color.BLACK);

	public static Mouse mouse = new Mouse();
	public static FrameDragListener frameDragListener = new FrameDragListener();
	private static JTextField textField_4;
	private static JTextField textField_5;
	public static class Mouse extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
            frame.setCursor(cursor);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
            frame.setCursor(cursor);
		}
	}

	public static class FrameDragListener extends MouseAdapter {

        private Point mouseDownCompCoords = null;
        
        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }

    private static boolean checkMine() {
		if(pool.getText().length() >= 5) {
			if(port.getText().length() >= 2 && !hasCharacters(port.getText())) {
				if(walletaddress.getText().length() >= 1) {
					if(minerName.getText().length() >= 1) {
						return true;
					}
					else {minerName.setBorder(red);}
				}
				else {walletaddress.setBorder(red);}
			}
			else {port.setBorder(red);}
		}
		else {pool.setBorder(red);}
		
		return false;
	}
	private static void mineThread() {
		Thread t = new Thread() {
			public void run() {
				while(chosenPanel == 1) {
					if(pool.getText().length() >= 5) {pool.setBorder(null);}
					if(port.getText().length() >= 2 && !hasCharacters(port.getText())) {port.setBorder(null);}
					if(walletaddress.getText().length() >= 1) {walletaddress.setBorder(null);}
					try {Thread.sleep(150);} catch (InterruptedException e) {}
				}
			}
		};t.start();
	}
	private static void mineChoice() {
		Thread t = new Thread() {
			public void run() {
				String oldChoice = "n";
				while(chosenPanel == 1) {
					String choice = crypto.getSelectedItem().toString().toUpperCase();
					
					if(!oldChoice.equalsIgnoreCase(choice.toLowerCase())) {
						oldChoice = choice;
				File settings = new File("C:\\CF\\CFMsaves\\" + choice+ ".txt");
				if(settings.exists()) {
					String[] content = file.readAll(settings).split("<!@&>");
					pool.setText(content[0]);
					port.setText(content[1]);
					walletaddress.setText(content[2]);
					if(content.length == 4) {minerName.setText(content[3]);}
				}
				}
					try {Thread.sleep(150);} catch (InterruptedException e) {}
				}
				
			}
		};t.start();
			
	}
/*	
	private static boolean checkConnect() { //checks for the details used to connect to discord
		Border border = BorderFactory.createLineBorder(Color.RED);
		if(name.getText().length() >= 1) {
			if(discordID.getText().length() == 18) {
				return true;
			}
			else {discordID.setBorder(border);}
		}
		else {name.setBorder(border);}
		
		return false;
	}
	private static void connectThread() {
		
		Thread t = new Thread() {
			public void run() {
				while(chosenPanel == 4) {
					if(name.getText().length() >= 1) {name.setBorder(null);}
					if(discordID.getText().length() == 18) {discordID.setBorder(null);}
					try {Thread.sleep(150);} catch (InterruptedException e) {}
				}
				}
			};t.start();
	}
	private static void connectionThread() {
		Thread t = new Thread() {
			public void run() {
				File settings = new File("C:\\CF\\CFMsaves\\discord.txt");
				if(settings.exists()) {
					String[] content = file.readAll(settings).split("<!@&>");
					name.setText(content[0]);
					discordID.setText(content[1]);
					}
				while(chosenPanel == 4) {
					if(discord.discord == 0) {Connection.setText("Connection : Off");}
					else if(discord.discord == 1) {Connection.setText("Connection : On"); Connection.setBorder(green);}
					else if(discord.discord == 2) {Connection.setText("Connection : Starting"); Connection.setBorder(yellow);}
					try {Thread.sleep(150);} catch (InterruptedException e) {}
				}
				}
			};t.start();
	}
*/
	private static boolean checkWallet() {
		if(passwordField.getText().length() >= 2) {
			if(passwordField.getText().contentEquals(passwordField_1.getText())){
				return true;
			}
			else {passwordField_1.setBorder(red); return false;}
		}
		else {passwordField.setBorder(red); return false;}
	}
	private static void walletThread() {
		Thread t = new Thread() {
			public void run() {
				while(chosenPanel == 5) {
					if(passwordField.getText().length() >= 2) {passwordField.setBorder(null);}
					if(passwordField.getText().contentEquals(passwordField_1.getText())){passwordField_1.setBorder(null);}
					try {Thread.sleep(150);} catch (InterruptedException e) {}
					}
				}
			};t.start();
	} 

	private static boolean checkWalletOpen() {
		if(passwordField1.getText().length() >= 2) {
				return true;
		}
		else {passwordField1.setBorder(red); return false;}
	}
	private static void walletOpenthread() {
		Thread t = new Thread() {
			public void run() {
				while(chosenPanel == 51) {
					if(passwordField1.getText().length() >= 2) {passwordField1.setBorder(null);}
					try {Thread.sleep(150);} catch (InterruptedException e) {}
					}
				}
			};t.start();
	} 

	private static void stakingThread() {
		Thread t = new Thread() {
			public void run() {
				while(chosenPanel == 53 || chosenPanel == 52) {
					try{double amount = Double.parseDouble(staking.getText().replace(" ", "")); staking.setBorder(null);}catch(NumberFormatException e) {;}
					try{double amount = Double.parseDouble(unstaking.getText().replace(" ", "")); unstaking.setBorder(null);}catch(NumberFormatException e) {;}
					try {Thread.sleep(150);} catch (InterruptedException e) {}
				}
			}
		};t.start();
	}
	
	private static boolean isDouble(String input) {
		
		if(input.replace(" ", "").length() == 0) {return false;}
		try{ Double str = Double.parseDouble(input); }catch(NumberFormatException f) {return false;}
		return true;
	}
	private static boolean checkStaking(String amount) {
		double Amount = 0;
		try{Amount = Double.parseDouble(amount.replace(" ", ""));}catch(NumberFormatException e) {return false;}
		double excess = Double.parseDouble(CFMwallet.FTstaked + "") % 0.01;
		if(Amount + excess + Double.parseDouble(CFMwallet.FTrewards + "") >= 0.01) {return true;}
		return false;
	}

	private static void transferThread() {
		Thread t = new Thread() {
			public void run() {
				while(chosenPanel == 54) {
					try {
					if(transferCrypto.getSelectedItem().toString().toUpperCase().contentEquals("FT")) {TransactionFee.setText("Transaction Fee : " + CFMwallet.total(new BigInteger("80000"), CFMwallet.BNB_GAS_PRICE) + " BNB, Unused BNB will be placed back in your account");}
					else if(transferCrypto.getSelectedItem().toString().toUpperCase().contentEquals("BNB")) {TransactionFee.setText("Transaction Fee : " + CFMwallet.total(new BigInteger("21000"), CFMwallet.BNB_GAS_PRICE) + " BNB");}
					else {TransactionFee.setText("Transaction Fee : " + CFMwallet.total(new BigInteger("21000"), CFMwallet.ETH_GAS_PRICE) + " ETH");}
					try {Thread.sleep(150);} catch (InterruptedException e) {}
					
					if(Address.getText().replace(" ", "").length() == 42 ) {
					Address.setBorder(null);}
					double amount = 0;
					try{amount = Double.parseDouble(Amount.getText().replace(" ", "")); Amount.setBorder(null);}catch(NumberFormatException e) {}

					}catch(NullPointerException e) {;}
				}
			}
		};t.start();
	}
	private static boolean checkInput() {
		if(Address.getText().replace(" ", "").length() == 42 && Address.getText().replace(" ", "").charAt(0) == '0' && Address.getText().replace(" ", "").charAt(1) == 'x') {
			double amount = 0;
			try{amount = Double.parseDouble(Amount.getText().replace(" ", ""));}catch(NumberFormatException e) {
				result.setForeground(Color.decode("#FF5555"));
				result.setText("Invalid amount");
				Amount.setBorder(red);
				return false;
			}
			if(amount <= CFMwallet.getBalance(transferCrypto.getSelectedItem().toString())) {
				return true;
			}
			else {
				result.setForeground(Color.decode("#FF5555"));
				result.setText("Insufficient Balance");
				return false;
			}
			
			
		}
		else {
		result.setForeground(Color.decode("#FF5555"));
		result.setText("Invalid address");
		Address.setBorder(red);
		return false;
		}
	}

	private static void checkBalance() {
		Thread t = new Thread() {
			public void run() {
				while(chosenPanel == 52 || chosenPanel == 53 || chosenPanel == 54) {
					try {
				BigDecimal FTbalance = CFMwallet.getFTbalance();
				BigDecimal BNBbalance = CFMwallet.getBNBbalance();
				BigDecimal ETHbalance = CFMwallet.getETHbalance();
				String[] data = CFMwallet.getStakingStats();
				System.out.println(data[1]);
				BigDecimal FTstaked = CFMwallet.Format(CFMwallet.hexToDecimal(data[0].replace(" ", "")));
				BigInteger block = CFMwallet.hexToDecimal(data[1].replace(" ", ""));
				BigDecimal FTrewards = CFMwallet.Format(CFMwallet.hexToDecimal(data[2].replace(" ", "")));
				System.out.println(CFMwallet.address);
				
				int change = 0;//indicating which panel should be repainted 1 : both , 2 : 2 , 3 : 3

				if(!FTbalance.equals(CFMwallet.FTbalance)) {
					frame1.FTbalance2.setText(FTbalance + "");
					frame1.FTbalance1.setText(FTbalance + "");
					CFMwallet.FTbalance = FTbalance; change = 1;}
				if(!BNBbalance.equals(CFMwallet.BNBbalance)) {frame1.BNBbalance.setText(BNBbalance + "");CFMwallet.BNBbalance = BNBbalance; change = 2;}
				if(!ETHbalance.equals(CFMwallet.ETHbalance)){frame1.ETHbalance.setText(ETHbalance + "");CFMwallet.ETHbalance = ETHbalance; change = 2;}
				if(!FTstaked.equals(CFMwallet.FTstaked)) {CFMwallet.FTstaked = FTstaked; change = 3;}
				if(!FTrewards.equals(CFMwallet.FTrewards)) {CFMwallet.FTrewards = FTrewards; change = 3;}
				
				if(change == 1) {
					stats.setText("Staked FT : " + NF.toFTdecimals(FTstaked + "") + "\n\nStaking Rewards : " + NF.toFTdecimals(FTrewards + "") + "\n\nBlock Staked At : " + block);
					
					panel5_2.revalidate();
					panel5_2.repaint();
					
					panel5_3.revalidate();
					panel5_3.repaint();
				}
				else if(change == 2) {
					panel5_2.revalidate();
					panel5_2.repaint();
				}
				else if(change == 3) {
					stats.setText("Staked FT : " + NF.toFTdecimals(FTstaked.toString()) + "\n\nStaking Rewards : " + NF.toFTdecimals(FTrewards.toString()) + "\n\nBlock Staked At : " + block.toString());
					panel5_3.revalidate();
					panel5_3.repaint();
				}
				
				try {Thread.sleep(10000);} catch (InterruptedException e) {}
				System.out.println("ok");
				
					}catch(NullPointerException e) {try {Thread.sleep(10000);} catch (InterruptedException e1) {};}
				}
				
			}
	};t.start();
	}
	
public static boolean hasCharacters(String string) {
		for(int t =0; t < string.length(); ++t) {
			char c = string.charAt(t);
			String f = Character.toString(c);
			try{int m = Integer.parseInt(f);}catch(NumberFormatException e) {return true;}
		}
		return false;
	}

public static void loadPanel(JPanel panel)   {
	if(panel == frame1.panel) {chosenPanel = 0;load();}
	
	else if(panel == frame1.panel1) {chosenPanel = 1; mineThread(); mineChoice();}
	
	else if(panel == frame1.panel2) {chosenPanel = 2;}
	
	else if(panel == frame1.panel3) {chosenPanel = 3;}
	
	else if(panel == frame1.panel5) {
	 if(CFMwallet.address != "0x" && CFMwallet.FTbalance.compareTo(new BigDecimal("-1")) != 0) {chosenPanel = 52; panel = panel5_2; checkBalance();}
	 else if(CFMwalletData.exists()) {chosenPanel = 51; walletOpenthread(); panel = panel5_1;}
	 else{chosenPanel = 5; walletThread();}
	 }
	else if(panel == frame1.panel5_1) {chosenPanel = 51; walletOpenthread();}
	
	else if(panel == frame1.panel5_2) {
		if(chosenPanel == 53) {chosenPanel = 52;}
		else{chosenPanel = 52; checkBalance(); stakingThread();}

		}
	
	else if(panel == frame1.panel5_3) { chosenPanel = 53; }
	
	else if(panel == frame1.panel5_4) {chosenPanel = 54; transferThread();}
	
	else if(panel == frame1.panel6) {chosenPanel = 6;}

	Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
    frame.setCursor(cursor);
	frame.getContentPane().removeAll();
	frame.getContentPane().add(panel, BorderLayout.CENTER);
	frame.revalidate();
	frame.repaint();
	panel.revalidate();
	panel.repaint();

    }
    
public static void load() {
	Thread t = new Thread() {
		public void run() {
			while(chosenPanel == 0) {
				
				ImageIcon _ETH = new ImageIcon(img + "ETH.png");
				ImageIcon ETHon = new ImageIcon(img + "ETH_on.png");
				ImageIcon ETHstarting = new ImageIcon(img + "ETH_starting.png");
				ImageIcon ETHerror = new ImageIcon(img + "ETH_error.png");
				
				ImageIcon _ETC = new ImageIcon(img + "ETC.png");
				ImageIcon ETCon = new ImageIcon(img + "ETC_on.png");
				ImageIcon ETCstarting = new ImageIcon(img + "ETC_starting.png");
				ImageIcon ETCerror = new ImageIcon(img + "ETC_error.png");
				
				ImageIcon _RVN = new ImageIcon(img + "RVN.png");
				ImageIcon RVNon = new ImageIcon(img + "RVN_on.png");
				ImageIcon RVNstarting = new ImageIcon(img + "RVN_starting.png");
				ImageIcon RVNerror = new ImageIcon(img + "RVN_error.png");
				
				ImageIcon _XMR = new ImageIcon(img + "XMR.png");
				ImageIcon XMRon = new ImageIcon(img + "XMR_on.png");
				ImageIcon XMRstarting = new ImageIcon(img + "XMR_starting.png");
				ImageIcon XMRerror = new ImageIcon(img + "XMR_error.png");
			
				if(mining.crypto.XMR == 1) {XMR.setIcon(XMRon);}
				else if(mining.crypto.XMR == 2){XMR.setIcon(XMRstarting);}
				else if(mining.crypto.XMR == 3 || mining.crypto.XMR == 4 || mining.crypto.XMR == 5){XMR.setIcon(XMRerror);}
				else {XMR.setIcon(_XMR);}
				
				if(mining.crypto.ETH == 1) {ETH.setIcon(ETHon);}
				else if(mining.crypto.ETH == 2) {ETH.setIcon(ETHstarting);}
				else if(mining.crypto.ETH == 3 || mining.crypto.ETH == 4 || mining.crypto.ETH == 5) {ETH.setIcon(ETHerror);}
				else {ETH.setIcon(_ETH);}
				
				if(mining.crypto.ETC == 1) {ETC.setIcon(ETCon);}
				else if(mining.crypto.ETC == 2) {ETC.setIcon(ETCstarting);}
				else if(mining.crypto.ETC == 3 || mining.crypto.ETC == 4 || mining.crypto.ETC == 5) {ETC.setIcon(ETCerror);}
				else {ETC.setIcon(_ETC);}
				
				/*if(mining.crypto.RVN == 1) {RVN.setIcon(RVNon);}
				else if(mining.crypto.RVN == 2) {RVN.setIcon(RVNstarting);}
				else if(mining.crypto.RVN == 3 || mining.crypto.RVN == 4 || mining.crypto.RVN == 5) {RVN.setIcon(RVNerror);}
				else {RVN.setIcon(_RVN);}*/

				try {Thread.sleep(100);} catch (InterruptedException e) {}
			}
		}
	};t.start();
}	

public static void openCrypto(String _crypto){
	
	panel6 = new JPanel();
	panel6.setBackground(Color.decode("#343c4b"));
	panel6.setLayout(null);

	panel6.removeAll();
	
	try {initialize6();} catch (Exception e1) {}
	
	JLabel MP = new JLabel();
	MP.setForeground(Color.WHITE);
	MP.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
	MP.setBounds(65, 140, 925, 25);
	panel6.add(MP);
	
	JLabel aShares = new JLabel();
	aShares.setForeground(Color.WHITE);
	aShares.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
	aShares.setBounds(65, 180, 925, 25);
	panel6.add(aShares);
	
	JLabel rShares = new JLabel();
	rShares.setForeground(Color.WHITE);
	rShares.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
	rShares.setBounds(65, 220, 925, 25);
	panel6.add(rShares);
	
	JLabel name = new JLabel();
	name.setForeground(Color.WHITE);
	name.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
	name.setBounds(65, 260, 925, 25);
	panel6.add(name);
	
	JLabel TU = new JLabel();
	TU.setForeground(Color.WHITE);
	TU.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 18));
	TU.setBounds(65, 300, 925, 25);
	panel6.add(TU);
	
	JEditorPane wallet = new JEditorPane();
	wallet.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
	wallet.setForeground(Color.WHITE);
	wallet.setBounds(65, 340, 925, 50);
	wallet.setBackground(Color.decode("#343c4b"));
	panel6.add(wallet);
	
	JLabel Miner = new JLabel();
	Miner.setForeground(Color.WHITE);
	Miner.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
	Miner.setBounds(65, 60, 925, 25);
	panel6.add(Miner);
	
	JLabel HR = new JLabel();
	HR.setForeground(Color.WHITE);
	HR.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
	HR.setBounds(65, 100, 925, 25);
	panel6.add(HR);
	
    JLabel lblNewLabel_6_1_1 = new JLabel(_crypto.toUpperCase() + " mining statistics");
    lblNewLabel_6_1_1.setForeground(Color.WHITE);
    lblNewLabel_6_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
    lblNewLabel_6_1_1.setBounds(55, 25, 274, 30);
    panel6.add(lblNewLabel_6_1_1);
    
	JLabel lblStoped = new JLabel();
	lblStoped.setText("");
	lblStoped.setVerticalAlignment(0);
	lblStoped.setHorizontalAlignment(0);
	lblStoped.setForeground(Color.WHITE);
	lblStoped.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
	lblStoped.setBackground(new Color(52, 60, 75));
	lblStoped.setBounds(890, 439, 50, 25);
	panel6.add(lblStoped);
	
    JLabel stop_btn = new JLabel("Stop Mining");
	stop_btn.setHorizontalAlignment(SwingConstants.CENTER);
	stop_btn.setForeground(new Color(212, 226, 255));
	stop_btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
	stop_btn.setBounds(840, 466, 150, 23);
	stop_btn.addMouseListener(mouse);
	stop_btn.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mousePressed(MouseEvent e) {
			stop_btn.setBorder(yellow);
    		Thread t = new Thread() {
    			public void run() {
    		boolean  condition = false;
    		if(_crypto.equalsIgnoreCase("eth")) {if(mining.crypto.ETH != 0) {condition = true; mining.crypto.ETH = 0; mining.crypto.ETHtime = null;}}
    		
    		else if(_crypto.equalsIgnoreCase("etc")) {if(mining.crypto.ETC != 0) {condition = true; mining.crypto.ETC = 0;mining.crypto.ETCtime = null;}}
    		
    		else if(_crypto.equalsIgnoreCase("rvn")) {if(mining.crypto.RVN != 0) {condition = true; mining.crypto.RVN = 0;mining.crypto.RVNtime = null;}}
    		
    		else if(_crypto.equalsIgnoreCase("xmr")) {
    			if(mining.crypto.XMR != 0) {mining.crypto.stop("xmr");condition = true; mining.crypto.XMR = 0;mining.crypto.XMRtime = null;}
    			else{stop_btn.setBorder(red);}}
    		
    		if(condition) {
    			mining.crypto.stopGPU();	
    			lblStoped.setText("Stopped");
    			stop_btn.setBorder(null);
    			try {Thread.sleep(3000);} catch (InterruptedException e) {}
    			lblStoped.setText("");
    		}
    		else {stop_btn.setBorder(red);try {Thread.sleep(150);} catch (InterruptedException e) {} stop_btn.setBorder(null);}
    			}
    		};t.start();
    	}

    });
	panel6.add(stop_btn);
	
    JEditorPane _stop = new JEditorPane();
    _stop.setForeground(new Color(212, 226, 255));
    _stop.setFont(new Font("Tahoma", Font.PLAIN, 11));
    _stop.setEnabled(false);
    _stop.setEditable(false);
    _stop.setBackground(new Color(33, 37, 47));
	_stop.setBounds(840, 466, 150, 23);
	_stop.setBorder(black);
	panel6.add(_stop);
	
	loadPanel(panel6);
	Thread t = new Thread() {
		public void run() {
			while(chosenPanel == 6) {
				if(_crypto.equalsIgnoreCase("xmr")) {
					if(mining.crypto.XMR == 1) {Miner.setText("Miner : On");}
					else if(mining.crypto.XMR == 2) {Miner.setText("Miner : Starting");}
					else if(mining.crypto.XMR == 3) {Miner.setText("Miner : Invalid Pool Or Port Or No Wifi Connection");}
					else if(mining.crypto.XMR == 4) {Miner.setText("Miner : Inputted Address Is Not Valid");}
					else if(mining.crypto.XMR == 5) {Miner.setText("Miner : Invalid Pool");}
					else {Miner.setText("Miner : off");}
					HR.setText("Hash Rate : " + mining.crypto.XMRhash + " h/s");
					aShares.setText("Accepted Shares : " + mining.crypto.XMRAshares);
					rShares.setText("Rejected Shares : " + mining.crypto.XMRRshares);
					MP.setText("Mining Pool : " + mining.crypto.XMRMP);
					wallet.setText("Wallet : " + mining.crypto.XMRwallet);
					name.setText("Miner Name : XMR miners can't have names");
					TU.setText("Time Up [" + mining.crypto.time(mining.crypto.XMRtime) + "]");
				}
				else if(_crypto.equalsIgnoreCase("eth")) {
					if(mining.crypto.ETH == 1) {Miner.setText("Miner : On");}
					else if(mining.crypto.ETH == 2) {Miner.setText("Miner : Starting");}
					else if(mining.crypto.ETH == 3) {Miner.setText("Miner : Invalid Pool Or Port Or No Wifi Connection");}
					else if(mining.crypto.ETH == 4) {Miner.setText("Miner : Inputted Address Is Not Valid");}
					else if(mining.crypto.ETH == 5) {Miner.setText("Miner : Not enough GPU memory to mine");}
					else {Miner.setText("Miner : Off");}
					HR.setText("Hash Rate : " + mining.crypto.ETHhash + " Mh/s");
					aShares.setText("Accepted Shares : " + mining.crypto.ETHAshares);
					rShares.setText("Rejected Shares : " + mining.crypto.ETHRshares);
					MP.setText("Mining Pool : " + mining.crypto.ETHMP);
					wallet.setText("Wallet : " + mining.crypto.ETHwallet);
					name.setText("Miner Name : " + mining.crypto.ETHworker);
					TU.setText("Time Up [" + mining.crypto.time(mining.crypto.ETHtime) + "]");
					
				}
				else if(_crypto.equalsIgnoreCase("etc")) {
					if(mining.crypto.ETC == 1) {Miner.setText("Miner : On");}
					else if(mining.crypto.ETC == 2) {Miner.setText("Miner : Starting");}
					else if(mining.crypto.ETC == 3) {Miner.setText("Miner : Invalid Pool Or Port Or No Wifi Connection");}
					else if(mining.crypto.ETC == 4) {Miner.setText("Miner : Inputted Address Is Not Valid");}
					else if(mining.crypto.ETC == 5) {Miner.setText("Miner : Not enough GPU memory to mine");}
					else {Miner.setText("Miner : Off");}
					HR.setText("Hash Rate : " + mining.crypto.ETChash + " Mh/s");
					aShares.setText("Accepted Shares : " + mining.crypto.ETCAshares);
					rShares.setText("Rejected Shares : " + mining.crypto.ETCRshares);
					MP.setText("Mining Pool : " + mining.crypto.ETCMP);
					wallet.setText("Wallet : " + mining.crypto.ETCwallet);
					name.setText("Miner Name : " + mining.crypto.ETCworker);
					TU.setText("Time Up [" + mining.crypto.time(mining.crypto.ETCtime) + "]");
					
				}
				else if(_crypto.equalsIgnoreCase("rvn")) {
					if(mining.crypto.RVN == 1) {Miner.setText("Miner : On");}
					else if(mining.crypto.RVN == 2) {Miner.setText("Miner : Starting");}
					else if(mining.crypto.RVN == 3) {Miner.setText("Miner : Invalid Pool, Port, Wallet Or No Wifi Connection");}
					else {Miner.setText("Miner : Off");}
					HR.setText("Hash Rate : " + mining.crypto.RVNhash + " Kh/s");
					aShares.setText("Accepted Shares : " + mining.crypto.RVNAshares);
					rShares.setText("Rejected Shares : " + mining.crypto.RVNRshares);
					MP.setText("Mining Pool : " + mining.crypto.RVNMP);
					wallet.setText("Wallet : " + mining.crypto.RVNwallet);
					name.setText("Miner Name : " + mining.crypto.RVNworker);
					TU.setText("Time Up [" + mining.crypto.time(mining.crypto.RVNtime) + "]");
					
				}
				
				try {Thread.sleep(150);} catch (InterruptedException e) {}
				
			}
		}
	};t.start();
	
	
}

public static void main(String[] args) throws Exception{
	
	//CFMwallet.unlockWallet("test");
	//CFMwallet.makeFTtransaction("0x753ebAf6F6D5C2e3E6D469DEc5694Cd3Aa1A0c21", new BigDecimal("10000000000"));
	System.out.println(CFMwallet.web3b);
	System.out.println(CFMwallet.web3j);
	
	File BNBgas = new File("C:\\CF\\CFMwallet\\BNBgasPrice.dat");
	File ETHgas = new File("C:\\CF\\CFMwallet\\ETHgasPrice.dat");
	File wallet = new File("C:\\CF\\CFMwallet");
	
	file.newFolder(wallet);
	file.newFile(BNBgas, "5");
	file.newFile(ETHgas, "100");
	
	CFMwallet.BNB_GAS_PRICE = new BigInteger(file.readAll(BNBgas).replace(" ", "")).multiply(new BigInteger("1000000000"));
	CFMwallet.ETH_GAS_PRICE = new BigInteger(file.readAll(ETHgas).replace(" ", "")).multiply(new BigInteger("1000000000"));
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageIcon on = new ImageIcon(img + "mining_logo_test.png");
					Image logo = on.getImage();
					
				    
					frame = new JFrame();
					frame.setUndecorated(true);
					frame.setResizable(false);
					frame.setBounds(500, 250, 1000, 500);
					frame.setVisible(true);
					frame.setTitle("Crypto Family Miner");
					frame.setIconImage(logo);
					frame.setDefaultCloseOperation(0);

					
					initialize();
				    initialize1();
					initialize2();
					initialize3();
					initialize5();
					initialize5_1();
					initialize5_4();
					initialize6();
					initialize7();
					
					
					loadPanel(panel);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
	}

private static void initialize() throws Exception  {

		panel = new JPanel();
		panel.setBackground(Color.decode("#343c4b"));
		panel.setLayout(null);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(118, 16, 0, 0);
		panel.add(verticalGlue);

		JLabel version = new JLabel(dev.version);
		version.setBounds(10, 420, 45, 14);
		version.setFont(new Font("Tahoma", Font.BOLD, 11));
		version.setForeground(Color.WHITE);
		panel.add(version);
		
		ImageIcon image = new ImageIcon(img + "ETH.png");
		ETH = new JLabel(image);
		ETH.setBounds(940, 36, 50, 50);
		ETH.addMouseListener(mouse);
		ETH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openCrypto("eth");
				
			}
		});
		panel.add(ETH);

		ImageIcon image1 = new ImageIcon(img + "XMR.png");
		XMR = new JLabel(image1);
		XMR.setBounds(940, 156, 50, 50);
		XMR.addMouseListener(mouse);
		XMR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openCrypto("xmr");
			}
		});
		panel.add(XMR);
		
		ImageIcon image2 = new ImageIcon(img + "ETC.png");
		ETC = new JLabel(image2);
		ETC.setBounds(940, 96, 50, 50);
		ETC.addMouseListener(mouse);
		ETC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openCrypto("etc");
			}
		});
		panel.add(ETC);
		
		/*ImageIcon image3 = new ImageIcon(img + "RVN.png");
		RVN = new JLabel(image3);
		RVN.setBounds(940, 156, 50, 50);
		RVN.addMouseListener(mouse);
		RVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openCrypto("rvn");
			}
		});
		panel.add(RVN);*/
		
		JLabel copyRight = new JLabel("\u00A92021 by Crypto Family");
		copyRight.setBounds(865, 486, 135, 14);
		copyRight.setForeground(Color.WHITE);
		panel.add(copyRight);
	
		//ImageIcon Instagram = new ImageIcon(getClass().getResource("Instagram_30px.png"));
		
		ImageIcon Instagram = new ImageIcon(img + "instagram_25px.png");
		JLabel Insta = new JLabel(Instagram);
		Insta.setBounds(970, 455, 25, 25);
		Insta.addMouseListener(mouse);
		Insta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 try {Desktop.getDesktop().browse(new URL("https://www.instagram.com/crypto_family_/").toURI());} catch (IOException | URISyntaxException e1) {}
			}
		});
		panel.add(Insta);
		
		ImageIcon Twitter = new ImageIcon(img + "Twitter_25px.png");
		JLabel Twit = new JLabel(Twitter);
		Twit.setBounds(935, 455, 25, 25);
		Twit.addMouseListener(mouse);
		Twit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 try {Desktop.getDesktop().browse(new URL("https://twitter.com/CryptoFamily3").toURI());} catch (IOException | URISyntaxException e1) {}
			}
		});
		panel.add(Twit);
		
		ImageIcon Discord_logo = new ImageIcon(img + "Discord_25px.png");
		JLabel Dis = new JLabel(Discord_logo);
		Dis.setBounds(900, 455, 25, 25);
		Dis.addMouseListener(mouse);
		Dis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 try {Desktop.getDesktop().browse(new URL("https://discord.gg/XQSGzkC").toURI());} catch (IOException | URISyntaxException e1) {}
			}
		});
		panel.add(Dis);
		
		ImageIcon Reddit = new ImageIcon(img + "Reddit_25px.png");
		JLabel Redd = new JLabel(Reddit);
		Redd.setBounds(865, 455, 25, 25);
		Redd.addMouseListener(mouse);
		Redd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 try {Desktop.getDesktop().browse(new URL("https://www.reddit.com/r/Crypto_Family?utm_medium=android_app&utm_source=share").toURI());} catch (IOException | URISyntaxException e1) {}
			}
		});
		panel.add(Redd);

		 logo = new JLabel(mining_logo);
		 logo.setBounds(411, 185, 179, 133);
		panel.add(logo);
		
		ImageIcon ml1 = new ImageIcon(img + "home_selected.png");
		JLabel home = new JLabel(ml1);
		home.setBounds(0, 25, 45, 45);
		panel.add(home);

		layout LayOut = new layout(panel);
		
		JLabel lblNewLabel = new JLabel("Start Mining");
		lblNewLabel.setBounds(65, 36, 150, 23);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.decode("#d4e2ff"));
		lblNewLabel.addMouseListener(mouse);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadPanel(panel1);
			}
		});
		lblNewLabel.setHorizontalAlignment(0);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		JLabel lblNewLabel_3 = new JLabel("About");
		lblNewLabel_3.setBounds(65, 72, 150, 23);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(212, 226, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.addMouseListener(mouse);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadPanel(panel2);
			}
		});
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("How To Use");
		lblNewLabel_4.setBounds(65, 108, 150, 23);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(212, 226, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.addMouseListener(mouse);
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadPanel(panel3);
			}
		});
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Update");
		lblNewLabel_4_1.setBounds(65, 144, 150, 23);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(new Color(212, 226, 255));
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_1.addMouseListener(mouse);
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				lblNewLabel_4_1.setBorder(green);
				Thread t = new Thread() {
					public void run() {
				if(dev.checkForUpdate()) {
					if(frame2.load("New Update Available \n\nThere is a new version of the Crypto Family Miner \nDo you want to download it ?")) {
						 try {Desktop.getDesktop().browse(new URL("https://cryptofamily3.github.io/webpage/cfminer.html").toURI());} catch (IOException | URISyntaxException e1) {}
						 }
				}
				
				else {
					frame3.load("No Updates Available \n\nYou have the latest version of the miner");
				}
					}
				};t.start();
				lblNewLabel_4_1.setBorder(null);
			}
			
			public void mousePressed(MouseEvent e) {
				lblNewLabel_4_1.setBorder(green);
			}
			
			public void mouseReleased(MouseEvent e) {
				lblNewLabel_4_1.setBorder(null);
			}
		});
		panel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Wallet");
		lblNewLabel_4_1_1_1.setBounds(65, 180, 150, 23);
		lblNewLabel_4_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1_1.setForeground(new Color(212, 226, 255));
		lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_1_1_1.addMouseListener(mouse);
		lblNewLabel_4_1_1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadPanel(panel5);
			}
		});
		panel.add(lblNewLabel_4_1_1_1);
		
		JEditorPane button1 = new JEditorPane();
		button1.setBounds(65, 36, 150, 23);
		button1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button1.setForeground(Color.LIGHT_GRAY);
		button1.setEditable(false);
		button1.setBackground(Color.decode("#21252f"));
		button1.setBorder(border);
		panel.add(lblNewLabel);
		panel.add(button1);
		
		JEditorPane button1_1 = new JEditorPane();
		button1_1.setBounds(65, 108, 150, 23);
		button1_1.setForeground(Color.LIGHT_GRAY);
		button1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button1_1.setEditable(false);
		button1_1.setBackground(new Color(33, 37, 47));
		button1_1.setBorder(border);
		panel.add(button1_1);
		
		JEditorPane button1_1_1 = new JEditorPane();
		button1_1_1.setBounds(65, 72, 150, 23);
		button1_1_1.setForeground(Color.LIGHT_GRAY);
		button1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button1_1_1.setEditable(false);
		button1_1_1.setBackground(new Color(33, 37, 47));
		button1_1_1.setBorder(border);
		panel.add(button1_1_1);
		
		JEditorPane button1_1_2 = new JEditorPane();
		button1_1_2.setBounds(65, 144, 150, 23);
		button1_1_2.setForeground(Color.LIGHT_GRAY);
		button1_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button1_1_2.setEditable(false);
		button1_1_2.setBackground(new Color(33, 37, 47));
		button1_1_2.setBorder(border);
		panel.add(button1_1_2);
		
		JEditorPane button1_1_4 = new JEditorPane();
		button1_1_4.setBounds(65, 180, 150, 23);
		button1_1_4.setForeground(Color.LIGHT_GRAY);
		button1_1_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		button1_1_4.setEditable(false);
		button1_1_4.setBackground(new Color(33, 37, 47));
		button1_1_4.setBorder(border);
		panel.add(button1_1_4);
		
		JEditorPane sidePanel = new JEditorPane();
		sidePanel.setFont(new Font("Tahoma", Font.PLAIN, 5));
		sidePanel.setForeground(Color.WHITE);
		sidePanel.setEditable(false);
		sidePanel.setBounds(0, 25, 45, 475);
		sidePanel.setBackground(new Color(33, 37, 47));
		sidePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
	            frame.setCursor(cursor);
			}
		});
		panel.add(sidePanel);

		
	}

private static void initialize1() throws Exception  {
	
	panel1 = new JPanel();
	panel1.setBackground(Color.decode("#343c4b"));
	panel1.setLayout(null);
	
	Component verticalGlue = Box.createVerticalGlue();
	verticalGlue.setBounds(118, 16, 0, 0);
	panel1.add(verticalGlue);
	
	ImageIcon ml = new ImageIcon(img + "mine_selected.png");
	JLabel mine = new JLabel(ml);
	mine.addMouseListener(frame1.mouse);
	mine.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			frame1.loadPanel(frame1.panel1);
		}
	});
	mine.setBounds(0, 70, 45, 45);
	panel1.add(mine);
	
	layout LayOut = new layout(panel1);
	
	JLabel lblNewLabel_6 = new JLabel("Crypto");
	lblNewLabel_6.setForeground(Color.WHITE);
	lblNewLabel_6.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	lblNewLabel_6.setBounds(65, 60, 369, 25);
	panel1.add(lblNewLabel_6);
	
	JLabel lblNewLabel_6_1 = new JLabel("Pool IP");
	lblNewLabel_6_1.setForeground(Color.WHITE);
	lblNewLabel_6_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	lblNewLabel_6_1.setBounds(65, 115, 369, 25);
	panel1.add(lblNewLabel_6_1);
	
	JLabel lblNewLabel_6_2 = new JLabel("Port");
	lblNewLabel_6_2.setForeground(Color.WHITE);
	lblNewLabel_6_2.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	lblNewLabel_6_2.setBounds(65, 171, 369, 25);
	panel1.add(lblNewLabel_6_2);
	
	JLabel lblNewLabel_6_3 = new JLabel("Wallet Address");
	lblNewLabel_6_3.setForeground(Color.WHITE);
	lblNewLabel_6_3.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	lblNewLabel_6_3.setBounds(65, 227, 369, 25);
	panel1.add(lblNewLabel_6_3);
	
	JLabel lblNewLabel_6_3_1 = new JLabel("Miner Name");
	lblNewLabel_6_3_1.setForeground(Color.WHITE);
	lblNewLabel_6_3_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	lblNewLabel_6_3_1.setBounds(65, 283, 369, 25);
	panel1.add(lblNewLabel_6_3_1);
	
	crypto = new JComboBox();
	crypto.setBounds(65, 85, 100, 20);
	crypto.addItem("ETH");
	crypto.addItem("ETC");
	//crypto.addItem("RVN");
	crypto.addItem("XMR");
	panel1.add(crypto);
	
	pool = new JTextField();
	pool.setBounds(65, 140, 435, 20);
	pool.setColumns(10);
	panel1.add(pool);
	
	port = new JTextField();
	port.setColumns(10);
	port.setBounds(65, 196, 435, 20);
	panel1.add(port);
	
	walletaddress = new JTextField();
	walletaddress.setColumns(10);
	walletaddress.setBounds(65, 252, 435, 20);
	panel1.add(walletaddress);
	
	minerName = new JTextField();
	minerName.setColumns(10);
	minerName.setBounds(65, 308, 435, 20);
	panel1.add(minerName);
	
	JLabel lblNewLabel_1 = new JLabel("Choose the crypto you want to mine");
	lblNewLabel_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
	lblNewLabel_1.setForeground(Color.WHITE);
	lblNewLabel_1.setBounds(510, 85, 427, 20);
	panel1.add(lblNewLabel_1);
	
	JLabel lblNewLabel_1_1 = new JLabel("Input the IP of the pool you want to mine in, ex : eu1.ethermine.org");
	lblNewLabel_1_1.setForeground(Color.WHITE);
	lblNewLabel_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
	lblNewLabel_1_1.setBounds(510, 140, 480, 20);
	panel1.add(lblNewLabel_1_1);
	
	JLabel lblNewLabel_1_1_1 = new JLabel("Input the port of the pool, ex : 4444");
	lblNewLabel_1_1_1.setForeground(Color.WHITE);
	lblNewLabel_1_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
	lblNewLabel_1_1_1.setBounds(510, 196, 480, 20);
	panel1.add(lblNewLabel_1_1_1);
	
	JLabel lblNewLabel_1_1_1_1 = new JLabel("Input your wallet address for the crypto you're mining");
	lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
	lblNewLabel_1_1_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
	lblNewLabel_1_1_1_1.setBounds(510, 252, 480, 20);
	panel1.add(lblNewLabel_1_1_1_1);
	
	JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Give your miner a beautiful name ");
	lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
	lblNewLabel_1_1_1_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
	lblNewLabel_1_1_1_1_1.setBounds(510, 308, 480, 20);
	panel1.add(lblNewLabel_1_1_1_1_1);
	
    JLabel lblNewLabel_6_1_1 = new JLabel("Start Mining");
    lblNewLabel_6_1_1.setForeground(Color.WHITE);
    lblNewLabel_6_1_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
    lblNewLabel_6_1_1.setBounds(55, 25, 241, 30);
    panel1.add(lblNewLabel_6_1_1);
	
	JEditorPane result = new JEditorPane();
	result.setEditable(false);
	result.setForeground(Color.WHITE);
	result.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 14));
	result.setBackground(new Color(52, 60, 75));
	result.setBounds(65, 359, 925, 75);
	panel1.add(result);
	
	JLabel mine_btn = new JLabel("Start Mining");
	mine_btn.setHorizontalAlignment(SwingConstants.CENTER);
	mine_btn.setForeground(new Color(212, 226, 255));
	mine_btn.setFont(new Font("Tahoma", Font.BOLD, 11));
	mine_btn.setBounds(840, 466, 150, 23);
	mine_btn.addMouseListener(mouse);
	mine_btn.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) { 
    		if(checkMine()) {
    			String choice = crypto.getSelectedItem().toString();
    			if(mining.crypto.gpu()) {result.setText("Stopped previous mining operation \nStarted " + choice.toUpperCase() + " mining operation has started \nyou can check your mining statiscs in the main menu by clicking on the icon of the crypto you're mining");}
    			else {result.setText("Your " + choice.toUpperCase() + " mining operation has started \nyou can check your mining statiscs in the main menu by clicking on the icon of the crypto you're mining");}
    			result.setBorder(green);
    		if(choice.equalsIgnoreCase("eth")) {phx.startETH(pool.getText(), port.getText(), walletaddress.getText(), minerName.getText());}
    		else if(choice.equalsIgnoreCase("etc")) {phx.startETC(pool.getText(), port.getText(), walletaddress.getText(), minerName.getText());}
    		//else if(choice.equalsIgnoreCase("rvn")) {rex.startRVN(pool.getText(), port.getText(), walletaddress.getText(), minerName.getText());}
    		else {xmr.startXMR(pool.getText(), port.getText(), walletaddress.getText());}
    		
    		}
    		else{
    			result.setText("Failed to start mining operation \nPlease check your inputed data and try again");
    			result.setBorder(red);
    		}
    		
    	}
    	public void mousePressed(MouseEvent e) {
    		if(checkMine()) {mine_btn.setBorder(green);}
    		else {mine_btn.setBorder(red);}
    	}
    	public void mouseReleased(MouseEvent e) {
    		mine_btn.setBorder(null);
    	}
    });
	panel1.add(mine_btn);
	
	JLabel lblSaved = new JLabel();
	lblSaved.setText("Saved");
	lblSaved.setVerticalAlignment(0);
	lblSaved.setForeground(Color.WHITE);
	lblSaved.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 14));
	lblSaved.setBackground(new Color(52, 60, 75));
	lblSaved.setBounds(737, 439, 0, 25);
	panel1.add(lblSaved);
	
	JLabel default_btn = new JLabel("Set As Default Settings");
	default_btn.setHorizontalAlignment(SwingConstants.CENTER);
	default_btn.setForeground(new Color(212, 226, 255));
	default_btn.setFont(new Font("Tahoma", Font.BOLD, 11));
	default_btn.setBounds(680, 466, 150, 23);
	default_btn.addMouseListener(mouse);
	default_btn.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) { 
			if(checkMine()) {
    			Thread t = new Thread() {
    				public void run() {
    			File settings = new File("C:\\CF\\CFMsaves\\" + crypto.getSelectedItem().toString().toUpperCase() + ".txt");
    			File settingsFile = new File("C:\\CF\\CFMsaves");
    			file.newFolder(settingsFile);
//    			pool, port, walletaddress, minerName;//
    			if(settings.exists()) {file.write(settings, pool.getText() + "<!@&>" + port.getText() + "<!@&>" + walletaddress.getText() + "<!@&>" + minerName.getText());}
    			else {file.write(settings, pool.getText() + "<!@&>" + port.getText() + "<!@&>" + walletaddress.getText() + "<!@&>" + minerName.getText());}
    			
    			lblSaved.setBounds(737, 439, 50, 25);
    				//panel4.add(lblSaved);
    				try {Thread.sleep(3000);} catch (InterruptedException e1) {System.out.println("lol");}
    				lblSaved.setBounds(737, 439, 0, 25);
    				
    				}
    				
    			};t.start();

    		}
    	}
    	public void mousePressed(MouseEvent e) {
    		if(checkMine()) {default_btn.setBorder(green);}
    		else {default_btn.setBorder(red);}
    	}
    	public void mouseReleased(MouseEvent e) {
    		default_btn.setBorder(null);
    	}
    });
	panel1.add(default_btn);
	
    JEditorPane _mine = new JEditorPane();
    _mine.setForeground(new Color(212, 226, 255));
    _mine.setFont(new Font("Tahoma", Font.BOLD, 11));
    _mine.setEnabled(false);
    _mine.setEditable(false);
	_mine.setBackground(new Color(33, 37, 47));
	_mine.setBounds(840, 466, 150, 23);
	_mine.setBorder(black);
	panel1.add(_mine);
	
	JEditorPane _default = new JEditorPane();
	_default.setForeground(new Color(212, 226, 255));
	_default.setFont(new Font("Tahoma", Font.BOLD, 11));
	_default.setEnabled(false);
	_default.setEditable(false);
	_default.setBackground(new Color(33, 37, 47));
	_default.setBounds(680, 466, 150, 23);
	_default.setBorder(black);
	panel1.add(_default);
	
	
}

private static void initialize2() throws Exception  {
	
	panel2 = new JPanel();
	panel2.setBackground(Color.decode("#343c4b"));
	panel2.setLayout(null);
	
	Component verticalGlue = Box.createVerticalGlue();
	verticalGlue.setBounds(118, 16, 0, 0);
	panel2.add(verticalGlue);
	
	ImageIcon ml3 = new ImageIcon(img + "about_selected.png");
	JLabel about = new JLabel(ml3);
	about.setBounds(0, 115, 45, 45);
	panel2.add(about);
	
	layout LayOut = new layout(panel2);
	
    JLabel lblNewLabel_2 = new JLabel("About The Crypto Family Miner");
    lblNewLabel_2.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
    lblNewLabel_2.setForeground(Color.WHITE);
    lblNewLabel_2.setBounds(55, 25, 935, 30);
    panel2.add(lblNewLabel_2);
    
    JLabel lblNewLabel_5 = new JLabel("What Is The Crypto Family Miner ?");
    lblNewLabel_5.setForeground(Color.WHITE);
    lblNewLabel_5.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
    lblNewLabel_5.setBounds(65, 70, 453, 25);
    panel2.add(lblNewLabel_5);
    
    JEditorPane about_1 = new JEditorPane();
    about_1.setEditable(false);
    about_1.setForeground(Color.WHITE);
    about_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
    about_1.setText("The Crypto Family Miner is a crypto currency mining and staking tool as well as a decentralized wallet created by the Crypto Family to simplify \ncrypto mining and make it simpler and easier for anyone to mine, stake and safely hold cryptos.\r\n"
    		+ "The miner also aims at improving performance and giving users easy control over mining and staking");
    about_1.setBounds(75, 100, 915, 54);
    about_1.setBackground(Color.decode("#343c4b"));
    panel2.add(about_1);
    
    JLabel lblNewLabel_5_1 = new JLabel("What Features Does The Miner Offer ?");
    lblNewLabel_5_1.setForeground(Color.WHITE);
    lblNewLabel_5_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
    lblNewLabel_5_1.setBounds(65, 165, 482, 25);
    panel2.add(lblNewLabel_5_1);
    
    JEditorPane about_1_1 = new JEditorPane();
    about_1_1.setEditable(false);
    about_1_1.setText("The Crypto Family Miner offers ease of use , improved performance, ease of  control and management, giving it's users smooth powerful mining and easy control and management over their mining and staking.\r\n"
    		+ "Check 'How To Use' in the main menu for more info.");
    about_1_1.setForeground(Color.WHITE);
    about_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
    about_1_1.setBackground(new Color(52, 60, 75));
    about_1_1.setBounds(75, 195, 915, 54);
    panel2.add(about_1_1);
    
    JEditorPane about_1_1_1 = new JEditorPane();
    about_1_1_1.setEditable(false);
    about_1_1_1.setText("Yes, future updates will bring more and more cryptos to the miner for everyone to mine, aswell as more cryptos to stake");
    about_1_1_1.setForeground(Color.WHITE);
    about_1_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
    about_1_1_1.setBackground(new Color(52, 60, 75));
    about_1_1_1.setBounds(75, 290, 915, 22);
    panel2.add(about_1_1_1);
    
    JLabel lblNewLabel_5_1_1 = new JLabel("Is The Miner Gonna Support Any Other Cryptos ?");
    lblNewLabel_5_1_1.setForeground(Color.WHITE);
    lblNewLabel_5_1_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
    lblNewLabel_5_1_1.setBounds(65, 260, 608, 25);
    panel2.add(lblNewLabel_5_1_1);
    
    JEditorPane about_1_1_1_1 = new JEditorPane();
    about_1_1_1_1.setEditable(false);
    about_1_1_1_1.setText("The miner doesn't  charge any fees, it's a free to use tool for everyone to enjoy");
    about_1_1_1_1.setForeground(Color.WHITE);
    about_1_1_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
    about_1_1_1_1.setBackground(new Color(52, 60, 75));
    about_1_1_1_1.setBounds(75, 353, 915, 25);
    panel2.add(about_1_1_1_1);
    
    JLabel lblNewLabel_5_1_1_1 = new JLabel("Does The Miner Charge Fees ?");
    lblNewLabel_5_1_1_1.setForeground(Color.WHITE);
    lblNewLabel_5_1_1_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
    lblNewLabel_5_1_1_1.setBounds(65, 323, 608, 25);
    panel2.add(lblNewLabel_5_1_1_1);
    
	JLabel lblt = new JLabel("More About The Crypto Family Miner");
	lblt.setForeground(Color.WHITE);
	lblt.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	lblt.setBounds(65, 389, 608, 25);
	panel2.add(lblt);
	
	JEditorPane lblt1 = new JEditorPane();
	lblt1.setText("The Crypto Family miner is a fully decentralized miner and wallet that doesn't require any ID verification or goverment permission, neither can it \r\nbe stopped or disabeled by anyone, it's a free and open to use tool for everyone, everywhere and anywhere to use");
	lblt1.setForeground(Color.WHITE);
	lblt1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
	lblt1.setEditable(false);
	lblt1.setBackground(new Color(52, 60, 75));
	lblt1.setBounds(75, 419, 915, 38);
	panel2.add(lblt1);
    
}

private static void initialize3() throws Exception  {
	
	panel3 = new JPanel();
	panel3.setBackground(Color.decode("#343c4b"));
	panel3.setLayout(null);
	
	Component verticalGlue = Box.createVerticalGlue();
	verticalGlue.setBounds(118, 16, 0, 0);
	panel3.add(verticalGlue);
	
	ImageIcon ml4 = new ImageIcon(img + "how_selected.png");
	JLabel how = new JLabel(ml4);
	how.setBounds(0, 160, 45, 45);
	panel3.add(how);
	
	layout LayOut = new layout(panel3);
    
    JLabel lblNewLabel_6 = new JLabel("How To Use ");
    lblNewLabel_6.setForeground(Color.WHITE);
    lblNewLabel_6.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
    lblNewLabel_6.setBounds(55, 25, 369, 30);
    panel3.add(lblNewLabel_6);
    
    JLabel lblNewLabel_5 = new JLabel("How To Mine ?");
    lblNewLabel_5.setForeground(Color.WHITE);
    lblNewLabel_5.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
    lblNewLabel_5.setBounds(65, 70, 453, 25);
    panel3.add(lblNewLabel_5);
    
    JEditorPane how_1 = new JEditorPane();
    how_1.setEditable(false);
    how_1.setForeground(Color.WHITE);
    how_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
    how_1.setText("On the main screen click on 'Start Mining', in there you can choose the crypto you want to mine, the pool you want to mine in and it's port, the \naddress you want to mine to and the name you want to give your worker. \r\n"
    		+ "Then click on 'Start Mining', and your mining will start.");
    how_1.setBounds(75, 100, 915, 54);
    how_1.setBackground(Color.decode("#343c4b"));
    panel3.add(how_1);
    
    JLabel lblNewLabel_5_1 = new JLabel("How To Check My Statistics ?");
    lblNewLabel_5_1.setForeground(Color.WHITE);
    lblNewLabel_5_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
    lblNewLabel_5_1.setBounds(65, 165, 453, 25);
    panel3.add(lblNewLabel_5_1);
    
    JEditorPane how_1_1 = new JEditorPane();
    how_1_1.setEditable(false);
    how_1_1.setText("On the main screen you can click on the cryptos icons on the right to show their statistics (Time Up, Accepted Shares, Rejected Shares, Hash Rate, Pool, Address, Miner<On,Off>), from there you can also turn off a mining process.");
    how_1_1.setForeground(Color.WHITE);
    how_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
    how_1_1.setBackground(new Color(52, 60, 75));
    how_1_1.setBounds(75, 195, 915, 38);
    panel3.add(how_1_1);
    
    JEditorPane how_1_1_1 = new JEditorPane();
    how_1_1_1.setEditable(false);
    how_1_1_1.setText("On the main screen click on 'Wallet', or on the side panel click on the wallet icon. In there you will be able to create a wallet and encrypt it with a password.  Following that you'll be able to access your wallet  at any time."
    		+ "\nIn there you will be able to view your balances of the supported cryptos as well as your deposit address and the option to transfer cryptos to \nother users."
    		+ "\nInside of the wallet you will see an option that says 'Staking', click on it and you will be redirected to the staking menu, in there you will see yourstaking statistics and the ability to stake more or unstake"
    		+ "\nCurrently the Crypto Family miner supports FT (Family Token) staking and aims to fascilitate it and make it easier and simpler for all users to \nstake their FT and manage it");
    how_1_1_1.setForeground(Color.WHITE);
    how_1_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
    how_1_1_1.setBackground(new Color(52, 60, 75));
    how_1_1_1.setBounds(75, 274, 915, 134);
    panel3.add(how_1_1_1);
    
    JLabel lblNewLabel_5_1_1 = new JLabel("How To Hold Cryptos And Stake ?");
    lblNewLabel_5_1_1.setForeground(Color.WHITE);
    lblNewLabel_5_1_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
    lblNewLabel_5_1_1.setBounds(65, 244, 453, 25);
    panel3.add(lblNewLabel_5_1_1);
    
	JLabel lblt = new JLabel("How To Keep My Funds Safe?");
	lblt.setForeground(Color.WHITE);
	lblt.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	lblt.setBounds(65, 419, 453, 25);
	panel3.add(lblt);
	
	JEditorPane lblt1 = new JEditorPane();
	lblt1.setText("The encryption used by the Crypto Family Miner on your wallet is unhackable, so no need to worry about anyone accessing your wallet without the password, but the wallet is stored locally on your device, so it is recommended to store the wallet file on a USB or on a backup hard disk to \nprevent the loss of your funds in case of any damage or loss to the current hard disk");
	lblt1.setForeground(Color.WHITE);
	lblt1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
	lblt1.setEditable(false);
	lblt1.setBackground(new Color(52, 60, 75));
	lblt1.setBounds(75, 449, 915, 51);
	panel3.add(lblt1);
    
}

private static void initialize5() throws Exception  {
	
	panel5 = new JPanel();
	panel5.setBackground(Color.decode("#343c4b"));
	panel5.setLayout(null);
	
	Component verticalGlue = Box.createVerticalGlue();
	verticalGlue.setBounds(118, 16, 0, 0);
	panel5.add(verticalGlue);
	
	ImageIcon ml6 = new ImageIcon(img + "wallet_selected.png");
	JLabel wallet = new JLabel(ml6);
	wallet.setBounds(0, 204, 45, 45);
	panel5.add(wallet);
	
	layout LayOut = new layout(panel5);
	 
		passwordField = new JPasswordField();
		passwordField.setBounds(374, 202, 295, 20);
		panel5.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(374, 264, 295, 20);
		panel5.add(passwordField_1);
		
     	JLabel createWallet = new JLabel("Create Wallet");
			createWallet.setBounds(447, 342, 150, 23);
			createWallet.setFont(new Font("Tahoma", Font.BOLD, 11));
			createWallet.setForeground(Color.decode("#d4e2ff"));
			createWallet.addMouseListener(mouse);
			createWallet.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(checkWallet()) {
						System.out.println("yes");
						CFMwallet.createWallet(passwordField.getText().replace(" ", ""));
						loadPanel(panel5_1);
					}
					else {System.out.println("no");}
				}
				public void mousePressed(MouseEvent e) {
					if(checkWallet()) {
						createWallet.setBorder(green);
					}
				}
			});
			createWallet.setHorizontalAlignment(0);
			panel5.add(createWallet);
			
			JEditorPane button1 = new JEditorPane();
			button1.setBounds(447, 342, 150, 23);
			button1.setFont(new Font("Tahoma", Font.BOLD, 11));
			button1.setForeground(Color.LIGHT_GRAY);
			button1.setEditable(false);
			button1.setBackground(Color.decode("#21252f"));
			button1.setBorder(black);
			panel5.add(button1);
			
		    JLabel lblNewLabel = new JLabel("It seems like you haven't made a wallet yet");
		    lblNewLabel.setForeground(Color.WHITE);
		    lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		    lblNewLabel.setVerticalAlignment(0);
		    lblNewLabel.setHorizontalAlignment(0);
		    lblNewLabel.setBounds(77, 89, 890, 28);
		    
		    panel5.add(lblNewLabel);
		    
			JLabel lblNewLabel_1 = new JLabel("Let's make one !");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
			lblNewLabel_1.setBounds(77, 128, 890, 28);
			lblNewLabel_1.setVerticalAlignment(0);
			lblNewLabel_1.setHorizontalAlignment(0);
			panel5.add(lblNewLabel_1);
			
			JLabel password = new JLabel("Password");
			password.setVerticalAlignment(SwingConstants.CENTER);
			password.setForeground(Color.WHITE);
			password.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
			password.setBounds(374, 177, 295, 18);
			panel5.add(password);
			
			JLabel confirmPassword = new JLabel("Confirm Password");
			confirmPassword.setVerticalAlignment(SwingConstants.CENTER);
			confirmPassword.setForeground(Color.WHITE);
			confirmPassword.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
			confirmPassword.setBounds(374, 239, 295, 18);
			panel5.add(confirmPassword);
			
			JLabel tro = new JLabel("/!\\ This wallet is stored locally on your C drive, don't loose it /!\\");
			tro.setVerticalAlignment(SwingConstants.CENTER);
			tro.setHorizontalAlignment(SwingConstants.CENTER);
			tro.setForeground(Color.WHITE);
			tro.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 17));
			tro.setBounds(118, 407, 849, 28);
			panel5.add(tro);
			
			JLabel tro_1 = new JLabel("/!\\ This is a decentralized program, so if you lose your password your funds cannot be recovered /!\\");
			tro_1.setVerticalAlignment(SwingConstants.CENTER);
			tro_1.setHorizontalAlignment(SwingConstants.CENTER);
			tro_1.setForeground(Color.WHITE);
			tro_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 17));
			tro_1.setBounds(77, 443, 890, 28);
			panel5.add(tro_1);
    
    
}

private static void initialize5_1() throws Exception {
	
	panel5_1 = new JPanel();
	panel5_1.setBackground(Color.decode("#343c4b"));
	panel5_1.setLayout(null);
	
	Component verticalGlue = Box.createVerticalGlue();
	verticalGlue.setBounds(118, 16, 0, 0);
	panel5_1.add(verticalGlue);
	
	ImageIcon ml6 = new ImageIcon(img + "wallet_selected.png");
	JLabel wallet = new JLabel(ml6);
	wallet.setBounds(0, 204, 45, 45);
	panel5_1.add(wallet);
	
	layout LayOut = new layout(panel5_1);
	
	JLabel passwordStat = new JLabel("");
	passwordStat.setVerticalAlignment(SwingConstants.CENTER);
	passwordStat.setHorizontalAlignment(SwingConstants.CENTER);
	passwordStat.setForeground(Color.decode("#FF5555"));
	passwordStat.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	passwordStat.setBounds(104, 370, 882, 28);
	panel5_1.add(passwordStat);
  	
	passwordField1 = new JPasswordField();
	passwordField1.setBounds(397, 240, 295, 20);
	passwordField1.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 10) {
				if(Unlocking) {return ;}
				if(checkWalletOpen()) {
					if(CFMwallet.unlockWallet(passwordField1.getText().replace(" ", ""))) {
						Unlocking = true;
						Thread t = new Thread() {
							public void run() {
								passwordStat.setForeground(Color.GREEN);
								passwordStat.setText("Loading Wallet...");
								System.out.println(CFMwallet.address);
								try{
								CFMwallet.loadStakingSats();
								System.out.println("ok");
								CFMwallet.loadBalances();
								System.out.println("ok");

									try {initialize5_2(); initialize5_3(); initialize5_4();} catch (Exception e1) {System.out.println("error");e1.printStackTrace();}
									loadPanel(panel5_2);
								
								}catch(NullPointerException e) {
								passwordStat.setForeground(Color.decode("#FF5555"));
								passwordStat.setText("Unable To Connect To The Block Chain, Make Sure You Have Wifi Connection");
								Unlocking = false;
								}
							}
						};t.start();

					}
					else {passwordStat.setText("Wrong Password");}
				}
			}
		}
	});
		panel5_1.add(passwordField1);
		
	  	JLabel createWallet = new JLabel("Unlock Wallet");
			createWallet.setBounds(470, 300, 150, 23);
			createWallet.setFont(new Font("Tahoma", Font.BOLD, 11));
			createWallet.setForeground(Color.decode("#d4e2ff"));
			createWallet.addMouseListener(mouse);
			createWallet.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(Unlocking) {return ;}
					if(checkWalletOpen()) {
						if(CFMwallet.unlockWallet(passwordField1.getText().replace(" ", ""))) {
							Unlocking = true;
							Thread t = new Thread() {
								public void run() {
									passwordStat.setForeground(Color.GREEN);
									passwordStat.setText("Loading Wallet...");
									System.out.println(CFMwallet.address);
									try{
									CFMwallet.loadStakingSats();
									System.out.println("ok");
									CFMwallet.loadBalances();
									System.out.println("ok");

										try {initialize5_2(); initialize5_3(); initialize5_4();} catch (Exception e1) {System.out.println("error");e1.printStackTrace();}
										loadPanel(panel5_2);
									
									}catch(NullPointerException e) {
									passwordStat.setForeground(Color.decode("#FF5555"));
									passwordStat.setText("Unable To Connect To The Block Chain, Make Sure You Have Wifi Connection");
									Unlocking = false;
									}
								}
							};t.start();

						}
						else {passwordStat.setText("Wrong Password");}
					}
				}
				public void mousePressed(MouseEvent e) {
					createWallet.setBorder(green);
				}
				public void mouseReleased(MouseEvent e) {
					createWallet.setBorder(null);
				}
			});
			createWallet.setHorizontalAlignment(0);
			panel5_1.add(createWallet);
			
			JEditorPane button1 = new JEditorPane();
			button1.setBounds(470, 300, 150, 23);
			button1.setFont(new Font("Tahoma", Font.BOLD, 11));
			button1.setForeground(Color.LIGHT_GRAY);
			button1.setEditable(false);
			button1.setBackground(Color.decode("#21252f"));
			button1.setBorder(black);
			panel5_1.add(button1);
			
			JLabel password = new JLabel("Password");
			password.setVerticalAlignment(SwingConstants.CENTER);
			password.setForeground(Color.WHITE);
			password.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
			password.setBounds(397, 215, 295, 18);
			panel5_1.add(password);
			
			JLabel lblNewLabel_1_1 = new JLabel("Welcome back !");
			lblNewLabel_1_1.setVerticalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setForeground(Color.WHITE);
			lblNewLabel_1_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
			lblNewLabel_1_1.setBounds(348, 153, 392, 28);
			panel5_1.add(lblNewLabel_1_1);
	 
    
}

private static void initialize5_2() throws Exception {
	
	panel5_2 = new JPanel();
	panel5_2.setBackground(Color.decode("#343c4b"));
	panel5_2.setLayout(null);
	
	Component verticalGlue = Box.createVerticalGlue();
	verticalGlue.setBounds(118, 16, 0, 0);
	panel5_2.add(verticalGlue);
	
	ImageIcon ml6 = new ImageIcon(img + "wallet_selected.png");
	JLabel wallet = new JLabel(ml6);
	wallet.setBounds(0, 204, 45, 45);
	panel5_2.add(wallet);
	
	layout LayOut = new layout(panel5_2);
	 
	
	JLabel Coppied = new JLabel("Coppied !");
	Coppied.setHorizontalAlignment(SwingConstants.CENTER);
	Coppied.setForeground(new Color(212, 226, 255));
	Coppied.setFont(new Font("Tahoma", Font.BOLD, 11));
	Coppied.setBounds(840, 81, 150, 23);
	
	JLabel Copy = new JLabel("Copy");
	Copy.setHorizontalAlignment(SwingConstants.CENTER);
	Copy.setForeground(new Color(212, 226, 255));
	Copy.setFont(new Font("Tahoma", Font.BOLD, 11));
	Copy.setBounds(840, 81, 150, 23);
	Copy.addMouseListener(mouse);
	Copy.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			Thread t = new Thread() {public void run() {
			Coppied.setBounds(840, 58, 150, 23);
			String myString = CFMwallet.address;
			StringSelection stringSelection = new StringSelection(myString);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			try {Thread.sleep(3000);} catch (InterruptedException e) {}
			Coppied.setBounds(840, 81, 150, 23);
			}};t.start();
			
		}
		public void mousePressed(MouseEvent e) {
			Copy.setBorder(green);
		}
		public void mouseReleased(MouseEvent e) {
			Copy.setBorder(null);
		}
	});
	
	JEditorPane CopyButton = new JEditorPane();
	CopyButton.setForeground(Color.LIGHT_GRAY);
	CopyButton.setFont(new Font("Tahoma", Font.BOLD, 11));
	CopyButton.setEditable(false);
	CopyButton.setBorder(black);
	CopyButton.setBackground(new Color(33, 37, 47));
	CopyButton.setBounds(840, 81, 150, 23);
	panel5_2.add(Copy);
	panel5_2.add(CopyButton);
	panel5_2.add(Coppied);
	
	FTbalance1 = new JEditorPane();
	FTbalance1.setFont(new Font("Tahoma", Font.BOLD, 15));
	FTbalance1.setForeground(Color.WHITE);
	FTbalance1.setText(NF.toFTdecimals(CFMwallet.FTbalance + ""));
	FTbalance1.setBounds(125, 198, 845, 25);
	FTbalance1.setBackground(new Color(70, 70, 80));
	panel5_2.add(FTbalance1);

	BNBbalance = new JEditorPane();
	BNBbalance.setForeground(Color.WHITE);
	BNBbalance.setText(NF.toFTdecimals(CFMwallet.BNBbalance + ""));
	BNBbalance.setFont(new Font("Tahoma", Font.BOLD, 15));
	BNBbalance.setBounds(125, 300, 845, 26);
	BNBbalance.setBackground(new Color(70, 70, 80));
	panel5_2.add(BNBbalance);
	
	ETHbalance = new JEditorPane();
	ETHbalance.setForeground(Color.WHITE);
	ETHbalance.setText(NF.toFTdecimals(CFMwallet.ETHbalance + ""));
	ETHbalance.setFont(new Font("Tahoma", Font.BOLD, 15));
	ETHbalance.setBounds(125, 404, 845, 26);
	ETHbalance.setBackground(new Color(70, 70, 80));
	panel5_2.add(ETHbalance);
	
	ImageIcon FT = new ImageIcon(img + "FT_40px.png");
	ImageIcon BNB = new ImageIcon(img + "BNB_40px.png");
	ImageIcon ETH = new ImageIcon(img + "ETH_40px.png");
	
	JLabel Transfer = new JLabel("Transfer");
	Transfer.setBounds(840, 466, 150, 23);
	Transfer.setFont(new Font("Tahoma", Font.BOLD, 11));
	Transfer.setForeground(Color.decode("#d4e2ff"));
	Transfer.addMouseListener(mouse);
	Transfer.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			loadPanel(panel5_4);
		}
	});
	Transfer.setHorizontalAlignment(0);
	panel5_2.add(Transfer);
	
	JEditorPane TransferButton = new JEditorPane();
	TransferButton.setBounds(840, 466, 150, 23);
	TransferButton.setFont(new Font("Tahoma", Font.BOLD, 11));
	TransferButton.setForeground(Color.LIGHT_GRAY);
	TransferButton.setEditable(false);
	TransferButton.setBackground(Color.decode("#21252f"));
	TransferButton.setBorder(black);
	panel5_2.add(TransferButton);
	
	JLabel FTicon = new JLabel(FT);
	FTicon.setBounds(73, 184, 52, 52);
	panel5_2.add(FTicon);
	
	JLabel BNBicon = new JLabel(BNB);
	BNBicon.setBounds(73, 287, 50, 50);
	panel5_2.add(BNBicon);
	
	JLabel ETHicon = new JLabel(ETH);
	ETHicon.setBounds(73, 391, 50, 50);
	panel5_2.add(ETHicon);
	
	
	JLabel lblNewLabel_9 = new JLabel("The following address can be used to deposit FT, BNB, and ETH to your wallet ");
	lblNewLabel_9.setForeground(Color.WHITE);
	lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblNewLabel_9.setBounds(55, 25, 935, 45);
	panel5_2.add(lblNewLabel_9);
	
	JLabel Address = new JLabel(CFMwallet.address);
	Address.setFont(new Font("Tahoma", Font.BOLD, 20));
	Address.setForeground(Color.WHITE);
	Address.setBounds(55, 70, 755, 45);
	Address.setVerticalAlignment(0);
	panel5_2.add(Address);
	
	JEditorPane midPanel = new JEditorPane();
	midPanel.setEnabled(false);
	midPanel.setEditable(false);
	midPanel.setFont(new Font("Tahoma", Font.BOLD, 25));
	midPanel.setBackground(Color.decode("#202035"));
	midPanel.setBounds(45, 25, 955, 90);
	midPanel.setBorder(black);
	panel5_2.add(midPanel);
	
	JLabel lblNewLabel_8 = new JLabel("Wallet");
	lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblNewLabel_8.setForeground(Color.WHITE);
	lblNewLabel_8.setBounds(45, 115, 478, 30);
	lblNewLabel_8.setVerticalAlignment(0);
	lblNewLabel_8.setHorizontalAlignment(0);
	panel5_2.add(lblNewLabel_8);
	
	
	JLabel Staking = new JLabel("Staking");
	Staking.setVerticalAlignment(SwingConstants.CENTER);
	Staking.setHorizontalAlignment(SwingConstants.CENTER);
	Staking.setForeground(Color.WHITE);
	Staking.setFont(new Font("Tahoma", Font.BOLD, 13));
	Staking.setBounds(523, 115, 477, 30);
	Staking.addMouseListener(mouse);
	Staking.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			loadPanel(panel5_3);
		}
	});
	panel5_2.add(Staking);
	
	JEditorPane stakingBG = new JEditorPane();
	stakingBG.setFont(new Font("Tahoma", Font.BOLD, 25));
	stakingBG.setEnabled(false);
	stakingBG.setEditable(false);
	stakingBG.setBorder(black);
	stakingBG.setBackground(new Color(32, 32, 53));
	stakingBG.setBounds(523, 115, 477, 30);
	panel5_2.add(stakingBG);
	
	JEditorPane sidePanel_2 = new JEditorPane();
	sidePanel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
	sidePanel_2.setEnabled(false);
	sidePanel_2.setEditable(false);
	sidePanel_2.setBorder(black);
	sidePanel_2.setBackground(new Color(70, 70, 80));
	sidePanel_2.setBounds(73, 184, 898, 52);
	panel5_2.add(sidePanel_2);
	
	JEditorPane sidePanel_2_1 = new JEditorPane();
	sidePanel_2_1.setFont(new Font("Tahoma", Font.BOLD, 25));
	sidePanel_2_1.setEnabled(false);
	sidePanel_2_1.setEditable(false);
	sidePanel_2_1.setBorder(black);
	sidePanel_2_1.setBackground(new Color(70, 70, 80));
	sidePanel_2_1.setBounds(73, 287, 898, 52);
	panel5_2.add(sidePanel_2_1);
	
	JEditorPane sidePanel_2_2 = new JEditorPane();
	sidePanel_2_2.setFont(new Font("Tahoma", Font.BOLD, 25));
	sidePanel_2_2.setEnabled(false);
	sidePanel_2_2.setEditable(false);
	sidePanel_2_2.setBorder(black);
	sidePanel_2_2.setBackground(new Color(70, 70, 80));
	sidePanel_2_2.setBounds(73, 391, 898, 52);
	panel5_2.add(sidePanel_2_2);
    
}

private static void initialize5_3() throws Exception {
	
	panel5_3 = new JPanel();
	panel5_3.setBackground(Color.decode("#343c4b"));
	panel5_3.setLayout(null);
	
	Component verticalGlue = Box.createVerticalGlue();
	verticalGlue.setBounds(118, 16, 0, 0);
	panel5_3.add(verticalGlue);
	
	ImageIcon ml6 = new ImageIcon(img + "wallet_selected.png");
	JLabel wallet = new JLabel(ml6);
	wallet.setBounds(0, 204, 45, 45);
	panel5_3.add(wallet);
	
	layout LayOut = new layout(panel5_3);
	 
	JLabel Coppied = new JLabel("Coppied !");
	Coppied.setHorizontalAlignment(SwingConstants.CENTER);
	Coppied.setForeground(new Color(212, 226, 255));
	Coppied.setFont(new Font("Tahoma", Font.BOLD, 11));
	Coppied.setBounds(840, 81, 150, 23);
	
	JLabel Copy = new JLabel("Copy");
	Copy.setHorizontalAlignment(SwingConstants.CENTER);
	Copy.setForeground(new Color(212, 226, 255));
	Copy.setFont(new Font("Tahoma", Font.BOLD, 11));
	Copy.setBounds(840, 81, 150, 23);
	Copy.addMouseListener(mouse);
	Copy.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			Thread t = new Thread() {public void run() {
			Coppied.setBounds(840, 58, 150, 23);
			String myString = CFMwallet.address;
			StringSelection stringSelection = new StringSelection(myString);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			try {Thread.sleep(3000);} catch (InterruptedException e) {}
			Coppied.setBounds(840, 81, 150, 23);
			}};t.start();
			
		}
		public void mousePressed(MouseEvent e) {
			Copy.setBorder(green);
		}
		public void mouseReleased(MouseEvent e) {
			Copy.setBorder(null);
		}
	});
	
	JEditorPane CopyButton = new JEditorPane();
	CopyButton.setForeground(Color.LIGHT_GRAY);
	CopyButton.setFont(new Font("Tahoma", Font.BOLD, 11));
	CopyButton.setEditable(false);
	CopyButton.setBorder(black);
	CopyButton.setBackground(new Color(33, 37, 47));
	CopyButton.setBounds(840, 81, 150, 23);
	panel5_3.add(Copy);
	panel5_3.add(CopyButton);
	panel5_3.add(Coppied);
	
	FTbalance2 = new JEditorPane();
	FTbalance2.setFont(new Font("Tahoma", Font.BOLD, 15));
	FTbalance2.setForeground(Color.WHITE);
	FTbalance2.setText(NF.toFTdecimals(CFMwallet.FTbalance + ""));
	FTbalance2.setBackground(new Color(70, 70, 80));
	FTbalance2.setBounds(125, 198, 840, 25);
	panel5_3.add(FTbalance2);
	
	ImageIcon FT = new ImageIcon(img + "FT_40px.png");
	
	JLabel FTicon = new JLabel(FT);
	FTicon.setBounds(73, 184, 52, 52);
	panel5_3.add(FTicon);
	
	
	JLabel lblNewLabel_9 = new JLabel("The following address can be used to deposit FT, BNB, and ETH to your wallet ");
	lblNewLabel_9.setForeground(Color.WHITE);
	lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblNewLabel_9.setBounds(55, 25, 935, 45);
	panel5_3.add(lblNewLabel_9);
	
	JLabel Address = new JLabel(CFMwallet.address);
	Address.setFont(new Font("Tahoma", Font.BOLD, 20));
	Address.setForeground(Color.WHITE);
	Address.setBounds(55, 70, 755, 45);
	Address.setVerticalAlignment(0);
	panel5_3.add(Address);
	
	JEditorPane midPanel = new JEditorPane();
	midPanel.setEnabled(false);
	midPanel.setEditable(false);
	midPanel.setFont(new Font("Tahoma", Font.BOLD, 25));
	midPanel.setBackground(Color.decode("#202035"));
	midPanel.setBounds(45, 25, 955, 90);
	midPanel.setBorder(black);
	panel5_3.add(midPanel);
	
	JLabel lblNewLabel_8 = new JLabel("Wallet");
	lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblNewLabel_8.setForeground(Color.WHITE);
	lblNewLabel_8.setBounds(45, 115, 478, 30);
	lblNewLabel_8.setVerticalAlignment(0);
	lblNewLabel_8.setHorizontalAlignment(0);
	lblNewLabel_8.addMouseListener(mouse);
	lblNewLabel_8.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			loadPanel(panel5_2);
		}
	});
	panel5_3.add(lblNewLabel_8);
	
	
	JLabel lblNewLabel_8_1 = new JLabel("Staking");
	lblNewLabel_8_1.setVerticalAlignment(SwingConstants.CENTER);
	lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_8_1.setForeground(Color.WHITE);
	lblNewLabel_8_1.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblNewLabel_8_1.setBounds(523, 115, 477, 30);
	panel5_3.add(lblNewLabel_8_1);
	
	JEditorPane stakingBG = new JEditorPane();
	stakingBG.setFont(new Font("Tahoma", Font.BOLD, 25));
	stakingBG.setEnabled(false);
	stakingBG.setEditable(false);
	stakingBG.setBorder(black);
	stakingBG.setBackground(new Color(32, 32, 53));
	stakingBG.setBounds(45, 115, 477, 30);
	panel5_3.add(stakingBG);
	
	JEditorPane sidePanel_2 = new JEditorPane();
	sidePanel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
	sidePanel_2.setEnabled(false);
	sidePanel_2.setEditable(false);
	sidePanel_2.setBorder(black);
	sidePanel_2.setBackground(new Color(70, 70, 80));
	sidePanel_2.setBounds(73, 184, 898, 52);
	panel5_3.add(sidePanel_2);
	
	stats = new JEditorPane();
	stats.setFont(new Font("Tahoma", Font.BOLD, 15));
	stats.setForeground(Color.WHITE);
	stats.setBackground(new Color(70, 70, 80));
	stats.setText("Staked FT : " + NF.toFTdecimals(CFMwallet.FTstaked + "") + "\n\nStaking Rewards : " + NF.toFTdecimals(CFMwallet.FTrewards + "") + "\n\nBlock Staked At : " + CFMwallet.block);
	stats.setBounds(74, 290, 896, 98);
	panel5_3.add(stats);
	System.out.println("Broooooooooooooooooooooooooooooooooooooooooooooooooooooooo " + NF.toFTdecimals(CFMwallet.FTrewards + ""));
	JEditorPane StatsPanel = new JEditorPane();
	StatsPanel.setForeground(Color.WHITE);
	StatsPanel.setText("Staking Statistics ");
	StatsPanel.setFont(new Font("Source Sans Pro", Font.BOLD, 21));
	StatsPanel.setEditable(false);
	StatsPanel.setBorder(black);
	StatsPanel.setBackground(new Color(70, 70, 80));
	StatsPanel.setBounds(73, 260, 898, 130);
	panel5_3.add(StatsPanel);
	
	staking = new JTextPane();
	staking.setBounds(73, 414, 450, 20);
	panel5_3.add(staking);
	
	unstaking = new JTextPane();
	unstaking.setBounds(73, 446, 450, 20);
	panel5_3.add(unstaking);
	
	JEditorPane Notification = new JEditorPane();
	Notification.setFont(new Font("Tahoma", Font.BOLD, 12));
	Notification.setBounds(72, 475, 918, 20);
	Notification.setForeground(Color.decode("#FF5560"));
	Notification.setBackground(Color.decode("#343c4b"));
	panel5_3.add(Notification);
	
	JLabel Stake = new JLabel("Stake");
	Stake.setHorizontalAlignment(SwingConstants.CENTER);
	Stake.setForeground(new Color(212, 226, 255));
	Stake.setFont(new Font("Tahoma", Font.BOLD, 11));
	Stake.setBounds(533, 411, 150, 23);
	Stake.addMouseListener(mouse);
	Stake.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if(stakeInUse) {return;}
			if(!isDouble(staking.getText())) {return;}
			if(!checkStaking(staking.getText())) {
			Notification.setForeground(Color.decode("#FF5560"));
			Notification.setText("Staking this amount of FT won't result in an increase in daily rewards");
			return;}
			
			stakeInUse = true;
			
			double amount = Double.parseDouble(staking.getText().replace(" ", ""));
			System.out.println(amount);
			Thread t = new Thread() {public void run() {
			System.out.println(CFMwallet.FTbalance);
			if(amount <= Double.parseDouble(CFMwallet.FTbalance + "")) {
				if(frame2.load("FT staking transaction "
						+ "\n\nFT to be staked : " + amount
						+ "\nBNB required for fees : " + new BigDecimal(CFMwallet.total(CFMwallet.BNB_GAS_PRICE, new BigInteger("76000")) + "")
						+ "\nUnused BNB in the transaction will be placed back in your account"
						+ "\n\nDo you wish to continue with the transaction ?")) {
					String hash = CFMwallet.stakeFT(new BigDecimal(amount + ""));
					if(hash == null) {
						Notification.setForeground(Color.decode("#FF5560"));
						Notification.setText("Insufficient BNB to pay the transaction fees");
					}
					else {

					Notification.setForeground(Color.GREEN);
					Notification.setText("Pending Staking Transaction : " + hash);
					}
					
				}
			}
			else {
				Notification.setForeground(Color.decode("#FF5560"));
				Notification.setText("Insufficient Funds");
				try {Thread.sleep(3000);} catch (InterruptedException e) {}
				Notification.setText("");
				}
			
			stakeInUse = false;
			}};t.start();
		}
		public void mousePressed(MouseEvent e) {
			if(stakeInUse) {Stake.setBorder(red); return;}
			if(!isDouble(staking.getText())) {staking.setBorder(red); return;}
			if(checkStaking(staking.getText())) {Stake.setBorder(green); return;}
			else {Stake.setBorder(red);}
		}
		public void mouseReleased(MouseEvent e) {
			Stake.setBorder(null);
		}
	});
	panel5_3.add(Stake);
	
	JLabel UnStake = new JLabel("UnStake");
	UnStake.setHorizontalAlignment(SwingConstants.CENTER);
	UnStake.setForeground(new Color(212, 226, 255));
	UnStake.setFont(new Font("Tahoma", Font.BOLD, 11));
	UnStake.setBounds(533, 443, 150, 23);
	UnStake.addMouseListener(mouse);
	UnStake.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if(UnStakeInUse) {return;}
			if(!isDouble(unstaking.getText())) {unstaking.setBorder(red);return;}
			
			UnStakeInUse = true;
			
			double amount = Double.parseDouble(unstaking.getText().replace(" ", ""));
			Thread t = new Thread() {public void run() {
			System.out.println(CFMwallet.FTbalance);
			if(amount <= Double.parseDouble(CFMwallet.FTstaked.add(CFMwallet.FTrewards) + "")) {
				if(frame2.load("FT unStaking transaction"
						+ "\n\nFT to be unStaked : " + amount
						+ "\nBNB required for fees : " + new BigDecimal(CFMwallet.total(CFMwallet.BNB_GAS_PRICE, new BigInteger("70000")) + "")
						+ "\nUnused BNB in the transaction will be placed back in your account"
						+ "\n\nDo you wish to continue with the transaction ?")) {
					String hash = CFMwallet.unstakeFT(amount);
					if(hash == null) {
						Notification.setForeground(Color.decode("#FF5560"));
						Notification.setText("Insufficient BNB to pay the transaction fees");
					}
					else {

					Notification.setForeground(Color.GREEN);
					Notification.setText("Pending UnStaking Transaction : " + hash);
					}
					
				}
			}
			else {
				Notification.setForeground(Color.decode("#FF5560"));
				Notification.setText("Insufficient Funds");
				try {Thread.sleep(3000);} catch (InterruptedException e) {}
				Notification.setText("");
				}
			
			UnStakeInUse = false;
			}};t.start();
		}
		public void mousePressed(MouseEvent e) {
			if(stakeInUse) {UnStake.setBorder(red);}
			if(isDouble(unstaking.getText())) {
				double amount = Double.parseDouble(unstaking.getText().replace(" ", ""));
				if(amount <= Double.parseDouble(CFMwallet.FTstaked.add(CFMwallet.FTrewards) + "")) {UnStake.setBorder(green);}
				}
			else {UnStake.setBorder(red);}
		}
		public void mouseReleased(MouseEvent e) {
			UnStake.setBorder(null);
		}
	});
	panel5_3.add(UnStake);
	
	JLabel StakeRewards = new JLabel("Stake Rewards");
	StakeRewards.setHorizontalAlignment(SwingConstants.CENTER);
	StakeRewards.setForeground(new Color(212, 226, 255));
	StakeRewards.setFont(new Font("Tahoma", Font.BOLD, 11));
	StakeRewards.setBounds(840, 411, 150, 23);
	StakeRewards.addMouseListener(mouse);
	StakeRewards.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if(stakeInUse) {return;}
			if(CFMwallet.FTrewards.compareTo(BigDecimal.ZERO) == 0) {
				Notification.setForeground(Color.decode("#FF5560"));
				Notification.setText("Can't stake 0 FT");
				return;
			}
			stakeInUse = true;
			
			Thread t = new Thread() {public void run() {
			System.out.println(CFMwallet.FTbalance);

				if(frame2.load("FT staking rewards transaction "
						+ "\n\nBNB required for fees : " + new BigDecimal(CFMwallet.total(CFMwallet.BNB_GAS_PRICE, new BigInteger("40000")) + "")
						+ "\nUnused BNB in the transaction will be placed back in your account"
						+ "\n\nDo you wish to continue with the transaction ?")) {
					String hash = CFMwallet.refreshStaking();
					if(hash == null) {
						Notification.setForeground(Color.decode("#FF5560"));
						Notification.setText("Insufficient BNB to pay the transaction fees");
					}
					else {

					Notification.setForeground(Color.GREEN);
					Notification.setText("Pending Staking Rewards Transaction : " + hash);
					}
					
				}
			
			stakeInUse = false;
			}};t.start();
		}
		public void mousePressed(MouseEvent e) {
			if(CFMwallet.FTrewards.compareTo(BigDecimal.ZERO) == 0) {StakeRewards.setBorder(red);}
			else {StakeRewards.setBorder(green);}
		}
		public void mouseReleased(MouseEvent e) {
			StakeRewards.setBorder(null);
		}
	});
	panel5_3.add(StakeRewards);
	
	JLabel UnStakeAll = new JLabel("Unstake All");
	UnStakeAll.setHorizontalAlignment(SwingConstants.CENTER);
	UnStakeAll.setForeground(new Color(212, 226, 255));
	UnStakeAll.setFont(new Font("Tahoma", Font.BOLD, 11));
	UnStakeAll.setBounds(840, 443, 150, 23);
	UnStakeAll.addMouseListener(mouse);
	UnStakeAll.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if(UnStakeInUse) {return;}
			
			UnStakeInUse = true;
			
			Thread t = new Thread() {public void run() {
			System.out.println(CFMwallet.FTbalance);
			if(CFMwallet.FTstaked.compareTo(BigDecimal.ZERO) == 1) {
				if(frame2.load("FT unStaking all transaction"
						+ "\n\nFT to be unStaked : " + (CFMwallet.FTstaked.add(CFMwallet.FTrewards))
						+ "\nBNB required for fees : " + new BigDecimal(CFMwallet.total(CFMwallet.BNB_GAS_PRICE, new BigInteger("56000")) + "")
						+ "\nUnused BNB in the transaction will be placed back in your account"
						+ "\n\nDo you wish to continue with the transaction ?")) {
					String hash = CFMwallet.unstakeAllFT();
					if(hash == null) {
						Notification.setForeground(Color.decode("#FF5560"));
						Notification.setText("Insufficient BNB to pay the transaction fees");
					}
					else {

					Notification.setForeground(Color.GREEN);
					Notification.setText("Pending UnStaking Transaction : " + hash);
					}
					
				}
			}
			else {
				Notification.setForeground(Color.decode("#FF5560"));
				Notification.setText("No FT in staking to unStake");
				try {Thread.sleep(3000);} catch (InterruptedException e) {}
				Notification.setText("");
				}
			
			UnStakeInUse = false;
			}};t.start();
		}
		public void mousePressed(MouseEvent e) {
			if(stakeInUse) {UnStakeAll.setBorder(red);}
			if(CFMwallet.FTstaked.compareTo(BigDecimal.ZERO) == 1) {UnStakeAll.setBorder(green);}
			else {UnStakeAll.setBorder(red);}
		}
		public void mouseReleased(MouseEvent e) {
			UnStakeAll.setBorder(null);
		}
	});
	panel5_3.add(UnStakeAll);
	
	JEditorPane TransferButton = new JEditorPane();
	TransferButton.setForeground(Color.LIGHT_GRAY);
	TransferButton.setFont(new Font("Tahoma", Font.BOLD, 11));
	TransferButton.setEditable(false);
	TransferButton.setBorder(black);
	TransferButton.setBackground(new Color(33, 37, 47));
	TransferButton.setBounds(533, 411, 150, 23);
	panel5_3.add(TransferButton);
	
	JEditorPane TransferButton_1 = new JEditorPane();
	TransferButton_1.setForeground(Color.LIGHT_GRAY);
	TransferButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
	TransferButton_1.setEditable(false);
	TransferButton_1.setBorder(black);
	TransferButton_1.setBackground(new Color(33, 37, 47));
	TransferButton_1.setBounds(533, 443, 150, 23);
	panel5_3.add(TransferButton_1);
	
	JEditorPane TransferButton_2 = new JEditorPane();
	TransferButton_2.setForeground(Color.LIGHT_GRAY);
	TransferButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
	TransferButton_2.setEditable(false);
	TransferButton_2.setBorder(black);
	TransferButton_2.setBackground(new Color(33, 37, 47));
	TransferButton_2.setBounds(840, 411, 150, 23);
	panel5_3.add(TransferButton_2);
	
	JEditorPane TransferButton_3 = new JEditorPane();
	TransferButton_3.setForeground(Color.LIGHT_GRAY);
	TransferButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
	TransferButton_3.setEditable(false);
	TransferButton_3.setBorder(black);
	TransferButton_3.setBackground(new Color(33, 37, 47));
	TransferButton_3.setBounds(840, 443, 150, 23);
	panel5_3.add(TransferButton_3);
	
    
}

private static void initialize5_4() {
	panel5_4 = new JPanel();
	panel5_4.setBackground(Color.decode("#343c4b"));
	panel5_4.setLayout(null);
	
	Component verticalGlue = Box.createVerticalGlue();
	verticalGlue.setBounds(118, 16, 0, 0);
	panel5_4.add(verticalGlue);
	
	ImageIcon ml6 = new ImageIcon(img + "wallet_selected.png");
	JLabel wallet = new JLabel(ml6);
	wallet.setBounds(0, 204, 45, 45);
	panel5_4.add(wallet);
	
	layout LayOut = new layout(panel5_4);
	 
	JLabel Coppied = new JLabel("Coppied !");
	Coppied.setHorizontalAlignment(SwingConstants.CENTER);
	Coppied.setForeground(new Color(212, 226, 255));
	Coppied.setFont(new Font("Tahoma", Font.BOLD, 11));
	Coppied.setBounds(840, 81, 150, 23);
	
	JLabel Copy = new JLabel("Copy");
	Copy.setHorizontalAlignment(SwingConstants.CENTER);
	Copy.setForeground(new Color(212, 226, 255));
	Copy.setFont(new Font("Tahoma", Font.BOLD, 11));
	Copy.setBounds(840, 81, 150, 23);
	Copy.addMouseListener(mouse);
	Copy.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			Thread t = new Thread() {public void run() {
			Coppied.setBounds(840, 58, 150, 23);
			String myString = CFMwallet.address;
			StringSelection stringSelection = new StringSelection(myString);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			try {Thread.sleep(3000);} catch (InterruptedException e) {}
			Coppied.setBounds(840, 81, 150, 23);
			}};t.start();
			
		}
		public void mousePressed(MouseEvent e) {
			Copy.setBorder(green);
		}
		public void mouseReleased(MouseEvent e) {
			Copy.setBorder(null);
		}
	});
	
	JEditorPane CopyButton = new JEditorPane();
	CopyButton.setForeground(Color.LIGHT_GRAY);
	CopyButton.setFont(new Font("Tahoma", Font.BOLD, 11));
	CopyButton.setEditable(false);
	CopyButton.setBorder(black);
	CopyButton.setBackground(new Color(33, 37, 47));
	CopyButton.setBounds(840, 81, 150, 23);
	panel5_4.add(Copy);
	panel5_4.add(CopyButton);
	panel5_4.add(Coppied);

	
	JLabel lblNewLabel_9 = new JLabel("The following address can be used to deposit FT, BNB, and ETH to your wallet ");
	lblNewLabel_9.setForeground(Color.WHITE);
	lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblNewLabel_9.setBounds(55, 25, 935, 45);
	panel5_4.add(lblNewLabel_9);
	
	JLabel Address = new JLabel(CFMwallet.address);
	Address.setFont(new Font("Tahoma", Font.BOLD, 20));
	Address.setForeground(Color.WHITE);
	Address.setBounds(55, 70, 755, 45);
	Address.setVerticalAlignment(0);
	panel5_4.add(Address);
	
	JEditorPane midPanel = new JEditorPane();
	midPanel.setEnabled(false);
	midPanel.setEditable(false);
	midPanel.setFont(new Font("Tahoma", Font.BOLD, 25));
	midPanel.setBackground(Color.decode("#202035"));
	midPanel.setBounds(45, 25, 955, 90);
	midPanel.setBorder(black);
	panel5_4.add(midPanel);
	
	JLabel lblNewLabel_8 = new JLabel("Wallet");
	lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblNewLabel_8.setForeground(Color.WHITE);
	lblNewLabel_8.setBounds(45, 115, 478, 30);
	lblNewLabel_8.setVerticalAlignment(0);
	lblNewLabel_8.setHorizontalAlignment(0);
	panel5_4.add(lblNewLabel_8);
	
	
	JLabel Staking = new JLabel("Staking");
	Staking.setVerticalAlignment(SwingConstants.CENTER);
	Staking.setHorizontalAlignment(SwingConstants.CENTER);
	Staking.setForeground(Color.WHITE);
	Staking.setFont(new Font("Tahoma", Font.BOLD, 13));
	Staking.setBounds(523, 115, 477, 30);
	Staking.addMouseListener(mouse);
	Staking.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			try {initialize5_3();} catch (Exception e1) {}
			loadPanel(panel5_3);
		}
	});
	panel5_4.add(Staking);
	
	JEditorPane stakingBG = new JEditorPane();
	stakingBG.setFont(new Font("Tahoma", Font.BOLD, 25));
	stakingBG.setEnabled(false);
	stakingBG.setEditable(false);
	stakingBG.setBorder(black);
	stakingBG.setBackground(new Color(32, 32, 53));
	stakingBG.setBounds(523, 115, 477, 30);
	panel5_4.add(stakingBG);
	
	JLabel lblNewLabel_6 = new JLabel("Crypto");
	lblNewLabel_6.setForeground(Color.WHITE);
	lblNewLabel_6.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	lblNewLabel_6.setBounds(65, 196, 369, 25);
	panel5_4.add(lblNewLabel_6);
	
	JLabel Addresstxt = new JLabel("Recipient's Address");
	Addresstxt.setForeground(Color.WHITE);
	Addresstxt.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	Addresstxt.setBounds(65, 252, 369, 25);
	panel5_4.add(Addresstxt);

	JLabel Amounttxt = new JLabel("Amount");
	Amounttxt.setForeground(Color.WHITE);
	Amounttxt.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	Amounttxt.setBounds(65, 308, 369, 25);
	panel5_4.add(Amounttxt);
	
	frame1.Address = new JTextField();
	frame1.Address.setColumns(10);
	frame1.Address.setBounds(65, 277, 435, 20);
	panel5_4.add(frame1.Address);
	
	Amount = new JTextField();
	Amount.setColumns(10);
	Amount.setBounds(65, 333, 435, 20);
	panel5_4.add(Amount);
	
	
	TransactionFee = new JLabel("Transaction Fee : ");
	TransactionFee.setForeground(Color.WHITE);
	TransactionFee.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
	TransactionFee.setBounds(65, 364, 925, 25);
	panel5_4.add(TransactionFee);
	
	transferCrypto = new JComboBox();
	transferCrypto.setFont(new Font("Tahoma", Font.BOLD, 11));
	transferCrypto.setBounds(65, 221, 100, 20);
	transferCrypto.addItem("FT");
	transferCrypto.addItem("BNB");
	transferCrypto.addItem("ETH");
	panel5_4.add(transferCrypto);
	

	
	JLabel lblNewLabel_1_1 = new JLabel("Address to send the crypto to ");
	lblNewLabel_1_1.setForeground(Color.WHITE);
	lblNewLabel_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
	lblNewLabel_1_1.setBounds(510, 277, 480, 20);
	panel5_4.add(lblNewLabel_1_1);
	
	
	result = new JEditorPane();
	result.setForeground(Color.WHITE);
	result.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 14));
	result.setEditable(false);
	result.setBackground(new Color(52, 60, 75));
	result.setBounds(65, 403, 925, 45);
	panel5_4.add(result);
	
	JLabel Transfer = new JLabel("Transfer");
	Transfer.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(checkInput()) {
				if(frame1.Address.getText().replace(" ", "") != CFMwallet.address) {
				String output = null;
				try {output = CFMwallet.makeTransaction(transferCrypto.getSelectedItem().toString(), frame1.Address.getText().replace(" ", ""), new BigDecimal(Amount.getText().replace(" ", "")));
				} catch (Exception e1) {}
				if(output == null) {
					result.setForeground(Color.decode("#FF5555"));
					result.setText("Insufficient crypto to pay transaction fees");
				}
				else {
					result.setForeground(Color.green);
					result.setText("Processing Transfer, Txn hash : " + output);
				}
				}
				else {
					result.setForeground(Color.decode("#FF5555"));
					result.setText("Why would you wanna send FT to yourself ?!");
				}
			}
		}
		public void mousePressed(MouseEvent e) {
			if(checkInput()) {
				if(frame1.Address.getText().replace(" ", "") != CFMwallet.address) {
					Transfer.setBorder(green);
					}
				else {Transfer.setBorder(red);}
				}
			else {Transfer.setBorder(red);}
		}
		public void mouseReleased(MouseEvent e) {
			Transfer.setBorder(null);
		}
	});
	Transfer.setHorizontalAlignment(SwingConstants.CENTER);
	Transfer.setForeground(new Color(212, 226, 255));
	Transfer.setFont(new Font("Tahoma", Font.BOLD, 11));
	Transfer.setBounds(840, 466, 150, 23);
	panel5_4.add(Transfer);
	
	JLabel Back = new JLabel("Back");
	Back.addMouseListener(mouse);
	Back.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			loadPanel(panel5_2);
		}
		public void mousePressed(MouseEvent e) {
			Back.setBorder(green);
		}
		public void mouseReleased(MouseEvent e) {
			Back.setBorder(null);
		}
	});
	Back.setHorizontalAlignment(SwingConstants.CENTER);
	Back.setForeground(new Color(212, 226, 255));
	Back.setFont(new Font("Tahoma", Font.BOLD, 11));
	Back.setBounds(55, 466, 150, 23);
	panel5_4.add(Back);
	
	JEditorPane _transfer = new JEditorPane();
	_transfer.setForeground(new Color(212, 226, 255));
	_transfer.setFont(new Font("Tahoma", Font.BOLD, 11));
	_transfer.setEnabled(false);
	_transfer.setEditable(false);
	_transfer.setBorder(black);
	_transfer.setBackground(new Color(33, 37, 47));
	_transfer.setBounds(840, 466, 150, 23);
	panel5_4.add(_transfer);
	
	JEditorPane _back = new JEditorPane();
	_back.setForeground(new Color(212, 226, 255));
	_back.setFont(new Font("Tahoma", Font.BOLD, 11));
	_back.setEnabled(false);
	_back.setEditable(false);
	_back.setBorder(black);
	_back.setBackground(new Color(33, 37, 47));
	_back.setBounds(55, 466, 150, 23);
	panel5_4.add(_back);
	
	JLabel lblNewLabel_6_2 = new JLabel("Crypto Transfer");
	lblNewLabel_6_2.setForeground(Color.WHITE);
	lblNewLabel_6_2.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
	lblNewLabel_6_2.setBounds(55, 160, 369, 25);
	panel5_4.add(lblNewLabel_6_2);

	JLabel lblNewLabel_1_1_1 = new JLabel("Amount of crypto to send to the address");
	lblNewLabel_1_1_1.setForeground(Color.WHITE);
	lblNewLabel_1_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
	lblNewLabel_1_1_1.setBounds(510, 333, 480, 20);
	panel5_4.add(lblNewLabel_1_1_1);

    	

}

private static void initialize6() throws Exception  {
	
	panel6 = new JPanel();
	panel6.setBackground(Color.decode("#343c4b"));
	panel6.setLayout(null);
	
	Component verticalGlue = Box.createVerticalGlue();
	verticalGlue.setBounds(118, 16, 0, 0);
	panel6.add(verticalGlue);
	
	layout LayOut = new layout(panel6);

}

private static void initialize7() throws Exception  {
	
	File BNBgas = new File("C:\\CF\\CFMwallet\\BNBgasPrice.dat");
	File ETHgas = new File("C:\\CF\\CFMwallet\\ETHgasPrice.dat");
	
	file.newFile(BNBgas, "20");
	file.newFile(ETHgas, "50");
	
	panel7 = new JPanel();
	panel7.setBackground(Color.decode("#343c4b"));
	panel7.setLayout(null);
	
	Component verticalGlue = Box.createVerticalGlue();
	verticalGlue.setBounds(118, 16, 0, 0);
	panel7.add(verticalGlue);
	
	layout LayOut = new layout(panel7);

	JLabel BNBgasstat = new JLabel("");
	BNBgasstat.setHorizontalAlignment(SwingConstants.CENTER);
	BNBgasstat.setForeground(new Color(212, 226, 255));
	BNBgasstat.setFont(new Font("Tahoma", Font.BOLD, 11));
	BNBgasstat.setBounds(178, 116, 485, 23);
	panel7.add(BNBgasstat);
	
	JLabel ETHgasstat = new JLabel("");
	ETHgasstat.setHorizontalAlignment(SwingConstants.CENTER);
	ETHgasstat.setForeground(new Color(212, 226, 255));
	ETHgasstat.setFont(new Font("Tahoma", Font.BOLD, 11));
	ETHgasstat.setBounds(178, 276, 485, 23);
	panel7.add(ETHgasstat);
	
	JLabel lblNewLabel = new JLabel("Set BNB gas Price");
	lblNewLabel.setBounds(673, 147, 150, 23);
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblNewLabel.setForeground(Color.decode("#d4e2ff"));
	lblNewLabel.addMouseListener(mouse);
	lblNewLabel.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			int gwei = 0;
			try { gwei = Integer.parseInt(textField_4.getText().replace(" ", "")); }catch(NumberFormatException e1) {
				BNBgasstat.setForeground(Color.decode("#FF5555"));
				BNBgasstat.setText("Invalid value");
			}
			if(gwei > 0) {
				file.write(BNBgas, gwei + "");
				BNBgasstat.setForeground(Color.green);
				BNBgasstat.setText("Successfully  set new BNB gas price to " + gwei + " gwei");
				CFMwallet.BNB_GAS_PRICE = new BigInteger(gwei + "").multiply(new BigInteger("1000000000"));
			}
			else {
				BNBgasstat.setForeground(Color.decode("#FF5555"));
				BNBgasstat.setText("Gas price can't be lower than 1 gwei");}
		}
		
		public void mousePressed(MouseEvent e) {
			
			int gwei = 0;
			try { gwei = Integer.parseInt(textField_4.getText().replace(" ", "")); }catch(NumberFormatException e1) {lblNewLabel.setBorder(red);}
			if(gwei > 0) {lblNewLabel.setBorder(green);}
			else {lblNewLabel.setBorder(red);}
			CFMwallet.BNB_GAS_PRICE = new BigInteger(gwei + "");
			
		}
		
		public void mouseReleased(MouseEvent e) {
			lblNewLabel.setBorder(null);
		}
		
	});
	lblNewLabel.setHorizontalAlignment(0);
	
	JLabel lblSetEthGas = new JLabel("Set ETH gas Price");
	lblSetEthGas.setHorizontalAlignment(SwingConstants.CENTER);
	lblSetEthGas.setForeground(new Color(212, 226, 255));
	lblSetEthGas.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblSetEthGas.setBounds(673, 307, 150, 23);
	lblSetEthGas.addMouseListener(mouse);
	lblSetEthGas.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			int gwei = 0;
			try { gwei = Integer.parseInt(textField_5.getText().replace(" ", "")); }catch(NumberFormatException e1) {
				ETHgasstat.setForeground(Color.decode("#FF5555"));
				ETHgasstat.setText("Invalid value");
			}
			if(gwei > 0) {
				file.write(ETHgas, gwei + "");
				ETHgasstat.setForeground(Color.green);
				ETHgasstat.setText("Successfully  set new ETH gas price to " + gwei + " gwei");
				CFMwallet.ETH_GAS_PRICE = new BigInteger(gwei + "").multiply(new BigInteger("1000000000"));
			}
			else {
				ETHgasstat.setForeground(Color.decode("#FF5555"));
				ETHgasstat.setText("Gas price can't be lower than 1 gwei");}
		}
		
		public void mousePressed(MouseEvent e) {
			
			int gwei = 0;
			try { gwei = Integer.parseInt(textField_5.getText().replace(" ", "")); }catch(NumberFormatException e1) {lblSetEthGas.setBorder(red);}
			if(gwei > 0) {lblSetEthGas.setBorder(green);}
			else {lblSetEthGas.setBorder(red);}
			
		}
		
		public void mouseReleased(MouseEvent e) {
			lblSetEthGas.setBorder(null);
		}
	});
	panel7.add(lblSetEthGas);
	
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	
	JEditorPane button1 = new JEditorPane();
	button1.setBounds(673, 147, 150, 23);
	button1.setFont(new Font("Tahoma", Font.BOLD, 11));
	button1.setForeground(Color.LIGHT_GRAY);
	button1.setEditable(false);
	button1.setBackground(Color.decode("#21252f"));
	button1.setBorder(border);
	panel7.add(lblNewLabel);
	panel7.add(button1);
	
	textField_4 = new JTextField(file.readAll(BNBgas));
	textField_4.setBounds(178, 150, 485, 20);
	panel7.add(textField_4);
	textField_4.setColumns(10);
	
	textField_5 = new JTextField(file.readAll(ETHgas));
	textField_5.setColumns(10);
	textField_5.setBounds(178, 310, 485, 20);
	panel7.add(textField_5);
	
	JEditorPane button1_1 = new JEditorPane();
	button1_1.setForeground(Color.LIGHT_GRAY);
	button1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
	button1_1.setEditable(false);
	button1_1.setBorder(border);
	button1_1.setBackground(new Color(33, 37, 47));
	button1_1.setBounds(673, 307, 150, 23);
	panel7.add(button1_1);
	
	JLabel lblgwei = new JLabel("(Gwei)");
	lblgwei.setHorizontalAlignment(SwingConstants.CENTER);
	lblgwei.setForeground(new Color(212, 226, 255));
	lblgwei.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblgwei.setBounds(833, 147, 78, 23);
	panel7.add(lblgwei);
	
	JLabel lblgwei_1 = new JLabel("(Gwei)");
	lblgwei_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblgwei_1.setForeground(new Color(212, 226, 255));
	lblgwei_1.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblgwei_1.setBounds(833, 307, 78, 23);
	panel7.add(lblgwei_1);
	
	JLabel lblSetGasroce = new JLabel("Set gas price of BNB and FT transactions");
	lblSetGasroce.setHorizontalAlignment(SwingConstants.CENTER);
	lblSetGasroce.setForeground(new Color(212, 226, 255));
	lblSetGasroce.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblSetGasroce.setBounds(178, 181, 485, 23);
	panel7.add(lblSetGasroce);
	
	JLabel lblSetGasPrice = new JLabel("Set gas price of ETH transactions");
	lblSetGasPrice.setHorizontalAlignment(SwingConstants.CENTER);
	lblSetGasPrice.setForeground(new Color(212, 226, 255));
	lblSetGasPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblSetGasPrice.setBounds(178, 341, 485, 23);
	panel7.add(lblSetGasPrice);

}
}