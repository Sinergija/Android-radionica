package ba.edu.sinergija.vestisinergija;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Android on 19.3.2016.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    private List<News> newsList;
    public NewsAdapter(Context context, List<News> newsList ) {
        super(context, 0, newsList);
        this.newsList = newsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_news, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.naslov);
        textView.setText(getItem(position).naslov);


        return convertView;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }
}
