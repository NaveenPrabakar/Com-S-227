package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * Models a link that connects a single path to nothing.
 * @author Naveen Prabakar
 *
 */

public class DeadEndLink extends AbstractLink {
	
	/**
	 * Deadendlink runs through AbstractLink
	 */
	public DeadEndLink() {
		
		endpoints(null);//protected method setA is called to indicate the endpoints are null
		getpaths(1);
		
	}
}
