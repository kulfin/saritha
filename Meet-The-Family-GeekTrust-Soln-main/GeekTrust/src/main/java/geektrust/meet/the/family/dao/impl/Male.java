package geektrust.meet.the.family.dao.impl;

import geektrust.meet.the.family.dao.Person;

public class Male extends Person {

    private static final String gender = "Male";

    public Male(String name) {
        super(name);
    }

    public static String getGender() {
        return gender;
    }
}
