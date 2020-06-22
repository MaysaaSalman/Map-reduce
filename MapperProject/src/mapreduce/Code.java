package mapreduce;


import injection.Import;

import java.io.Serializable;

public class Code implements Serializable {
    private static final long serialVersionUID = 38123456237570L;


    private Import imports;
    private String code;



    public Import getImports() {
        return imports;
    }

    public void setImports(Import imports) {
        this.imports = imports;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Code(Import imports, String code) {
        this.imports = imports;
        this.code = code;
    }
}
