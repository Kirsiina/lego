package runnables;

import data.Data;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class MotorA implements Runnable { //ja B nyt toistaiseksi, voidaan luultavasti jakaa omiin luokkiinsa
	
	// määrittävät luokan muuttujat, käytetään moottorien kontrolloimiseen
	private EV3LargeRegulatedMotor motorA; //vasen moottori
	private EV3LargeRegulatedMotor motorB; //oikea moottori
	
	//kummallekin moottorille oma nopeus
	private final static int aSpeed = Data.speed;
	private final static int bSpeed = Data.speed;
	
	//private static int wait = 5;
	
	//yksittäisen moottorin nopeuden alentamiseen
	private static int lowerSpeed = 100; 
	
	public MotorA(Port A, Port B) {  //luokan muodostin, jolla päästään käsiksi moottoreihin. Voidaan uudelleen nimetä luokan kanssa selkeyden vuoksi, 
									// jos ei hajoteta moottoreita kahteen eri luokkaan
		
		motorA = new EV3LargeRegulatedMotor(A); //Oliot, joilla portit määritelty
		motorB = new EV3LargeRegulatedMotor(B);	
	}
	
	
	//Toteuttaa Runnable-luokan:
	@Override
	public void run() {  
	while (Data.color >= 0.06 && Data.color <= 0.14) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			
			setSpeed();
			moveForward();
			
			if (Data.color >= 0.19) {
				turnLeft();
			}
			
			if (Data.color <= 0.04) {
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
		
		motorA.stop();
		motorB.stop();
	}
	
	//käännytään oikealle hidastamalla oikeaa (B) moottoria
	public void turnRight() { //jos erilliset luokat moottorille, niin jaetaan käskyt sen mukaan? Eli samat metodit, mutta käskytetään moottoreita omissa luokissaan
		
		int newspeed = bSpeed-lowerSpeed;
		motorB.setSpeed(newspeed);
		motorA.setSpeed(aSpeed);
//		motorA.forward(); //käännytään asettamalla uudet vauhdit?
//		motorB.backward();
//		
	}
	
	//käännytään vasemmalle hidastamalla vasenta (A) moottoria
	public void turnLeft() {
		
		int newspeed = aSpeed-lowerSpeed;
		motorA.setSpeed(newspeed);
		motorB.setSpeed(bSpeed);
//		motorA.backward();
//		motorB.forward();
		
	}



}
