package runnables;

import data.Data;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class MotorA implements Runnable { //ja B nyt toistaiseksi, voidaan luultavasti jakaa omiin luokkiinsa
	
	// m‰‰ritt‰v‰t luokan muuttujat, k‰ytet‰‰n moottrien kontrolloimiseen
	private EV3LargeRegulatedMotor motorA; //oikea moottori
	private EV3LargeRegulatedMotor motorB; //vasen moottori
	
	private final static int speed = 200; //muuta myˆhemmin Data-luokan arvoksi, jos mahdollista. T‰llˆin nopeutta voisi vaihdella tilanteen mukaan
	
	public MotorA(Port A, Port B) {  //luokan muodostin, jolla p‰‰st‰‰n k‰siksi moottoreihin. Voidaan uudelleen nimet‰ luokan kanssa selkeyden vuoksi, 
									// jos ei hajoteta moottoreita kahteen eri luokkaan
		
		motorA = new EV3LargeRegulatedMotor(A); //Oliot, joilla portit m‰‰ritelty
		motorB = new EV3LargeRegulatedMotor(B);
		
//		motorA.setSpeed(speed);  siirretty omaan metodiin, jotta k‰ynnistys onnistuu mainiss‰
//		motorB.setSpeed(speed);
		
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
			
			motorA.forward();
			motorB.forward();
			
		}
		
	}
	
//	public void moveForward() { //siirretty runnable-metodiin
//		
//		motorA.forward();
//		motorB.forward(); 
//	}
	
	public void setSpeed() {
		
		motorA.setSpeed(speed);  
		motorB.setSpeed(speed);
	}
	
	public void stopMotor() {
		
		Data.shouldRun = false;
		
//		motorA.stop(); //korvattu yll‰olevalla
//		motorB.stop();
	}
	
	public void turnRight() { //jos erilliset luokat moottorille, niin jaetaan k‰skyt sen mukaan? Eli samat metodit, mutta k‰skytet‰‰n moottoreita omissa luokissaan
		
		motorA.backward();
		motorB.forward();
		
	}
	
	public void turnLeft() {
		
		motorA.forward();
		motorB.backward();
		
	}



}
