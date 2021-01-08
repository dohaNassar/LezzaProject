package com.example.jit.lezzaproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class GroupActivity extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.about_item) {
            Intent i = new Intent(getApplicationContext(),ShowOrdersActivity.class);
            startActivity(i);
        }else if(item.getItemId()== R.id.close_item){
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(getApplicationContext(), Log_inActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            //SharedPreferences sp = getSharedPreferences("loginSettings", MODE_PRIVATE);
            //SharedPreferences.Editor editor = sp.edit();
            //editor.putString("email", "");
            //editor.putString("password", "");
            //editor.commit();
            //Intent i = new Intent(getApplicationContext(),LogInActivity.class);
            //startActivity(i);

            //finish();
        }else if (item.getItemId()== R.id.search_res_activity){
            Intent i = new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(i);
        }else if (item.getItemId()== R.id.settings_activity){
            //Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
            //startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }



}
