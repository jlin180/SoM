package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
//fragment
public class NotingFragment extends Fragment implements RetrieveGetSentiment.AsyncResponse {

    public NotingFragment() {
    }

    Hashtable<String, Object> comm = new Hashtable<>();
    String str;
    String st;
    static int[] arr = new int[2]; //count of the feelings neg

    public static ArrayList journal = new ArrayList<String>();

    public ArrayList getJournal() {
        return journal;
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
        View view = inflater.inflate(R.layout.fragment_noting, container, false);
        Button feelTodayBtn = view.findViewById(R.id.feelTodayBtn);
        feelTodayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check(v.getRootView());

            }
        });

        return view;
    }

    public void uploadData(String score) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/temp/logs/");
        String str = this.str.toLowerCase();
        double s = Double.parseDouble(score);

        String key = DateFormat.getDateTimeInstance().format(new Date()).toString();
        LogObj input = new LogObj(this.str, s);
//        comm.put("DateTime", DateFormat.getDateTimeInstance().format(new Date()));
//        comm.put("log", this.str);
//        comm.put("Happy%", s);
//        comments(comm);
        ref.child(key).setValue(input);
        countComment(s);
    }

    public void countComment(Double s) {
        if (s <= .5) {
            int a = arr[0]; // bad comments
            a = a + 1;
            arr[0] = a;
        } else {
            int b = arr[1]; //good comment
            b = b + 1;
            arr[1] = b;
        }
    }

    public String getGoodORBad() {
        if (arr[0] > arr[1]) {
            return "We always have tomorrow to look forward to. Let's try to make the best out of it!";
        } else return "You had a good day, let's do it again!!!";
    }


    public static int Evaluate(int i) {
        return arr[i];
    }


    /**
     * this is calling the happy activity (next activity 2)
     * or if the input is negative it will call the sad activity (next activity 1)
     *
     * @param score
     */
    public void analyzer(String score) {
        uploadData(score);

        if (Double.parseDouble(score) <= .5) {
            Intent i = new Intent(getActivity(), SadActivity.class);
            startActivity(i);

        } else {
            Intent n = new Intent(getActivity(), HappyActivity.class);
            startActivity(n);
        }
    }

    @Override
    public void processFinish(String output) {
        final String[] score2 = {null};
        score2[0] = output;
        analyzer(score2[0]);
    }

    public void setStr(String a) {
        this.str = a;
        String temp = a.toLowerCase();
        journal.add(temp);
    }

    public void Check(View view) {
        //final String[] score2 = {null};//don't know if this is used
        EditText feeling = view.findViewById(R.id.editText);
        st = feeling.getText().toString();

        System.out.println(st + "**********");

        setStr(st);
        RetrieveGetSentiment RetrieveGetSentiment = new RetrieveGetSentiment(this);
        RetrieveGetSentiment.execute(feeling.getText().toString());
    }


}
