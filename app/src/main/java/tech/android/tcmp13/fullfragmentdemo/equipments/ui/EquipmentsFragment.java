package tech.android.tcmp13.fullfragmentdemo.equipments.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tech.android.tcmp13.fullfragmentdemo.R;
import tech.android.tcmp13.fullfragmentdemo.equipments.model.Equipment;
import tech.android.tcmp13.fullfragmentdemo.todo.control.MarkAsTodoListener;

/**
 * Created by tcmp13-t on 12/21/2016.
 */
public class EquipmentsFragment extends Fragment {

    private ArrayAdapter<Equipment> adapter;
    private MarkAsTodoListener markAsTodoListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.fragmentListView);
        adapter = new ArrayAdapter<>(getActivity(),//The context = the attached activity
                android.R.layout.simple_list_item_1,//The list item layout
                android.R.id.text1,//The TextView id in the provided layout
                generateEquipments());
        listView.setAdapter(adapter);//The data
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Object o = adapter.getItem(position);
                if (markAsTodoListener != null)
                    markAsTodoListener.markAsToDo(o);
            }
        });
        return view;
    }

    private List<Equipment> generateEquipments() {

        List<Equipment> result = new ArrayList<>(4);
        for (int i = 0; i < 4; i++)
            result.add(new Equipment("Crafty " + i, "Google " + i));
        return result;
    }

    public void addEquipment(Equipment equipment) {

        adapter.add(equipment);
        adapter.notifyDataSetChanged();
    }

    public void setMarkAsTodoListener(MarkAsTodoListener markAsTodoListener) {
        this.markAsTodoListener = markAsTodoListener;
    }
}
