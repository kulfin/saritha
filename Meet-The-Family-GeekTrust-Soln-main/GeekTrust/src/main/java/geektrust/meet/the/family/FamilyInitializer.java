package geektrust.meet.the.family;

import geektrust.meet.the.family.dao.Family;
import geektrust.meet.the.family.dao.impl.Female;
import geektrust.meet.the.family.dao.impl.Male;
import geektrust.meet.the.family.service.helper.FamilyRelationshipFinderHelper;
import geektrust.meet.the.family.service.impl.FamilyRelationshipServiceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static geektrust.meet.the.family.service.utils.FamilyConstants.*;

public class FamilyInitializer {

    public static void main(String[] args) {
        String[] query;
        //BuildFamily Tree
        Male kingShan = new Male("King Shan");
        Female queenAnga = new Female("Queen Anga");
        Family shanFamily = new Family(kingShan, queenAnga);
        initializeTheFamilyTree(shanFamily);

        //Execute queries
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line = reader.readLine();
            while (line != null) {
                line = line.replaceAll("[^a-zA-Z_\\-\\ ]", "");
                query = line.split(" ");
                if (query.length > 2 && query.length < 5)
                    executeQuery(query, shanFamily);
                line = reader.readLine();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No Records in File");
        } catch (IOException e) {
            System.out.println("Either file doesnt exists ");
        } catch (Exception e) {
            System.out.println("System Error");
        }
    }

    private static void executeQuery(String[] query, Family shanFamily) {
        FamilyRelationshipServiceImpl familyRelationshipService = new FamilyRelationshipServiceImpl();
        if (query[0].equals(ADD_CHILD)) {
            Family family = FamilyRelationshipFinderHelper.searchPersonInFamily(query[1], shanFamily);
            String result = familyRelationshipService.addChild(query[2], query[3], family);
            if (result != null)
                System.out.println(result);
            else
                System.out.println(PERSON_NOT_FOUND);
        } else if (query[0].equals(GET_RELATIONSHIP)) {
            Family family = FamilyRelationshipFinderHelper.searchPersonInFamily(query[1], shanFamily);
            String result = familyRelationshipService.getRelationship(family, query[2], query[1]);
            if (result != null)
                System.out.println(result);
            else
                System.out.println(PERSON_NOT_FOUND);
        }
    }


    private static void initializeTheFamilyTree(Family shanFamily) {
        shanFamily.getChildren().put("Chit", generateMale("Chit"));
        shanFamily.getChildren().put("Ish", generateMale("Ish"));
        shanFamily.getChildren().put("Vich", generateMale("Vich"));
        shanFamily.getChildren().put("Aras", generateMale("Aras"));
        shanFamily.getChildren().put("Satya", generateFemale("Satya"));

        Female nonBloodFamilyMember = new Female("Amba");
        shanFamily.getChild("Chit").setWife(nonBloodFamilyMember);
        shanFamily.getChild("Chit").getChildren().put("Dritha", generateFemale("Dritha"));
        shanFamily.getChild("Chit").getChildren().put("Tritha", generateFemale("Tritha"));
        shanFamily.getChild("Chit").getChildren().put("Vritha", generateFemale("Vritha"));
        shanFamily.getChild("Chit").getChild("Dritha").setHusband(new Male("Jaya"));
        shanFamily.getChild("Chit").getChild("Dritha").getChildren().put("Yodhan", generateMale("Yodhan"));

        Female nonBloodFamilyMember2 = new Female("Lika");
        shanFamily.getChild("Vich").setWife(nonBloodFamilyMember2);
        shanFamily.getChild("Vich").getChildren().put("Vila", generateFemale("Vila"));
        shanFamily.getChild("Vich").getChildren().put("Chika", generateFemale("Chika"));


        Female nonBloodFamilyMember3 = new Female("Chitra");
        shanFamily.getChild("Aras").setWife(nonBloodFamilyMember3);
        shanFamily.getChild("Aras").getChildren().put("Jnki", generateFemale("Jnki"));
        shanFamily.getChild("Aras").getChild("Jnki").setHusband(new Male("Arit"));
        shanFamily.getChild("Aras").getChild("Jnki").getChildren().put("Laki", generateMale("Laki"));
        shanFamily.getChild("Aras").getChild("Jnki").getChildren().put("Lavnya", generateFemale("Lavnya"));
        shanFamily.getChild("Aras").getChildren().put("Ahit", generateMale("Ahit"));

        shanFamily.getChild("Satya").setHusband(new Male("Vyan"));
        shanFamily.getChild("Satya").getChildren().put("Asva", generateMale("Asva"));
        shanFamily.getChild("Satya").getChildren().put("Vyas", generateMale("Vyas"));
        shanFamily.getChild("Satya").getChildren().put("Atya", generateFemale("Atya"));
        Female nonBloodFamilyMember4 = new Female("Satvy");
        shanFamily.getChild("Satya").getChild("Asva").setWife(nonBloodFamilyMember4);
        shanFamily.getChild("Satya").getChild("Asva").getChildren().put("Vasa", generateMale("Vasa"));
        Female nonBloodFamilyMember5 = new Female("Krpi");
        shanFamily.getChild("Satya").getChild("Vyas").setWife(nonBloodFamilyMember5);
        shanFamily.getChild("Satya").getChild("Vyas").getChildren().put("Kriya", generateMale("Kriya"));
        shanFamily.getChild("Satya").getChild("Vyas").getChildren().put("Krithi", generateFemale("Krithi"));
    }

    public static Family generateFemale(String name) {
        return new Family(null, new Female(name));
    }

    public static Family generateMale(String name) {
        return new Family(new Male(name), null);
    }
}
