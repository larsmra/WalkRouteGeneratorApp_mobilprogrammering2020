package no.hiof.larsmra.walkroutegenerator.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import no.hiof.larsmra.walkroutegenerator.R;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel scheduleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        View root = inflater.inflate(R.layout.schedule_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_schedule);
        scheduleViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}