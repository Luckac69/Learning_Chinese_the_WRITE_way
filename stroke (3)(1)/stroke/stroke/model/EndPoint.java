package stroke.model;

import java.text.DecimalFormat;

public class EndPoint {
	private double x, y;
	
	public EndPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public String toString() {
        DecimalFormat df = new DecimalFormat("#.###");
        String  fx = df.format(x), 
        		fy = df.format(y);

		return fx + "," + fy;
	}
}
