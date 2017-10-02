package org.jobsity.run.model;

/**
* Person
* Object to represents a person
*
* @author alexander.vera
* @since 30/10/2017
*
*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* 
*/
public abstract class Person{
	private Long id;
	private String name;
	
	public Person(){
		setId(0L);
		setName("");
	}
	
	public Person(Long _id, String _name){
		setId(0L);
		setName(_name);
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
