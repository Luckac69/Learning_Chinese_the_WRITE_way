package stroke.model;

import java.util.List;

public interface IStroke {
	StrokeEnum getStrokeType();

	List<EndPoint> getPoints();
	
}
