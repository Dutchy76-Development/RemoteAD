package nl.thedutchmc.remotead.client.pi4j;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class GpioSetup {
	
	public void initGpio() {
		final GpioController gpio = GpioFactory.getInstance();
		
		System.out.println("Initalizing GPIO!");
		
		GpioPinDigitalInput sensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, "sensor", PinPullResistance.PULL_DOWN);
		
		sensor.addListener(new GpioSensorListener());
	}
}
