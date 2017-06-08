package warenautomat;

public class KonfigurationBestellung {
	
	private int mGrenze;
	private int mBestellAnzahl;
	
	/**
	 * @constructor
	 */
	public KonfigurationBestellung(int pGrenze, int pBestellAnzahl) {
		mGrenze = pGrenze;
		mBestellAnzahl = pBestellAnzahl;
	}
	
	/**
	 *
	 * @return
	 */
	public int getBestellAnzahl() {
		return mBestellAnzahl;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getGrenze() {
		return mGrenze;
	}
}
