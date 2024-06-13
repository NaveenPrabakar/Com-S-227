package hw4;

import api.Crossable;
import api.Point;
import api.PointPair;
import api.PositionVector;

/**
 * Class that models a train simulation.
 * 
 * @author Naveen Prabakar
 */
public abstract class AbstractLink implements Crossable {
	/**
	 * Declared Point variable endPoint1:  Defines endpoint1
	 */
	private Point endPoint1;

	/**
	 * Declared PointPair variable array paths:  collects the array of point pairs
	 */

	private PointPair paths[];

	/**
	 * Declared int variable number: Collects the number of paths
	 */

	private int number;

	/**
	 * Declared boolean variable enter:  Becomes true or false depending on whether
	 * the train is crossing or not
	 */

	private boolean enter;

	/**
	 * Declared boolean variable VectorA:  Gets pointA at a certain positionvector
	 */

	private Point VectorA;

	/**
	 * Declared boolean variable VectorB:  gets pointB at a certain positionvector
	 */

	private Point VectorB;

	/**
	 * Declared point variable geththepoint:  gets the connected point at a specific point
	 */

	private Point getthepoint;

	/**
	 * Models a link between paths. This class implements Crossable which extends
	 * Traversable.
	 */
	public AbstractLink() {

		endPoint1 = null;

		number = 0;

		enter = false;

		paths = null;

		VectorA = null;

		VectorB = null;

	}

	/**
	 * Shifts a set of points to the next set of points in the path
	 * 
	 * @param positionVector positions of endpoints
	 */

	public void shiftPoints(PositionVector positionVector) {

		if (endPoint1 == null && paths == null) {// shiftpoints won't run if deadlink is called

			return;

		}

		trainEnteredCrossing();

		if (enter) {

			VectorA = getConnectedPoint(positionVector.getPointB());

			Vectorendpoint();// protected method called to get the next point in the path

			positionVector.setPointA(VectorA);

			positionVector.setPointB(VectorB);

		}

		trainExitedCrossing();

	}

	/**
	 * Gets the connected point that's connected to the given point
	 * 
	 * @Param point It is the connected to the point that is to return
	 * @return returns the connected point given by the parameter
	 */

	public Point getConnectedPoint(Point point) {

		if (endPoint1 == null && paths == null) {

			return null;

		}

		if (endPoint1 == null && paths != null) {

			Multi(point);

			return getthepoint;

		}

		return null;
	}

	/**
	 * Becomes true if the train has entered crossing
	 */

	public void trainEnteredCrossing() {

		enter = true;

	}
	/**
	 * Becomes false if the train has exited the crossing
	 */

	public void trainExitedCrossing() {

		enter = false;

	}

	/**
	 * Gets the number of paths present
	 * 
	 * @return returns the number of paths
	 */

	public int getNumPaths() {
		// TODO Auto-generated method stub
		return number;
	}

	/**
	 * Gets an endpoint from subclass
	 * 
	 * @param endpoint1 first endpoint
	 */

	protected void endpoints(Point endpoint1) {

		endPoint1 = endpoint1;
	}

	/**
	 * gets the array of point pairs
	 * 
	 * @param connections array of point pairs
	 */
	protected void getPath(PointPair[] connections) {

		paths = connections;
	}

	/**
	 * gets the number of paths in a certain subclass
	 * 
	 * @param num number of paths
	 */
	protected void getpaths(int num) {

		number = num;

	}

	/**
	 * gets the connected point for multi
	 * 
	 * @param point the given point
	 */
	protected void Multi(Point point) {

		for (int i = 0; i < paths.length; i++) {

			if (paths[i].getPointA() == point) {

				getthepoint = paths[i].getPointB();

			}

		}

		for (int i = 0; i < paths.length; i++) {

			if (paths[i].getPointB() == point) {

				getthepoint = paths[i].getPointA();

			}
		}

	}

	/**
	 * Gets the next point in the path for shiftpoints
	 */
	protected void Vectorendpoint() {

		if (VectorA == VectorA.getPath().getLowpoint()) {

			VectorB = VectorA.getPath().getPointByIndex(1);
		}

		else if (VectorA == VectorA.getPath().getHighpoint()) {

			VectorB = VectorA.getPath().getPointByIndex(VectorA.getPointIndex()-1);
		}
	}

}
