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
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.device.SensorListAdapter;
import com.home.smart.thuans.homeassistance.device.SensorModel;
import com.home.smart.thuans.homeassistance.utils.DeviceConstant;
import com.home.smart.thuans.homeassistance.utils.HouseControlCenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlBoardFragment extends Fragment {
    private RequestQueue queue;
    private TextView responseText;
    private List<SensorModel> sensorModelList;
    private ListView listSensor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_control_board,container,false);
        Button btnUpdate = (Button) rootView.findViewById(R.id.btnUpdateHouseStatus);
        responseText = (TextView) rootView.findViewById(R.id.responseText);
        //init list sensor
        sensorModelList = new ArrayList<SensorModel>();
        SensorModel sm = new SensorModel("Cửa chính", "Đang tắt", DeviceConstant.SENSOR_DOOR,1);
        SensorModel sm1 = new SensorModel("Đèn Phòng ngủ", "Đang bật", DeviceConstant.SENSOR_LIGHT,1);
        SensorModel sm2 = new SensorModel("Máy lạnh", "Đang bật", DeviceConstant.SENSOR_TEMP,1);

        sensorModelList.add(sm);
        sensorModelList.add(sm1);
        sensorModelList.add(sm2);

        listSensor = (ListView)rootView.findViewById(R.id.lstSensorStatus);

        SensorListAdapter sensorAdapter = new SensorListAdapter(this.getContext(), R.layout.sensor_list , sensorModelList);
        listSensor.setAdapter(sensorAdapter);

        final RequestQueue queue = HouseControlCenter.getInstance(this.getContext()).
                getRequestQueue();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = HouseControlCenter.CENTER_ADDRESS;
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
                        setState(responseText.toString());
                    }
                });

                queue.add(stringRequest);
            }
        });


        return rootView;
    }
    private void setState(String dataResponse){
        String split = "<br/>";
        String[] data = dataResponse.split(split);

        sensorModelList.get(0).setValue("đóng");
        SensorListAdapter sensorAdapter = new SensorListAdapter(this.getContext(), R.layout.sensor_list , sensorModelList);
        listSensor.setAdapter(sensorAdapter);
    }
}