/**
 * Responsibilities of class: holds information about location. Integrated in Item class
 * as every item has a location. 
 * 
 * @author Stephanie Huynh
 * @author Abraham Kim
 *         Other contributors:
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Gaddis, T. (2015). Starting out with Java: From control structures through objects. Addison-Wesley.
 * 
 * @version 1.0 10/03/2022
 */

public class Location
{
	//// Fields ////
	//static allows field to increase from where it left off
	//i.e: starts @ 1 when first object created, then increases to 2 @ next object, etc
	private static int shelfCounter = 1;
	private static int aisleCounter = 1;
	private int shelf = 1;
	private int aisle = 1;
	
	///// Constructors/////
	/**
	 * No arg constructor that will auto assign shelf and aisle value
	 */
	public Location()
	{
		//post increment
		shelf = shelfCounter++;
		aisle = aisleCounter++;
	}
	
	//// Methods ////
	/**
	 * Get aisle
	 * 
	 * @return int
	 */
	public int getAisle()
	{
		return aisle;
	}
	
	/**
	 * Get shelf
	 * 
	 * @return int
	 */
	public int getShelf()
	{
		return shelf;
	}

	/**
	* Set Aisle
	* 
	* @param newAisle
	*/
	public void setAisle(int newAisle)
	{
		aisle = newAisle;
	}
	
	/**
	* Set shelf
	* 
	* @param newShelf
	*/
	public void setShelf(int newShelf)
	{
		shelf = newShelf;
	}
	
	/**
	 * To String to return information of aisle and shelf values
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return " \t Aisle: " + aisle + " \t Shelf: " + shelf;
	}
}
