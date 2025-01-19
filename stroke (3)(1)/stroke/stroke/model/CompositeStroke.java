package stroke.model;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeStroke implements IStroke {
	protected StrokeEnum strokeType;
	protected List<EssentialStroke> segementStroke;

	public CompositeStroke(StrokeEnum strokeType) {
		this.strokeType = strokeType;
		segementStroke = new ArrayList<>();
	}

}
