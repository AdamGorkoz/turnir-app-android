package turka.turnirapp.views.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.models.LeagueTeamModel;

import java.util.List;

import turka.turnirapp.R;
import turka.turnirapp.TeamActivity;
import turka.turnirapp.model.LeagueTeam;
import turka.turnirapp.views.fragment.MessageFragment;

/**
 * Created by turka on 10/23/2016.
 */

public class LeagueAdapter extends RecyclerView.Adapter<LeagueAdapter.LeagueTeamViewHolder> {
    private List<LeagueTeam> leagueTeams;
    private LayoutInflater mInflator;

    public LeagueAdapter(Context context, List<LeagueTeam> leagueTeams){
        this.leagueTeams = leagueTeams;
        this.mInflator = LayoutInflater.from(context);
    }

    @Override
    public LeagueAdapter.LeagueTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.mInflator.inflate(R.layout.leagueteam_item,parent,false);
        return new LeagueAdapter.LeagueTeamViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return leagueTeams.size();
    }

    @Override
    public void onBindViewHolder(LeagueAdapter.LeagueTeamViewHolder holder, int position) {
        LeagueTeam current = leagueTeams.get(position);
        holder.itemView.setBackgroundColor(position % 2  == 0 ? Color.rgb(245,245,245) : Color.rgb(250,250,250));
        holder.setData(current,position);
    }

    class LeagueTeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView spot;
        TextView name;
        TextView points;
        TextView matches;
        TextView diff;
        int position;
        LeagueTeam current;
        private final Context context;

        public LeagueTeamViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
            this.name = (TextView) itemView.findViewById(R.id.league_team_name);
            this.spot = (TextView) itemView.findViewById(R.id.league_team_spot);
            this.points = (TextView) itemView.findViewById(R.id.league_team_points);
            this.matches = (TextView) itemView.findViewById(R.id.league_team_matches);
            this.diff = (TextView) itemView.findViewById(R.id.league_team_diff);
        }

        public void setData(LeagueTeam current, int position) {
            this.name.setText(current.getName());
            this.spot.setText(String.valueOf(position + 1));
            this.points.setText(String.valueOf(current.getPoints()));
            this.matches.setText(String.valueOf(current.getMatches()));
            this.diff.setText(String.valueOf(current.getDiff()));
            this.current = current;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            LeagueTeam leagueTeam = leagueTeams.get(getAdapterPosition());
            leagueTeam.setSpot(getAdapterPosition() + 1);
            Intent intent = new Intent(context, TeamActivity.class);
            intent.putExtra("team",leagueTeam);
            context.startActivity(intent);
        }
    }
}
