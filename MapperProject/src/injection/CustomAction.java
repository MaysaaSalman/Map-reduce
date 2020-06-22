package injection;

import compiler.MethodInvocationUtils;
import compiler.RuntimeCompiler;
import util.Utils;

import java.util.stream.Collectors;


public class CustomAction {
    private String name;
    private String code;
    private String superClass;
    private Import importStatements;
    private Parameter parameter;

    private RuntimeCompiler compiler = new RuntimeCompiler();

    private String className;
    private static final String CLASS_NAME_PREFIX = "CustomCode";
    private static final String METHOD_NAME = "execute";

    public CustomAction() {
        importStatements = new Import();
        setClassName();
    }

    public void execute() {
        try {
            compiler.addClass(className, buildClass());
            compiler.compile();
            invokeMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildClass() {
        StringBuilder sb = new StringBuilder(getImportsAsString());
        sb.append("\npublic class ");
        sb.append(className);
        sb.append(" ");
        sb.append(getSuperClassAsString());
        sb.append(String.format(" {\n\tpublic static void %s(Parameter parameters){\t", METHOD_NAME));
        sb.append(getCode());
        sb.append("\t}\n}");
        return sb.toString();
    }

    private String getSuperClassAsString() {
        if (getSuperClass() == null) {
            return "extends Object"; // extend any default class you want if needed!
        }
        return "extends " + getSuperClass();
    }

    private String getImportsAsString() {
        return getImportStatements().getLibraries().stream()
                .map(lib -> String.format("import %s;", lib.getLibraryPath()))
                .collect(Collectors.joining("\n"));
    }

    private void invokeMethod() {
        MethodInvocationUtils.invokeStaticMethod(compiler.getCompiledClass(className), METHOD_NAME, parameter);
    }

    public String getCode() {
        if (code == null) {
            code = "";
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String getSuperClass() {
        return superClass;
    }


    public String getName() {
        if (name == null) {
            name = "CustomAction Code";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Import getImportStatements() {
        return importStatements;
    }

    private void setClassName() {
        className = String.format("%s_%s", CLASS_NAME_PREFIX, Utils.getSerial());
    }
}