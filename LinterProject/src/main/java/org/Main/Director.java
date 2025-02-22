package org.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Pattern;

public class Director {
    static String OutputPath = "outputs/";
    private final SVGPrinter PUMLInterface;
    private final ASMHandler ASMInterface = new ASMHandler();
    private final Queue<String> ClassesToAnalyze;
    private final ArrayList<ClassContainerOperator> operationPipeline = new ArrayList<ClassContainerOperator>();
    private final Pattern blacklistPattern;

    public Director(Queue<String> ClassesToAnalyze,
                    String fileName,
                    Set<String> identifiers,
                    List<String> blacklist,
                    int overUseThreshold) {
        StringBuilder patternBuilder = new StringBuilder("^(");
        for(String blName : blacklist) {
            patternBuilder.append(blName.replace(".", "(/|\\.)"));
            patternBuilder.append("|");
        }
        patternBuilder.deleteCharAt(patternBuilder.length() - 1);
        patternBuilder.append(")");
        blacklistPattern = Pattern.compile(patternBuilder.toString());
        String fullOutputFile = OutputPath + fileName + ".svg";
        PUMLInterface = new SVGPrinter(fullOutputFile, overUseThreshold);

        this.ClassesToAnalyze = ClassesToAnalyze;

        composeAnalysisPipeline(identifiers);
    }

    public void composeAnalysisPipeline(Set<String> identifiers){
        operationPipeline.add(new ArrowAnalyzer());
        operationPipeline.add(new BlacklistArrowRemover(blacklistPattern));
        operationPipeline.add(new DuplicateArrowRemover());
        if (identifiers.contains("SingletonSearch"))
            operationPipeline.add(new SingletonIdentifier(identifiers.contains("SingletonAbuseSearch")));
        if (identifiers.contains("DecoratorSearch"))//z
            operationPipeline.add(new DecoratorIdentifier());
    }

    public void analyze() {
        ArrayList<ClassContainer> classContainers = invokeASM();

        for (ClassContainerOperator CCO : operationPipeline) {
            CCO.reworkClasses(classContainers);
        }

        invokePUML(classContainers);
    }

    public ArrayList<ClassContainer> invokeASM() {
        return ASMInterface.CompileFiles(ClassesToAnalyze, blacklistPattern);
    }

    public void invokePUML(ArrayList<ClassContainer> ClassDetails) {
        PUMLInterface.output(ClassDetails);
    }
}

