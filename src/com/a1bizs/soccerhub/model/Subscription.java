package com.a1bizs.soccerhub.model;

import java.sql.Date;

public class Subscription {
	private int memberid;
	private Date start;
	private Date end;
	
	
	public Subscription(int memberid) {
		super();
		this.memberid = memberid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}

}
