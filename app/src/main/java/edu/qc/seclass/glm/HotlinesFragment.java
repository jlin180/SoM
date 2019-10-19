package edu.qc.seclass.glm;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//fragment
public class HotlinesFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_hotlines,container,false);
    }
}
