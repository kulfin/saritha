package geektrust.meet.the.family.dao.impl;

import geektrust.meet.the.family.dao.Person;

public class Female extends Person {

    private static final String gender = "Female";


    public Female(String name) {
        super(name);
    }

    public static String getGender() {
        return gender;
    }


}
