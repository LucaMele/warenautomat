package warenautomat;
import java.util.Date;

public class Ware {
	private String mWarenname;
	private double mPreis;
	private Date mAblaufdatum;
	
	/**
	 * @constructor
	 * @param pWarenname
	 * @param pPreis
	 */
	public Ware(String pWarenname, double pPreis, Date pAblaufdatum) {
		mWarenname = pWarenname;
		mPreis = pPreis;
		mAblaufdatum = pAblaufdatum;
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
	 * @return
	 */
	public Date getAblaufsdatum() {
		return mAblaufdatum;
	}
}
