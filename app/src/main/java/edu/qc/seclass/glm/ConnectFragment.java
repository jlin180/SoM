package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//fragment
public class ConnectFragment extends Fragment {

    /**
     * Every time we create a new instance of this class we are calling
     * the onCreateView method
     * From my understanding, the returned inflater populates the layout (fragment_evaluation) with
     * the objects we created for it, somewhat of an onCreate method for the fragment
     * This is needed because we are not creating a new activity
     * Creating new activities makes your app slower
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connect,container,false);

        Button mapBtn = (Button) view.findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent ( getActivity(), MapActivity.class);
                startActivity(i);
            }
        });

        ImageView communityButton = view.findViewById(R.id.imageView);
        communityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent (getActivity(), MapActivity.class);
                i.putExtra("whichLocation", "comun");
                startActivity(i);
            }
        });

        ImageView educationButton = view.findViewById(R.id.imageView2);
        educationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MapActivity.class);
                i.putExtra("whichLocation","edu");
                startActivity(i);
            }
        });

        ImageView volunteerButton = view.findViewById(R.id.imageView3);
        volunteerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MapActivity.class);
                i.putExtra("whichLocation","vol");
                startActivity(i);
            }
        });

        return view;
    }








}
