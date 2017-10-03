package org.jobsity.run.model;

/**
* Person
* Object to represents a person
*
* @author alexander.vera
* @since 30/10/2017

*/
public abstract class Person{
	private Long id;
	private String name;
	
	public Person(){
		this.id=0L;
		this.name="";
	}
	
	public Person(Long id, String name){
		this.id=id;
		this.name=name;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* 
*/