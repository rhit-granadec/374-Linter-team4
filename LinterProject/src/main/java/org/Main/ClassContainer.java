package org.Main;

import java.util.ArrayList;

public class ClassContainer {

    private String name;
    private ArrayList<MethodContainer> Methods;
    boolean isAbstract = false;
    boolean isInterface = false;

    public class MethodContainer {
        public String name;
        public String returnValue;
        public ArrayList<String> inputs;

        MethodContainer(String name, String returnValue, ArrayList<String> inputs){
            this.name = name;
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
    private ArrayList<String> Dependencies;
    private ArrayList<String> Inherits;

    private ArrayList<AssociationContainer> Associations;

    public class AssociationContainer {
        public String ClassName;
        public String AssociationName;

        AssociationContainer(String className, String associationName){
            this.ClassName = className;
            this.AssociationName = associationName;
        }
    }


    public ClassContainer() {
        Methods = new ArrayList<MethodContainer>();
        Fields = new ArrayList<FieldContainer>();
        Dependencies = new ArrayList<String>();
        Inherits = new ArrayList<String>();
        Associations = new ArrayList<AssociationContainer>();
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addMethod(String methodName, String returnValue, ArrayList<String> inputs) {
        Methods.add(new MethodContainer(methodName, returnValue, inputs));
    }

    public void addField(String fieldName, String returnType ) {
        Fields.add(new FieldContainer(fieldName, returnType));
    }

    public void addDependencies(String dependencyName ) {
        Dependencies.add(dependencyName);
    }

    public void addAssociation(String className, String associationName) {
        Associations.add(new AssociationContainer(className, associationName));
    }

    public void addInheritance(String inheritedName) {
        Inherits.add(inheritedName);
    }

    public ArrayList<MethodContainer> getMethods() {
        return Methods;
    }

    public ArrayList<FieldContainer> getFields() {
        return Fields;
    }

    public ArrayList<String> getDependencies() {
        return Dependencies;
    }

    public ArrayList<AssociationContainer> getAssociations() {
        return Associations;
    }

    public ArrayList<String> getInherits() {
        return Inherits;
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
