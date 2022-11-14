import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Responsibilities of class: Run program for user to input inventory
 * and search inventory.
 * 
 * 
 * @author Stephanie Huynh
 * @author Abraham Kim
 * Other contributors:
 * Tasha Frankie
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Gaddis, T. (2015). Starting out with Java: From control structures through objects. Addison-Wesley.
 * 
 *         
 * @version 1.0 10/03/2022
 */

public class InventorySystem
{

	// Global constraint variable to keep track of index of the inventory
	// arraylist
	static int count = 0;

	public static void main(String[] args) throws IOException
	{

		// arrayList of Item - can consist of TV or Computer objects that we
		// make
		ArrayList<Item> inventory = new ArrayList<Item>();

		// read any existing inventory
		readFromFile("inventoryInfo.txt", inventory);

		// opens stream for input
		Scanner keyboard = new Scanner(System.in);

		// print Menu
		printMenu(inventory, keyboard);

		writeToFile("inventoryInfo.txt", inventory);

		printInventory(inventory);

		printQuantity(inventory);

		keyboard.close();
	}

	/**
	 * Prints Menu options and goes into check/add depending on choice
	 * 
	 * @param inventory to add/search
	 * @param keyboard
	 */
	public static void printMenu(ArrayList<Item> inventory, Scanner keyboard)
	{
		String choice;

		boolean askCont = true;
		// while loop to continue as user likes
		while (askCont != false)
		{
			// ask if they want to add or check or show all
			choice = askMenu(keyboard);
			if (choice.equalsIgnoreCase("Add"))
			{
				addInventory(inventory, keyboard);
			}

			else if (choice.equalsIgnoreCase("Check"))
			{
				checkInventory(inventory, keyboard);
			}

			else if (choice.equalsIgnoreCase("Show all"))
			{
				printInventory(inventory);
			}
			askCont = askMenuContinue(keyboard);
		}
	}

	/**
	 * Add inventory to Item arrayList
	 * 
	 * @param inventory to add to
	 * @param keyboard
	 */
	public static void addInventory(ArrayList<Item> inventory, Scanner keyboard)
	{
		String category = null;
		// int count = 0; //used to keep track of ArrayList indexing
		// int quantity; //quantity of each Item

		boolean choice = true;

		// while loop as user decides when they want to stop or keep adding more
		// items
		while (choice != false)
		{
			// get category from user
			category = askCategory(keyboard);

			if (category.equalsIgnoreCase("TV"))
			{
				addTV(inventory, category, keyboard);

			

				keyboard.nextLine(); // clear input
				choice = askContinue(keyboard);

			}
			else if (category.equalsIgnoreCase("Computer"))
			{
				addComputer(inventory, category, keyboard);

				keyboard.nextLine(); // clear input

				choice = askContinue(keyboard);
			}
		}
	}

	/**
	 * Check inventory
	 * 
	 * @param inventory
	 * @param keyboard
	 */
	public static void checkInventory(ArrayList<Item> inventory,
			Scanner keyboard)
	{

		// holds user input variables that will help us check inventory
		String category;
		String type;
		int size;

		boolean choice = true;

		while (choice != false)
		{
			category = askCategory(keyboard);

			if (category.equalsIgnoreCase("TV"))
			{
				// ask type of TV
				type = askTVType(keyboard);

				if (type.equalsIgnoreCase("OLED"))
				{
					// ask size of TV
					size = askTVSize(keyboard);

					keyboard.nextLine();

					// search for item in inventory arrayList and print quantity
					// if found
					searchTVInventory(inventory, category, size, type);

					choice = askContinue(keyboard);
				}
				else if (type.equalsIgnoreCase("LCD"))
				{
					// ask size of tv
					size = askTVSize(keyboard);

					keyboard.nextLine(); // clear input
					// search for item in inventory arrayList and print quantity
					// if found
					searchTVInventory(inventory, category, size, type);

					choice = askContinue(keyboard);
				}

			}

			else if (category.equalsIgnoreCase("Computer"))
			{
				// ask size of computer
				size = askComputerSize(keyboard);

				keyboard.nextLine(); // clear input

				// search for item in inventory arrayList and print quantity if
				// found
				searchComputerInventory(inventory, category, size);

				choice = askContinue(keyboard);
			}
		}
	}

	/**
	 * add TV object to inventory
	 * 
	 * @param inventory to add to
	 * @param category  for constructor
	 * @param keyboard
	 */
	public static void addTV(ArrayList<Item> inventory, String category,
			Scanner keyboard)
	{
		String type;
		int size;

		type = askTVType(keyboard);

		size = askTVSize(keyboard);

		// checks if item exists and adds quantities to existing or new item
		ifItemExists(inventory, category, size, type, keyboard);

	}

