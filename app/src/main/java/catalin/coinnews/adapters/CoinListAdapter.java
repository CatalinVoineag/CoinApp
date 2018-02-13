package catalin.coinnews.adapters;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import catalin.coinnews.AppDatabase;
import catalin.coinnews.R;
import catalin.coinnews.database.DataSource;
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

    public CoinListAdapter(Context mContext, ArrayList<Coin> cryptoCoins) {
        context = mContext;
        coins = cryptoCoins;
        db = Room.databaseBuilder(context, AppDatabase.class, "production").allowMainThreadQueries().build();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_list, parent ,false);
        holder = new ViewHolder(view);
        return holder;
    }

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
        public ViewHolder(View itemView) {
            super(itemView);
            nameField = (TextView) itemView.findViewById(R.id.coinName);
            priceField = (TextView) itemView.findViewById(R.id.price);
            holdingsField = (TextView) itemView.findViewById(R.id.holdings);
            favoriteField = (TextView) itemView.findViewById(R.id.favorite);

            itemView.setOnClickListener(this);
            favoriteField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Coin coin = coins.get(getPosition());
                    FavoriteCoin favCoin = new FavoriteCoin(coin.getName(), coin.getSymbol(), coin.getRank(), coin.getPriceUsd(), coin.getPriceBtc());
                    db.favoriteCoinDao().insertAll(favCoin);
                    FavoriteCoin favCoin2 = db.favoriteCoinDao().getAll().get(0);
                    Toast.makeText(context, favCoin2.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bindData(Coin coin) {
            nameField.setText(coin.getName());
            priceField.setText(String.valueOf(coin.getPriceUsd()));
//            holdingsField.setText(String.valueOf(coin.getHoldings()));

//            favoriteField.setText(coin.getAlert() ? "True" : "False");
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(context, CoinShowActivity.class);
//            String coinName = coins.get(this.getLayoutPosition()).getName().toLowerCase();
//            intent.putExtra("coinName", coinName);
//            context.startActivity(intent);
            Toast.makeText(context, "FUCK!", Toast.LENGTH_SHORT).show();
        }

    }
}
