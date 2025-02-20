package org.Main;

import java.util.*;

public class Main {

    static Queue<String> ClassesToAnalyze = new LinkedList<String>();
    static List<String> blacklist = new ArrayList<>();
    static String outputFilename = "FullSetup";

    public static void main(String[] args) {
        Director runner;
        resetBlacklist();

        //analysis options
        HashSet<String> identifiers = new HashSet<String>();
        identifiers.add("SingletonSearch");
        identifiers.add("SingletonAbuseSearch");
        identifiers.add("DecoratorSearch");

        setupDuckAnalysis();
        runner = new Director(ClassesToAnalyze,
                outputFilename,
                identifiers,
                blacklist);
        runner.analyze();

        setupSelfAnalysis();
        runner = new Director(ClassesToAnalyze,
                outputFilename,
                identifiers,
                blacklist);
        runner.analyze();

        setupCypherAnalysis();
        runner = new Director(ClassesToAnalyze,
                outputFilename,
                identifiers,
                blacklist);
        runner.analyze();

        setupStarbuzzAnalysis();
        runner = new Director(ClassesToAnalyze,
                outputFilename,
                identifiers,
                blacklist);
        runner.analyze();

        setupSingletonSetAnalysis();
        runner = new Director(ClassesToAnalyze,
                outputFilename,
                identifiers,
                blacklist);
        runner.analyze();

        setupLargeAppAnalysis();
        runner = new Director(ClassesToAnalyze,
                outputFilename,
                identifiers,
                blacklist);
        runner.analyze();
    }

    private static void resetBlacklist() {
        blacklist.clear();
        blacklist.add("java.lang.Object"); // required
        blacklist.add("java"); // recommended
    }

    private static void setupSelfAnalysis() {
        // Self-analysis configuration
        resetBlacklist();
        blacklist.add("org.w3c");
        blacklist.add("net.sourceforge.plantuml.klimt");
        blacklist.add("net.sourceforge.plantuml.tim");
        blacklist.add("net.sourceforge.plantuml.file");
        blacklist.add("net.sourceforge.plantuml.preproc");
        blacklist.add("net.sourceforge.plantuml.core");
        blacklist.add("net.sourceforge.plantuml.text");
        blacklist.add("org.objectweb.asm");
        //blacklist.add("net.sourceforge.plantuml");
        ClassesToAnalyze.add("org.Main.Main");
        outputFilename = "self";
    }

    private static void setupDuckAnalysis() {
        // duck class analysis
        resetBlacklist();
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
        outputFilename = "duck";
    }

    private static void setupCypherAnalysis() {
        resetBlacklist();
        ClassesToAnalyze.add("org.requestedTest.DecryptionInputStream");
        ClassesToAnalyze.add("org.requestedTest.EncryptionOutputStream");
        ClassesToAnalyze.add("org.requestedTest.IDecryption");
        ClassesToAnalyze.add("org.requestedTest.IEncryption");
        ClassesToAnalyze.add("org.requestedTest.SubstitutionCipher");
        ClassesToAnalyze.add("org.requestedTest.TextEditorApp");
        outputFilename = "cypher";
    }


    private static void setupStarbuzzAnalysis() {
        resetBlacklist();
        ClassesToAnalyze.add("headfirst.decorator.io.InputTest");
        ClassesToAnalyze.add("headfirst.decorator.io.LowerCaseInputStream");

        ClassesToAnalyze.add("headfirst.decorator.starbuzz.Beverage");
        ClassesToAnalyze.add("headfirst.decorator.starbuzz.CondimentDecorator");
        ClassesToAnalyze.add("headfirst.decorator.starbuzz.DarkRoast");
        ClassesToAnalyze.add("headfirst.decorator.starbuzz.Decaf");
        ClassesToAnalyze.add("headfirst.decorator.starbuzz.Espresso");
        ClassesToAnalyze.add("headfirst.decorator.starbuzz.HouseBlend");
        ClassesToAnalyze.add("headfirst.decorator.starbuzz.Milk");
        ClassesToAnalyze.add("headfirst.decorator.starbuzz.Mocha");
        ClassesToAnalyze.add("headfirst.decorator.starbuzz.Soy");
        ClassesToAnalyze.add("headfirst.decorator.starbuzz.StarbuzzCoffee");
        ClassesToAnalyze.add("headfirst.decorator.starbuzz.Whip");
        outputFilename = "starbuzz";
    }

