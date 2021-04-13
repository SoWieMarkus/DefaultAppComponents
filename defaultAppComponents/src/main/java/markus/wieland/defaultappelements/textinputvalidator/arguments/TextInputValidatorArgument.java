package markus.wieland.defaultappelements.textinputvalidator.arguments;

import androidx.annotation.NonNull;

public abstract class TextInputValidatorArgument {

    protected final String errorMessage;

    public TextInputValidatorArgument(@NonNull String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public abstract boolean validate(String string);

}

