package runnables;

import data.Data;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class MotorA implements Runnable { //ja B nyt toistaiseksi, voidaan luultavasti jakaa omiin luokkiinsa
	
	// m‰‰ritt‰v‰t luokan muuttujat, k‰ytet‰‰n moottrien kontrolloimiseen
	private EV3LargeRegulatedMotor motorA; //vasen moottori
	private EV3LargeRegulatedMotor motorB; //oikea moottori
	
	private final static int speed = 200; //muuta myˆhemmin Data-luokan arvoksi, jos mahdollista. T‰llˆin nopeutta voisi vaihdella tilanteen mukaan
	
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
			
			moveForward();		
		}
		
	}
	
	//kummatkin moottorit kulkee eteenp‰in
	public void moveForward() {
		
		motorA.forward();
		motorB.forward(); 
	}
	
	//asetetaan nopeus
	public void setSpeed() {
		
		motorA.setSpeed(speed);  
		motorB.setSpeed(speed);
	}
	
	//pys‰ytet‰‰n moottori
	public void stopMotor() {
		
		Data.shouldRun = false;
		
//		motorA.stop(); //korvattu yll‰olevalla
//		motorB.stop();
	}
	
	//k‰‰nnyt‰‰n oikealle
	public void turnRight() { //jos erilliset luokat moottorille, niin jaetaan k‰skyt sen mukaan? Eli samat metodit, mutta k‰skytet‰‰n moottoreita omissa luokissaan
		
		motorA.forward(); 
		motorB.backward();
		
	}
	
	//k‰‰nnyt‰‰n vasemmalle
	public void turnLeft() {
		
		motorA.backward();
		motorB.forward();
		
	}



}
