package com.example.jit.lezzaproject;


import android.media.MediaDrm;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

public class ChatActivity extends GroupActivity {

    String email ;
    List<Message>messages;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String date;
    String msg;
    boolean isInetial = true;
    FirebaseFirestore firebaseStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final EditText msgET =findViewById(R.id.msgET);
        final RecyclerView rv =findViewById(R.id.chatList);
        final ChatAdapter chatAdapter =new ChatAdapter(messages);
        rv.setAdapter(chatAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        ImageButton send =findViewById(R.id.msgSendBtn);


        firebaseStore = FirebaseFirestore.getInstance();
        firebaseStore.collection("messages").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (!isInetial) {
                    List<DocumentChange> changes = queryDocumentSnapshots.getDocumentChanges();
                    for (DocumentChange ch : changes) {
                        if (ch.getType().equals(DocumentChange.Type.ADDED)) {
                            Message msg = ch.getDocument().toObject(Message.class);
                            if(! msg.getSender().equals(email) ){
                                messages.add(msg);
                                chatAdapter.notifyDataSetChanged();

                            }
                        }
                    }

                }
            }
        });


        if(getIntent()!= null && getIntent().hasExtra("email")) {

            email = getIntent().getStringExtra("email");
        }

        messages =new ArrayList<>();

        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        date = simpledateformat.format(calander.getTime());

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = msgET.getText().toString();

                CollectionReference reference = firebaseStore.collection("messages");
                Message m = new Message();
                m.setTxt(msg);
                m.setSender(email);
                m.setTime(date);
                Log.d("ttt" ,"time : "+ date);

                messages.add(m);
                chatAdapter.notifyDataSetChanged();
                msgET.setText("");
                rv.scrollToPosition(messages.size()-1);

                Task<DocumentReference> task = reference.add(m);

                task.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "msg added successfully ", Toast.LENGTH_SHORT).show();
                        msgET.getText().clear();
                        isInetial = false;

                    }
                });
            }


        });

    }
}

