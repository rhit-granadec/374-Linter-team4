package org.Main;

import java.util.ArrayList;
import java.util.List;

public class DecoratorIdentifier implements ClassContainerOperator {
    //z
    @Override
    public void reworkClasses(List<ClassContainer> classList) {
        for (ClassContainer cc : classList) {
            ArrayList<String> implementedInterfaces = new ArrayList<>();
            ArrayList<String> extendedClasses = new ArrayList<>();
            for (ClassContainer.AssociationContainer assoc : cc.getAssociations()) {
                if (assoc.relationshipType == ClassContainer.relationshipType.Implementation) {
                    implementedInterfaces.add(assoc.ClassName);
                }
                if (assoc.relationshipType == ClassContainer.relationshipType.Extension) {
                    extendedClasses.add(assoc.ClassName);
                    for (int i = 0; i < classList.size(); i++) {
                        if (extendedClasses.contains(classList.get(i).getName())) {
                            for (ClassContainer.AssociationContainer extendAssoc : classList.get(i).getAssociations()) {
                                if (extendAssoc.relationshipType == ClassContainer.relationshipType.Extension) {
                                    extendedClasses.add(extendAssoc.ClassName);
                                }
                            }
                        }
                    }
                }
            }

            if (implementedInterfaces.isEmpty() && extendedClasses.isEmpty()) {
                continue;
            }

            boolean hasComponentField = false;
            for (ClassContainer.FieldContainer field : cc.getFields()) {
                if (implementedInterfaces.contains(field.returnValue)) {
                    hasComponentField = true;
                    break;
                }
                if (extendedClasses.contains(field.returnValue)) {
                    hasComponentField = true;
                    break;
                }
            }
            boolean hasComponentInConstructor = false;
            for (ClassContainer.MethodContainer method : cc.getMethods()) {
                if (method.name.contains("<init>") || method.name.contains("<clinit>")) {
                    for (String paramType : method.inputs) {
                        if (implementedInterfaces.contains(paramType)) {
                            hasComponentInConstructor = true;
                            break;
                        }
                        if (extendedClasses.contains(paramType)) {
                            hasComponentInConstructor = true;
                            break;
                        }
                    }
                    if (hasComponentInConstructor) {
                        break;
                    }
                }
            }

            boolean hasDelegation = false;
            for (ClassContainer.MethodContainer method : cc.getMethods()) {
                if (!method.name.contains("<init>")) {
                    String lowerName = method.name.toLowerCase();
                    if (lowerName.contains("delegate") || lowerName.contains("wrap")) {
                        hasDelegation = true;
                        break;
                    }
                }
            }

            if (hasComponentField || hasComponentInConstructor || hasDelegation) {
                cc.setDecorator();
            }
        }
    }
}
