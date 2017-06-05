package warenautomat;

import java.util.ArrayList;
import java.util.Date;

public class Statistik {
	private ArrayList<Ware> mWarenbezug = new ArrayList<Ware>();

	/**
	 * 
	 * @param pWare
	 */
	public void erfasseWarenbezug(Ware pWare) {
		mWarenbezug.add(pWare);
	}
	
	/**
	 *
	 * @return
	 */
	public ArrayList<Ware> gibWarenbezug() {
		return mWarenbezug;
	}

	/**
	 *
	 * @param pName
	 * @param pDatum
	 * @return
	 */
	public int berechneAnzahlWaren(String pName, Date pDatum) {
		int totWaren = 0;
		for (Ware ware: mWarenbezug) {
		    if (ware.getWarenname().equals(pName) && ware.getVerkausdatum().after(pDatum)) {
		    	totWaren++;
		    }
		}
		return totWaren;
	}
}
