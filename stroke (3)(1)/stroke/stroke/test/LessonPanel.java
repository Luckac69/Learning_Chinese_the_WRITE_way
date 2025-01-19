package stroke.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LessonPanel extends JComponent {
	String background = "images\\bg_town.jpg";
	String teachImage = "images\\xu_wei.png";
	//String teachImage = "ctwwimage\\MTeacher.jpg";
	String canvasImage = "images\\Chalkboard.png";
	String charImage = "images\\char10.png";
	Image bkImg, thImg, cvImg, ziImg; 
	int selectedCharIndex = 0;
	int selectedLesson = 7;
	
	Rectangle2D.Double charDisplay;
	Rectangle2D.Double lessonDisplay = new Rectangle2D.Double(10, 10, 68, 900);

	Font customFont = null;
	Font chalkFont = null;

	public static void main(String[] args) {
		JFrame f = new JFrame("Chinese the WRITE Way @ 汉字一二三");

		JComponent contentPane = new LessonPanel();
		f.setContentPane(contentPane);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}

	public LessonPanel() {
		bkImg = new ImageIcon(background).getImage();
		thImg = new ImageIcon(teachImage).getImage();
		cvImg = new ImageIcon(canvasImage).getImage();
		ziImg = new ImageIcon(charImage).getImage();
		this.setMaximumSize(new Dimension(1900, 950));
		this.setMinimumSize(new Dimension(1900, 950));
		this.setPreferredSize(new Dimension(1900, 950));
		this.addMouseListener(new CharChoosingListener());
		this.addMouseListener(new LessonChoosingListener());
		try {
		    //create the font to use. Specify the size!
		    customFont = Font.createFont(Font.TRUETYPE_FONT, 
		    		new File("images/TianShiBaoDiaoTiJian-1.ttf")).deriveFont(12f);
		    chalkFont = Font.createFont(Font.TRUETYPE_FONT, 
		    		new File("images/SanJiQiChuanKaiShu-2.ttf")).deriveFont(240f);
		    //chalkFont = Font.createFont(Font.TRUETYPE_FONT, 
		    //		new File("images/YanZhenQingFaShu-2.ttf")).deriveFont(240f);
		    //customFont = Font.createFont(Font.TRUETYPE_FONT, 
		    //new File("ctwwimage/TianShiSongTiJiuZiXing-1.ttf")).deriveFont(12f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}

		//use the font
		this.setFont(customFont);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bkImg, 0, 0, null);
		g.drawImage(thImg, 1220, 120, null);
		g.drawImage(cvImg, 100, 80, null);
		//g.drawImage(ziImg, 500, 300, null);
		drawZi(g, "鼻");
		paintLessons((Graphics2D)g);
		
		g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 50));
		//g.setFont(new Font(Noto Sans SC", Font.PLAIN, 50));
		
		/*
		//Lesson 2 w/ 1"
		g.setColor(Color.WHITE);
		g.fillRect(350, 810, 520, 65);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("一二三", 360, 860);
		g.setColor(Color.BLACK);
		g.drawString("四五六七八九十", 360 + 150, 860);
		paintLesson((Graphics2D)g, 1);
		drawPinyin(g, "shí");
		drawMeaning(g, "ten");
		markCharacter((Graphics2D)g, 0.25f); // change manually
		*/
		
		/*
		//Lesson 5
		g.setColor(Color.WHITE);
		g.fillRect(350, 810, 260, 65);
		g.setColor(Color.BLACK);
		g.drawString("人大天地也", 355, 860);
		paintLesson((Graphics2D)g, 5);

		markCharacter((Graphics2D)g, 0.25f); // change manually

		drawPinyin(g, "tiān");
		drawMeaning(g, "sky");
		*/
		
		// Lesson 7
		g.setColor(Color.WHITE);
		//g.fillRect(350, 810, 310, 65);
		charDisplay = new Rectangle2D.Double(350, 810, 310, 65);
		((Graphics2D)g).fill(charDisplay);
		g.setColor(Color.BLACK);
		g.drawString("口舌目耳鼻自", 355, 860);
		paintLesson((Graphics2D)g, selectedLesson);

		//selectedCharIndex = 4;
		markCharacter((Graphics2D)g, 0.25f); // change manually

		drawPinyin(g, "bí");
		drawMeaning(g, "nose");
		//drawTeacher(g, 0.85);
	}
	
	private void drawZi(Graphics g, String chnChar) {
		//int fontSize = g.getFont().getSize();
		g.setFont(chalkFont);
		g.setColor(Color.WHITE);
		g.drawString(chnChar, 480, 480);
		g.setFont(customFont);
	}

	private void paintLesson(Graphics2D g, int i) {
		String l = "Lesson " + i;
		if (i == 1)
			l += " - Numbers";
		else if (i==5)
			l += " - 人大天";
		else if (i==7)
			l += " - On Your Face";
		g.drawString(l, 360, 55);
		
	}

	private void paintLessons(Graphics2D g) {
		int lessonCount = 25;
		//int selected = 7;
		int lessonId;
		RoundRectangle2D.Double lessonMarker; 
		Color bkgd = Color.LIGHT_GRAY;
		Color frgd = Color.BLACK;
		//Font f = new Font("LucidaSans", Font.BOLD, 14);
		g.setFont(new Font(g.getFont().getFamily(), Font.BOLD, 
				g.getFont().getSize()));
		
		for (int i=0; i<lessonCount; i++) {
			lessonId = i+1;
			lessonMarker = new RoundRectangle2D.Double(10, 35*i + 10, 
					68, 20, 5, 5);
			if (lessonId==selectedLesson) {
				g.setColor(Color.YELLOW);
			}
			else
				g.setColor(bkgd);
			g.fill(lessonMarker);
			g.setColor(frgd);
			g.draw(lessonMarker);
			g.drawString("Lesson " + lessonId, 12, 35*i + 25);
		}
	}

	private void markCharacter(Graphics2D g2d, float alpha) {
		  //g2d.setPaint(Color.blue);
		double xRef = charDisplay.getMinX();
		  RoundRectangle2D.Double redSquare = 
				  new RoundRectangle2D.Double(xRef + 5 + selectedCharIndex * 50, 
						  817, 50, 50, 15, 15);
		  //g2d.fill(blueSquare);
		  Color color = new Color(1, 0, 0, alpha); //Red 
		  g2d.setPaint(color);
		  g2d.fill(redSquare);
		}

	private void drawTeacher(Graphics g, double d) {
		int newH = (int)(thImg.getHeight(null) * d);
		int newW = (int)(thImg.getWidth(null) * d);
		g.drawImage(thImg, 1220, 200, 1200+newW, 200+newH, 
				0, 0, thImg.getWidth(null), thImg.getHeight(null), 
				null, null);
	}

	private void drawPinyin(Graphics g, String pinyin) {
		//g.setColor(Color.WHITE);
		//g.fillRect(550, 200, 80, 60);
		g.setColor(Color.RED);
		g.drawString(pinyin, 560, 250);
	}

	private void drawMeaning(Graphics g, String chnChar) {
		//g.setColor(Color.WHITE);
		//g.fillRect(550, 550, 120, 60);
		//g.setColor(Color.LIGHT_GRAY);
		g.setColor(new Color(100, 188, 242));
		g.drawString(chnChar, 560, 590);
	}

	class LessonChoosingListener extends MouseAdapter {
        public void mousePressed(MouseEvent mouseEvent) {
        	Point mousePoint = mouseEvent.getPoint();
        	if (lessonDisplay.contains(mousePoint)) {
        		selectedLesson = 1 + ((int)(mousePoint.y - 
        				lessonDisplay.getMinY()))/35;
        		System.out.println(selectedLesson + "@" + mousePoint);
        	}
        	repaint();
        }
	}

	class CharChoosingListener extends MouseAdapter {
        public void mousePressed(MouseEvent mouseEvent) {
        	Point mousePoint = mouseEvent.getPoint();
        	if (charDisplay.contains(mousePoint)) {
        		selectedCharIndex = ((int)(mousePoint.x - 5 - 
        				charDisplay.getMinX()))/50;
        		System.out.println(selectedCharIndex + "@" + mousePoint);
        	}
        	repaint();
        }
	}
}
