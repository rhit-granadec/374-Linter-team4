package org.Main;

import java.util.ArrayList;
import java.util.Queue;

public class Director {
    static String OutputPath = "outputs/";
    final String fileName;
    private String fullOutputFile;
    private SVGPrinter PUMLInterface;
    private ASMHandler ASMInterface = new ASMHandler();
    private ArrowAnalyzer arrowAnalyzer = new ArrowAnalyzer();
    private Queue<String> ClassesToAnalyze;

    public Director(Queue<String> ClassesToAnalyze, String fileName) {
        this.fileName = fileName;
        this.fullOutputFile = OutputPath + fileName + ".svg";
        PUMLInterface = new SVGPrinter(fullOutputFile);

        this.ClassesToAnalyze = ClassesToAnalyze;

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

