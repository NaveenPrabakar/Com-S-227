package hw4;

import api.Crossable;

import api.Point;
import api.PointPair;
import api.PositionVector;

/**
 * Models a link with a minimum of 2 to a maximum of 6 paths. The following
 * figure shows 6 paths.
 * 
 * @author Naveen Prabakar
 *
 */
public class MultiSwitchLink extends MultiFixedLink {
	/**
	 * Declared PointPair array variable a gets the list of point pairs
	 */
	private PointPair[] a;

	/**
	 * Creates a new MultiSwitchLink. The given array of point pairs describes the
	 * connection. Each PointPair indicates where the train comes from and goes to.
	 * 
	 * @param connections array of point pairs
	 */

	public MultiSwitchLink(PointPair[] connections) {

		super(connections);// inherits the methods from MultiFixedLink

		a = connections;

	}

	/**
	 * Swaps a point pair with another given the following index
	 * 
	 * @param pointPair the pair that's going to swap in
	 * @param index     the index the pair is getting swapped
	 */
	public void switchConnection(PointPair pointPair, int index) {

		a[index] = pointPair;
	}

}
