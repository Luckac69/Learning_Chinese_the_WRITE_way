package stroke.thinning;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import stroke.image.ImageUtil;

public class CharThinningTest {
	int[][] image;
	
	Image ziImg;
	BufferedImage ziToTrace;
	int dotValue = -15066598, blankValue = -1;
	// -1 or Color(255,255,255,255) is blank; 
	// else is a dot or Color(28,28,28,255)

	public static void main(String[] args) {
		new CharThinningTest().test();
	}

	private void test() {
		loadImageFromFile("images/xinobs.png");
		//loadImageFromFile("ZiImage/6587JinWen.png");
		//loadImageFromFile("lmzis/不StrokeNo.png.png");
		
		int[][] traceImage = new ThinningService().doZhangSuenThinning(image, false);
		
		printTraceLine(traceImage);
		
		showThinnedZi();
		
		saveThinnedToFile("thinned/dong_bronze.png");
		/*
		
		divideNumbersNSave();
		*/
	}

	private void divideNumbersNSave() {
		ziImg = new ImageIcon("image/numbers.png").getImage();
		ziToTrace = ImageUtil.toBufferedImage(ziImg);
		int top, left, width, height, gap;
		top = 0;
		left = 8;
		width = 54;
		height = 54;
		gap = 13;

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    //bGr.drawImage(img, 0, 0, null);
	    //bGr.dispose();
			
	    File file;
		String[] numberInEnglish = {"one", "two", "three", "four", "five",
				"six", "seven", "eight", "nine", "ten"
		};

		for (int i=0; i<10; i++) {
			file = new File("image/" + numberInEnglish[i] + ".png");
			for (int col=0; col<width; col++) {
				for (int row=0; row<height; row++)
					bimage.setRGB(col, row, ziToTrace.getRGB(col + left, top + row + i*(height + gap)));
			}
			try {
				ImageIO.write(bimage, "png", file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void saveThinnedToFile(String fileName) {
		File file = new File(fileName);
		try {
			ImageIO.write(this.ziToTrace, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showThinnedZi() {
		System.out.println(ziToTrace.getWidth() + "x" + ziToTrace.getHeight());		
		ImageIcon thinZi = new ImageIcon(ziToTrace);
		JOptionPane.showConfirmDialog(null, "Thinned Zi Image", "Test", 0, 0, thinZi);
	}

	private void printTraceLine(int[][] traceImage) {
		int w = traceImage[0].length;
		int h = traceImage.length;
		image = new int[h][w];
		for (int r=0; r<h; r++) {
			for (int c=0; c<w; c++) {
				//color = new Color(traceImage[r][c]);
				if (traceImage[r][c] == 1) {
					System.out.print('.');
					ziToTrace.setRGB(c, r, dotValue);
				}
				else {
					System.out.print(' ');
					ziToTrace.setRGB(c, r, blankValue);
				}
			}
			System.out.println();		
		}
	}

	private void loadImageFromFile(String imageFile) {
		ziImg = new ImageIcon(imageFile).getImage();
		
		ziToTrace = ImageUtil.toBufferedImage(ziImg);
		System.out.println(dotValue);		
		System.out.println(blankValue);		
		int w = ziToTrace.getWidth();
		int h = ziToTrace.getHeight();
		
		image = new int[h][w];
		for (int r=0; r<h; r++) {
			for (int c=0; c<w; c++) {
				image[r][c] = (ziToTrace.getRGB(c, r) == -1)?0:1; 
				// -1 or Color(255,255,255,255) is a dot; else is blank
			}
		}
	}
}
