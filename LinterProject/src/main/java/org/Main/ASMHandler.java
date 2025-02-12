package org.Main;

import org.objectweb.asm.ClassReader;

import java.util.ArrayList;

public class ASMHandler {
    ArrayList<String> classesToAnalyze;
    public ASMHandler() {
        this.classesToAnalyze = new ArrayList<String>();
    }

    public ArrayList<ClassContainer> CompileFiles(ArrayList<String> ClassNames) {

        ArrayList<ClassContainer>  classContainers= new ArrayList<ClassContainer>();
        try {
            for(String classToAnalyze : ClassNames) {
                ClassPrinter cp = new ClassPrinter();
                try{
                    ClassReader cr = new ClassReader(classToAnalyze);
                    cr.accept(cp, 0);
                    classContainers.add(cp.getClassContainer());
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
