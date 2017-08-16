package main.java.com.epam.barkou.parking.controller.command.command_xml_supplier.xml_parser;

import main.java.com.epam.barkou.parking.controller.command.command_xml_supplier.xml_parser.XMLCommand;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.io.*;
import java.util.*;

public class XmlHandler extends DefaultHandler {

	private XMLCommand currentCommand;

	private ArrayList<XMLCommand> arrayList = new ArrayList<XMLCommand>();

	private CharArrayWriter contents = new CharArrayWriter();

	private final static String TAG_COMMAND ="command";

	public void startElement(String namespaceURI, String localName, String qName, Attributes attr) throws SAXException {

		contents.reset();

		if (localName.equals(TAG_COMMAND)) {
			currentCommand = new XMLCommand();

		}

	}

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

		if (localName.equals(TAG_COMMAND)) {
			currentCommand.setName(contents.toString());
			arrayList.add(currentCommand);
		}

	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		contents.write(ch, start, length);
	}

	public void print_all() {
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).getName());
		}
	}
	public ArrayList <XMLCommand> getCommandsList(){
		return arrayList;
	}
	

}