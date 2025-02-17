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
            boolean hasConstructors = false;
            boolean hasMethods = false;
            boolean isAbused = abuseSearch;
            for(ClassContainer.MethodContainer MC : CC.getMethods()){
                String access = MC.access;
                if(Objects.equals(MC.name, "<init>")){
                     if(!Objects.equals(access.charAt(access.length()-1), '-')){
                        isSingleton = false;
                     }
                     hasConstructors = true;
                }else if(Objects.equals(MC.returnValue, CC.getName())) {
                    if(access.length() < 9) {
                        isSingleton = false;
                    } else if(!access.startsWith("{static}")) {
                        isSingleton = false;
                    }
                    hasMethods = true;
                }else {
                    isAbused = false;
                    hasMethods = true;
                }
            }
            if(isAbused && hasConstructors && hasMethods) CC.setAbusedSingleton();
            else if (isSingleton && hasConstructors && hasMethods) CC.setSingleton();
        }
    }
}
