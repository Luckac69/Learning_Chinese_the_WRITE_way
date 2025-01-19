package stroke.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import stroke.ChnCharStrokeSequence;
import stroke.DianStroke;
import stroke.model.EndPoint;
import stroke.model.EssentialStroke;
import stroke.model.IStroke;
import stroke.model.StrokeEnum;

import stroke.HengStroke;
import stroke.NaStroke;
import stroke.PieStroke;
import stroke.ShuStroke;
import stroke.image.ImageUtil;
import stroke.image.StrokeInPlace;

public class MizigeLabel extends JLabel {
	private ChnCharStrokeSequence charShape;
	
	// default gridbox size 
	private int size = 320;
	private int pad = 10;
	private int gridboxSize = 300;

	// pre-loaded strokes for improvising
	private Image[] strokeImages;
	private boolean displayZiInBox = false;
	private List<StrokeInPlace> sips;
	
	// zi image produced using a font set
	private BufferedImage fontImage;
	private boolean displayFontZiInBox = false;	
	
	public MizigeLabel(ChnCharStrokeSequence charShape) {
		this.charShape = charShape;
		
		this.setPreferredSize(new Dimension(size, size));
		loadStrokesAsImages();
		
		sips = new ArrayList<>();
	}
	
	public void paintComponent(Graphics g) {
		if (displayFontZiInBox) {
			System.out.println("YYY");
			displayFontZiInBox(g);
		}
		
		drawGridBox(g);
		
		if (displayZiInBox) {
			System.out.println("XXX");
			displayZiInBox(g);
		}
		
		drawStrokesInBox(g);
	}

