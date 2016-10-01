package pl.oskarpolak.mybook.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.oskarpolak.mybook.Book;
import pl.oskarpolak.mybook.MainActivity;
import pl.oskarpolak.mybook.R;
import pl.oskarpolak.mybook.adapters.MainListAdapter;
import pl.oskarpolak.mybook.database.BookData;
import pl.oskarpolak.mybook.database.UserData;

/**
 * A simple {@link Fragment} subclass.
 */
public class RentedBooksFragment extends Fragment {

    @BindView(R.id.rentedBooksList)
    ListView list;


    public RentedBooksFragment() {
        // Required empty public constructor
    }

    BookData bookData;
    UserData userData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rented_books, container, false);
        ButterKnife.bind(this, v);
        bookData = new BookData(this.getContext());
        userData = new UserData(this.getContext());
        List<Book> booksList = new ArrayList<>();

        for (String s : MainActivity.user.getRentedBooks()) {
            if(!s.equals("") || !s.equals(null))
            booksList.add(bookData.getBookByName(s));
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createDialog(((Book) parent.getItemAtPosition(position)).getName());
            }
        });

        Log.e("debug", booksList.toString());

        list.setAdapter(new MainListAdapter(this.getContext(), booksList));

        return v;
    }

    private void createDialog(final String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Oddaj książkę");
        builder.setMessage("Czy na pewno chcesz oddać książkę?");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.user.getRentedBooks().remove(name);
                bookData.bringBackBook(name);
                userData.rentBook(MainActivity.user.getName(), name);
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
