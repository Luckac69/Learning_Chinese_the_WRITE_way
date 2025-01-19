package stroke.image;

import java.awt.Point;

public 	class StrokeInPlace {
	public int strokeType;
	public Point location;
	public double fX = 1;
	public double fY = 1;
	
	public StrokeInPlace(int strokeType, Point location) {
		this.strokeType = strokeType;
		this.location = location;
	}
	
	/*
dian				
320	270	w	330	0.322265625
650	670	h	400	0.390625
			
pie				
821	112	w	-683	-0.666992188
138	904	h	792	0.7734375
			
na				
184	136	w	679	0.663085938
863	867	h	731	0.713867188
			
ti				
162	655	w	707	0.690429688
869	289	h	-366	-0.357421875

	*/
	public void mapTo(Point start, Point end) {
		System.out.println("mapTo Stroke type = " + strokeType);

		switch (strokeType) {
		case 5: 
			fX *= Math.abs(end.x - start.x) / .690 / 280;
			fY *= Math.abs(end.y - start.y) / .357 / 280;
			location.x = (int)(start.x - 280 * (162./1024) * fX);
			location.y = (int)(start.y - 280 * (655./1024) * fY);
			System.out.println(fX + "-" + fY + "@" + location);
			break;
		case 4: 
			fX *= Math.abs(end.x - start.x) / .663 / 280;
			fY *= Math.abs(end.y - start.y) / .714 / 280;
			location.x = (int)(start.x - 280 * (184./1024) * fX);
			location.y = (int)(start.y - 280 * (136./1024) * fY);
			System.out.println(fX + "-" + fY + "@" + location);
			break;
		case 3: 
			fX *= Math.abs(end.x - start.x) / .667 / 280;
			fY *= Math.abs(end.y - start.y) / .773 / 280;
			location.x = (int)(start.x - 280 * (821./1024) * fX);
			location.y = (int)(start.y - 280 * (112./1024) * fY);
			System.out.println(fX + "-" + fY + "@" + location);
			break;
		case 0: 
			fX *= Math.abs(end.x - start.x) / .322 / 280;
			fY *= Math.abs(end.y - start.y) / .391 / 280;
			location.x = (int)(start.x - 280 * (320./1024) * fX);
			location.y = (int)(start.y - 280 * (270./1024) * fY);
			System.out.println(fX + "-" + fY + "@" + location);
			break;
		case 1: 
			fX *= Math.abs(end.x - start.x) / .85 / 280;
			fY = fX * 0.9;
			location.x = (int)(start.x - 280 * .075 * fX);
			location.y = (int)(start.y - 280 * .475 * fY);
			System.out.println(fX + "@" + location);
			break;
		case 2: 
			fY *= Math.abs(end.y - start.y) / .826 / 280;
			fX = fY * 0.9;
			location.x = (int)(start.x - 280 * .495 * fX);
			location.y = (int)(start.y - 280 * .087 * fY);
			System.out.println(fY + "@" + location);
			break;
/*
zhe				
175	207	w	558	0.544921875
733	911	h	704	0.6875
			
gou				
476	73	w	2	0.001953125
478	964	h	891	0.870117188
			
wan				
183	121	w	701	0.684570313
884	802	h	681	0.665039063

*/
		case 6: 
			fX *= Math.abs(end.x - start.x) / .545 / 280;
			fY *= Math.abs(end.y - start.y) / .688 / 280;
			location.x = (int)(start.x - 280 * (175./1024) * fX);
			location.y = (int)(start.y - 280 * (207./1024) * fY);
			System.out.println(fX + "-" + fY + "@" + location);
			break;
		case 7: 
			fY *= Math.abs(end.y - start.y) / .870 / 280;
			location.x = (int)(start.x - 280 * (476./1024));
			location.y = (int)(start.y - 280 * (73./1024) * fY);
			System.out.println(fY + "@" + location);
			break;
		case 8:
			fX *= Math.abs(end.x - start.x) / .685 / 280;
			fY *= Math.abs(end.y - start.y) / .665 / 280;
			location.x = (int)(start.x - 280 * (183./1024) * fX);
			location.y = (int)(start.y - 280 * (121./1024) * fY);
			System.out.println(fX + "-" + fY + "@" + location);
			break;
		}
	}
}
