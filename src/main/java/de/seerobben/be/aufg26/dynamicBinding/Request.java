package de.seerobben.be.aufg26.dynamicBinding;

public class Request {

	private String from;
	private String to;
	private String postCondition;

	public Request(String from, String to, String postCondition) {
		this.from = from;
		this.to = to;
		this.postCondition = postCondition;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getPostCondition() {
		return postCondition;
	}

}
