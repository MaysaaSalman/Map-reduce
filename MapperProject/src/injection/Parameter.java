package injection;


public class Parameter {
    private NodeEngine engine;

    public Parameter(NodeEngine engine) {
        this.engine = engine;
    }

    public NodeEngine getEngine() {
        return engine;
    }

    public void setEngine(NodeEngine engine) {
        this.engine = engine;
    }
}
