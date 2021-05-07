package com.problem.assessment.geektrust;

import java.util.ArrayList;
import java.util.List;

public class Relation {
    
    private String personUnavailable = "PERSON_NOT_FOUND";
    
    private String childAdditionSuccess = "CHILD_ADDITION_SUCCEEDED";
    
    private String childAdditionFailed = "CHILD_ADDITION_FAILED";
    
    private String nothingToReturn = "NONE";
    
    private List<Person> personRelation = new ArrayList<>();

    public void addRelation(Person relation) {
        personRelation.add(relation);
    }
    
    //Check person exists or not
    public boolean checkPersonExists(String personName) {
        for(Person person : personRelation) {
            if(person.getPerson1().equalsIgnoreCase(personName) || person.getPerson2().equalsIgnoreCase(personName)) {
                return true;
            }
        }
        return false;
    }
    
    //Print result
    public String printResult(List<String> result) {
        if(result.isEmpty()) {
            return nothingToReturn;
        } else {
            return result.toString().replace("[", "").replace("]", "").replace(",", "");
        }
    }
    
    // Child Addition
    public String addChild(String motherName, String childName, RelationType childRelationType) {
        if(checkPersonExists(motherName)) {
            for(Person person : personRelation) {
                if(person.getPerson2().equalsIgnoreCase(motherName)) {
                    if(person.getPerson2Relation().equals(RelationType.WIFE)) {
                        String husbandName = person.getPerson1();
                        Person addChild = new Person(husbandName, RelationType.FATHER,childName,childRelationType);
                        personRelation.add(addChild);
                        return childAdditionSuccess;
                    }
                }
            }
            return childAdditionFailed;
        }
        return personUnavailable;
       
    }

    //Fetching Children
    public String fetchChildren(String parentName, RelationType type) {

        List<String> childrenName = new ArrayList<String>();
        if(checkPersonExists(parentName)) {
            for(Person person : personRelation) {
                if(person.getPerson1().equals(parentName) && person.getPerson1Relation().equals(RelationType.FATHER)) {
                    if(person.getPerson2Relation().equals(type)) {
                        childrenName.add(person.getPerson2());
                    }
                }

                if(person.getPerson2().equalsIgnoreCase(parentName) && person.getPerson2Relation().equals(RelationType.WIFE)) {
                    String husbandName = person.getPerson1();
                    for(Person getChildren : personRelation) {
                        if(getChildren.getPerson1().equals(husbandName) && getChildren.getPerson1Relation().equals(RelationType.FATHER)) {
                            if(getChildren.getPerson2Relation().equals(type)) {
                                childrenName.add(getChildren.getPerson2());
                            }
                        }
                    }
                    
                }
            } 
            return printResult(childrenName);
        }
        return personUnavailable;

    }

    
    // Fetching siblings
    public String fetchSiblings(String personName) {
        List<String> siblingsName = new ArrayList<String>();
        if(checkPersonExists(personName)) {
            for(Person person : personRelation ) {
                if (person.getPerson2().equalsIgnoreCase(personName)) {
                    if(person.getPerson1Relation().equals(RelationType.FATHER)) {
                        String fatherName = person.getPerson1();
                        for(Person getSiblings : personRelation) {
                            if(getSiblings.getPerson1().equals(fatherName)) {
                                if(!getSiblings.getPerson2().equalsIgnoreCase(personName)) {
                                    siblingsName.add(getSiblings.getPerson2());
                                }
                            }
                        }
                        return printResult(siblingsName);
                    }
                }
            }
        }
        return personUnavailable;

    }
    
    //Fetching Maternal Uncle or Aunt
    public String fetchMaternalUncleOrAunt(String personName, RelationType relation) {
        List<String> maternalUncleOrAunt = new ArrayList<String>();
        if(checkPersonExists(personName)) {
            for(Person person : personRelation) {
                if(person.getPerson2().equalsIgnoreCase(personName)) {
                    if(person.getPerson1Relation().equals(RelationType.FATHER)) {
                        String personFatherName = person.getPerson1();
                        for(Person getMotherName : personRelation ) {
                            if(getMotherName.getPerson1().equals(personFatherName) && getMotherName.getPerson1Relation().equals(RelationType.HUSBAND)) {
                                String motherName = getMotherName.getPerson2();
                                for ( Person getMotherFatherName : personRelation ) {
                                    if (getMotherFatherName.getPerson1Relation().equals(RelationType.FATHER) && getMotherFatherName.getPerson2().equals(motherName)) {
                                        String motherFatherName = getMotherFatherName.getPerson1();
                                        for(Person getMaternalUncleOrAunt : personRelation ) {
                                            if(getMaternalUncleOrAunt.getPerson1().equals(motherFatherName)) {
                                                if(!getMaternalUncleOrAunt.getPerson2().equalsIgnoreCase(motherName)) {
                                                    if(relation.equals(RelationType.MATERNAL_UNCLE)) {
                                                        if(getMaternalUncleOrAunt.getPerson2Relation().equals(RelationType.SON)) {
                                                            maternalUncleOrAunt.add(getMaternalUncleOrAunt.getPerson2());
                                                        }
                                                    } else if (relation.equals(RelationType.MATERNAL_AUNT)) {
                                                        if(getMaternalUncleOrAunt.getPerson2Relation().equals(RelationType.DAUGHTER)) {
                                                            maternalUncleOrAunt.add(getMaternalUncleOrAunt.getPerson2());
                                                        }
                                                    }

                                                }

                                            }
                                        }

                                    }
                                }
                            }
                        }
                        return printResult(maternalUncleOrAunt);
                    }
                }
            }
        }
        return personUnavailable;
    }

