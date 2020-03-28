package com.example.animalgame.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.animalgame.R;

import java.util.ArrayList;

public class PlayerListAdapter extends ArrayAdapter<Player>{

        private static final String TAG = "PlayerListAdapter";

        private Context mContext;
        private int mResource;
        private int lastPosition = -1;

        /**
         * Holds variables in a View
         */
        private static class ViewHolder {
            TextView name;
            TextView score;
        }


        public PlayerListAdapter(Context context, int resource, ArrayList<Player> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //get the persons information
            String name = getItem(position).getName();
            int score = getItem(position).getScore();

            //Create the person object with the information
            Player player = new Player(name,score);

            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            ViewHolder holder;


            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.textView1);
                holder.score = (TextView) convertView.findViewById(R.id.textView2);

                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }


            lastPosition = position;




            holder.name.setText(player.getName());
            holder.score.setText(Integer.toString(player.getScore()));


            return convertView;
        }
    }

