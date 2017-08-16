package main.java.com.epam.barkou.parking.controller.command.command_xml_supplier.util;

public class StringUtil {

	public static String convertCommandToEnumForm(String commandName) {

		final StringBuilder builder = new StringBuilder();
		final char[] startCommandArr = commandName.toCharArray();
		final int symbolA = 65;
		final int symbolZ = 90;
		final int firstElement = 0;
		final char separator = '_';
		
		for (int i = 0; i < startCommandArr.length; i++) {

			if (startCommandArr[i] >= symbolA && startCommandArr[i] <= symbolZ) {

				if (i == firstElement) {
					builder.append(startCommandArr[i]);

				} else {
					builder.append(separator);
					builder.append(startCommandArr[i]);
				}

				continue;
			}
			builder.append(startCommandArr[i]);

		}

		String finalString = builder.toString().toUpperCase();

		return finalString;

	}
}
