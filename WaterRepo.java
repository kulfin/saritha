package com.geektrust.water.service;
import java.util.HashMap;

import com.geektrust.water.management.model.Member;

public class WaterRepo {

	private HashMap<String, Member> map = new HashMap<String, Member>();

	public void addMember(Member member) {
		map.put(member.getWaterRatio(), member);
	}

	public Member get(String name) {
		return map.get(name);
	}

}
