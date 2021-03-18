package runnables;

import data.Data;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class MotorA implements Runnable { 
	
	// m‰‰ritt‰v‰t luokan muuttujat, k‰ytet‰‰n moottorien kontrolloimiseen
	private EV3LargeRegulatedMotor motorA; //vasen moottori
	private EV3LargeRegulatedMotor motorB; //oikea moottori
	
	//kummallekin moottorille oma nopeus
	private final static int aSpeed = Data.speed;
	private final static int bSpeed = Data.speed;
	
	
	//yksitt‰isen moottorin nopeuden alentamiseen
	private static int lowerSpeed = 150; 
	
	//luokan muodostin, jolla p‰‰st‰‰n k‰siksi moottoreihin.
	public MotorA(Port A, Port B) {  
									
		//Oliot, joilla portit m‰‰ritelty
		motorA = new EV3LargeRegulatedMotor(A); 
		motorB = new EV3LargeRegulatedMotor(B);	
	}
	
	
	//Toteuttaa Runnable-luokan:
	@Override
	public void run() {  
		while (Data.shouldRun) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			
			setSpeed();
			moveForward();
			
			if (Data.distance < 0.1) {
				setSpeed();
				moveBackward();
				Delay.msDelay(1500);
				avoidObstacle();
				if (Data.color <= 0.25) {
					sharpTurnRight();
				}
			}
			
			while (Data.color >= 0.6) {
				turnLeft();
			}

			while (Data.color < 0.25) {
				turnRight();
			}
				
		}
		
	}
	
	//kummatkin moottorit kulkee eteenp‰in
	public void moveForward() {
		
		motorA.forward();
		motorB.forward(); 
	}
	
	//asetetaan moottoreille nopeus Data-luokasta saatavalla arvolla
	public void setSpeed() {
		
		motorA.setSpeed(aSpeed);  
		motorB.setSpeed(bSpeed);
	}
	
	//pys‰ytet‰‰n moottori
	public void stopMotor() {
		
		Data.shouldRun = false;
		
		motorA.stop();
		motorB.stop();
	}
	
	//k‰‰nnyt‰‰n oikealle hidastamalla oikeaa (B) moottoria
	public void turnRight() { 
		
		int newspeed = bSpeed-lowerSpeed;
		motorB.setSpeed(newspeed);
		motorA.setSpeed(aSpeed);
		
	}
	
	//k‰‰nnyt‰‰n vasemmalle hidastamalla vasenta (A) moottoria
	public void turnLeft() {
		
		int newspeed = aSpeed-lowerSpeed;
		motorA.setSpeed(newspeed);
		motorB.setSpeed(bSpeed);
		
	}
	
	
	public void sharpTurnRight(){
		motorB.backward();
		motorA.forward();
	}

	public void sharpTurnLeft(){
		motorA.backward();
		motorB.forward();
	}
	
	public void avoidObstacle() {
		stopMotor();
		sharpTurnRight();
		Delay.msDelay(700);
		setSpeed();
		moveForward();
		Delay.msDelay(1500);
		stopMotor();
		sharpTurnLeft();
		Delay.msDelay(700);
		setSpeed();
		moveForward();
		Delay.msDelay(1500);
		sharpTurnLeft();
		Delay.msDelay(700);
		setSpeed();
		moveForward();
		
	}

	
	public void moveBackward(){
		
		motorA.backward();
		motorB.backward();
	}


}
