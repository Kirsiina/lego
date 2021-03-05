package app;

import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import runnables.ColorSensor;
import runnables.MotorA;

public class LegoMain {

	public static void main(String[] args) {
		
		log("Program starts"); //kirjoitetaan lokiin ohjelman k�ynnistys
		
		ColorSensor colorsensor = new ColorSensor(SensorPort.S4); //luodaan olio ColorSensor-luokasta, jolle m��ritell��n portti
		MotorA motorA = new MotorA(MotorPort.A, MotorPort.B); //luodaan olio MotorA-luokasta kutsumalla sen muodostinta, ja l�hetet��n k�ytett�v�t portit


		Thread tMotorA = new Thread(motorA);  //luodaan s�ie, joka k�ytt�� oliota
		Thread tColorSensor = new Thread(colorsensor);
		
		System.out.println("Press any key to start");
		
		Button.waitForAnyPress();
		
		// k�ynnistet��n s�ikeet
		tMotorA.start();  //liikutaan eteenp�in
		tColorSensor.start(); // tutkitaan sensorista saatavaa v�ri�
		
		
		//niin kauan kuin olion followPath-metodi palauttaa arvon true, annetaan moottoreille nopeus. Jos palautuu false, moottorit sammuvat.
		while (colorsensor.followPath()) {
			motorA.setSpeed();
		}
		motorA.stopMotor();
		


	}

	private static void log(String msg) {
		System.out.println("log>\t" + msg);
		
	}

}
