package geektrust.meet.the.family.service.helper;

import geektrust.meet.the.family.dao.Family;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static geektrust.meet.the.family.service.utils.FamilyConstants.*;

public class FamilyRelationshipFinderHelper {

    public static String findElders(Family family, int height, String gender, String parentSide) {
        String personName = "";
        if (parentSide.equals(MOTHER_SIDE)) {
            personName = family.getFamilyHierarchyPath().get(height - HEIGHT_LEVEL_TWO).getWife().getName();

        } else if (parentSide.equals(FATHER_SIDE)) {
            personName = family.getFamilyHierarchyPath().get(height - HEIGHT_LEVEL_TWO).getHusband().getName();
        }
        if (family.getFamilyHierarchyPath().get(height - HEIGHT_LEVEL_ONE).getChild(personName) != null) {

            return getInLaws(family.getFamilyHierarchyPath().get(height - HEIGHT_LEVEL_ONE), gender, personName);
        }
        return PERSON_NOT_FOUND;
    }

    public static String findSiblings(Family family, int height, String personName) {
        if (family.getFamilyHierarchyPath().get(height - 1).getChild(personName) != null)
            return String.join(" ",
                    family.getFamilyHierarchyPath().get(height - 1).getChildren().entrySet()
                            .stream()
                            .filter(
                                    entry -> !(entry.getKey().equals(personName)
                                    )
                            ).collect(
                            Collectors.toMap(
                                    entry -> entry.getKey(),
                                    entry -> entry.getValue(),
                                    (e1, e2) -> e1,
                                    LinkedHashMap::new
                            )
                    ).keySet()
            );
        return PERSON_NOT_FOUND;
    }

    public static String findInLaws(Family family, int height, String gender, String personName) {
        if (family.isMarriedCouple()) {
            if (family.getHusband().getName().equals(personName)) {
                personName = family.getWife().getName();

            } else if (family.getWife().getName().equals(personName)) {
                personName = family.getHusband().getName();
            }

            if (family.getFamilyHierarchyPath().get(height - HEIGHT_LEVEL_ONE).getChild(personName) != null) {

                return getInLaws(family.getFamilyHierarchyPath().get(height - HEIGHT_LEVEL_ONE), gender, personName);
            }
        } else {
            return getSiblingsSpouse(family.getFamilyHierarchyPath().get(height - HEIGHT_LEVEL_ONE), gender, personName);
        }

        return PERSON_NOT_FOUND;
    }

    private static String getSiblingsSpouse(Family family, String gender, String personName) {
        Set<String> inLaws;
        if (gender.equals(FEMALE)) {
            inLaws = family.
                    getChildren()
                    .entrySet()
                    .stream().filter(
                            entry -> entry.getValue().getHusband() != null
                                    && entry.getValue().getHusband().getName().equals(entry.getKey()))
                    .filter(
                            (entry) -> (
                                    entry.getValue().isMarriedCouple()
                                            && entry.getValue().getWife() != null
                                            && !entry.getValue().getWife().getName().equals(personName)))
                    .collect(
                            Collectors.toMap(
                                    entry -> entry.getValue().getWife().getName(),
                                    entry -> entry.getValue(),
                                    (e1, e2) -> e1,
                                    LinkedHashMap::new
                            ))
                    .keySet();

        } else {
            inLaws = family.
                    getChildren()
                    .entrySet()
                    .stream().filter(
                            entry -> entry.getValue().getWife() != null
                                    && entry.getValue().getWife().getName().equals(entry.getKey())).
                            filter(
                                    (entry) -> (
                                            entry.getValue().isMarriedCouple()
                                                    && entry.getValue().getHusband() != null
                                                    && !entry.getValue().getHusband().getName().equals(personName)))
                    .collect(
                            Collectors.toMap(
                                    entry -> entry.getValue().getHusband().getName(),
                                    entry -> entry.getValue(),
                                    (e1, e2) -> e1,
                                    LinkedHashMap::new

                            ))
                    .keySet();
        }
        if (!inLaws.isEmpty())
            return String.join(" ", inLaws);
        else
            return PERSON_NOT_FOUND;
    }

    private static String getInLaws(Family family, String gender, String personName) {
        Set<String> inLaws;
        if (gender.equals(FEMALE)) {
            inLaws = family.
                    getChildren()
                    .entrySet()
                    .stream().
                            filter(
                                    (entry) -> (
                                            entry.getValue().getWife() != null
                                                    && entry.getValue().getWife().getName().equals(entry.getKey())
                                                    && !entry.getValue().getWife().getName().equals(personName)))
                    .collect(
                            Collectors.toMap(
                                    entry -> entry.getKey(),
                                    entry -> entry.getValue(),
                                    (e1, e2) -> e1,
                                    LinkedHashMap::new
                            ))
                    .keySet();

        } else {
            inLaws = family.
                    getChildren()
                    .entrySet()
                    .stream().
                            filter(
                                    (entry) -> (
                                            entry.getValue().getHusband() != null
                                                    && entry.getValue().getHusband().getName().equals(entry.getKey())
                                                    && !entry.getValue().getHusband().getName().equals(personName)))
                    .collect(
                            Collectors.toMap(
                                    entry -> entry.getKey(),
                                    entry -> entry.getValue(),
                                    (e1, e2) -> e1,
                                    LinkedHashMap::new
                            ))
                    .keySet();
        }
        if (!inLaws.isEmpty())
            return String.join(" ", inLaws);
        else
            return PERSON_NOT_FOUND;
    }

    public static String findRelation(Family family, String gender) {

        if (gender.equals(FEMALE))
            return String.join(" ",
                    family.getChildren().entrySet()
                            .stream()
                            .filter(
                                    (entry) -> entry.getValue().getWife() != null
                            )
                            .collect(
                                    Collectors.toMap(
                                            entry -> entry.getKey(),
                                            entry -> entry.getValue(),
                                            (e1, e2) -> e1,
                                            LinkedHashMap::new
                                    )
                            ).keySet()
            );
        else
            return String.join(" ",
                    family.getChildren().entrySet()
                            .stream()
                            .filter(
                                    (entry) -> entry.getValue().getHusband() != null)
                            .collect(
                                    Collectors.toMap(
                                            entry -> entry.getKey(),
                                            entry -> entry.getValue(),
                                            (e1, e2) -> e1,
                                            LinkedHashMap::new
                                    )
                            ).keySet()
            );

    }


    public static Family searchPersonInFamily(String name, Family parentFamily) {
        Family family = null;
        if (parentFamily.isMarriedCouple()) {
            family = parentFamily.searchPerson(name);
            if (family == null) {
                family = searchPersonInChildrenFamilies(name, parentFamily, family);
            } else {
                if (family != parentFamily)
                    family.getFamilyHierarchyPath().add(parentFamily);
            }
        }

        return family;
    }

    private static Family searchPersonInChildrenFamilies(String name, Family parentFamily, Family childFamily) {
        for (Map.Entry<String, Family> entry : parentFamily.getChildren().entrySet()
        ) {
            childFamily = searchPersonInFamily(name, entry.getValue());
            if (childFamily != null) {
                if (childFamily != parentFamily)
                    childFamily.getFamilyHierarchyPath().add(parentFamily);
                break;
            }
        }
        return childFamily;
    }
}