	/**
	 * add Computer object to inventory
	 * 
	 * @param inventory to add to
	 * @param category  for constructor
	 * @param keyboard
	 */
	public static void addComputer(ArrayList<Item> inventory, String category,
			Scanner keyboard)
	{
		int size;

		size = askComputerSize(keyboard);

		// "NA" passed because computer does not have type
		// checks if item exists and adds quantities to existing or new item
		ifItemExists(inventory, category, size, "NA", keyboard);

	}

	/**
	 * ask if user would like to add or check inventory
	 * 
	 * @param keyboard
	 * @return string choice
	 * @throws IllegalArgumentException if choice is not "ADD" "CHECK" or "SHOW
	 *                                  ALL".
	 *                                  prompts user to enter information again.
	 * @throws InputMistmatchException  if choice is not a string
	 */
	public static String askMenu(Scanner keyboard)
	{
		boolean validInput = false;
		String choice = null;
		System.out.println("What would you like to do?");
		System.out.println("Add or Check or Show all");
		do
		{
			try
			{
				choice = keyboard.nextLine();
				choice = choice.toUpperCase();
				if (choice.equals("ADD") || choice.equals("CHECK")
						|| choice.equals("SHOW ALL"))
					validInput = true;
				else throw new IllegalArgumentException();

			}
			catch (IllegalArgumentException e)
			{
				System.out.println(
						"Please enter Add or Check or show all. Try again.");
			}
			catch (InputMismatchException e)
			{
				System.out.println("Please enter a String. Try again.");
			}
		} while (!validInput);

		return choice;
	}

	/**
	 * ask user for category
	 * 
	 * @param keyboard
	 * @return String category
	 * @throws IllegalArgumentException if choice category is not "TV" or
	 *                                  "COMPUTER"
	 * 
	 */
	public static String askCategory(Scanner keyboard)
	{
		boolean validInput = false;
		String category = null;
		System.out.println("\nEnter category of TV or Computer: ");
		do
		{
			try
			{
				category = keyboard.nextLine();
				category = category.toUpperCase();
				if (category.equals("TV") || category.equals("COMPUTER"))
					validInput = true;
				else throw new IllegalArgumentException();
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("Please enter TV or Computer. Try again.");
			}
			catch (InputMismatchException e)
			{
				System.out.println("Please enter a String. Try again.");
			}
		} while (!validInput);
		return category;
	}

	/**
	 * ask user for type of TV
	 * 
	 * @param keyboard
	 * @return String type
	 * @throws IllegalArgumentException if type is not "OLED" or "LCD"
	 */
	public static String askTVType(Scanner keyboard)
	{
		boolean validInput = false;
		String type = null;
		System.out.println("Enter type: OLED or LCD ");
		do
		{
			try
			{
				type = keyboard.nextLine();
				type = type.toUpperCase();
				if (type.equals("OLED") || type.equals("LCD"))
					validInput = true;
				else throw new IllegalArgumentException();
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("\nPlease enter OLED or LCD. Try again.");
			}
			catch (InputMismatchException e)
			{
				System.out.println("Please enter a String. Try again.");
			}
		} while (!validInput);
		return type;

	}

	/**
	 * Ask user for TV size
	 * 
	 * @param keyboard
	 * @return int size
	 * @throws IllegalArgumentException if size is not an int of 40, 50, or 60
	 */
	public static int askTVSize(Scanner keyboard)
	{
		int size = 0;
		boolean validInput = false;
		System.out.println("\nEnter screen size of 40, 50, or 60: ");
		do
		{
			try
			{
				size = keyboard.nextInt();
				if (size == 40 || size == 50 || size == 60) validInput = true;
				else throw new IllegalArgumentException();

			}
			catch (IllegalArgumentException | InputMismatchException e)
			{
				System.out.println(
						"Please enter a number of 40, 50, or 60. Try again");
				keyboard.nextLine(); // clear input
			}
		} while (!validInput);
		return size;
	}

	/**
	 * Ask user for Computer size
	 * 
	 * @param keyboard
	 * @return int size
	 * @throws IllegalArgumentException if size is not an int of 15, 20, or 25
	 */
	public static int askComputerSize(Scanner keyboard)
	{
		int size = 0;
		boolean validInput = false;
		System.out.println("\nEnter screen size of 15, 20, or 25: ");
		do
		{
			try
			{
				size = keyboard.nextInt();
				if (size == 15 || size == 20 || size == 25) validInput = true;
				else throw new IllegalArgumentException();

			}
			catch (IllegalArgumentException | InputMismatchException e)
			{
				System.out.println(
						"Please enter screen size of 15, 20, or 25. Try again");
				keyboard.nextLine(); // clear input
			}
		} while (!validInput);
		return size;
	}

