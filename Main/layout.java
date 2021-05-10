package Main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.frame1.FrameDragListener;

public class layout {
	
	public static final String img = "C:\\CF\\CFM\\img\\";

	private static ImageIcon home_icon = new ImageIcon(img + "home.png");
	private static ImageIcon mine_icon = new ImageIcon(img + "mine.png");
	private static ImageIcon about_icon = new ImageIcon(img + "about.png");
	private static ImageIcon how_icon = new ImageIcon(img + "how.png");
	private static ImageIcon wallet_icon = new ImageIcon(img + "wallet.png");
	private static ImageIcon exit_icon = new ImageIcon(img + "exit.png");
	private static ImageIcon min_icon = new ImageIcon(img + "min.png");
	private static ImageIcon settings_icon = new ImageIcon(img + "settings.png");	
	
	public layout(JPanel panel) {
		
	JLabel version = new JLabel(dev.version);
	version.setFont(new Font("Tahoma", Font.BOLD, 11));
	version.setForeground(Color.WHITE);
	version.setBounds(10, 420, 45, 14);
	panel.add(version);
	
	JLabel settings = new JLabel(settings_icon);
	settings.addMouseListener(frame1.mouse);
	settings.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			frame1.loadPanel(frame1.panel7);
		}
	});
	settings.setBounds(0, 365, 45, 45);
	panel.add(settings);
	
	JLabel home = new JLabel(home_icon);
	home.addMouseListener(frame1.mouse);
	home.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			frame1.loadPanel(frame1.panel);
		}
	});
	home.setBounds(0, 25, 45, 45);
	panel.add(home);
	
	JLabel mine = new JLabel(mine_icon);
	mine.addMouseListener(frame1.mouse);
	mine.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			frame1.loadPanel(frame1.panel1);
		}
	});
	mine.setBounds(0, 70, 45, 45);
	panel.add(mine);
	
	JLabel about = new JLabel(about_icon);
	about.addMouseListener(frame1.mouse);
	about.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			frame1.loadPanel(frame1.panel2);
		}
	});
	about.setBounds(0, 115, 45, 45);
	panel.add(about);

	JLabel how = new JLabel(how_icon);
	how.addMouseListener(frame1.mouse);
	how.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			frame1.loadPanel(frame1.panel3);
		}
	});
	how.setBounds(0, 160, 45, 45);
	panel.add(how);
	
	JLabel exit = new JLabel(exit_icon);
	exit.addMouseListener(frame1.mouse);
	exit.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			mining.crypto.stopGPU(); mining.crypto.stop("xmr");
			System.exit(0);
		}
		
		public void mousePressed(MouseEvent e) {
			exit.setBorder(frame1.green);
		}
	});
	exit.setBounds(0, 455, 45, 45);
	panel.add(exit);
	
	JLabel wallet = new JLabel(wallet_icon);
	wallet.setBounds(0, 204, 45, 45);
	wallet.addMouseListener(frame1.mouse);
	wallet.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			frame1.loadPanel(frame1.panel5);
		}
	});
	panel.add(wallet);

    JLabel min = new JLabel(min_icon);
    min.addMouseListener(frame1.mouse);
    min.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		frame1.frame.setState(frame1.frame.ICONIFIED);
    	}
    });
    min.setBounds(965, 0, 25, 25);
    panel.add(min);
    
    JEditorPane sidePanel = new JEditorPane();
	sidePanel.setBounds(0, 25, 45, 475);
	sidePanel.setBackground(Color.decode("#21252f"));
	sidePanel.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
			frame1.frame.setCursor(cursor);
		}
	});
	panel.add(sidePanel);
	
	JEditorPane topPane = new JEditorPane();
	topPane.setText("Crypto Family Miner");
	topPane.setEditable(false);
	FrameDragListener frameDragListener = new FrameDragListener();
	topPane.addMouseListener(frameDragListener);
	topPane.addMouseMotionListener(frameDragListener);
	topPane.setFont(new Font("Tahoma", Font.BOLD, 11));
	topPane.setForeground(Color.decode("#d8e4ff"));
	topPane.setBounds(0, 0, 1000, 25);
	topPane.setBackground(Color.decode("#060e20"));
	topPane.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
			frame1.frame.setCursor(cursor);
		}
	});
	panel.add(topPane);
	
	}
}