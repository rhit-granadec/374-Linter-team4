package org.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Director {
    static String OutputPath = "outputs/";
    private final SVGPrinter PUMLInterface;
    private final ASMHandler ASMInterface = new ASMHandler();
    private final Queue<String> ClassesToAnalyze;
    private final ArrayList<ClassContainerOperator> operationPipeline = new ArrayList<ClassContainerOperator>();

    public Director(Queue<String> ClassesToAnalyze,
                    String fileName,
                    Set<String> identifiers) {
        String fullOutputFile = OutputPath + fileName + ".svg";
        PUMLInterface = new SVGPrinter(fullOutputFile);

        this.ClassesToAnalyze = ClassesToAnalyze;

        composeAnalysisPipeline(identifiers);
        beginAnalysis();
    }

    public void composeAnalysisPipeline(Set<String> identifiers){
        operationPipeline.add(new ArrowAnalyzer());
        operationPipeline.add(new DuplicateArrowRemover());
        if (identifiers.contains("SingletonSearch"))
            operationPipeline.add(new SingletonIdentifier(identifiers.contains("SingletonAbuseSearch")));
        if (identifiers.contains("DecoratorSearch"))//z
            operationPipeline.add(new DecoratorIdentifier());
    }

    public void beginAnalysis() {
        ArrayList<ClassContainer> classContainers = invokeASM();

        for (ClassContainerOperator CCO : operationPipeline) {
            CCO.reworkClasses(classContainers);
        }

        invokePUML(classContainers);
    }

    public ArrayList<ClassContainer> invokeASM() {
        return ASMInterface.CompileFiles(ClassesToAnalyze);
    }

    public void invokePUML(ArrayList<ClassContainer> ClassDetails) {
        PUMLInterface.output(ClassDetails);
    }
}

