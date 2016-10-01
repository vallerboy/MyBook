package pl.oskarpolak.mybook.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.oskarpolak.mybook.MainActivity;
import pl.oskarpolak.mybook.R;
import pl.oskarpolak.mybook.Utils;
import pl.oskarpolak.mybook.database.BookData;
import pl.oskarpolak.mybook.database.UserData;


public class DynamicBookFragment extends Fragment {


    @BindView(R.id.dynamicAuthor)
    TextView author;

    @BindView(R.id.dynamicPages)
    TextView pages;

    @BindView(R.id.dynamicDesc)
    TextView desc;

    @BindView(R.id.dynamicImage)
    ImageView image;

    @BindView(R.id.dynamicTitle)
    TextView title;

    @BindView(R.id.buttonRent)
    Button rent;

    private BookData dataBook;
    private UserData dataUser;

    public DynamicBookFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBook = new BookData(this.getContext());
        dataUser = new UserData(this.getContext());

    }

    @OnClick(R.id.buttonRent)
    public void onRent(){
        dataBook.rentBook(getArguments().getString("name"));
        MainActivity.user.getRentedBooks().add(getArguments().getString("name"));
        dataUser.rentBook(MainActivity.user.getName(), MainActivity.user.ArrayAtNames());
        Utils.showMessageWithoutAction(getContext(), "Wypożyczono książkę!", "Książka");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dynamic_book, container, false);
        ButterKnife.bind(this, v);

        title.setText(getArguments().getString("name"));
        pages.setText("" + getArguments().getInt("pages"));
        desc.setText(getArguments().getString("desc"));
        author.setText(getArguments().getString("author"));

        Picasso.with(this.getContext()).load(getArguments().getString("URL")).into(image);

        return v;
    }

}
