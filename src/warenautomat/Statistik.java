package warenautomat;

import java.util.ArrayList;

public class Statistik {
	private ArrayList<Ware> mStatistikHistorie = new ArrayList<Ware>();

	/**
	 * 
	 * @param pWare
	 */
	public void setWare(Ware pWare) {
		mStatistikHistorie.add(pWare);
	}
	
	/**
	 *
	 * @return
	 */
	public ArrayList<Ware> gibStatistikHistorie() {
		return mStatistikHistorie;
	}
}
