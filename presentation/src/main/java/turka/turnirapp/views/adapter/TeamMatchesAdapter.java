package turka.turnirapp.views.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import turka.turnirapp.R;
import turka.turnirapp.model.TeamMatch;

/**
 * Created by turka on 5/13/2017.
 */

public class TeamMatchesAdapter extends RecyclerView.Adapter<TeamMatchesAdapter.ViewHolder> {


    private final List<TeamMatch> mMatches;
    private LayoutInflater mInflator;

    public TeamMatchesAdapter(Context context , List<TeamMatch> matches) {
        mMatches = matches;
        this.mInflator = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.match_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TeamMatch current = mMatches.get(position);
        holder.itemView.setBackgroundColor(position % 2  == 0 ? Color.rgb(245,245,245) : Color.rgb(250,250,250));
        holder.setData(current);
    }

    @Override
    public int getItemCount() {
        return mMatches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mDateView;
        public final TextView mOpponentView;
        public final TextView mOpponentScore;
        public TeamMatch current;

        public ViewHolder(View itemView) {
            super(itemView);
            mDateView = (TextView) itemView.findViewById(R.id.match_date);
            mOpponentView = (TextView) itemView.findViewById(R.id.match_opponent);
            mOpponentScore = (TextView) itemView.findViewById(R.id.match_score);
        }

        public void setData(TeamMatch teamMatch){
            this.current = teamMatch;
            mDateView.setText(new SimpleDateFormat("dd/MM/yyyy").format(teamMatch.getMatchDate()));
            mOpponentView.setText(teamMatch.getOpponent());
            mOpponentScore.setText(teamMatch.getScore());
        }
    }
}
