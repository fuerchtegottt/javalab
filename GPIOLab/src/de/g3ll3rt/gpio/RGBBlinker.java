package de.g3ll3rt.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


/**
 * Beispiel für Farbwechseldiode
 * <img src="./doc-files/RGBDiode01.png" />
 */
public class RGBBlinker {
	public static void main(String[] args) throws InterruptedException {

		System.out.println("Blinker V1.5... gestartet");

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

		int counter = 60; // countdown in sek.

		do {
			counter = counter - 1;
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
		} while (counter > 0);

		gpio.shutdown();

		System.out.println("Programm beendet");
	}
}
