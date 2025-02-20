package org.Main;

import javax.lang.model.type.ArrayType;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlacklistArrowRemover implements ClassContainerOperator {
    Pattern blacklistPattern;

    public BlacklistArrowRemover(Pattern blacklistPattern) {
        this.blacklistPattern = blacklistPattern;
    }

    @Override
    public void reworkClasses(List<ClassContainer> classList) {
        for (ClassContainer CC: classList) {

            ArrayList<ClassContainer.AssociationContainer> assocs = CC.getAssociations();
            int i = 0;
            HashSet<Integer> removes = new HashSet<>();
            while(i < assocs.size()) {
                ClassContainer.AssociationContainer assoc = assocs.get(i);
                Matcher m = blacklistPattern.matcher(assoc.ClassName);
                if(m.find()) {
                    removes.add(i);
                }
                i++;
            }

            Integer[] results = new Integer[removes.size()];
            removes.toArray(results);
            Arrays.sort(results);

            for(i = results.length-1; i >=0; i--){
                assocs.remove((int) results[i]);
            }

            CC.setAssociations(assocs);
        }
    }
}