package org.Main;

import java.util.ArrayList;
import java.util.Stack;

public class ArrowAnalyzer {

    public void reworkClasses(ArrayList<ClassContainer> classList) {
        dependencyAnalysis(classList);
        associationAnalysis(classList);
    }

    private void dependencyAnalysis(ArrayList<ClassContainer> classList) {
        ArrayList<String> names = new ArrayList<String>();
        for(ClassContainer containedClass : classList) {
            names.add(containedClass.getName());
        }

        for(ClassContainer containedClass : classList) {
            ArrayList<ClassContainer.MethodContainer> methods = containedClass.getMethods();
            for(ClassContainer.MethodContainer method : methods) {
                for(String input : method.inputs) {
                    if(names.contains(input)) {
                        containedClass.addDependencies(input);
                    }
                }
            }
        }
    }

    private void associationAnalysis(ArrayList<ClassContainer> classList) {
        ArrayList<String> names = new ArrayList<String>();
        for(ClassContainer containedClass : classList) {
            names.add(containedClass.getName());
        }

        for(ClassContainer containedClass : classList) {
            Stack<Integer> removes = new Stack<>();
            ArrayList<ClassContainer.FieldContainer> fields = containedClass.getFields();
            for(ClassContainer.FieldContainer field : fields) {
                if(names.contains(field.returnValue)) {
                    removes.add(fields.indexOf(field));
                    containedClass.addAssociation(field.returnValue, field.name);
                }
            }
            while(!removes.empty()){
                containedClass.getFields().remove(((int) removes.pop()));
            }
        }
    }

}
