package warenautomat;

import java.util.Date;

public class Fach {
	private Ware mWare;
	
	/**
	 * @constructor
	 */
	public Fach() {}
	
	/**
	 *
	 * @param pWarenname
	 * @param pPreis
	 * @param pAblaufdatum
	 */
	public void fuelleFachAuf(String pWarenname, double pPreis, Date pAblaufdatum) {
		mWare = new Ware(pWarenname, pPreis, pAblaufdatum);
		System.out.print("\nNeue Ware erstellt: " + pWarenname + ", " + pPreis + " CHF, Ablaufdatum:" + pAblaufdatum + "\n");
	}
	
	/**
	 * 
	 * @return
	 */
	public Ware getWare() {
		return mWare;
	}
}
