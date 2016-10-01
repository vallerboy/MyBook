package pl.oskarpolak.mybook.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.oskarpolak.mybook.R;
import pl.oskarpolak.mybook.Utils;
import pl.oskarpolak.mybook.database.BookData;


public class AddBookFragment extends Fragment {


    @BindView(R.id.addBookAuthor)
    EditText addBookAuthor;

    @BindView(R.id.addBookDesc)
    EditText addBookDesc;

    @BindView(R.id.addButtonURL)
    EditText addBookURL;

    @BindView(R.id.addBookPages)
    EditText addBookPages;

    @BindView(R.id.addBookName)
    EditText addBookName;


    public AddBookFragment() {
        // Required empty public constructor
    }


    BookData bookData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookData = new BookData(this.getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_book, container, false);
        ButterKnife.bind(this, v);


        return v;
    }

    @OnClick(R.id.buttonAddBook)
    public void onClickButtonAddBook(){
        if(bookData.addBook(addBookName.getText().toString(), addBookAuthor.getText().toString(), addBookDesc.getText().toString(), addBookURL.getText().toString(), Integer.parseInt(addBookPages.getText().toString()))){
            Utils.showMessageWithoutAction(this.getContext(), "Dodano książkę!", "Dodawnie książki");
        }
    }




}
