package com.pilotprogrammer.beans;

public class MyXmlConfigBean {
	public String sayHello(String first, String last) {
		String className = getClass().toString();
		return String.format("%s says hello to %s %s!", className, first, last);
	}
}
