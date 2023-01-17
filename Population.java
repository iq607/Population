import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - Creates a database from the list of USA cities and sorts
 * 	the	data for population and city name using Selection Sort, Insertion 
 * 	Sort, and Merge Sort. Gives user top 50 cities that match requested 
 * 	input
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Iris Quan
 *	@since	January 9, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	public static void main(String [] args)
	{
		Population pop = new Population();
		pop.readPopData();
		pop.run();
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/** opens file to read, puts city into arraylist */
	public void readPopData()
	{
		Scanner popReader = FileUtils.openToRead(DATA_FILE).useDelimiter("[\t\n]");
		cities = new ArrayList<City>();
		String temp = "";
		while (popReader.hasNext())
		{
			temp = popReader.nextLine();
			cities.add(makeCity(temp));
		}
		System.out.println(cities.size()+" cities in database\n");
	}
	
	/**	creates and returns a new city object from string input
	 * 	@param 	temp 	string input with city details
	 * 	@return			city object with details
	 */
	public City makeCity(String temp)
	{
		String state = temp.substring(0,temp.indexOf("\t"));
		temp = temp.substring(temp.indexOf("\t")+1);
		String name = temp.substring(0,temp.indexOf("\t"));
		temp = temp.substring(temp.indexOf("\t")+1);
		String type = temp.substring(0,temp.indexOf("\t"));
		temp = temp.substring(temp.indexOf("\t")+1);
		int popu = Integer.parseInt(temp);
		return new City(name, type, state, popu);
	}
	
	/**	checks if inputted state is valid
	 * 	@param	state	string with state name
	 * 	@return			true if valid state, else false
	 */
	public boolean checkValidState(String state)
	{
		String [] stateNames = new String[]{"Alabama","Alaska","Arizona",
		"Arkansas","California","Colorado","Connecticut","Delaware","Florida",
		"Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas",
		"Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan",
		"Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada",
		"New Hampshire","New Jersey","New Mexico","New York","North Carolina",
		"North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island",
		"South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont",
		"Virginia","Washington","West Virginia","Wisconsin","Wyoming"};
		for (String name: stateNames)
		{
			if (state.equals(name))
				return true;
		}
		return false;
	}
	
	/**	runs the thing */
	public void run()
	{
		printIntroduction();
		int input = 0;
		long startMillisec = 0;
		long endMillisec = 0;
		ArrayList<City> matchingCities = new ArrayList<City>();
		String city = "";
		while (true)
		{
			printMenu();
			input = Prompt.getInt("Enter selection");
			if (input == 9)
				break;
			else if (input == 6)
			{
				city = Prompt.getString("Enter city name");
				System.out.println("City "+city+ " by population");
				for (int i = 0; i < cities.size(); i += 1)
				{
					if (cities.get(i).getCityName().equalsIgnoreCase(city))
						matchingCities.add(cities.get(i));
				}
				matchingCities = SortMethodsCity.mergeSort(cities, "pop");
				if (matchingCities.size() > 50)
					matchingCities = matchingCities.subList(matchingCities.size()-50, matchingCities.size());
				matchingCities = SortMethodsCity.reverseArr(matchingCities);
			}
			else if (input == 5)
			{
				String state = "";
				while (!checkValidState(state))
					state = Prompt.getString("Enter state name (ie. Alabama)");
				System.out.println("Fifty most populous states in "+state);
				for (int i = 0; i < cities.size(); i += 1)
				{
					if (cities.get(i).getStateName().equals(state))
						matchingCities.add(cities.get(i));
				}
				matchingCities = SortMethodsCity.mergeSort(cities, "pop");
				if (matchingCities.size() > 50)
					matchingCities = matchingCities.subList(matchingCities.size()-50, matchingCities.size());
				matchingCities = SortMethodsCity.reverseArr(matchingCities);
			}
			else if (input == 4)
			{
				System.out.println("Fifty cities sorted by name descending");
				startMillisec = System.currentTimeMillis();
				matchingCities = SortMethodsCity.mergeSort(cities, "name");
				matchingCities = matchingCities.subList(matchingCities.size()-50, matchingCities.size());
				matchingCities = SortMethodsCity.reverseArr(matchingCities);
			}
			else if (input == 3)
			{
				System.out.println("Fifty cities sorted by name");
				startMillisec = System.currentTimeMillis();
				matchingCities = SortMethodsCity.insertionSortName(cities);
				matchingCities = matchingCities.subList(0, 50);
			}
			else if (input == 2)
			{
				System.out.println("Fifty most populous cities");
				startMillisec = System.currentTimeMillis();
				matchingCities = SortMethodsCity.mergeSort(cities, "pop");
				matchingCities = matchingCities.subList(matchingCities.size()-50, matchingCities.size());
				matchingCities = SortMethodsCity.reverseArr(matchingCities);
			}
			else if (input == 1)
			{
				System.out.println("Fifty least populous cities");
				startMillisec = System.currentTimeMillis();
				matchingCities = SortMethodsCity.selectionSortPop(cities);
				matchingCities = matchingCities.subList(0, 50);
			}
			SortMethodsCity.printArray(matchingCities);
			endMillisec = System.currentTimeMillis();
			System.out.println("Elapsed Time "+(int)(endMillisec-startMillisec)+" millseconds");
		}
		System.out.println("Thanks for using Population!");
	}
}
