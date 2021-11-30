package com.universal.dsdictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.universal.dsdictionary.Models.Definitions;
import com.universal.dsdictionary.R;
import com.universal.dsdictionary.ViewHolders.DefinationViewHolder;

import java.util.List;

public class DefinationAdapter extends RecyclerView.Adapter<DefinationViewHolder> {
    private Context context;
    private List<Definitions> definitionsList;

    public DefinationAdapter(Context context, List<Definitions> definitionsList) {
        this.context = context;
        this.definitionsList = definitionsList;
    }

    @NonNull
    @Override
    public DefinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinationViewHolder(LayoutInflater.from(context).inflate(R.layout.definations_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefinationViewHolder holder, int position) {
        holder.textview_definition.setText("Definition: " + definitionsList.get(position).getDefinition());
        holder.textview_example.setText("Example: " + definitionsList.get(position).getExample());
        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(definitionsList.get(position).getSynonyms());
        antonyms.append(definitionsList.get(position).getAntonyms());

        holder.textview_synonyms.setText(synonyms);
        holder.textview_antonyms.setText(antonyms);

        holder.textview_synonyms.setSelected(true);
        holder.textview_antonyms.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }
}
