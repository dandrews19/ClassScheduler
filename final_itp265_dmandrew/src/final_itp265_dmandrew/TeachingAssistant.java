package final_itp265_dmandrew;

/**
 * Teaching Assistant extends Person and gives information on the professor that each teaching assistant works with and the class that they work with
 * @author Dylan Andrews
 * ITP 265, Spring 2021, Coffee Section
 * Email: dmandrew@usc.edu
 * @version: May 4, 2021
 *
 */
public class TeachingAssistant extends Person {
	// instance variables
	protected String professor;
	protected Class taClass;
	
	/**
	 * Constructor for the TeachingAssistant class
	 * @param name - string name for the Teaching Assistant
	 * @param professor -  string name for the professor that the teaching assistant works with
	 * @param clas - Class object representing the class the teaching assistant works with
	 */
	public TeachingAssistant(String name, String professor, Class clas) {
		super(name);
		
		this.professor = professor;
		this.taClass = clas;
	}
	/**
	 * getter for the professor name
	 * @return professor - a string representation of the professor's name that the teaching assistant works with
	 */
	public String getProfessor() {
		return professor;
	}
	
	
	/**
	 * setter for the professor variable
	 * @param professor - a string representation of the professor the teaching assistant works with
	 */
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	/**
	 * getter for the taClass variable
	 * @return taClass - a Class object representing the class that the teaching assistant works with
	 */
	public Class getTaClass() {
		return taClass;
	}

	/**
	 * setter for the taClass variable
	 * @param taClass - a Class object representing the class that the teaching assistant works with
	 */
	public void setTaClass(Class taClass) {
		this.taClass = taClass;
	}

	/**
	 * hashCode method for TeachingAssistant Class
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((taClass == null) ? 0 : taClass.hashCode());
		return result;
	}

	/**
	 * equals method for TeachingAssistant class
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeachingAssistant other = (TeachingAssistant) obj;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (taClass == null) {
			if (other.taClass != null)
				return false;
		} else if (!taClass.equals(other.taClass))
			return false;
		return true;
	}

	/**
	 * toString method for TeachingAssistant object
	 */
	@Override
	public String toString() {
		return "Teaching Assistant " + this.getName() + " works with Professor " + this.getProfessor(); 
	}
	
	
	

}
