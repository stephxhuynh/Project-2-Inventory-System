/**
 * Responsibilities of class: Super class that holds information
 *  about items that will be added to inventory.
 * 
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
public abstract class Item
{
	//// Fields ////
	private final String category;
	private Location location;
	
	
	//// Constructors ////

	/**
	 * Constructor passing just category
	 * 
	 * @param newCategory
	 */
	public Item(String newCategory) {
		category = newCategory;
		location = new Location();
	}

	//// Methods ////
	/**
	 * Get quantity of item
	 * 
	 * @return int of quantity
	 */
	abstract int getQuantity(); 
	
	
	/**
	 * Set quantity of item
	 * 
	 * @param newQuantity
	 */
	abstract void setQuantity(int newQuantity);
	
	/**
	 * Get screen size of item
	 * 
	 * @return int of size
	 */
	abstract int getScreenSize();
	
	/**
	 * Get location of item
	 * 
	 * @return
	 */
	public Location getLocation()
	{
		return location;
	}
	
	/**
	 * Set location of item 
	 * 
	 * @param newLocation
	 */
	public void setLocation(Location newLocation)
	{
		location = newLocation;
	}
	
	/**
	 * Get category of item
	 * 
	 * @return category
	 */
	final String getCategory()
	{
		return category;
	}
	
	/**
	 * To string method to print category, screen size, quantity, and location
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "Category: " + getCategory()  + " \t Screen size: " 
				+ getScreenSize() + "in \t Quantity: " + getQuantity() + getLocation();
	}
	

}
