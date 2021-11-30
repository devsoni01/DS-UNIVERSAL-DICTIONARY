package com.universal.dsdictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.universal.dsdictionary.Models.Definitions;
import com.universal.dsdictionary.Models.Meanings;
import com.universal.dsdictionary.R;
import com.universal.dsdictionary.ViewHolders.MeaningViewHolder;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningViewHolder> {
    private Context context;
    protected List<Meanings> meaningsList;

    public MeaningAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningViewHolder(LayoutInflater.from(context).inflate(R.layout.meanings_list_items, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {
        holder.textview_partsOfSpeech.setText("Parts Of Speech" + meaningsList.get(position).getPartOfSpeech());
        holder.recycler_definations.setHasFixedSize(true);
        holder.recycler_definations.setLayoutManager(new GridLayoutManager(context, 1));
        DefinationAdapter definationAdapter = new DefinationAdapter(context, meaningsList.get(position).getDefinitions());
        holder.recycler_definations.setAdapter(definationAdapter);

    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
