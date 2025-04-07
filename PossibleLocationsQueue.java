package project3;

public class PossibleLocationsQueue implements PossibleLocations {

	Location queue[];
	int capacity;
	int size = 0;
	
	/**
     * Default constructor creating a queue with a capacity of 100
     */
	public PossibleLocationsQueue() {
		capacity = 100;
		queue = new Location[capacity];
	}
	
	/**
	    * Creates a queue with the given capacity.
	    * @param cap capacity of the queue
	    */
	public PossibleLocationsQueue(int cap) {
		capacity = cap;
		queue = new Location[capacity];
	}
	
	/**
	    * Add a Location object to this collection.
	    * @param s object to be added
	    * @throws NullPointerException if the given location is null
	    */
	public void add(Location s) {
		if (s == null) {
			throw new NullPointerException();
		}
		
		if (capacity == size) {
			capacity *= 2;
			Location newQueue[] = new Location[capacity];
			for (int i=0; i<size; i++) {
				newQueue[i] = queue[i];
			}
			
			queue = newQueue;
		} 
		
		queue[size] = s;
		size++;
		
	}

	/**
     * Remove the next object from this collection. The specific
     * item returned is determined by the underlying structure
     * by which this collection is represented.
     * @return the next object, or null if set is empty
     */
	public Location remove() {
		if (size == 0) {
			return null;
		} 
		
		Location removed = queue[0];
		for (int i=0; i<size-1; i++) {
			queue[i] = queue[i+1];
		}
		
		size--;
		return removed;
	}

	/**
     * Determines if this collection is empty or not.
     * @return  true, if set is empty, false, otherwise.
     */
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/**
     * Returns the string representation of this collection.
     * The string representation consists of a list of the collection's
     * elements in the order they would be removed and returned by future
     * calls to remove(). The elements should be enclosed in square brackets (`"[]"`).
     * Adjacent elements are separated by the characters `", "` (comma and space).
     * @return the string representation of this collection
     */
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		
		String print = "[" + queue[0];
		
		for (int i=1; i<size; i++) {
			print += ", " + queue[i];
		}
		
		return print + "]";
	}

}