	/**
	 * Search for TV item in inventory to find quantity of item if it exists in
	 * inventory arraylist
	 * if item exists, print quantity; if not, print does not exist
	 * 
	 * @param inventory to search
	 * @param category  to compare
	 * @param size      to compare
	 * @param type      to compare
	 *
	 */
	public static void searchTVInventory(ArrayList<Item> inventory,
			String category, int size, String type)
	{
		boolean exists = false;
		int quantity = 0;
		for (int i = 0; i < inventory.size(); i++)
		{
			if (inventory.get(i).getCategory().equals(category))
			{
				if (inventory.get(i).getScreenSize() == size)
					if (((TV) inventory.get(i)).getType().equals(type))
				{
					exists = true;
					quantity = inventory.get(i).getQuantity();
				}

			}
		}
		if (exists == true)
		{
			System.out.println("\nQuantity of " + category + " " + size + "in "
					+ type + " is:\t" + quantity);
		}
		else System.out.println("Item does not exist");

	}

	/**
	 * Search for Computer item in inventory to find quantity if item exists in
	 * inventory arraylist
	 * if item exist, print quantity; if not, print does not exist
	 * 
	 * @param inventory
	 * @param category
	 * @param size
	 * @return int quantity
	 */
	public static void searchComputerInventory(ArrayList<Item> inventory,
			String category, int size)
	{
		boolean exists = false;
		int quantity = 0;
		for (int i = 0; i < inventory.size(); i++)
		{
			if (inventory.get(i).getCategory().equals(category))
				if (inventory.get(i).getScreenSize() == size)
			{
				exists = true;
				quantity = inventory.get(i).getQuantity();
			}
		}
		if (exists == true) System.out.println("\nQuantity of " + category + " "
				+ size + "in is:\t" + quantity);
		else System.out.println("Item does not exist");

	}

	/**
	 * ask user for quantity they want to add
	 * 
	 * @param keyboard
	 * @return int quantity
	 * @throws IllegalArgument if quantity is negative or equal to 0
	 */
	public static int askQuantity(Scanner keyboard)
	{
		int quantity = 0;
		boolean validInput = false;
		System.out.println("\nEnter quantity: ");
		do
		{
			try
			{
				quantity = keyboard.nextInt();
				if (quantity <= 0) throw new IllegalArgumentException();
				validInput = true;

			}
			catch (IllegalArgumentException | InputMismatchException e)
			{
				System.out.println("Please enter valid number. Try again.");
				keyboard.nextLine(); // clear input
			}
		} while (!validInput);
		return quantity;
	}

	/**
	 * Ask user if they want to continue
	 * 
	 * @param keyboard
	 * @return boolean
	 * @throws IllegalArgumentException if "y" or "n" is not entered
	 * 
	 */
	public static boolean askContinue(Scanner keyboard)
	{
		boolean validInput = false;
		String value;
		System.out.println("\nDo you wish to continue?: y or n: ");
		do
		{
			try
			{
				value = keyboard.nextLine();
				if (value.equalsIgnoreCase("y") || value.equalsIgnoreCase("n"))
					validInput = true;
				else throw new IllegalArgumentException();
				if (value.equals("y"))
				{
					return true;
				}
				else if (value.equals("n")) return false;
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("Please enter y or n. Try again");
			}
		} while (!validInput);
		return false;
	}

	/**
	 * Prompt user to check or add again
	 * 
	 * @param keyboard
	 * @return boolean
	 * @throws IllegalArgumentException if "y" or "n" is not entered
	 * 
	 */
	public static boolean askMenuContinue(Scanner keyboard)
	{
		boolean validInput = false;
		String value;
		System.out.println("\nDo you wish to add or check again? y or n: ");
		do
		{
			try
			{
				value = keyboard.nextLine();
				if (value.equalsIgnoreCase("y") || value.equalsIgnoreCase("n"))
					validInput = true;
				else throw new IllegalArgumentException();
				if (value.equals("y"))
				{
					return true;
				}
				else if (value.equals("n")) return false;
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("Please enter y or n. Try again");
			}
		} while (!validInput);
		return false;
	}

	/**
	 * Print all inventory out
	 * 
	 * @param inventory
	 */
	public static void printInventory(ArrayList<Item> inventory)
	{
		for (int i = 0; i < inventory.size(); i++)
		{
			System.out.println(inventory.get(i).toString());
		}
		System.out.println();
	}

	/**
	 * Print total quantities of inventory
	 * 
	 * @param inventory
	 */
	public static void printQuantity(ArrayList<Item> inventory)
	{

		int total = 0;
		for (int i = 0; i < inventory.size(); i++)
		{
			total += inventory.get(i).getQuantity();
		}
		System.out.println("Inventory has " + total + " items");
	}

