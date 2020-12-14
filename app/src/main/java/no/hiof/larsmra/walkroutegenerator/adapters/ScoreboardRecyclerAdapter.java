package no.hiof.larsmra.walkroutegenerator.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import no.hiof.larsmra.walkroutegenerator.R;
import no.hiof.larsmra.walkroutegenerator.activities.UserProfileActivity;
import no.hiof.larsmra.walkroutegenerator.models.User;

public class ScoreboardRecyclerAdapter extends RecyclerView.Adapter<ScoreboardRecyclerAdapter.ViewHolder> {

    private static final String TAG = "ScoreboardRecyclerAdapt";

    private List<User> users;
    private Context context;

    public ScoreboardRecyclerAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreboardRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scoreboard_list_item, parent, false);
        return new ScoreboardRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.username.setText(users.get(position).getName());

        holder.points.setText(Long.toString(users.get(position).getPoints()));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + users.get(position).getName());

                Intent intent = new Intent(context, UserProfileActivity.class);
                intent.putExtra("user", users.get(position));
                context.startActivity(intent);
            }
        });
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView points;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.text);
            points = itemView.findViewById(R.id.list_points);
            parentLayout = itemView.findViewById(R.id.list_item_layout);
        }
    }

}
