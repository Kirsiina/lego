package app;

import data.Data;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.robotics.Color;
import lejos.utility.Delay;
import runnables.*;

public class Main {

	public static void main(String[] args) {
		

		RunMotorA runMotorA = new RunMotorA();
		RunMotorB runMotorB = new RunMotorB();
		
		ReadColor readColor = new ReadColor();
		

		Thread motorA = new Thread(runMotorA);
		Thread motorB = new Thread(runMotorB);
		Thread readcolor = new Thread(readColor);
		
		
		System.out.println("Press any key to start");
		

		Sound.beepSequenceUp();
		

		Button.waitForAnyPress();
		
		motorA.start();  //säie alkaa
		motorB.start();
		readcolor.start();
		
		
		runMotorA.startMotor(); //olio kutsuu metodia, jolla nopeus
		runMotorB.startMotor();
		
		//Delay.msDelay(4000);
		
		if (Data.color == Color.BROWN) {
		runMotorA.stopMotor();
		runMotorB.stopMotor();
		}
		
		
	}

}
