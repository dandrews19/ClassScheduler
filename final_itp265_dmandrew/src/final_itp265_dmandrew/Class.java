package final_itp265_dmandrew;


/**
 * This Class class provides basic information of a class at school, such as professor, department, ta, name, units, and rating
 * @author Dylan Andrews
 * ITP 265, Spring 2021, Coffee Section
 * Email: dmandrew@usc.edu
 * @version: May 4, 2021
 *
 */
public class Class implements Rating, Comparable<Class>{

	// instance variables
	protected String name;
	protected Department dept;
	protected String prof;
	protected String ta;
	protected double units;
	protected double rating;
	
	/**
	 * Constructor for the Class class
	 * @param name - the name of the class
	 * @param dept - the department the class (from the department enum)
	 * @param prof - the name of the professor who teaches the class
	 * @param ta - the name of the teaching assistant who works in that class
	 * @param units - the units the class is worth
	 * @param rating - the rating of the class (randomly generated using R)
	 */
	public Class(String name, Department dept, String prof, String ta, double units, double rating) {
		super();
		this.name = name;
		this.dept = dept;
		this.prof = prof;
		this.ta = ta;
		this.units = units;
		this.rating = rating;
	}

	/**
	 * getter for the name
	 * @return name - the name of the class
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for the name of the class
	 * @param name - the name of the class
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter for the department the class is in
	 * @return department - the department that the class is in (one of the options in the Department enum)
	 */
	public Department getDept() {
		return dept;
	}

	/**
	 * setter for the Department that the class is in
	 * @param dept - department that the class is in (one of the options in the Department enum)
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}

	/**
	 * getter for the professor of the class
	 * @return prof - the string for the name of the professor who teaches the class
	 */
	public String getProf() {
		return prof;
	}

	/**
	 * setter for the name of the professor who teaches the class
	 * @param prof - the string for the name of the professor who teaches the class
	 */
	public void setProf(String prof) {
		this.prof = prof;
	}

	/**
	 * getter for the name of the teaching assistant
	 * @return ta - the string for the name of the teaching assistant
	 */
	public String getTa() {
		return ta;
	}

	/**
	 * setter for the name of the teaching assistant of the class
	 * @param ta - the string for the name of the teaching assistant for the class
	 */
	public void setTa(String ta) {
		this.ta = ta;
	}

	/**
	 * getter for the units the class is worth
	 * @return units - a double containing the number of units a class is worth
	 */
	public double getUnits() {
		return units;
	}

	/**
	 * setter for the units a class is worth
	 * @param units - a double containing the number of units a class is worth
	 */
	public void setUnits(double units) {
		this.units = units;
	}
	
	
	/**
	 * setter for the rating of the class
	 * @param rating - a double containing the rating (out of 5) for a class
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	/**
	 * getter for the rating of the class
	 * @return rating - a double containing the rating (out of 5) for a class
	 */
	public double getRating() {
		return rating;
	}


	/**
	 * hashCode method for the Class class
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dept == null) ? 0 : dept.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((prof == null) ? 0 : prof.hashCode());
		result = prime * result + ((ta == null) ? 0 : ta.hashCode());
		long temp;
		temp = Double.doubleToLongBits(units);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	/**
	 * equals method for the Class class
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Class other = (Class) obj;
		if (dept != other.dept)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (prof == null) {
			if (other.prof != null)
				return false;
		} else if (!prof.equals(other.prof))
			return false;
		if (ta == null) {
			if (other.ta != null)
				return false;
		} else if (!ta.equals(other.ta))
			return false;
		if (Double.doubleToLongBits(units) != Double.doubleToLongBits(other.units))
			return false;
		return true;
	}

	/**
	 * toString method for the Class class, returns a string of all the info the object contains
	 * @return a string summarizing the info of the Class object
	 */
	@Override
	public String toString() {
		return "Class " + this.getName() + " with Professor " + this.getProf() + " and Teaching Assistant " + this.getTa() + " in the " + this.getDept().toString() + " department, for " + 
	this.getUnits() + " units, and has a rating of " + this.getRating() + " out of 5.0";
	}


	/**
	 * compareTp method that compares classes based on their rating
	 * @return an integer indicating whether or not the class is rated higher than the one its being compared to	
	 */
	@Override
	public int compareTo(Class o) {
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
