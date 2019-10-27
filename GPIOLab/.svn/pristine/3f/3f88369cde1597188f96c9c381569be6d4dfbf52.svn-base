package de.g3ll3rt.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * LED an GPIO 1 blinkt 5 Sekunden
 * 
 * @author C. Gellert
 */
public class Blinker {
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("Blinker V1.5... gestartet");
        
        // GPIO Controller initialisieren
        final GpioController gpio = GpioFactory.getInstance();
        
        // provision gpio pin #01 as an output pin and turn on
        final GpioPinDigitalOutput pin01 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED01", PinState.HIGH);
        
        
        pin01.setShutdownOptions(true, PinState.LOW);
        
        Thread.sleep(5000);
        gpio.shutdown();
        
        System.out.println("Programm beendet");
    }
}