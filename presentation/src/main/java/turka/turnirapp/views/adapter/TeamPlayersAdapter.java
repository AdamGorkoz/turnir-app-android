package turka.turnirapp.views.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import turka.turnirapp.R;
import turka.turnirapp.model.Player;

import java.util.List;

public class TeamPlayersAdapter extends RecyclerView.Adapter<TeamPlayersAdapter.ViewHolder> {

    private final List<Player> mPlayers;
    private LayoutInflater mInflator;

    public TeamPlayersAdapter(Context context , List<Player> players) {
        mPlayers = players;
        this.mInflator = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.player_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Player current = mPlayers.get(position);
        holder.itemView.setBackgroundColor(position % 2  == 0 ? Color.rgb(244, 252, 250) : Color.rgb(255,255,255));
        holder.setData(current);
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Player current;

        public ViewHolder(View itemView) {
            super(itemView);
            mIdView = (TextView) itemView.findViewById(R.id.player_name);
            mContentView = (TextView) itemView.findViewById(R.id.player_goals);
        }

        public void setData(Player player){
            this.current = player;
            mIdView.setText(player.getName());
            mContentView.setText(String.valueOf(player.getGoals()));
        }
    }
}
