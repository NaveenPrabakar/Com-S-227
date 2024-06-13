package hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;

/**
 * Class that models a game.
 * @author Naveen Prabakar
 * 
 */
public class ConnectGame {
	
	/**
	 * Declared ShowDialogListener Variable dialogListener
	 * Defines a new block if the level surpasses the max
	 * 
	 */
	private ShowDialogListener dialogListener;
	
	/**
	 * Declared scoreListener Variable scoreListener
	 * Defines a the score after a set of selections
	 */
	
	private ScoreUpdateListener scoreListener;
	
	/**
	 * Declared int Variable minimum
	 * Defines the minimum level of a tile
	 */
	
	private int minimum;
	
	/**
	 * Declared int Variable maximum
	 * Defines the maximum level of a tile
	 */
	
	private int maximum;
	
	/**
	 * Declared Random variable ran
	 * The operator that randomly chooses a tile between minimum and maximum
	 */
	
	private Random ran;
	
	/**
	 * Declared boolean variable next
	 * Defines whether two tiles are adjacent or not
	 * 
	 */
	
	private boolean next;
	
	/**
	 * Declared long variable scores
	 * Defines the current score of the game
	 */
	
	private long scores;
	
	/**
	 * Declared int variable selections
	 * Defines the amount of tiles clicked in that selection
	 */
	
	private int selections;
	
	/**
	 * Declared Tile array collect
	 * Defines the stored tiles the player has clicked on
	 */
	
	private Tile[] collect;
	
	/**
	 * Declared Grid variable grid
	 * Defines the created grid
	 */
	
	private Grid grid;
	
	/**
	 * Declared boolean variable selected
	 * Defines whether or not a selection of tiles has started
	 */
	
	private boolean selected = false;
	
	/**
	 * Declared boolean variable finalselected
	 * Defines whether or not when the user is finishing the selection of tiles, the last tile in the 
	 * array is equal to the last tile they clicked
	 * 
	 */
	
	private boolean finalselected = false;
	
	/**
	 * Declared ArrayList Tile store
	 * Stores all valid selected Tiles
	 */

	
	private ArrayList<Tile> store = new ArrayList <Tile>();
	
	/**
	 * Declared Tile variable temp
	 * Stores the previous tile that was added to the arrayList
	 */
	
