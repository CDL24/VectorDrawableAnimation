package com.example.climbachiya.glikeanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout mRevealView;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(position);

    }

    //load fragment
    private void loadFragment(int position) {

        if (position == 0) {
            getFragmentManager().beginTransaction().replace(R.id.container, new FragmentVector()).commit();
        }else if(position == 1){
            getFragmentManager().beginTransaction().replace(R.id.container, new FragmentVectorAnimation()).commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.side_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.vector_action) {
            position = 0;
            loadFragment(position);
        }else if (item.getItemId() == R.id.vector_animation_action){
            position = 1;
            loadFragment(position);
        }
        return super.onOptionsItemSelected(item);
    }

}