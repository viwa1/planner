package de.sartison.planner.client.util;

public class LocalStorage {
	public native void setItem(String key, String value) /*-{
		localStorage.setItem(key, value);
	}-*/;
	
	public native String getItem(String key) /*-{
		localStorage.getItem(key);
	}-*/;
	
	public native String removeItem(String key) /*-{
		localStorage.removeItem(key);
	}-*/;
}
