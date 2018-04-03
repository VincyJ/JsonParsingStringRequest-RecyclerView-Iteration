package example.com.jsonparsingstringrequestrecycler_iteration;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //View to display names
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private List<String> namesList;
    // Creating Progress dialog.
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        namesList = new ArrayList<>();
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please Wait, data loading");
        progressDialog.show();
        getStringRequest();
    }

    private void getStringRequest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://siva123.000webhostapp.com/php/read_json.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray mJsonArray;
                try {
                    mJsonArray = new JSONArray(response);
                    for (int i = 0; i < mJsonArray.length(); i++) {
                        JSONObject person = (JSONObject) mJsonArray.get(i);
                        String name = person.getString("name");
                        namesList.add(name);
                    }
                    // Hiding the progress dialog after all task complete.
                    progressDialog.dismiss();
                    // setting datas into the adapter
                    mAdapter = new CustomAdapter(MainActivity.this, namesList);
                    // setting adapter in the list
                    mRecyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Hiding the progress dialog after all task complete.
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Sorry, no data available", Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
