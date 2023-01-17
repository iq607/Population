import java.util.ArrayList;
/**
 *	SortMethodsCity - Sorts an arraylist of cities in ascending order.
 * use in conjunction with Population.java
 *
 *	@author Iris Quan
 *	@since January 9, 2023
 */
 
public class SortMethodsCity {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		arraylist of city objects to sort
	 *  @return 		sorted arraylist
	 
	public ArrayList<City> bubbleSort(ArrayList<City> arr) 
	{
		for (int outer = arr.size() - 1; outer > 0; outer -= 1)
		{
			for (int inner = 0; inner < outer; inner += 1)
			{
				if (arr.get(inner).compareTo(arr.get(inner+1)) > 0)
					arr = swap(arr, inner, inner+1);
			}
		}
		return arr;
	}
	*/
	
	/**
	 *	Swaps two city objects in arraylist arr
	 *	@param arr		arraylist of city objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 * 	@return 		arraylist with swapped positions
	 */
	private static ArrayList<City> swap(ArrayList<City> arr, int x, int y) 
	{
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
		return arr;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending population order 
	 *	@param arr		arraylist of city objects to sort
	 * 	@return			sorted arraylist
	 */
	public static ArrayList<City> selectionSortPop(ArrayList<City> arr) 
	{
		int maxIndex = 0;
		for (int outer = arr.size() - 1; outer > 0; outer -= 1)
		{
			for (int inner = 0; inner <= outer; inner += 1)
			{
				if (arr.get(inner).compareTo(arr.get(maxIndex)) > 0)
					maxIndex = inner;
			}
			arr = swap(arr, outer, maxIndex);
		}
		return arr;
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending alphabetical order
	 *	@param arr		arraylist of city objects to sort
	 * 	@return			sorted arraylist
	 */
	public static ArrayList<City> insertionSortName(ArrayList<City> arr) 
	{
		City temp = null;
		for (int outer = 1; outer < arr.size(); outer += 1)
		{
			for (int inner = 0; inner <= outer; inner += 1)
			{
				if (arr.get(outer).compareTwo(arr.get(inner)) < 0)
				{
					temp = arr.get(outer);
					for (int i = outer; i > inner; i -= 1)
						arr.set(i, arr.get(i-1));
					arr.set(inner, temp);
				}
			}
		}
		return arr;
	}
	
	/**
	 *	Merge Sort algorithm - in descending order by type
	 *	@param arr		arraylist of city objects to sort
	 * 	@param type		"pop" for population, "name" for city name
	 * 	@return 		sorted arraylist
	 */
	public static ArrayList<City> mergeSort(ArrayList<City> arr, String type) 
	{
		ArrayList<City> tempArr = recursiveArr(arr, type);
		for(int i = 0; i < arr.size(); i++){
			arr.set(i, tempArr.get(i));
		}
		return arr;
	}
	
	/**
	 *	recursive array that returns mergesorted version of arr, ascending
	 *	@param arr		arraylist of city objects to sort
	 * 	@param type		"pop" for population, "name" for city name
	 * 	@return		mergesorted arraylist
	 */
	public static ArrayList<City> recursiveArr(ArrayList<City> arr, String type)
	{
		ArrayList<City> arr1 = null;
		ArrayList<City> arr2 = null;
		if (arr.size() == 1 || arr.size() == 2)
		{
			if (arr.size() == 2)
			{
				if ((type.equals("pop") && arr.get(0).compareTo(arr.get(1)) > 0)||
					(type.equals("name") && arr.get(0).compareTwo(arr.get(1)) > 0))
				{
					City temp = arr.get(0);
					arr.set(0, arr.get(1));
					arr.set(1, temp);
				}
			}
			return arr;
		}
		else
		{
			arr1 = recursiveArr(splitArr(arr, 0, arr.size()/2), type);
			arr2 = recursiveArr(splitArr(arr, arr.size()/2+1, arr.size()-1), type);
			return mergeArr(arr1, arr2, type);
		}
	}
	
	/**
	 *	merges 2 given city arraylists, ascending order by type
	 *	@param arr1		first array
	 * 	@param arr2		second array
	 * 	@param type		"pop" for population, "name" for city name
	 * 	@return 	merged array
	 */
	public static ArrayList<City> mergeArr(ArrayList<City>arr1, ArrayList<City> arr2, String type)
	{
		ArrayList<City> mergedArr = new ArrayList<City>(arr1.size() + arr2.size());
		int index1 = 0;
		int index2 = 0;
		while (index1 + index2 < mergedArr.size())
		{
			if (index1 < arr1.size() && index2 < arr2.size())
			{
				if ((type.equals("pop") && arr1.get(index1).compareTo(arr2.get(index2)) > 0) ||
					(type.equals("name") && arr1.get(index1).compareTwo(arr2.get(index2)) > 0))
				{
					mergedArr.set(index1+index2, arr2.get(index2));
					index2 += 1;
				}
				else
				{
					mergedArr.set(index1+index2, arr1.get(index1));
					index1 += 1;
				}
			}
			else
			{
				if (index1 == arr1.size())
				{
					mergedArr.set(index1+index2, arr2.get(index2));
					index2 += 1;
				}
				else if (index2 == arr2.size())
				{
					mergedArr.set(index1+index2, arr1.get(index1));
					index1 += 1;
				}
			}
		}
		return mergedArr;
	}
	
	/**
	 *	splits an array to get desired sub-array
	 *	@param arr		arraylist of city objects to split
	 *	@param start	starting index of sub-array, inclusive
	 * 	@param end		ending index of sub-array, inclusive
	 * 	@return 	the sub-array
	 */
	public static ArrayList<City> splitArr(ArrayList<City> arr, int start, int end)
	{
		ArrayList<City> splittedArr = new ArrayList<City>(end - start + 1);
		for (int i = start; i < end + 1; i += 1)
			splittedArr.set(i-start, arr.get(i));
		return splittedArr;
	}
	
	/**
	 * reverses an array
	 * 	@param arr 		arraylist of city objects
	 * 	@return			the reversed arraylist
	 */
	public static ArrayList<City> reverseArr(ArrayList<City> arr)
	{
		for (int i = 0; i < arr.size()/2; i += 1)
		{
			City temp = arr.get(i);
			arr.set(i, arr.get(arr.size() - i - 1));
			arr.set(arr.size() - i - 1, temp);
		}
		return arr;
	}
	
	/*****************************************************************/
	/************************* yeah ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an arraylist of cities to the screen
	 *	@param arr		the arraylist of cities
	 */
	public static void printArray(ArrayList<City> arr) {
		System.out.printf("%4s%-22s%-22s%-13s%11s\n","","State","City",
			"Type","Population");
		for (int i = 0; i < arr.size(); i += 1)
			System.out.printf("%2d: %s\n", i + 1, arr.get(i));
	}
}
