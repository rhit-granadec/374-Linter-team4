package org.Main;

import java.util.*;

public class Main {

    static Queue<String> ClassesToAnalyze;
    public static void main(String[] args) {

        String outputFilename = "FullSetup";
        List<String> blacklist = new ArrayList<>();
        ClassesToAnalyze = new LinkedList<String>();
        blacklist.add("java.lang.Object"); // required
        blacklist.add("java"); // recommended

        // Self-analysis configuration
        blacklist.add("org.w3c");
        blacklist.add("net.sourceforge.plantuml.klimt");
        blacklist.add("net.sourceforge.plantuml.tim");
        blacklist.add("net.sourceforge.plantuml.file");
        blacklist.add("net.sourceforge.plantuml.preproc");
        blacklist.add("net.sourceforge.plantuml.core");
        blacklist.add("net.sourceforge.plantuml.text");
        blacklist.add("org.objectweb.asm");
        ClassesToAnalyze.add("org.Main.Main");

//        ClassesToAnalyze.add("org.requestedTest.DecryptionInputStream");
//        ClassesToAnalyze.add("org.requestedTest.EncryptionOutputStream");
//        ClassesToAnalyze.add("org.requestedTest.IDecryption");
//        ClassesToAnalyze.add("org.requestedTest.IEncryption");
//        ClassesToAnalyze.add("org.requestedTest.SubstitutionCipher");
//        ClassesToAnalyze.add("org.requestedTest.TextEditorApp");
//        ClassesToAnalyze.add("org.CPS2.Main");
        ClassesToAnalyze.add("org.decorator.DecoyDuck");
        ClassesToAnalyze.add("org.decorator.DuckCall");
        ClassesToAnalyze.add("org.decorator.DuckSimulator");
        ClassesToAnalyze.add("org.decorator.Goose");
        ClassesToAnalyze.add("org.decorator.GooseAdapter");
        ClassesToAnalyze.add("org.decorator.MallardDuck");
        ClassesToAnalyze.add("org.decorator.Quackable");
        ClassesToAnalyze.add("org.decorator.RedheadDuck");
        ClassesToAnalyze.add("org.decorator.RubberDuck");
        ClassesToAnalyze.add("org.decorator.QuackCounter");


        HashSet<String> identifiers = new HashSet<String>();
        identifiers.add("SingletonSearch");
        identifiers.add("SingletonAbuseSearch");
        identifiers.add("DecoratorSearch");

        Director runner = new Director(ClassesToAnalyze,
                outputFilename,
                identifiers,
                blacklist);
    }
}
