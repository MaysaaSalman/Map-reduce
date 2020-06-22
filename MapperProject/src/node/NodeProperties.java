package node;

import java.util.HashMap;
import java.util.Map;

public class NodeProperties {

    private final static Map<String, Object> properties = new HashMap<>();

    // set default properties
    static {
        properties.put("NODE_NAME", "Andromeda");
        properties.put("THREADS", 2);
        properties.put("MAX_SESSIONS", 10);
        // ...
    }

    public static Object setProprty(String key, Object value) {
        return properties.put(key, value);
    }

    public static Object get(String propertyKey) {
        return properties.get(propertyKey);
    }

    public static Map<String, Object> getNodeProperties() {
        return new HashMap<>(properties);
    }
}