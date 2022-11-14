/**
 * Responsibilities of class: Holds information about computers that will be added to inventory.
 * Extends Item class as a computer is an item
 * 
 * @author Stephanie Huynh
 * @author Abraham Kim
 *         Other contributors:
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Gaddis, T. (2015). Starting out with Java: From control structures through objects. Addison-Wesley.
 * @version 1.0 10/03/2022
 */
public class Computer extends Item
{
	//// Fields ////
	private int quantity;
	private int screenSize;
	
	//// Constructor ////
	
	/**
	 * Constructor that takes category
	 * 
	 * @param category
	 */
	public Computer(String category)
	{
		super(category);
	}
	
	/**
	 * Constructor that takes category and size of screen
	 * 
	 * @param category to assign
	 * @param newSize to assign
	 */
	public Computer(String category, int newSize)
	{
		super(category);
		screenSize = newSize;
	}
	
	//// Methods ////
	/**
	 * Get screen size
	 * 
	 * @return int 
	 */
	@Override
	
	public int getScreenSize()
	{
		return screenSize;
	}
	
	/**
	 * Get quantity
	 * 
	 * @return int
	 */
	@Override
	public int getQuantity()
	{
		return quantity;
	}
	
	/**
	 * Set quantity
	 * 
	 * @param newQuantity to set
	 */
	@Override
	public void setQuantity(int newQuantity)
	{
		quantity = newQuantity;
	}
	
}
