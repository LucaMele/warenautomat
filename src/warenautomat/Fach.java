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
	}
	
	/**
	 * 
	 * @return
	 */
	public Ware getWare() {
		return mWare;
	}
	
	/**
	 * 
	 * @param pWare
	 */
	public void setWare(Ware pWare) {
		mWare = pWare;
	}
}
