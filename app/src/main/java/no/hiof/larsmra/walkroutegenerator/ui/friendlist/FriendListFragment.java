package no.hiof.larsmra.walkroutegenerator.ui.friendlist;

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

public class FriendListFragment extends Fragment {

    private FriendListViewModel friendListViewModel;

    public static FriendListFragment newInstance() {
        return new FriendListFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        friendListViewModel = ViewModelProviders.of(this).get(FriendListViewModel.class);
        View root = inflater.inflate(R.layout.friend_list_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_friend_list);
        friendListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}