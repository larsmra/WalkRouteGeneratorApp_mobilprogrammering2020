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
import no.hiof.larsmra.walkroutegenerator.activities.GroupActivity;
import no.hiof.larsmra.walkroutegenerator.models.Group;

public class GroupRecyclerAdapter extends RecyclerView.Adapter<GroupRecyclerAdapter.ViewHolder> {

    private static final String TAG = "GroupRecyclerHolder";

    private List<Group> mGroups;
    private Context mContext;

    public GroupRecyclerAdapter(List<Group> groups, Context context) {
        mGroups = groups;
        mContext = context;
    }

    @NonNull
    @Override
    public GroupRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new GroupRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupRecyclerAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.groupName.setText(mGroups.get(position).getName());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mGroups.get(position).getName());

                Intent intent = new Intent(mContext, GroupActivity.class);
                intent.putExtra("group", mGroups.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView groupName;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.text);
            parentLayout = itemView.findViewById(R.id.list_item_layout);
        }
    }
}
