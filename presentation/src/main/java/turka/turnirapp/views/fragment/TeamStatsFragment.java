package turka.turnirapp.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import turka.turnirapp.R;
import turka.turnirapp.model.LeagueTeam;

public class TeamStatsFragment extends Fragment {

    private static final String TEAM_PARAM = "TEAM_PARAM";

    private LeagueTeam mLeagueTeam;
    private Context mContext;

    public TeamStatsFragment() {
        // Required empty public constructor
    }

    public static TeamStatsFragment newInstance(LeagueTeam leagueTeam) {
        TeamStatsFragment fragment = new TeamStatsFragment();
        Bundle args = new Bundle();
        args.putParcelable(TEAM_PARAM, leagueTeam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLeagueTeam = getArguments().getParcelable(TEAM_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_team_stats, container, false);

        TextView positionTextView = (TextView)fragmentView.findViewById(R.id.team_stats_position);
        positionTextView.setText(String.valueOf(mLeagueTeam.getSpot()));

        TextView pointsTextView = (TextView)fragmentView.findViewById(R.id.team_stats_points);
        pointsTextView.setText(String.valueOf(mLeagueTeam.getPoints()));

        TextView matchesTextView = (TextView)fragmentView.findViewById(R.id.team_stats_matches);
        matchesTextView.setText(String.valueOf(mLeagueTeam.getMatches()));

        TextView winsTextView = (TextView)fragmentView.findViewById(R.id.team_stats_wins);
        winsTextView.setText(String.valueOf(mLeagueTeam.getWins()));

        TextView drawsTextView = (TextView)fragmentView.findViewById(R.id.team_stats_draws);
        drawsTextView.setText(String.valueOf(mLeagueTeam.getDraws()));

        TextView lossesTextView = (TextView)fragmentView.findViewById(R.id.team_stats_losses);
        lossesTextView.setText(String.valueOf(mLeagueTeam.getLosses()));

        TextView goalsTextView = (TextView)fragmentView.findViewById(R.id.team_stats_goals);
        goalsTextView.setText(mLeagueTeam.getGoals());

        TextView diffTextView = (TextView)fragmentView.findViewById(R.id.team_stats_diff);
        diffTextView.setText(String.valueOf(mLeagueTeam.getDiff()));

        return fragmentView;
    }
    

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
