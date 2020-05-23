package com.example.pragati;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    private static int clickCounter=0;
    Fragment fragment;
    static List<Chat> textList = new ArrayList<>();
    static List<Chat> texts = new ArrayList<>();
    private static MessageListAdapter mMessageAdapter;
    static String []messages;
    static View fragment_view;
    Context mcontext;
    static RecyclerView recyclerView;
    public static final int SEND=0;
    public static final int RECEIVE=1;
    public Utility(Context context){
        mcontext=context;
    }
    public void getData(){
        //Chat objects added to texts list
        texts.add(new Chat("Hi Jack. What are you doing?",SEND,0));
        texts.add(new Chat("Hi Mary. I'm filling out a job application.",RECEIVE,0));
        texts.add(new Chat("Aren't you finished with school already?",SEND,0));
        texts.add(new Chat("No. I have one more semester",RECEIVE,0));
        texts.add(new Chat("But it would be great to have a job lined up.",RECEIVE,0));
        texts.add(new Chat("How is your day going?",RECEIVE,0));
        texts.add(new Chat("Quite busy. I'm preparing for my presentation tomorrow on our marketing strategy.",SEND,0));
        texts.add(new Chat("You must feel stressed out now.",RECEIVE,0));
        texts.add(new Chat("That's an understatement.",SEND,0));
        texts.add(new Chat("Well then, let me lighten up your mood.Look!",RECEIVE,R.drawable.cat));
        texts.add(new Chat("This is so cute!",SEND,0));
        texts.add(new Chat("I know right! I'm thinking of getting this breed as my cat.",RECEIVE,0));
        texts.add(new Chat("When are you getting this?",SEND,0));
        texts.add(new Chat("I'll get it this christmas",RECEIVE,0));
        texts.add(new Chat("I also wanted to buy a cat",SEND,0));
        texts.add(new Chat("But I'm more of a dog person",SEND,0));
        texts.add(new Chat("Why don't you buy a dog then?",RECEIVE,0));
        texts.add(new Chat("A little too much care involved",SEND,0));
        texts.add(new Chat("Yes, but it's good to have a pet",RECEIVE,0));
        texts.add(new Chat("Weren't you thinking of a breed once?",RECEIVE,0));
        texts.add(new Chat("Yes, Golden retriever",SEND,R.drawable.dog));
        texts.add(new Chat("Oh yes, I remember!",RECEIVE,0));
        texts.add(new Chat("My friend, Arthur, has this dog",RECEIVE,0));
        texts.add(new Chat("His dog is really energetic",RECEIVE,0));
        texts.add(new Chat("Yes, dogs are energetic",SEND,0));
        texts.add(new Chat("Especially German shepherds",SEND,0));
        texts.add(new Chat("",RECEIVE,R.drawable.gs));
        texts.add(new Chat("This is a german shepherd, rt?",RECEIVE,0));
        texts.add(new Chat("Yes it is",SEND,0));
        texts.add(new Chat("These are one of the finest dog breeds",SEND,0));




    }
    public void Clicked_mainActivity(RecyclerView mMessageRecycler, View view) {
        fragment_view=view;
        if(!isNetworkConnected()){
            createFragment();
        }
        else{
            recyclerView=mMessageRecycler;
            textList.add(texts.get(clickCounter));
            mMessageAdapter = new MessageListAdapter(mcontext, textList);
            recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
            recyclerView.setAdapter(mMessageAdapter);
            clickCounter++;
        }

    }
    public void clicked_adapter()
    {

        if(clickCounter<texts.size()){
            if(!isNetworkConnected()){
                createFragment();
            }
            else{
                addItems(texts.get(clickCounter));
                recyclerView.smoothScrollToPosition(mMessageAdapter.getItemCount()-1);
                clickCounter++;
            }

        }
        else{
            Toast.makeText(mcontext,"End of story",Toast.LENGTH_LONG).show();
            clickCounter++;
        }
    }
    public void addItems(Chat chat) {
        textList.add(chat);
        mMessageAdapter.notifyDataSetChanged();
    }
    public void createFragment(){


        fragment = new NoInternetFragment();
        fragment_view.setVisibility(View.VISIBLE);
        if (fragment != null) {
            ((FragmentActivity)mcontext).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

        }


    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm
                = (ConnectivityManager)mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
