package runnables;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import data.Data;

//oikean puolen moottorin käskyttäminen

public class RunMotorA implements Runnable{
	
	
	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);

	
	//Toteuttaa Runnable-luokan
	@Override
	public void run() {
		
		while (Data.shouldRun) {
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			
			Data.direction = 1;
			motorA.forward();
			//System.out.println("Motor A moving forward!");
		}
		
	}
	
	public void startMotor() {
		motorA.setPower(50);
		//System.out.println("Set speed for motorA!");
		
	}
	
	public void stopMotor() {
		Data.shouldRun = false;
	
	}

}
