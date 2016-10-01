package pl.oskarpolak.mybook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.oskarpolak.mybook.database.UserData;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginEdit)
    EditText loginEdit;

    @BindView(R.id.passwordEdit)
    EditText passwordEdit;

    @BindView(R.id.rememberPassword)
    CheckBox rememberPassword;


    private UserData userData;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        preferences = getSharedPreferences("loginMode", Context.MODE_PRIVATE);
        loginEdit.setText(preferences.getString("login", "Twój login"));
        passwordEdit.setText(preferences.getString("password", "Twoje hasło"));
        rememberPassword.setChecked(preferences.getBoolean("isChecked", false));

        userData = new UserData(this);
    }

    private boolean areLoginFieldsEmpty() {
        return loginEdit.getText().toString().isEmpty() || passwordEdit.getText().toString().isEmpty();
    }

    private void checkRememberPassword() {
        SharedPreferences.Editor editor = preferences.edit();

        if (rememberPassword.isChecked()) {
            editor.putString("login", loginEdit.getText().toString());
            editor.putString("password", passwordEdit.getText().toString());
            editor.putBoolean("isChecked", true);
            editor.commit();
        } else {
            editor.clear();
            editor.commit();
        }
    }

    @OnClick(R.id.loginButton)
    public void onClickLogin() {
        if (areLoginFieldsEmpty()) {
            Utils.showMessageWithoutAction(this, "Wypełnij wszystkie pola", "Logowanie");
        } else {
            if (userData.checkLoginAndPassword(loginEdit.getText().toString(), passwordEdit.getText().toString())) {

                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("nick", loginEdit.getText().toString());

                startActivity(i);
                finish();

            } else {
                Utils.showMessageWithoutAction(this, "Niepoprawne dane", "Logownaie");
            }
            checkRememberPassword();
        }
    }

    @OnClick(R.id.registerButton)
    public void onClickRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

}
