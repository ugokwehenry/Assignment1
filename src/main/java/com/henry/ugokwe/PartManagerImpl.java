package com.henry.ugokwe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

import com.henry.ugokwe.DeliveryAddress;

public class PartManagerImpl implements PartManager {
	private static DeliveryAddress deliveryAddrss;
	private static Scanner scan;

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		//PartManagerImpl obj = new PartManagerImpl();
		//deliveryAddrss = new DeliveryAddress();
		boolean valididate = validateIntger(5, 8);
	System.out.println(valididate);
		if(valididate){
			
		}
		//obj.SubmitPartForManufactureAndDelivery(34, 0, deliveryAddrss);
	}

	public PartResponse SubmitPartForManufactureAndDelivery(int partNumber,
			int quantity, DeliveryAddress deliveryAddress) {
		PartResponse object = null;

		int partNo = 0;
		int qty = 0;
		try {
			File fileName = new File(
					"/Users/HENRY/workspace/unit-test/IncomingOrder.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Load the input XML document, parse it and return an instance of the
			
			// Document class.
			Document document = builder.parse(fileName);
			System.out.println("Root element :"
					+ document.getDocumentElement().getNodeName());

			// Get the value of the dealer attribute.
			NodeList nodeList = document.getElementsByTagName("item");
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					partNo = Integer.parseInt(elem
							.getElementsByTagName("partnumber").item(0)
							.getChildNodes().item(0).getNodeValue());
					qty = Integer.parseInt(elem
							.getElementsByTagName("quantity").item(0)
							.getChildNodes().item(0).getNodeValue());
					System.out.println("Part Number: " + partNo
							+ "Quantity is: " + qty);

					if (partNumber == partNo) {
						successMessage(partNumber, quantity);
					}else{
						failureMessage(partNumber, quantity);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}


	/**
	 * This function writes a failure message to the xml if the security
	 * authorization fails
	 */
	public static void successMessage(int sParNtumber,
			int sQuantity) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element itemElement = doc.createElement("item");
			doc.appendChild(itemElement);

			// partNumber elements
			Element partnumber = doc.createElement("partnumber");
			partnumber.appendChild(doc.createTextNode(""+sParNtumber));
			itemElement.appendChild(partnumber);

			// Quantity elements
			Element quantity = doc.createElement("quantity");
			quantity.appendChild(doc.createTextNode(""+sQuantity));
			itemElement.appendChild(quantity);

			// Result elements
			Element result = doc.createElement("result");
			result.appendChild(doc.createTextNode("Success"));
			itemElement.appendChild(result);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult results = new StreamResult(new File(
					SecurityImpl.getProgramPath()+ "\\partResponseSuccessMessage.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
			transformer.transform(source, results);

			System.out.println("File saved!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This function writes a failure message to the xml if the security
	 * authorization fails
	 */
	public static void failureMessage(int fParNtumber,
			int fQuantity) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element itemElement = doc.createElement("item");
			doc.appendChild(itemElement);

			// partNumber elements
			Element partnumber = doc.createElement("partnumber");
			partnumber.appendChild(doc.createTextNode(""+fParNtumber));
			itemElement.appendChild(partnumber);

			// Quantity elements
			Element quantity = doc.createElement("quantity");
			quantity.appendChild(doc.createTextNode(""+fQuantity));
			itemElement.appendChild(quantity);

			// Result elements
			Element result = doc.createElement("result");
			result.appendChild(doc.createTextNode("failure"));
			itemElement.appendChild(result);
			
			// Errormsg elements
			Element errorMsg = doc.createElement("errorMessage");
			errorMsg.appendChild(doc.createTextNode("invalid part"));
			itemElement.appendChild(errorMsg);
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult results = new StreamResult(new File(
					SecurityImpl.getProgramPath()+ "\\partResponseFailureMessage.xml"));

			transformer.transform(source, results);

			System.out.println("File saved!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
}
