package com.a1bizs.soccerhub.model;

import java.io.Serializable;

public class ListItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int fixid;
	private String teamA;
	private String teamB;
	private String date;
	private String time;
	private String tips;
	private String odds;
	
	
	public int getFixid() {
		return fixid;
	}
	public void setFixid(int fixid) {
		this.fixid = fixid;
	}
	public String getTeama() {
		return teamA;
	}
	public void setTeama(String teamA) {
		this.teamA = teamA;
	}
	public String getTeamb() {
		return teamB;
	}
	public void setTeamb(String teamB) {
		this.teamB = teamB;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getOdds() {
		return odds;
	}
	public void setOdds(String odds) {
		this.odds = odds;
	}
	@Override
	public String toString() {
		return "ListItem [fixid=" + fixid + ", teamA=" + teamA + ", teamB="
				+ teamB + ", date=" + date + ", time=" + time + ", tips="
				+ tips + ", odds=" + odds + "]";
	}
	
}
