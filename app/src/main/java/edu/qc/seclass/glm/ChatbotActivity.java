package edu.qc.seclass.glm;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;

import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class ChatbotActivity extends AppCompatActivity {

    private ListView mListView;
    private FloatingActionButton mButtonSend;
    private EditText mEditTextMessage;
    private ImageView mImageView;
    private ChatMessageAdapter mAdapter;

    public String result = "";

    private Context mContext;

    private Assistant watsonAssistant;
    private SessionResponse watsonAssistantSession;

    private void createServices() {
        watsonAssistant = new Assistant("2019-10-19", new IamAuthenticator(mContext.getString(R.string.assistant_apikey)));
        watsonAssistant.setServiceUrl(mContext.getString(R.string.assistant_url));
        Log.v("created", checkInternetConnection());
        Log.v("Created" , watsonAssistant.getName());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = getApplicationContext();

        mListView = (ListView) findViewById(R.id.listView);
        mButtonSend = (FloatingActionButton) findViewById(R.id.btn_send);
        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mImageView = (ImageView) findViewById(R.id.iv_image);
        mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);

//code for sending the message
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString().trim();
                if(message.length() > 0) {
                    sendMessage(message);
                    mEditTextMessage.setText("");
                    mListView.setSelection(mAdapter.getCount() - 1);
                    Log.v("result", "after done");
                    Log.v("result", "after unning " + result);
//                ChatMessage chatMessage = new ChatMessage(result, true, true);
//                mAdapter.add(chatMessage);
                }
            }
        });

        createServices();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendMessage(String message) {
        final String inputmessage = message.trim();
        ChatMessage chatMessage = new ChatMessage(message, true, false);
        mAdapter.add(chatMessage);
        //respond as Helloworld
//        mimicOtherMessage("HelloWorld");
        Thread thread = new Thread(new Runnable() {
            volatile boolean running = true;
            public void run() {
                try {
                    if (watsonAssistantSession == null) {
                        Log.v("error", "watson assistant was null");
                        ServiceCall<SessionResponse> call = watsonAssistant.createSession(new CreateSessionOptions.Builder().assistantId(mContext.getString(R.string.assistant_id)).build());
                        Log.v("error",call.toString());

                        Log.v("error", "execute and result");
                        watsonAssistantSession = call.execute().getResult();
                        Log.v("error", "done if");
                    }
                    Log.v("error", "before message input");
                    MessageInput input = new MessageInput.Builder()
                            .text(inputmessage)
                            .build();
                    Log.v("error", "before message options");
                    MessageOptions options = new MessageOptions.Builder()
                            .assistantId(mContext.getString(R.string.assistant_id))
                            .input(input)
                            .sessionId(watsonAssistantSession.getSessionId())
                            .build();
                    Log.v("error", "before response");
                    MessageResponse response = watsonAssistant.message(options).execute().getResult();
                    Log.i("asd", "run: " + response);
                    final Message outMessage = new Message();
                    if (response != null &&
                            response.getOutput() != null &&
                            !response.getOutput().getGeneric().isEmpty() &&
                            "text".equals(response.getOutput().getGeneric().get(0).responseType())) {
                        outMessage.setMessage(response.getOutput().getGeneric().get(0).text());
                        outMessage.setId("2");

//                        messageArrayList.add(outMessage.getMessage());
//                        Log.v("result", response.getOutput().toString());
//                        JSONObject obj = new JSONObject(response.getOutput().toString());
//                        Log.v("result", obj.toString());
//                        Log.v("result", "find Split");
//                        Log.v("result", response.getOutput().getGeneric().get(0).text());
//                        Log.v("result", obj.getJSONObject("generic").toString());
//                        Log.v("result", obj.get("generic").toString());
                        result = response.getOutput().getGeneric().get(0).text();
//                        running = false;
//                        if(!running) return;
//                        mimicOtherMessage(result);
                        Log.v("result", result);

//                        mAdapter.add(chatMessage);
                    }
                    else{
                        Log.v("error", "broke if loop");
                    }
                } catch (Exception e) {
                    Log.v("error", "exception");
                }
            }
        });
        thread.start();
        try{
            thread.join();
        }
        catch (Exception e){

        }
        Log.v("result", "end");
        Log.v("result", result);
        mimicOtherMessage(result);
    }

    private void mimicOtherMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, false, false);
        mAdapter.add(chatMessage);
//        result = message;
//        return;1
    }

    private void sendMessage() {
        ChatMessage chatMessage = new ChatMessage(null, true, true);
        mAdapter.add(chatMessage);

        mimicOtherMessage();
    }

    private void mimicOtherMessage() {
        ChatMessage chatMessage = new ChatMessage(null, false, true);
        mAdapter.add(chatMessage);
    }

    private String checkInternetConnection() {
        // get Connectivity Manager object to check connection
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        // Check for network connections
        if (isConnected) {
            return "true";
        } else {
            Toast.makeText(this, " No Internet Connection available ", Toast.LENGTH_LONG).show();
            return "false";
        }

    }

}