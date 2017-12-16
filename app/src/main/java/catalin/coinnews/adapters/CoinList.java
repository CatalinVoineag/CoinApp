package catalin.coinnews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import catalin.coinnews.MainActivity;
import catalin.coinnews.R;
import catalin.coinnews.models.Coin;

/**
 * Created by catalin on 16/12/17.
 */

public class CoinList extends RecyclerView.Adapter<CoinList.ViewHolder>{

    private Context context;
    private ArrayList<Coin> coins;
    private ViewHolder holder;

    public CoinList(Context mContext, ArrayList<Coin> cryptoCoins) {
        context = mContext;
        coins = cryptoCoins;
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
        private TextView alertField;
        public ViewHolder(View itemView) {
            super(itemView);
            nameField = (TextView) itemView.findViewById(R.id.coinName);
            priceField = (TextView) itemView.findViewById(R.id.price);
            holdingsField = (TextView) itemView.findViewById(R.id.holdings);
            alertField = (TextView) itemView.findViewById(R.id.alert);

            itemView.setOnClickListener(this);
        }

        public void bindData(Coin coin) {
            nameField.setText(coin.getName());
            priceField.setText(String.valueOf(coin.getPrice_usd()));
            holdingsField.setText(String.valueOf(coin.getHoldings()));
            alertField.setText(coin.getAlert() ? "True" : "False");
        }

        @Override
        public void onClick(View view) {
            String price = String.format("Price of %s is %s$", nameField.getText(), priceField.getText());
            Toast.makeText(context, price, Toast.LENGTH_LONG).show();
        }
    }
}
