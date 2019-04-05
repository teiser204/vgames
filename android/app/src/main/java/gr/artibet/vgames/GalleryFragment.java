package gr.artibet.vgames;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import gr.artibet.vgames.adapters.GalleryAdapter;

public class GalleryFragment extends Fragment {

    // class members
    private List<String> mImageList;

    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // Required empty public constructor
    public GalleryFragment() {
        mImageList = new ArrayList<String>();
    }

    // Set image list
    public void setImageList(List<String> imageList) {
        mImageList = imageList;
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Initialize recycler view
        buildRecyclerView(view);

        return view;
    }

    // Build recycler view
    private void buildRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.galleryRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new GalleryAdapter(getActivity(), this.mImageList);
        mRecyclerView.setAdapter(mAdapter);

        // Set item click listener
        mAdapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String image = mImageList.get(position);

                Dialog dlg = new Dialog(getActivity());
                dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dlg.setContentView(R.layout.dialog_image);

                ImageView iv = dlg.findViewById(R.id.dialog_imageview);

                Glide.with(getActivity())
                        .load(image)
                        .placeholder(R.drawable.ic_image_placeholder)
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .into(iv);

                dlg.show();


//                Glide.with(getActivity())
//                        .load(image)
//                        .into(iv);


                // Open image preview
                //Intent intent = new Intent(getActivity(), GameActivity.class);
                //intent.putExtra("GAME_ID", game.getId());
                //startActivity(intent);

            }
        });
    }

}
