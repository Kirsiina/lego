package runnables;

import data.Data;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;


//vasemman puolen moottorin käskyttäminen

public class RunMotorB implements Runnable{
	
	UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

	@Override
	public void run() {
		
		while (Data.shouldRun) {
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			
			Data.direction = 1;
			motorB.forward();
			//System.out.println("Motor A moving forward!");
		}
		
	}
	
	public void startMotor() {
		motorB.setPower(50);
		//System.out.println("Set speed for motorA!");
		
	}
	
	public void stopMotor() {
		Data.shouldRun = false;
	}
}
