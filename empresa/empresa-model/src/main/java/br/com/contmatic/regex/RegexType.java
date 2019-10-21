package br.com.contmatic.regex;

/**
 * The Class Regex.
 */
public final class RegexType {

	/** The Constant URL. */
	public static final String URL = "^(https?|http|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	/** The Constant NOME. */
	public static final String NOME = "(\\w|\\s|ç|[á-ú])+";

	/** The Constant CEP. */
	public static final String CEP = "\\d+";

	/**
	 * Instantiates a new regex type.
	 */
	private RegexType() {

	}
}
