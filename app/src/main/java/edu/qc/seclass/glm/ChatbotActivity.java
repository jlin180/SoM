package edu.qc.seclass.glm;


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

public class ChatbotActivity extends AppCompatActivity {

    private ListView mListView;
    private FloatingActionButton mButtonSend;
    private EditText mEditTextMessage;
    private ImageView mImageView;
    private ChatMessageAdapter mAdapter;

    private Context mContext;

    private Assistant watsonAssistant;
    private SessionResponse watsonAssistantSession;

    private void createServices() {
        watsonAssistant = new Assistant("2019-10-19", new IamAuthenticator("9H4OlARrmzLNuMO-6Vinwrc3BmznhMxbkcWzxcqNT2-8"));
        watsonAssistant.setServiceUrl("https://gateway-wdc.watsonplatform.net/assistant/api/v2/assistants/ce930a54-1d6c-4372-aaf5-4573f9e0e4a1/sessions");

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
                String message = mEditTextMessage.getText().toString();
                sendMessage(message);
                mEditTextMessage.setText("");
                mListView.setSelection(mAdapter.getCount() - 1);
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
            public void run() {
//                try {
                    if (watsonAssistantSession == null) {
                        Log.v("error", "watson assistant was null");
                        ServiceCall<SessionResponse> call = watsonAssistant.createSession(new CreateSessionOptions.Builder().assistantId("ce930a54-1d6c-4372-aaf5-4573f9e0e4a1").build());
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
                            .assistantId("ce930a54-1d6c-4372-aaf5-4573f9e0e4a1")
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
                        mimicOtherMessage("Hello world");
                    }
//                    else{
//                        Log.v("error", "broke if loop");
//                    }
//                } catch (Exception e) {
//                    Log.v("error", "exception");
//                }
            }
        });

        thread.start();

        mimicOtherMessage("Out of try");
    }

    private void mimicOtherMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, false, false);
        mAdapter.add(chatMessage);
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
}