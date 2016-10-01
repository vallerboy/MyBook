package pl.oskarpolak.mybook.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.oskarpolak.mybook.Book;
import pl.oskarpolak.mybook.R;
import pl.oskarpolak.mybook.adapters.MainListAdapter;
import pl.oskarpolak.mybook.database.BookData;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @BindView(R.id.mainListBook)
    ListView listView;

    private BookData data;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);
        data = new BookData(this.getContext());
        listView.setAdapter(new MainListAdapter(this.getContext(), data.getAllBooksToRent()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DynamicBookFragment fragment = new DynamicBookFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", ((Book) parent.getItemAtPosition(position)).getName());
                bundle.putString("author", ((Book) parent.getItemAtPosition(position)).getAuthor());
                bundle.putString("URL", ((Book) parent.getItemAtPosition(position)).getImageURL());
                bundle.putString("desc", ((Book) parent.getItemAtPosition(position)).getDescription());
                bundle.putInt("pages", ((Book) parent.getItemAtPosition(position)).getPages());
                fragment.setArguments(bundle);
                MainFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentsContainer, fragment).commit();
            }
        });
        return v;
    }

}
