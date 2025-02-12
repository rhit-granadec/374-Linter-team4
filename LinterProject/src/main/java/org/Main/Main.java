package org.Main;

import java.util.ArrayList;

public class Main {

    static ArrayList<String> ClassesToAnalyze;
    public static void main(String[] args) {
        ClassesToAnalyze = new ArrayList<>();
        ClassesToAnalyze.add("org.requestedTest.DecryptionInputStream");
        ClassesToAnalyze.add("org.requestedTest.EncryptionOutputStream");
        ClassesToAnalyze.add("org.requestedTest.IDecryption");
        ClassesToAnalyze.add("org.requestedTest.IEncryption");
        ClassesToAnalyze.add("org.requestedTest.SubstitutionCipher");
        ClassesToAnalyze.add("org.requestedTest.TextEditorApp");
        Director runner = new Director(ClassesToAnalyze);
    }
}
