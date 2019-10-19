package edu.qc.seclass.glm;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
//fragment
public class LogFragment extends Fragment {
    ListView listView;
    NotingFragment arrayOfLogs = new NotingFragment();
    ArrayList<String> ourJournal = arrayOfLogs.getJournal();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);
        if (!ourJournal.isEmpty()) {
            populateList(view);
        }
        return view;
    }

    private void populateList(View view) {
        System.out.println(ourJournal.get(0) + "????????????????????????");
        //building the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.log_item, ourJournal);
        listView = (ListView) view.findViewById(R.id.journal_list_view);
        listView.setAdapter(adapter);
    }
}
