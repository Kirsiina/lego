package app;

import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import runnables.ColorSensor;
import runnables.MotorA;

public class LegoMain {

	public static void main(String[] args) {
		
		log("Program starts"); //kirjoitetaan lokiin ohjelman käynnistys
		
		ColorSensor colorsensor = new ColorSensor(SensorPort.S4); //luodaan olio ColorSensor-luokasta, jolle määritellään portti
		MotorA motorA = new MotorA(MotorPort.A, MotorPort.B); //luodaan olio MotorA-luokasta kutsumalla sen muodostinta, ja lähetetään käytettävät portit

		//luodaan säikeet, joka käyttää olioita
		Thread tMotorA = new Thread(motorA);  
		Thread tColorSensor = new Thread(colorsensor);
		
		System.out.println("Press any key to start");
		
		Button.waitForAnyPress();
		
		// käynnistetään säikeet
		tMotorA.start();  //liikutaan eteenpäin
		tColorSensor.start(); // tutkitaan sensorista saatavaa väriä

		Button.waitForAnyPress();
		motorA.stopMotor();
		

	}

	private static void log(String msg) {
		System.out.println("log>\t" + msg);
		
	}

}
