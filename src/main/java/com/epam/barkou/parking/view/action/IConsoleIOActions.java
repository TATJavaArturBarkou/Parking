package main.java.com.epam.barkou.parking.view.action;


public abstract class IConsoleIOActions {

    protected final static String EMPTY_INPUT="";
    protected final static String SPACE_INPUT=" ";

    public abstract boolean checkInput(String command);

    public abstract String getEnterMessage();



}
