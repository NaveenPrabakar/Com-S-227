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
public class MultiFixedLink extends AbstractLink {

	/**
	 * Creates a new MultiFixedLink. The given array of point pairs describes the
	 * connection. Each PointPair indicates where the train comes from and goes to.
	 * 
	 * @param connections array of point pairs
	 */

	public MultiFixedLink(PointPair[] connections) {

		getPath(connections);

		getpaths(connections.length);// calls protected method getPaths to collect the number of paths to Abstract
										// link
	}

}
