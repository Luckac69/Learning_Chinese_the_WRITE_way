package stroke.test;

import java.util.Map;
import java.util.HashMap;

import stroke.ChnCharStrokeSequence;
import stroke.DianStroke;
import stroke.HengStroke;
import stroke.PieStroke;
import stroke.ShuStroke;
import stroke.NaStroke;
import stroke.model.EndPoint;
import stroke.model.StrokeEnum;

public class StrokeTestSeqBank {
	Map<Character, ChnCharStrokeSequence> ziStrokeSeqDictionary;

	public StrokeTestSeqBank() {
		ziStrokeSeqDictionary = new HashMap<>();
		
		loadSeqForTestChars();
	}

	private void loadSeqForTestChars() {
		// load yi
		char zi ='一';
		ChnCharStrokeSequence strkSeq = new ChnCharStrokeSequence(zi);
		//HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.1, 0.5), new EndPoint(0.9, 0.5));
		//HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.105, 0.452), new EndPoint(0.862, 0.457));
		HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.102,0.495), new EndPoint(0.923,0.49));
		strkSeq.addStroke(heng);
		this.ziStrokeSeqDictionary.put(zi, strkSeq);
		
		// load yi
		zi ='二';
		strkSeq = new ChnCharStrokeSequence(zi);
		//HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.1, 0.5), new EndPoint(0.9, 0.5));
		//HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.105, 0.452), new EndPoint(0.862, 0.457));
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.281,0.301), new EndPoint(0.73,0.255));
		strkSeq.addStroke(heng);
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.112,0.668), new EndPoint(0.913,0.653));
		strkSeq.addStroke(heng);
		this.ziStrokeSeqDictionary.put(zi, strkSeq);
		
		// load san
		zi = '三';
		strkSeq = new ChnCharStrokeSequence(zi);
		//HengStroke heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.26, 0.25), new EndPoint(0.7, 0.22));
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.301,0.235), new EndPoint(0.719,0.199));
		strkSeq.addStroke(heng);
		//heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.3, 0.5), new EndPoint(0.65, 0.47));
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.316,0.48), new EndPoint(0.694,0.454));
		strkSeq.addStroke(heng);
		//heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.1, 0.7), new EndPoint(0.9, 0.7));
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.112,0.735), new EndPoint(0.949,0.709));
		strkSeq.addStroke(heng);
		this.ziStrokeSeqDictionary.put(zi, strkSeq);
		
		// load ren
		zi = '人';
		strkSeq = new ChnCharStrokeSequence(zi);
		PieStroke pie = new PieStroke(StrokeEnum.PIE, new EndPoint(0.551,0.189), 
				new EndPoint(0.066,0.791));
		strkSeq.addStroke(pie);
		NaStroke na = new NaStroke(StrokeEnum.NA, new EndPoint(0.454,0.403), 
				new EndPoint(0.964,0.796));
		strkSeq.addStroke(na);
		this.ziStrokeSeqDictionary.put(zi, strkSeq);
		
		// load da
		zi = '大';
		strkSeq = new ChnCharStrokeSequence(zi);
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.199,0.434), new EndPoint(0.806,0.378));
		strkSeq.addStroke(heng);
		pie = new PieStroke(StrokeEnum.PIE, new EndPoint(0.51,0.112), 
				new EndPoint(0.133,0.847));
		strkSeq.addStroke(pie);
		na = new NaStroke(StrokeEnum.NA, new EndPoint(0.469,0.459), 
				new EndPoint(0.939,0.847));
		strkSeq.addStroke(na);
		this.ziStrokeSeqDictionary.put(zi, strkSeq);
		
		// load tian 
		// learned, as HENGs
		//[HENG(0.311,0.194)-->(0.724,0.148), HENG(0.168,0.464)-->(0.832,0.413), 
		// HENG(0.128,0.827)-->(0.52,0.24), HENG(0.48,0.454)-->(0.934,0.852)]
		zi = '天';
		strkSeq = new ChnCharStrokeSequence(zi);
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.311,0.194), new EndPoint(0.724,0.148));
		strkSeq.addStroke(heng);
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.168,0.464), new EndPoint(0.832,0.413));
		strkSeq.addStroke(heng);
		pie = new PieStroke(StrokeEnum.PIE, new EndPoint(0.52,0.24), 
				new EndPoint(0.128,0.827));
		strkSeq.addStroke(pie);
		na = new NaStroke(StrokeEnum.NA, new EndPoint(0.48,0.454), 
				new EndPoint(0.934,0.852));
		/*
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.15,0.10), new EndPoint(0.85,0.10));
		strkSeq.addStroke(heng);
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.10,0.40), new EndPoint(0.90,0.40));
		strkSeq.addStroke(heng);
		pie = new PieStroke(StrokeEnum.PIE, new EndPoint(0.5, 0.1), 
				new EndPoint(0.1, 0.9));
		strkSeq.addStroke(pie);
		na = new NaStroke(StrokeEnum.NA, new EndPoint(0.49, 0.42), 
				new EndPoint(0.9, 0.9));
				*/
		strkSeq.addStroke(na);
		this.ziStrokeSeqDictionary.put(zi, strkSeq);
		
		// load wang 
		zi = '王';
		strkSeq = new ChnCharStrokeSequence(zi);
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.281,0.224), new EndPoint(0.74,0.184));
		strkSeq.addStroke(heng);
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.316,0.495), new EndPoint(0.704,0.459));
		strkSeq.addStroke(heng);
		ShuStroke shu = new ShuStroke(StrokeEnum.SHU, new EndPoint(0.5,0.224), new EndPoint(0.5,0.75));
		strkSeq.addStroke(shu);
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.107,0.77), new EndPoint(0.929,0.765));
		strkSeq.addStroke(heng);
		this.ziStrokeSeqDictionary.put(zi, strkSeq);
		
		//[HENG(0.173,0.224)-->(0.867,0.184), HENG(0.117,0.724)-->(0.633,0.25), 
		// HENG(0.449,0.342)-->(0.52,0.403), HENG(0.617,0.469)-->(0.888,0.663)]
		
		// load wang 
		zi = '不';
		strkSeq = new ChnCharStrokeSequence(zi);
		heng = new HengStroke(StrokeEnum.HENG, new EndPoint(0.173,0.224), new EndPoint(0.867,0.184));
		strkSeq.addStroke(heng);
		pie = new PieStroke(StrokeEnum.PIE, new EndPoint(0.633,0.25), new EndPoint(0.117,0.724));
		strkSeq.addStroke(pie);
		shu = new ShuStroke(StrokeEnum.SHU, new EndPoint(0.55,0.374), new EndPoint(0.55,0.91));
		strkSeq.addStroke(shu);
		DianStroke dian = new DianStroke(StrokeEnum.DIAN, new EndPoint(0.617,0.469), new EndPoint(0.888,0.663));
		strkSeq.addStroke(dian);
		this.ziStrokeSeqDictionary.put(zi, strkSeq);
	}
	
	public ChnCharStrokeSequence lookupStrokeSeq(char zi) {
		return this.ziStrokeSeqDictionary.get(zi);
	}
}	
