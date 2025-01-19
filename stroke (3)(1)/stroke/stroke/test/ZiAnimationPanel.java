package stroke.test;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import stroke.image.ImageUtil;

public class ZiAnimationPanel extends JPanel {
	BufferedImage ziImage;
	int top, left, width, height, gap;
	
	public ZiAnimationPanel(BufferedImage ziImage) {
		this.ziImage = ziImage;
		top = 0;
		left = 8;
		width = 54;
		height = 54;
		gap = 13;
	}
	
	public void paintComponent(Graphics g) {
		
		g.drawImage(ziImage, 0, 0, null);
		
		/*
		for (int i=0; i<=10; i++) {
			g.drawLine(left, top + i*(height + gap) - gap, left + width, top + i* (height + gap) - gap);
			g.drawLine(left, top + i*(height + gap) - height/2 - gap, left + width, top + i* (height + gap) - height/2 - gap);
			g.drawLine(left, top + i*(height + gap), left + width, top + i* (height + gap));
		}
		
		g.drawLine(left, top, left, top + 10*(height + gap));
		g.drawLine(left + width/2, top, left + width/2, top + 10*(height + gap));
		g.drawLine(left + width, top, left + width, top + 10*(height + gap));
		*/
		
	}

	private void markStroke(int xStart, int xFront) {
		int blankValue = -1, redValue = -123456;
		for (int row=110; row<166; row++) {
			for (int col=xStart; col<xFront; col++) {
				//for (int col=0; col<ziImage.getWidth(); col++) {
				//if (col == xFront)
				//	continue;
			
				if (ziImage.getRGB(col, row) != blankValue) {
					ziImage.setRGB(col, row, redValue);
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("CharAnimate");
		//ImageIcon zi = new ImageIcon("image/dong_bronze.png");
		ImageIcon zi = new ImageIcon("image/three270.png");
		//ZiAnimationPanel p = new ZiAnimationPanel(ImageUtil.toBufferedImage(zi.getImage()));
		
		BufferedImage testImage = ImageUtil.imgFromFontset('三');
		JOptionPane.showConfirmDialog(null, "" + '三', 
				"Character from Font", 0, 0, new ImageIcon(testImage));
		ZiAnimationPanel p = new ZiAnimationPanel(testImage);
		f.add(p);
		f.setSize(500, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		ActionListener listener = new ActionListener() {
			int xStart = 87, xEnd = 200; 
			int xFront = xStart;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (xFront < xEnd) {
					//System.out.println(xFront);
					//mark middle HENG in SAN
					p.markStroke(xStart, xFront++);
				}
				f.repaint();
			}
		};
		
		final int DELAY = 30;
		// Milliseconds between timer ticks
		Timer t = new Timer(DELAY, listener);
			
		JButton b = new JButton("Trace it");
		f.add(b, BorderLayout.EAST);
		b.addActionListener(new ActionListener() {
			boolean idle = true;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (idle) {
					t.start();				
					b.setText("Stop it");
					idle = false;
				}
				else {
					t.stop();				
					b.setText("Trace it");
					idle = true;
				}
			}
			
		});
		
		f.setVisible(true);
	}
}
