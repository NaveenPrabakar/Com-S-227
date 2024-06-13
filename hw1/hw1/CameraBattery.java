package hw1;

/**
 * Will simulate the charging and drainge of a camera battery
 * 
 * @author Naveen Prabakar
 *
 */

public class CameraBattery {

	
	public static final int NUM_CHARGER_SETTINGS = 4; 
	
	public static final double CHARGE_RATE = 2.0;
	
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0; 
	
	/**
	 * Declared variable battery.
	 * Will show the current status in terms of charge
	 */
	private double battery;
	
	/**
	 * Declared variable camera
	 * Will show the charge of the camera depending on if it is
	 * connected or not
	 */
	
	private double camera;
	
	/**
	 * Declared variable cameracap
	 * Will be set to 0 or 1 depending if the camera and battery are
	 * connected
	 */
	
	
	private double cameracap;
	
	/**
	 * Declared variable setting
	 * Records how many times the button is pressed for the external charger
	 */
	
	
	private int setting;
	
	/**
	 * Declared Camerachar
	 * Records how much will be charged into the battery
	 */

	private double Camerachar;
	
	/**
	 * Declared drainagetotal
	 * Records the total amount the battery is drained
	 */
	

	private double drainagetotal;
	
	/**
	 * Declared variable batteryMax
	 * Stores the maximum amount of charge a battery can have
	 */
	
	
	private double batteryMax;
	
	/**
	 * Declared variable powerconsumption
	 * Stores the amount of power consumption
	 */
	
	
	
	private double powerconsumption;
	
	/**
	 * Declared variable drainageMax
	 * Stores the maximum valuable the battery can be drained by
	 */
	
	
	private double drainageMax;
	
	/**
	 * Declared variable externalcap
	 * Stores the value 1 or 0 depending if the external charger is connected
	 * to the battery
	 */

	
	private double externalcap;
	
	/**
	 * Declared variable externalCharge
	 * Stores the amount the externalcharger will charge the battery
	 * 
	 */
	
	
	
	private double externalcharge;
	
	
	
	/**
	 * makes a battery with a set intial charge
	 * @param batteryStartingCharge
	 *       The intial amount of battery charge
	 * 
	 * sets the max amount the battery can be charged up to
	 * @param batteryCapacity
	 *        The max amount to how much the battery can charge
	
	 */
	
	
	
	
	public CameraBattery(double batteryStartingCharge, double batteryCapacity)
	
	{

		battery = batteryStartingCharge;
		batteryMax = batteryCapacity;
		
		powerconsumption = DEFAULT_CAMERA_POWER_CONSUMPTION ;
		
		
		
		
		
	}
	
	/**
	 * Increases the setting up to three before returning to zero per press
	 * 
	 */
	
	public void buttonPress() {
		
		setting += 1;
		
		setting = setting % NUM_CHARGER_SETTINGS;
	}
	
	/**
	 * Charges the battery depending on the amount of minutes
	 * @param minutes
	 *        The amount of minutes the battery will charge
	 * 
	 * @return Camerachar
	 *         Returns the amount the battery will be charged by
	 */
	
	public double cameraCharge(double minutes) {
		
		Camerachar = Math.min(batteryMax, ((minutes * CHARGE_RATE) * cameracap));
		

		
		battery = Math.min(batteryMax,(battery + Camerachar));
		
		camera = Math.max(camera, battery)* cameracap;
		
		
		return Camerachar;
		
	}
	
	/**
	 * Drains the battery depending on the number of minutes
	 * @param minutes
	 *        The amount of minutes the battery was used
	 * @return drainageMax
	 *        Returns the max amount the battery can be drained
	 */
	
	public double drain(double minutes) {
		
		camera = Math.min(camera, battery) * cameracap;
		
		double drainage;
		
		
		
		
		drainage = (powerconsumption * (Math.min(camera, minutes)));
		
		drainagetotal += drainage; 
		
		drainageMax = Math.min(battery,drainage);
		
		
		
	
		
		
		
		
		
	    battery = Math.max(0, (battery - drainageMax));
	   
		camera = Math.min(camera, battery) * cameracap;
		
		return drainageMax;
		
	}
	
	/**
	 * Resets the total drainage count back to zero
	 */
	
	public void resetBatteryMonitor() {
		drainagetotal= 0;
		
	}
	
	
	/**
	 * Returns the max battery capacity
	 * @return batteryMax
	 *         Max capacity of this battery
	 * 
	 */
	
	public double getBatteryCapacity() {
		
		return batteryMax;
	}
	
	/**
	 * returns the current status of the battery's charge
	 * @return battery
	 *         Current charge of the battery
	 */
	
	
	public double getBatteryCharge() {
		
		battery = Math.min(batteryMax, battery);
		
		return battery;

	}
	
	/**
	 * Connects the battery and camera
	 */
    public void moveBatteryCamera() {
		
		camera = battery;
		cameracap = 1;
	}
    /**
     * disconnects the camera or externalcharger from the battery
     */
    
    public void removeBattery() {
    	
    	
    	cameracap = 0;
    	externalcap = 0;
    	
    }
    
    /**
     * returns the current status of the camera's charge
	 * @return camera
	 *         The current charge of the camera
	 * 
	 */
	
	public double getCameraCharge() {
		
		
		return camera;
	
	}
	
	/**
	 * returns the powerconsumption rate
	 * @return powerconsumption
	 *         The rate of how fast the drain would be
	 */
	
	public double getCameraPowerConsumption() {
		
		

		return powerconsumption;
		
		
	}
	
	/**
	 * returns the amount of times the button is pressed
	 * @return setting
	 *         The amount of times the button is pressed
	 */
	
	
	
	public int getChargerSetting() {
		return setting;
	}
	
	/**
	 * returns the total amount the of drainage after reset or starting from 0
	 * @return drainagetotal
	 *         The sum of all the drainges starting from zero/reset
	 * 
	 */
	
	public double getTotalDrain() {
		return drainagetotal;

	}
	
	/**
	 * Connects the battery to the externalcharger
	 */
	
	public void moveBatteryExternal() {
		externalcap = 1;
		
	}
	
	/**
	 * Charges the battery from the external charger depending on number of minutes
	 * @param minutes
	 *        The amount of minutes the external charger will charge the battery
	 * 
	 * returns the total amount the battery is charged
	 * @return externalcharge
	 *         The total amount charged to the battery for the external charger
	 */

	 public double externalCharge(double minutes) {
		 externalcharge = Math.min(batteryMax,  (battery + (minutes * setting) * externalcap * 2.0))- battery;
		 
		 battery = Math.min(batteryMax,  (battery + externalcharge));
		 
		 
		 return externalcharge;
	 }
	 
	 /**
	  * Sets the powerconsumption depending on the rate given
	  * @param cameraPowerConsumption
	  *        How much the drainage rate of the battery will be
	  */
	 
	 public void setCameraPowerConsumption(double cameraPowerConsumption) {
		 powerconsumption = (DEFAULT_CAMERA_POWER_CONSUMPTION * cameraPowerConsumption);
		 
	 }
	
	
	
}




