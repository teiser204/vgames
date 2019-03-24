package gr.artibet.vgames.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gr.artibet.vgames.R;
import gr.artibet.vgames.models.Language;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

    private List<Language> languageList;

    // Adapter's view holder
    public static class LanguageViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewDesc;

        public LanguageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDesc = itemView.findViewById(R.id.tvLanguageDesc);
        }
    }

    // Constructor
    public LanguageAdapter(List<Language> languageList){
        this.languageList = languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.language_item, viewGroup, false);
        LanguageViewHolder viewHolder = new LanguageViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder languageViewHolder, int i) {
        Language language = languageList.get(i);
        languageViewHolder.textViewDesc.setText(language.getDesc());
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }
}
