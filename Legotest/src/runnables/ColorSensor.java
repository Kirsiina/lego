package runnables;

import data.Data;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

public class ColorSensor implements Runnable{
	
	EV3ColorSensor	sensor;
	float[]		sample;
	
	
//	private final static float PATH_COLOR = Data.color;
	
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
		sensor.setCurrentMode(Color.RED);
		sample = new float[sensor.sampleSize()];
	}
	
	public float getRed()
	{
		sensor.fetchSample(sample, 0);
		return sample[0];
		
	}
	



}
