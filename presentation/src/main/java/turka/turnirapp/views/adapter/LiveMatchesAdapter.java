package turka.turnirapp.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import turka.turnirapp.model.LiveMatch;
import turka.turnirapp.R;
import turka.turnirapp.utils.MatchTimerFormatter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LiveMatchesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> mData;
    private LayoutInflater mInflator;

    public LiveMatchesAdapter(Context context , List<Object> data) {
        this.mData = data;
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
            final LiveMatchViewHolder liveMatchViewHolder = (LiveMatchViewHolder)holder;
            liveMatchViewHolder.setData((LiveMatch)current,position);
            if(liveMatchViewHolder.timerSubscription != null && !liveMatchViewHolder.timerSubscription.isUnsubscribed()){
                liveMatchViewHolder.timerSubscription.unsubscribe();
                liveMatchViewHolder.timerSubscription = null;
            }

            liveMatchViewHolder.timerSubscription = Observable.interval(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            if(liveMatchViewHolder.current.getMatchStatus() == 1){
                                LiveMatch match = liveMatchViewHolder.current;
                                String timerString = MatchTimerFormatter.getFormattedMatchTimer(match.getSecondHalf() != null && match.getSecondHalf() ? match.getSecondHalfStartTime() :  match.getStartTime() , match.getSecondHalf());
                                liveMatchViewHolder.time.setText(timerString);
                            }
                        }

            });
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

        public Subscription timerSubscription;
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

            switch(current.getMatchStatus()){
                case 1:
                    //this.time.setVisibility(View.GONE);
                    break;
                case 2:
                    this.time.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    this.time.setText("H.T");
                    break;
                case 4:
                    this.time.setText("F.T");
                    break;
            }

            this.score.setText(current.getFirstTeamGoals() + "-" + current.getSecondTeamGoals());
            this.current = current;
            this.position = position;
        }

        public void setTimerSubscription(Subscription timerSubscription) {
            this.timerSubscription = timerSubscription;
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
