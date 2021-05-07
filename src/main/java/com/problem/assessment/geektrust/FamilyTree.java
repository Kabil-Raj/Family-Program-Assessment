package com.problem.assessment.geektrust;

import java.io.File;
import java.util.Scanner;

enum RelationType {
    FATHER, MOHTER, SON, DAUGHTER, HUSBAND, WIFE, SIBLINGS, MATERNAL_AUNT, MATERNAL_UNCLE, PATERNAL_AUNT, PATERNAL_UNCLE, BROTHER_IN_LAW, SISTER_IN_LAW
}


public class FamilyTree {
    
    Relation relation = new Relation();
    
    String addChild = "ADD_CHILD";
    String getRelationship = "GET_RELATIONSHIP";
    
    String siblings = "Siblings";
    String son = "Son";
    String daughter = "Daughter";
    String brotherInLaw = "Brother-In-Law";
    String sisterInLaw = "Sister-In-Law";
    String maternalAunt = "Maternal-Aunt";
    String paternalAunt = "Paternal-Aunt";
    String maternalUncle = "Maternal-Uncle";
    String paternalUncle = "Paternal-Uncle";
    String maleGender = "Male";
    String femaleGender = "Female";
    
    
    public RelationType fetchRelationType(String relationName) {
        if(relationName.equalsIgnoreCase(daughter) || relationName.equalsIgnoreCase(femaleGender)) {
            return RelationType.DAUGHTER;
        } else if (relationName.equalsIgnoreCase(son) || relationName.equalsIgnoreCase(maleGender)) {
            return RelationType.SON;
        } else if (relationName.equalsIgnoreCase(siblings)) {
            return RelationType.SIBLINGS;
        } else if (relationName.equalsIgnoreCase(maternalUncle)) {
            return RelationType.MATERNAL_UNCLE;
        } else if (relationName.equalsIgnoreCase(maternalAunt)) {
            return RelationType.MATERNAL_AUNT;
        } else if (relationName.equalsIgnoreCase(paternalUncle)) {
            return RelationType.PATERNAL_UNCLE;
        } else if (relationName.equalsIgnoreCase(paternalAunt)) {
            return RelationType.PATERNAL_AUNT;
        } else if(relationName.equalsIgnoreCase(brotherInLaw)) {
            return RelationType.BROTHER_IN_LAW;
        } else if(relationName.equalsIgnoreCase(sisterInLaw)) {
            return RelationType.SISTER_IN_LAW;
        }
        return null;
    }
    
    public void inputData(String inputData) {
        if(inputData.contains(addChild)) {
            String[] getChildDetails = inputData.split(" ");
            if(getChildDetails.length == 4) {
                addChild(getChildDetails[1], getChildDetails[2], getChildDetails[3]);
            }
        } else if(inputData.contains(getRelationship)) {
            String[] relationDetails = inputData.split(" ");
            if(relationDetails[2].equals(siblings)) {
                fetchSiblings(relationDetails[1]);
            } else if (relationDetails[2].equals(son) || relationDetails[2].equals(daughter) ) {
                fetchChildren(relationDetails[1], relationDetails[2]);
            } else if (relationDetails[2].equals(brotherInLaw) || relationDetails[2].equals(sisterInLaw)) {
                fetchBrotherOrSisterInLaw(relationDetails[1], relationDetails[2]);
            } else if (relationDetails[2].equals(maternalAunt) || relationDetails[2].equals(maternalUncle)) {
                fetchMaternalAuntOrUncle(relationDetails[1], relationDetails[2]);
            } else if (relationDetails[2].equals(paternalAunt) || relationDetails[2].equals(paternalUncle)) {
                fetchParentalAuntOrUncle(relationDetails[1], relationDetails[2]);
            } 
        }
    }
    
    public void addPerson(String person1, RelationType person1Relation, String person2, RelationType person2Relation) {
        Person familyRelation = new Person(person1,person1Relation,person2,person2Relation);
        relation.addRelation(familyRelation);
        
    }
    
    public void fetchChildren(String personName, String relationName) {
        RelationType getRelationType = fetchRelationType(relationName);
        System.out.println(relation.fetchChildren(personName, getRelationType));
    }
    
    public void fetchSiblings(String personName) {
        System.out.println(relation.fetchSiblings(personName));
    }
    
    
    public void fetchMaternalAuntOrUncle(String personName, String relationType) {
        RelationType getRelationType = fetchRelationType(relationType);
        System.out.println(relation.fetchMaternalUncleOrAunt(personName, getRelationType));
    }
    
    public void fetchParentalAuntOrUncle(String personName, String relationType) {
        RelationType getRelationType = fetchRelationType(relationType);
        System.out.println(relation.fetchPaternalUncleOrAunt(personName, getRelationType));
    }
   
    public void fetchBrotherOrSisterInLaw (String personName, String relationType) {
        RelationType getRelationType = fetchRelationType(relationType);
        System.out.println(relation.fetchBrotherOrSisterInLaw(personName, getRelationType));
    }
    
    public void addChild(String motherName, String childName, String childGender) {
        RelationType childRelationType = fetchRelationType(childGender);
        System.out.println(relation.addChild(motherName, childName, childRelationType));
    }
    
    
    
