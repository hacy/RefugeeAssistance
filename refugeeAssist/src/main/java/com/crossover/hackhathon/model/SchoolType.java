package com.crossover.hackhathon.model;

public enum SchoolType {
	
	KINDERGARDEN("KINDER GARDEN"),
	PRIMARY("PRIMARY SCHOOL"),
	HIGHSCHOOL("HIGH SCHOOL"),
	UNIVERSITY("UNIVERSITY"),
	MEDICINESCHOOL("MEDICINE SCHOOL");
	
	private final String text;
	
	private SchoolType(final String text){
		this.text = text;
	}
	
	@Override
	public String toString(){
		return text;
	}
	
	public static String[] names() {
		SchoolType[] roles = values();
	    String[] names = new String[roles.length];

	    for (int i = 0; i < roles.length; i++) {
	        names[i] = roles[i].name();
	    }

	    return names;
	}

}
