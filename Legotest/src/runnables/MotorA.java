package runnables;

import data.Data;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class MotorA implements Runnable { 
	
	// määrittävät luokan muuttujat, käytetään moottorien kontrolloimiseen
	private EV3LargeRegulatedMotor motorA; //vasen moottori
	private EV3LargeRegulatedMotor motorB; //oikea moottori
	
	//kummallekin moottorille oma nopeus
	private final static int aSpeed = Data.speed;
	private final static int bSpeed = Data.speed;
	
	
	//yksittäisen moottorin nopeuden alentamiseen
	private static int lowerSpeed = 100; 
	
	//luokan muodostin, jolla päästään käsiksi moottoreihin.
	public MotorA(Port A, Port B) {  
									
		//Oliot, joilla portit määritelty
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
			
			while (Data.color >= 0.16) {
				turnLeft();
			}
			
			while (Data.color < 0.06) {
				turnRight();
			}
	
			
				
		}
		
	}
	
	//kummatkin moottorit kulkee eteenpäin
	public void moveForward() {
		
		motorA.forward();
		motorB.forward(); 
	}
	
	//asetetaan moottoreille nopeus Data-luokasta saatavalla arvolla
	public void setSpeed() {
		
		motorA.setSpeed(aSpeed);  
		motorB.setSpeed(bSpeed);
	}
	
	//pysäytetään moottori
	public void stopMotor() {
		
		Data.shouldRun = false;
		
		motorA.stop();
		motorB.stop();
	}
	
	//käännytään oikealle hidastamalla oikeaa (B) moottoria
	public void turnRight() { 
		
		int newspeed = bSpeed-lowerSpeed;
		motorB.setSpeed(newspeed);
		motorA.setSpeed(aSpeed);
		
	}
	
	//käännytään vasemmalle hidastamalla vasenta (A) moottoria
	public void turnLeft() {
		
		int newspeed = aSpeed-lowerSpeed;
		motorA.setSpeed(newspeed);
		motorB.setSpeed(bSpeed);
		
	}



}
