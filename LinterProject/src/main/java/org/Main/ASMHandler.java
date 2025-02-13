package org.Main;

import org.objectweb.asm.ClassReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ASMHandler {
    HashSet<String> classesToAnalyze;
    Pattern javaPattern = Pattern.compile("^java");

    public ArrayList<ClassContainer> CompileFiles(Queue<String> classNames) {
        classesToAnalyze = new HashSet<String>(classNames);
        ArrayList<ClassContainer>  classContainers= new ArrayList<ClassContainer>();
        try {
            while(!classNames.isEmpty()) {
                String classToAnalyze = classNames.poll();
                Matcher m = javaPattern.matcher(classToAnalyze);
                if(m.find()) {
                    continue;
                }
                ClassPrinter cp = new ClassPrinter();
                try{
                    ClassReader cr = new ClassReader(classToAnalyze);
                    cr.accept(cp, 0);
                    ClassContainer cc = cp.getClassContainer();
                    classContainers.add(cc);
                    for(ClassContainer.AssociationContainer assoc : cc.getAssociations()) {
                        String cName = assoc.ClassName;
                        if(!classesToAnalyze.contains(cName)) {
                            System.out.println(cName);
                            classNames.offer(cName);
                            classesToAnalyze.add(cName);
                        }
                    }
                    for(String variable : cp.getVariables()) {
                        if(!classesToAnalyze.contains(variable)) {
                            System.out.println(variable);
                            classNames.offer(variable);
                            classesToAnalyze.add(variable);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("ClassReader FILE ERROR:");
                    System.out.println(e);
                    return null;
                }
            }
            return classContainers;
        } catch(Exception e) {
            System.out.println("INPUT FILE ERROR:");
            System.out.println(e);
            return null;
        }
    }
}
