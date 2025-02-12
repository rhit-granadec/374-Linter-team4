package org.example;

import org.objectweb.asm.ClassReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Objects;

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
