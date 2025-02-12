package org.analyze;

import org.example.ClassPrinter;

public class James implements person {
    int numbers;
    String realName;

    ClassPrinter pront;

    public James() {
        numbers = 2;
        realName = "Jarnathin";
        TestFunc();
        TestFunc2(null);
    }

    public void TestFunc() {
        numbers++;
    }

    public void TestFunc2(Fred restOfName) {

    }
}