	private void drawStrokesInBox(Graphics g) {
		List<IStroke> strokesInZi = charShape.getStrokes();
		
		EssentialStroke/*HengStroke*/ heng;	// try with all essential stroke types
		int x1, y1, x2, y2;
		/*Line2D.Double*/ Shape hLine = null;
		//QuadCurve2D.Double qCurve; // for essential strokes except HENG & SHU
		Color tempC = g.getColor();
		for (IStroke s : strokesInZi) {
			if (s instanceof EssentialStroke) 
			{
				g.setColor(Color.YELLOW);
				heng = (EssentialStroke/*HengStroke*/)s;
				x1 = pad + (int)(heng.getP1().getX() * gridboxSize);
				x2 = pad + (int)(heng.getP2().getX() * gridboxSize);
				y1 = pad + (int)(heng.getP1().getY() * gridboxSize);
				y2 = pad + (int)(heng.getP2().getY() * gridboxSize);
				if (s instanceof HengStroke ||
					s instanceof ShuStroke /*|| s instanceof DianStroke*/) { 
					hLine = new Line2D.Double(pad + heng.getP1().getX() * gridboxSize,
							pad + heng.getP1().getY() * gridboxSize,
							pad + heng.getP2().getX() * gridboxSize,
							pad + heng.getP2().getY() * gridboxSize);
				//g.drawLine(x1, y1, x2, y2);
				}	
				else if (s instanceof PieStroke){
					hLine = new QuadCurve2D.Double(pad + heng.getP1().getX() * gridboxSize,
							pad + heng.getP1().getY() * gridboxSize,
							pad + (heng.getP2().getX() + (heng.getP1().getX() - heng.getP2().getX())*2/3) * gridboxSize,
							pad + (heng.getP1().getY() + (heng.getP2().getY() - heng.getP1().getY())*2/3) * gridboxSize,
							pad + heng.getP2().getX() * gridboxSize,
							pad + heng.getP2().getY() * gridboxSize);
				}
				else if (s instanceof NaStroke){
					hLine = new QuadCurve2D.Double(pad + heng.getP1().getX() * gridboxSize,
							pad + heng.getP1().getY() * gridboxSize,
							pad + (heng.getP1().getX() + (heng.getP2().getX() - heng.getP1().getX())*1/3) * gridboxSize,
							pad + (heng.getP1().getY() + (heng.getP2().getY() - heng.getP1().getY())*2/3) * gridboxSize,
							pad + heng.getP2().getX() * gridboxSize,
							pad + heng.getP2().getY() * gridboxSize);
				}
				
				else if (s instanceof DianStroke){
					hLine = new QuadCurve2D.Double(pad + heng.getP1().getX() * gridboxSize,
							pad + heng.getP1().getY() * gridboxSize,
							pad + (heng.getP1().getX() + (heng.getP2().getX() - heng.getP1().getX())*3/4) * gridboxSize,
							pad + (heng.getP1().getY() + (heng.getP2().getY() - heng.getP1().getY())*1/2) * gridboxSize,
							pad + heng.getP2().getX() * gridboxSize,
							pad + heng.getP2().getY() * gridboxSize);
				}
			}
			g.setColor(Color.GREEN);
			// make line looks smooth
			Graphics2D g2d = (Graphics2D) g;
			BasicStroke boldStroke = new BasicStroke(3f);
	        g2d.setStroke(boldStroke);
			g2d.setRenderingHint(
			    RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.draw(hLine);
			
			g.setColor(tempC);
		}
	}

	private void drawGridBox(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		int x = pad, y = pad, bb = 150;
		
		Color tempColor = g.getColor();
		g.setColor(Color.RED);
		
		Line2D.Float line;
		for (int h=0; h<3; h++) {
			line = new Line2D.Float(x, y + h*bb, x + 2*bb, y + h*bb);
			g2d.draw(line);
		}
		for (int v=0; v<3; v++) {
			line = new Line2D.Float(x + v*bb, y, x + v*bb, y + 2*bb);
			g2d.draw(line);
		}
		line = new Line2D.Float(x, y, x + 2*bb, y + 2*bb);
		g2d.draw(line);
		line = new Line2D.Float(x, y + 2*bb, x + 2*bb, y);
		g2d.draw(line);

		g.setColor(tempColor);
	}
	
	private void loadStrokesAsImages() {
		String[] strokeLabels = {"d", "h", "v", "t", "p", "u", "hv", "vj", "vaj"}; 
		//String[] strokeLabels = {"h", "v", "p", "u", "d", "t", "hv", "vj", "vaj"}; 
		strokeImages = new Image[strokeLabels.length];
		Image strokeImg;
		for (int i=0; i<strokeLabels.length; i++) {
			// mingti strokes
			//strokeImg = new ImageIcon("images/strokes/mingti/Cjk_m_str_" + 
			// kaiti strokes
				strokeImg = new ImageIcon("images/strokes/kaiti/Cjk_k_str_" + 
					strokeLabels[i] + ".svg.png").getImage();
			strokeImages[i] = strokeImg;
		}
	}
	
	private void displayFontZiInBox(Graphics g) {
		List<IStroke> strokesInZi = charShape.getStrokes();
		System.out.println(this.charShape.getChnChar() + " stroke count  : " + strokesInZi.size());
		fontImage = ImageUtil.imgFromFontset(this.charShape.getChnChar());
		
		//JOptionPane.showConfirmDialog(null, "Font image...", "Font image...", 
		//		0, 0, new ImageIcon(fontImage));
		
		g.drawImage(fontImage, pad, pad, null);
		 
		for (IStroke s : strokesInZi) {
			System.out.println("stroke type  : " + s);
			Point startPoint = getStartPoint(fontImage, s);
			drawAimCross(g, startPoint);
		}
			
	}

	private void drawAimCross(Graphics g, Point p) {
		Color temp = g.getColor();
		g.setColor(Color.GREEN);
		g.drawLine(p.x - pad, p.y, p.x +pad, p.y);
		g.drawLine(p.x, p.y - pad, p.x, p.y +pad);
		g.setColor(temp);
	}

	private Point getStartPoint(BufferedImage fontImage, IStroke s) {
		int x = 75 + pad, y = 150 + pad;
		if (s instanceof HengStroke) {
			System.out.println("stroke type  : " + s.getStrokeType());
			HengStroke h = (HengStroke)s;
			x = (int)(h.getP1().getX() * gridboxSize + pad);
			y = (int)(h.getP1().getY() * gridboxSize + pad);
			System.out.println("start x =: " + x);
			System.out.println("start y =: " + y);
			System.out.println("font color: " + fontImage.getRGB(160 - pad, y - pad));
		}
		return new Point(160, y);
	}

	private void displayZiInBox(Graphics g) {
		List<IStroke> strokesInZi = charShape.getStrokes();
		System.out.println("stroke count  : " + strokesInZi.size());
		StrokeInPlace sip;
		for (IStroke s : strokesInZi) {
			System.out.println("stroke type  : " + s);
			//if (s.getStrokeType().equals(StrokeEnum.HENG)) {
			if (s instanceof EssentialStroke) {
				int index = lookupImageIndex(s.getStrokeType().ordinal());
				EssentialStroke heng = (EssentialStroke)s;
				Point start = new Point((int)(heng.getP1().getX()*gridboxSize),
						(int)(heng.getP1().getY()*gridboxSize));
				sip = new StrokeInPlace(index, start);
				int x2 = (int)(heng.getP2().getX()*gridboxSize) + pad;
				int y2 = (int)(heng.getP2().getY()*gridboxSize) + pad;
				sip.mapTo(start, new Point(x2, y2));
				g.drawImage(this.strokeImages[sip.strokeType], 
						sip.location.x, sip.location.y, 
						(int)(sip.fX * 280), (int)(sip.fY * 280), null);
				//g.drawImage(this.strokeImages[index], 0, 0, size, size, null);				
			}
			/*
			else if (s.getStrokeType().equals(StrokeEnum.SHU)) {
				int index = 2;
				ShuStroke shu = (ShuStroke)s;
				Point start = new Point((int)(shu.getP1().getX()*gridboxSize),
						(int)(shu.getP1().getY()*gridboxSize));
				sip = new StrokeInPlace(index, start);
				int x2 = (int)(shu.getP2().getX()*gridboxSize) + pad;
				int y2 = (int)(shu.getP2().getY()*gridboxSize) + pad;
				sip.mapTo(start, new Point(x2, y2));
				g.drawImage(this.strokeImages[sip.strokeType], 
						sip.location.x, sip.location.y, 
						(int)(sip.fX * 280), (int)(sip.fY * 280), null);
				//g.drawImage(this.strokeImages[index], 0, 0, size, size, null);				
			}
			*/
		}
	}

	private int lookupImageIndex(int ordinal) {
		int index;
		switch(ordinal) {
		case 0:
			index = 1;
			break;
		case 1:
			index = 2;
			break;
		case 2: 
			index = 3;
			break;
		case 3:
			index = 4;
			break;
		case 4:
			index = 0;
			break;
		case 5:
			index = 5;
			break;
		default:
			index = -1;
			break;
		}
		
		return index;
	}

	public void setDisplayZiInBox(boolean b) {
		this.displayZiInBox = b;
	}
	

	public static void main(String[] args) {
		char zi = '王';
		//char zi = '三';
		JFrame f = new JFrame("汉字123 Test - "+ zi);

		ChnCharStrokeSequence testZi;
		
		testZi = prepareZi(zi);
		MizigeLabel mzg = new MizigeLabel(testZi);
		mzg.setDisplayZiInBox(true);
		//mzg.setDisplayFontZiInBox(true);
		f.add(mzg);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	private void setDisplayFontZiInBox(boolean b) {
		this.displayFontZiInBox = b;
	}

	private static ChnCharStrokeSequence prepareZi(char zi) {
		if(zi == '一') {
			ChnCharStrokeSequence yiZi = new ChnCharStrokeSequence(zi);
			//HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.1, 0.5), new EndPoint(0.9, 0.5));
			//HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.105, 0.452), new EndPoint(0.862, 0.457));
			HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.102,0.495), new EndPoint(0.923,0.49));
			yiZi.addStroke(heng);
			return yiZi;
		}
		else if(zi == '三') {
			ChnCharStrokeSequence yiZi = new ChnCharStrokeSequence(zi);
			//HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.26, 0.25), new EndPoint(0.7, 0.22));
			HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.301,0.235), new EndPoint(0.719,0.199));
			yiZi.addStroke(heng);
			//heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.3, 0.5), new EndPoint(0.65, 0.47));
			heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.316,0.48), new EndPoint(0.694,0.454));
			yiZi.addStroke(heng);
			//heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.1, 0.7), new EndPoint(0.9, 0.7));
			heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.112,0.735), new EndPoint(0.949,0.709));
			yiZi.addStroke(heng);			
			return yiZi;
		}

		return null;
	}

}
