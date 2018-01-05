package de.sartison.planner.client.dao;

import de.sartison.planner.client.util.LocalStorage;

public class LocalAccess {
	private LocalStorage localStorage = new LocalStorage();
	
	public String getEmail() {
		return localStorage.getItem("EMAIL");
	}
	
	public String getPassword() {
		return localStorage.getItem("PASSWORD");
	}
}
