package org.Main;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ASMHandler {
    HashSet<String> analyzedClasses;
    Pattern blacklistPattern;

    public ArrayList<ClassContainer> CompileFiles(Queue<String> classNames, Pattern blacklistPattern) {
        this.blacklistPattern = blacklistPattern;
        analyzedClasses = new HashSet<>();
        ArrayList<ClassContainer>  classContainers= new ArrayList<ClassContainer>();
        try {
            while(!classNames.isEmpty()) {
                String classToAnalyze = classNames.poll();
                Matcher m = blacklistPattern.matcher(classToAnalyze);
                if(m.find()) {
                    continue;
                }
                if(analyzedClasses.contains(classToAnalyze)) {
                    continue;
                } else if(analyzedClasses.contains(classToAnalyze.replace('/', '.'))) {
                    continue;
                }
                try{
                    ClassNode classNode = new ClassNode();
                    ClassReader cr = new ClassReader(classToAnalyze);
                    cr.accept(classNode, ClassReader.EXPAND_FRAMES);

                    ClassPrinter cp = new ClassPrinter(classNode);
                    cr.accept(cp, 0);
                    ClassContainer cc = cp.getClassContainer();

//                    inspectMethods(classNode, cc);

                    classContainers.add(cc);
                    for(ClassContainer.AssociationContainer assoc : cc.getAssociations()) {
                        String cName = assoc.ClassName;
                        while(cName.charAt(cName.length()-1) == ']') {
                            cName = cName.substring(0, cName.length()-2);
                        }
                        if(!analyzedClasses.contains(cName)) {
//                            System.out.println(cName);
                            classNames.offer(cName);
//                            analyzedClasses.add(cName);
//                            System.out.println("Added class " + cName);
                        }
                    }
                    for(String variable : cp.getVariables()) {
                        if(!analyzedClasses.contains(variable)) {
//                            System.out.println(cName);
                            classNames.offer(variable);
//                            analyzedClasses.add(variable);
//                            System.out.println("Added class " + cName);
                        }
                    }
                    analyzedClasses.add(classToAnalyze);
                } catch (Exception e) {
                    System.out.println("ClassReader FILE ERROR:");
                    System.out.println("Can't find " + classToAnalyze);
                    System.out.println(e);
                    return null;
                }
            }
            return classContainers;
        } catch(Exception e) {
            System.out.println("INPUT FILE ERROR:");
            System.out.println(e);
            return null;
        }
    }

    private static void inspectMethods(ClassNode classNode, ClassContainer cc) {
        ArrayList<MethodNode> methods = (ArrayList<MethodNode>) classNode.methods;
        for (MethodNode method : methods) {
            System.out.println("	Method: " + method.name);
            System.out
                    .println("	Internal JVM method signature: " + method.desc);

            System.out.println("	Return type: "
                    + Type.getReturnType(method.desc).getClassName());

            System.out.println("	Args: ");
            for (Type argType : Type.getArgumentTypes(method.desc)) {
                System.out.println("		" + argType.getClassName());
                // FIXME: what is the argument's *variable* name?
            }

            String access = switch (method.access) {
                case Opcodes.ACC_PUBLIC -> "public";
                case Opcodes.ACC_PRIVATE -> "private";
                case Opcodes.ACC_PROTECTED -> "protected";
                default -> "package private";
            };
            System.out.println("	access? "
                    + access);
            System.out.println("	static? "
                    + ((method.access & Opcodes.ACC_STATIC) != 0));
            System.out.println("	final? "
                    + ((method.access & Opcodes.ACC_FINAL) != 0));
            System.out.println("    abstract? "
                    + ((method.access & Opcodes.ACC_ABSTRACT) != 0));

            System.out.println();

            // Print the method's instructions
            printInstructions(method, cc);
        }
    }

    private static void printInstructions(MethodNode methodNode, ClassContainer cc) {
        InsnList instructions = methodNode.instructions;
        for (int i = 0; i < instructions.size(); i++) {

            // We don't know immediately what kind of instruction we have.
            AbstractInsnNode insn = instructions.get(i);

            // FIXME: Is instanceof the best way to deal with the instruction's type?
            if (insn instanceof MethodInsnNode) {
                // A method call of some sort; what other useful fields does this object have?
                MethodInsnNode methodCall = (MethodInsnNode) insn;
                System.out.println("		Call method: " + methodCall.owner + " "
                        + methodCall.name);
            } else if (insn instanceof VarInsnNode) {
                // Some some kind of variable *LOAD or *STORE operation.
                VarInsnNode varInsn = (VarInsnNode) insn;
                int opCode = varInsn.getOpcode();
                // See VarInsnNode.setOpcode for the list of possible values of
                // opCode. These are from a variable-related subset of Java
                // opcodes.
            }
            // There are others...
            // This list of direct known subclasses may be useful:
            // http://asm.ow2.org/asm50/javadoc/user/org/objectweb/asm/tree/AbstractInsnNode.html

            // TODO: how do I write a lint check to tell if this method has a bad name?
        }
    }
}
