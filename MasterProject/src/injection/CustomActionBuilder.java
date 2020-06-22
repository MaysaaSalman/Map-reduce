package injection;

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

    // You can only use static members from super class
    public CustomActionBuilder extend(String superClass) {
        customAction.setSuperClass(superClass);
        return this;
    }

    // Add any needed import statements
    public CustomActionBuilder importing(String libraryPath) {
        customAction.getImportStatements().addLibrary(libraryPath);
        return this;
    }

    // Set the parameter that you pass and use in your custom code
    public CustomActionBuilder withParameter(Parameter parameter) {
        customAction.setParameter(parameter);
        return this;
    }

    // Build the action in order to execute it
    public CustomAction build() {
        return customAction;
    }
}