package final_itp265_dmandrew;

/**
 * This Menu enum contains all the menu options and their descriptions
 * @author Dylan Andrews
 * ITP 265, Spring 2021, Coffee Section
 * Email: dmandrew@usc.edu
 * @version: May 4, 2021
 *
 */
public enum Menu {
	// menu options and descriptions
	PRINT_CLASS_LIST("Print out all of the classes on the class list"),
	 PRINT_CLASS_BY_DEPT("Print all the classes by a selected department"),
	 PRINT_CLASS_BY_PROFESSOR("Print all the classes by a select professor"),
	 FIND_CLASS_BY_NAME("Find a class by specifying its name"),
	 ADD_CLASS("Add a class to your schedule"),
	 DROP_CLASS("Drop a class from your schedule"),
	 PRINT_SCHEDULE("Print your class schedule"),
	 GET_PROFESSORS_BY_RATING("Returns a list of the professors, ranked by rating from high to low"),
	 GET_CLASSES_BY_RATING("Returns a list of classes, ranked by rating from high to low"),
	 PRINT_TA_INFO("Returns a list of all the TA's and their info"),
	  QUIT("Quit");
	    
	private String description;
	    /**
	     * Constructor assigning a menu option to a description
	     * @param d - the description of the menu option
	     */
	    private Menu(String d){
	         this.description = d;   
	    }
	    
	    /**
	     * returns the Menu Option based on integer input
	     * @param num - the int input for the class number
	     * @return a department correlating with the integer input
	     */
	    public static Menu getOption(int num){
	       return Menu.values()[num-1];
	       
	    }
	    
	    /**
	     * method that prints out the menu in a user-friendly manner
	     */
	    public static void printMenu(){
	         for(int i = 1 ; i <= Menu.values().length; i++){
	             System.out.println(i + ": " + Menu.values()[i-1].description);
	            }
	    }
	    
	    /**
	     * method that returns the number of options in the menu
	     * @return an integer representing the number of options in the menu
	     */
	    public static int getNumOptions(){
	        return Menu.values().length;
	    }
}
