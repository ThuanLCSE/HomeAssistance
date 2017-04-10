package layout;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.chat.ChatArrayAdapter;
import com.home.smart.thuans.homeassistance.chat.ChatMessage;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private static ChatFragment instance = null;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private Button buttonSend;
    private ListView listView;
    private ChatArrayAdapter chatArrayAdapter;
    private EditText chatText;
    private int Flag = 0;
    private boolean side;
    private String demoBotMsg[] = new String[]{
        "Thưa anh đã 5 giờ 45 phút anh có muốn chuyển chế độ chuẩn bị buổi tối không ạ",
        "Máy lạnh phòng khách đã bật",
        "Bình pha nước nóng đã được chuẩn bị",
        "Xin chào ông chủ tôi có thể giúp gì được ạ?",
        "Dạ thưa ông chủ phòng ngủ đang 20 độ ạ",
        "Dạ đã bật đèn phòng ngủ ạ",
        "Dạ thưa ông chủ đã chuyển sang chế độ tiếp khách ạ",
        "Thưa ông chủ nhà có khách ạ",
        "Thưa ông chủ báo động đã được bật",
        "Dự đoán khách: cậu Văng"
    };

    public static ChatFragment getInstance() {
        if (instance == null) {
            instance = new ChatFragment();
        }
        return instance;
    }

    public void setFlag(int flag) {
        Flag = flag;
    }



    private int botMsgCount = -1;
    private int noti = -1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat,container,false);
        if (getArguments() != null) {

            Log.d("------------", "onCreateView:" + getArguments().getSerializable("notiClick"));
        }
        if (savedInstanceState != null) {
            botMsgCount = savedInstanceState.getInt("botMsgCount");

        }



        buttonSend = (Button) rootView.findViewById(R.id.btnChatSend);

        listView = (ListView) rootView.findViewById(R.id.lstMsgChat);

        chatArrayAdapter = new ChatArrayAdapter(rootView.getContext(), R.layout.msg_right);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) rootView.findViewById(R.id.txtHumanChat);
        side = !side;
        botMsgCount++;
        sendChatMessage(demoBotMsg[botMsgCount]);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage(chatText.getText().toString());
                //bot reply
                botMsgCount++;
                if (botMsgCount == demoBotMsg.length) botMsgCount = -1;
                sendChatMessage(demoBotMsg[botMsgCount]);
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });

        return rootView;
    }



    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"vi");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Nói gì đi");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            System.out.println("Fail to nhận diện giọng nói");
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("botMsgCount", botMsgCount);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == -1 && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    sendChatMessage(result.get(0));
                    //bot reply
                    botMsgCount++;
                    if (botMsgCount == demoBotMsg.length) botMsgCount = -1;
                    sendChatMessage(demoBotMsg[botMsgCount]);
                }
                break;
            }

        }
    }
    private boolean sendChatMessage(String message) {
        chatArrayAdapter.add(new ChatMessage(side, message));
        chatText.setText("");
        side = !side;
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.d("------------", "Res:" + getArguments().getSerializable("notiClick"));

        if (Flag == 111111) {
            promptSpeechInput();
            this.setFlag(0);
        }
    }

}
