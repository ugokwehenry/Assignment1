package com.henry.ugokwe;

import java.io.File;
import java.io.FileNotFoundException;
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
import org.xml.sax.SAXException;

import com.henry.ugokwe.common.Validation;
import com.henry.ugokwe.SecurityImpl;

public class DatabaseImpl extends Validation implements IDatabase {

	private static Scanner reader;
	public static final String USER_INPUT = getUserInput();

	/**
	 * This function writes a failure message to the xml if the security
	 * authorization fails
	 */
	public static void partResponseSuccess(int sParNtumber, int sQuantity) {
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
			partnumber.appendChild(doc.createTextNode("" + sParNtumber));
			itemElement.appendChild(partnumber);

			// Quantity elements
			Element quantity = doc.createElement("quantity");
			quantity.appendChild(doc.createTextNode("" + sQuantity));
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
			StreamResult results = new StreamResult(new File(getProgramPath()
					+ "\\partResponseSuccessMessage.xml"));
			transformer.transform(source, results);
			System.out
					.println("File {partResponseSuccessMessage.xml} saved to directory ==> "
							+ getProgramPath() + " ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function writes a failure message to the xml if the security
	 * authorization fails
	 */
	public static void partResponseFailure(int fParNtumber, int fQuantity) {
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
			partnumber.appendChild(doc.createTextNode("" + fParNtumber));
			itemElement.appendChild(partnumber);

			// Quantity elements
			Element quantity = doc.createElement("quantity");
			quantity.appendChild(doc.createTextNode("" + fQuantity));
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
			StreamResult results = new StreamResult(new File(getProgramPath()
					+ "\\partResponseFailureMessage.xml"));
			System.out
					.println("File {partResponseFailureMessage.xml} saved to directory ==> "
							+ getProgramPath() + " ");
			transformer.transform(source, results);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		boolean flag = true;
		try {
			String getProgramPath = getProgramPath();
			String fileName = getFilePath(getProgramPath + "\\" + USER_INPUT);
			validateXML(fileName);
			if (flag) {
				DatabaseImpl database = new DatabaseImpl();
				SecurityImpl security = new SecurityImpl();
				boolean validateAccess = security.IsDealerAuthorized(
						"XXX-1234-ABCD-1234", "kkklas8882kk23nllfjj88290");
				if (validateAccess) {
					SecurityImpl.successMessage();
					database.SubmitPartForManufacture(1234, 2);
					System.out.println("Authorized!");
				} else {
					SecurityImpl.failureMessage();
					System.out.println("Access Denied!");
				}
			}
		} catch (ParserConfigurationException e) {
			flag = false;
			System.out.println(e.getMessage() + "ParserConfigurationException");
		} catch (FileNotFoundException e) {
			flag = false;
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			flag = false;
			System.out.println(e.getMessage() + "SAXException");
		} catch (IOException e) {
			flag = false;
			System.out.println(e.getMessage());
		}
		System.out.println("xml file is valid: " + flag);
	}

	/**
	 * public static void main(String[] args) throws
	 * ParserConfigurationException, SAXException, IOException { DatabaseImpl
	 * obj = new DatabaseImpl(); obj.SubmitPartForManufacture(1234, 2); }
	 **/

	public void SubmitPartForManufacture(int parNtumber, int quantity) {
		int partNo = 0;
		int qty = 0;
		try {
			String getProgramPath = getProgramPath();
			// String sFileName = reader.nextLine();
			String fileName = getFilePath(getProgramPath + "\\" + USER_INPUT);

			// String fileName = getFilePath(getProgramPath()+"");
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Load the input XML document, parse it and return an instance of
			// the Document class.
			Document document = builder.parse(fileName);

			List<Item> items = new ArrayList<Item>();

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
				}

				items.add(new Item(partNo, qty));

			}
			// Iterate through all item on the xml.
			for (Item item : items) {
				if ((parNtumber == item.getPartNumber())
						&& (quantity == item.getQuantity())) {
					partResponseSuccess(parNtumber, quantity);
				} else {
					partResponseFailure(parNtumber, quantity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
