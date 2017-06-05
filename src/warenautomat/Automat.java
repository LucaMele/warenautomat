package warenautomat;

import java.util.ArrayList;
import java.util.Date;

/**
 * Der Automat besteht aus 7 Drehtellern welche wiederum je aus 16 Fächer
 * bestehen. <br>
 * Der erste Drehteller und das jeweils erste Fach haben jeweils die Nummer 1
 * (nicht 0!). <br>
 * Im Weitern hat der Automat eine Kasse. Diese wird vom Automaten instanziert.
 */
public class Automat {
  
  private static final int NR_DREHTELLER = 7;
  private static final int MAX_POSIZIONEN = 16;
  private Drehteller[] mDrehteller;
  private Kasse mKasse;
  private int mDrehtellerPosition;

  /**
   * Der Standard-Konstruktor. <br>
   * Führt die nötigen Initialisierungen durch (u.a. wird darin die Kasse
   * instanziert).
   */
  public Automat() {
	  mDrehteller = new Drehteller[NR_DREHTELLER];
	  for (int i = 0; i < NR_DREHTELLER; i++) {
		  mDrehteller[i] = new Drehteller(MAX_POSIZIONEN);
	  }
	  mDrehtellerPosition = 0;
	  mKasse = new Kasse();
  }

  /**
   * Füllt ein Fach mit Ware. <br>
   * Wenn das Service-Personal den Automaten füllt, wird mit einem
   * Bar-Code-Leser zuerst die Ware gescannt. <br>
   * Daraufhin wird die Schiebe-Tür geöffnet. <br>
   * Das Service-Personal legt die neue Ware ins Fach und schliesst das Fach. <br>
   * Die Hardware resp. System-Software ruft die Methode
   * <code> Automat.fuelleFach() </code> auf.
   * 
   * @param pDrehtellerNr Der Drehteller bei welchem das Fach hinter der
   *          Schiebe-Türe gefüllt wird. <br>
   *          Nummerierung beginnt mit 1 (nicht 0)!
   * @param pWarenName Der Name der neuen Ware.
   * @param pPreis Der Preis der neuen Ware.
   * @param pVerfallsDatum Das Verfallsdatum der neuen Ware.
   */
  public void fuelleFach(int pDrehtellerNr, String pWarenName, double pPreis, Date pVerfallsDatum) {
	 if (pDrehtellerNr <= 7) {
		 mDrehteller[pDrehtellerNr- 1].fuelleFachAuf(mDrehtellerPosition, pWarenName, pPreis, pVerfallsDatum);
		 aktualisiereDrehteller(pDrehtellerNr - 1);
	 }
  }

  /**
   * Gibt die Objekt-Referenz auf die <em> Kasse </em> zurück.
   */
  public Kasse gibKasse() {
    return mKasse;
  }

  /**
   * Wird von der System-Software jedesmal aufgerufen wenn der gelbe Dreh-Knopf
   * gedrückt wird. <br>
   * Die Applikations-Software führt die Drehteller-Anzeigen nach (Warenpreis,
   * Verfallsdatum). <br>
   * Das Ansteuern des Drehteller-Motors übernimmt die System-Software (muss
   * nicht von der Applikations-Software gesteuert werden.). <br>
   * Die System-Software stellt sicher, dass <em> drehen </em> nicht durchgeführt wird
   * wenn ein Fach offen ist.
   */
  public void drehen() {
	  SystemSoftware.dreheWarenInGui();
	  mDrehtellerPosition++;
	  if (mDrehtellerPosition >= 16) {
		  mDrehtellerPosition = 0;
	  }
	  aktualieserePreise();
  }
  
  /**
   *
   */
  private void aktualieserePreise() {
	  for (int i = 0; i < NR_DREHTELLER; i++) {
		  aktualisiereDrehteller(i);
	  }
  }
  
  /**
   * 
   * @param drehtellerNr
   */
  private void aktualisiereDrehteller(int drehtellerNr) {
	  SystemSoftware.output(false);
	  Ware ware = getWareMitPositionen(drehtellerNr, mDrehtellerPosition);
	  if (ware != null) {
		  SystemSoftware.zeigeWareInGui(drehtellerNr + 1, ware.getWarenname(), ware.getAblaufsdatum());
		  SystemSoftware.zeigeWarenPreisAn(drehtellerNr + 1, ware.getPreis());
		  SystemSoftware.zeigeVerfallsDatum(drehtellerNr + 1, SystemSoftware.gibAktuellesDatum().before(ware.getAblaufsdatum()) ? 1 : 2);
	  } else {
		  SystemSoftware.zeigeWareInGui(drehtellerNr + 1, "", SystemSoftware.gibAktuellesDatum());
		  SystemSoftware.zeigeWarenPreisAn(drehtellerNr + 1, 0.0);
		  SystemSoftware.zeigeVerfallsDatum(drehtellerNr + 1, 0);
	  }
	  SystemSoftware.output(true);
  }
  
  /**
   * 
   * @param pDrehtellerNr
   * @param pAktuellePosition
   * @return
   */
  private Ware getWareMitPositionen(int pDrehtellerNr, int pAktuellePosition) {
	  return mDrehteller[pDrehtellerNr].getFach(pAktuellePosition).getWare();
  }

