import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;

/**
 * @author Tom Riske, Paul Weichbrodt, Tobias Baade
 * @version 17.10.2011
 *
 * Ausf�hrbare Klasse: 
 * 		Die Klasse erm�glicht es mit dem LEGO NXT Roboter (leJOS NXJ 0.9) 
 *		einer schwarzen Linie zu folgen. Spezifikationen des Roboters:
 * 		linker Motor an Motorport A
 * 		rechter Motor an Motorport B
 * 		Lichtsensor an Sensorport 3
 * 
 */
public class LinieFolgen {
	public static LightSensor lightSensor = new LightSensor(SensorPort.S3);
	public static Motor linkerMotor = Motor.A;
	public static Motor rechterMotor = Motor.B;
	public static final int MAX_LIGHT = 440; 				// 0-1023
	public static final int MIN_LIGHT = 250; 				// 0-1023
	public static final int MAXGESCHWINDIGKEIT = 700;		//in Grad pro Sekunde
	public static final int SUCHGESCHWINDIGKEIT = 195;		//in Grad pro Sekunde
	public static int geschwindigkeit;						//aktuelle Geschwindigkeit
	public static boolean letzteRichtung;					//Richtung in der zuletzt die Linie gefunden wurde
	
	public static void main(String[] args) throws InterruptedException {
		while(true){
			geradeFahren();
			if(geschwindigkeit < MAXGESCHWINDIGKEIT)		//wenn aktuelle Geschwindigkeit kleiner als Maximalgeschwindigkeit
				beschleunigen(); 					 		//dann beschleunige 
			if(!istLinie()){								//wenn Roboter ist nicht auf Linie
				setGeschwindigkeit(SUCHGESCHWINDIGKEIT);	//dann verringere die Geschwindigkeit
				linieSuchen();								//und suche die Linie
			}
		}
	}
	/*
	 * �berpr�ft ob Lichtsensor �ber der Linie ist
	 */
	public static boolean istLinie(){
		boolean out = false;
		int lightValue = lightSensor.getNormalizedLightValue();	// holt aktuellen Lichtwert vom Sensor
		if(lightValue < MAX_LIGHT && lightValue > MIN_LIGHT ) 	// pr�ft ob Lichtwert im Bereich der schwarzen Linie ist
			out = true; 										//ist auf Linie
		else
			out = false; 										//ist nicht auf Linie
		return out;
	}
	/*
	 * l�sst den Roboter vorw�rts eine gerade Linie Fahren
	 */
	public static void geradeFahren(){
		linkerMotor.setSpeed(geschwindigkeit); 
		rechterMotor.setSpeed(geschwindigkeit);
		linkerMotor.forward();
		rechterMotor.forward();
	}
	/*
	 * l�sst den Roboter nach links drehen
	 */
	public static void linksDrehen(){
		linkerMotor.setSpeed(geschwindigkeit);
		rechterMotor.setSpeed(geschwindigkeit);
		linkerMotor.backward();
		rechterMotor.forward();
	}
	/*
	 * l�sst den Roboter nach rechts drehen
	 */
	public static void rechtsDrehen(){
		linkerMotor.setSpeed(geschwindigkeit);
		rechterMotor.setSpeed(geschwindigkeit);
		linkerMotor.forward();
		rechterMotor.backward();
	}
	/*
	 * l�sst den Roboter die Linie suchen
	 */
	public static void linieSuchen() throws InterruptedException{
		int zaehler = 0;										//Anzahl der Suchdurchl�ufe in eine Richtung
		int suchdurchlaeufe = 124;								//maximale Anzahl der Suchdurchl�ufe in eine Richtung
		while(!istLinie()){										//Solange die Linie nicht gefunden wurde
			if(letzteRichtung)									//	Dreht sich in die Richtung in der
				rechtsDrehen();									//	zuletzt eine Linie gefunden wurde
			else
				linksDrehen();
			zaehler = 0;										//	zahler auf 0 setzen
			while(!istLinie()){ 								//	Solange Linie nicht gefunden in die Richtung drehen
				Thread.sleep(4);
				zaehler ++;										//	  zaehler erh�hen
				if(zaehler >= suchdurchlaeufe)					//	  wenn der zaehler gr��er ist als die maximale Anzahl der Durchl�ufe
				break;											//		Schleifendurchlauf abbrechen
			}
			if(!istLinie()){									//	wenn Linie nicht gefunden
				suchdurchlaeufe *= 2;							//	  maximale Durchlaufzahl verdoppeln
				setLetzteRichtung(!getLetzteRichtung());		//	  Suchrichtung �ndern
			}
		}
		for(int i = 0; i <= 1; i++)								//Nachlaufzeit wenn Linie gefunden 
			Thread.sleep(5);
	}
	/*
	 * erh�t die aktuelle Geschwindigkeit
	 */
	private static void beschleunigen(){
		geschwindigkeit += 3;
	}
	/*
	 * gibt die Richtung zur�ck in der der Roboter zuletzt gesucht hat
	 */
	private static boolean getLetzteRichtung() {
		return letzteRichtung;
	}
	/*
	 * �ndert den Wert der Variable 'letzteRichtung'
	 */
	private static void setLetzteRichtung(boolean lRichtung) {
		letzteRichtung = lRichtung;
	}
	/*
	 * �ndert den Wert der Variable 'geschwindigkeit'
	 */
	private static void setGeschwindigkeit(int geschw) {
		geschwindigkeit = geschw;
	}

}
