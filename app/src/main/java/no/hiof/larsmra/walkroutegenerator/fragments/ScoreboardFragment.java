package no.hiof.larsmra.walkroutegenerator.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.hiof.larsmra.walkroutegenerator.R;
import no.hiof.larsmra.walkroutegenerator.adapters.ScoreboardRecyclerAdapter;
import no.hiof.larsmra.walkroutegenerator.models.User;

public class ScoreboardFragment extends Fragment {

    private static final String TAG = "ScoreboardFragment";

    TextView tvPoints;

    private List<User> users = new ArrayList<>();
    private List<String> userUids = new ArrayList<>();

    private FirebaseFirestore firestoreDb;
    private CollectionReference userCollectionRef;
    private ListenerRegistration listenerRegistration;

    private RecyclerView recyclerView;
    private ScoreboardRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.scoreboard_fragment, container, false);
        Log.d(TAG, "onCreateView");

        firestoreDb = FirebaseFirestore.getInstance();
        userCollectionRef = firestoreDb.collection("users");


        tvPoints = root.findViewById(R.id.list_points);

        recyclerView = root.findViewById(R.id.recycler_view_scoreboard);
        adapter = new ScoreboardRecyclerAdapter(users, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void createReadListener() {
        listenerRegistration = userCollectionRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w(TAG, "onEvent: Listen failed.", error);
                    return;
                }

                for (DocumentChange documentChange : value.getDocumentChanges()) {
                    QueryDocumentSnapshot documentSnapshot = documentChange.getDocument();
                    User user = documentSnapshot.toObject(User.class);
                    user.setUid(documentSnapshot.getId());
                    int position = userUids.indexOf(user.getUid());

                    switch (documentChange.getType()) {
                        case ADDED:
                            users.add(user);
                            break;
                        case MODIFIED:
                            users.set(position, user);
                            break;
                        case REMOVED:
                            users.remove(position);
                    }
                }

                Log.d(TAG, "onEvent: temp: " + users);

                Collections.sort(users);

                List<String> tempUids = new ArrayList<>();
                for (User user : users) {
                    tempUids.add(user.getUid());
                }
                userUids = tempUids;

                adapter.setUsers(users);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        createReadListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (listenerRegistration != null) {
            listenerRegistration.remove();
        }
    }

}