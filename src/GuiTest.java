//==============================================================================
// Project   : Master of Advanced Studies in Software-Engineering 2017
// Modul     : Projektarbeit OO Softwareentwicklung "Warenautomat"
//             Teil: Design&Implementation
// Title     : GUI-Test-Applikation
// Author    : Thomas Letsch
// Tab-Width : 2
/*///===========================================================================
* Description: Demo-Programm zur Anwendung des GUI's des Warenautomaten. 
$Revision    : 1.10 $  $Date: 2017/05/31 11:20:15 $ 
/*///===========================================================================
//       1         2         3         4         5         6         7         8
//345678901234567890123456789012345678901234567890123456789012345678901234567890
//==============================================================================

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import warenautomat.*;

public class GuiTest {
  
  static public void main(String pArgs[]) throws ParseException {

    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);

    Automat automat = new Automat();
    Kasse kasse = automat.gibKasse();
    
    SystemSoftware.erzeugeGUI(automat);
    
    automat.fuelleFach(3, "Mars", 2.00, df.parse("01.01.2009"));
    automat.fuelleFach(7, "Mars", 2.30, df.parse("01.01.2109"));
    
    for(int i = 0; i < 16; i++) {
    	
	if (i == 3 || i == 6) {
		String day = String.format("%02d", (i+1));
		automat.fuelleFach(1, "Mars"+(i+1), i+1, df.parse(day+".01.2100"));
    	automat.fuelleFach(4, "Kinder"+(i+1), Math.round((i+1) / 2.0), df.parse(day+".01.1988"));
	} else {
		String day = String.format("%02d", i+1);
        automat.fuelleFach(1, "Mars"+(i+1), i+1, df.parse(day+".01.2100"));
	    automat.fuelleFach(4, "Twix"+(i+1), Math.round(i+1 / 2.0), df.parse(day+".01.2100"));
		automat.fuelleFach(2, "MarsGenerischAbgelauf", i+1, df.parse(day+".01.2002"));
		automat.fuelleFach(6, "MarsGenerischNichtAbge", i+1, df.parse(day+".01.2032"));
	}
	  automat.drehen();
    }
    
    automat.konfiguriereBestellung("Mars3", 1, 2);
    automat.konfiguriereBestellung("Mars4", 1, 2);
    automat.konfiguriereBestellung("MarsGenerischAbgelauf", 1, 2);
    automat.konfiguriereBestellung("MarsGenerischNichtAbge", 1, 2);
  }

}


