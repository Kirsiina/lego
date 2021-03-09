package runnables;

import data.Data;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class MotorA implements Runnable { //ja B nyt toistaiseksi, voidaan luultavasti jakaa omiin luokkiinsa
	
	// m‰‰ritt‰v‰t luokan muuttujat, k‰ytet‰‰n moottrien kontrolloimiseen
	private EV3LargeRegulatedMotor motorA; //vasen moottori
	private EV3LargeRegulatedMotor motorB; //oikea moottori
	
	//kummallekin moottorille oma nopeus
	private final static int aSpeed = Data.speed;
	private final static int bSpeed = Data.speed;
	
	//yksitt‰isen moottorin nopeuden alentamiseen
	private static int lowerSpeed = 100; 
	
	public MotorA(Port A, Port B) {  //luokan muodostin, jolla p‰‰st‰‰n k‰siksi moottoreihin. Voidaan uudelleen nimet‰ luokan kanssa selkeyden vuoksi, 
									// jos ei hajoteta moottoreita kahteen eri luokkaan
		
		motorA = new EV3LargeRegulatedMotor(A); //Oliot, joilla portit m‰‰ritelty
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
			
			//jos v‰ri‰ ei en‰‰ havaita, k‰‰nnyt‰‰n ensisijaisesti oikealle.
			//Jos v‰ri havaitaan, keskeytet‰‰n while-loop
			while (Data.colorDetected == false) { 
				turnRight();
				
				if (Data.colorDetected) {

					break;
				}
				
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
	public void turnRight() { //jos erilliset luokat moottorille, niin jaetaan k‰skyt sen mukaan? Eli samat metodit, mutta k‰skytet‰‰n moottoreita omissa luokissaan
		
		int newspeed = bSpeed-lowerSpeed;
		motorB.setSpeed(newspeed);
		motorA.setSpeed(aSpeed);
//		motorA.forward(); //k‰‰nnyt‰‰n asettamalla uudet vauhdit?
//		motorB.backward();
//		
	}
	
	//k‰‰nnyt‰‰n vasemmalle hidastamalla vasenta (A) moottoria
	public void turnLeft() {
		
		int newspeed = aSpeed-lowerSpeed;
		motorA.setSpeed(newspeed);
		motorB.setSpeed(bSpeed);
//		motorA.backward();
//		motorB.forward();
		
	}



}
