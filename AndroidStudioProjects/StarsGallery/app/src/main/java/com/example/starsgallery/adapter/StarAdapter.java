package com.example.starsgallery.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private List<Star> stars;        // liste originale
    private List<Star> starsFilter;  // liste filtrée affichée
    private Context context;

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.starsFilter = new ArrayList<>(stars);
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        final StarViewHolder holder = new StarViewHolder(v);

        // 🔥 Gestion du clic → Popup
        holder.itemView.setOnClickListener(view -> {

            int position = holder.getAdapterPosition();
            Star star = starsFilter.get(position);

            // Charger popup layout
            View popup = LayoutInflater.from(context)
                    .inflate(R.layout.star_edit_item, parent, false);

            ImageView img = popup.findViewById(R.id.img);
            RatingBar ratingBar = popup.findViewById(R.id.ratingBar);

            // ✅ CORRECTION ICI
            img.setImageResource(star.getImg());

            // Mettre note actuelle
            ratingBar.setRating(star.getRating());

            // Création du dialog
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("Notez cette star ⭐")
                    .setMessage("Attribuez une note entre 1 et 5")
                    .setView(popup)

                    .setPositiveButton("Valider", (d, which) -> {
                        float newRating = ratingBar.getRating();

                        // Mise à jour de la star
                        star.setRating(newRating);

                        // Rafraîchir uniquement l'élément
                        notifyItemChanged(position);
                    })

                    .setNegativeButton("Annuler", null)
                    .create();

            dialog.show();
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {

        Star star = starsFilter.get(position);

        holder.name.setText(star.getName());
        holder.rating.setRating(star.getRating());

        Glide.with(context)
                .load(star.getImg())
                .placeholder(R.drawable.star)
                .error(R.drawable.star)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return starsFilter.size();
    }

    // 🔍 FILTRAGE (SearchView)
    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                List<Star> filtered = new ArrayList<>();

                if (charSequence == null || charSequence.length() == 0) {
                    filtered.addAll(stars);
                } else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();

                    for (Star p : stars) {
                        if (p.getName().toLowerCase().startsWith(filterPattern)) {
                            filtered.add(p);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filtered;
                results.count = filtered.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                starsFilter.clear();
                starsFilter.addAll((List<Star>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    // 🔹 ViewHolder
    public static class StarViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView name;
        RatingBar rating;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgStar);
            name = itemView.findViewById(R.id.tvName);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}