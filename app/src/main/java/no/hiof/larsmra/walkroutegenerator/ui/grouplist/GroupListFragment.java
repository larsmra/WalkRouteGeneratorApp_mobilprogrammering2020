package no.hiof.larsmra.walkroutegenerator.ui.grouplist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import no.hiof.larsmra.walkroutegenerator.R;

public class GroupListFragment extends Fragment {

    private GroupListViewModel groupListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupListViewModel = ViewModelProviders.of(this).get(GroupListViewModel.class);
        View root = inflater.inflate(R.layout.group_list_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_group_list);
        groupListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}