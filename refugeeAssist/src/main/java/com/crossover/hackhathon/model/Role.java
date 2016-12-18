package com.crossover.hackhathon.model;

public enum Role {
	REFUGEE("REFUGEE"),
	ADMIN("ADMIN"),
	GOVERNMENT_MEMBER("GOVERNMENT_MEMBER");
	
	private final String text;
	
	private Role(final String text){
		this.text = text;
	}
	
	@Override
	public String toString(){
		return text;
	}
	
	public static String[] names() {
	    Role[] roles = values();
	    String[] names = new String[roles.length];

	    for (int i = 0; i < roles.length; i++) {
	        names[i] = roles[i].name();
	    }

	    return names;
	}
}
