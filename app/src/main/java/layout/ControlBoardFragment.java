package layout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.utils.HouseControlCenter;

public class ControlBoardFragment extends Fragment {
    private RequestQueue queue;
    private TextView responseText;
    private String hostArduino = "http://192.168.8.150:8080/";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_control_board,container,false);
        Button btnUpdate = (Button) rootView.findViewById(R.id.btnUpdateHouseStatus);
        responseText = (TextView) rootView.findViewById(R.id.responseText);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = hostArduino;
// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        responseText.setText("Response is: "+ response.toString());
                        setState(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseText.setText("That didn't work!"+ error.getMessage());
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

// Instantiate the RequestQueue.
        HouseControlCenter controlUtil = new HouseControlCenter(rootView.getContext());
        queue = controlUtil.getQueue();
        return rootView;
    }
    private void setState(String dataResponse){
        String split = "<br/>";
        String[] data = dataResponse.split(split);
//        if (data[1] != null ){
//            if (data[1].contains("on")){
//                lightStt.setBackgroundColor(Color.GREEN);
//            } else
//            if (data[1].contains("off")){
//                lightStt.setBackgroundColor(Color.RED);
//            }
//        }
//        if (data[2] != null ){
//            tempStt.setText(data[2].substring(20));
//        }
    }
}