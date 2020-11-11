package com.androidtesttask.ui.map;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import com.androidtesttask.R;
import com.androidtesttask.data.model.Data;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String ARG_DATA = "ARG_DATA";
    private Data item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setToolbar();

        final TextView idTv = findViewById(R.id.tv_id);
        final TextView nameTv = findViewById(R.id.tv_name);
        final TextView countryTv = findViewById(R.id.tv_country);

        if (getIntent().getExtras() != null) {
            item = (Data) getIntent().getSerializableExtra(ARG_DATA);
            assert item != null;
            idTv.setText(item.getId());
            nameTv.setText(item.getName());
            countryTv.setText(item.getCountry());
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    private void setToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(item.getLat(), item.getLon());
        googleMap.addMarker(new MarkerOptions().position(latLng).title(item.getCountry()));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(item.getLat(), item.getLon()), 12.0f));
    }
}