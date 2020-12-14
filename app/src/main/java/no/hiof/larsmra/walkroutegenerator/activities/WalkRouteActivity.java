package no.hiof.larsmra.walkroutegenerator.activities;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

import java.util.Random;

import no.hiof.larsmra.walkroutegenerator.R;

public class WalkRouteActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener {

    private static final String TAG = "WalkRouteActivity";

    private GoogleMap mMap;
    private LatLng currentLocation = new LatLng(59.1292475,11.3506146);
    private LatLng destination1;
    private LatLng destination2;
    private LatLng end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_route);

        findUserLocation();

        generateRoute();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void findUserLocation() {
        // I tried to retrieve the user location, but the app keeps crashing when asking for permissions.
        /*
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 1);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            }
        });
         */
    }


    private void generateRoute() {
        double dist = 0.01;

        Random random = new Random();
        double rand1 = random.nextDouble() * 2 - 1;
        double number1 = dist * rand1;

        double rand2 = random.nextDouble() * 2 - 1;
        double number2 = dist * rand2;

        double lat1 = currentLocation.latitude + number1;
        double lng1 = currentLocation.longitude + number2;

        Log.d(TAG, "generateRoute: Lat: " + lat1 + ", lng: " + lng1);

        destination1 = new LatLng(lat1, lng1);

        double rand3 = random.nextDouble() * 2 - 1;
        double number3 = dist * rand3;

        double rand4 = random.nextDouble() * 2 - 1;
        double number4 = dist * rand4;

        double lat2 = lat1 + number3;
        double lng2 = lng1 + number4;

        destination2 = new LatLng(lat2, lng2);

        end = currentLocation;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: called.");
        mMap = googleMap;

        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMinZoomPreference(15);

        googleMap.addPolyline(new PolylineOptions().clickable(true).add(
                currentLocation,
                destination1,
                destination2,
                end));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}