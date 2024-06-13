package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * Models a fixed link with three paths
 * 
 * @author Naveen Prabakar
 *
 */
public class StraightLink extends SwitchLink {

	/**
	 * Creates a new TurnLink. The given endpoints correspond to the paths as
	 * labeled below.
	 * 
	 * @param endpointA point1
	 * @param endpointB point2
	 * @param endpointC point3
	 */

	public StraightLink(Point endpointA, Point endpointB, Point endpointC) {

		super(endpointA, endpointB, endpointC); // Inherits from switchLink

	}

}
