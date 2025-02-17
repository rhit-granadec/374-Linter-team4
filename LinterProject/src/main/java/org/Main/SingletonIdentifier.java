package org.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SingletonIdentifier implements ClassContainerOperator{

    private final boolean abuseSearch;

    public SingletonIdentifier(boolean abuseSearch) {
        this.abuseSearch = abuseSearch;
    }

    @Override
    public void reworkClasses(List<ClassContainer> classList) {
        for (ClassContainer CC: classList) {
            boolean isSingleton = true;
            boolean isAbused = abuseSearch;
            for(ClassContainer.MethodContainer MC : CC.getMethods()){
                String access = MC.access;
                if(Objects.equals(MC.name, "<init>")
                        && !Objects.equals(access.charAt(access.length()-1), '-')){
                    isSingleton = false;
                }else if(Objects.equals(MC.returnValue, CC.getName())) {
                    if(access.length() < 9) {
                        isSingleton = false;
                    } else if(!access.startsWith("{static}")) {
                        isSingleton = false;
                    }
                }else {
                    isAbused = false;
                }
            }
            if(isAbused) CC.setAbusedSingleton();
            else if (isSingleton) CC.setSingleton();
        }
    }
}
