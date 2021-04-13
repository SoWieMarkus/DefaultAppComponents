package markus.wieland.defaultappelements.textinputvalidator.arguments;

public class MaxLengthValidatorArgument extends TextInputValidatorArgument{

    private final int maxAmountOfChars;

    public MaxLengthValidatorArgument(int maxAmountOfChars,String errorMessage) {
        super(errorMessage);
        this.maxAmountOfChars = maxAmountOfChars;
    }

    public int getMaxAmountOfChars() {
        return maxAmountOfChars;
    }

    @Override
    public boolean validate(String string) {
        return string.length() <= maxAmountOfChars;
    }
}
