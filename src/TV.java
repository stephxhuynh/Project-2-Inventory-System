/**
 * Responsibilities of class: Holds information about tvs that will be added to inventory.
 * Extends item class as a tv is an item.
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

public class TV extends Item
{
	//// Fields ////
	private int quantity;
	private int screenSize;
	private String type;
	
	//// Constructor ////
	/**
	 * constructor
	 * 
	 * @param category
	 */
	public TV(String category)
	{
		super(category);
	}
	
	/**
	 * constructor
	 * 
	 * @param category to assign
	 * @param newSize 
	 * @param newType
	 */
	public TV(String category, int newSize, String newType)
	{
		super(category);
		screenSize = newSize;
		type = newType;
	}
	
	
	//// Methods ////
	/**
	 * get screen size
	 * 
	 * @return int
	 */
	@Override
	public int getScreenSize()
	{
		return screenSize;
	}
	
	/**
	 * get quantity
	 * 
	 * @return int
	 */
	@Override
	public int getQuantity()
	{
		return quantity;
	}
	
	/**
	 * set quantity
	 */
	@Override
	public void setQuantity(int newQuantity)
	{
		quantity = newQuantity;
	}
	
	/**
	 * get type of TV
	 * 
	 * @return String
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * to string to print type and also print rest of information by calling super
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "Type: " + getType() + "\t " + super.toString();
	}
	
	
}
