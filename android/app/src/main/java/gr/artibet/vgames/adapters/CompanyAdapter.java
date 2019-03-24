package gr.artibet.vgames.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gr.artibet.vgames.R;
import gr.artibet.vgames.models.Company;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {

    private List<Company> companyList;

    // Adapter's view holder
    public static class CompanyViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewDesc;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDesc = itemView.findViewById(R.id.tvCompanyDesc);
        }
    }

    // Constructor
    public CompanyAdapter(List<Company> companyList){

        this.companyList = companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.company_item, viewGroup, false);
        CompanyViewHolder viewHolder = new CompanyViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder companyViewHolder, int i) {
        Company company = companyList.get(i);
        companyViewHolder.textViewDesc.setText(company.getDesc());
    }

    @Override
    public int getItemCount() {

        return companyList.size();
    }
}
