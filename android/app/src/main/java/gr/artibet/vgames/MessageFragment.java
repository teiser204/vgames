package gr.artibet.vgames;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MessageFragment extends Fragment {

    private String mMessage;
    private TextView mTvMessage = null;


    public MessageFragment() {
        // Required empty public constructor
    }

    public void setMessage(String message) {
        mMessage = message;
        if (mTvMessage != null) {
            mTvMessage.setText(mMessage);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_message, container, false);
        mTvMessage = view.findViewById(R.id.tvMessage);
        mTvMessage.setText(mMessage);

        return view;

    }

}
