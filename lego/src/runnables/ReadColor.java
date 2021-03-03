package runnables;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
//import lejos.robotics.Color;
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
			
			colorsensor.setFloodlight(6); //pit‰isi k‰ynnist‰‰ valo ja laittaa se valkoiseksi
			colorsensor.getColorID(); //t‰m‰n pit‰isi hakea arrayhin tallennetun v‰rin
			
			
			if (Data.shouldRun == false) {  //suljetaan v‰risensori, kun ei en‰‰ kuljeta eteenp‰in
				colorsensor.close();
			}
		}
		
		
	}
	
	

}
