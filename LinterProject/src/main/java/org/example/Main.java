package org.example;

import org.objectweb.asm.ClassReader;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    static ArrayList<String> ClassesToAnalyze;
    static String OutputPath = "outputs/";
    static String fileName = "FullSetup";
    static String fullOutputFile = OutputPath + fileName + ".svg";
    private SVGPrinter PUMLInterface = new SVGPrinter(fullOutputFile);
    private ASMHandler ASMInterface = new ASMHandler();
    private ArrowAnalyzer arrowAnalyzer = new ArrowAnalyzer();


    public static void main(String[] args) {
        ClassesToAnalyze = new ArrayList<>();
        ClassesToAnalyze.add("org.requestedTest.DecryptionInputStream");
        ClassesToAnalyze.add("org.requestedTest.EncryptionOutputStream");
        ClassesToAnalyze.add("org.requestedTest.IDecryption");
        ClassesToAnalyze.add("org.requestedTest.IEncryption");
        ClassesToAnalyze.add("org.requestedTest.SubstitutionCipher");
        ClassesToAnalyze.add("org.requestedTest.TextEditorApp");
        Main runner = new Main();
    }

    public Main() {
        ArrayList<ClassContainer> classContainers = invokeASM();
        arrowAnalyzer.reworkClasses(classContainers);
        invokePUML(classContainers);
    }

    public ArrayList<ClassContainer> invokeASM() {
        return ASMInterface.CompileFiles(ClassesToAnalyze);
    }

    public void invokePUML(ArrayList<ClassContainer> ClassDetails) {
        PUMLInterface.output(ClassDetails);
    }
}

