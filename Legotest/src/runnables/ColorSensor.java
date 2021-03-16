package runnables;

import data.Data;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class ColorSensor implements Runnable{
	
	EV3ColorSensor	sensor;
	float[]		sample;
	
	
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
														
	// luokan muodostin
	public ColorSensor(Port port) {
		sensor = new EV3ColorSensor(port);

	}
	
	public EV3ColorSensor getSensor()
	{
		return sensor;
	}
	

	
	public void setRedMode()
	{
		sensor.setFloodlight(Color.RED);
		sensor.setFloodlight(true);
		sensor.setCurrentMode("Red");
		sample = new float[sensor.sampleSize()];
	}
	
	public float getRed()
	{
		sensor.fetchSample(sample, 0);
		return sample[0];
		
	}
	
	public void setFloodLight(boolean on)
	{
		sensor.setFloodlight(on);
	}
	
	/**
	 * Set floodlight default led color.
	 * @param color Color id value from Color object.
	 */
	public void setFloodLight(int color)
	{
		sensor.setFloodlight(color);
	}
	
	/**
	* Map color integer to name.
	* @param color Color id value.
	* @return String with color name.
	*/
	public static String colorName(int color)
	{
		switch (color)
		{
			
			case Color.RED:
				return "Red";	

		}
		
		return "";
	}


}
