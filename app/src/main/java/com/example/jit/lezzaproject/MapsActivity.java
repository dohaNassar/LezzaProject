package com.example.jit.lezzaproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import jxl.Sheet;
import jxl.Workbook;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseFirestore firestore;
    ArrayList<Resturant> resturants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Descroption: Read from excel
        readFromExcel(getApplicationContext(), resturants);

        //


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Descroption: To set Map settings
        UiSettings settings = mMap.getUiSettings();
        settings.setZoomControlsEnabled(true);
        settings.setCompassEnabled(true);
        settings.setMyLocationButtonEnabled(true);

        //Descroption: Move the camera to Gaza
        LatLng latLng = new LatLng(31.5, 34.46667);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 9);
        mMap.animateCamera(cameraUpdate, 5000, null);

        //Descroption: To put the resturants markers
        for(int i = 0 ; i < resturants.size() ; i++) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(resturants.get(i).getLatitude(), resturants.get(i).getLongitude()))
                    .title(resturants.get(i).getDescrip())).setTag(resturants.get(i));
        }

        //Descroption: To make listener after click on each mark
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Resturant res = (Resturant) marker.getTag();
                for(int i = 0 ; i < resturants.size() ; i++){
                    if(res.isEqual(resturants.get(i))){
                        setInfoWindowAdapter(i);
                    }
                }

                return false;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                try {
                    Resturant r = (Resturant) marker.getTag();
                    String resName = r.getRestaurant();
                    String resImg = r.getPhoto();
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    intent.putExtra("img", resImg);
                    intent.putExtra("resName", resName);
                    startActivity(intent);
                }catch (Exception e){
                    Log.d("ttt","Error: "+ e.getMessage());
                }
            }
        });
    }

    //Descroption: Method to read data from Excel
    public ArrayList<Resturant> readFromExcel(Context context, ArrayList<Resturant> resturants){
        try {
            AssetManager am = getAssets();
            InputStream is = am.open("resturants.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            //int col = s.getColumns();
            for(int i=1;i<row;i++) {
                Resturant r = new Resturant();
                //Cell z = s.getCell(0, i);
                r.setRestaurant(s.getCell(0,i).getContents());
                r.setLatitude(Double.parseDouble(s.getCell(1,i).getContents()));
                r.setLongitude(Double.parseDouble(s.getCell(2,i).getContents()));
                r.setDescrip(s.getCell(3,i).getContents());
                r.setPhoto(s.getCell(4,i).getContents());
                resturants.add(r);
            }
        } catch (Exception e) {
            Toast.makeText(context, "error", Toast.LENGTH_LONG);
        }
        return resturants;
    }

    //Descroption: Method To set window adapter
    public void setInfoWindowAdapter(int i){
        MyInfoWindowAdapter infoWindowAdapter = new MyInfoWindowAdapter(getApplicationContext(), resturants.get(i));
        mMap.setInfoWindowAdapter(infoWindowAdapter);
    }
}
