package project3;

public class PossibleLocationsStack implements PossibleLocations {
	
	private class Node {
		Location location;
		Node next;
		
		public Node(Location loc) {
			location = loc;
		}
		
		public Location getLoc() {
			return location;
		}
		
		public void setNext(Node n) {
			next = n;
		}
		
		public Node getNext() {
			return next;
		}
	}
	
	Node list = null;
	
	/**
	    * Add a Location object to this collection.
	    * @param s object to be added
	    * @throws NullPointerException if the given location is null
	    */
	public void add(Location s) {
		if (s == null) {
			throw new NullPointerException();
		}
		
		if (list == null) {
			list = new Node(s);
		} else {
			Node newNode = new Node(s);
			newNode.setNext(list);
			list = newNode;
		}
	}

	/**
     * Remove the next object from this collection. The specific
     * item returned is determined by the underlying structure
     * by which this collection is represented.
     * @return the next object, or null if set is empty
     */
	public Location remove() {
		if (list == null){
			return null;
		}
		else{
			Location loc = list.getLoc();
			list = list.getNext();
			return loc;	
		}
	}

	/**
     * Determines if this collection is empty or not.
     * @return  true, if set is empty, false, otherwise.
     */
	public boolean isEmpty() {
		return (list == null);
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
		
		Node node = list;
		String print = "[" + node.getLoc();
		node = node.getNext();
		
		while (node != null) {
			print += ", " + node.getLoc();
			node = node.getNext();
		}
		
		return print + "]";
	}

}
