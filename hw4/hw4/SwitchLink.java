package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * Models a switchable link with three paths. A boolean turn determines which
 * path trains take. By default turn is set to false
 * 
 * @author Naveen Prabakar
 * 
 */
public class SwitchLink extends AbstractLink {

	private Point endPoint1;

	private Point endPoint2;

	private Point endPoint3;

	private boolean getsetturn;

	/**
	 * The given endpoints correspond to the paths as labeled below.
	 * 
	 * @param endpointA point1
	 * @param endpointB point2
	 * @param endpointC point3
	 */

	public SwitchLink(Point endpointA, Point endpointB, Point endpointC) {

		endPoint1 = endpointA;

		endPoint2 = endpointB;

		endPoint3 = endpointC;

		endpoints(endpointA);

		getpaths(3);// protected method setA is called to get the points to abstractlink

		// protected method setD is called to true to indicate this subclass was called

	}

	/**
	 * Updates the link to turn or not turn. The turn cannot be modified (do
	 * nothing) when the train is currently in (entered but not exited) the
	 * crossing.
	 * 
	 * @param turn true or false depending on the turn
	 */
	public void setTurn(boolean turn) {

		getsetturn = turn;

		// protected method setF is called to collect setTurn boolean in Abstractlink
	}

	/**
	 * gets the connected point for the given point
	 * 
	 * @param point , the given point @return, returns the point that is connected
	 *              to the given point
	 */
	@Override
	public Point getConnectedPoint(Point point) {

		if (getsetturn) {

			if (point == endPoint1) {

				return endPoint3;

			}

			else if (point == endPoint2) {

				return endPoint1;

			}

			else if (point == endPoint3) {

				return endPoint1;

			}
		}

		else {

			if (point == endPoint1) {

				return endPoint2;

			}

			else if (point == endPoint2) {
				return endPoint1;

			}

			else if (point == endPoint3) {

				return endPoint1;

			}
		}

		return null;

	}

}
