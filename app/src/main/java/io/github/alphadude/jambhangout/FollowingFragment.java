package io.github.alphadude.jambhangout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingFragment extends Fragment {


    public FollowingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_scroll, container, false);
        ArrayList<Users> words = new ArrayList<Users>();

        words.add(new Users("Ayaosi Godfrey", "Alphadude",R.drawable.firebase));
        words.add(new Users("Micheal Morah", "BLUE",R.drawable.firebase));
        words.add(new Users("Jon Snow", "BROWN",R.drawable.firebase));
        words.add(new Users("Peace Pink", "Perrie",R.drawable.firebase));
        words.add(new Users("Jonah Jones", "GREY",R.drawable.firebase));
        words.add(new Users("Jesse Jay", "RED",R.drawable.firebase));
        words.add(new Users("Chidi ", "WHITE",R.drawable.firebase));




        UserAdapter itemsAdapter = new UserAdapter(getActivity(), words,R.color.colorAccent);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        return rootView;
    }

}
