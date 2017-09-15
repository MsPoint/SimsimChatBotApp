package com.example.muamarsidik.simsimichatbot;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.muamarsidik.simsimichatbot.Adapter.CustomAdapter;
import com.example.muamarsidik.simsimichatbot.helper.HttpDataHandler;
import com.example.muamarsidik.simsimichatbot.model.ChatModel;
import com.example.muamarsidik.simsimichatbot.model.SimsimModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<ChatModel> list_chat = new ArrayList<>();
    FloatingActionButton btn_send_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list_message);
        editText = (EditText)findViewById(R.id.user_message);
        btn_send_message = (FloatingActionButton)findViewById(R.id.fab);

        btn_send_message.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                ChatModel model = new ChatModel(text, true); // Pengguna mengirim chat
                list_chat.add(model);
                new simsimApi().execute(list_chat);

                // menghapus chat user

                editText.setText("");
            }
        });
    }

    private class simsimApi extends AsyncTask<List<ChatModel>,Void,String>{

        String stream = null;
        List<ChatModel> models;
        String text = editText.getText().toString();

        @Override
        protected String doInBackground(List<ChatModel>... lists) {
            String url = String.format("http://sandbox.api.simsimi.com/request.p?key=%s&lc=en&ft=1.0&text=%s",getString(R.string.simsim_api),text);
            models = lists[0];
            HttpDataHandler httpDataHandler = new HttpDataHandler();
            stream = httpDataHandler.GetHTTPData(url);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            SimsimModel response = gson.fromJson(s, SimsimModel.class);

            ChatModel chatModel = new ChatModel(response.getResponse(),false); // Respon Simsim

            models.add(chatModel);
            CustomAdapter adapter = new CustomAdapter(models,getApplicationContext());
            listView.setAdapter(adapter);
        }
    }
}
