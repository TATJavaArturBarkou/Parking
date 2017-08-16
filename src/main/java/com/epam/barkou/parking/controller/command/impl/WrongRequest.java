package main.java.com.epam.barkou.parking.controller.command.impl;

import main.java.com.epam.barkou.parking.controller.command.Command;

public class WrongRequest extends Command {

	private final int accessLevel = 0;

	@Override
	public String execute(String request) {
		return null;
	}


}
