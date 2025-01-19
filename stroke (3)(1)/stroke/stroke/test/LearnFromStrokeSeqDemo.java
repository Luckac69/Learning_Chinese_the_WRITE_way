package stroke.test;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import stroke.ChnCharStrokeSequence;
import stroke.HengStroke;
import stroke.image.ImageUtil;
import stroke.model.EndPoint;
import stroke.model.EssentialStroke;
import stroke.model.StrokeEnum;

public class LearnFromStrokeSeqDemo {

	public static void main(String[] args) {
		//char chnChar = '一'; //'三';'天';'人';'大';'王';'二'; 
		char chnChar = '字';
		String path = "lmzis";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		System.out.println(listOfFiles.length);
		String imageFile;
		Image seriesImage = null;
		for( File f : listOfFiles) {
			System.out.println(f.getName());
			if (f.getName().charAt(0) == chnChar) {
			//if (f.getName().indexOf(chnChar) > 0) {
				imageFile = path + "/" + f.getName();
				System.out.println(imageFile);
				ImageIcon iicon = new ImageIcon(imageFile);
				JOptionPane.showConfirmDialog(null, "Stroking sequence for " + chnChar, 
						"Current Stroke", 0, 0, iicon);
				seriesImage = iicon.getImage();
				break;
			}
		}

		int gridColor = -10175500, strokeColor = -898229;
		BufferedImage strokeSeqImage = ImageUtil.toBufferedImage(seriesImage);
		int[] xStrokeDotCount = new int[196], yStrokeDotCount = new int[196], 
				xGridDotCount = new int[196], yGridDotCount = new int[196];
		int strokeCount = strokeSeqImage.getWidth()/210 - 1;
		ChnCharStrokeSequence charSSeq;
		charSSeq = new ChnCharStrokeSequence(chnChar);
		HengStroke heng;
		Point p1, p2;
		for (int strk = 0; strk < strokeCount; strk++) {
			p1 = new Point(202 + strk*210, 0); 
			p2 = new Point(0, 0);
		for (int x=0; x < xStrokeDotCount.length; x++) {
			for (int y=6; y < yStrokeDotCount.length; y++) {
				if (strokeSeqImage.getRGB(y + strk*210, x) == gridColor) {
					xGridDotCount[x]++;
					yGridDotCount[y]++;
				}
				else if (strokeSeqImage.getRGB(y + strk*210, x) == strokeColor) {
					xStrokeDotCount[x]++;
					yStrokeDotCount[y]++;
					if (y < p1.x) {
						p1.x = y;
						p1.y = x;
					}
					else if (y > p2.x) {
						p2.x = y;
						p2.y = x;
					}
				}
			}
		}
		
		/*
		System.out.println("Along X");
		for (int i=0; i < yStrokeDotCount.length; i++) {
			if (yStrokeDotCount[i] > 3) {
				System.out.println(i + "\t-x-> " + yStrokeDotCount[i]);
			}
		}
		
		System.out.println("Along Y");
		for (int i=0; i < yStrokeDotCount.length; i++) {
			if (xStrokeDotCount[i] > 100) {
				System.out.println(i + "\\t-y-> " + xStrokeDotCount[i]);
			}
		}
		
		printChar(xStrokeDotCount, yStrokeDotCount, xGridDotCount, yGridDotCount);
		/*
		JOptionPane.showConfirmDialog(null, "Stroking sequence for " + chnChar, 
				"Current Stroke", 0, 0, new ImageIcon("lmzis/一Stroke1.png"));
		
		*/
		System.out.println("p1 --> " + p1);
		System.out.println("p2 --> " + p2);
		//HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.1, 0.5), new EndPoint(0.9, 0.5));
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint((p1.x - 6)/196., (p1.y-11)/196.), 
				new EndPoint((p2.x - 6)/196., (p2.y - 11)/196.));
		charSSeq.addStroke(heng);
		System.out.println(charSSeq);
		}
	}

	private static void printChar(int[] xStrokeDotCount, int[] yStrokeDotCount, int[] xGridDotCount, int[] yGridDotCount) {
		for (int y = 0; y < xGridDotCount.length; y++) {
			System.out.println((xStrokeDotCount[y] + 9)/10 + "\t" + (xGridDotCount[y] + 90)/100);
		}
		
		System.out.println("\n\n");
		for (int x = 0; x < xGridDotCount.length; x++) {
			System.out.print((yStrokeDotCount[x] + 9)/10);
			
		}
		System.out.println("\n\n");
		for (int x = 0; x < xGridDotCount.length; x++) {
			System.out.print((yGridDotCount[x] + 90)/100);
			
		}
	}

}
