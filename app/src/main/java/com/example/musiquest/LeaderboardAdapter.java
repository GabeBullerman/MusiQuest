package com.example.musiquest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

        public class LeaderboardAdapter extends ArrayAdapter<Player> {

            public LeaderboardAdapter(Context context, ArrayList<Player> players) {
                super(context, 0, players);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View listItemView = convertView;

                if (listItemView == null) {
                    listItemView = LayoutInflater.from(getContext()).inflate(R.layout.leaderboard_item, parent, false);
                }

                Player currentPlayer = getItem(position);

                TextView playerRankTextView = listItemView.findViewById(R.id.playerRank);
                TextView playerNameTextView = listItemView.findViewById(R.id.playerName);
                TextView playerScoreTextView = listItemView.findViewById(R.id.playerScore);

                playerRankTextView.setText(currentPlayer.leaderboardRank());
                playerNameTextView.setText(currentPlayer.leaderboardUsername());
                playerScoreTextView.setText(String.valueOf(currentPlayer.leaderboardScore()));

                return listItemView;
            }
        }

