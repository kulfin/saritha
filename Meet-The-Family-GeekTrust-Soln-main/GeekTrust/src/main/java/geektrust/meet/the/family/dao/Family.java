package geektrust.meet.the.family.dao;

import geektrust.meet.the.family.dao.impl.Female;
import geektrust.meet.the.family.dao.impl.Male;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Family {

    private Male husband;
    private Female wife;
    private Map<String, Family> children;
    private List<Family> familyHierarchyPath;

    public Family(Male husband, Female wife) {
        this.husband = husband;
        this.wife = wife;
        children = new HashMap<>();
        familyHierarchyPath = new ArrayList<>();

    }

    public Male getHusband() {
        return husband;
    }

    public void setHusband(Male husband) {
        this.husband = husband;
    }

    public Female getWife() {
        return wife;
    }

    public void setWife(Female wife) {
        this.wife = wife;
    }

    public Map<String, Family> getChildren() {
        return children;
    }

    public Family getChild(String childName) {
        return children.get(childName);
    }


    public List<Family> getFamilyHierarchyPath() {
        return familyHierarchyPath;
    }


    public Boolean isMarriedCouple() {
        if (husband != null && wife != null)
            return true;
        return false;
    }

    public Family searchPerson(String name) {
        if (husband.getName().equals(name) || wife.getName().equals(name))
            return this;
        else if (this.getChild(name) != null) {
            return this.getChild(name);
        } else
            return null;
    }

}
