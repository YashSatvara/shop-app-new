package com.example.shopapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Adapter.ChatRVAdapter;
import com.example.shopapp.Adapter.MessageAdapter;
import com.example.shopapp.Model.ChatModal;
import com.example.shopapp.Model.MessageModel;
import com.example.shopapp.Model.MsgModal;
import com.example.shopapp.Webservice.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatFragment extends Fragment {


    //  private MessageAdapter messageAdapter;
    // private List<MessageModel> messageList;
    // private String currentUserId = "YOUR_CURRENT_USER_ID"; // Replace with actual user ID


    private EditText messageInput;
    private Button sendButton;
    private RecyclerView messageRecyclerView;
    private final String BOT_KEY = "bot";
    private final String USER_KEY = "user";
    private ArrayList<ChatModal>chatModalArrayList;
    private ChatRVAdapter chatRVAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        // Initialize views
        messageRecyclerView = view.findViewById(R.id.messagesRecyclerView);
        messageInput = view.findViewById(R.id.messageInput);
        sendButton = view.findViewById(R.id.sendButton);
        chatModalArrayList = new ArrayList<>();
        chatRVAdapter = new ChatRVAdapter(chatModalArrayList,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        messageRecyclerView.setLayoutManager(manager);
        messageRecyclerView.setAdapter(chatRVAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(messageInput.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Please enter your message",Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(messageInput.getText().toString());
                messageInput.setText("");
            }
        });

    /*    messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList, getContext(), currentUserId);

        messageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        messageRecyclerView.setAdapter(messageAdapter);*/

        // Set send button click listener
/*        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageInput.getText().toString().trim();
                if (!messageText.isEmpty()) {
                    sendMessage(currentUserId, messageText);
                } else {
                    Toast.makeText(getContext(), "Message cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        // Handle back press
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = getParentFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                } else {
                    // Navigate to HomeFragment if back stack is empty
                    fragmentManager.beginTransaction()
                            .replace(R.id.linearLayout, new HomeFragment())
                            .commit();
                }
            }
        });

        return view;
    }

    private  void getResponse(String message){
        chatModalArrayList.add(new ChatModal(message,USER_KEY));
        chatRVAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=183591&key=3M0EvEb8UVoOD3Wq&uid=[uid]&msg="+message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MsgModal> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<MsgModal>() {
            @Override
            public void onResponse(Call<MsgModal> call, Response<MsgModal> response) {
                if(response.isSuccessful()){
                    MsgModal modal = response.body();
                    chatModalArrayList.add(new ChatModal(modal.getCnt(),BOT_KEY));
                    chatRVAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {

                Log.e("ChatFragment", "Failed to fetch bot response: " + t.getMessage()); // Log for debugging
                chatModalArrayList.add(new ChatModal("Please revert your question", BOT_KEY));
                chatRVAdapter.notifyDataSetChanged();
                messageRecyclerView.scrollToPosition(chatModalArrayList.size() - 1);
            }
        });
    }

/*    private void sendMessage(String senderId, String messageText) {
        if (senderId != null) {
            MessageModel message = new MessageModel(senderId, messageText);
            messageList.add(message);
            messageAdapter.notifyDataSetChanged();
            messageInput.setText("");
            messageRecyclerView.scrollToPosition(messageList.size() - 1);
        } else {
            Toast.makeText(getContext(), "Sender ID is null", Toast.LENGTH_SHORT).show();
        }
    }*/
}
