package warenautomat;

public class Muenzseule {

	private int mKapazitaet;
	private double mMuenzart;
	private int mAnzahlMuenzen;
	
	/**
	 *
	 * @param pKapazitaet
	 * @param pMuenzart
	 * @param pAnzahlMuenzen
	 */
	public Muenzseule(int pKapazitaet , double pMuenzart, int pAnzahlMuenzen) {
		mKapazitaet = pKapazitaet;
		mMuenzart = pMuenzart;
		mAnzahlMuenzen = pAnzahlMuenzen;
	}
	
	public int gibAnzahlMuenzen() {
		return mAnzahlMuenzen;
	}

	/**
	 * 
	 * @return
	 */
	public double gibMuenzart() {
		return mMuenzart;
	}

	/**
	 *
	 * @param pAnzahl
	 * @return
	 */
	public boolean fuegeMunzenHinzu(int pAnzahl) {
		if (mAnzahlMuenzen + pAnzahl <= 100) {
			mAnzahlMuenzen = mAnzahlMuenzen + pAnzahl;
			return true;
		}
		return false;
	}
	
	/**
	 *
	 * @param pAnzahl
	 * @return
	 */
	public boolean entferneMuenzen(int pAnzahl) {
		if (mAnzahlMuenzen - pAnzahl >= 0) {
			mAnzahlMuenzen = mAnzahlMuenzen - pAnzahl;
			return true;
		}
		return false;
	}
}
