package com.b.simple.design.business.text;

public class TextHelper {

    public String swapLastTwoCharacters(String str) {
		int length = str.length();
        if (length < 2)
            return str;

        char lastChar = str.charAt(length - 1);
        char lastButOneChar = str.charAt(length -2);
		String unchangedPart = str.substring(0, length - 2);
        return unchangedPart + lastChar + lastButOneChar;
    }

    public String truncateAInFirst2Positions(String str) {
        if (str.length() < 2)
            return str.replaceAll("A", "");

		String first2Characters = str.substring(0, 2);
		String first2CharactersUpdated = first2Characters.replaceAll("A", "");

		String unchangedPart = str.substring(2);

		return first2CharactersUpdated + unchangedPart;
    }
}
