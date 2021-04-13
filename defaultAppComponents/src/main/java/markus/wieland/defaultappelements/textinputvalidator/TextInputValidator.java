package markus.wieland.defaultappelements.textinputvalidator;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.textinputvalidator.arguments.TextInputValidatorArgument;

public class TextInputValidator {

    private final List<TextInputValidatorArgument> arguments;

    public TextInputValidator() {
        this.arguments = new ArrayList<>();
    }

    public TextInputValidator add(TextInputValidatorArgument textInputValidatorArgument) {
        arguments.add(textInputValidatorArgument);
        return this;
    }

    public ValidatorResult validate(@NonNull String string) {
        for (TextInputValidatorArgument argument : arguments) {
            if (!argument.validate(string)) return new ValidatorResult(argument.getErrorMessage(), false);
        }
        return new ValidatorResult(null, true);
    }


}
