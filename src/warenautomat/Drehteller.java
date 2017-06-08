
package warenautomat;

import java.util.Date;


public class Drehteller {
	private Fach[] mFach;
  
	/**
	 * 
	 * @param pMaxPositionen
	 */
	public Drehteller(int pMaxPositionen) {
		mFach = new Fach[pMaxPositionen];
		for (int i = 0; i < pMaxPositionen; i++) {
			mFach[i] = new Fach();
		}
	}
	
	/**
	 * 
	 * @param mFachNr
	 * @param pWarenname
	 * @param pPreis
	 * @param pAblaufdatum
	 */
	public void fuelleFachAuf(int mFachNr, String pWarenname, double pPreis, Date pAblaufdatum) {
		mFach[mFachNr].fuelleFachAuf(pWarenname, pPreis, pAblaufdatum);
	}
	
	/**
	 * 
	 * @param mFachNr
	 * @return
	 */
	public Fach getFach(int mFachNr) {
		return mFach[mFachNr];
	}
}
