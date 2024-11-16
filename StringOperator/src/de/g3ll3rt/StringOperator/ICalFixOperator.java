package de.g3ll3rt.StringOperator;

/**
 * @author chg
 * sample operator implementation
 *
 */
public class ICalFixOperator implements StringOperationBase {

	@Override
	public String execute(String output) throws Exception {
		StringBuffer strBufferOut = new StringBuffer();
		final String CR_LF = new String("\n");
		
		String[] outList = output.split("\\r\\n");
		String length = outList.length + "";
		int counter = 0;
		
		for(int i = 0; i < outList.length; i++) {
			if(outList[i].startsWith("DTEND") && (outList[i].endsWith("T000000"))){
				counter = counter + 1;
				
				if (i > 0) {
					int prevIndex = i - 2;
					//String startHour = outList[prevIndex].substring(17, 19);
					String startHour = outList[prevIndex].substring(36, 38);
					System.out.println( prevIndex + " " + outList[prevIndex]);
					String endHour = addHourToHourString(startHour, 1);
					
					String oldLine = outList[i];
					outList[i] = outList[i].replace("T00", "T" + endHour);
					outList[i] = outList[i] + "Z";
					
					System.out.println(startHour + "->" + endHour );
					//System.out.println(oldLine + " --> " + outList[i]);
				}
			}
			strBufferOut.append(outList[i]);
			strBufferOut.append(CR_LF);
			
		}
		return strBufferOut.toString();
	}

	@Override
	public String getTitle() {
      return "ICal - Fixer";
	}

	@Override
	public String getDefaultOutputExtension() {
		// TODO Auto-generated method stub
		return "TXT";
	}
	
	public String addHourToHourString(String hour, int hourOffset) {
		Integer hourInt;
		
	try {	
		hourInt = Integer.valueOf(hour);
    } catch (NumberFormatException e) {
       hourInt = 0;
    }
	
	   Integer newHourInt = hourInt + hourOffset;
	   
	   //24h boundary (dont apply to dates > 11PM )
	   if(newHourInt > 23) {
		   newHourInt = 23;
	   }
	   
	   if(newHourInt < 10) {
		   return "0" + newHourInt;
	   } else {
		   return "" + newHourInt;
	   }
	}

	
	public static void main(String[] args){
		new StringOperatorGUI(new ICalFixOperator());
	}

}
