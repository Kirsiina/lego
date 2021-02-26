package runnables;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import data.Data;

public class ReadColor implements Runnable{

	EV3ColorSensor colorsensor = new EV3ColorSensor(SensorPort.S4);
	
	@Override
	public void run() {
		while (Data.shouldRun) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Data.color = colorsensor.getColorID();
			Data.floodlight = colorsensor.setFloodlight(Data.flcolor);
			
			if (Data.shouldRun == false) {
				colorsensor.close();
			}
		}
		
	}

}
