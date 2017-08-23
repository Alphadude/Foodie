package io.github.alphadude.jambhangout;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alphadude on 8/23/17.
 */

public class UserAdapter extends ArrayAdapter<Users> {

    private int color;

    //private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    public UserAdapter(Activity context, ArrayList<Users> words, int color){

        super(context,0,words);
        this.color = color;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.user_list, parent, false);
        }

        // Get the object located at this position in the list
        Users currentuser = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.username);
        // set this text on the name TextView
        nameTextView.setText(currentuser.getName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.fullname);
        // set this text on the number TextView
        numberTextView.setText(currentuser.getEmail());


        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        imageView.setImageResource(currentuser.getImage());

        View textcontainer = listItemView.findViewById(R.id.layout);
        int mcolor = ContextCompat.getColor(getContext(),color);
        textcontainer.setBackgroundColor(mcolor);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }


}
