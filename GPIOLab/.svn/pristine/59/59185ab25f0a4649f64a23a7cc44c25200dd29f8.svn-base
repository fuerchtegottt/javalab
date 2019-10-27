package de.g3ll3rt.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


/**
 * Beispiel für Farbwechseldiode
 * <img src="./doc-files/RGBDiode01.png" />
 */
public class Button {
	public static boolean leaveProgram = false;
	public static void main(String[] args) throws InterruptedException {

		System.out.println("RGB with Button V1.0... gestartet");

		// GPIO Controller initialisieren
		final GpioController gpio = GpioFactory.getInstance();

		// provision gpio pin #01 as an output pin and turn on
		final GpioPinDigitalOutput pin00 = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_00, "LED00", PinState.HIGH);
		final GpioPinDigitalOutput pin01 = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_01, "LED01", PinState.HIGH);
		final GpioPinDigitalOutput pin02 = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_02, "LED02", PinState.HIGH);

		pin00.setShutdownOptions(true, PinState.LOW);
		pin01.setShutdownOptions(true, PinState.LOW);
		pin02.setShutdownOptions(true, PinState.LOW);

		
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06, PinPullResistance.PULL_DOWN);
        
        // create and register gpio pin listener
        myButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
            	System.out.println("STATE CHANGE");
                leaveProgram = true;
            }
            
        });

		do {
			switch (0 + (int) (Math.random() * 3)) {
			case 0:
				pin00.toggle();
				break;
			case 1:
				pin01.toggle();
				break;
			case 2:
				pin02.toggle();
				break;
			}
			Thread.sleep(1000);
		} while (leaveProgram == false);

		gpio.shutdown();

		System.out.println("Programm beendet");
	}
}
