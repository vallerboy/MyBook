package pl.oskarpolak.mybook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.oskarpolak.mybook.database.UserData;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.registerLogin)
    EditText registerLogin;

    @BindView(R.id.registerPassword)
    EditText registerPassword;

    @BindView(R.id.registerPasswordAgain)
    EditText registerPasswordAgain;

    @BindView(R.id.registerPhone)
    EditText registerPhone;

    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        userData = new UserData(this);
    }

    private boolean areLoginFieldsEmpty(){
        return registerLogin.getText().toString().isEmpty() || registerPassword.getText().toString().isEmpty() || registerPasswordAgain.getText().toString().isEmpty() || registerPhone.getText().toString().isEmpty();
    }

    private void createSusscesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rejestracja");
        builder.setMessage("Pomyślnie zarejestrowano!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //startActivity(); Uruchamianie głównego activity
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @OnClick(R.id.buttonRegister)
    public void onClickRegister(){
         if(areLoginFieldsEmpty()){
             Utils.showMessageWithoutAction(this, "Wypełnij wszystkie pola", "Rejestracja");
         }else {
            if(userData.registerUser(registerLogin.getText().toString(), registerPassword.getText().toString(), registerPhone.getText().toString())){
                createSusscesDialog();
            }
         }
        Log.e("Rejestracja", "Kliknięto przycisk");
    }



}
