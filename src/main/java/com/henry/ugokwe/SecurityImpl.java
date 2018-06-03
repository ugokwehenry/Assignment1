package com.henry.ugokwe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * 
 * @author HENRY This class validates the Dealer by comparing the dealer's Id
 *         and Dealer's access key
 */
public class SecurityImpl implements Security {

	private static Scanner in;

	public boolean IsDealerAuthorized(String dealerid, String dealeraccesskey) {
		String dealerId = "";
		String accessKey = "";
		try {
			//in = new Scanner(System.in);
			//String getProgramPath = getProgramPath();
		    //String sFileName = in.nextLine();
			//String fileName = getFilePath(getProgramPath+sFileName);
			
			//File fileName = new File("incomingOrder.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			factory.setValidating(false);
			factory.setNamespaceAware(true);
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			
			// Load the input XML document, parse it and return an instance of the Document class.
			Document document = builder.parse(DatabaseImpl.USER_INPUT);

			// Get the value of the dealer attribute.
			NodeList nodeList = document.getElementsByTagName("dealer");
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					// Get the value of all sub-elements.
					dealerId = elem.getElementsByTagName("dealerid").item(0)
							.getChildNodes().item(0).getNodeValue();
					accessKey = elem.getElementsByTagName("dealeraccesskey")
							.item(0).getChildNodes().item(0).getNodeValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (dealerid.trim().equals(dealerId)) {
			if (dealeraccesskey.trim().equals(accessKey)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This function writes a failure message to the xml if the security
	 * authorization fails
	 */
	public static void failureMessage() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element orderElement = doc.createElement("order");
			doc.appendChild(orderElement);

			// Result elements
			Element result = doc.createElement("result");
			result.appendChild(doc.createTextNode("Failure"));
			orderElement.appendChild(result);

			// Error elements
			Element error = doc.createElement("error");
			error.appendChild(doc.createTextNode("Not authorized"));
			orderElement.appendChild(error);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult results = new StreamResult(new File(getProgramPath()+"\\deniedMsg.xml"));

			transformer.transform(source, results);
			System.out.println("File {deniedMsg.xml} saved to directory ==> "+getProgramPath() +" ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function writes a failure message to the xml if the security
	 * authorization fails
	 */
	public static void successMessage() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			docFactory.setValidating(false);
			docFactory.setNamespaceAware(true);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element orderElement = doc.createElement("order");
			doc.appendChild(orderElement);

			// Result elements
			Element result = doc.createElement("result");
			result.appendChild(doc.createTextNode("Success"));
			orderElement.appendChild(result);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult results = new StreamResult(new File(getProgramPath()+"\\authorizedMsg.xml"));

			// Output to console for testing
			transformer.transform(source, results);

			System.out.println("File {authorizedMsg.xml} saved to directory ==> "+getProgramPath() +" ");

		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	/**
	 * This function checks if file path is valid
	 * @param fileName
	 * @return
	 */
	public static String getFilePath (String fileName){
		in = new Scanner(System.in);
		System.out.print("Please enter filename here : ");
		String filePath = null;
		if(fileName == null || fileName.trim().isEmpty()){
			System.out.println("You need to specify a File path!");
		}else{
			filePath = in.nextLine();
		      in.close();            
		}    
	    return filePath;
	}
	 public static String getProgramPath() throws UnsupportedEncodingException {
	      java.net.URL url = SecurityImpl.class.getProtectionDomain().getCodeSource().getLocation();
	      String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
	      String parentPath = new File(jarPath).getParentFile().getPath();
	      return parentPath;
	   }
	 

}
