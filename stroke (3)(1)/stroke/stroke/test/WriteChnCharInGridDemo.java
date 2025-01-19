package stroke.test;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import stroke.ChnCharStrokeSequence;

public class WriteChnCharInGridDemo {

	public static void main(String[] args) {
		String testZis = "一二三人大天王不";
		Character[] zis = new Character[testZis.length()];
		for (int i=0; i<zis.length; i++)
			zis[i] = testZis.charAt(i);
		
		JFrame f = new JFrame("汉字123 Test - 字");
		char zi = (Character)JOptionPane.showInputDialog(f, "Please choose a 字", 
				"Select", 0, null, zis, 0); // = '王';

		ChnCharStrokeSequence testZi;
		StrokeTestSeqBank ziBank = new StrokeTestSeqBank();
		
		System.out.print(zi + "==>");
		testZi = ziBank.lookupStrokeSeq(zi);
		System.out.println(testZi.getChnChar());

		MizigeLabel mzg = new MizigeLabel(testZi);
		mzg.setDisplayZiInBox(true);
		//mzg.setDisplayFontZiInBox(true);
		f.add(mzg);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		/*
		while (true) {
			zi = (Character)JOptionPane.showInputDialog(f, "Please choose a 字", 
					"Select", 0, null, zis, 0);
			mzg = new MizigeLabel(ziBank.lookupStrokeSeq(zi));
			f.add(mzg);
			f.repaint();
		}
		*/
	}

}
