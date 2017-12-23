package de.sartison.planner.server.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class PlannerApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	 
    public PlannerApplication() {
        singletons.add(new PlannerRestService());
    }
 
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
