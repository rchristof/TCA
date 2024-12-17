package bundle;

import java.util.Locale;
import java.util.ResourceBundle;

public final class Msg {
	
	private static Locale currentLocale;
	private static ResourceBundle messages;
	
	public final static Locale localePtBr = new Locale("pt", "BR");
	public final static Locale localeUsEn = new Locale("en", "US");
	public final static Locale localeEsEs = new Locale("es", "ES");
	
	static {
		currentLocale = localePtBr;
		refreshMessages();
	}
	
	private Msg() { }
	
	public static void changeLocaleToEnUs() {
		if (currentLocale != localeUsEn) {
			currentLocale = localeUsEn;
			refreshMessages();
		}
	}
	
	public static void changeLocaleToPtBr() {
		if (currentLocale != localePtBr) {
			currentLocale = localePtBr;
			refreshMessages();
		}
	}
	
	public static void changeLocaleToEsEs() {
		if (currentLocale != localeEsEs) {
			currentLocale = localeEsEs;
			refreshMessages();
		}
	}
	
	private static void refreshMessages() {
		messages = ResourceBundle.getBundle("bundle/MessagesBundle", currentLocale);
	}
	
	public static String get(String key) {
		return messages.getString(key);
	}
	
	public static Locale getCurrentLocale() {
		return currentLocale;
	}

	public static String getP(String key) {
		return get(key) + ":";
	}

	public static String getS(String key) {
		return get(key) + " ";
	}

}