    private static void setupSingletonSetAnalysis() {
        resetBlacklist();
        ClassesToAnalyze.add("headfirst.singleton.chocolate.ChocolateBoiler");
        ClassesToAnalyze.add("headfirst.singleton.chocolate.ChocolateController");

        ClassesToAnalyze.add("headfirst.singleton.classic.Singleton");

        ClassesToAnalyze.add("headfirst.singleton.dcl.Singleton");
        ClassesToAnalyze.add("headfirst.singleton.dcl.SingletonClient");

//        ClassesToAnalyze.add("headfirst.singleton.OtherSingletons.SingletonOthers");

        ClassesToAnalyze.add("headfirst.singleton.stat.Singleton");
        ClassesToAnalyze.add("headfirst.singleton.stat.SingletonClient");

        ClassesToAnalyze.add("headfirst.singleton.subclass.CoolerSingleton");
        ClassesToAnalyze.add("headfirst.singleton.subclass.HotterSingleton");
        ClassesToAnalyze.add("headfirst.singleton.subclass.Singleton");
        ClassesToAnalyze.add("headfirst.singleton.subclass.SingletonTestDrive");

        ClassesToAnalyze.add("headfirst.singleton.threadsafe.Singleton");
        outputFilename = "singleton";
    }

    private static void setupLargeAppAnalysis() {
        resetBlacklist();
        blacklist.add("org.json.simple");


        ClassesToAnalyze.add("otherselection.Main");

        ClassesToAnalyze.add("otherselection.Application.AppCommunicator");

        ClassesToAnalyze.add("otherselection.BusinessLogic.AdvancedControllerContact");
        ClassesToAnalyze.add("otherselection.BusinessLogic.ControllerContactBehavior");
        ClassesToAnalyze.add("otherselection.BusinessLogic.OrderCreator");
        ClassesToAnalyze.add("otherselection.BusinessLogic.OrderFactory");
        ClassesToAnalyze.add("otherselection.BusinessLogic.OrderObserver");
        ClassesToAnalyze.add("otherselection.BusinessLogic.ProgrammableControllerContact");
        ClassesToAnalyze.add("otherselection.BusinessLogic.SimpleControllerContact");

        ClassesToAnalyze.add("otherselection.DataSource.BaseOrder");
        ClassesToAnalyze.add("otherselection.DataSource.Cream");
        ClassesToAnalyze.add("otherselection.DataSource.DarkRoast");
        ClassesToAnalyze.add("otherselection.DataSource.DatabaseHandler");
        ClassesToAnalyze.add("otherselection.DataSource.Milk");
        ClassesToAnalyze.add("otherselection.DataSource.Nutmeg");
        ClassesToAnalyze.add("otherselection.DataSource.NutraSweet");
        ClassesToAnalyze.add("otherselection.DataSource.OrderComponent");
        ClassesToAnalyze.add("otherselection.DataSource.OrderDecorator");
        ClassesToAnalyze.add("otherselection.DataSource.PumpkinSpice");
        ClassesToAnalyze.add("otherselection.DataSource.Sugar");

        ClassesToAnalyze.add("otherselection.JUnitTests.Milestone3Test");
        ClassesToAnalyze.add("otherselection.JUnitTests.MyTestCases");

        ClassesToAnalyze.add("otherselection.Testing.TestController");
        outputFilename = "largeApp";
    }
}
