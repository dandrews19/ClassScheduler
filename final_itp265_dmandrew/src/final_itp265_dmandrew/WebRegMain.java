package final_itp265_dmandrew;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;

/**
 * This the main class of the program that carries out all of the functionality necessary to run the program,
 * from displaying the menu to carrying out specific functions based on user input
 * @author Dylan Andrews
 * ITP 265, Spring 2021, Coffee Section
 * Email: dmandrew@usc.edu
 * @version: May 6, 2021
 *
 */

public class WebRegMain {
	// instance variables, with the purpose of providing the functions the necessary information to carry out their processes
	private BFF bff;
	private List<Class> classes;
	private HashMap<String, ArrayList<Class>> professors;
	private Map<String, ArrayList<String>> users;
	private String major;
	private ArrayList<Class> userClasses;
	private ArrayList<Professor> professorObjects;
	private double userUnits;
	private String username;
	private boolean newUser;
	private final double UNIT_LIMIT = 18.0;
	private ArrayList<TeachingAssistant> taObjects;
	private String userPassword;
	
	
	/**
	 *
	 *  constructor:
	 *  takes in user info, if the user is new, prompts them to create an account with a password, if a user isn't new
	 * it reads in their data and allows them to access the class they previously enrolled in
	 */
	public WebRegMain() {
		bff = new BFF();
		classes = readClassFile("src/allData.tsv");
		professors = readProfessorFile("src/allData.tsv");
		users = readUserFile("src/UserInfo.tsv");
		professorObjects = readProfessorData();
		taObjects = readTaData();
		System.out.println("Welcome to USC's Web Registration Portal");
		int loginOption = bff.inputInt("Would you like to: \n1) Login \n2) Sign Up", 1, 2);
		// login in vs sign up if/else
		if (loginOption == 1) {
			username = bff.inputWord("Please enter your username");
			while (!users.containsKey(username)) {
				username = bff.inputWord("Please enter a valid username (reminder: usernames are case sensitive)");
			}
			String password = bff.inputWord("Please enter your password");
			while (!password.equals(users.get(username).get(0))) {
				password = bff.inputWord("Incorrect password (reminder: passwords are case sensitive");
			}
			userPassword = password;
			major = users.get(username).get(1);
			userClasses = classesToList(username);
			userUnits = getUnits(userClasses);
			newUser = false;
			
			
		}
		else {
			username = bff.inputWord("Please enter your desired username");
			while (users.containsKey(username)) {
				username = bff.inputWord("Username has been taken, please select another");
			}
			String password = bff.inputWord("Please enter your desired password");
			String confirmedPassword = "";
			while(!confirmedPassword.equals(password)) {
				confirmedPassword = bff.inputLine("Please confirm your password");
				if (!confirmedPassword.equals(password)) {
					System.out.println("Incorrect match, please try again");
				}
			
			}
			System.out.println("What is your major? Here are the options: ");
			Department.printDepts();
			String choice = bff.inputWord("");
			major = Department.matchDepartment(choice).name();
			userPassword = confirmedPassword;
			
			userClasses = new ArrayList<Class>();
			userUnits = 0.0;
			newUser = true;
			
			
			
			
		}
		
	}
	/**
	 * 
	 * This teaching assistant method reads Teaching Assistant data from the allData file
	 * @return ArrayList of TeachingAssistant objects
	 */
	private ArrayList<TeachingAssistant> readTaData() {
		ArrayList<TeachingAssistant> ta = new ArrayList<TeachingAssistant>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("src/allData.tsv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split("	");
		        ta.add(new TeachingAssistant(values[7], values[2], new Class(values[4], Department.matchDepartment(values[1]), values[2], values[7], Double.parseDouble(values[0]), Double.parseDouble(values[5]))));
	        	
		        
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return ta;
	}

	/**
	 * this method reads in data from the allData method and transforms it into professor data
	 * @return prof, and ArrayList of Professor objects
	 */
	private ArrayList<Professor> readProfessorData() {
		ArrayList<Professor> prof = new ArrayList<Professor>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("src/allData.tsv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split("	");
		        for(Entry<String, ArrayList<Class>> s: professors.entrySet()) {
		        	if (s.getKey().equalsIgnoreCase(values[2])) {
		        		Professor p = new Professor(values[2], s.getValue(), Double.parseDouble(values[6]));
		        		if (!prof.contains(p)) {
		        			prof.add(p);
		        		}
		        	}	
				}
	        	
		        
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		return prof;
		
	}

	/**
	 * this method calls on the appropriate functions based on user input, prints menu from Menu enum
	 */
	public void run() {
		
		boolean keepGoing = true;
		
		while (keepGoing == true) {
			System.out.println("");
			Menu.printMenu();
			int choice = bff.inputInt(">", 1, Menu.getNumOptions());
			Menu option = Menu.getOption(choice);
			switch(option){

			case PRINT_CLASS_LIST: 
				printClassList();
				break;
			case  PRINT_CLASS_BY_DEPT: 
				printClassByDept();
				break;
			case PRINT_CLASS_BY_PROFESSOR: 
				printClassByProfessor();
				break;
			case FIND_CLASS_BY_NAME: 
				BFF helper = new BFF();
				String input = helper.inputLine("Enter the name of the class you wish to find");
				Class c = findClassByName(input);
				if (c== null) {
					System.out.println("No class was found based on the input \"" + input + "\"");
					
				}
				else {
					System.out.println("Here was what was found for the input \""   + input + "\"");
					System.out.println("\t" + c.toString());
				}
				

				break;
			case ADD_CLASS: 
				addClass();
				break;
			case DROP_CLASS: 
				dropClass();
				
				break;
			case PRINT_SCHEDULE: 
				printSchedule();
				
				break;
			case GET_PROFESSORS_BY_RATING: 
				getProfessorsByRating();
				
				break;
			case GET_CLASSES_BY_RATING:
				getClassesByRating();
				break;
			case PRINT_TA_INFO:
				printTAInfo();
				break;
			case QUIT:  
				System.out.println("Saving user data...");
				saveUsersToFile();
				keepGoing = false; 
				break;
			}
		}
		bff.print("Goodbye");
	}
		


	/**
	 * this method prints all the information about TeachingAssistants that is stored in the taObjects ArrayList
	 */
	private void printTAInfo() {
		System.out.println("Here are all of the Teaching Assistants:");
		for(TeachingAssistant ta: taObjects) {
			System.out.println("\t" + ta.toString());
		}
		
	}

	/**
	 * this method prints out all of the classes from highest to lowest rating, 
	 * using the comparable interface to sort the classes
	 * 
	 */
	private void getClassesByRating() {
		System.out.println("Here is a list of classes, sorted by rating:");
		classes.sort(null);
		for(Class c: classes) {
			System.out.println("\t" + c.toString());
		}
		
	}
	
	/**
	 * this function takes in all the user info from the users HashMap and saves it back into the UserInfo.tsv file it came from,
	 * along with any changes made throughout the course of the program
	 */
	private void saveUsersToFile() {
		ArrayList<String> saving = new ArrayList<String>();
		saving.add(this.userPassword);
		saving.add(this.major);
		for (Class c: userClasses) {
			saving.add(c.getName());
		}
		if (users.containsKey(this.username)) {
			users.remove(this.username);
		}
		
		users.put(this.username, saving);
		try {
			FileWriter tsvWriter = new FileWriter("src/UserInfo.tsv");
			tsvWriter.append("username");
			tsvWriter.append("	");
			tsvWriter.append("password");
			tsvWriter.append("	");
			tsvWriter.append("major");
			
			for (int i = 1; i < 19; i++) {
				tsvWriter.append("	");
				tsvWriter.append("class" + String.valueOf(i));
				
			}
			
			for(Entry<String, ArrayList<String>> d: users.entrySet()) {
				tsvWriter.append("\n");
				tsvWriter.append(d.getKey());
				
				for(String s: d.getValue()) {
					tsvWriter.append("	");
					tsvWriter.append(s);
				}
				
			}
			tsvWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * This method prints out a list of professors, sorted by rating using the Comparable interface
	 */
	private void getProfessorsByRating() {
		System.out.println("Here is a list of professors, sorted by rating:");
		professorObjects.sort(null);
		for(Professor p: professorObjects) {
			System.out.println("\t" + p.toString());
		}
		
	}

	
	/**
	 * This functions takes in a user's schedule and prints it out, along with information about the major and GE classes that
	 * are fulfilled on the schedule, and the units the user has taken on so far
	 */
	private void printSchedule() {
		ArrayList<String> levels = new ArrayList<>();
		ArrayList<String> categories = new ArrayList<>();
		if (!this.userClasses.isEmpty()) {
			System.out.println("Here is your schedule of classes:");
			for(Class c: this.userClasses) {
				if(c.getDept().toString().equals(this.major)) {
					MajorClass m = new MajorClass(c.getName(), c.getDept(), c.getProf(), c.getTa(), c.getUnits(), c.getRating());
					String level = String.valueOf(m.getLevel());
					levels.add(level);
				}
				else {
					GenEd m = new GenEd(c.getName(), c.getDept(), c.getProf(), c.getTa(), c.getUnits(), c.getRating());
					String category = m.getGeCategory();
					categories.add(category);
					
				}
				
				System.out.println("\t" + c.toString());
				
				
			}
			System.out.println("\nCurrent # of units (out of " + this.UNIT_LIMIT + "):" + this.userUnits);
			if (!levels.isEmpty()) {
				System.out.print("Major classes on schedule: ");
				for(String s: levels) {
					System.out.print(this.major + " " + s + ",");
				}
				System.out.println("");
			}
			else {
				System.out.println("No major classes on schedule");
			}
			if (!categories.isEmpty()) {
				System.out.print("GE Categories satisfied: ");
				for(String s: categories) {
					System.out.print(s + ",");
				}
				System.out.println("");
			}
			else {
				System.out.println("No GE classes on schedule");
			}
			
			
		}
		else {
			System.out.println("No classes on schedule");
		}
		
	}
	/**
	 * This method drops a class from the user's schedule based on user input for the name
	 */
	private void dropClass() {
		BFF helper = new BFF();
		String input = helper.inputLine("Enter the name of the class you wish to drop");
		boolean found = false;
		
		for(Class c: this.userClasses) {
			if (c.getName().equalsIgnoreCase(input)) {
				this.userClasses.remove(c);
				this.userUnits -= c.getUnits();
				System.out.println("Successfully removed " + c.toString() + " from your schedule");
				found = true;
				break;
			}
		}
		if (found == false) {
			System.out.println("No class was found on your schedule with the name \"" + input + "\"");
		}
		
	}
	
	
	/**
	 * this method adds a class to the user's schedule based on user input for the name, taking into account the amount of units
	 * the user has already accrued
	 */
	private void addClass() {
		if (this.userUnits >= this.UNIT_LIMIT) {
			System.out.println("You are currently at the unit limit. Please drop a class or contact your advisor to make changes");
		}
		else {
			BFF helper = new BFF();
			String input = helper.inputLine("Enter the name of the class you wish to add");
			Class c = findClassByName(input);
			
			if (c != null) {
				if(c.getUnits() + this.userUnits > this.UNIT_LIMIT) {
					System.out.println("You are unable to add this class due to a unit limit. Please drop a class or contact your advisor to make changes");
				}
				else if (this.userClasses.contains(c)) {
					System.out.println("You have already this class to your schedule, cannot have duplicate courses");
				}
				else {
					this.userClasses.add(c);
					this.userUnits += c.getUnits();
					System.out.println("Successfully added " + c.toString() + " to your schedule");
				
					
				}
			}
			else {
				System.out.println("No class could be found based on the input \"" + input + "\"");
			}
		}
		
	}
	
	/**
	 * This method finds a class from the list of classes based on user input
	 * @param input, input from the user for the name of the class they wish to find
	 * @return the class that the user wanted to find, if no class is found, returns null
	 */
	private Class findClassByName(String input) {
		boolean found = false;
		for(Class c: classes) {
			if (c.getName().equalsIgnoreCase(input)) {
				found = true;
				return c;
				
			}
		}
		if (found == false) {
			System.out.println("No class with the name \"" + input + "\" was found" );
			return null;
		}
		return null;
		
	}
	/**
	 * this method prints all the classes that are taught by a specified professor, based on user input for
	 * the professor's name
	 */
	private void printClassByProfessor() {
		BFF bff = new BFF();
		String professorName = bff.inputLine("Please enter the professor you'd like to find: ");
		if(professors.containsKey(professorName) ) {
			System.out.println("Here is a look at classes taught by Professor " + professorName + ": ");
			ArrayList<Class> c = professors.get(professorName);
			for(Class h: c) {
				System.out.println("\t" + h.toString());
			}
			
		}
		else {
			System.out.println(professorName + " was not found");
			
		}
		
	}
	
	/**
	 * This method prints all the classes relating to a specific department,
	 * based on user input for the department's name
	 */
	private void printClassByDept() {
		BFF bff = new BFF();
		String dept = bff.inputWord("Please input the department that you would like to see classes for.");
		Department de = Department.matchDepartment(dept);
		System.out.println("Here's all the classes in the " + de.toString() + " department");
			for(Class c: classes) {
				if(c.getDept().equals(de)) {
					System.out.println("\t" + c.toString());
				}
			}
		}
		

	/**
	 * This method prints out a list of all the classes, no specific order
	 */
	private void printClassList() {
		System.out.println("Here is a list of all the classes");
		int counter = 1;
		for(Class c: classes) {
			System.out.println(counter + ": " + c.toString());
			counter += 1;
		}
		
	}

	
	
	/**
	 * This method gets the number of units a user is enrolled in based on class schedule
	 * @param userClasses2, an ArrayList of Class objects in the user classes
	 * @return units, the number of units a user is enrolled in
	 */
	private double getUnits(ArrayList<Class> userClasses2) {
		double units = 0.0;
		for(Class c: userClasses2) {
			units += c.getUnits();
		}
		return units;
	}


	/**
	 * converts the list of classes in the user HashMap to the an ArrayList of Class objects that can 
	 * be manipulated in the program
	 * @param username, the user's username that they stored in the UserInfo file
	 * @return userClasses, an ArrayList storing information on the user's classes
	 */
	private ArrayList<Class> classesToList(String username) {
		int length = users.get(username).size();
		ArrayList<Class> userClasses = new ArrayList<Class>();
		if (length > 2) {
			for(int i = 2; i < length; i++) {
				String c = users.get(username).get(i);
				for(Class h: classes) {
					if(h.getName().equalsIgnoreCase(c)) {
						userClasses.add(h);
						break;
					}
				}
		
			}
		}
		return userClasses;
	}


	/**
	 * This method reads user data from the UserInfo file and returns a HashMap containing all the information
	 * from all the users in the system
	 * @param string, a name of the file where the user information is stored (for this project, will always be UserInfo.tsv)
	 * 
	 * @return userMap, a HashMap containing information on each user and their classes
	 */
	private Map<String, ArrayList<String>> readUserFile(String string) {
		HashMap<String, ArrayList<String>> userMap = new HashMap<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(string))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split("	");
		        userMap.put(values[0], new ArrayList<String>());
		        userMap.get(values[0]).add(values[1]);
		        userMap.get(values[0]).add(values[2]);
		        if (values.length > 2) {
		        	for(int i = 3; i < values.length; i++) {
		        		userMap.get(values[0]).add(values[i]);
		        	}
		        }
		        
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		
		
		return userMap;
	}


	/**
	 * This method reads the information on professors and returns a HashMap of their name and an ArrayList
	 * of the classes they teach
	 * @param string, the file where the professor data comes from (for this project, will always be allData.tsv)
	 * @return professorClasses, a HashMap containing the professor's name as the key and an ArrayList of their classes
	 */
	private HashMap<String, ArrayList<Class>> readProfessorFile(String string) {
		HashMap<String, ArrayList<Class>> professorClasses= new HashMap<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(string))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split("	");
		        if (!professorClasses.containsKey(values[2])) {
		        	professorClasses.put(values[2], new ArrayList<Class>());
		        	
		        	professorClasses.get(values[2]).add(new Class(values[4], Department.matchDepartment(values[1]), values[2], values[7], Double.parseDouble(values[0]), Double.parseDouble(values[5])));
		        	
		        } 
		        else {
		        	professorClasses.get(values[2]).add(new Class(values[4], Department.matchDepartment(values[1]), values[2], values[7], Double.parseDouble(values[0]), Double.parseDouble(values[5])));
		        }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		return professorClasses;
	}


	/**
	 * This method reads the file containing the information on classes and returns an ArrayList of containing all the class Objects
	 * @param string, the file that the information is read from
	 * @return classes, an ArrayList of Class objects
	 */
	private List<Class> readClassFile(String string) {
		ArrayList<Class> classes = new ArrayList<Class>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(string))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split("	");
		        classes.add(new Class(values[4], Department.matchDepartment(values[1]), values[2], values[7], Double.parseDouble(values[0]), Double.parseDouble(values[5])));
	        	
		        
		    }
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		
		return classes;
	}
	
	
	/**
	 * The main program, just calls the run() method
	 * @param args
	 */
	public static void main(String[] args) {
		WebRegMain program = new WebRegMain();
		program.run();

	}



	

	
	
}
