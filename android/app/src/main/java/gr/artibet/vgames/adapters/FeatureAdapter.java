package gr.artibet.vgames.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gr.artibet.vgames.R;
import gr.artibet.vgames.models.Feature;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder> {

    private List<Feature> featureList;
    private OnItemClickListener mItemListener;

    // Item click interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemListener = listener;
    }

    // Adapter's view holder
    public static class FeatureViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewDesc;

        public FeatureViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            textViewDesc = itemView.findViewById(R.id.tvFeatureDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null ) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    // Constructor
    public FeatureAdapter(List<Feature> featureList){

        this.featureList = featureList;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feature_item, viewGroup, false);
        FeatureViewHolder viewHolder = new FeatureViewHolder(v, mItemListener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder featureViewHolder, int i) {
        Feature feature = featureList.get(i);
        featureViewHolder.textViewDesc.setText(feature.getDesc() + " (" + feature.getTotalGames() + ")");
    }

    @Override
    public int getItemCount() {

        return featureList.size();
    }
}
