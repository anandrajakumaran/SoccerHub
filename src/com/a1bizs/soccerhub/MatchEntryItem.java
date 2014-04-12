package com.a1bizs.soccerhub;

import com.a1bizs.soccerhub.model.matchDb;


public class MatchEntryItem implements MatchItem{

	public matchDb match;

	public MatchEntryItem(matchDb match) {
		this.match = match;
	}
	
	@Override
	public boolean isSection() {
		return false;
	}

}
