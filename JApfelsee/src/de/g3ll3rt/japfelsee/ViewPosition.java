package de.g3ll3rt.japfelsee;

public class ViewPosition {
	private double posX;
	private double posY;
	private double posZ;
	private int    angle;
	private ViewPosition(double posX, double posY, double posZ, int angle) {
		
	}
	
	public static ViewPosition getNewViewPosition(double posX, double posY, double posZ, int angle) throws JApfelseeException {
		
		validatePosData(posX, posY, posZ, angle);
		return new ViewPosition(posX, posY, posZ, angle);
	}
	
	public static void validatePosData(double posX, double posY, double posZ, int angle) throws JApfelseeException {
		//Positionsdaten validieren
	}
	
	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public double getPosZ() {
		return posZ;
	}

	public int getAngle() {
		return angle;
	}
}
