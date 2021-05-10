package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import javax.swing.JLabel;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.SwingConstants;

public class frame3 {

	static int result = 0;
	static Border red = BorderFactory.createLineBorder(Color.RED);
	static Border green = BorderFactory.createLineBorder(Color.GREEN);
	static Border yellow = BorderFactory.createLineBorder(Color.YELLOW);
	static Border black = BorderFactory.createLineBorder(Color.BLACK);
	
	private static JPanel panel, panel1;
	public static final String img = "C:\\CF\\CFM\\img\\";
	public static JFrame frame;	public static Mouse mouse = new Mouse();
	public static FrameDragListener frameDragListener = new FrameDragListener();
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
	
	public static void main(String[] args) {
		load("FT Staking transaction \n\nBNB required for fees : 0.001 \nUnused BNB in the transaction will be placed back in your balance "
				+ "\n\nAdding FT to your staking will result int the reset of your staking timer, are you sure you want to add FT to your staking now ?");
	}
	public static void load(String content)  {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ImageIcon on = new ImageIcon(img + "mining_logo_test.png");
						Image logo = on.getImage();

						
					    
						frame = new JFrame();
						frame.setUndecorated(true);
						frame.setResizable(false);
						frame.setBounds(frame1.frame.getX() + 250, frame1.frame.getY() + 175, 500, 150);
						frame.setVisible(true);
						frame.setTitle("Crypto Family Miner");
						frame.setIconImage(logo);
						frame.setDefaultCloseOperation(0);
						frame.addMouseListener(frameDragListener);
						frame.addMouseMotionListener(frameDragListener);
						
						initialize(content);
					    frame.setCursor(Cursor.DEFAULT_CURSOR);
						frame.getContentPane().removeAll();
						frame.getContentPane().add(panel, BorderLayout.CENTER);
						
						System.out.println("lol");
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});

			
		}
	
	public static void initialize(String content) {
		panel = new JPanel();
		panel.setBackground(Color.decode("#343c4b"));
		panel.setBorder(black);
		panel.setLayout(null);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(118, 16, 0, 0);
		panel.add(verticalGlue);
		
		JLabel ok = new JLabel("ok");
		ok.setFont(new Font("Tahoma", Font.BOLD, 11));
		ok.setForeground(Color.WHITE);
		ok.setBounds(175, 125, 150, 23);
		ok.setVerticalAlignment(0);
		ok.setHorizontalAlignment(0);
		ok.addMouseListener(mouse);
		ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				//frame.disable();
			}

		});
		panel.add(ok);
		
		JEditorPane okButton = new JEditorPane();
		okButton.setForeground(Color.LIGHT_GRAY);
		okButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		okButton.setEditable(false);
		okButton.setBorder(black);
		okButton.setBackground(new Color(33, 37, 47));
		okButton.setBounds(175, 125, 150, 23);

		panel.add(okButton);
		
		
		JEditorPane Text = new JEditorPane();
		Text.setEditable(false);
		Text.setFont(new Font("Tahoma", Font.BOLD, 12));
		Text.setText(content);
		Text.setForeground(Color.WHITE);
		Text.setBounds(1, 1, 498, 111);
		Text.addMouseListener(frameDragListener);
		Text.addMouseMotionListener(frameDragListener);
		Text.setBackground(Color.decode("#343c4b"));
		panel.add(Text);
		
	}
}
