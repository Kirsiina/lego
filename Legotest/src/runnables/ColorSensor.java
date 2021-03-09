package runnables;

import data.Data;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;

public class ColorSensor extends EV3ColorSensor implements Runnable{
	
	private final static int PATH_COLOR = Data.color;
	
	//niin kauan kuin Data.shouldrun on arvoltaan tosi, pyöritetään followPath-metodia
	@Override
	public void run() {
		while (Data.shouldRun)
		{			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			
			followPath();
			
			if (!followPath()) {
				Data.colorDetected = false;	
			}
			
			else {
				Data.colorDetected = true;
			}


		}
		
	}
														
	// luokan muodostin, joka kutsuu yläluokan muodostinta
	public ColorSensor(Port port) {
		super(port);
	}
	
	// tutkitaan, onko sensorista haettava väri sama kuin viivan väri
	public boolean followPath() {
		
		return getColorID() == PATH_COLOR;
	}


}
