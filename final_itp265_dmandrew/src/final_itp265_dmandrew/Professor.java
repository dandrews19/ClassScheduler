package final_itp265_dmandrew;
import java.util.*;


/**
 * Professor class extends the Person class and gives information the classes they teach as well as their rating
 * @author Dylan Andrews
 * ITP 265, Spring 2021, Coffee Section
 * Email: dmandrew@usc.edu
 * @version: May 4, 2021
 *
 */
public class Professor extends Person implements Rating, Comparable<Professor>{
	
	// instance variables 
	protected List<Class> classes;
	protected double rating;
	
	/**
	 * Constructor for the Professor class
	 * @param name - string name for the Professor Object
	 * @param classes - List of classes that the professor teaches
	 * @param rate - double rating for the professor (out of 5)
	 */
	public Professor(String name, List<Class> classes, double rate) {
		super(name);
		this.classes = classes;
		this.rating = rate;
		
	}

	/**
	 * getter for the list of classes the Professor object teaches
	 * @return classes - List of Class objects
	 */
	public List<Class> getClasses() {
		return classes;
	}

	/**
	 * setter for the list of Classes
	 * @param classes - list of Class objects
	 */
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	/**
	 * getter for the rating of the Professor object
	 * @return rating - double representing the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * setter for the rating attribute of the Professor object
	 * @param rating - double representing the rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * hashCode method for Professor object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		double result = super.hashCode();
		result = prime * result + ((classes == null) ? 0 : classes.hashCode());
		result = prime * result + rating;
		return (int) result;
	}

	/**
	 * toString method for Professor Object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (classes == null) {
			if (other.classes != null)
				return false;
		} else if (!classes.equals(other.classes))
			return false;
		if (rating != other.rating)
			return false;
		return true;
	}
	
	// toString method
	@Override
	public String toString() {
		return "Professor " + this.getName() + " has a rating of " + this.getRating();
	}

	// compares professors based on their rating (implements Comparable interface)
	@Override
	public int compareTo(Professor o) {
		double compareRating = o.getRating();
		if (this.rating > compareRating) {
			return -1;
		}
		else if (Math.abs(this.rating - compareRating) <= 0.0001) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	
	
	
	
	
	
	
	

}
