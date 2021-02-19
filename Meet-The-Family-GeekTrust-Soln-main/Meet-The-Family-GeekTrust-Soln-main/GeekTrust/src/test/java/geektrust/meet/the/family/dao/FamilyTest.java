package geektrust.meet.the.family.dao;

import geektrust.meet.the.family.dao.impl.Female;
import geektrust.meet.the.family.dao.impl.Male;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FamilyTest {

    Family family;
    Male male;
    Female female;
    Female child;
    Family childFamily;


    @Before
    public void setUp() {
        male = new Male("John");
        female = new Female("Jane");
        family = new Family(male, female);
        child = new Female("Jane Kid");
        childFamily=  new Family(null, new Female("Jane Kid"));
        family.getChildren().put("Jane Kid",childFamily);
    }

    @Test
    public void testGetHusband() {
        Assert.assertEquals(male, family.getHusband());
    }

    @Test
    public void testGetWife() {
        Assert.assertEquals(female, family.getWife());
    }

    @Test
    public void testGetChildren() {
        Assert.assertEquals(childFamily,family.getChildren().get("Jane Kid"));
    }

    @Test
    public void testGetChild() {
        Assert.assertEquals(childFamily,family.getChild("Jane Kid"));
    }

    @Test
    public void testGetFamilyHierarchyPath() {
        family.getChild("Jane Kid").getFamilyHierarchyPath().add(family);
        Assert.assertEquals(family,family.getChild("Jane Kid").getFamilyHierarchyPath().get(0));
    }

    @Test
    public void testIsMarriedCouple() {
        Assert.assertEquals(true,family.isMarriedCouple());
    }

    @Test
    public void testSearchPerson() {
        Assert.assertEquals(family,family.searchPerson("John"));

    }
}