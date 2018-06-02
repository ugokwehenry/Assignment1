package com.henry.ugokwe.common;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.henry.ugokwe.SecurityImpl;

public class Validation {


	private static Scanner scan;
	/**
	 * This function validates if User's input is integer
	 */
	public static boolean validateIntger(int x, int y){
		scan = new Scanner(System.in);
		boolean finished = false;
		while(!finished){
			while(scan.hasNextInt()){
				x = scan.nextInt();
				y = scan.nextInt();
				if(x < 0 || y < 0){
					System.out.println("Error: Number smaller 0");
				}else{
					System.out.println("Correct");
					finished = true;
				}
			}
		}
		return finished;
	}
	public static boolean isInteger(Object object) {
		if(object instanceof Integer) {
			return true;
		} else {
			String string = object.toString();
			
			try {
				Integer.parseInt(string);
			} catch(Exception e) {
				return false;
			}	
		}
	  
	    return true;
	}
	/**
	 * This function validate the xml structure and format. It throws expection/returns and error code if any validation fails.
	 * @param xmlFileName
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void validateXML(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory =  DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setValidating(true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			documentBuilder.setErrorHandler(new ErrorHandler() {
				
				public void warning(SAXParseException exception) throws SAXException {throw exception;}		
				public void fatalError(SAXParseException exception) throws SAXException {throw exception;}			
				public void error(SAXParseException exception) throws SAXException {throw exception;}
			});
			documentBuilder.parse(new File(xmlFileName));
		}
	
	public static String getUserInput(){
		System.out.print("Please enter filename here : ");
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		userInput = scanner.nextLine();
			scanner.close();               
	    return userInput;
	}
	
	/**
	 * This function checks if file path is valid
	 * @param fileName
	 * @return
	 */
	public static  String getFilePath (String fileName){
		String filePath = null;
		if(fileName == null || fileName.trim().isEmpty()){
			System.out.println("You need to specify a File path!");
	        return "";
		}else{
			filePath = fileName;       
		}    
	    return filePath;
	}
	 public static String getProgramPath() throws UnsupportedEncodingException {
	      java.net.URL url = SecurityImpl.class.getProtectionDomain().getCodeSource().getLocation();
	      String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
	      String parentPath = new File(jarPath).getParentFile().getPath();
	      return parentPath;
	   }
	 
	 public static String getFileFromProgramPath(){
		return null;
		 
	 }
}
