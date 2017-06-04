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
	
  private Muenzseule[] mMuenzseule;
  
  final static int CAPACITY_MUENZEULE = 100;
  final static boolean DRY_RUN = true;

  // immer von klein zu gross!
  final static double VALUE_MUENZEN[] =  { 0.10, 0.20, 0.50, 1.00, 2.00 };

  private double mEinwurfBetrag;

  /**
   * Standard-Konstruktor. <br>
   * Führt die nötigen Initialisierungen durch.
   */
  public Kasse() {
	int totMuenezen = VALUE_MUENZEN.length;
	mMuenzseule = new Muenzseule[totMuenezen];
	for (int i = 0; i < totMuenezen; i++) {
		mMuenzseule[i] = new Muenzseule(CAPACITY_MUENZEULE, VALUE_MUENZEN[i], 0);
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
	for (int i = 0; i < mMuenzseule.length; i++) {
		if (getIntValueMuenze(mMuenzseule[i].gibMuenzart()) == getIntValueMuenze(pMuenzenBetrag)) {
			return checkAndFillAndShow(mMuenzseule[i], 1);
		}
	}
    return false;
  }
  
  /**
   * 
   * @param mMuenzseule
   * @param pAmount
   * @return
   */
   private boolean checkAndFillAndShow(Muenzseule pMuenzseule, int pAmount) {
	   if (pMuenzseule.fuegeMunzenHinzu(pAmount)) {
		   aktualisiereBetrag(pMuenzseule, pAmount);
		   return true;
	   }
	   return false;
   }
   
   /**
    *
    * @param pMuenzseule
    */
   private void aktualisiereBetrag(Muenzseule pMuenzseule, int pAmount) {
	   int eingefuegtesGeld = getIntValueMuenze(pMuenzseule.gibMuenzart()) * pAmount;
	   mEinwurfBetrag = getDoubleValueMuenze(getIntValueMuenze(mEinwurfBetrag) + eingefuegtesGeld);
	   SystemSoftware.zeigeBetragAn(mEinwurfBetrag);
   }

/**
   *
   * @param muenze
   * @return
   */
   public int getIntValueMuenze(double muenze) {
	  return (int) Math.round(muenze * 100);
  }
  
  /**
   * 
   * @return
   */
  public double getEinwurfBetrag() {
	  return mEinwurfBetrag;
  }
  
  /**
   * 
   * @param muenze
   * @return
   */
  public double getDoubleValueMuenze(int muenze) {
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
  
  /**
   * 
   * @param betrag
   * @param pDryRun
   * @return
   */
  private double entferneGeldMuenzseule(double betrag, boolean pDryRun) {
	int preisInt = getIntValueMuenze(betrag);
	int pEinwurfBetrag = getIntValueMuenze(mEinwurfBetrag);
	if (pEinwurfBetrag == 0) {
	    return 0;
	}
    for (int i = mMuenzseule.length - 1; i >= 0; i--) {
    	int gesamtWertSeule = gibWertMuenzeuleinInt(mMuenzseule[i]);
    	if (gesamtWertSeule > 0 && (preisInt / gesamtWertSeule) >= 1) {
    		preisInt = preisInt - gesamtWertSeule;
    	}
    	if (preisInt == 0) {
    		break;
    	}
	}
    int originalBetrag = getIntValueMuenze(betrag);
    if (preisInt == originalBetrag) {
    	return betrag;
    }
	return getDoubleValueMuenze(pEinwurfBetrag - getIntValueMuenze(betrag) + preisInt);
  }
  
  /**
   *
   * @param pMuenzseule
   * @return
   */
  private int gibWertMuenzeuleinInt(Muenzseule pMuenzseule) { 
	  return getIntValueMuenze(pMuenzseule.gibMuenzart()) * pMuenzseule.gibAnzahlMuenzen();
  }

  /**
   *
   * @param betrag
   * @return
   */
  public boolean istAusreichendWechselgeldVorhanden(double betrag) {
	double restGeldNachBezahlung = entferneGeldMuenzseule(betrag, DRY_RUN);
	if (getIntValueMuenze(restGeldNachBezahlung) == 0) {
		return true;
	}
	double restGeldNachWechselgeld = entferneGeldMuenzseule(restGeldNachBezahlung, DRY_RUN);
	return !(getIntValueMuenze(restGeldNachWechselgeld) == getIntValueMuenze(restGeldNachBezahlung));
  }

}
