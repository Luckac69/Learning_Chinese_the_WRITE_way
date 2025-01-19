package stroke;

import java.util.ArrayList;
import java.util.List;

import stroke.model.IStroke;

public class ChnCharStrokeSequence {
	private char chnChar;
	private List<IStroke> strokes;
	
	public ChnCharStrokeSequence(char chnChar) {
		this.chnChar = chnChar;
		strokes = new ArrayList<>();
	}
	
	public void addStroke(IStroke s) {
		strokes.add(s);
	}
	
	public String toString() {
		return strokes.toString();
	}

	public List<IStroke> getStrokes() {
		return this.strokes;
	}

	public char getChnChar() {
		// TODO Auto-generated method stub
		return chnChar;
	}
}
