package app.infosys.volleydemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    
    UserAdapter userAdapter;
    RecyclerView UserRecycVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserRecycVw=findViewById(R.id.UserRecycVw);
        StringRequest stringRequest=new StringRequest(1, "https://malikwaqas077.000webhostapp.com/android/select.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //getting the data and saving them into separate arrays for using in the recycler view adapter
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = new JSONArray(jsonObject.getString("students"));
                    int IncomingDataLength = result.length();
                    UserRvData userRvData = new UserRvData(IncomingDataLength);
                    for (int i = 0; i < IncomingDataLength; i++) {
                        JSONObject QueryData = result.getJSONObject(i);
                        userRvData.AccountNameDummy[i] = QueryData.getString("username");
                        userRvData.PhoneDummy[i] = QueryData.getString("phoneno");


                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

                userAdapter = new UserAdapter(MainActivity.this, UserRvData.getListData());
                UserRecycVw.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                UserRecycVw.setAdapter(userAdapter);
                RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                itemAnimator.setAddDuration(1000);
                itemAnimator.setRemoveDuration(1000);
                UserRecycVw.setItemAnimator(itemAnimator);
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);



    }
}
