/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Iris Quan
 *	@since	1/9/23
 */
public class City implements Comparable<City> {
	
	// fields
	private String cityName;
	private String cityType;
	private String stateName;
	private int population;
	
	// constructor
	public City(String cName, String cType, String sName, int pop)
	{
		cityName = cName;
		cityType = cType;
		stateName = sName;
		population = pop;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other)
	{
		if (population != other.getPopulation())
			return population - other.getPopulation();
		else if (!(stateName.equals(other.getStateName())))
			return stateName.compareTo(other.getStateName());
		else
			return cityName.compareTo(other.getCityName());	
	}
	
	/**	Compare two cities names
	 *	@param other		the other City to compare
	 *	@return				positive if other city's name is further along 
	 * 						alphabet than this city's name, else negative
	 */
	public int compareTwo(City other)
	{
		return cityName.compareTo(other.getCityName());
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other)
	{
		return cityName.equals(other.getCityName()) && stateName.equals(other.getStateName());
	}
	
	/**	Accessor methods */
	public String getCityName() {	return cityName;}
	public String getCityType() {	return cityType;}
	public String getStateName() { 	return stateName;}
	public int getPopulation() {	return population;}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName,
						cityType, population);
	}
}
