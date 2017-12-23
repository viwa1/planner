package de.sartison.planner.client.util;

public final class Utils {
	private Utils() {

	}

	public static final native String sha256(String str) /*-{
		return $wnd.CryptoJS.SHA256(str).toString($wnd.CryptoJS.enc.Hex);
	}-*/;

	public static final boolean isNullOrEmpty(String str) {
		return (str == null) || (str.trim().length() == 0);
	}
}
