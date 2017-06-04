package warenautomat;


import warenautomat.SystemSoftware;

/**
 * Die Kasse verwaltet das eingenommene Geld sowie das Wechselgeld. <br>
 * Die Kasse hat fünf Münz-Säulen für: <br>
 * - 10 Rappen <br>
 * - 20 Rappen <br>
 * - 50 Rappen <br>
 * - 1 Franken <br>
 * - 2 Franken <br>
 */
public class Kasse {
	
  private Muenzeule[] mMuenzeule;
  
  final static int CAPACITY_MUENZEULE = 100;
  final static double VALUE_MUENZEN[] =  { 0.10, 0.20, 0.50, 1.00, 2.00 };
  private double mEinwurfBetrag;

  /**
   * Standard-Konstruktor. <br>
   * Führt die nötigen Initialisierungen durch.
   */
  public Kasse() {
	int totMuenezen = VALUE_MUENZEN.length;
	mMuenzeule = new Muenzeule[totMuenezen];
	for (int i = 0; i < totMuenezen; i++) {
		mMuenzeule[i] = new Muenzeule(CAPACITY_MUENZEULE, VALUE_MUENZEN[i], 0);
	}
	mEinwurfBetrag = 0.0;
  }

  /**
   * Diese Methode wird aufgerufen nachdem das Personal beim Verwalten des
   * Wechselgeldbestand die Münzart und die Anzahl der Münzen über die
   * Tastatur eingegeben hat 
   * (siehe Use-Case "Wechselgeldbestand (Münzbestand) verwalten").
   * 
   * @param pMuenzenBetrag Der Betrag der Münzart in Franken.
   * @param pAnzahl Die Anzahl der Münzen. Bei der Entnahme von Münzen als
   *                entsprechender negativer Wert.
   * @return Anzahl der Münzen welche hinzugefügt resp. entnommen werden (bei
   *         Entnahme als negativer Wert). <br>
   *         Im Normalfall entspricht dieser Wert dem Übergabeparameter 
   *         <code>pAnzahl</code>. <br> 
   *         Er kann kleiner sein falls beim Hinzufügen in der Münzsäule zu 
   *         wenig Platz vorhanden ist oder wenn bei der Entnahme ein grössere 
   *         Anzahl angegeben wurde als tatsächlich in der Münzsäule vorhanden 
   *         ist. <br>
   *         Wenn ein nicht unterstützter Münzbetrag übergeben wurde: -200
   */
  public int verwalteMuenzbestand(double pMuenzenBetrag, int pAnzahl) {
    
    return 0; // TODO
    
  }

  /**
   * Diese Methode wird aufgerufen nachdem das Personal beim Geldauffüllen den
   * Knopf "Bestätigen" gedrückt hat
   * (siehe Use-Case "Wechselgeldbestand (Münzbestand) verwalten"). <br>
   * Verbucht die Münzen gemäss dem vorangegangenen Aufruf der Methode 
   * <code>verwalteMuenzbestand()</code>.
   */
  public void verwalteMuenzbestandBestaetigung() {
    
    // TODO
    
  }
 
  /**
   * Diese Methode wird aufgerufen wenn ein Kunde eine Münze eingeworfen hat. <br>
   * Führt den eingenommenen Betrag entsprechend nach. <br>
   * Stellt den nach dem Einwerfen vorhandenen Betrag im Kassen-Display dar. <br>
   * Eingenommenes Geld steht sofort als Wechselgeld zur Verfügung. <br>
   * Die Münzen werden von der Hardware-Kasse auf Falschgeld, Fremdwährung und
   * nicht unterstützte Münzarten geprüft, d.h. diese Methode wird nur
   * aufgerufen wenn ein Münzeinwurf soweit erfolgreich war. <br>
   * Ist die Münzsäule voll (d.h. 100 Münzen waren vor dem Einwurf bereits darin
   * enthalten), so wird mittels
   * <code> SystemSoftware.auswerfenWechselGeld() </code> unmittelbar ein
   * entsprechender Münz-Auswurf ausgeführt. <br>
   * Hinweis: eine Hardware-Münzsäule hat jeweils effektiv Platz für 101 Münzen.
   * 
   * @param pMuenzenBetrag Der Betrag der neu eingeworfenen Münze in Franken.
   * @return <code> true </code>, wenn er Einwurf erfolgreich war. <br>
   *         <code> false </code>, wenn Münzsäule bereits voll war.
   */
  public boolean einnehmen(double pMuenzenBetrag) {
	int totMuenezen = VALUE_MUENZEN.length;
	for (int i = 0; i < totMuenezen; i++) {
		if (getIntValueMuenze(mMuenzeule[i].getMuenzart()) == getIntValueMuenze(pMuenzenBetrag)) {
			return checkAndFillAndShow(mMuenzeule[i], 1);
		}
	}
    return false;
  }
  
  /**
   * 
   * @param mMuenzeule
   * @param pAmount
   * @return
   */
   private boolean checkAndFillAndShow(Muenzeule pMuenzeule, int pAmount) {
	   if (pMuenzeule.fuegeMunzenHinzu(pAmount)) {
		   aktualisiereBetrag(pMuenzeule, pAmount);
		   return true;
	   }
	   return false;
   }
   
   /**
    *
    * @param pMuenzeule
    */
   private void aktualisiereBetrag(Muenzeule pMuenzeule, int pAmount) {
	   int eingefuegtesGeld = getIntValueMuenze(pMuenzeule.getMuenzart()) * pAmount;
	   mEinwurfBetrag = getDoubleValueMuenze(getIntValueMuenze(mEinwurfBetrag) + eingefuegtesGeld);
	   SystemSoftware.zeigeBetragAn(mEinwurfBetrag);
   }

/**
   *
   * @param muenze
   * @return
   */
  private int getIntValueMuenze(double muenze) {
	  return (int) Math.round(muenze * 100);
  }
  
  /**
   * 
   * @param muenze
   * @return
   */
  private double getDoubleValueMuenze(int muenze) {
	  return muenze / 100.0;
  }

  /**
   * Bewirkt den Auswurf des Restbetrages.
   */
  public void gibWechselGeld() {
    
    // TODO
    
  }

  /**
   * Gibt den Gesamtbetrag der bisher verkauften Waren zurück. <br>
   * Analyse: Abgeleitetes Attribut.
   * 
   * @return Gesamtbetrag der bisher verkauften Waren.
   */
  public double gibBetragVerkaufteWaren() {
    
    return 0.0; // TODO
    
  }

}
