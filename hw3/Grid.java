package hw3;

import api.Tile;

/**
 * Represents the game's grid.
 * @author Naveen Prabakar
 */
public class Grid {
	
	/**
	 * Declared int variable width1
	 * Defines the width of the grid
	 */
	private int width1;
	
	/**
	 * Declared int variable height
	 * Defines the height of the grid
	 */
	
	private int height1;
	
	/**
	 * Declared Tile 2-D array arr
	 * Creates the grid for the game
	 */
	private Tile[][] games;
	
	
	/**
	 * Creates a new grid.
	 * 
	 * @param width  -number of columns
	 * @param height -number of rows
	 */
	public Grid (int width, int height) {
		
		width1 = width;
		
		height1 = height;
		
		games = new Tile[width1][height1];
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	@Override
	public String toString() {
		String str = "";
		for (int y=0; y<getHeight(); y++) {
			if (y > 0) {
				str += "\n";
			}
			str += "[";
			for (int x=0; x<getWidth(); x++) {
				if (x > 0) {
					str += ",";
				}
				str += getTile(x, y);
			}
			str += "]";
		}
		return str;
	}
	
	
	/**
	 * Get the grid's width.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width1;
	}
	
	/**
	 * Get the grid's height.
	 * 
	 * @return height
	 */
	public int getHeight() {
		
		return height1;
		
	}
	
	
	/**
	 * Gets the tile for the given column and row.
	 * 
	 * @param x the column
	 * @param y the row
	 * @return
	 */
	public Tile getTile(int x, int y){
	
		return games[x][y];
	}
	
	
	/**
	 * Sets the tile for the given column and row. Calls tile.setLocation().
	 * 
	 * @param tile the tile to set
	 * @param x    the column
	 * @param y    the row
	 */
	public void setTile(api.Tile tile, int x, int y) {
		
		
		tile.setLocation(x, y);
		
		games[x][y] = tile;
		
		
		
		
		
		
	}
}
