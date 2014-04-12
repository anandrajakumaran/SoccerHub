package com.a1bizs.soccerhub.model;

public class memberDb {
     
    int    id;
    String name;
    String email;
    String password;
    boolean verified;
     
    
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public memberDb(){
    	this.id 	= 0;
    }
    public memberDb(int id, String name, String email, String password, String other){
        this.id      = id;
        this.name    = name;
        this.email   = email;
        this.password= password;
    }
     
    public memberDb( String name, String email, String password, String other){
    	this.name    = name;
    	this.email   = email;
    	this.password= password;
    }
    
}