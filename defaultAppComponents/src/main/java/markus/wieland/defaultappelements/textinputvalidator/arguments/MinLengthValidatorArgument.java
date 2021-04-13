package markus.wieland.defaultappelements.textinputvalidator.arguments;

public class MinLengthValidatorArgument extends TextInputValidatorArgument {

    private final int minAmountOfChars;

    public MinLengthValidatorArgument(int minAmountOfChars, String errorMessage) {
        super(errorMessage);
        this.minAmountOfChars = minAmountOfChars;
    }

    public int getMinAmountOfChars() {
        return minAmountOfChars;
    }

    @Override
    public boolean validate(String string) {
        return string.length() >= minAmountOfChars;
    }
}
