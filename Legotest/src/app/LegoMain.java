package app;

import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import runnables.MotorA;

public class LegoMain {

	public static void main(String[] args) {
		
		MotorA motorA = new MotorA(MotorPort.A, MotorPort.B); //luodaan olio MotorA-luokasta kutsumalla sen muodostinta, ja l‰hetet‰‰n k‰ytett‰v‰t portit
		
		Thread tMotorA = new Thread(motorA);  //luodaan s‰ie, joka k‰ytt‰‰ oliota
		
		System.out.println("Press any key to start");
		
		Button.waitForAnyPress();
		
		tMotorA.start();  //k‰ynnistet‰‰n s‰ie
		
		motorA.setSpeed(); //oliolla moottoreille nopeus
		
		
		Delay.msDelay(1000);
		
		motorA.stopMotor();
		motorA.turnRight();
		
		Delay.msDelay(1000);
		
		motorA.turnLeft();
		
		Delay.msDelay(1000);

		motorA.stopMotor();

	}

}
