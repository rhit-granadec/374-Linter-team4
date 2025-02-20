package org.Main;

import java.util.ArrayList;

public class ClassContainer {

    private String name;
    private ArrayList<MethodContainer> Methods;
    boolean isAbstract = false;
    boolean isInterface = false;
    boolean isSingleton = false;
    boolean isAbusedSingleton = false;
    boolean isDecorator = false;//z

    public class MethodContainer {
        public String name;
        public String access;
        public String returnValue;
        public ArrayList<String> inputs;

        MethodContainer(String name, String access, String returnValue, ArrayList<String> inputs){
            this.name = name;
            this.access = access;
            this.returnValue = returnValue;
            this.inputs = inputs;
        }
    }
    private ArrayList<FieldContainer> Fields;

    public class FieldContainer {
        public String name;
        public String returnValue;

        FieldContainer(String name, String returnValue){
            this.name = name;
            this.returnValue = returnValue;
        }
    }

    private ArrayList<AssociationContainer> Associations;


    public class AssociationContainer {
        public String ClassName;
        public String AssociationName;
        public relationshipType relationshipType;
        //z
        public String LCardinality;
        public String RCardinality;
        AssociationContainer(String className, String associationName, relationshipType relationshipType){
            this.ClassName = className;
            this.AssociationName = associationName;
            this.relationshipType = relationshipType;
            //z
            this.RCardinality = "";
            this.LCardinality = "";
        }
        //z
        public void setLCardinality(String card) {
            this.LCardinality = card;
        }

        public void setRCardinality(String card) {
            this.RCardinality = card;
        }
    }

    public enum relationshipType {
        Aggregation,
        Dependency,
        DependencyWeak,
        Extension,
        Implementation,
        Composition,
    }


    public ClassContainer() {
        Methods = new ArrayList<MethodContainer>();
        Fields = new ArrayList<FieldContainer>();
        Associations = new ArrayList<AssociationContainer>();
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addMethod(String methodName, String access, String returnValue, ArrayList<String> inputs) {
        Methods.add(new MethodContainer(methodName, access, returnValue, inputs));
    }

    public void addField(String fieldName, String type ) {
        Fields.add(new FieldContainer(fieldName, type));
    }

//    public void addAssociation(String className, String associationName, relationshipType relationshipType) {
//        Associations.add(new AssociationContainer(className, associationName, relationshipType));
//    }

    public void addAssociation(String className, String associationName, relationshipType relationshipType, String leftCard, String rightCard) {
        AssociationContainer assoc = new AssociationContainer(className, associationName, relationshipType);
        assoc.setLCardinality(leftCard);
        assoc.setRCardinality(rightCard);
        Associations.add(assoc);
    }

    public void addAssociation(String className, String associationName, relationshipType relationshipType) {
        addAssociation(className, associationName, relationshipType, "", "");
    }

    public ArrayList<MethodContainer> getMethods() {
        return Methods;
    }

    public ArrayList<FieldContainer> getFields() {
        return Fields;
    }

    public ArrayList<AssociationContainer> getAssociations() {
        return Associations;
    }
    public void setAssociations(ArrayList<AssociationContainer> givenAssociations) {
        Associations = givenAssociations;
    }

    public void makeAbstract() {
        this.isAbstract = true;
    }

    public void makeInterface() {
        this.isInterface = true;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public boolean isSingleton() {return isSingleton;}
    public boolean isAbusedSingleton() {return isAbusedSingleton;}
    public void setSingleton() {this.isSingleton = true;}
    public void setAbusedSingleton() {
        setSingleton();
        isAbusedSingleton = true;
    }
    //z
    public void setDecorator(){
        this.isDecorator = true;
    }
    //z
    public boolean isDecorator(){
        return isDecorator;
    }

}