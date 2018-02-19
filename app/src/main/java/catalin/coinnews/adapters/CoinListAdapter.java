package catalin.coinnews.adapters;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import catalin.coinnews.AppDatabase;
import catalin.coinnews.CoinShowActivity;
import catalin.coinnews.MainActivity;
import catalin.coinnews.R;
import catalin.coinnews.models.Coin;
import catalin.coinnews.models.FavoriteCoin;

/**
 * Created by catalin on 16/12/17.
 */

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Coin> coins;
    private ViewHolder holder;
    private AppDatabase db;
    private String tab;

    public CoinListAdapter(Context mContext, ArrayList<Coin> cryptoCoins, String tabName) {
        context = mContext;
        coins = cryptoCoins;
        db = Room.databaseBuilder(context, AppDatabase.class, "production").allowMainThreadQueries().build();
        tab = tabName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_list, parent ,false);
        holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(coins.get(position));
    }

    @Override
    public long getItemId(int i) {
        return 0; //Don't need this, yet
    }

    @Override
    public int getItemCount() {
       return coins.size();
    }

    // ViewHolder Helper
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameField;
        private TextView priceField;
        private TextView holdingsField;
        private TextView favoriteField;
        public ViewHolder(final View itemView) {
            super(itemView);
            nameField = (TextView) itemView.findViewById(R.id.coinName);
            priceField = (TextView) itemView.findViewById(R.id.price);
            holdingsField = (TextView) itemView.findViewById(R.id.holdings);
            favoriteField = (TextView) itemView.findViewById(R.id.favorite);

            itemView.setOnClickListener(this);
            favoriteField.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NewApi")
                @Override
                public void onClick(View view) {

                    Coin coin = coins.get(getAdapterPosition());
                    Coin favCoin = new Coin(coin.getName(), coin.getSymbol(), coin.getRank(), coin.getPriceUsd(), coin.getPriceBtc());
                    if (db.coinDao().findByName(coin.getName().toString()) != null) {
                        db.coinDao().delete(db.coinDao().findByName(coin.getName()));
                        if (tab.equals("Favorites")) {
                            holder.delete(getAdapterPosition());
                        } else {
                            favoriteField.setBackground(context.getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp, null));
                        }
                    } else {
                        try {db.coinDao().insertAll(favCoin);}
                        catch (SQLiteConstraintException e) {
                            Toast.makeText(context, db.coinDao().findByName(coin.getName()).getName().toString(), Toast.LENGTH_SHORT).show();
                        }
                        favoriteField.setBackground(context.getResources().getDrawable(R.drawable.ic_favorite_black_24dp, null));
                    }
                }
            });
            nameField.setOnClickListener(this);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void bindData(Coin coin) {
            nameField.setText(coin.getName());
            priceField.setText(String.valueOf(coin.getPriceUsd()));

            if (db.coinDao().findByName(coin.getName().toString()) != null && !tab.equals("Favorites")) {
                favoriteField.setBackground(context.getResources().getDrawable(R.drawable.ic_favorite_black_24dp, null));
            } else if (tab.equals("Favorites")) {
                favoriteField.setBackground(context.getResources().getDrawable(R.drawable.ic_cancel_black_24dp, null));
            } else if (tab.equals("Coins")) {
                favoriteField.setBackground(context.getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp, null));
            }
        }

        public void delete(int position) {
            coins.remove(position);
            notifyItemRemoved(position);
        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, CoinShowActivity.class);
            intent.putExtra("Coin", coins.get(getAdapterPosition()));
            context.startActivity(intent);
//            String coinName = coins.get(this.getLayoutPosition()).getName().toLowerCase();
//            intent.putExtra("coinName", coinName);
//            context.startActivity(intent);
//            holder.delete(getAdapterPosition());
//            Toast.makeText(context, "FUCK!", Toast.LENGTH_SHORT).show();


        }

    }
}
