package markus.wieland.defaultappelements.textinputvalidator.arguments;

public class MatchesPatternValidatorArgument extends TextInputValidatorArgument{

    private final String pattern;

    public MatchesPatternValidatorArgument(String pattern, String errorMessage) {
        super(errorMessage);
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public boolean validate(String string) {
        return string.matches(pattern);
    }
}
