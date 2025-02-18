package org.Main;

import java.util.ArrayList;
import java.util.List;

public class DecoratorIdentifier implements ClassContainerOperator {
    //z
    @Override
    public void reworkClasses(List<ClassContainer> classList) {
        for (ClassContainer cc : classList) {
            ArrayList<String> implementedInterfaces = new ArrayList<>();
            for (ClassContainer.AssociationContainer assoc : cc.getAssociations()) {
                if (assoc.relationshipType == ClassContainer.relationshipType.Implementation) {
                    implementedInterfaces.add(assoc.ClassName);
                }
            }

            if (implementedInterfaces.isEmpty()) {
                continue;
            }

            boolean hasComponentField = false;
            for (ClassContainer.FieldContainer field : cc.getFields()) {
                if (implementedInterfaces.contains(field.returnValue)) {
                    hasComponentField = true;
                    break;
                }
            }

            boolean hasComponentInConstructor = false;
            for (ClassContainer.MethodContainer method : cc.getMethods()) {
                if (method.name.contains("<init>")) {
                    for (String paramType : method.inputs) {
                        if (implementedInterfaces.contains(paramType)) {
                            hasComponentInConstructor = true;
                            break;
                        }
                    }
                    if (hasComponentInConstructor) {
                        break;
                    }
                }
            }

            if (hasComponentField || hasComponentInConstructor) {
                cc.setDecorator();
            }
        }
    }
}
