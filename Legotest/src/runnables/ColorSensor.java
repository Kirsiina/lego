package runnables;

import data.Data;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

/**
 * @author Jenny, Jenna, Kirsi
 * 
 * ColorSensor-luokka muodostaa muodostaa yhteyden porttiin ja hakee sensorista
 * arvoja v‰lill‰ 0-1, joita se k‰ytt‰‰ viivan seurantaan. ColorSensor totetuttaa
 * myˆs Runnable-rajapinnan.
 */

public class ColorSensor implements Runnable{
	
	
	/**
	 * Luodaan muuttuja EV3ColorSensor-luokasta, ja toinen float-tyyppinen muuttuja,
	 * johon voi tallentaa useampia arvoja
	 */
	EV3ColorSensor	sensor;
	float[]		sample;
	
	/**
	 * Run-metodi, jossa asetetaan sensorin valo p‰‰lle, haetaan sensorista saatava arvo
	 * ja asetetaan se Data-luokan color-muuttujan arvoksi.
	 */
	@Override
	public void run() {
		while (Data.shouldRun)
		{			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
				
			setRedMode();
			getRed();
			Data.color = sample[0];
			
		}
		
	}
														
	/**
	 * Luokan muodostin, joka saa parametrin‰ portin.
	 * @param port
	 */
	public ColorSensor(Port port) {
		sensor = new EV3ColorSensor(port);

	}
	
	/**
	 * Asettaa sensor-muuttujan EV3ColorSensor-luokalle.
	 * @return sensor
	 */
	public EV3ColorSensor getSensor()
	{
		return sensor;
	}
	

	/**
	 * Asetetaan sensorin valo p‰‰lle, valolle v‰ri, ja alustetaan
	 * sample-muuttuja.
	 */
	public void setRedMode()
	{
		sensor.setFloodlight(Color.RED);
		sensor.setFloodlight(true);
		sensor.setCurrentMode("Red");
		sample = new float[sensor.sampleSize()];
	}
	
	/**
	 * Haetaan sensorista palautuva arvo ja sijoitetaan se sample-
	 * muuttujan indeksiin 0, jonka j‰lkeen se palautetaan.
	 * @return sample[0]
	 */
	public float getRed()
	{
		sensor.fetchSample(sample, 0);
		return sample[0];
		
	}
	
	/**
	 * Asetetaan sensorin valo p‰‰lle.
	 * @param on
	 */
	public void setFloodLight(boolean on)
	{
		sensor.setFloodlight(on);
	}
	

	/**
	 * Asetetaan valolle v‰ri. Parametrin‰ Color id-arvo.
	 * @param valon v‰ri
	 */
	public void setFloodLight(int color)
	{
		sensor.setFloodlight(color);
	}


}
