package no.hiof.larsmra.walkroutegenerator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import no.hiof.larsmra.walkroutegenerator.models.Group;
import no.hiof.larsmra.walkroutegenerator.R;

public class GroupActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        title = findViewById(R.id.group_title);
        description = findViewById(R.id.group_description);

        Intent intent = getIntent();
        Group group = (Group) intent.getSerializableExtra("group");

        if (group != null) {
            //setTitle(group.getTitle());
            title.setText(group.getName());
            description.setText(group.getDescription());
        }
    }
}