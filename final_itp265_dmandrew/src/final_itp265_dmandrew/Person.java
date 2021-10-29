package final_itp265_dmandrew;

/**
 * This Person abstract class gives basic information on a Person for this project (just their name).
 * @author Dylan Andrews
 * ITP 265, Spring 2021, Coffee Section
 * Email: dmandrew@usc.edu
 * @version: May 4, 2021
 *
 */
public abstract class Person {
	
	// name instance variable
	protected String name;
	/**
	 * Constructor for the Person Class
	 * @param name - string representing the name of the P
	 */
	public Person(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * getter for the name
	 * @return name - string representing the name for the person
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter for the name
	 * @param name - string representing the name for the person
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * hashCode method for Person object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/**
	 * equals method for person Object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * toString method for Person object
	 */
	@Override
	public String toString() {
		return "Person " + this.getName();
	}
	
	
	

}
