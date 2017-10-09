package org.jobsity.run.model;

/**
* Person class. Object that represents a person
*
* @author alexander.vera
* @since 30/10/2017
*
*/
public abstract class Person{
	private String name;
	
	public Person(){
		this.name="";
	}
	
	public Person(String name){
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

/*
* 
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* 
*/