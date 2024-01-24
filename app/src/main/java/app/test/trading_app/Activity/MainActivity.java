package app.test.trading_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.test.trading_app.CryptoAdapter;
import app.test.trading_app.CryptoModal;
import app.test.trading_app.R;
import app.test.trading_app.TopListAdapter;
import app.test.trading_app.databinding.ActivityMainBinding;
import app.test.trading_app.utils.CoreTask;

public class MainActivity extends AppCompatActivity {

   ActivityMainBinding binding;
    String performanceHour ;
    String performanceDay ;
    String performanceMonth ;
    String performanceYear ;

    ArrayList<CryptoModal> cryptoModals = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               binding.hour.setBackground(getDrawable(R.drawable.selected));
               binding.day.setBackground(getDrawable(R.drawable.un_selected));
               binding.month.setBackground(getDrawable(R.drawable.un_selected));
               binding.year.setBackground(getDrawable(R.drawable.un_selected));
               loadData("hour");
            }
        });

        binding.day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.hour.setBackground(getDrawable(R.drawable.un_selected));
                binding.day.setBackground(getDrawable(R.drawable.selected));
                binding.month.setBackground(getDrawable(R.drawable.un_selected));
                binding.year.setBackground(getDrawable(R.drawable.un_selected));
                loadData("day");

            }
        });

        binding.month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.hour.setBackground(getDrawable(R.drawable.un_selected));
                binding.day.setBackground(getDrawable(R.drawable.un_selected));
                binding.month.setBackground(getDrawable(R.drawable.selected));
                binding.year.setBackground(getDrawable(R.drawable.un_selected));
                loadData("month");

            }
        });

        binding.year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.hour.setBackground(getDrawable(R.drawable.un_selected));
                binding.day.setBackground(getDrawable(R.drawable.un_selected));
                binding.month.setBackground(getDrawable(R.drawable.un_selected));
                binding.year.setBackground(getDrawable(R.drawable.selected));
                loadData("year");

            }
        });

        loadData("day");

        binding.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        binding.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        binding.card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });


        this.binding.navLeft.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                new CoreTask().navTask(MainActivity.this,menuItem,MainActivity.this,binding);
                return true;
            }
        });
        this.binding.toolwarIc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.binding.dl.openDrawer(Gravity.LEFT);
            }
        });

    }
    private  void loadData(String s){
        binding.r1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        CryptoAdapter cryptoAdapter = new CryptoAdapter(this, new ArrayList<>(),s);
        binding.r1.setAdapter(cryptoAdapter);

        binding.r2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        CryptoAdapter cryptoAdapter2 = new CryptoAdapter(this, new ArrayList<>(),s);
        binding.r2.setAdapter(cryptoAdapter2);

//        binding.rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        TopListAdapter topListAdapter = new TopListAdapter(this, cryptoModals);
//        binding.rcv.setAdapter(topListAdapter);



        cryptoAdapter.setRecyclerView(binding.r1);
        cryptoAdapter2.setRecyclerView(binding.r2);
        cryptoAdapter.startAutoScroll();
        cryptoAdapter2.startAutoScroll();


        String url = "https://ariyankabir.com/data.php";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                JsonArrayRequest.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                       // Toast.makeText(MainActivity.this, String.valueOf(response.length()), Toast.LENGTH_SHORT).show();

                        if (response.length() == 0) {

                        } else {
                            ArrayList<CryptoModal> transarray = new ArrayList<>();
                            ArrayList<CryptoModal> transarray2 = new ArrayList<>();

                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject dataObject = response.getJSONObject(i);
                                    String id = dataObject.getString("id");
                                    String name = dataObject.getString("name");
                                    String symbol = dataObject.getString("symbol");
                                    String price = dataObject.getString("price");
                                    String img = dataObject.getString("image");


                                    JSONObject performanceObj = dataObject.getJSONObject("performance");

                                    performanceHour = performanceObj.getString("hour");
                                    performanceDay = performanceObj.getString("day");
                                    performanceMonth = performanceObj.getString("month");
                                    performanceYear = performanceObj.getString("year");


                                    CryptoModal transObj = new CryptoModal(
                                            id, name, symbol, price,
                                            performanceHour, performanceDay, performanceMonth, performanceYear,img
                                    );
                                    ////////////

                                    if (Integer.parseInt(id)<10){
                                        cryptoModals.add(transObj);

                                    }
                                    TopListAdapter topListAdapter = new TopListAdapter(MainActivity.this,cryptoModals);
                                    binding.rcv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                    binding.rcv.setAdapter(topListAdapter);
                                      ///////////

                                    if (Integer.parseInt(id) % 2 == 0) {
                                        transarray.add(transObj);
                                    } else {
                                        transarray2.add(transObj);
                                    }
                                }

                                cryptoAdapter.updateTrans(transarray);
                                cryptoAdapter2.updateTrans(transarray2);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                error -> {

                }
        ) {};

        requestQueue.add(jsonArrayRequest);



    }
}