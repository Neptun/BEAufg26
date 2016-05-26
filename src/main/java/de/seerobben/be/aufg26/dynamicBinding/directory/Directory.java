package de.seerobben.be.aufg26.dynamicBinding.directory;
public class Directory{
	@SuppressWarnings("rawtypes")	
	public Class[] getClasses(){
		return this.getClass().getClasses();
	}
/**
 * Each inner class represents a service in this directory 
 * Each method is an operation of the service
 * No two operations must have the same name
 */
    public static class DynamicBindingTravels{
// Scenario 1: There are several operations which return bookings for trips 
// According to their postconditions they offer flights or trainrides or hikes
// In this scenario the values of the input parameters are passed via preconditions of the requester

// 1. A direct flight from Hannover to Muenchen    	
    	@CapabilityAnnotation(precondition="operationType(travel)", postcondition="hmDirectFlight.pl")
    	public String book1(String par0, String par1){
    	  	return "LH201 from " + par0  + " to " + par1;
    	}
    	
// 2. A flight from Hannover to Muenchen via Frankfurt    	
    	@CapabilityAnnotation(precondition="operationType(travel)", postcondition="hmViaFrFlight.pl")
    	public String book2(String par0, String par1){
    	  	return "LH207 from " + par0  + " to " + par1 + " via Frankfurt";
    	}
// 3. A train ride from Hannover to Muenchen without changing trains    	
    	@CapabilityAnnotation(precondition="operationType(travel)", postcondition="hmDirectTrain.pl")
    	public String book3(String par0, String par1){
    	  	return "ICE508 from " + par0  + " to " + par1;
    	}
// 4. A hike tour from Hannover to Muenchen    	
    	@CapabilityAnnotation(precondition="operationType(hike)", postcondition="hmHike.pl")
    	public String book4(String par0, String par1){
    	  	return "Per pedes from " + par0  + " to " + par1;
    	}    	
    }
    
    public static class DynamicBindingLoans{
// A simple test example: A loan is granted to any partner (getLoan0.pl = "type(result,loan)")
// ... if requester claims a banking service   	
    	@CapabilityAnnotation(precondition="requestedOperationType(banking),type(par0,partner)", postcondition="getLoan0.pl")
        public String getLoan0(String par0){
        	return "Cancellation of all contracts for " + par0;
        }
//... if requester claims an insurance service    	
    	@CapabilityAnnotation(precondition="requestedOperationType(insurance),type(par0,partner)", postcondition="getLoan0.pl")
        public String getLoan0Ins(String par0){
        	return "Teures Darlehen fuer " + par0 + ". Ausgestellt durch Versicherung.";
        }
    	
// Depending on the rating a loan is issued    	
    	// ... for vips a superior contract is issued
    	@CapabilityAnnotation(precondition="requestedOperationType(banking),type(par0,partner),rating(par0,vip)", postcondition="getLoan1.pl")
        public String getLoan1(String par0){
        	return "Superior Contract for " + par0;
        }
    	// ... for premium partners a normal contract is issued
    	@CapabilityAnnotation(precondition="requestedOperationType(banking),type(par0,partner),rating(par0,premium)", postcondition="getLoan2.pl")
        public String getLoan2(String par0){
        	return "Normal Contract for " + par0;
        }
    	// ... for standard persons a bad loan is issued
    	@CapabilityAnnotation(precondition="requestedOperationType(banking),type(par0,person),rating(par0,standard)", postcondition="getLoan3.pl")
        public String getLoan3(String par0){
        	return "Bad Loan for " + par0;
        }	
    }
}
