package app;

import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import runnables.ColorSensor;
import runnables.MeasureDistance;
import runnables.MotorAB;

/**
 * @author Jenny, Jenna, Kirsi
 * 
 * P‰‰luokka, josta s‰ikeiden avulla k‰skytet‰‰n muita luokkia.
 */
public class LegoMain {

	public static void main(String[] args) {

		/** 
		 * Luodaan oliot luokista k‰ytt‰en luokan muodostinta, ja annetaan parametriksi 
		 * k‰ytett‰v‰ portti, poikkeuksena MeasureDistance-luokka.
		 */
		MeasureDistance measuredistance = new MeasureDistance();
		ColorSensor colorsensor = new ColorSensor(SensorPort.S4); 
		MotorAB motorA = new MotorAB(MotorPort.A, MotorPort.B); 

		/**
		 * Luodaan s‰ikeet, joka k‰ytt‰‰ olioita
		 */
		Thread tMotorA = new Thread(motorA);  
		Thread tColorSensor = new Thread(colorsensor);
		Thread tMeasureDistance = new Thread(measuredistance);
		
		/**
		 * Odotetaan, ett‰ k‰ytt‰j‰ painaa mit‰ tahansa n‰pp‰int‰.
		 */
		System.out.println("Press any key to start");
		Button.waitForAnyPress();
		
		/**
		 * K‰ynnistet‰‰n s‰ikeet. Ensin tutkitaan sensorista saatavaa v‰ri‰, toisena
		 * liikutaan eteenp‰in ja kolmantena tutkitaan mahdollisia esteit‰.
		 */
		tColorSensor.start();
		Delay.msDelay(1500);
		tMotorA.start(); 
		tMeasureDistance.start();
		
		/**
		 * Mik‰ tahansa napp‰imen painallus pys‰ytt‰‰ moottorin, ja samalla
		 * koko ohjelman.
		 */
		Button.waitForAnyPress();
		motorA.stopMotor();
		

	}


}
