import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Represent a rectangular grid of field positions.
 * Each position is able to store a single person.
 * 
 * @author David J. Barnes and Michael Kolling
 * @author Maria Chli
 * @version 22-Oct-2008
 */
public class Field
{
	// The depth and width of the field.
	private int depth, width;
	// Storage for the persons.
	private Actor[][] field;
	
	/**
	 * Represent a field of the given dimensions.
	 * The topology of the grid is torus shaped. 
	 * @param depth The depth of the field.
	 * @param width The width of the field.
	 */
	public Field(int depth, int width)
	{
		this.depth = depth;
		this.width = width;
		field = new Actor[depth][width];
	}
	
	/**
	 * Empty the field.
	 */
	public void clear()
	{
		for(int row = 0; row < depth; row++) {
			for(int col = 0; col < width; col++) {
				field[row][col] = null;
			}
		}
	}
	
	/**
	 * Place a person at the given location.
	 * If there is already a person at the location it will
	 * be lost.
	 * @param actor The person to be placed.
	 * @param row Row coordinate of the location.
	 * @param col Column coordinate of the location.
	 */
	public void place(Actor actor, int row, int col)
	{
		place(actor, new Location(row, col));
	}
	
	/**
	 * Place a person at the given location.
	 * If there is already a person at the location it will
	 * be lost.
	 * @param actor The person to be placed.
	 * @param location Where to place the person.
	 */
	public void place(Actor actor, Location location)
	{
		field[location.getRow()][location.getCol()] = actor;
	}
	
	/**
	 * Clear the given location.
	 * If there is a person at the location it will
	 * be lost.
	 * @param location The location to be cleared.
	 */
	public void clearLocation(Location location)
	{
		field[location.getRow()][location.getCol()] = null;
	}
	
	/**
	 * Return the person at the given location, if any.
	 * @param location Where in the field.
	 * @return The person at the given location, or null if there is none.
	 */
	public Actor getObjectAt(Location location)
	{
		return getObjectAt(location.getRow(), location.getCol());
	}
	
	/**
	 * Return the person at the given location, if any.
	 * @param row The desired row.
	 * @param col The desired column.
	 * @return The person at the given location, or null if there is none.
	 */
	public Actor getObjectAt(int row, int col)
	{
		return field[row][col];
	}
	

	/**
	 * Try to find a free location that is adjacent to the
	 * given location. If there is none, then return the current
	 * location if it is free. If not, return null.
	 * The returned location will be within the valid bounds
	 * of the field.
	 * @param location The location from which to generate an adjacency.
	 * @return A valid location within the grid area. This may be the
	 *         same object as the location parameter, or null if all
	 *         locations around are full.
	 */
	public Location freeAdjacentLocation(Location location)
	{
		Iterator<Location> adjacent = adjacentLocations(location);
		while(adjacent.hasNext()) {
			Location next = (Location) adjacent.next();
			if(field[next.getRow()][next.getCol()] == null) {
				return next;
			}
		}
		// check whether current location is free
		if(field[location.getRow()][location.getCol()] == null) {
			return location;
		} 
		else {
			return null;
		}
	}
	
	/*
	 * 
	 * @return A copy of this field
	 */
	public Field cloneField()
	{
		Field clone = new Field(this.getDepth(), this.getWidth());
		for(int row = 0; row < depth; row++) {
			for(int col = 0; col < width; col++) {
				clone.field[row][col] = this.field[row][col];
			}
		}
		return clone;	
	}

	/**
	 * Generate an iterator over a shuffled list of locations adjacent
	 * to the given one. The list will not include the location itself.
	 * All locations will lie within the grid.
	 * The topology of the grid is torus shaped. 
	 * This means that it is like a chessboard but when a piece goes beyond the 
	 * bottom row it reappears from the first row and vice versa. 
	 * Similarly when it goes beyond the rightmost column it reappears from the 
	 * leftmost column and vice versa.
	 * @param location The location from which to generate adjacencies.
	 * @return An iterator over locations adjacent to that given.
	 */
	public Iterator<Location> adjacentLocations(Location location)
	{
		return adjacentLocations(location, 1);		
	}
	
	
	/**
	 * Generate an iterator over a shuffled list of locations within
	 * "manhattan distance" w to the given one. The list will not include the location itself.
	 * All locations will lie within the grid.
	 * The topology of the grid is torus shaped. 
	 * This means that it is like a chessboard but when a piece goes beyond the 
	 * bottom row it reappears from the first row and vice versa. 
	 * Similarly when it goes beyond the rightmost column it reappears from the 
	 * leftmost column and vice versa.
	 * @param location The location from which to generate adjacencies.
	 * @param w The "manhattan" radius of the neighbourhood.
	 * @return An iterator over locations adjacent to that given.
	 */
	public Iterator<Location> adjacentLocations(Location location, int w)
	{
		int row = location.getRow();
		int col = location.getCol();
		LinkedList<Location> locations = new LinkedList<Location>();
		for(int roffset = -w; roffset <= w; roffset++) 
		{
			int nextRow = row + roffset;
			//if(nextRow >= 0 && nextRow < depth) {
			for(int coffset = -w; coffset <= w; coffset++) 
			{
				int nextCol = col + coffset;
				// Exclude invalid locations and the original location.
/*
					if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
						locations.add(new Location(nextRow, nextCol));
					}
*/
				if(nextCol<0)
					nextCol = nextCol + width;
				if(nextCol>=width)
					nextCol = nextCol - width;
				if(nextRow<0)
					nextRow = nextRow + depth;
				if(nextRow>=depth)
					nextRow = nextRow - depth;
				locations.add(new Location(nextRow, nextCol));
				}
			//}
		}
		Random rand = RandomGenerator.getRandom();
		Collections.shuffle(locations,rand);
		return locations.iterator();
	}
	
	public void setDepth(int d)
	{
		depth = d;
	}
	
	public void setWidth(int w)
	{
		width = w;
	}
	
	/**
	 * @return The depth of the field.
	 */
	public int getDepth()
	{
		return depth;
	}
	
	/**
	 * @return The width of the field.
	 */
	public int getWidth()
	{
		return width;
	}
}
