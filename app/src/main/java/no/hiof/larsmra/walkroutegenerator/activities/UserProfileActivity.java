package no.hiof.larsmra.walkroutegenerator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import no.hiof.larsmra.walkroutegenerator.R;
import no.hiof.larsmra.walkroutegenerator.models.User;

public class UserProfileActivity extends AppCompatActivity {

    private TextView name;
    private TextView points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.user_name);
        points = findViewById(R.id.user_points);

        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("user");

        if (user != null) {
            name.setText(user.getName());
            points.setText("Points: " + user.getPoints());
        }
    }
}