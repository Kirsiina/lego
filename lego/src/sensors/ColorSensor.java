package sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorIdentifier;
import lejos.robotics.SampleProvider;

import data.Data;

public class ColorSensor implements ColorIdentifier{

	EV3ColorSensor sensor;
	SampleProvider sp;
	float[]	colorsample;

	
	public ColorSensor(Port port) {   //luokan muodostin? Jos joku tiet‰‰ paremmin niin kommentoikaa :)
		
		sensor = new EV3ColorSensor(port);
		sp = sensor.getColorIDMode();
		colorsample = new float[sp.sampleSize()];
	}
	
	public EV3ColorSensor getSensor() {
		
		return sensor;
	}

	
	@Override
	public int getColorID() {  //hakee v‰rin sensorista ja tallentaa sen arrayhin
		
		sp.fetchSample(colorsample, 0);
		return (int) colorsample[0];
	}
	
	
	public boolean isFloodLightOn() {
		
		return true;

	}
	
	public boolean setFloodlight(int flcolor) { // tarkoituksena siis asettaa sensorin valo p‰‰lle kun moottorit on p‰‰ll‰, ja m‰‰ritet‰‰n valon v‰ri
		
		if (Data.shouldRun == true){
			flcolor = Data.flcolor;
			Data.flcolor = Color.WHITE;
			Data.floodlight =  true;
			//isFloodLightOn();
		}
		
		else {
			flcolor = Color.NONE;  //t‰m‰n pit‰isi sulkea valo
			Data.floodlight = false;
		}
		
		return Data.floodlight;
		
			
	}
	
	
	public void close() {
		sensor.close();
		Data.floodlight = false;
	}
	
	
}
