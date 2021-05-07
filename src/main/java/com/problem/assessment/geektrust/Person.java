package com.problem.assessment.geektrust;


public class Person {
    
    private String person1;
    private RelationType person1Relation;
    
    private String person2;
    private RelationType person2Relation;
    
    
    Person(String person1, RelationType person1Relation, String person2, RelationType person2Relation) {
        this.person1 = person1;
        this.person1Relation = person1Relation;
        this.person2 = person2;
        this.person2Relation = person2Relation;
        
    }
    
 
    public String getPerson1() {
        return person1;
    }

    public RelationType getPerson1Relation() {
        return person1Relation;
    }

    public String getPerson2() {
        return person2;
    }

    public RelationType getPerson2Relation() {
        return person2Relation;
    }

}
