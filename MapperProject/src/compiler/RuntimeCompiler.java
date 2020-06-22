package compiler;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RuntimeCompiler {

    private final JavaCompiler javaCompiler;
    private final Map<String, byte[]> classData;
    private final MapClassLoader mapClassLoader;
    private final ClassDataFileManager classDataFileManager;
    private final List<JavaFileObject> compilationUnits;

    public RuntimeCompiler() {
        this.javaCompiler = ToolProvider.getSystemJavaCompiler();
        if (javaCompiler == null) {
            throw new NullPointerException(
                    "No JavaCompiler found. Make sure to run this with " + "a JDK, and not only with a JRE");
        }
        this.classData = new LinkedHashMap<>();
        this.mapClassLoader = new MapClassLoader();
        this.classDataFileManager = new ClassDataFileManager(javaCompiler.getStandardFileManager(null, null, null));
        this.compilationUnits = new ArrayList<>();
    }


    public void addClass(String className, String code) {
        String javaFileName = className + ".java";
        JavaFileObject javaFileObject = new MemoryJavaSourceFileObject(javaFileName, code);
        compilationUnits.add(javaFileObject);
    }

    public boolean compile() {
        DiagnosticCollector<JavaFileObject> diagnosticsCollector = new DiagnosticCollector<>();
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, classDataFileManager, diagnosticsCollector, null,
                null, compilationUnits);
        boolean success = task.call();
        compilationUnits.clear();
        for (Diagnostic<?> diagnostic : diagnosticsCollector.getDiagnostics()) {
            System.out.println(diagnostic.getKind() + " : " + diagnostic.getMessage(null));
            System.out.println("Line " + diagnostic.getLineNumber() + " of " + diagnostic.getSource());
            System.out.println();
        }
        return success;
    }

    public Class<?> getCompiledClass(String className) {
        return mapClassLoader.findClass(className);
    }

    private static final class MemoryJavaSourceFileObject extends SimpleJavaFileObject {

        private final String code;

        private MemoryJavaSourceFileObject(String fileName, String code) {
            super(URI.create("string:///" + fileName), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return code;
        }
    }


    private class MapClassLoader extends ClassLoader {
        @Override
        public Class<?> findClass(String name) {
            byte[] b = classData.get(name);
            return defineClass(name, b, 0, b.length);
        }
    }

    private class MemoryJavaClassFileObject extends SimpleJavaFileObject {

        private final String className;

        private MemoryJavaClassFileObject(String className) {
            super(URI.create("string:///" + className + ".class"), Kind.CLASS);
            this.className = className;
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return new ClassDataOutputStream(className);
        }
    }


    private class ClassDataFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {

        private ClassDataFileManager(StandardJavaFileManager standardJavaFileManager) {
            super(standardJavaFileManager);
        }

        @Override
        public JavaFileObject getJavaFileForOutput(final Location location, final String className,
                                                   JavaFileObject.Kind kind, FileObject sibling) throws IOException {
            return new MemoryJavaClassFileObject(className);
        }
    }


    private class ClassDataOutputStream extends OutputStream {

        private final String className;
        private final ByteArrayOutputStream baos;

        private ClassDataOutputStream(String className) {
            this.className = className;
            this.baos = new ByteArrayOutputStream();
        }

        @Override
        public void write(int b) throws IOException {
            baos.write(b);
        }

        @Override
        public void close() throws IOException {
            classData.put(className, baos.toByteArray());
            super.close();
        }
    }
}

/** @see: https://stackoverflow.com/questions/935175/convert-string-to-code**/