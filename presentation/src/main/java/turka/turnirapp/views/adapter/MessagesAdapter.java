package turka.turnirapp.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.models.MessageModel;

import java.text.SimpleDateFormat;
import java.util.List;

import turka.turnirapp.R;
import turka.turnirapp.model.Message;

/**
 * Created by turka on 10/22/2016.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private List<MessageModel> messages;
    private LayoutInflater mInflator;

    public MessagesAdapter(Context context, List<MessageModel> messages){
        this.messages = messages;
        this.mInflator = LayoutInflater.from(context);
    }


    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.mInflator.inflate(R.layout.message_item,parent,false);
        return new MessageViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        MessageModel current = messages.get(position);
        holder.setData(current,position);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView text;
        TextView date;
        int position;
        MessageModel current;

        public MessageViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.message_title);
            this.text = (TextView) itemView.findViewById(R.id.message_text);
            this.date = (TextView) itemView.findViewById(R.id.message_date);
        }

        public void setData(MessageModel current, int position) {
            this.title.setText(current.getTitle());
            this.text.setText(current.getBody());
            this.date.setText(new SimpleDateFormat("dd/MM/yyyy").format(current.getDate()));
            this.current = current;
            this.position = position;
        }
    }

}