	private Tile temp;
	
	
	
	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width,int height,int min,int max,java.util.Random rand) {
		
		minimum = min;
		
		maximum = max;
		
		ran = rand;
		
		grid = new Grid(width,height);
		
	}
	
	
	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile() {
		
		int coordinate =  ran.nextInt(maximum - minimum) + minimum;
		
		return new Tile(coordinate);	
		
	}
	
	
	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	public void radomizeTiles() {
		
		
		for (int a = 0; a < grid.getWidth(); a++) { // iterate over columns
		    for (int b = 0; b < grid.getHeight(); b++) { // iterate over rows
		    	
		    	grid.setTile(getRandomTile(), a, b);
		    }
		}
	}
	
	
	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2) {
		
	
		
	int x1 = t1.getX();
	
	int y1 = t1.getY();
	
	int x2 = t2.getX();
	
	int y2 = t2.getY();
	
	if(Math.abs(x2 - x1) == 1 && y2 == y1) {
		
		next = true;
	}
	
	else if(Math.abs(y2 - y1) == 1 && x2 == x1) {
		next = true;
	}
	
	else if(Math.abs(x2-x1) + Math.abs((y2-y1)) == 2){
		
		next = true;
	}
	
	else {
		next = false;
	}
	
	return next;
		
		
	}
	
	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	public boolean tryFirstSelect(int x, int y) {
		
		
		boolean first = false;
		
            if(selected == false) {
            	
            	selected = true;
            	
            	first = true;
            	
            	grid.getTile(x, y).setSelect(true);
            	
            	store.add(selections,grid.getTile(x, y));
        		
        		
        		
        		collect = new Tile[store.size()];
        		
        		for(int a = 0; a <store.size(); a++) {// converts the arrayList into an array
        			
        			collect[a] = store.get(a);
        		}
        		
        		grid.getTile(x, y).setSelect(true);
        		
        		temp = grid.getTile(x, y);
			
			selections++;
            
            
            }
		
		
		return first;
		
	}
	
	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 */
	public void tryContinueSelect(int x, int y) {
		
		
		Tile news = grid.getTile(x, y);
		
		
		if(selections == 1) {
			
			if(isAdjacent(temp, news)) {
				
				if(temp.getLevel() == news.getLevel()) {
					
					store.add(selections, news);
					
					news.setSelect(true);
					
					
					
					selections++;
					
					
					
					collect = new Tile[store.size()];
					
					for(int a = 0; a <store.size(); a++) {// converts the arrayList into an array
						
						collect[a] = store.get(a);
					}
					
					temp = news;
				
				
			}
			
				
				
				
			}
		}
		
		else if(selections >= 2) {
			//If statement compares the x and y coordinates of the previous tile to the current one
			if(store.get(store.size()-2).getX() == news.getX() && store.get(store.size()-2).getY() == news.getY()) {
				
				unselect(temp.getX(),temp.getY());
				
				temp = store.get(store.size()-1);
				
				return;
				
			}
			
			else if(isAdjacent(temp, news)) {
				
				
				if(temp.getLevel() == news.getLevel()) {
					
					
					store.add(selections,news);
					
					news.setSelect(true);
					
					
					
					collect = new Tile[store.size()];
					
					for(int a = 0; a <store.size(); a++) {// converts the arrayList into an array
						
						collect[a] = store.get(a);
					}
					
					selections++;
					
					temp = news;
					
					
					
					
					
					
				}
				
				else if(news.getLevel() == temp.getLevel()+1) {
					
					store.add(selections,news);
					
					news.setSelect(true);
					
					
					collect = new Tile[store.size()];
					
					for(int a = 0; a <store.size(); a++) {// converts the arrayList into an array
						
						collect[a] = store.get(a);
					}
					
					selections++;
					
					temp = news;
					
					
					
					
					
				}
				
			}
				
				
			}
			
		
				
			}
			

		
	
	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */
	public boolean tryFinishSelection(int x, int y) {
        
		
		if(store.get(store.size()-1) != grid.getTile(x, y)) {
			
			finalselected = false;

		}
		
		
		
		else if(collect.length > 1) {
			
			finalselected = true;
			
            int levels = grid.getTile(x, y).getLevel();
			
			scores += Math.pow(2, levels+1);
			
			scoreListener.updateScore((long) scores);
			
			setScore((long) scores);
			
			getScore();
		
			
			upgradeLastSelectedTile();
			
			dropSelected();
			
			for(int a = 0; a < store.size(); a++) {// iterates through all the selected tiles and sets them to false
				
				store.get(a).setSelect(false);
			}
			
			
			
			store.clear();
			
			collect = new Tile[0];
			
			selections = 0;
			
			selected = false;

			
			
			
			
		}
		
		else if(collect.length <= 1) {
			
			finalselected = true;
			
            for(int a = 0; a < store.size(); a++) {// iterates through all the selected tiles and sets them to false
				
				store.get(a).setSelect(false);
			}
			
			
			selected = false;
		}
		
		return finalselected;
		
	}
	
	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2".
	 */
	public void upgradeLastSelectedTile() {
		
		Tile upgrade = store.get(store.size()-1);
		
		
		
		int level = upgrade.getLevel();
		
		upgrade.setLevel(level +1);
		
		if(upgrade.getLevel() > maximum){
			
			dialogListener.showDialog("New Block: " + upgrade.getLevel() + "Removing blocks:" + minimum);
			
			minimum++;
			
			maximum++;
			
			
		}
		
	}
	
	
	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	public Tile[] getSelectedAsArray() {
	
		return collect;
		
	}
	
	
	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	public void dropLevel(int level) {
		

		for(int a = 0; a < grid.getWidth(); a++) {
			for(int b = 0; b <grid.getHeight(); b ++) {
				
				if(grid.getTile(a, b).getLevel() == level) {
					
					
					for(int c = 0; c < b + 1; c++) {//increments the value b, j number of times
						
						//if the b value minus the c value is  0, that means it's the top of the grid
						if(b - c == 0) {
							
							grid.setTile(getRandomTile(), a, b -c);
							
							
						}
						//if the b value minus the c value is greater than 0, it means we haven't hit the top of the grid yet 
						else if(b - c >= 1) {
							
							grid.setTile(grid.getTile(a, b-c-1), a , b-c);
						}
						
						
						
					}
					}
				}
			}
		}
		
	
	
	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	public void dropSelected() {
		
		
		
		for(int a = 0; a <grid.getWidth(); a++) {
			for(int b = 0; b < grid.getHeight(); b++) {
				
				for(int c = 0; c < store.size(); c++) {
					
					if(grid.getTile(a, b) == store.get(c)) {
						
						for(int d = 0; d < b + 1; d++) {// iterates through the d value b times
							
							//if b minus d is equal to 0, it's at the top of the grid
							if(b - d== 0) {
								
								grid.setTile(getRandomTile(), a, b-d);
								
								
							}
							
							//if b - d is greater then 0, it means it's not at the top of the grid
							else if(b - d >= 1) {
								
								grid.setTile(grid.getTile(a, b-d-1), a, b-d);
								
								
							}
						}
						
						
					}
				}
			}
		}
	}
								
								
						
					
				
			
	
								
								
							
			
		
	
	
	/**
	 * Remove the tile from the selected tiles.
	 * 
	 * @param x column of the tile
	 * @param y row of the tile
	 */
	public void unselect(int x, int y) {
		
		
	    store.remove(grid.getTile(x, y));
	    
	    collect = new Tile[store.size()];
		
		for(int a = 0; a < collect.length; a++) {
			
			collect[a] = store.get(a);
		}
		
		grid.getTile(x, y).setSelect(false);
		
		
	
		
		
		
	}
	
	
	/**
	 * Gets the player's score.
	 * 
	 * @return the score
	 */
	public long getScore() {
		
		return scores;
		
	}
	
	
	
	
	/**
	 * Gets the game grid.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		
		
		return grid;
		
		
		
		
	}
	
	
	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel() {
		
		return minimum;
	}
	
	
	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel() {
		
		return maximum;
	}
	
	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long score) {
		
		
		scores = score;
		
	}
	
	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	public void setGrid(Grid grid) {
		
		this.grid = grid;
		
	}
	

	/**
	 * Sets the minimum tile level.
	 * 
	 * @param minTileLevel the lowest level tile
	 */
	public void setMinTileLevel(int minTileLevel) {
		
		minimum = minTileLevel;
		
	}
	
	/**
	 * Sets the maximum tile level.
	 * 
	 * @param maxTileLevel the highest level tile
	 */
	public void setMaxTileLevel(int maxTileLevel) {
		
		maximum = maxTileLevel;
		
	}
	
	


	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath) {
		GameFileUtil.save(filePath, this);
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) {
		GameFileUtil.load(filePath, this);
	}
	
	
}
