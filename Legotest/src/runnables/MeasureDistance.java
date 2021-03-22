package runnables;


import data.Data;
import lejos.hardware.port.SensorPort;
import sensors.UltraSonicSensor;

/**
 * @author Jenny, Jenna, Kirsi
 * 
 * MeasureDistance toteuttaa Runnable-rajapinnan Ultra Sonic Sensorille.
 */

public class MeasureDistance implements Runnable {
	
	/**
	 * Luodaan olio UltraSonicSensor luokasta, ja annetaan muodostimelle 
	 * parametrinä käytettävä portti.
	 */
	UltraSonicSensor uss = new UltraSonicSensor(SensorPort.S3);

	/**
	 * Säikeen käyttämä run metodi, jossa asetetaan Data-luokan distance-
	 * muuttujalle sensorista saatava etäisyysarvo.
	 */
	@Override
	public void run() {
		
		while(Data.shouldRun) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        Data.distance = uss.getRange();
		}
	}

}
