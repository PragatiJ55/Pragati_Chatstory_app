package com.example.pragati;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NoInternetFragment extends Fragment {
    Button retry;
    OnCallbackReceived mCallback;
    TextView tv;
    RelativeLayout v;

    public NoInternetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_no_internet, container, false);
        v=rootView.findViewById(R.id.no_internet_background);
        tv=rootView.findViewById(R.id.nointernettxt);
        retry=rootView.findViewById(R.id.retry);


        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected()){
                    mCallback.Update();
                }
                else{
                    Toast.makeText(getActivity(),"You're still offline",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnCallbackReceived) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement TextClicked");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback=null;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public interface OnCallbackReceived {
        public void Update();
    }



}
