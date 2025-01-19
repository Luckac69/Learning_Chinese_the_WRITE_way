package stroke.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

public class ImageUtil {
	public static int[][] convertImageForThinning(BufferedImage ziToTrace) {
		int w = ziToTrace.getWidth();
		int h = ziToTrace.getHeight();
		int blankValue = ziToTrace.getRGB(0, 0);
		
		int[][] image = new int[h][w];
		for (int r=0; r<h; r++) {
			for (int c=0; c<w; c++) {
				image[r][c] = (ziToTrace.getRGB(c, r) == blankValue)?0:1; 
				// -1 or Color(255,255,255,255) is a dot; else is blank
			}
		}
		
		return image;
	}
	
	public static void printTraceLine(int[][] traceImage) {
		for (int r=0; r<traceImage.length; r++) {
			for (int c=0; c<traceImage[0].length; c++) {
				//color = new Color(traceImage[r][c]);
				if (traceImage[r][c] == 1) {
					System.out.print('.');
				}
				else {
					System.out.print(' ');
				}
			}
			System.out.println();		
		}
	}

	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), 
	    		img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

	public static BufferedImage imgFromFontset(char c) {
		Font chalkFont = null;
	
	    try {
			chalkFont = Font.createFont(Font.TRUETYPE_FONT, 
					new File("images/SanJiQiChuanKaiShu-2.ttf")).deriveFont(300f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //chalkFont = Font.createFont(Font.TRUETYPE_FONT, 
	    //		new File("images/BeiShiDaShuoWenXiaoZhuan-1.ttf")).deriveFont(240f);
	    
	    //chalkFont = Font.createFont(Font.TRUETYPE_FONT, 
	    //		new File("images/YanZhenQingFaShu-2.ttf")).deriveFont(240f);
	    //customFont = Font.createFont(Font.TRUETYPE_FONT, 
	    //new File("ctwwimage/TianShiSongTiJiuZiXing-1.ttf")).deriveFont(12f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    //register the font
	    ge.registerFont(chalkFont);
	    
	    BufferedImage bimage = new BufferedImage(300, 300, 
	    		BufferedImage.TYPE_INT_ARGB);

	    Graphics2D bGr = bimage.createGraphics();
	    bGr.setFont(chalkFont);
	    bGr.setColor(Color.WHITE);
	    bGr.fillRect(0, 0, 300, 300);	    
	    bGr.setColor(Color.BLUE);
	    bGr.drawString("" + c, 0, 240);
	    bGr.dispose();

		return bimage;
	}

}
