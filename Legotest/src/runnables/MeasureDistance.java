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
	 * parametrin� k�ytett�v� portti.
	 */
	UltraSonicSensor uss = new UltraSonicSensor(SensorPort.S3);

	/**
	 * S�ikeen k�ytt�m� run metodi, jossa asetetaan Data-luokan distance-
	 * muuttujalle sensorista saatava et�isyysarvo.
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
