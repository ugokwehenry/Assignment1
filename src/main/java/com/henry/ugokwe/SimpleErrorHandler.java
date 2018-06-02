package com.henry.ugokwe;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler{

	public void warning(SAXParseException exception) throws SAXException {
		 System.out.println(exception.getMessage());
		
	}

	public void error(SAXParseException exception) throws SAXException {
		 System.out.println(exception.getMessage());
		
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		 System.out.println(exception.getMessage());
		
	}

}
