package warenautomat;

public class Muenzseule {

	private int mKapazitaet;
	private double mMuenzart;
	private int mAnzahlMuenzen;
	private int mTemporaereAnzahlMuenzen;
	private boolean mIstAmVerwalten;
	private boolean mIstDirty;
	
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
		mTemporaereAnzahlMuenzen = pAnzahlMuenzen;
		mIstDirty = false;
		mIstAmVerwalten = false;
	}
	
	/**
	 *
	 * @return
	 */
	public int gibAnzahlMuenzen() {
		if (mIstAmVerwalten) {
			return mTemporaereAnzahlMuenzen;
		}
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
	 * @param pIstAmVerwalten
	 */
	public void istAmVerwalten(boolean pIstAmVerwalten) {
		mIstAmVerwalten = pIstAmVerwalten;
		if (pIstAmVerwalten && !mIstDirty) {
			mTemporaereAnzahlMuenzen = mAnzahlMuenzen;
		} else {
			mIstDirty = true;
		}
	}
	
	/**
	 * 
	 */
	public void speichereVerwalteteMuenzen() {
		if (!mIstAmVerwalten && mIstDirty) {
			mAnzahlMuenzen = mTemporaereAnzahlMuenzen;
			mIstDirty = false;
		}
	}

	/**
	 *
	 * @param pAnzahl
	 * @return
	 */
	public boolean fuegeMunzenHinzu(int pAnzahl) {
		if (pAnzahl == 0) {
			return false;
		}
		if (mIstAmVerwalten) {
			if (mTemporaereAnzahlMuenzen + pAnzahl <= Kasse.CAPACITY_MUENZEULE) {
				mTemporaereAnzahlMuenzen = mTemporaereAnzahlMuenzen + pAnzahl;
			} else {
				mTemporaereAnzahlMuenzen = Kasse.CAPACITY_MUENZEULE;
			}
		} else {
			if (mAnzahlMuenzen + pAnzahl <= Kasse.CAPACITY_MUENZEULE) {
				mAnzahlMuenzen = mAnzahlMuenzen + pAnzahl;
			} else {
				mAnzahlMuenzen = Kasse.CAPACITY_MUENZEULE;
			}
		}
		return true;
	}
	
	/**
	 *
	 * @param pAnzahl
	 * @return
	 */
	public boolean entferneMuenzen(int pAnzahl) {
		if (pAnzahl == 0) {
			return false;
		}
		if (mIstAmVerwalten) {
			if (mTemporaereAnzahlMuenzen - pAnzahl >= 0) {
				mTemporaereAnzahlMuenzen = mTemporaereAnzahlMuenzen - pAnzahl;
			} else {
				mTemporaereAnzahlMuenzen = 0;
			}
		} else {
			if (mAnzahlMuenzen - pAnzahl >= 0) {
				mAnzahlMuenzen = mAnzahlMuenzen - pAnzahl;
			} else {
				mAnzahlMuenzen = 0;
			}
		}
		return true;
	}
}
