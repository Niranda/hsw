import lejos.nxt.LCD;

/**
 * Die Ausf�hrbare Klasse Weglaufen ben�tigt die Klassen Robot, Feld und
 * ReciveThread um richtig zu Funktionieren. Sie Steuert den Roboter mithilfe
 * der Battle Cam Software, sodass er vor einem anderen Roboter fliehen kann.
 * 
 * @author Paul Weichbrodt, Tom Riske, Tobias Baade
 * @version 10.01.2012
 * 
 */
public class Weglaufen {
	/**
	 * Die Main-Methode erzeugt und startet den ReciveThread und initialisiert
	 * eine Instanz der Klasse Robot. Anschlie�end wartet er bis der
	 * ReciveThread erste Koordinaten empfangen hat und beginnt dann mit dem
	 * Weglaufen.
	 * 
	 * @param args
	 *            Es werden keine Parameter ben�tigt.
	 */
	public static void main(String[] args) throws Exception {
		ReciveThread rt = new ReciveThread();
		rt.start();
		Robot robot = new Robot(rt);
		while (!robot.feld.istBereit()) {
			Thread.sleep(50);
			LCD.clear();
			LCD.drawString("Wartet", 0, 0);
		}
		robot.feld.aktualisiereGegnerKoords();
		while (rt.getStatus() == ReciveThread.CONNECTED) {
			robot.weglaufen();
		}
	}
}
