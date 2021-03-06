package com.kiran.invento2k21;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EventsActivity extends AppCompatActivity {

    private RecyclerView mBlogList;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        mBlogList = findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("events");
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Events, OngoingViewHolder> FirebaseAdapter = new FirebaseRecyclerAdapter<Events, OngoingViewHolder>(
                Events.class,
                R.layout.design_row,
                OngoingViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(OngoingViewHolder viewHolder, Events model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDescription());
                viewHolder.setImage(getApplicationContext(), model.getImage());


            }
        };

        mBlogList.setAdapter(FirebaseAdapter);
    }

    public static class OngoingViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public OngoingViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

        }

        public void setTitle(String title) {

            TextView post_title = mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setDesc(String desc) {

            TextView post_desc = mView.findViewById(R.id.post_description);
            post_desc.setText(desc);
        }


        public void setImage(Context ctx, String image) {
            ImageView post_image = mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);

        }

    }
}

