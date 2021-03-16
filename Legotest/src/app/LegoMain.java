package app;

import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import runnables.ColorSensor;
import runnables.MeasureDistance;
import runnables.MotorA;
import sensors.UltraSonicSensor;

public class LegoMain {

	public static void main(String[] args) {
		
		log("Program starts"); //kirjoitetaan lokiin ohjelman käynnistys
		
		//MeasureDistance measuredistance = new MeasureDistance();
		ColorSensor colorsensor = new ColorSensor(SensorPort.S4); //luodaan olio ColorSensor-luokasta, jolle mï¿½ï¿½ritellï¿½ï¿½n portti
		MotorA motorA = new MotorA(MotorPort.A, MotorPort.B); //luodaan olio MotorA-luokasta kutsumalla sen muodostinta, ja lï¿½hetetï¿½ï¿½n kï¿½ytettï¿½vï¿½t portit

		//luodaan säikeet, joka käyttää olioita
		Thread tMotorA = new Thread(motorA);  
		Thread tColorSensor = new Thread(colorsensor);
		//Thread tMeasureDistance = new Thread(measuredistance);
		
		System.out.println("Press any key to start");
		
		Button.waitForAnyPress();
		
		// käynnistetään säikeet
		tColorSensor.start(); // tutkitaan sensorista saatavaa väriä
		tMotorA.start();  //liikutaan eteenpäin
		//tMeasureDistance.start();
		

		Button.waitForAnyPress();
		motorA.stopMotor();
		

	}

	private static void log(String msg) {
		System.out.println("log>\t" + msg);
		
	}

}
