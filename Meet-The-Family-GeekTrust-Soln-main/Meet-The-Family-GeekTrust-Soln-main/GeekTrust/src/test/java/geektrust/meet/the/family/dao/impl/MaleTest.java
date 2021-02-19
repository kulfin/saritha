package geektrust.meet.the.family.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static geektrust.meet.the.family.service.utils.FamilyConstants.MALE;
import static org.junit.Assert.*;

public class MaleTest {
    Male male;

    @Before
    public void setUp() {
        male= new Male("John");
    }

    @Test
    public void testGetGender() {
        Assert.assertEquals(MALE,male.getGender());
    }
}