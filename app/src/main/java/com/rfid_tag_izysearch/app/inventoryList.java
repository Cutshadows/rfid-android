package com.rfid_tag_izysearch.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rfid_tag_izysearch.app.helpers.HttpHelpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class inventoryList extends AppCompatActivity {

    private ArrayList<String> dataset;
    private ArrayAdapter<String> adapter;
    //private ListView lisInventory;
    private TextView lisInventory;

    private RequestQueue mQueue;

    private static final String TAG="Inventory_list";

    public static final String URL_RFID="http://rw.izyrfid.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);

        //lisInventory=(ListView) findViewById(R.id.listRfid);
        //mQueue= Volley.newRequestQueue(this);
        lisInventory=(TextView) findViewById(R.id.listInventory);

        requestInventory();
        //dataset=new ArrayList<>();
        //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataset);
        //lisInventory.setText(adapter.getItem(1));
       // lisInventory.setAdapter(adapter);
        //lisInventory=(TextView) findViewById(R.id.listInventory);

       /*HttpHelpers http = new HttpHelpers(getApplicationContext(), "http://rw.izyrfid.com", "");
        http.addHeader("Authorization", "Bearer 5pPkQJOl2diSP7POzytZlM1F-Sx-PdhwYO911p0EsMQf5MfmC4kdvrJgLHy_Mhw92tOfAMro1aaO2XS8ieTvXTKEusCrB23yNMPJXok7KFtGeEtQX07AZGpNyUraK0LZvGwSXQP7OfXZIeZyzBqtiRCdCy6DHId1svcyfcXZrIop4MfeloH2W0Wr66XlSM5In1Z4LMZ9hVJj6uXsvR4dW0mm1PDmnn0EUW_JeGmBYQi86-aXLBZI86vnFxMAu1C48ZYbf8cnUFWLCe22SVaoZtfEReqdBuAWmbBB3ORCY8ZsjTm4VUNtuZN3hb1asClgQsGHAnmdPt5UtlkACARO6MojoNvQhZ8dyo6y2WKUUitGACHOvvWepMiKib7vQ_55LEOLoo7Tn2iSg_SMX9STorE0Jgh77BMzO5hr_N5CsEMULCb_QyoxFoQCCBi6uf6IcOK_QHu2wEiI7NMQv4VH359ZNcgNbtHhzpDfseurf_LigpyRZkG28jcKysa0FFiz");

        http.client(Request.Method.GET, "/api/inventory/GetAllInventories", "application/json; charset=utf-8", null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                try{
                    Log.e(TAG, "List Inventario : "+response);
                    lisInventory.setText("DATA :"+response);

                }catch (Exception e){
                    Log.e(TAG, "Error : "+e.getMessage());

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse " + error);
            }
        });*/
       // dataset = new ArrayList<>();


    }

    private void requestInventory() {
        String customURL = URL_RFID + "/api/inventory/GetAllInventories";
        RequestQueue mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, customURL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d(TAG, response.toString());

                    JSONArray jsonArray = response.getJSONArray("");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject tagsdetail = jsonArray.getJSONObject(i);
                        int id = tagsdetail.getInt("Id");
                        String Name = tagsdetail.getString("Name");
                        Log.d(TAG, "onOk "+Name);


                    }

                } catch (JSONException e) {
                    Log.e(TAG, "Fallo "+e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer 5pPkQJOl2diSP7POzytZlM1F-Sx-PdhwYO911p0EsMQf5MfmC4kdvrJgLHy_Mhw92tOfAMro1aaO2XS8ieTvXTKEusCrB23yNMPJXok7KFtGeEtQX07AZGpNyUraK0LZvGwSXQP7OfXZIeZyzBqtiRCdCy6DHId1svcyfcXZrIop4MfeloH2W0Wr66XlSM5In1Z4LMZ9hVJj6uXsvR4dW0mm1PDmnn0EUW_JeGmBYQi86-aXLBZI86vnFxMAu1C48ZYbf8cnUFWLCe22SVaoZtfEReqdBuAWmbBB3ORCY8ZsjTm4VUNtuZN3hb1asClgQsGHAnmdPt5UtlkACARO6MojoNvQhZ8dyo6y2WKUUitGACHOvvWepMiKib7vQ_55LEOLoo7Tn2iSg_SMX9STorE0Jgh77BMzO5hr_N5CsEMULCb_QyoxFoQCCBi6uf6IcOK_QHu2wEiI7NMQv4VH359ZNcgNbtHhzpDfseurf_LigpyRZkG28jcKysa0FFiz");
                return headers;
            }
        };
        mQueue.add(request);

    }
}
