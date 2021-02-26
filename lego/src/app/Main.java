package app;

import lejos.hardware.Button;
import lejos.hardware.Sound;
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
		
		motorA.start();
		motorB.start();
		readcolor.start();
		
		runMotorA.startMotor();
		runMotorB.startMotor();
		
		Delay.msDelay(2000);
		
		runMotorA.stopMotor();
		runMotorB.stopMotor();
		
		
		
	}

}
