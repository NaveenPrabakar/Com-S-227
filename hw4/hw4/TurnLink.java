package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * Models a fixed link with three paths.
 * 
 * @author Naveen Prabakar
 *
 */
public class TurnLink extends AbstractLink {

	/**
	 * Declared Point endPoint1 gets the first point
	 */
	private Point endPoint1;

	/**
	 * Declared Point endPoint2 gets the 2nd point
	 */

	private Point endPoint2;

	/**
	 * Declared Point endPoint3 gets the 3rd point
	 */

	private Point endPoint3;

	public TurnLink(Point endpointA, Point endpointB, Point endpointC) {

		endPoint1 = endpointA;

		endPoint2 = endpointB;

		endPoint3 = endpointC;

		endpoints(endpointA);// protected method endpoints is called to collect endpoint for Abstractlink

		getpaths(3);// protected method getpaths is called to get number of paths to abstractlink

	}

	/**
	 * gets the connected point for the given point
	 * 
	 * @param point , the given point @return, returns the point that is connected
	 *              to the given point
	 */

	@Override
	public Point getConnectedPoint(Point point) {

		if (point == endPoint1) {

			return endPoint3;

		}

		else if (point == endPoint2) {

			return endPoint1;

		}

		else if (point == endPoint3) {

			return endPoint1;

		}

		return null;

	}
}
