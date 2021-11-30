package com.universal.dsdictionary.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.universal.dsdictionary.R;

public class PhoneticsViewHolder extends RecyclerView.ViewHolder {
    public TextView textview_phonetic;
    public ImageButton imagebutton_audio;
    public PhoneticsViewHolder(@NonNull View itemView) {
        super(itemView);

        textview_phonetic = itemView.findViewById(R.id.textview_phonetic);
        imagebutton_audio = itemView.findViewById(R.id.imagebutton_audio);
    }
}
