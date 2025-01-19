package stroke.model;

import java.util.List;
import java.util.ArrayList;

public abstract class EssentialStroke implements IStroke {
	protected EndPoint p1, p2;
	protected StrokeEnum strokeType;

	public EssentialStroke(StrokeEnum strokeType) {
		this.strokeType = strokeType;
	}
	
	public EssentialStroke(StrokeEnum type, EndPoint p1, EndPoint p2) {
		this(type);
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public StrokeEnum getStrokeType() {
		return strokeType;
	}

	public EndPoint getP1() {
		return p1;
	}

	public EndPoint getP2() {
		return p2;
	}
	
	public List<EndPoint> getPoints() {
		ArrayList<EndPoint> points = new ArrayList<>();
		
		points.add(p1);
		points.add(p2);
		return points;
	}

}
