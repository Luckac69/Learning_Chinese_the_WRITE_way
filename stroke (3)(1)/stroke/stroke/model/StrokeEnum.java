package stroke.model;

import java.util.Set;
import java.util.TreeSet;

public enum StrokeEnum {
	HENG, SHU, PIE, NA, DIAN, TI, // essential
	SHU_GOU, HENG_ZHE, HENG_ZHE_GOU, HENG_GOU, 
	HENG_PIE, SHU_ZHE, SHU_TI, // intermediate
	PIE_TI, PIE_DIAN, XIE_GOU, SHU_WAN_GOU,
	SHU_ZHE_ZHE_GOU, HENG_WAN_GOU, HENG_PIE_WANG_GOU, // advanced
	HENG_ZHE_TI, WAN_GOU, WO_GOU, HENG_ZHE_WAN_GOU, 
	HENG_ZHE_ZHE_ZHE_GOU, SHU_WAAN, HENG_ZHE_WAN,
	HENG_ZHE_ZHE_PIE, SHU_ZHE_PIE, HENG_XIE_GOU,
	SHU_ZHE_ZHE, HENG_ZHE_ZHE,HENG_ZHE_ZHE_ZHE;		// 32 in all ==> 33 w/ HWG
	// https://www.mamaloveprint.com/2021/01/mama-love-print-k3-k3-free-download.html#google_vignette
	// changes made
	// - DIAN moved behind NA
	// - SHU replaced the name “ZHI”
	// - ZHE used when an perpendicular turn is made
	// http://bishun.strokeorder.info/bihua.php#google_vignette
	// lists 32 types, like many other mainland(?) resources
	// HENG_WAN_GOU as in 乙 is not included
	
	public String toString() {
		String strokeName = this.name();
		return strokeName;
	}
	
	public String getAbbr() {
		String str = this.name();
		//System.out.println(str);
		String abbr = str.substring(0, 1);		
		int indexOfUnderscore = str.indexOf("_");
		while (indexOfUnderscore > 0){
			str = str.substring(indexOfUnderscore + 1);
			abbr += str.substring(0, 1);		
			indexOfUnderscore = str.indexOf("_");
		}
		
		return abbr;
	}
	
	public static void main(String[] args) {
		//System.out.println(HENG.getAbbr());
		//System.out.println(HENG_PIE_WANG_GOU.getAbbr());
		Set<String> abbrs = new TreeSet<>();
		for (StrokeEnum type: StrokeEnum.values()) {
			System.out.println(type.ordinal() + ":\t" + type.getAbbr());
			abbrs.add(type.toString());
		}
			
		System.out.println(abbrs.size());
		System.out.println(abbrs);
	}
}
