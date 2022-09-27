package com.example.demo.model;

public class Foo {
	
	private int id;
	private String name;
	
	public Foo() {
		
	}
	
	public Foo(String name) {
		super();
		this.name = name;
	}

	public Foo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
