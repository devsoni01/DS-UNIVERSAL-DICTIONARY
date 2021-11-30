package com.universal.dsdictionary.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.universal.dsdictionary.R;

public class MeaningViewHolder extends RecyclerView.ViewHolder {
   public TextView textview_partsOfSpeech;
   public RecyclerView recycler_definations;

    public MeaningViewHolder(@NonNull View itemView) {
        super(itemView);
        textview_partsOfSpeech = itemView.findViewById(R.id.textview_partsOfSpeech);
        recycler_definations = itemView.findViewById(R.id.recycler_definations);
    }
}
