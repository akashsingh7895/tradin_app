package app.test.trading_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TopListAdapter extends RecyclerView.Adapter<TopListAdapter.ViewHolder> {
    Context context;
    List<CryptoModal> list;

    public TopListAdapter(Context context, List<CryptoModal> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final CryptoModal model= list.get(position);
        holder.title.setText(model.getName());

        Picasso.get()
                .load("https://cryptobubbles.net/backend/"+model.getImg())
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .into(holder.thumbnail);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,desc,uploadTime;
        ImageView thumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.name);
//            desc = itemView.findViewById(R.id.desc);
//            uploadTime = itemView.findViewById(R.id.date);
        }
    }
}
