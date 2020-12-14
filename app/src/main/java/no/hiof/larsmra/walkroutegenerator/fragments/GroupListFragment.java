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

import no.hiof.larsmra.walkroutegenerator.models.Group;
import no.hiof.larsmra.walkroutegenerator.R;
import no.hiof.larsmra.walkroutegenerator.adapters.GroupRecyclerAdapter;

public class GroupListFragment extends Fragment {

    private static final String TAG = "GroupListFragment";

    private List<Group> groups;
    private List<String> groupUids;

    private FirebaseFirestore firestoreDb;
    private CollectionReference groupCollectionRef;
    private ListenerRegistration listenerRegistration;

    private RecyclerView recyclerViewGroups;
    private GroupRecyclerAdapter adapterGroups;

    private RecyclerView recyclerViewOtherGroups;
    private GroupRecyclerAdapter adapterOtherGroups;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.group_list_fragment, container, false);
        Log.d(TAG, "onCreateView");

        firestoreDb = FirebaseFirestore.getInstance();
        groupCollectionRef = firestoreDb.collection("groups");

        groups = new ArrayList<>();
        groupUids = new ArrayList<>();

        recyclerViewOtherGroups = root.findViewById(R.id.recycler_view_groups);
        adapterOtherGroups = new GroupRecyclerAdapter(groups, getContext());
        recyclerViewOtherGroups.setAdapter(adapterOtherGroups);
        recyclerViewOtherGroups.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void createReadListener() {
        listenerRegistration = groupCollectionRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
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

                    Group group = documentSnapshot.toObject(Group.class);

                    Log.d(TAG, "onEvent: Group: " + group);

                    group.setUid(documentSnapshot.getId());
                    int position = groupUids.indexOf(group.getUid());

                    switch (documentChange.getType()) {
                        case ADDED:
                            groups.add(group);
                            groupUids.add(group.getUid());
                            adapterOtherGroups.notifyItemInserted(groups.size() - 1);
                            break;
                        case MODIFIED:
                            groups.set(position, group);
                            adapterOtherGroups.notifyItemChanged(position);
                            break;
                        case REMOVED:
                            groups.remove(position);
                            groupUids.remove(position);
                            adapterOtherGroups.notifyItemRemoved(position);
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