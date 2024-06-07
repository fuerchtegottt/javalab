package de.g3ll3rt.jstinker;

public class JStinkerPlotter {
	private String quoteString;
	final private String CR_LF = new String("\n");
	public JStinkerPlotter( String [] quote) {
		quoteString = getString( quote, 30 );

	}

	private String getString(String[] quote, int limit) {

		String out;
		StringBuffer strBuffer = new StringBuffer();

		for (String element : quote) { 
			strBuffer.append( element);
			strBuffer.append(" ");
		}

		out = strBuffer.toString();

		if( out.length() > limit) {
			out = out.substring(0, limit);
		}

		return out; 
	}
	
	public String getBubble(String quote) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(" ------------------------------------------------------------------  ");
		buffer.append(CR_LF);
		buffer.append("< STINKER: " + quote );
		buffer.append(CR_LF);
		buffer.append(" ------------------------------------------------------------------  ");
		buffer.append(CR_LF);
		buffer.append( "     \\ ");
		
		return buffer.toString();
	}
	
	public String getStinker() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(CR_LF);
		buffer.append("    .-\"\"-.          "); buffer.append(CR_LF);
		buffer.append("   /)    (\\\\        "); buffer.append(CR_LF);
		buffer.append("   ( ' \\' )          "); buffer.append(CR_LF);
		buffer.append("    \\  = /           "); buffer.append(CR_LF);
		buffer.append("     )--(             "); buffer.append(CR_LF);
		return buffer.toString();
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getBubble( this.quoteString));
		buffer.append(getStinker());
		return buffer.toString();
	}
}
