package warenautomat;
import java.util.Date;

public class Ware {
	private String mWarenname;
	private double mPreis;
	private Date mAblaufdatum;
	private Date mVerkaufsdatum;
	private KonfigurationBestellung mKonfigurationBestellung;
	
	/**
	 * @constructor
	 * @param pWarenname
	 * @param pPreis
	 */
	public Ware(String pWarenname, double pPreis, Date pAblaufdatum) {
		mWarenname = pWarenname;
		mPreis = pPreis;
		mAblaufdatum = pAblaufdatum;
		mVerkaufsdatum = null;
		mKonfigurationBestellung = null;
	}
	
	/**
	 *
	 * @return
	 */
	public String getWarenname() {
		return mWarenname;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getPreis() {
		return mPreis;
	}
	
	/**
	 *
	 * @param pVerkausdatum
	 */
	public void setVerkausdatum(Date pVerkausdatum) {
		mVerkaufsdatum = pVerkausdatum;
	}
	
	/**
	 *
	 * @param pVerkausdatum
	 */
	public Date getVerkausdatum() {
		return mVerkaufsdatum;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getAblaufsdatum() {
		return mAblaufdatum;
	}
	
	/**
	 *
	 * @param pGrenze
	 * @param pBestellAnzahl
	 */
	public void aktualisiereKonfigurationBestellung(int pGrenze, int pBestellAnzahl) {
		mKonfigurationBestellung = new KonfigurationBestellung(pGrenze, pBestellAnzahl);
	}
	
	/**
	 * 
	 * @return
	 */
	public KonfigurationBestellung getKonfigurationBestellung() {
		return mKonfigurationBestellung;
	}
}
