package org.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ArrowAnalyzer implements ClassContainerOperator {

    @Override
    public void reworkClasses(List<ClassContainer> classList) {
        dependencyAnalysis(classList);
        associationAnalysis(classList);
    }

    private void dependencyAnalysis(List<ClassContainer> classList) {
        ArrayList<String> names = new ArrayList<String>();
        for (ClassContainer containedClass : classList) {
            names.add(containedClass.getName());
        }

        for (ClassContainer containedClass : classList) {
            ArrayList<ClassContainer.MethodContainer> methods = containedClass.getMethods();
            for (ClassContainer.MethodContainer method : methods) {
                for (String input : method.inputs) {
                    if (names.contains(input)) {
//                        containedClass.addAssociation(input, null, ClassContainer.relationshipType.Dependency);
                        containedClass.addAssociation(input, null, ClassContainer.relationshipType.Dependency, "1", "1");
                    }
                }
            }
        }
    }

    private void associationAnalysis(List<ClassContainer> classList) {
        ArrayList<String> names = new ArrayList<String>();
        for (ClassContainer containedClass : classList) {
            names.add(containedClass.getName());
        }

//        for(ClassContainer containedClass : classList) {
//            Stack<Integer> removes = new Stack<>();
//            ArrayList<ClassContainer.FieldContainer> fields = containedClass.getFields();
//            for(ClassContainer.FieldContainer field : fields) {
//                if(names.contains(field.returnValue)) {
//                    removes.add(fields.indexOf(field));
//                    containedClass.addAssociation(
//                            field.returnValue,
//                            field.name,
//                            ClassContainer.relationshipType.Dependency);
//                }
//            }
//            while(!removes.empty()){
//                containedClass.getFields().remove(((int) removes.pop()));
//            }
//        }
        for (ClassContainer containedClass : classList) {
            ArrayList<ClassContainer.FieldContainer> fields = containedClass.getFields();
            for (ClassContainer.FieldContainer field : fields) {
                if (names.contains(field.returnValue)) {
                    String rightCard = "0..1";
                    if (field.returnValue.endsWith("[]")) {
                        rightCard = "0..*";
                    } else if (isCollectionType(field.returnValue)) {
                        rightCard = "0..*";
                    }
                    containedClass.addAssociation(field.returnValue, field.name,
                            ClassContainer.relationshipType.Dependency,
                            "1", rightCard);
                }
            }
        }
    }

    private boolean isCollectionType(String typeName) {
        if (typeName == null) return false;
        return typeName.startsWith("java.util.List")
                || typeName.startsWith("java.util.Set")
                || typeName.startsWith("java.util.Collection")
                || typeName.startsWith("java.util.Map");
    }
}


