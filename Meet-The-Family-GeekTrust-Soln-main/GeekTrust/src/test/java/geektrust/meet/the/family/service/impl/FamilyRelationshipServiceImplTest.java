package geektrust.meet.the.family.service.impl;

import geektrust.meet.the.family.dao.Family;
import geektrust.meet.the.family.dao.impl.Female;
import geektrust.meet.the.family.dao.impl.Male;
import geektrust.meet.the.family.service.helper.FamilyRelationshipFinderHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static geektrust.meet.the.family.service.utils.FamilyConstants.FEMALE;
import static geektrust.meet.the.family.service.utils.FamilyConstants.MALE;
import static org.junit.Assert.*;

public class FamilyRelationshipServiceImplTest {

    Family family;
    Male male;
    Female female;
    Female child;
    FamilyRelationshipServiceImpl familyRelationshipService;

    @Before
    public void setUp() {
        male = new Male("John");
        female = new Female("Jane");
        family = new Family(male, female);
        child = new Female("Jane Kid");
        family.getChildren().put("Jane Kid", new Family(null, child));
        familyRelationshipService = new FamilyRelationshipServiceImpl();
    }

    @Test
    public void testAddChild() {
        familyRelationshipService.addChild("John Kid", MALE, family);
        Assert.assertEquals("John Kid", family.getChild("John Kid").getHusband().getName());
    }

    @Test
    public void testGetRelationshipForSon() {
        familyRelationshipService.addChild("John Kid", MALE, family);
        Assert.assertEquals("John Kid", familyRelationshipService.getRelationship(family, "Son", "John"));
    }

    @Test
    public void testGetRelationshipForDaughter() {
        familyRelationshipService.addChild("John Female Kid", FEMALE, family);
        Assert.assertEquals("Jane Kid John Female Kid", familyRelationshipService.getRelationship(family, "Daughter", "John"));
    }

    @Test
    public void testGetRelationshipForSiblings() {
        familyRelationshipService.addChild("John Kid", MALE, family);
        familyRelationshipService.addChild("John Kid 2", MALE, family);
        familyRelationshipService.addChild("John Kid 3", MALE, family);
        Family searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("John Kid 3", family);
        Assert.assertEquals("John Kid John Kid 2 John Kid 3", familyRelationshipService.getRelationship(searchedFamily, "Siblings", "Jane Kid"));
    }

    @Test
    public void testGetRelationshipForSisterInLaw() {
        Family searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("Jane", family);
        familyRelationshipService.addChild("John Kid", MALE, searchedFamily);
        family.getChild("John Kid").setWife(new Female("John Kid Wife"));
        searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("Jane Kid", family);
        Assert.assertEquals("John Kid Wife", familyRelationshipService.getRelationship(searchedFamily, "Sister-In-Law", "Jane Kid"));
    }

    @Test
    public void testGetRelationshipForBrotherInLaw() {
        Family searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("Jane", family);
        familyRelationshipService.addChild("John Kid", FEMALE, searchedFamily);
        family.getChild("John Kid").setHusband(new Male("John Kid Husband"));
        searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("Jane Kid", family);
        Assert.assertEquals("John Kid Husband", familyRelationshipService.getRelationship(searchedFamily, "Brother-In-Law", "Jane Kid"));
    }

    @Test
    public void testGetRelationshipForPaternalUncle() {
        Family searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("Jane", family);
        familyRelationshipService.addChild("John Kid", MALE, searchedFamily);
        familyRelationshipService.addChild("John Kid 2", MALE, searchedFamily);
        family.getChild("John Kid 2").setWife(new Female("John Kid Wife"));
        searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("John Kid 2", family);
        familyRelationshipService.addChild("John Kid Second Level", MALE, searchedFamily);
        searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("John Kid Second Level", family);
        Assert.assertEquals("John Kid", familyRelationshipService.getRelationship(searchedFamily, "Paternal-Uncle", "John Kid 2"));
    }

    @Test
    public void testGetRelationshipForPaternalAunt() {
        Family searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("Jane", family);
        familyRelationshipService.addChild("John Kid", MALE, searchedFamily);
        familyRelationshipService.addChild("John Kid 2", MALE, searchedFamily);
        family.getChild("John Kid 2").setWife(new Female("John Kid Wife"));
        searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("John Kid 2", family);
        familyRelationshipService.addChild("John Kid Second Level", FEMALE, searchedFamily);
        searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("John Kid Second Level", family);
        Assert.assertEquals("Jane Kid", familyRelationshipService.getRelationship(searchedFamily, "Paternal-Aunt", "John Kid 2"));
    }

    @Test
    public void testGetRelationshipForMaternalUncle() {
        Family searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("Jane", family);
        familyRelationshipService.addChild("John Kid", MALE, searchedFamily);
        familyRelationshipService.addChild("John Kid 2", FEMALE, searchedFamily);
        family.getChild("John Kid 2").setHusband(new Male("John Kid Husband"));
        searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("John Kid 2", family);
        familyRelationshipService.addChild("John Kid Second Level", MALE, searchedFamily);
        searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("John Kid Second Level", family);
        Assert.assertEquals("John Kid", familyRelationshipService.getRelationship(searchedFamily, "Maternal-Uncle", "John Kid 2"));
    }

    @Test
    public void testGetRelationshipForMaternalAunt() {
        Family searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("Jane", family);
        familyRelationshipService.addChild("John Kid", MALE, searchedFamily);
        familyRelationshipService.addChild("John Kid 2", FEMALE, searchedFamily);
        family.getChild("John Kid 2").setHusband(new Male("John Kid Husband"));
        searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("John Kid 2", family);
        familyRelationshipService.addChild("John Kid Second Level", FEMALE, searchedFamily);
        searchedFamily = FamilyRelationshipFinderHelper.searchPersonInFamily("John Kid Second Level", family);
        Assert.assertEquals("Jane Kid", familyRelationshipService.getRelationship(searchedFamily, "Maternal-Aunt", "John Kid 2"));
    }
}