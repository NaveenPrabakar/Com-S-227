package hw4;

import api.Point;
import api.PositionVector;

/**
 * Models a link between exactly two paths. When the train reaches the endpoint
 * of one of the paths it passes to the endpoint of the other path next.
 * 
 * @author Naveen Prabakar
 */
public class CouplingLink extends StraightLink {

	/**
	 * Constructor calls to protected methods in Abstractlink and runs through
	 * AbstractLink
	 * 
	 * @param endPoint1 point 1
	 * @param endPoint2 point 2
	 */

	public CouplingLink(Point endPoint1, Point endPoint2) {

		super(endPoint1, endPoint2, null); // Inherits method from StraightLink

	}

	/**
	 * Gets the number of Paths
	 */

	@Override
	public int getNumPaths() {
		// TODO Auto-generated method stub
		return 2;
	}

}