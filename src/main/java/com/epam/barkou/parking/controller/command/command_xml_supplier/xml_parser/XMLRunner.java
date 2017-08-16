package main.java.com.epam.barkou.parking.controller.command.command_xml_supplier.xml_parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import main.java.com.epam.barkou.parking.controller.exception.ControllerException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class XMLRunner {

	private final static String PATH_TO_XML = "/commands_list.xml";
	private final static String ERROR_WHILE_PARSING = "Error while file parsing";

	public static ArrayList<XMLCommand> parseXML() throws ControllerException {
		String path = XmlHandler.class.getProtectionDomain().getCodeSource().getLocation().getPath();

		XmlHandler xml = null;
		try {

			XMLReader xr = XMLReaderFactory.createXMLReader();

			xml = new XmlHandler();
			xr.setContentHandler(xml);

			xr.parse(new InputSource(new FileReader(path + PATH_TO_XML)));
		} catch (IOException | SAXException e) {
			throw new ControllerException(ERROR_WHILE_PARSING, e);
		}

		return xml.getCommandsList();
	}
}
