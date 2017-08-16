package main.java.com.epam.barkou.parking.controller.command;


import main.java.com.epam.barkou.parking.controller.exception.ControllerException;

public abstract class Command {

	protected final static String SPLITTER = "&";
    protected final static String MESSAGE_NOT_ENOUGH_PARAMS = "Not able to perform operation, you haven't specified necessary parameters";

	public abstract String execute(String request) throws ControllerException;
}