    public static void main(String args[]) {
        
        
        FamilyTree familyTree = new FamilyTree();
        
        // add family members in the below format
        // Child relationship --> "Father_Name", RelationType.FATHER, Child_Name, RelationType Son or Daughter
        // HUsband Wife relationship --> "Husband_Name, RelationType HUSBAND, WIFE_Name, RelationType WIFE
        familyTree.addPerson("Shan", RelationType.HUSBAND, "Anga", RelationType.WIFE);
        familyTree.addPerson("Shan", RelationType.FATHER, "Chit", RelationType.SON);
        familyTree.addPerson("Shan", RelationType.FATHER, "Ish", RelationType.SON);
        familyTree.addPerson("Shan", RelationType.FATHER, "Vich", RelationType.SON);
        familyTree.addPerson("Shan", RelationType.FATHER, "Aras", RelationType.SON);
        familyTree.addPerson("Shan", RelationType.FATHER, "Satya", RelationType.DAUGHTER);
        familyTree.addPerson("Chit", RelationType.HUSBAND, "Amba", RelationType.WIFE);
        familyTree.addPerson("Chit", RelationType.FATHER, "Dritha", RelationType.DAUGHTER);
        familyTree.addPerson("Chit", RelationType.FATHER, "Tritha", RelationType.DAUGHTER);
        familyTree.addPerson("Chit", RelationType.FATHER, "Vritha", RelationType.SON);
        familyTree.addPerson("Vich", RelationType.HUSBAND, "Lika", RelationType.WIFE);
        familyTree.addPerson("Vich", RelationType.FATHER, "Vila", RelationType.DAUGHTER);
        familyTree.addPerson("Vich", RelationType.FATHER, "Chika", RelationType.DAUGHTER);
        familyTree.addPerson("Aras", RelationType.HUSBAND, "Chitra", RelationType.WIFE);
        familyTree.addPerson("Aras", RelationType.FATHER, "Jnki", RelationType.DAUGHTER);
        familyTree.addPerson("Aras", RelationType.FATHER, "Ahit", RelationType.DAUGHTER);
        familyTree.addPerson("Vyan", RelationType.HUSBAND, "Satya", RelationType.WIFE);
        familyTree.addPerson("Vyan", RelationType.FATHER, "Asva", RelationType.SON);
        familyTree.addPerson("Vyan", RelationType.FATHER, "Vyas", RelationType.SON);
        familyTree.addPerson("Vyan", RelationType.FATHER, "Atya", RelationType.DAUGHTER);
        familyTree.addPerson("Jaya", RelationType.HUSBAND, "Dritha", RelationType.WIFE);
        familyTree.addPerson("Jaya", RelationType.FATHER, "Yodhan", RelationType.SON);
        familyTree.addPerson("Arit", RelationType.HUSBAND, "Jnki", RelationType.WIFE);
        familyTree.addPerson("Arit", RelationType.FATHER, "Laki", RelationType.SON);
        familyTree.addPerson("Arit", RelationType.FATHER, "Lavnya", RelationType.DAUGHTER);
        familyTree.addPerson("Asva", RelationType.HUSBAND, "Satvy", RelationType.WIFE);
        familyTree.addPerson("Asva", RelationType.FATHER, "Vasa", RelationType.SON);
        familyTree.addPerson("Vyas", RelationType.HUSBAND, "Krpi", RelationType.WIFE);
        familyTree.addPerson("Vyas", RelationType.FATHER, "Kriya", RelationType.SON);
        familyTree.addPerson("Vyas", RelationType.FATHER, "Krithi", RelationType.DAUGHTER);
        
        /*  Test Code
//        familyTree.addPerson("Kab", RelationType.FATHER, "Amba", RelationType.DAUGHTER);
//        familyTree.addPerson("Kab", RelationType.FATHER, "Arae", RelationType.SON);
//        familyTree.addPerson("Kabi", RelationType.FATHER, "Vyan", RelationType.SON);
//        familyTree.addPerson("Kabi", RelationType.FATHER, "Ayan", RelationType.SON);
        
       
        familyTree.addChild("Ka", "Xyz", "Female");
        familyTree.fetchChildren("Chit", "Daughter");
        familyTree.fetchChildren("Chit", "Son");
        familyTree.fetchSiblings("Chit");
        
        familyTree.fetchMaternalAuntOrUncle("Dritha", "Maternal-Uncle");
            
        familyTree.fetchParentalAuntOrUncle("Shan", "Paternal-Aunt");
        
        familyTree.fetchBrotherOrSisterInLaw("Tritha",RelationType.BROTHER_IN_LAW);
        
        familyTree.fetchBrotherOrSisterInLaw("Atya",RelationType.SISTER_IN_LAW);
        
        familyTree.check("Chit");
        familyTree.check("Amba");
        familyTree.check("Ka");
        */
        
        // Scanning inputs from document
        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String inputData = scanner.nextLine();
                familyTree.inputData(inputData);
            } scanner.close();
        } catch(Exception e) {
            System.out.println("document not found");
        }
        
    }
    
    
    
}
