package main.java.com.epam.barkou.parking.controller.util;

public class RequestChecker {

	public static boolean checkParamsQuantity(String[] requestData, int paramsQuantity) {

		
		if (requestData.length >= (paramsQuantity + 1)) {
			return true;
		}

		return false;

	}

}