	/**
	 * Read information from file
	 * 
	 * @param filename  to read from
	 * @param inventory to add to
	 * @throws FileNotFoundException thrown if no such file exists
	 */
	public static void readFromFile(String fileName, ArrayList<Item> inventory)
			throws FileNotFoundException
	{
		Scanner input = null;
		String type;
		String category;
		int size;
		int quantity;

		try
		{
			File myFile = new File(fileName);
			input = new Scanner(myFile);
			while (input.hasNextLine())
			{
				type = input.next();
				if (type.equals("OLED") || type.equals("LCD"))
				{
					category = input.next();
					size = input.nextInt();
					quantity = input.nextInt();

					input.nextLine(); // clear newline

					inventory.add(new TV(category, size, type));
					inventory.get(count).setQuantity(quantity);
					count++;
				}
				else
				{
					category = input.next();
					size = input.nextInt();
					quantity = input.nextInt();

					input.nextLine(); // clear newline

					inventory.add(new Computer(category, size));
					inventory.get(count).setQuantity(quantity);
					count++;
				}

			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File does not exist");
		}
		finally
		{
			if (input != null)
			{
				input.close();
			}
			System.out.println(
					"System finished reading any existing inventory file.\n");
		}
	}

	/**
	 * Write inventory to file
	 * 
	 * @param fileName  to write to
	 * @param inventory to copy information from
	 * @throws IOException
	 * @throws FileNotFoundException if no such file exists
	 */
	public static void writeToFile(String fileName, ArrayList<Item> inventory)
			throws IOException, FileNotFoundException
	{

		FileWriter fWriter = null;
		PrintWriter pWriter = null;
		try
		{
			fWriter = new FileWriter(fileName);
			pWriter = new PrintWriter(fWriter);
			for (int i = 0; i < inventory.size(); i++)
			{
				/*
				 * instanceof because TV and Computer data is written in a bit
				 * differently
				 * TV: type = LCD OR OLED
				 * Computer: type = NA
				 */

				if (inventory.get(i) instanceof TV)
				{
					pWriter.println(((TV) inventory.get(i)).getType() + " "
							+ inventory.get(i).getCategory() + " "
							+ inventory.get(i).getScreenSize() + " "
							+ inventory.get(i).getQuantity() + " "
							+ inventory.get(i).getLocation());
				}
				else if (inventory.get(i) instanceof Computer)
				{
					pWriter.println("NA " + inventory.get(i).getCategory() + " "
							+ inventory.get(i).getScreenSize() + " "
							+ inventory.get(i).getQuantity() + " "
							+ inventory.get(i).getLocation());
				}

			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Cannot write to file, file does not exist");
		}
		finally
		{
			System.out.println("System saved information to file");
			pWriter.close();
		}
	}

	/**
	 * checks if item exists before adding new item to inventory
	 * if it does, prompt user to add quantity to it
	 * if not, create new object in inventory ArrayList and prompt user to add
	 * quantity
	 * 
	 * @param inventory to search and add to
	 * @param category
	 * @param size
	 * @param type
	 * @param keyboard
	 */
	public static void ifItemExists(ArrayList<Item> inventory, String category,
			int size, String type, Scanner keyboard)
	{
		boolean exists = false;
		int indexExist = -1;
		int quantity = 0;

		// search through inventory ArrayList
		// if variables match, item exists, and save index of where item exists
		for (int i = 0; i < inventory.size(); i++)
		{
			if (inventory.get(i) instanceof TV)
			{
				if (((TV) inventory.get(i)).getType().equals(type))
					if (inventory.get(i).getScreenSize() == size)
				{
					exists = true;
					indexExist = i;
				}

			}
			else if (inventory.get(i) instanceof Computer)
			{
				if (inventory.get(i).getScreenSize() == size)
				{
					exists = true;
					indexExist = i;
				}
			}
		}

		// if item exists, add quantity of items to that item
		if (exists == true)
		{
			System.out.println("\nItem already exists. Simply add quantity.");
			quantity = askQuantity(keyboard);
			quantity = quantity + inventory.get(indexExist).getQuantity();
			inventory.get(indexExist).setQuantity(quantity);
		}
		// else if inventory does not exist, create new item/object depending on
		// specified category
		else if (exists == false)
		{
			if (category.equals("TV"))
			{
				inventory.add(new TV(category, size, type));
				quantity = askQuantity(keyboard);
				inventory.get(count).setQuantity(quantity);
				//increasing count to index through arraylist for new items
				count++;
			}
			else
			{
				inventory.add(new Computer(category, size));
				quantity = askQuantity(keyboard);
				inventory.get(count).setQuantity(quantity);
				//increasing count to index through arraylist for new items
				count++;
			}
		}

	}

}
