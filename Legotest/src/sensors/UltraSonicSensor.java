package sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.SampleProvider;

/**
 * @author Jenny, Jenna, Kirsi
 * 
 * UltraSonicSensor-luokka muodostaa yhteyden porttiin, hakee 
 * et‰isyysarvoja Ultrasonic-sensorista ja toteuttaa RangeFinder-rajapinnan.
 */
public class UltraSonicSensor implements RangeFinder{
	
	/**
	 * Luokan muuttujien alustus. 
	 */
	EV3UltrasonicSensor sensor;
	SampleProvider		sp;
    float [] 			sample;
		
    
    /** 
     * Luokan muodostin, joka saa parametrin‰ portin.
     * Luodaan olio EV3UltraSonicSensor-luokasta, jonka parametrin‰
     * portti. SampleProvider-rajapinnan muuttuja saa arvokseen sensorista
     * haetun et‰isyyden. Sample-muuttuja saa arvokseen float-tyyppisi‰
     * elementtej‰.
     * @param portti
     */
	public UltraSonicSensor(Port port) {
		sensor = new EV3UltrasonicSensor(port);
		sp = sensor.getDistanceMode();
		sample = new float[sp.sampleSize()];
	}

	/**
	 * Asettaa sensor-muuttujan EV3UltrasonicSensor-luokalle.
	 * @return luokasta luodun muuttujan.
	 */
	public EV3UltrasonicSensor getSensor() {
		return sensor;
	}

	/**
	 * Hakee et‰isyyden esteeseen k‰ytt‰en UltraSonic-sensoria.
	 * @return Et‰isyyden metreiss‰.
	 */
	@Override
	public float getRange() {
		sp.fetchSample(sample, 0);

		return sample[0];
	}

	/**
	 * Hakee et‰isyyden esteeseen k‰ytt‰en UltraSonic-sensoria.
	 * @return Et‰isyyden metreiss‰.
	 */
	@Override
	public float[] getRanges() {
		sp.fetchSample(sample, 0);

		return sample;
	}

	/**
	 * Tarkistaa, onko UltraSonic-sensori p‰‰ll‰.
	 * @return True, jos on, False jos ei.
	 */
	public boolean isEnabled() {
		return sensor.isEnabled();
	}

	/**
	 * Laittaa UltraSonic-sensorin p‰‰lle.
	 */
	public void enable() {
		sensor.enable();
	}

	/**
	 * Laittaa UltraSonic-sensorin pois p‰‰lt‰.
	 */
	public void disable() {
		sensor.disable();
	}

	/**
	 * Sulkee sensorin.
	 */
	public void close() {
		sensor.close();
	}
    	
}

