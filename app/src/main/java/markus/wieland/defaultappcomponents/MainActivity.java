package markus.wieland.defaultappcomponents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import markus.wieland.defaultappelements.textinputvalidator.TextInputValidator;
import markus.wieland.defaultappelements.textinputvalidator.arguments.MaxLengthValidatorArgument;
import markus.wieland.defaultappelements.textinputvalidator.arguments.MinLengthValidatorArgument;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}