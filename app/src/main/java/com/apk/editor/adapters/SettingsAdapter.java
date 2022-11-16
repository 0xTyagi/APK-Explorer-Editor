package com.apk.editor.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.editor.R;
import com.apk.editor.utils.APKEditorUtils;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import in.sunilpaulmathew.sCommon.Utils.sSerializableItems;
import in.sunilpaulmathew.sCommon.Utils.sUtils;

/*
 * Created by APK Explorer & Editor <apkeditor@protonmail.com> on March 31, 2021
 */
public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    private static ClickListener clickListener;

    private static ArrayList<sSerializableItems> data;

    int dd = 0;

    public SettingsAdapter(ArrayList<sSerializableItems> data) {
        SettingsAdapter.data = data;
    }

    @NonNull
    @Override
    public SettingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_about, parent, false);
        Log.e("qqq", viewType+ " ");
        ViewHolder holder = new ViewHolder(rowItem);
        holder.id = dd;
        dd++;
        return holder;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull SettingsAdapter.ViewHolder holder, int position) {
        Log.e("qqq", position + " " + holder.id);
        holder.Title.setText(data.get(position).getTextOne());
        Log.e("qqq", data.get(position).getTextOne());
        if (data.get(position).getTextTwo() != null) {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.Description.setText(data.get(position).getTextTwo());
            Log.e("qqq", data.get(position).getTextTwo());
        } else {
            holder.Description.setVisibility(View.GONE);
            Log.e("qqq", "Text2 is null");
        }
        if (sUtils.isDarkTheme(holder.Title.getContext())) {
            holder.Title.setTextColor(APKEditorUtils.getThemeAccentColor(holder.Title.getContext()));
        }
        if (position != 9 && position != 10) {
            holder.mIcon.setColorFilter(sUtils.isDarkTheme(holder.Title.getContext()) ? Color.WHITE : Color.BLACK);
            Log.e("qqq", "filling");
        } else {
            holder.mIcon.clearColorFilter();
        }
        if (data.get(position).getIcon() != null) {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageDrawable(data.get(position).getIcon());
            Log.e("qqq", data.get(position).getIcon().toString());
        } else {
            holder.mIcon.setVisibility(View.GONE);
            Log.e("qqq", "Icon is null");
        }
        if (Build.VERSION.SDK_INT >= 29 && (position == 5 || position == 6)) {
            holder.Title.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.Description.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            Log.e("qqq", "Strike through");
        }
    }

    @Override
    public int getItemCount() {
        //Log.e("qqq", "size: " + data.size());
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final AppCompatImageButton mIcon;
        private final MaterialTextView Description, Title;
        public int id;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.mIcon = view.findViewById(R.id.icon);
            this.Title = view.findViewById(R.id.title);
            this.Description = view.findViewById(R.id.description);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        SettingsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

}