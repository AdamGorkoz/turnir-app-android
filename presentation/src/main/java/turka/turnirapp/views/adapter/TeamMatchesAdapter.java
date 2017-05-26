package turka.turnirapp.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import turka.turnirapp.R;
import turka.turnirapp.TeamMatchActivity;
import turka.turnirapp.model.LeagueTeam;
import turka.turnirapp.model.TeamMatch;

/**
 * Created by turka on 5/13/2017.
 */

public class TeamMatchesAdapter extends RecyclerView.Adapter<TeamMatchesAdapter.ViewHolder> {


    private final List<TeamMatch> mMatches;
    private final LeagueTeam mCurrentTeam;
    private LayoutInflater mInflator;

    public TeamMatchesAdapter(Context context , List<TeamMatch> matches, LeagueTeam team) {
        mMatches = matches;
        mCurrentTeam = team;
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
        holder.itemView.setBackgroundColor(position % 2  == 0 ? Color.rgb(244, 252, 250) : Color.rgb(255,255,255));
        holder.setData(current);
    }

    @Override
    public int getItemCount() {
        return mMatches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public final TextView mDateView;
        public final TextView mOpponentView;
        public final TextView mScore;
        public TeamMatch current;
        private final Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
            mDateView = (TextView) itemView.findViewById(R.id.match_date);
            mOpponentView = (TextView) itemView.findViewById(R.id.match_opponent);
            mScore = (TextView) itemView.findViewById(R.id.match_score);
        }

        public void setData(TeamMatch teamMatch){
            this.current = teamMatch;
            mDateView.setText(new SimpleDateFormat("dd/MM/yyyy").format(teamMatch.getMatchDate()));
            mOpponentView.setText(teamMatch.getOpponent());
            mScore.setText(teamMatch.getScore());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, TeamMatchActivity.class);
            intent.putExtra("match",current);
            intent.putExtra("team",mCurrentTeam);
            context.startActivity(intent);
        }
    }
}
