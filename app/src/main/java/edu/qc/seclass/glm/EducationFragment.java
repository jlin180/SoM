package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
//fragment
public class EducationFragment extends Fragment {
    public EducationFragment() {
    }
    /**
     * Every time we create a new instance of this class we are calling
     * the onCreateView method
     * From my understanding, the returned inflater populates the layout with
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
        View view = inflater.inflate(R.layout.fragment_education, container, false);
        ImageButton moodDisorderBtn = view.findViewById(R.id.mood_disorder_btn);
        moodDisorderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MoodActivity.class);
                startActivity(i);
            }
        });

        ImageButton anxietyBtn = view.findViewById(R.id.anxiety_btn);
        anxietyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(getActivity(), AnxietyActivity.class);
                startActivity(n);
            }
        });

        ImageButton autismBtn = view.findViewById(R.id.autism_btn);
        autismBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getActivity(), AutismActivity.class);
                startActivity(m);
            }
        });
        return view;
    }
}



