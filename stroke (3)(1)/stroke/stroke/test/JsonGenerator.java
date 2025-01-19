package stroke.test;

import java.util.List;
import java.util.Set;

import stroke.ChnCharStrokeSequence;
import stroke.model.EndPoint;
import stroke.model.IStroke;

public class JsonGenerator {
	static StrokeTestSeqBank db = new StrokeTestSeqBank();

	public static void main(String[] args) {
		// generate json expr for chn char with stroke seq info
		//char cch = '三';
		
		Set<Character> cchList = db.ziStrokeSeqDictionary.keySet();
		for (char cch : cchList)
			printJsonForChnChar(cch);
	}

	private static void printJsonForChnChar(char cch) {
		String json = "{\"chnChar\":\"" + cch + "\", \"unicode\":\"" + 
		getUnicode(cch) + "\",\"strokes\":\n[";
		
		ChnCharStrokeSequence seq = db.lookupStrokeSeq(cch);
		for (IStroke s : seq.getStrokes()) {
			//System.out.println(s);
			json += "\"strokeType\":\"" + s.getStrokeType() + "\",";
			json += "\"points\":" + formJsonArray(s.getPoints()) + ",\n";
		}
		
		json = json.substring(0, json.length()-2) + "\n]}";
		System.out.println(json);
		
	}

	private static String getUnicode(char cch) {
		String code = "\\u" + Integer.toHexString(cch | 0x10000).substring(1);
		return code;
	}

	private static String formJsonArray(List<EndPoint> points) {
		String json = "[";
		for (EndPoint ep : points) {
			json += "{\"x\":" + ep.getX() + "," +
					"\"y\":" + ep.getY() + "},";
		}
		
		return json.substring(0, json.length()-1) + "]";
	}

}
