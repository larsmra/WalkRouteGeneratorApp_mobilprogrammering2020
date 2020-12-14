package no.hiof.larsmra.walkroutegenerator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import no.hiof.larsmra.walkroutegenerator.activities.CreateScheduleActivity;
import no.hiof.larsmra.walkroutegenerator.R;
import no.hiof.larsmra.walkroutegenerator.activities.WalkRouteActivity;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);

        final Button generateWalkRouteButton = (Button)root.findViewById(R.id.generate_route_button);
        generateWalkRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Generate route");
                Intent intent = new Intent(getActivity(), WalkRouteActivity.class);
                startActivity(intent);
            }
        });

        final Button createScheduleButton = (Button)root.findViewById(R.id.create_schedule_button);
        createScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Create schedule");
                Intent intent = new Intent(getActivity(), CreateScheduleActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}