package main.java.com.epam.barkou.parking.view.scanner;

import java.util.Scanner;

public class ScannerContainer {
    private static Scanner scanner = null;

    public static Scanner getScanner() {
        if (scanner == null) {
            return new Scanner(System.in);
        } else return scanner;
    }
}
