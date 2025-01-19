package stroke;

import stroke.model.EndPoint;
import stroke.model.EssentialStroke;
import stroke.model.StrokeEnum;

public class ShuStroke extends EssentialStroke {
	// to model x, y directions as defined in essential stroke class

	public ShuStroke(StrokeEnum strokeType, EndPoint p1, EndPoint p2) {
		super(strokeType, p1, p2);
	}
	
	public String toString() {
		return strokeType + "(" + p1 + ")-->(" + p2 + ")";
	}
}