package turka.turnirapp.views.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import turka.turnirapp.R;
import turka.turnirapp.model.MatchEvent;

/**
 * Created by turka on 5/25/2017.
 */

public class MatchEventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<MatchEvent> mMatchEvents;
    private LayoutInflater mInflator;

    public MatchEventsAdapter(Context context , List<MatchEvent> events) {
        mMatchEvents = events;
        this.mInflator = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        MatchEvent current = mMatchEvents.get(position);
        return current.getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 1:
                View goalView = mInflator.inflate(R.layout.goal_event, parent, false);
                return new GoalViewHolder(goalView);
            case 2:
                View cardView = mInflator.inflate(R.layout.card_event, parent, false);
                return new CardViewHolder(cardView);
            default:
                View defaultView = mInflator.inflate(R.layout.goal_event, parent, false);
                return new GoalViewHolder(defaultView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        MatchEvent current = mMatchEvents.get(position);
        holder.itemView.setBackgroundColor(position % 2  == 0 ? Color.rgb(244, 252, 250) : Color.rgb(255,255,255));
        switch (holder.getItemViewType()){
            case 1:
                ((GoalViewHolder)holder).setData(current);
                break;
            case 2:
                ((CardViewHolder)holder).setData(current);
        }
    }

    @Override
    public int getItemCount() {
        return mMatchEvents.size();
    }

    public class GoalViewHolder extends RecyclerView.ViewHolder {
        public final TextView mPlayerNameLeft;
        public final TextView mMinuteLeft;
        public final TextView mPlayerNameRight;
        public final TextView mMinuteRight;
        public final ImageView mBallLeft;
        public final ImageView mBallRight;
        public MatchEvent current;

        public GoalViewHolder(View itemView) {
            super(itemView);
            mPlayerNameLeft = (TextView) itemView.findViewById(R.id.event_player_name_left);
            mMinuteLeft = (TextView) itemView.findViewById(R.id.event_minute_left);
            mPlayerNameRight = (TextView) itemView.findViewById(R.id.event_player_name_right);
            mMinuteRight = (TextView) itemView.findViewById(R.id.event_minute_right);
            mBallLeft = (ImageView) itemView.findViewById(R.id.ball_image_left);
            mBallRight = (ImageView) itemView.findViewById(R.id.ball_image_right);
        }

        public void setData(MatchEvent event){
            this.current = event;
            mPlayerNameLeft.setText("");
            mMinuteLeft.setText("");
            mPlayerNameRight.setText("");
            mMinuteRight.setText("");
            mBallLeft.setVisibility(View.GONE);
            mBallRight.setVisibility(View.GONE);

            switch(event.getPosition()){
                case 1:
                    mPlayerNameLeft.setText(event.getPlayerName());
                    mMinuteLeft.setText(event.getMinute());
                    mBallLeft.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    mPlayerNameRight.setText(event.getPlayerName());
                    mMinuteRight.setText(event.getMinute());
                    mBallRight.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public final TextView mPlayerNameLeft;
        public final TextView mMinuteLeft;
        public final TextView mPlayerNameRight;
        public final TextView mMinuteRight;
        public final ImageView mRedCardLeft;
        public final ImageView mRedCardRight;
        public final ImageView mYellowCardLeft;
        public final ImageView mYellowCardRight;
        public MatchEvent current;

        public CardViewHolder(View itemView) {
            super(itemView);
            mPlayerNameLeft = (TextView) itemView.findViewById(R.id.event_player_name_left);
            mMinuteLeft = (TextView) itemView.findViewById(R.id.event_minute_left);
            mPlayerNameRight = (TextView) itemView.findViewById(R.id.event_player_name_right);
            mMinuteRight = (TextView) itemView.findViewById(R.id.event_minute_right);
            mRedCardLeft = (ImageView) itemView.findViewById(R.id.card_red_image_left);
            mRedCardRight = (ImageView) itemView.findViewById(R.id.card_red_image_right);
            mYellowCardLeft = (ImageView) itemView.findViewById(R.id.card_yellow_image_left);
            mYellowCardRight = (ImageView) itemView.findViewById(R.id.card_yellow_image_right);
        }

        public void setData(MatchEvent event){
            this.current = event;
            mPlayerNameLeft.setText("");
            mMinuteLeft.setText("");
            mPlayerNameRight.setText("");
            mMinuteRight.setText("");

            mRedCardLeft.setVisibility(View.GONE);
            mRedCardRight.setVisibility(View.GONE);
            mYellowCardLeft.setVisibility(View.GONE);
            mYellowCardRight.setVisibility(View.GONE);

            switch(event.getPosition()){
                case 1:
                    mPlayerNameLeft.setText(event.getPlayerName());
                    mMinuteLeft.setText(event.getMinute());
                    switch (event.getSubtype()){
                        case 1:
                            mYellowCardLeft.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            mRedCardLeft.setVisibility(View.VISIBLE);
                            break;
                    }
                    break;
                case 2:
                    mPlayerNameRight.setText(event.getPlayerName());
                    mMinuteRight.setText(event.getMinute());
                    switch (event.getSubtype()){
                        case 1:
                            mYellowCardRight.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            mRedCardRight.setVisibility(View.VISIBLE);
                            break;
                    }
                    break;
            }


        }
    }
}
