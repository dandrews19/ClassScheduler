package final_itp265_dmandrew;

/**
 * GenEd class is an extension of the Class class and describes the GE category satisfied by the course
 * @author Dylan Andrews
 * ITP 265, Spring 2021, Coffee Section
 * Email: dmandrew@usc.edu
 * @version: May 4, 2021
 *
 */
public class GenEd extends Class {
	// instance variable
	protected String geCategory;
	
	/**
	 * Constructor for the GenEd class
	 * @param name - the name of the class
	 * @param dept - the Department the class is in (from the Department enum)
	 * @param prof - the name of the professor who teaches the class
	 * @param ta - the name of the teaching assistant who teaches the class
	 * @param units - the units the class is worth
	 * @param rating - the rating of the class out of 5
	 */
	public GenEd(String name, Department dept, String prof, String ta, double units, double rating) {
		super(name, dept, prof, ta, units, rating);
		this.geCategory = name.substring(0, 1); // since i don't have the data on the categories, I'm just taking the first letter of the name
	}
	/**
	 * getter for the category that the GE class falls under
	 * @return geCategory - a string representing the category the class falls under
	 */
	public String getGeCategory() {
		return geCategory;
	}

	/**
	 * setter for the geCategory
	 * @param geCategory - the geCategory the class falls under
	 */
	public void setGeCategory(String geCategory) {
		this.geCategory = geCategory;
	}
	/**
	 * hashCode method for the GenEd class
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((geCategory == null) ? 0 : geCategory.hashCode());
		return result;
	}
	/**
	 * equals method for GenEd class
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenEd other = (GenEd) obj;
		if (geCategory == null) {
			if (other.geCategory != null)
				return false;
		} else if (!geCategory.equals(other.geCategory))
			return false;
		return true;
	}
	
	/**
	 * toString method summarizing the info of the GenEd object
	 */
	@Override
	public String toString() {
		return "General Education class " + this.getName() + " with Professor " + this.getProf() + " in the " + this.getDept().toString() + " department, for " + 
				this.getUnits() + " units, and satisfies category " + this.getGeCategory() + " GE requirement";
	}
	

}
