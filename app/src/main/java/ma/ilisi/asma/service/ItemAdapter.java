package ma.ilisi.asma.service;


import com.squareup.picasso.Picasso;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import ma.ilisi.asma.R;
import ma.ilisi.asma.model.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;
    // Constructeur pour initialiser l'adaptateur avec une liste d'objets Item

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }
    // Called when RecyclerView needs a new ViewHolder
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_view, parent, false);
        return new ItemViewHolder(view);
    }
    // Appelée pour lier les données à la vue d'un ViewHolder spécifique
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        // Définir les valeurs des TextView avec les données de l'objet Item
        holder.textTitle.setText(item.getName());
        holder.textReleaseDate.setText("Date: " + item.getFirstAirDate());
        holder.textRatings.setText("Rating: " + item.getVoteAverage());
        holder.textDescription.setText(item.getOverview());

        // Charger l'image en utilisant la bibliothèque Picasso
        Picasso.get().load(item.getImageUrl()).placeholder(R.drawable.unknown).into(holder.imageCover);
    }
    // Return the total number of items in the data set
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // Classe statique ItemViewHolder qui étend RecyclerView.ViewHolder
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescription, textReleaseDate, textRatings;
        ImageView imageCover;
        // Classe statique ItemViewHolder qui étend RecyclerView.ViewHolder
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            textDescription = itemView.findViewById(R.id.text_description);
            textReleaseDate = itemView.findViewById(R.id.text_release_date);
            textRatings = itemView.findViewById(R.id.text_ratings);
            imageCover = itemView.findViewById(R.id.image_cover);
        }
    }

}

