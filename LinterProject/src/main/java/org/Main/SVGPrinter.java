package org.Main;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SVGPrinter {
    private String outputFile;
    public SVGPrinter(String outputFile) {
        this.outputFile = outputFile;
    }
    public void output(ArrayList<ClassContainer> sourceClasses) {
        String source = processClassContainers(sourceClasses);
        try {

            SourceStringReader reader = new SourceStringReader(source);
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            // Write the first image to "os"
            String desc = reader.generateImage(os, new FileFormatOption(FileFormat.SVG));
            os.close();

            // The XML is stored into svg
            final String svg = new String(os.toByteArray(), Charset.forName("UTF-8"));
            FileWriter fw=new FileWriter(outputFile);
            fw.write(svg);
            fw.close();
        } catch(Exception e) {
            System.out.println("ERROR:");
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private String processClassContainers(ArrayList<ClassContainer> input) {
        StringBuilder source = new StringBuilder("@startuml\n\n");
        if(input != null) {
            for (ClassContainer CC : input
            ) {
                source.append(processClassContainer(CC));
            }
        }
        source.append("@enduml");
        return source.toString();
    }

    private String processClassContainer(ClassContainer input) {
        StringBuilder source = new StringBuilder();
        String className = input.getName().replace('/', '.');
        String trimCName = className.substring(className.lastIndexOf('.') + 1);
        if(input.isAbstract) source.append("abstract ");
        if(input.isInterface) source.append("interface ");
        else source.append("class ");
        source.append(className);
        if(input.isSingleton()) source.append(" <<Singleton>>#F88;line:red");
//z
        if(input.isDecorator())
            source.append(" <<Decorator>> #88F;line:blue");
        source.append(" {\n");

        for(ClassContainer.FieldContainer field : input.getFields()) {
            source.append(" ");
            source.append(field.name.replace('/', '.'));
            source.append(": ");
            source.append(field.returnValue);
            source.append("\n");
        }

        for(ClassContainer.MethodContainer method : input.getMethods()){
            source.append(" ");
            source.append(method.access);
            boolean isConstructor = method.name.contains("<init>");
            source.append(method.name.replace('/', '.').replace("<init>", trimCName));
            source.append("(");
            if(!method.inputs.isEmpty()) {
                for (String methodInput : method.inputs) {
                    source.append(methodInput);
                    source.append(", ");
                }
                source.delete(source.length() - 2, source.length());
            }
            source.append(")");
            if(!isConstructor)
                source.append(": ").append(method.returnValue);
            source.append("\n");
        }

        source.append("}\n");

        for(ClassContainer.AssociationContainer association : input.getAssociations()) {
            source.append(className);
            String arrow = getArrow(association);
            source.append(arrow);
            String assocName = association.ClassName.replace('/', '.');
            while(assocName.charAt(assocName.length()-1) == ']') {
                assocName = assocName.substring(0, assocName.length()-2);
            }
            source.append(assocName);
            if(association.AssociationName != null) {
                source.append(" : ");
                source.append(association.AssociationName.replace('/', '.'));
            }
            source.append("\n");
        }

        source.append("\n");

        return source.toString();
    }

    private static String getArrow(ClassContainer.AssociationContainer association) {
        String arrow;
        if (association.relationshipType != null) {
            switch (association.relationshipType ) {
                case Composition -> arrow = "--*";
                case Aggregation -> arrow = "--o";
                case Dependency -> arrow = "-->";
                case DependencyWeak -> arrow = "..>";
                case Extension -> arrow = "--|>";
                case Implementation -> arrow = "..|>";
                default -> arrow = "-->";
            }
        } else arrow = "-->";
        return arrow;
    }
}
