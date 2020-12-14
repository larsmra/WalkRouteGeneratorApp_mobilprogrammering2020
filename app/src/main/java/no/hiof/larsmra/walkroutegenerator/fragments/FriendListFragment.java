package no.hiof.larsmra.walkroutegenerator.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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
import java.util.List;

import no.hiof.larsmra.walkroutegenerator.R;
import no.hiof.larsmra.walkroutegenerator.models.User;
import no.hiof.larsmra.walkroutegenerator.adapters.UserRecyclerAdapter;

public class FriendListFragment extends Fragment {

    private static final String TAG = "FriendListFragment";

    private FirebaseFirestore firestoreDb;
    private CollectionReference userCollectionRef;

    private ListenerRegistration listenerRegistration;

    private RecyclerView recyclerView;

    private UserRecyclerAdapter adapter;

    private List<User> users = new ArrayList<>();
    private List<String> userUids = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.friend_list_fragment, container, false);
        Log.d(TAG, "onCreateView");

        firestoreDb = FirebaseFirestore.getInstance();
        userCollectionRef = firestoreDb.collection("users");

        recyclerView = root.findViewById(R.id.recycler_view_users);
        adapter = new UserRecyclerAdapter(users, getContext());
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

                Log.d(TAG, "onEvent: Listen.");

                for (DocumentChange documentChange : value.getDocumentChanges()) {
                    QueryDocumentSnapshot documentSnapshot = documentChange.getDocument();

                    Log.d(TAG, "onEvent: document: " + documentChange.getDocument());

                    User user = documentSnapshot.toObject(User.class);

                    Log.d(TAG, "onEvent: Group: " + user);

                    user.setUid(documentSnapshot.getId());
                    int position = userUids.indexOf(user.getUid());

                    switch (documentChange.getType()) {
                        case ADDED:
                            users.add(user);
                            userUids.add(user.getUid());
                            adapter.notifyItemInserted(users.size() - 1);
                            break;
                        case MODIFIED:
                            users.set(position, user);
                            adapter.notifyItemChanged(position);
                            break;
                        case REMOVED:
                            users.remove(position);
                            userUids.remove(position);
                            adapter.notifyItemRemoved(position);
                    }
                }
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

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}