package org.Main;

import javax.lang.model.type.ArrayType;
import java.util.*;

public class DuplicateArrowRemover implements ClassContainerOperator{

    @Override
    public void reworkClasses(List<ClassContainer> classList) {
        for (ClassContainer CC: classList) {
            ArrayList<ClassContainer.AssociationContainer> assoc = CC.getAssociations();
            int i = 0;
            ArrayList<Integer> removes = new ArrayList<>();
            while(i < assoc.size()) {
                ClassContainer.AssociationContainer majorAssoc = assoc.get(i);
                for(int j = i+1; j < assoc.size(); j++) {
                    ClassContainer.AssociationContainer minorAssoc = assoc.get(j);
                    if(removeAssoc(majorAssoc, minorAssoc)) {
                        removes.add(j);
                    }
                }

//                while(!removes.isEmpty()){
//                    int currentPos = removes.pop();
//                    assoc.remove(currentPos);
//                }

                i++;
            }

            CC.setAssociations(assoc);
        }
    }

    public boolean removeAssoc(ClassContainer.AssociationContainer majorAssoc,
            ClassContainer.AssociationContainer minorAssoc) {
        if (majorAssoc.relationshipType == minorAssoc.relationshipType)
            if (Objects.equals(majorAssoc.AssociationName, minorAssoc.AssociationName)) {

                if (Objects.equals(majorAssoc.ClassName, minorAssoc.ClassName))
                    return true;
                else if (Objects.equals(majorAssoc.ClassName.replace('/', '.'),
                        minorAssoc.ClassName.replace('/', '.')))
                    return true;
            }
        return false;
    }
}

//.contains(classToAnalyze.replace('/', '.'))