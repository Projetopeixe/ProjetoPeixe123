package br.edu.ufopa.cadfishmaster;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Type of map
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


        //Event of click
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;
                Toast.makeText(MapsActivity.this, "Lat: " + latitude +
                        "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Local")
                        .snippet("Descrição")
                        .icon(
                                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                        ));
            }
        });
        // Add a marker in Sydney and move the camera
        LatLng ibirapuera = new LatLng(-23.587650, -46.659788);
        mMap.addMarker(new MarkerOptions()
                .position(ibirapuera)
                .title("Ibirapuera")
                .icon(
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                ));
        mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(ibirapuera, 15)
        );
    }
}
