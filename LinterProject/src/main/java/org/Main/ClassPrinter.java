package org.Main;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.objectweb.asm.Opcodes.ASM7;

public class ClassPrinter extends ClassVisitor {

    private final ClassNode cn;
    ClassContainer CC = new ClassContainer();
    private final Set<String> variables = new HashSet<String>();
    public ClassPrinter(ClassNode cn) {
        super(ASM7);
        this.cn = cn;
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
        //String parsedSuperName;
        String parsedSuperName = (slashSuperName == -1) ? superName : superName.substring(slashSuperName+1);

        if(slashSuperName == -1) parsedSuperName = superName;
        else parsedSuperName = superName.substring(slashSuperName+1);
        if(!parsedSuperName.equals("Object") ) {
            CC.addAssociation(superName, null, ClassContainer.relationshipType.Extension, "1", "1");
        }

        for(String singleInterface : interfaces) {
//            int slashInterfaceName = singleInterface.lastIndexOf("/");
//            String parsedInterface;
//            if(slashInterfaceName == -1) parsedInterface = singleInterface;
//            else parsedInterface = singleInterface.substring(slashInterfaceName+1);
            CC.addAssociation(singleInterface, null, ClassContainer.relationshipType.Implementation,"1","1");
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

//    public FieldVisitor visitField(int access, String name, String desc,
//                                   String signature, Object value) {
//        CC.addField(name, variableParser(desc));
//        return null;
//    }

    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        String fieldType = variableParser(desc);
        CC.addField(name, fieldType);

//        if(fieldType.endsWith("[]")) {
//            CC.addAssociation(fieldType, name, ClassContainer.relationshipType.Dependency, "1", "0..*");
//        } else {
//            CC.addAssociation(fieldType, name, ClassContainer.relationshipType.Dependency, "1", "1");
//        }

        if(fieldType.endsWith("[]")) {
            CC.addAssociation(fieldType, name, ClassContainer.relationshipType.Dependency,
                    "1", "0..*");
        }

        else if(isCollectionType(fieldType)) {
            CC.addAssociation(fieldType, name, ClassContainer.relationshipType.Dependency,
                    "1", "0..*");
        }

        else {
            CC.addAssociation(fieldType, name, ClassContainer.relationshipType.Dependency,
                    "1", "0..1");
        }

        return null;
    }

    private boolean isCollectionType(String typeName) {
        if (typeName == null) return false;
        return typeName.startsWith("java.util.List")
                || typeName.startsWith("java.util.Set")
                || typeName.startsWith("java.util.Collection")
                || typeName.startsWith("java.util.Map");
    }

    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        return null;
    }

    public void visitEnd() {
        //this saves the methods, and calls the analyzer for the local variables
        cn.methods.forEach(method -> {
            analyzeLocalVars(method);
            String methodName = method.name.trim();

            String methodAccess = "";
            if((method.access & Opcodes.ACC_STATIC) != 0) {
                methodAccess = "{static} ";
            }
            if((method.access & Opcodes.ACC_ABSTRACT) != 0) {
                methodAccess = "{abstract} ";
            }
            methodAccess += switch (method.access) {
                case Opcodes.ACC_PUBLIC -> "+";
                case Opcodes.ACC_PRIVATE -> "-";
                case Opcodes.ACC_PROTECTED -> "#";
                default -> "~";
            };

            String returnVar = descReturnParser(method.desc);
            if(!Character.isUpperCase(returnVar.charAt(0))) {
                CC.addAssociation(returnVar, null, ClassContainer.relationshipType.Dependency);
            }

            ArrayList<String> args = descVariablesParser(method.desc);
            for (String arg : args) {
                if(!Character.isUpperCase(arg.charAt(0))) {
                    CC.addAssociation(arg, null, ClassContainer.relationshipType.Dependency);
                }
            }

            CC.addMethod(methodName, methodAccess, returnVar, args);
        });
    }

    private void analyzeLocalVars(MethodNode method) {
        List<LocalVariableNode> localVariables = method.localVariables;
        if(localVariables != null) {
            for (LocalVariableNode lvn : localVariables) {
                String localVarName = localVarParser(lvn.desc);
                if (localVarName != null) {
                    System.out.println("local var: " + localVarName);
                    CC.addAssociation(localVarName,
                            null,
                            ClassContainer.relationshipType.DependencyWeak);
                }
            }
        }
    }

    private String localVarParser(String varName) {
        char type = varName.charAt(0);
        if(type == '[') {
            String restOfVar = localVarParser(varName.substring(1));
            if (restOfVar != null) return restOfVar + "[]";
        } else if (type == 'L'){
            String newVarName = varName.substring(1, varName.length()-1);
            variables.add(newVarName);
            return newVarName;
        }
        return null;
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
            String newVarName = varName.substring(1, varName.length()-1);
            variables.add(newVarName);
            return newVarName;
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

    public Set<String> getVariables() {
        return variables;
    }
}
