package sensors;

//import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
//import lejos.robotics.Color;
import lejos.robotics.ColorIdentifier;
import lejos.robotics.SampleProvider;

import data.Data;

public class ColorSensor implements ColorIdentifier{ //V‰risensoriluokka implementoi v‰rintunnistusrajapinnan

	EV3ColorSensor colorsensor;
	SampleProvider sp;
	float[]	colorsample;
	
	//Port port = LocalEV3.get().getPort("S4");
	
	public ColorSensor(Port port) {   //luokan muodostin? Jos joku tiet‰‰ paremmin niin kommentoikaa :)
		
		colorsensor = new EV3ColorSensor(port);
		sp = colorsensor.getColorIDMode();
		colorsample = new float[sp.sampleSize()];
	}
	
	public EV3ColorSensor getSensor() {
		
		return colorsensor;
	}

	
	@Override
	public int getColorID() {  //hakee v‰rin sensorista ja tallentaa sen arrayhin
		
		sp.fetchSample(colorsample, 0);
		return (int) colorsample[0];
		

	}
	
	//Alla olevia ei siis tarvitakkaan. 
//	public boolean isFloodlightOn() { //tarkistetaan, onko valo p‰‰ll‰
//		
//		return true;
//	}
	
	
//	public boolean setFloodlight(int flcolor) { // m‰‰ritet‰‰n valon v‰ri
//
//		if (Data.shouldRun){
//			Data.flcolor = 6;
//			flcolor = Data.flcolor;
//			Data.floodlight =  true;
//		}
//		
//		else {
//			flcolor = 0;  //t‰m‰n pit‰isi sulkea valo
//			Data.floodlight = false;
//		}
//		
//		return Data.floodlight;
//		
//			
//	}
	
	
	public void close() { // sulkee sensorin ja sen valon
		colorsensor.close();
		Data.floodlight = false;
	}
	
	
}
