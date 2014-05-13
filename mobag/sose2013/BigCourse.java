package Course;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class BigCourse {
	/*
	 * Klassenvariablen
	 */
	// Motoren
	public static NXTRegulatedMotor L, R;
	// Touchsensor
	public static TouchSensor EXIT;
	// Lichtsensor
	public static LightSensor LIGHT;

	// Letzte Drehrichtung
	public static int last_dir;
	// Letzte Position (auf oder neben Linie)
	public static int last_pos;

	// Sucht nach der schwarzen Linie
	public static SearchThread searchThread;

	/*
	 * Klassen-Konstanten
	 */

	// Grenze zwischen wei� und schwarz
	public static int LIGHT_BORDER = 36;
	// Geschwindigkeit: vorw�rts fahren
	public static int FORWARD_SPEED = 500;

	// links
	public static int DLEFT = 0;
	// rechts
	public static int DRIGHT = 1;
	// weiss
	public static int POS_WHITE = 0;
	// schwarz
	public static int POS_BLACK = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Press any button to start");
		Button.waitForAnyPress();
		init();
		moveForward();
		// Solange der Abbruch-Knopf nicht gedr�ckt wurde
		while (!EXIT.isPressed()) {
			// Falls sich der Zustand ge�ndert hat
			if (changedState()) {
				// Falls der Roboter auf der Linie ist
				if (isOnLine()) {
					// SearchThread falls er existiert stoppen
					if (searchThread != null) {
						searchThread.stopSearching();
					}
					// Vorw�rtsbewegung einleiten
					moveForward();
				} else {
					// Falls der Roboter neben der Linie ist
					// Vorw�rtsbewegung stoppen
					stopMoving();
					// Neuen Such-Thread anlegen und starten
					searchThread = new SearchThread(L, R);
					searchThread.start();
				}
			}
		}
		// Suche anhalten
		searchThread.stopSearching();
	}

	/*
	 * gleichzeituges Anhalten der beiden Motoren
	 * Parameter:
	 * 				keine
	 * R�ckgabe:
	 * 				keine
	 */
	private static void stopMoving() {
		L.stop(true);
		R.stop(true);
	}

	/*
	 * gibt an, ob sich der Zustand ge�ndert hat, also ob ein Wechsel
	 * von schwarz zu wei� oder umgekehrt stattgefunden hat
	 * Parameter:
	 * 				keine
	 * R�ckgabe:
	 * 				true  - Zustand ge�ndert
	 * 				false - Zustand nicht ge�ndert
	 */
	private static boolean changedState() {
		// Falls auf Linie
		if (isOnLine()) {
			// Falls die letzte Postion schwarz
			if (last_pos == POS_BLACK) {
				return false;
			} else {
				// Falls die letzte Postion schwarz
				last_pos = POS_BLACK;
				return true;
			}
		} else {
			// Falls neben Linie
			// Falls die letzte Postion wei�
			if (last_pos == POS_WHITE) {
				return false;
			} else {
				// Falls die letzte Postion wei�
				last_pos = POS_WHITE;
				return true;
			}
		}
	}

	/*
	 * Vorw�rtsbewegung einleiten
	 * Parameter:
	 * 				keine
	 * R�ckgabe:
	 * 				keine
	 */
	private static void moveForward() {
		stopMoving();
		L.forward();
		R.forward();
		L.setSpeed(FORWARD_SPEED);
		R.setSpeed(FORWARD_SPEED);
	}

	/*
	 * gibt an, ob sich der Roboter auf der Linie befindet
	 * Parameter:
	 * 				keine
	 * R�ckgabe:
	 * 				true  - auf Linie
	 * 				false - nicht auf Linie
	 */
	public static boolean isOnLine() {
		return LIGHT.readValue() < LIGHT_BORDER;
	}

	/*
	 * Komponenten instanzieren
	 * Parameter:
	 * 				keine
	 * R�ckgabe:
	 * 				keine
	 */
	private static void init() {
		// TODO Auto-generated method stub
		// Touchsensor, Lichtsensor instanzieren
		EXIT = new TouchSensor(SensorPort.S2);
		LIGHT = new LightSensor(SensorPort.S1);
		// Motoren zuweisen
		L = Motor.C;
		R = Motor.A;
		// letzte Richtung festlegen
		last_dir = DLEFT;
		// aktuelle Postion ermitteln und zuweisen
		last_pos = isOnLine() ? POS_BLACK : POS_WHITE;
	}

}
