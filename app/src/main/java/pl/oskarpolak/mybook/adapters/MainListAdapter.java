package pl.oskarpolak.mybook.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import pl.oskarpolak.mybook.Book;
import pl.oskarpolak.mybook.R;

/**
 * Created by OskarPraca on 2016-10-01.
 */

public class MainListAdapter extends BaseAdapter {

    private Context context;
    private List<Book> booksData;

    private LayoutInflater  inflater;

    public MainListAdapter(Context con, HashMap<String, Book> data){
        context = con;
        booksData = new ArrayList(data.values());

        for(Book b : booksData){
            Log.e("Data", b.getAuthor());
        }

        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public MainListAdapter(Context con, List<Book> data){
        context = con;
        booksData = data;


        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return booksData.size();
    }

    @Override
    public Object getItem(int position) {
        return booksData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

         if(view != null) {
           holder = (ViewHolder) view.getTag();
         }else{
             view = inflater.inflate(R.layout.book_row_layout, parent, false);
             holder = new ViewHolder(view);
             view.setTag(holder);
         }

        holder.description.setText(booksData.get(position).getDescription());
        holder.name.setText(booksData.get(position).getName());
        Log.e("IMAGE", booksData.get(position).getImageURL());
        Picasso.with(view.getContext()).load(booksData.get(position).getImageURL()).into(holder.image);



        return view;
    }




    static class ViewHolder {

        @BindView(R.id.mainImage)
        ImageView image;

        @BindView(R.id.mainDesc)
        TextView description;

        @BindView(R.id.mainName)
        TextView name;




          public ViewHolder(View v){
              ButterKnife.bind(this, v);
          }
    }
}
