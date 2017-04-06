package layout;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.chat.ChatArrayAdapter;
import com.home.smart.thuans.homeassistance.chat.ChatMessage;

public class ChatFragment extends Fragment {
    private Button buttonSend;
    private ListView listView;
    private ChatArrayAdapter chatArrayAdapter;
    private EditText chatText;
    private boolean side;
    private String demoBotMsg[] = new String[]{
            "Xin chào ông chủ tôi có thể giúp gì được ạ?",
            "Dạ thưa ông chủ phòng ngủ đang 20 độ ạ",
            "Dạ đã bật đèn phòng ngủ ạ",
            "Dạ thưa ông chủ đã chuyển sang chế độ tiếp khách ạ",
            "Thưa ông chủ nhà có khách ạ",
            "Thưa ông chủ báo động đã được bật",
            "Dự đoán khách: cậu Văng",
            "Thưa ông chủ đã 8 giờ 45 phút ông có muốn chuyển chế độ chuẩn bị phòng ngủ không ạ",
            "Thưa ông máy lạnh đã được bật",
            "Thưa ông đã đóng cửa phòng ngủ ạ"
    };

    private int botMsgCount = -1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat,container,false);
        if (savedInstanceState != null){
            botMsgCount = savedInstanceState.getInt("botMsgCount");
        }

        buttonSend = (Button) rootView.findViewById(R.id.btnChatSend);

        listView = (ListView) rootView.findViewById(R.id.lstMsgChat);

        chatArrayAdapter = new ChatArrayAdapter(rootView.getContext(), R.layout.msg_right);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) rootView.findViewById(R.id.txtHumanChat);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    sendChatMessage(chatText.getText().toString());
                    //bot reply
                    botMsgCount++;
                    if (botMsgCount == demoBotMsg.length) botMsgCount = -1;
                    return sendChatMessage(demoBotMsg[botMsgCount]);
                }
                return false;
            }
        });
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
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("botMsgCount", botMsgCount);
    }

    private boolean sendChatMessage(String message) {
        chatArrayAdapter.add(new ChatMessage(side, message));
        chatText.setText("");
        side = !side;
        return true;
    }
}
