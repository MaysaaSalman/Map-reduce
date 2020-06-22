package node;


public class NodeEngine {
    private String name;

    public NodeEngine(String name) {
        this.name = name;
    }

    public void makeMagic(String magic) {
        System.out.printf("You are inside, wanna me execute some magic for you? here it is: {%s}, from %s.\n", magic, name);
    }

    public void runService(String serviceName) {
        System.out.printf("Let me run this service {%s} for you inside %s.\n", serviceName, name);
        // run the service!
    }
}