    // Fetching Paternal Uncle Or Aunt
    public String fetchPaternalUncleOrAunt(String personName, RelationType relationType) {
        List<String> paternalUncleOrAunt = new ArrayList<String>();
        if(checkPersonExists(personName)) {
            for(Person getChildFatherName : personRelation) {
                if(getChildFatherName.getPerson2().equalsIgnoreCase(personName)) {
                    if(getChildFatherName.getPerson1Relation().equals(RelationType.FATHER)) {
                        String childFatherName = getChildFatherName.getPerson1();
                        for(Person paternalFatherName : personRelation) {
                            if(paternalFatherName.getPerson1Relation().equals(RelationType.FATHER) && paternalFatherName.getPerson2().equals(childFatherName)) {
                                String patFatherName = paternalFatherName.getPerson1();
                                for(Person getPaternalUncleOrAunt : personRelation) {
                                    if(getPaternalUncleOrAunt.getPerson1().equals(patFatherName)) {
                                        if(!getPaternalUncleOrAunt.getPerson2().equals(childFatherName)) {
                                            if(relationType.equals(RelationType.PATERNAL_UNCLE)) {
                                                if(getPaternalUncleOrAunt.getPerson2Relation().equals(RelationType.SON)) {
                                                    paternalUncleOrAunt.add(getPaternalUncleOrAunt.getPerson2());
                                                }
                                            } else if ( relationType.equals(RelationType.PATERNAL_AUNT)) {
                                                if(getPaternalUncleOrAunt.getPerson2Relation().equals(RelationType.DAUGHTER)) {
                                                    paternalUncleOrAunt.add(getPaternalUncleOrAunt.getPerson2());
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                        return printResult(paternalUncleOrAunt);
                    }

                }
            }
        }
        return personUnavailable;
    }
    
    
    // Fetching Brother In Law & Sister In Law
    public String fetchBrotherOrSisterInLaw(String personName, RelationType relationType) {
       
        if(checkPersonExists(personName)) {
            if(relationType.equals(RelationType.BROTHER_IN_LAW)) {
                List<String> brotherInlaw = new ArrayList<String>();
                for(Person person : personRelation) {
                    if(person.getPerson2Relation().equals(RelationType.SON) && person.getPerson2().equalsIgnoreCase(personName)) {
                        fetchOwnBrotherInLawOrSisterInLaw(personName, relationType, brotherInlaw);
                        fetchWifeBrothersOrSisters(personName, relationType, brotherInlaw);
                        return printResult(brotherInlaw);
                    } else if (person.getPerson2Relation().equals(RelationType.DAUGHTER) || person.getPerson2Relation().equals(RelationType.WIFE) && person.getPerson2().equalsIgnoreCase(personName)) {
                        fetchOwnBrotherInLawOrSisterInLaw(personName, relationType, brotherInlaw);
                        fetchHusbandBrothersOrSiters(personName, relationType, brotherInlaw);
                        return printResult(brotherInlaw);
                    } 
                }
                
            } else if(relationType.equals(RelationType.SISTER_IN_LAW)) {
                List<String> sisterInLaw = new ArrayList<String>();
                for(Person person : personRelation) {
                    if(person.getPerson2Relation().equals(RelationType.SON) && person.getPerson2().equalsIgnoreCase(personName)) {
                        fetchOwnBrotherInLawOrSisterInLaw(personName, relationType, sisterInLaw);
                        fetchWifeBrothersOrSisters(personName, relationType, sisterInLaw);
                        return printResult(sisterInLaw);
                    } else if (person.getPerson2Relation().equals(RelationType.DAUGHTER) || person.getPerson2Relation().equals(RelationType.WIFE) && person.getPerson2().equalsIgnoreCase(personName)) {
                        fetchOwnBrotherInLawOrSisterInLaw(personName, relationType, sisterInLaw);
                        fetchHusbandBrothersOrSiters(personName, relationType, sisterInLaw);
                        return printResult(sisterInLaw);
                    } 
                }
            } 
        }

        return personUnavailable;
    }
    
    // Fetching Own Brother wife or Sister Husband
    public void fetchOwnBrotherInLawOrSisterInLaw(String personName, RelationType relationType, List<String> addBrotherOrSisterInLaw) {
        for(Person getPersonFatherName : personRelation) {
            if(getPersonFatherName.getPerson1Relation().equals(RelationType.FATHER) && getPersonFatherName.getPerson2().equalsIgnoreCase(personName)) {
                String personFathername = getPersonFatherName.getPerson1();
                for(Person personSiblings : personRelation) {
                    if(personSiblings.getPerson1().equals(personFathername) && personSiblings.getPerson1Relation().equals(RelationType.FATHER)) {
                        if(!personSiblings.getPerson2().equalsIgnoreCase(personName)) {
                            if(relationType.equals(RelationType.BROTHER_IN_LAW)) {
                                String sisterName = personSiblings.getPerson2();
                                for(Person getSisterHusband : personRelation) {
                                    if(getSisterHusband.getPerson1Relation().equals(RelationType.HUSBAND) && getSisterHusband.getPerson2().equals(sisterName)) {
                                        addBrotherOrSisterInLaw.add(getSisterHusband.getPerson1());
                                    }
                                }
                            } else if (relationType.equals(RelationType.SISTER_IN_LAW)) {
                                String brotherName = personSiblings.getPerson2();
                                for(Person getBrotherWife : personRelation) {
                                    if(getBrotherWife.getPerson2Relation().equals(RelationType.WIFE) && getBrotherWife.getPerson1().equals(brotherName)) {
                                        addBrotherOrSisterInLaw.add(getBrotherWife.getPerson2());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    // Fetching Wife Brothers or Sisters
    public void fetchWifeBrothersOrSisters(String personName, RelationType relationType, List<String> addBrotherOrSisterInLaw) {
        for(Person getWifeName : personRelation) {
            if(getWifeName.getPerson2Relation().equals(RelationType.WIFE) && getWifeName.getPerson1().equalsIgnoreCase(personName)) {
                String wifeName = getWifeName.getPerson2();
                for(Person wifeRelation : personRelation) {
                    if(wifeRelation.getPerson1Relation().equals(RelationType.FATHER) && wifeRelation.getPerson2().equals(wifeName)) {
                        String wifeFatherName = wifeRelation.getPerson1();
                        for(Person getWifeSiblings : personRelation) {
                            if(getWifeSiblings.getPerson1().equals(wifeFatherName) && getWifeSiblings.getPerson1Relation().equals(RelationType.FATHER)) {
                                if(relationType.equals(RelationType.BROTHER_IN_LAW)) {
                                    if(getWifeSiblings.getPerson2Relation().equals(RelationType.SON)) {
                                        addBrotherOrSisterInLaw.add(getWifeSiblings.getPerson2());
                                    }
                                } else if ( relationType.equals(RelationType.SISTER_IN_LAW)) {
                                    if(getWifeSiblings.getPerson2Relation().equals(RelationType.DAUGHTER)) {
                                        if(!getWifeSiblings.getPerson2().equalsIgnoreCase(wifeName)) {
                                            addBrotherOrSisterInLaw.add(getWifeSiblings.getPerson2());
                                        }
                                    }

                                }

                            }
                        }

                    }
                }
            }
        }
    }
    
    // Fetching Husband Brothers or Sisters
    public void fetchHusbandBrothersOrSiters ( String personName, RelationType relationType, List<String> addBrotherOrSisterInLaw) {
        for(Person getHusbandName : personRelation) {
            if(getHusbandName.getPerson1Relation().equals(RelationType.HUSBAND) && getHusbandName.getPerson2().equalsIgnoreCase(personName)) {
                String husbandName = getHusbandName.getPerson1();
                for(Person husbandRelation : personRelation) {
                    if(husbandRelation.getPerson1Relation().equals(RelationType.FATHER) && husbandRelation.getPerson2().equals(husbandName)) {
                        String husbandFatherName = husbandRelation.getPerson1();
                        for(Person getHusbandSiblings : personRelation) {
                            if(getHusbandSiblings.getPerson1().equals(husbandFatherName) && getHusbandSiblings.getPerson1Relation().equals(RelationType.FATHER)) {
                                if(relationType.equals(RelationType.BROTHER_IN_LAW)) {
                                    if(getHusbandSiblings.getPerson2Relation().equals(RelationType.SON)) {
                                        if(!getHusbandSiblings.getPerson2().equals(husbandName)) {
                                            addBrotherOrSisterInLaw.add(getHusbandSiblings.getPerson2());
                                        }
                                    }
                                }  else if (relationType.equals(RelationType.SISTER_IN_LAW)) {
                                    if(getHusbandSiblings.getPerson2Relation().equals(RelationType.DAUGHTER)) {
                                        addBrotherOrSisterInLaw.add(getHusbandSiblings.getPerson2());
                                    }
                                }

                            }
                        }
                    }
                }

            }
        }
    }
}
