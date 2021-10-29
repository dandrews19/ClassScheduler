package final_itp265_dmandrew;
import java.util.Random;

/**
 * MajorClass extends the Class class and indicates which level the major course fulfills
 * @author Dylan Andrews
 * ITP 265, Spring 2021, Coffee Section
 * Email: dmandrew@usc.edu
 * @version: May 4, 2021
 *
 */
public class MajorClass extends Class {
	
	// instance variable
	protected int level;
	
	/**
	 * Constructor for the MajorClass class
	 * @param name - name of the class
	 * @param dept - Department the class is in (from the Department enum)
	 * @param prof - the name of the professor who teaches the class
	 * @param ta - the name of the teaching assistant who works with the class
	 * @param units - the number of units the class is worth
	 * @param rating - the rating of the class (out of 5)
	 */
	public MajorClass(String name, Department dept, String prof, String ta, double units, double rating) {
		super(name, dept, prof, ta, units, rating);
		Random rand = new Random();
		this.level = rand.nextInt(400) + 100; // gets random level between 100 and 500 since I don't have data for class levels
	}
	/**
	 * getter for the level of the class
	 * @return level - the level of the class from 100 to 500
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * setter for the level of the class
	 * @param level - an integer representing the level of the class from 100 to 500
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * hashCode method for the MajorClass class
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + level;
		return result;
	}

	/**
	 * equals method for the MajorClass class
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MajorClass other = (MajorClass) obj;
		if (level != other.level)
			return false;
		return true;
	}
	
	/**
	 * toString method returning a string summarizing the information of the MajorClass object
	 */
	@Override
	public String toString() {
		return "Major class " + this.getName() + " with Professor " + this.getProf() + " in the " + this.getDept().toString() + " department, for " + 
				this.getUnits() + " units, and is a level " + this.getLevel() + " major class";
	}
	
	

	
	
	

}
