package warenautomat;

public class Muenzeule {

	private int mKapazitaet;
	private double mMuenzart;
	private int mAnzahlMuenzen;
	
	/**
	 *
	 * @param pKapazitaet
	 * @param pMuenzart
	 * @param pAnzahlMuenzen
	 */
	public Muenzeule(int pKapazitaet , double pMuenzart, int pAnzahlMuenzen) {
		mKapazitaet = pKapazitaet;
		mMuenzart = pMuenzart;
		mAnzahlMuenzen = pAnzahlMuenzen;
	}

	/**
	 * 
	 * @return
	 */
	public double getMuenzart() {
		return mMuenzart;
	}

	/**
	 *
	 * @param pAnzahl
	 * @return
	 */
	public boolean fuegeMunzenHinzu(int pAnzahl) {
		if (mAnzahlMuenzen < 100) {
			mAnzahlMuenzen++;
			return true;
		}
		return false;
	}
}
