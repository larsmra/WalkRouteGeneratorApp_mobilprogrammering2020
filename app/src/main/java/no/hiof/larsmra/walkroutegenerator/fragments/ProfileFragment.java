package no.hiof.larsmra.walkroutegenerator.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import no.hiof.larsmra.walkroutegenerator.R;
import no.hiof.larsmra.walkroutegenerator.models.User;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    FirebaseAuth auth;
    FirebaseFirestore firestoreDb;
    CollectionReference userCollectionRef;

    TextView tvName;
    TextView tvEmail;
    TextView tvPoints;
    Button logOutBtn;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.profile_fragment, container, false);

        auth = FirebaseAuth.getInstance();
        firestoreDb = FirebaseFirestore.getInstance();
        userCollectionRef = firestoreDb.collection("users");

        tvName = root.findViewById(R.id.current_user_name);
        tvEmail = root.findViewById(R.id.current_user_email);
        tvPoints = root.findViewById(R.id.current_user_points);

        logOutBtn = root.findViewById(R.id.log_out_btn);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(inflater.getContext()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(inflater.getContext(), "Signing out", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

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
                            tvName.setText(user.getName());
                            tvEmail.setText(user.getEmail());
                            tvPoints.setText("Points: " + user.getPoints());
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

    @Override
    public void onResume() {
        super.onResume();
        getAndSetUserValues();
    }
}