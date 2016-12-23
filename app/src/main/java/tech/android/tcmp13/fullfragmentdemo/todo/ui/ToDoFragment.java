package tech.android.tcmp13.fullfragmentdemo.todo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tech.android.tcmp13.fullfragmentdemo.R;
import tech.android.tcmp13.fullfragmentdemo.equipments.model.Equipment;

/**
 * Created by tcmp13-t on 12/21/2016.
 */
public class ToDoFragment extends Fragment {

    private ArrayAdapter<Object> adapter;
    private List<Object> todoItems;

    /**
     * The constructor is the first method to be called.\
     * Even though we can't add params, we can still control the implementation.
     * Init any "empty" member variables here.
     */
    public ToDoFragment() {

        super();
        todoItems = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.fragmentListView);
        adapter = new ArrayAdapter<>(getActivity(),//The context = the attached activity
                android.R.layout.simple_list_item_1,//The list item layout
                android.R.id.text1,//The TextView id in the provided layout
                todoItems);
        listView.setAdapter(adapter);//The data
        return view;
    }

    public void add(Object o) {

        if (adapter == null) {
            todoItems.add(o);
        } else {
            adapter.add(o);
            adapter.notifyDataSetChanged();
        }
    }
}
