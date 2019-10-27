package de.g3ll3rt.gpio;

import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

public class LCDSay {

    public final static int LCD_ROWS = 2;
    public final static int LCD_ROW_1 = 0;
    public final static int LCD_ROW_2 = 1;
    public final static int LCD_COLUMNS = 16;
    public final static int LCD_BITS = 4;
    
    public static void main(String args[]) throws InterruptedException {

        // initialize LCD
        final GpioLcdDisplay lcd = new GpioLcdDisplay(LCD_ROWS,          // number of row supported by LCD
                                                LCD_COLUMNS,       // number of columns supported by LCD
                                                RaspiPin.GPIO_25,  // LCD RS pin     (= 26 on RASPY 2)
                                                RaspiPin.GPIO_24,  // LCD strobe pin (= 19 on RASYP 2)
                                                RaspiPin.GPIO_23,  // LCD data bit 1 (= 13 on RASYP 2)
                                                RaspiPin.GPIO_22,  // LCD data bit 2 (= 6 on RASPY 2)
                                                RaspiPin.GPIO_21,  // LCD data bit 3 (= 5 on RASPY 2)
                                                RaspiPin.GPIO_14); // LCD data bit 4 (= 11 on RASPY 2)
        
        
        // clear LCD
        lcd.clear();

        // write line 2 to LCD
        if (args != null){
          if (args.length == 2){             //2-WORD mode
        	  lcd.write(LCD_ROW_1, args[0]);
        	  lcd.write(LCD_ROW_2, args[1]);  
          } else {                           //flow-text mode
              StringBuffer str = new StringBuffer();
              for (int i = 0; i < args.length; i++){
            	  str.append(args[i]);
            	  str.append(" ");
              }  
              lcd.write(LCD_ROW_1, str.toString());
          }
        } else {
          lcd.clear();
        }
    }
}