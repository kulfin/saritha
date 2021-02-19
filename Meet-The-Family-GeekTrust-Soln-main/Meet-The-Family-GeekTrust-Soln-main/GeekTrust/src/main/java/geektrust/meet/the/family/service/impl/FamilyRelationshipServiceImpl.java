package geektrust.meet.the.family.service.impl;

import geektrust.meet.the.family.dao.Family;
import geektrust.meet.the.family.dao.impl.Female;
import geektrust.meet.the.family.dao.impl.Male;
import geektrust.meet.the.family.service.FamilyRelationshipService;
import geektrust.meet.the.family.service.helper.FamilyRelationshipFinderHelper;

import static geektrust.meet.the.family.service.utils.FamilyConstants.*;

public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {

    @Override
    public String addChild(String name, String gender, Family family) {

        if (family.isMarriedCouple()) {
            addChildBasedOnGender(name, gender, family);
            return CHILD_ADDITION_SUCCEEDED;
        } else
            return CHILD_ADDITION_FAILED;
    }

    private void addChildBasedOnGender(String name, String gender, Family family) {
        if (gender.equals(FEMALE))
            family.getChildren().put(name, new Family(null, new Female(name)));
        else
            family.getChildren().put(name, new Family(new Male(name), null));
    }

    @Override
    public String getRelationship(Family family, String relation, String personName) {
        try {
            switch (relation) {
                case "Son":
                    return FamilyRelationshipFinderHelper.findRelation(family, MALE);
                case "Daughter":
                    return FamilyRelationshipFinderHelper.findRelation(family, FEMALE);
                case "Siblings":
                    return FamilyRelationshipFinderHelper.findSiblings(family, HEIGHT_LEVEL_ONE, personName);
                case "Sister-In-Law":
                    return FamilyRelationshipFinderHelper.findInLaws(family, HEIGHT_LEVEL_ONE, FEMALE, personName);
                case "Brother-In-Law":
                    return FamilyRelationshipFinderHelper.findInLaws(family, HEIGHT_LEVEL_ONE, MALE, personName);
                case "Paternal-Uncle":
                    return FamilyRelationshipFinderHelper.findElders(family, HEIGHT_LEVEL_TWO, MALE, FATHER_SIDE);
                case "Paternal-Aunt":
                    return FamilyRelationshipFinderHelper.findElders(family, HEIGHT_LEVEL_TWO, FEMALE, FATHER_SIDE);
                case "Maternal-Uncle":
                    return FamilyRelationshipFinderHelper.findElders(family, HEIGHT_LEVEL_TWO, MALE, MOTHER_SIDE);
                case "Maternal-Aunt":
                    return FamilyRelationshipFinderHelper.findElders(family, HEIGHT_LEVEL_TWO, FEMALE, MOTHER_SIDE);
                default:
                    return PERSON_NOT_FOUND;
            }
        } catch (NullPointerException e) {
            return PERSON_NOT_FOUND;
        }
    }


}
