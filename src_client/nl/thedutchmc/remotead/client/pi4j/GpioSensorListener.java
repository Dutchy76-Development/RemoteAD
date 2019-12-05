package nl.thedutchmc.remotead.client.pi4j;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class GpioSensorListener implements GpioPinListenerDigital {

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

        System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
        
        boolean isRunning = false;
        
        if(event.getState().isHigh() && !isRunning) {
        	isRunning = true;
        	final SensorTriggered sensorTriggered = new SensorTriggered();
        	try {
				sensorTriggered.onTrigger();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        } else if(event.getState().isLow()) {
        	//TODO if state goes low
        }
 	}
}
