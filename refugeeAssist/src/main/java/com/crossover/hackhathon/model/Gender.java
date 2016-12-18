package com.crossover.hackhathon.model;


public enum Gender {

	MAN("MAN"),
	WOMAN("WOMAN");
	
	private final String text;
	
	private Gender(final String text){
		this.text = text;
	}
	
	@Override
	public String toString(){
		return text;
	}
	
	public static String[] names() {
		Gender[] roles = values();
	    String[] names = new String[roles.length];

	    for (int i = 0; i < roles.length; i++) {
	        names[i] = roles[i].name();
	    }

	    return names;
	}
}
