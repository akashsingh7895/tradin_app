package app.test.trading_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import app.test.trading_app.R;
import app.test.trading_app.databinding.ActivityMainBinding;
import app.test.trading_app.utils.CoreTask;

public class MainActivity extends AppCompatActivity {

   ActivityMainBinding binding;
    
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
            }
        });

        binding.day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.hour.setBackground(getDrawable(R.drawable.un_selected));
                binding.day.setBackground(getDrawable(R.drawable.selected));
                binding.month.setBackground(getDrawable(R.drawable.un_selected));
                binding.year.setBackground(getDrawable(R.drawable.un_selected));
            }
        });

        binding.month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.hour.setBackground(getDrawable(R.drawable.un_selected));
                binding.day.setBackground(getDrawable(R.drawable.un_selected));
                binding.month.setBackground(getDrawable(R.drawable.selected));
                binding.year.setBackground(getDrawable(R.drawable.un_selected));
            }
        });

        binding.year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.hour.setBackground(getDrawable(R.drawable.un_selected));
                binding.day.setBackground(getDrawable(R.drawable.un_selected));
                binding.month.setBackground(getDrawable(R.drawable.un_selected));
                binding.year.setBackground(getDrawable(R.drawable.selected));
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
}