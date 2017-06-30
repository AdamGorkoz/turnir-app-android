package turka.turnirapp.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import turka.turnirapp.model.LiveMatch;
import turka.turnirapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LiveMatchesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> mData;
    private LayoutInflater mInflator;

    public LiveMatchesAdapter(Context context , List<LiveMatch> matches) {
        Collections.sort(matches, new Comparator<LiveMatch>() {
            @Override
            public int compare(LiveMatch match1, LiveMatch match2) {
                return match1.getTime().compareTo(match2.getTime());
            }
        });

        Set<String> timeSet = new HashSet<String>();
        for (LiveMatch match : matches) {
            timeSet.add(match.getTime());
        }

        mData = new ArrayList<Object>();

        for(String time : timeSet){
            mData.add(time);
            for(LiveMatch match : matches){
                if(match.getTime().equals(time)){
                    mData.add(match);
                }
            }
        }

        this.mInflator = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        Object current =  mData.get(position);
        if(current instanceof LiveMatch){
            return 1;
        }
        else{
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 1){
            View view = this.mInflator.inflate(R.layout.fragment_livematch_item, parent, false);
            return new LiveMatchViewHolder(view);
        }
        else{
            View view = this.mInflator.inflate(R.layout.fragment_livematch_time_section, parent, false);
            return new LiveMatchSectionViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Object current = mData.get(position);
        if(holder.getItemViewType() == 1){
            ((LiveMatchViewHolder)holder).setData((LiveMatch)current,position);
        }
        else{
            ((LiveMatchSectionViewHolder)holder).setData((String)current);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class LiveMatchViewHolder extends RecyclerView.ViewHolder {

        TextView firstTeamName;
        TextView score;
        TextView time;
        TextView secondTeamName;
        int position;
        LiveMatch current;

        public LiveMatchViewHolder(View view) {
            super(view);
            this.firstTeamName = (TextView) itemView.findViewById(R.id.first_team_name);
            this.secondTeamName = (TextView) itemView.findViewById(R.id.second_team_name);
            this.score = (TextView) itemView.findViewById(R.id.match_score);
            this.time = (TextView) itemView.findViewById(R.id.match_time);
        }

        public void setData(LiveMatch current, int position) {
            this.firstTeamName.setText(current.getFirstTeamName());
            this.secondTeamName.setText(current.getSecondTeamName());
            this.time.setText(current.getTime());
            this.score.setText(current.getFirstTeamGoals() + "-" + current.getSecondTeamGoals());
            this.current = current;
            this.position = position;
        }
    }

    class LiveMatchSectionViewHolder extends RecyclerView.ViewHolder {

        TextView time;

        public LiveMatchSectionViewHolder(View view) {
            super(view);
            this.time = (TextView) itemView.findViewById(R.id.section_time);
        }

        public void setData(String time) {
            this.time.setText(time);
        }
    }
}
