package geektrust.meet.the.family.service;

import geektrust.meet.the.family.dao.Family;

public interface FamilyRelationshipService {

    String addChild(String name, String gender, Family family);
    String getRelationship(Family person, String relation,String personName);

}
