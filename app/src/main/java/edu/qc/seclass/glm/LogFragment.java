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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

//fragment
public class LogFragment extends Fragment {
    ListView listView;
    ArrayList<String> ourJournal = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_log, container, false);
        populateList(view);
        return view;
    }

    private void populateList(View view) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/temp/");
        ref.child("logs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LogObj input = snapshot.getValue(LogObj.class);
                    ourJournal.add(input.log);
                }
                showList(ourJournal, view);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void showList(ArrayList <String> ourJournal, View view){
        //building the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.log_item, ourJournal);
        listView = (ListView) view.findViewById(R.id.journal_list_view);
        listView.setAdapter(adapter);
    }
}
