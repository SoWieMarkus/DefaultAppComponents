package markus.wieland.defaultappelements.textinputvalidator;

public class ValidatorResult {
    private final String errorMessage;
    private final boolean isValid;

    public ValidatorResult(String errorMessage, boolean isValid) {
        this.errorMessage = errorMessage;
        this.isValid = isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isValid() {
        return isValid;
    }
}
