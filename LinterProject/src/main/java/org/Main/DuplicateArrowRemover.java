package org.Main;

import javax.lang.model.type.ArrayType;
import java.util.*;

public class DuplicateArrowRemover implements ClassContainerOperator{

    @Override
    public void reworkClasses(List<ClassContainer> classList) {
        for (ClassContainer CC: classList) {

            ArrayList<ClassContainer.AssociationContainer> assoc = CC.getAssociations();
            int i = 0;
            HashSet<Integer> removes = new HashSet<>();
            while(i < assoc.size()) {
                ClassContainer.AssociationContainer majorAssoc = assoc.get(i);
                for(int j = 0; j < assoc.size(); j++) {
                    if(i == j) continue;
                    if(removes.contains(i)) continue;
                    ClassContainer.AssociationContainer minorAssoc = assoc.get(j);
                    if(removeAssoc(majorAssoc, minorAssoc)) {
                        removes.add(j);
                    }
                }
                i++;
            }

            Integer[] results = new Integer[removes.size()];
            removes.toArray(results);
            Arrays.sort(results);

            for(i = results.length-1; i >=0; i--){
                assoc.remove((int) results[i]);
            }

            CC.setAssociations(assoc);
        }
    }

    public boolean removeAssoc(ClassContainer.AssociationContainer majorAssoc,
            ClassContainer.AssociationContainer minorAssoc) {
        if (majorAssoc.relationshipType == minorAssoc.relationshipType
            || minorAssoc.relationshipType.equals(ClassContainer.relationshipType.DependencyWeak))
            if (minorAssoc.AssociationName == null || minorAssoc.AssociationName.isEmpty()
                    || Objects.equals(majorAssoc.AssociationName, minorAssoc.AssociationName)) {

                if (Objects.equals(majorAssoc.ClassName, minorAssoc.ClassName))
                    return true;
                else return Objects.equals(majorAssoc.ClassName.replace('/', '.'),
                        minorAssoc.ClassName.replace('/', '.'));
            }
        return false;
    }
}

//.contains(classToAnalyze.replace('/', '.'))