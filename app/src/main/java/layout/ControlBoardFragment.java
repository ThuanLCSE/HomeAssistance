package layout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
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
import com.home.smart.thuans.homeassistance.status.HouseStatusListAdapter;
import com.home.smart.thuans.homeassistance.status.HouseStatusModel;
import com.home.smart.thuans.homeassistance.utils.DeviceConstant;
import com.home.smart.thuans.homeassistance.utils.HouseControlCenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlBoardFragment extends Fragment {
    private static final String TAG = "ControlBoardFragment";
    private RequestQueue queue;
    private TextView responseText;
    private List<SensorModel> sensorModelList;
    private List<HouseStatusModel> statusList;
    private GridView listStatus;
    private RecyclerView listSensor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_control_board,container,false);
        Button btnUpdate = (Button) rootView.findViewById(R.id.btnUpdateHouseStatus);
//        responseText = (TextView) rootView.findViewById(R.id.responseText);

        //init list house status
        statusList = new ArrayList<HouseStatusModel>();
        HouseStatusModel hsm = new HouseStatusModel("Nhiệt độ phòng khách", "25'C");
        HouseStatusModel hsm1 = new HouseStatusModel("Nhiệt độ phòng ngủ", "20'C");
        HouseStatusModel hsm2 = new HouseStatusModel("Máy lạnh", "bật");
        HouseStatusModel hsm3 = new HouseStatusModel("Nhiệt độ nhà ăn", "20'C");
        HouseStatusModel hsm4 = new HouseStatusModel("Tivi ", "bật");
        statusList.add(hsm);
        statusList.add(hsm1);
        statusList.add(hsm2);
        statusList.add(hsm3);
        statusList.add(hsm4);

        listStatus = (GridView) rootView.findViewById(R.id.lstHouseStatus);

        HouseStatusListAdapter houstStatusAdapter = new HouseStatusListAdapter(this.getContext(), R.layout.house_status_list , statusList);
        listStatus.setAdapter(houstStatusAdapter);

        //init list sensor
        sensorModelList = new ArrayList<SensorModel>();
        SensorModel sm = new SensorModel("Cửa chính", "Đang tắt", DeviceConstant.SENSOR_DOOR,1);
        SensorModel sm1 = new SensorModel("Đèn Phòng ngủ", "Đang bật", DeviceConstant.SENSOR_LIGHT,1);
        SensorModel sm2 = new SensorModel("Máy lạnh", "Đang bật", DeviceConstant.SENSOR_TEMP,1);

        sensorModelList.add(sm);
        sensorModelList.add(sm1);
        sensorModelList.add(sm2);

        listSensor = (RecyclerView)rootView.findViewById(R.id.lstSensorStatus);
        listSensor.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        SensorListAdapter sensorAdapter = new SensorListAdapter(sensorModelList);
        listSensor.setAdapter(sensorAdapter);
        listSensor.setLayoutManager(MyLayoutManager);

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
        SensorListAdapter sensorAdapter = new SensorListAdapter(sensorModelList);
        listSensor.setAdapter(sensorAdapter);
    }
}