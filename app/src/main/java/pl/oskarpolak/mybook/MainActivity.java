package pl.oskarpolak.mybook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.oskarpolak.mybook.database.UserData;
import pl.oskarpolak.mybook.fragments.AddBookFragment;
import pl.oskarpolak.mybook.fragments.DynamicBookFragment;
import pl.oskarpolak.mybook.fragments.MainFragment;
import pl.oskarpolak.mybook.fragments.RentedBooksFragment;
import pl.oskarpolak.mybook.fragments.StatusFragment;

public class MainActivity extends AppCompatActivity {



    public static User user;
    private  UserData userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentsContainer, new MainFragment()).commit();

        userData = new UserData(this);
        user = userData.getUserByName(getIntent().getExtras().getString("nick"));


    }

    @OnClick(R.id.buttonMain)
    public void onClickButtonMain(){
         getSupportFragmentManager().beginTransaction().replace(R.id.fragmentsContainer, new MainFragment()).commit();
    }

    @OnClick(R.id.buttonMyBooks)
    public void onClickButtonMyBooks(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentsContainer, new RentedBooksFragment()).commit();
    }

    @OnClick(R.id.buttonAddBook)
    public void onClickButtonAddBook(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentsContainer, new AddBookFragment()).commit();
    }

    @OnClick(R.id.buttonStatus)
    public void onClickButtonStatus(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentsContainer, new StatusFragment()).commit();
    }

    @OnClick(R.id.buttonLogout)
    public void onClickButtonLogout(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }



}
