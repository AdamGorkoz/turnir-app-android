package turka.turnirapp.mvp.views;

import com.models.MessageModel;

import java.util.List;

/**
 * Created by turka on 10/30/2016.
 */

public interface MessagesListView extends View {
    void updateMessagesList(List<MessageModel> messages);
    void showLoadingIndicator();
    void hideLoadingIndicator();
    void showNoMessagesView();
    void hideNoMessagesView();
}
