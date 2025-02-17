package org.Main;

import org.objectweb.asm.*;

import java.util.ArrayList;

import static org.objectweb.asm.Opcodes.ASM7;

public class ClassPrinter extends ClassVisitor {

    ClassContainer CC = new ClassContainer();
    public ClassPrinter() {
        super(ASM7);
    }

    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {

        if(access == 1537) CC.makeInterface();
        else if (access == 1057) CC.makeAbstract();

//        int slashIndexName = name.lastIndexOf("/");
//        String parsedName;
//        if(slashIndexName == -1) parsedName = name;
//        else parsedName = name.substring(slashIndexName+1);
        CC.setName(name);

        int slashSuperName = superName.lastIndexOf("/");
        String parsedSuperName;
        if(slashSuperName == -1) parsedSuperName = superName;
        else parsedSuperName = superName.substring(slashSuperName+1);
        if(!parsedSuperName.equals("Object") ) {
            CC.addAssociation(superName, null, ClassContainer.relationshipType.Extension);
        }

        for(String singleInterface : interfaces) {
//            int slashInterfaceName = singleInterface.lastIndexOf("/");
//            String parsedInterface;
//            if(slashInterfaceName == -1) parsedInterface = singleInterface;
//            else parsedInterface = singleInterface.substring(slashInterfaceName+1);
            CC.addAssociation(singleInterface, null, ClassContainer.relationshipType.Implementation);
        }
    }

    public void visitSource(String source, String debug) {
        //has filename information
    }

    public void visitOuterClass(String owner, String name, String desc) {
        //never reached in main test code. unsure
    }

    public AnnotationVisitor visitAnnotation(String desc,
                                             boolean visible) {
        return null;
    }

    public void visitAttribute(Attribute attr) {
        //never reached. unsure.
    }

    public void visitInnerClass(String name, String outerName,
                                String innerName, int access) {
//        System.out.println(name);
//        System.out.println(outerName);
//        System.out.println(innerName);
//        System.out.println(access);
        //looks for specific inner classes. may need to leverage in some areas.
    }

    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        CC.addField(name, variableParser(desc));
        return null;
    }

    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {

//        ClassReader cr = new ClassReader(classToAnalyze);
//        cr.accept(cp, 0);
//        ClassContainer cc = cp.getClassContainer();
        if(name.charAt(0) == '<') return null;
        CC.addMethod(name, descReturnParser(desc), descVariablesParser(desc));
        return null;
    }

    public void visitEnd() {
    }

    private String variableParser(String varName) {
        char type = varName.charAt(0);
        if(type == '[') {
            return variableParser(varName.substring(1))+"[]";
        } else if(type == 'V') {
            return "Void";
        } else if (type == 'Z') {
            return "Boolean";
        } else if (type == 'C') {
            return "Char";
        } else if (type == 'B') {
            return "Byte";
        } else if (type == 'S') {
            return "Short";
        } else if (type == 'I') {
            return "Int";
        } else if (type == 'F') {
            return "Float";
        } else if (type == 'J') {
            return "Long";
        } else if (type == 'D') {
            return "Double";
        } else if (type == 'L'){
//            String[] splitList = varName.split("/");
//            String name = splitList[splitList.length-1];
            return varName.substring(1, varName.length()-1);
        } else {
            return varName;
        }
    }

    private ArrayList<String> descVariablesParser(String desc) {
        int lastPosition = desc.lastIndexOf(")");
        String inputSubstring = desc.substring(1, lastPosition);
        ArrayList<String> returnList = new ArrayList<String>();

        int pointer = 0;
        while(!inputSubstring.isEmpty()) {
            String subSubstring;
            while(true) {
                if(inputSubstring.charAt(pointer) == '[') {
                    pointer++;
                    continue;
                } else if (inputSubstring.charAt(pointer) == 'L'){
                    pointer = inputSubstring.indexOf(';');
                    break;
                } else break;
            }
            returnList.add(variableParser(inputSubstring.substring(0, pointer+1)));
            inputSubstring = inputSubstring.substring(pointer+1);
            pointer = 0;
        }
        return returnList;
    }

    private String descReturnParser(String desc) {
        int lastPosition = desc.lastIndexOf(")");
        String returnVariable = desc.substring(lastPosition+1);
        return variableParser(returnVariable);
    }

    public ClassContainer getClassContainer() {
        return CC;
    }
}
