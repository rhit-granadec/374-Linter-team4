package org.Main;

import java.util.ArrayList;

public class ClassContainer {

    private String name;
    private String fullName;
    private ArrayList<MethodContainer> Methods;
    boolean isAbstract = false;
    boolean isInterface = false;

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
        AssociationContainer(String className, String associationName, relationshipType relationshipType){
            this.ClassName = className;
            this.AssociationName = associationName;
            this.relationshipType = relationshipType;
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

    public void addAssociation(String className, String associationName, relationshipType relationshipType) {
        Associations.add(new AssociationContainer(className, associationName, relationshipType));
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
}
