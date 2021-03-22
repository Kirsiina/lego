package runnables;

import data.Data;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

/**
 * @author Jenny, Jenna, Kirsi
 * 
 * MotorAB-luokka muodostaa yhdeyden kumpaakin moottoriin,
 * liikuttaa sit‰ viivaa seuraten saamalla sensoreista arvoja
 * Data-luokan kautta ja toteuttaa Runnable-rajapinnan.
 */

public class MotorAB implements Runnable { 
	
	/** 
	 * M‰‰ritt‰v‰t luokan muuttujat, k‰ytet‰‰n moottorien kontrolloimiseen
	 * motorA on vasen moottori, motorB on oikea moottori.
	 */
	private EV3LargeRegulatedMotor motorA;
	private EV3LargeRegulatedMotor motorB;
	
	/**
	 * Asetetaan moottoreille Data-luokasta saatava nopeus.
	 */
	private final static int aSpeed = Data.speed;
	private final static int bSpeed = Data.speed;
	
	
	/**
	 * Muuttuja yksitt‰isen moottorin nopeuden alentamiseen
	 */
	private static int lowerSpeed = 150; 
	
	/**
	 * Luokan muodostin, jolla p‰‰st‰‰n k‰siksi moottoreihin
	 * antamalla parametrein‰ portit.
	 */
	public MotorAB(Port A, Port B) {  

		motorA = new EV3LargeRegulatedMotor(A); 
		motorB = new EV3LargeRegulatedMotor(B);	
	}
	
	
	/**
	 * Run-metodi liikuttaa robottia ensisijaisesti eteenp‰in
	 * viivan reunaa seuraten niin kauan, kun Data-luokan shouldRun-muuttuja 
	 * on tosi. Ensin asetetaan nopeus ja liikutaan eteenp‰in. Data-luokan 
	 * color-muuttujan arvo m‰‰ritt‰‰, kumpaan suuntaan k‰‰nnyt‰‰n. Data-luokan 
	 * distance-muuttuja m‰‰ritt‰‰, tehd‰‰nkˆ v‰istˆliikkeit‰. T‰t‰ ominaisuutta 
	 * ei olla p‰‰sty kunnolla testaamaan.
	 */
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
	
	/**
	 * Kummatkin moottorit kulkee eteenp‰in.
	 */
	public void moveForward() {
		
		motorA.forward();
		motorB.forward(); 
	}
	
	/**
	 * Asetetaan moottoreille nopeus Data-luokasta saatavalla arvolla.
	 */
	public void setSpeed() {
		
		motorA.setSpeed(aSpeed);  
		motorB.setSpeed(bSpeed);
	}
	
	/**
	 * Pys‰ytet‰‰n moottorit, ja annetaan Data-luokan shouldRun-muuttujalle
	 * arvoksi false.
	 */
	public void stopMotor() {
		
		Data.shouldRun = false;
		
		motorA.stop();
		motorB.stop();
	}
	
	/**
	 * K‰‰nnyt‰‰n oikealle hidastamalla oikeaa (B) moottoria
	 */
	public void turnRight() { 
		
		int newspeed = bSpeed-lowerSpeed;
		motorB.setSpeed(newspeed);
		motorA.setSpeed(aSpeed);
		
	}
	
	/**
	 * K‰‰nnyt‰‰n vasemmalle hidastamalla vasenta (A) moottoria
	 */
	public void turnLeft() {
		
		int newspeed = aSpeed-lowerSpeed;
		motorA.setSpeed(newspeed);
		motorB.setSpeed(bSpeed);
		
	}
	
	/**
	 *  K‰‰nnyt‰‰n tiukasti oikealle peruuttamalla oikealla moottorilla,
	 * kun vasen menee eteenp‰in.
	 */
	public void sharpTurnRight(){
		motorB.backward();
		motorA.forward();
	}

	/**
	 * K‰‰nnyt‰‰n tiukasti vasemmalle peruuttamalla vasemmalla moottorilla,
	 * kun oikea menee eteenp‰in.
	 */
	public void sharpTurnLeft(){
		motorA.backward();
		motorB.forward();
	}
	
	/**
	 * Ajatus siit‰, miten esteit‰ voisi v‰ist‰‰. Ei olla testattu t‰ysin toimivaksi.
	 */
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

	/**
	 * Peruutetaan kummallakin moottorilla.
	 */
	public void moveBackward(){
		
		motorA.backward();
		motorB.backward();
	}


}
