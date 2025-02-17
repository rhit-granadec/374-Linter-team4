package org.Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static Queue<String> ClassesToAnalyze;
    public static void main(String[] args) {

        String outputFilename = "FullSetup";

        ClassesToAnalyze = new LinkedList<String>();
//        ClassesToAnalyze.add("org.requestedTest.DecryptionInputStream");
//        ClassesToAnalyze.add("org.requestedTest.EncryptionOutputStream");
//        ClassesToAnalyze.add("org.requestedTest.IDecryption");
//        ClassesToAnalyze.add("org.requestedTest.IEncryption");
//        ClassesToAnalyze.add("org.requestedTest.SubstitutionCipher");
//        ClassesToAnalyze.add("org.requestedTest.TextEditorApp");
        ClassesToAnalyze.add("org.CPS2.Main");

        HashSet<String> identifiers = new HashSet<String>();
        identifiers.add("SingletonSearch");
        identifiers.add("SingletonAbuseSearch");

        Director runner = new Director(ClassesToAnalyze,
                outputFilename,
                identifiers);
    }
}
