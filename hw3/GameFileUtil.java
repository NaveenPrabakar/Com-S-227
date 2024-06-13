package hw3;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import api.Tile;

/**
 * Utility class with static methods for saving and loading game files.
 * @author Naveen Prabakar
 */
public class GameFileUtil {
	/**
	 * Saves the current game state to a file at the given file path.
	 * <p>
	 * The format of the file is one line of game data followed by multiple lines of
	 * game grid. The first line contains the: width, height, minimum tile level,
	 * maximum tile level, and score. The grid is represented by tile levels. The
	 * conversion to tile values is 2^level.
	 * 
	 * 
	 * @param filePath the path of the file to save
	 * @param game     the game to save
	 */
	public static void save(String filePath, ConnectGame game) {
		
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			
			Grid load = game.getGrid();
			String save = "";
			
			for(int a = 0; a < load.getHeight(); a++) {
				
				if(a == 0) {
					
					save += "" + load.getWidth() + " " + load.getHeight() + " " + game.getMinTileLevel() + " " 
							+ game.getMaxTileLevel() + " " + game.getScore() + "\n";
					
					
				}
				
				for(int b = 0; b < load.getWidth(); b++) {
					//if b = 0, it means it's the start of a new row, so no spaces are added
					if(b == 0) {
						
						save += load.getTile(b, a).getLevel();
					}
					
					//if b is not 0, that means the row is still going, so spaces between the numbers
					
					else {
						save += " " + load.getTile(b, a).getLevel();
					}
					
				}
				
				//if a is not equal to the last column, a new line is started
				
				if(a != load.getHeight()-1) {
					save += "\n";
				}
				
			}
			
			writer.write(save);
			
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 */
	public static void load(String filePath ,ConnectGame game) {
		
		try {
			
			File files = new File(filePath);
			
			Scanner scnr = new Scanner(files);
			
			Grid load = new Grid(scnr.nextInt(), scnr.nextInt());
			
			game.setGrid(load);
	        
	        
			
				for(int a = 0; a < load.getHeight(); a++) {
					
					if(a == 0) {
				        
				        game.setMinTileLevel(scnr.nextInt());
				        
				        game.setMaxTileLevel(scnr.nextInt());
				        
				        game.setScore(scnr.nextInt());
				        
						
						
					}
					
					for(int b = 0; b < load.getWidth(); b++) {
						// iterates through each box of the grid, which is then assigned a tile with the scanner
						
						load.setTile(new Tile(scnr.nextInt()), b, a);
					}
				}
			
				
			}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
}
	

		