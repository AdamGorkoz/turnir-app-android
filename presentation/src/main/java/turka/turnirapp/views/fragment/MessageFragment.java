package turka.turnirapp.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.models.MessageModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import turka.turnirapp.AndroidApplication;
import turka.turnirapp.R;
import turka.turnirapp.di.di.components.DaggerMessagesComponent;
import turka.turnirapp.mvp.presenters.MessagesListPresenter;
import turka.turnirapp.mvp.views.MessagesListView;
import turka.turnirapp.views.adapter.MessagesAdapter;

public class MessageFragment extends Fragment implements MessagesListView {

    private Context mContext;
    private MessagesAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView noMessagesTextView;
    private RecyclerView messagesList;

    @Inject
    MessagesListPresenter presenter;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AndroidApplication app = (AndroidApplication) getActivity().getApplication();
        DaggerMessagesComponent.builder()
                .applicationComponent(app.getApplicationComponent())
                .build().inject(this);

        View fragmentView = inflater.inflate(R.layout.fragment_message, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshMessages();
            }
        });

        noMessagesTextView = (TextView) fragmentView.findViewById(R.id.no_messages);
        messagesList = (RecyclerView) fragmentView.findViewById(R.id.messages_list);


        setupMessagesView();
        initPresenter();

        return fragmentView;
    }

    private void initPresenter() {
        presenter.attachView(this);
        presenter.onCreate();
    }

    private void setupMessagesView() {

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(mContext);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        messagesList.setLayoutManager(mLinearLayoutManagerVertical);

        messagesList.setItemAnimator(new DefaultItemAnimator());
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

    @Override
    public void updateMessagesList(List<MessageModel> messages) {
        if(mAdapter == null){
            mAdapter = new MessagesAdapter(mContext, messages);
            messagesList.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoadingIndicator() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadingIndicator() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNoMessagesView() {
        messagesList.setVisibility(View.GONE);
        noMessagesTextView.setVisibility(View.VISIBLE );
    }

    @Override
    public void hideNoMessagesView() {
        if(messagesList.getVisibility() != View.VISIBLE){
            messagesList.setVisibility(View.VISIBLE);
            noMessagesTextView.setVisibility(View.INVISIBLE);
        }
    }

}
