package injection;

import java.util.ArrayList;

public class CustomActionBuilder {
    private CustomAction customAction;

    // Start a new action
    public CustomActionBuilder newAction(String name) {
        customAction = new CustomAction();
        customAction.setName(name);
        return this;
    }

    // Your code will be wrapped by a static method
    public CustomActionBuilder withCode(String code) {
        customAction.setCode(code);
        return this;
    }

    // Build the action in order to execute it
    public CustomAction build() {
        return customAction;
    }
}