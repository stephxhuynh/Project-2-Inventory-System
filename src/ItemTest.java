import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class ItemTest
{

	@Test
	public void testComputerConstructor()
	{
		Computer computer = new Computer("computer", 20);
		assertEquals("computer", computer.getCategory());
		assertEquals(20, computer.getScreenSize());
	}
	
	@Test
	public void testTVConstructor()
	{
		TV tv1 = new TV("TV", 40, "OLED");
		assertEquals("TV", tv1.getCategory());
		assertEquals(40, tv1.getScreenSize());
		assertEquals("OLED", tv1.getType());
	}
	
	@Test
	public void testNoArgLocationConstructor()
	{
		Location location = new Location();
		assertEquals(5, location.getAisle());
		assertEquals(5, location.getShelf());
	}
	
	@Test
	public void testTVToString()
	{
		TV tv1 = new TV("TV", 40, "OLED");
		TV tv2 = new TV("TV", 50, "LCD");
		assertTrue(tv1.toString().contains("OLED"));
		assertTrue(tv2.toString().contains("LCD"));
	}
	
	
//	@Test
//	public void TestIfItemExists()
//	{	
//		ArrayList<Item> inventory = new ArrayList<Item>();
//		TV tv1 = new TV("TV", 40, "OLED");
//		inventory.add(tv1);
//		assertTrue(InventorySystem.ifItemExists(inventory, "TV", 40, "OLED"));
//	}
}
