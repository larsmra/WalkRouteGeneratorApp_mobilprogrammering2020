package no.hiof.larsmra.walkroutegenerator.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import no.hiof.larsmra.walkroutegenerator.R;
import no.hiof.larsmra.walkroutegenerator.models.User;

public class ScheduleFragment extends Fragment {

    private static final String TAG = "ScheduleFragment";

    FirebaseAuth auth;
    FirebaseFirestore firestoreDb;
    CollectionReference userCollectionRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.schedule_fragment, container, false);

        auth = FirebaseAuth.getInstance();
        firestoreDb = FirebaseFirestore.getInstance();
        userCollectionRef = firestoreDb.collection("users");

        final TextView textView = root.findViewById(R.id.text_schedule);

        return root;
    }

    private void getAndSetUserValues() {
        if (auth.getUid() != null) {
            DocumentReference currentUserDocumentRef = userCollectionRef.document(auth.getUid());
            currentUserDocumentRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "onComplete: DocumentSnapshot data " + document.getData());
                            User user = document.toObject(User.class);
                        }
                        else {
                            Log.d(TAG, "onComplete: Document not found.");
                        }
                    }
                    else {
                        Log.d(TAG, "onComplete: Failed. " + task.getException());
                    }
                }
            });
        }
    }

}