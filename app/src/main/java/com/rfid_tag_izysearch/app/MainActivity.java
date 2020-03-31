package com.rfid_tag_izysearch.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.rfid_tag_izysearch.app.helpers.HttpHelpers;
import com.rfid_tag_izysearch.app.repositories.TagsRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {


    private EditText editId, editDesc;

    private ListView listRfid;
    private Button button, ejecutar_api;
    private static final String TAG="MainActivity";

    private ArrayList<String> dataset;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    private void initComponent() {
        editId=(EditText) findViewById(R.id.editId);
        editDesc=(EditText) findViewById(R.id.editDesc);
        listRfid=(ListView) findViewById(R.id.listRfid);
        button=(Button) findViewById(R.id.button);
        ejecutar_api=(Button) findViewById(R.id.ejecutar_api);

        button.setOnClickListener(this);
        ejecutar_api.setOnClickListener(this);


        dataset=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataset);
        listRfid.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        final TagsRepository tagsRepository = new TagsRepository(this);
        switch (v.getId()) {
            case R.id.button:
                String id = editId.getText().toString();
                String desc = editDesc.getText().toString();
                Boolean state = false;
                tagsRepository.InsertTag(id, desc, state);
                ToastMg(tagsRepository.ViewTags());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //dataset=tagsRepository.ViewAllTags();
                        dataset.addAll(tagsRepository.ViewAllTags());
                        adapter.notifyDataSetChanged();
                    }
                });
                break;
            case R.id.ejecutar_api:
                /*HttpHelpers http=new HttpHelpers(getApplicationContext(),"http://rw.izyrfid.com", "");
                http.addHeader("Authorization", "Bearer kBGaLpCuaItYkPiPVM77CQNYCkjO67ye0h_rjpijbS8zmQ4XlrfH4Zq68LHEmz1O63kTYvMN-TnfibHUDqsYbLR6ZNVMDSabRYTa2uD0MyXZp2eXBINXAPEDRs_Zg6j-9p10LOJjhxAePEI-1FgNT_VcMT4T08zqz2btsGaZIdngmQD5tjm_So0u4hiOZX76Z4hdACAiaet4ZA9Jyndnq7u3AjKh76c69vvUznOq0Nor3xri6PrgBzPvqX-tSdohA8X57crD4NkwhZUWHnVfOONS9zJZB44R6SvauRRtP-8Ejg2O-UWEu3_KuuNzjXPsSZldFnrYqdCgXwiUFXleZoBByzZy5rul8z7WDyG2pCjM-v4ijAFDakpcjCvVgpRDpbsbZ1EjFAzi1_IvRh3tjS-zokbXDAk_j_PAxZ4A1H0YpNhva_-JXcChJ1qPV9KFElhaZ3691o5xH3hrmTscs7MtleXqB1H_nVUFL3juOilKf7GO2VLAz6_pE4BMArnF");
                http.client(Request.Method.GET, "/api/inventory/GetAllInventories", "application/json; charset=utf-8", null, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.e(TAG, "onResponse "+response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse "+error);
                    }
                });*/
                Intent goInventory=new Intent(this, inventoryList.class);
                startActivity(goInventory);
                break;
        }
    }


    public void ToastMg(String texto) {
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
    }
}
