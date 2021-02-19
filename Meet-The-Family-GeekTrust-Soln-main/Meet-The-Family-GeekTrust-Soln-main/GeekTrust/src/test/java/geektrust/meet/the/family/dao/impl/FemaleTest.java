package geektrust.meet.the.family.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static geektrust.meet.the.family.service.utils.FamilyConstants.FEMALE;
import static org.junit.Assert.*;

public class FemaleTest {
Female female;
    @Before
    public void setUp() {
        female= new Female("Jane");
    }

    @Test
    public void testGetGender() {
        Assert.assertEquals(FEMALE,female.getGender());
    }
}