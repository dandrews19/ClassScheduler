package final_itp265_dmandrew;

/**
 * This department enum describes which department each class can be from, as well as the majors 
 * (not all majors included, any input other than MATH, PSYC, HIST, SPAN, ITP, and CSCI is considered an "OTHER" major)
 * @author Dylan Andrews
 * ITP 265, Spring 2021, Coffee Section
 * Email: dmandrew@usc.edu
 * @version: May 4, 2021
 *
 */
public enum Department {
	// different departments
	MATH(new String[] {"MATH"}),
	PSYC(new String[] {"PSYC", "PSYCH"}),
	HIST(new String[] {"HIST"}),
	SPAN(new String[] {"SPAN"}),
	ITP(new String[] {"ITP"}),
	CSCI(new String[] {"CSCI"}),
	OTHER(new String[] {});

	private String[] names;
	
	/**
	 * Constructor that assigns the names to a variable
	 * @param names - a list of strings representing the names of the departments
	 */
	private Department(String[] names) {
		this.names = names;
	}
	
	/**
	 * a method that matches a string to a department name
	 * @param name - as string that represents a name of a department someone wants to find
	 * @return dept - the department match found
	 */
	public static Department matchDepartment(String name) {
		Department dept = OTHER;
		for(Department s: Department.values()) {
			for(String n: s.names) {
				if(n.equalsIgnoreCase(name))
					dept = s;
			}
		}
		
		return dept;
	}
	
	
	/**
	 * a method that prints out the departments by their name
	 */
	 public static void printDepts(){
         for(int i = 1 ; i <= Department.values().length; i++){
             System.out.println("- " + Department.values()[i-1].toString());
            }
    }
	
}
