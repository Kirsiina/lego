package runnables;


import data.Data;
import lejos.hardware.port.SensorPort;
import sensors.UltraSonicSensor;


public class MeasureDistance implements Runnable {
	
	UltraSonicSensor uss = new UltraSonicSensor(SensorPort.S3);

	@Override
	public void run() {
		// TODO Auto-generated method stub
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
