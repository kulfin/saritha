package com.geektrust.water.management.model;
import com.geektrust.water.management.resources.WaterConstants;

import java.util.HashSet;
import java.util.Set;





public class Commands {
    private Set<String> commands = new HashSet<String>();
    private int commandCount = 0;
	Set<String> commandSet = new HashSet<String>();
    		
    public Commands() {
        this.commands.add(WaterConstants.ALLOT_WATER);
        this.commands.add(WaterConstants.BILL);
        this.commands.add(WaterConstants.ADD_GUESTS);
    }
    public Set<String> getCommands(){
        return this.commands;
    }
    public void setCommandCount(int num){
		this.commandCount = num;
	}
    public int getCommandCount(){
		return this.commandCount;
	}
	public String[] getCommands(String[] arguments,int index){
		commandSet = this.getCommands();
		String[] list = new String[arguments.length];
		int k = WaterConstants.ZERO;
		for (int j = index+WaterConstants.ONE;j<arguments.length;j++){
			if(!commandSet.contains(arguments[j])){
        		if(arguments[j] != null){
        			list[k++] = arguments[j];
        		}
        	} else {
        		break;
        	}
    	}
		this.setCommandCount(k);
		return list;
	}
}
