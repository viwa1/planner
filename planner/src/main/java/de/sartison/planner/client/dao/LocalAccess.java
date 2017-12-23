package de.sartison.planner.client.dao;

import de.sartison.planner.client.util.LocalStorage;

public class LocalAccess {
	private LocalStorage localStorage = new LocalStorage();
	
	public String getUser() {
		return localStorage.getItem("PASSWORD");
	}
	
	public String getPassword() {
		return localStorage.getItem("USER");
	}
}
