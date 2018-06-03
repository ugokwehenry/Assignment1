# Assignment1

# Assignment-1 is a program that performs the follows:
1. It authenticates the user request, to ensuring the request is coming from an authorized dealer
2. It Validates the the order to ensuring the dealer is ordering a valid part
3. It process the user order request, and records it in the database for manufacturing and delivery
4. It returns a response to the dealer indicating success or failure.

# Libraries

# Installation Notes
The program is designed in java thus run in JVM. It will run on any machine that has JDK installed. 
Program execution steps:
 	
	a. Copy the program folder (Assignment1) to any directory, for e.g. A:/CS/Courses/QA/Assignment/Assignment-1/
	
 	b. Open the terminal or Command Prompt in windows 
	
	c. Navigate to the directory where the program folder was copied, for e.g. A:/CS/Courses/QA/Assignment/Assignment-1/
	
	d. Copy the xml file (either incomingOrder.xml) in the same directory for e.g. A:/CS/Courses/QA/Assignment/Assignment-1/
	
	e. Copy the incomingOrder.dtd file to the same directory e.g. A:/CS/Courses/QA/Assignment/Assignment-1/
	
	f. Type java -jar assignment1.jar on the command prompt to run the program. The program will start execution. It displays on the console and prompt for input parameter incomingOrder.xml {Please enter filename here :} 
	g. Enter the name of the xml file (same file that was copied to the program directory. the .xml file and assignment1.jar must reside on the same directory) on the console.
	
# Sample Reponses
If the xml file path and structure is valid and the part number and quantity are valid?, the system return the following responses;
	{File {authorizedMsg.xml} saved to directory ==> }
	{Authorized!}
	xml file is valid - true
    {File {partResponseSuccessMessage.xml} saved to directory ==> } 
Also, it writes the respective response for security and partmanager an xml files.

# Validation Messages
The system cannot find the file specified - If the system is unable to read valid xml from the user
Tag not properly closed - System throws this message if any of the xml tag is not closed i.e The end-tag for element type "partnumber" must end with a '>'

# Code Examples
