package training.supportbank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
    public static void main(String args[]) throws IOException {
       
    	String regex_one = ",(\\w+(\\s\\w+)*),(\\w+(\\s\\w+)*),(\\w+(\\s\\w+)*),(\\d+\\.\\d{0,2})";
    	ArrayList<String> nameList = new ArrayList<String>();				// List to store parsed names
    	ArrayList<Account> accountList = new ArrayList<Account>();
    	File csvFile = new File("Transactions2014.csv");					// Object for csv file
    	BufferedReader input = null;										// Reader to retrieve text from file
    	String current_line, parsed_name, parsed_amount_string;				// Strings for temp storage	
    	double parsed_amount_double = 0.0;
    	Pattern regexPattern = Pattern.compile(regex_one);					// Pattern object to compile regex
		Matcher regexMatcher = null;										// Matcher object to use pattern
		DecimalFormat df2 = new DecimalFormat (".##");
		
		
    	// ATTEMPT TO OPEN CSV FILE
		try {													
			input = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
		// READ LINE FROM CSV FILE	
		while((current_line = input.readLine()) != null){				// While loop reads text file line by line
			
			regexMatcher = regexPattern.matcher(current_line);			// Creates Matcher from regex
			
			// POPULATE NAMELIST/ACCOUNTLIST
			while(regexMatcher.find()) {								// Checks for regex match in current line
				
				parsed_amount_string = regexMatcher.group(7);
				parsed_amount_double = (Double.parseDouble(parsed_amount_string));
				
				parsed_name = regexMatcher.group(1);					// Reads regex group 2 ("From" column)
				if(!nameList.contains(parsed_name)) { 					// Checks to see if name is in nameList
					nameList.add(parsed_name);							// Adds new names to nameList 
					Account tempAccount = new Account(parsed_name);
					accountList.add(tempAccount);
				}
				
				for (Account index : accountList) {
					if(index.name.equals(parsed_name))
						index.updateBalance(-(parsed_amount_double));
				}
				
				parsed_name = regexMatcher.group(3);					// Reads regex group 3 ("To" column)
				if(!nameList.contains(parsed_name)) {					// Checks to see if name is in nameList
					nameList.add(parsed_name);							// Adds new names to nameList
					Account tempAccount = new Account(parsed_name);
					accountList.add(tempAccount);
				}
				
				for (Account index : accountList) {
					if(index.name.equals(parsed_name))
						index.updateBalance(parsed_amount_double);
				}
			}
		}
				
		System.out.println("Contents of accountList: \n");
		
		for (Account index : accountList) {
			System.out.println(index.name + " £" + df2.format(index.balance));
			//System.out.println(index.name + " £" + index.balance);
		}		
		input.close();
    }
}