  /**
   * Beim Versuch eine Schiebetüre zu öffnen ruft die System-Software die
   * Methode <code> oeffnen() </code> der Klasse <em> Automat </em> mit der
   * Drehteller-Nummer als Parameter auf. <br>
   * Es wird überprüft ob alles o.k. ist: <br>
   * - Fach nicht leer <br>
   * - Verfallsdatum noch nicht erreicht <br>
   * - genug Geld eingeworfen <br>
   * - genug Wechselgeld vorhanden <br>
   * Wenn nicht genug Geld eingeworfen wurde, wird dies mit
   * <code> SystemSoftware.zeigeZuWenigGeldAn() </code> signalisiert. <br>
   * Wenn nicht genug Wechselgeld vorhanden ist wird dies mit
   * <code> SystemSoftware.zeigeZuWenigWechselGeldAn() </code> signalisiert. <br>
   * Wenn o.k. wird entriegelt (<code> SystemSoftware.entriegeln() </code>) und
   * positives Resultat zurückgegeben, sonst negatives Resultat. <br>
   * Es wird von der System-Software sichergestellt, dass zu einem bestimmten
   * Zeitpunkt nur eine Schiebetüre offen sein kann.
   * 
   * @param pDrehtellerNr Der Drehteller bei welchem versucht wird die
   *          Schiebe-Türe zu öffnen. <br>
   *          Nummerierung beginnt mit 1 (nicht 0)!
   * @return Wenn alles o.k. <code> true </code>, sonst <code> false </code>.
   */
  public boolean oeffnen(int pDrehtellerNr) {
    if (istFachLeer(pDrehtellerNr -1)) {
    	System.out.print("\n istFachLeer \n");
    	return false;
    }
    if (istVerfallsdatumErreicht(pDrehtellerNr -1)) {
    	System.out.print("\n istVerfallsdatumErreicht \n");
    	return false;
    }
    if (!istGenugGeldEingeworfen(pDrehtellerNr -1)) {
    	SystemSoftware.zeigeZuWenigGeldAn();
    	System.out.print("\n istGenugGeldEingeworfen \n");
    	return false;
    }
    if (!istGenugWechselgeldVorhanden(pDrehtellerNr -1)) {
    	SystemSoftware.zeigeZuWenigWechselGeldAn();
    	System.out.print("\n istGenugWechselgeldVorhanden \n");
    	return false;
    }
    
    mKasse.entferneGeldMuenzseule(getWareMitPositionen(pDrehtellerNr-1, mDrehtellerPosition).getPreis(), !Kasse.DRY_RUN, Kasse.OEFFNEN_MODUS);
    Fach fach = mDrehteller[pDrehtellerNr-1].getFach(mDrehtellerPosition);
    Ware ware = fach.getWare();
    ware.setVerkausdatum(SystemSoftware.gibAktuellesDatum());
    mKasse.getStatistik().erfasseWarenbezug(ware);
    fach.setWare(null);
    
    aktualisiereDrehteller(pDrehtellerNr-1);
    
    SystemSoftware.entriegeln(pDrehtellerNr);
    return true;
    
  }
  
  /**
   *
   * @param pDrehtellerNr
   * @return
   */
  private boolean istFachLeer(int pDrehtellerNr) {
	  if (getWareMitPositionen(pDrehtellerNr, mDrehtellerPosition) == null) {
		  return true;
	  }
	  return false;
  }
  
  /**
   *
   * @param pDrehtellerNr
   * @return
   */
  private boolean istVerfallsdatumErreicht(int pDrehtellerNr) {
	  if (SystemSoftware.gibAktuellesDatum().before(getWareMitPositionen(pDrehtellerNr, mDrehtellerPosition).getAblaufsdatum())) {
		  return false;
	  }
	  return true;
  }
  
  /**
  *
  * @param pDrehtellerNr
  * @return
  */
  private boolean istGenugGeldEingeworfen(int pDrehtellerNr) {
	  return mKasse.istAusreichendGuthabendVorhanden(getWareMitPositionen(pDrehtellerNr, mDrehtellerPosition).getPreis());
  }
  
  /**
   *
   * @param pDrehtellerNr
   * @return
   */
  private boolean istGenugWechselgeldVorhanden(int pDrehtellerNr) {
	  return mKasse.istAusreichendWechselgeldVorhanden(getWareMitPositionen(pDrehtellerNr, mDrehtellerPosition).getPreis());
  }

  /**
   * Gibt den aktuellen Wert aller im Automaten enthaltenen Waren in Franken
   * zurück. <br>
   * Analyse: <br>
   * Abgeleitetes Attribut. <br>
   * 
   * @return Der totale Warenwert des Automaten.
   */
  public double gibTotalenWarenWert() {
	int totWaren = 0;
    for (int i = 0; i < mDrehteller.length; i++) {
    	for (int j = 0; j < MAX_POSIZIONEN; j++) {
    		Ware ware = getWareMitPositionen(i, j);
    		if (ware != null) {
    			totWaren += mKasse.getIntValueMuenze(ware.getPreis());
    		}
    	}
    }
    return mKasse.getDoubleValueMuenze(totWaren);
    
  }

  /**
   * Gibt die Anzahl der verkauften Ware <em> pName </em> seit (>=)
   * <em> pDatum </em> Zahl zurück.
   * 
   * @param pName Der Name der Ware nach welcher gesucht werden soll.
   * @param pDatum Das Datum seit welchem gesucht werden soll.
   * @return Anzahl verkaufter Waren.
   */
  public int gibVerkaufsStatistik(String pName, Date pDatum) {
    return mKasse.getStatistik().berechneAnzahlWaren(pName, pDatum);
  }
  
}
