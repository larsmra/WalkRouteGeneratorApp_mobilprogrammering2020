package no.hiof.larsmra.walkroutegenerator.activities;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import no.hiof.larsmra.walkroutegenerator.R;

public class WalkRouteActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener {

    private static final String TAG = "WalkRouteActivity";

    private GoogleMap mMap;
    private LatLng start;
    private LatLng destination1;
    private LatLng destination2;
    private LatLng end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_route);

        generateRoute();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void generateRoute() {
        start = new LatLng(59.1292658, 11.3524027);
        double lat = start.latitude;
        double lng = start.longitude;


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: called.");
        mMap = googleMap;

        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMinZoomPreference(15);

        googleMap.addPolyline(new PolylineOptions().clickable(true).add(
                start,
                destination1,
                destination2,
                end));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(start));

        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
    }

    private void setDummyData() {
        start = new LatLng(59.1291326,11.3520987);
        destination1 = new LatLng(59.1248037,11.3621154);
        destination2 = new LatLng(59.1325113,11.355725);
        end = start;
    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}