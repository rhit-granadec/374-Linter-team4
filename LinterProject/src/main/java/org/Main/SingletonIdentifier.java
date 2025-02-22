package org.Main;

import java.util.*;

public class SingletonIdentifier implements ClassContainerOperator{

    private final boolean abuseSearch;

    public SingletonIdentifier(boolean abuseSearch) {
        this.abuseSearch = abuseSearch;
    }

    @Override
    public void reworkClasses(List<ClassContainer> classList) {
        Set<ClassContainer> singletons = new HashSet<>();
        boolean hasChanged = true;
        while(hasChanged) {
            hasChanged = false;
//            List<ClassContainer> newClassList = List.copyOf(classList);
            for (ClassContainer CC: classList) {
                if(singletons.contains(CC)) {
                    continue;
                }
                boolean isSingleton = true;
                boolean hasConstructors = false;
                boolean hasMethods = false;
                boolean isAbused = abuseSearch;
                boolean extendsSingleton = false;
                for(ClassContainer.MethodContainer MC : CC.getMethods()){
                    String access = MC.access;
                    if(Objects.equals(MC.name, "<init>") || Objects.equals(MC.name, "<clinit>")){
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

                for (ClassContainer.AssociationContainer assoc : CC.getAssociations()) {
                    if (assoc.relationshipType == ClassContainer.relationshipType.Extension) {
                        String superSingleton = assoc.ClassName;
                        for(ClassContainer cc2 : classList) {
                            if(cc2.getName().equals(superSingleton)) {
                                if(cc2.isSingleton()) {
                                    extendsSingleton = true;
                                }
                                break;
                            }
                        }
                    }
                }

                if(isAbused && (hasConstructors && hasMethods) || extendsSingleton) {
                    CC.setAbusedSingleton();
                    singletons.add(CC);
                    hasChanged = true;
                }
                else if (isSingleton && (hasConstructors && hasMethods) || extendsSingleton) {
                    CC.setSingleton();
                    singletons.add(CC);
                    hasChanged = true;
                }
            }
        }
    }
}
