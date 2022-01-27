package com.geektrust.water.management.model;

import java.util.HashMap;

public class Rooms {
	private int members;
	private HashMap<Integer,Integer> rooms = new HashMap<Integer,Integer>();
	private int guests;
	
	public Rooms(){
		this.rooms.put(2, 3);
		this.rooms.put(3, 5);
	}
	public int getGuests() {
		return this.guests;
	}
	public void setGuests(String guests) {
		//this.guests = guests;

		this.guests += Integer.parseInt(guests);
	}
	public int getRooms(int room) {
		return this.rooms.get(room);
	}
	public void setRooms() {
		this.rooms.put(2, 3);
		this.rooms.put(3, 5);
	}
	public int getMembers() {
		return this.members;
	}
	public void setMembers(int members) {
		this.members = members;
	}

}
