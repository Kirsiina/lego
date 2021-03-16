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
		
		log("Program starts"); //kirjoitetaan lokiin ohjelman k�ynnistys
		
		//MeasureDistance measuredistance = new MeasureDistance();
		ColorSensor colorsensor = new ColorSensor(SensorPort.S4); //luodaan olio ColorSensor-luokasta, jolle m��ritell��n portti
		MotorA motorA = new MotorA(MotorPort.A, MotorPort.B); //luodaan olio MotorA-luokasta kutsumalla sen muodostinta, ja l�hetet��n k�ytett�v�t portit

		//luodaan s�ikeet, joka k�ytt�� olioita
		Thread tMotorA = new Thread(motorA);  
		Thread tColorSensor = new Thread(colorsensor);
		//Thread tMeasureDistance = new Thread(measuredistance);
		
		System.out.println("Press any key to start");
		
		Button.waitForAnyPress();
		
		// k�ynnistet��n s�ikeet
		tColorSensor.start(); // tutkitaan sensorista saatavaa v�ri�
		tMotorA.start();  //liikutaan eteenp�in
		//tMeasureDistance.start();
		

		Button.waitForAnyPress();
		motorA.stopMotor();
		

	}

	private static void log(String msg) {
		System.out.println("log>\t" + msg);
		
	}

}
