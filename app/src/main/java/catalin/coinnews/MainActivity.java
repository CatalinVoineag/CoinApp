package catalin.coinnews;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import butterknife.ButterKnife;
import catalin.coinnews.adapters.CoinListAdapter;

import catalin.coinnews.fragments.CoinListFragment;
import catalin.coinnews.managers.CoinManager;
import catalin.coinnews.models.Coin;
import catalin.coinnews.models.CoinList;
import catalin.coinnews.models.FavoriteCoin;
import catalin.coinnews.services.CoinListService;
import catalin.coinnews.services.CoinListServiceImpl;

public class MainActivity extends AppCompatActivity {

    private static Context contextOfApplication;
    private Button getCoin;
    private ProgressDialog progressDialog;
    private ArrayList<Coin> cryptoCoins;
    private RecyclerView recyclerView;

    private FragmentTabHost mTabHost;
    public static int currentTab;
    private CoinManager coinManager;
    private getData task;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextOfApplication = getApplicationContext();
        ButterKnife.bind(this);
        db = Room.databaseBuilder(contextOfApplication, AppDatabase.class, "production").allowMainThreadQueries().build();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (task == null ) {
            task = (getData) new getData().execute();
        }
//        generateTabs();
    }

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    class getData extends AsyncTask<String, Void, ArrayList<Coin>> implements Serializable {

        protected void onPreExecute(){
            super.onPreExecute();
//            if (progressDialog == null) {
//                progressDialog = ProgressDialog.show(MainActivity.this, "Please wait.", "Retrieving data...",true );
//            }
        }

        @Override
        protected ArrayList<Coin> doInBackground(String... strings) {
            CoinListService cls;
            Coin coin = null;
            ArrayList<Coin> coins = new ArrayList<>();

            cls = new CoinListServiceImpl();

            try {
                coinManager = cls.getCoins();
            } catch (IOException | NetworkErrorException |JSONException e) {
                e.printStackTrace();
            }
            return coins;

        }

        protected void onPostExecute(ArrayList<Coin> coins) {
            cryptoCoins = coins;

            if (cryptoCoins != null && !cryptoCoins.isEmpty()) {


            }
            generateTabs();
//            if (dataSource.readCoins().isEmpty()) {
//                for(Coin coin: coins) {
//                    dataSource.create(coin);
//                }
//            }

//            CoinListAdapter adapter = new CoinListAdapter(contextOfApplication, dataSource.readCoins());
//            CoinListAdapter adapter = new CoinListAdapter(contextOfApplication, coins);
//            recyclerView.setAdapter(adapter);
//
//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contextOfApplication);
//            recyclerView.setLayoutManager(layoutManager);
//
////            recyclerView.setHasFixedSize(true); ONLY IF THE LIST IS A FIXED SIZED!


//            if (progressDialog != null) {
//                try {
//                    progressDialog.dismiss();
//                } catch (Exception ex) {}
//                progressDialog = null;
//            }
        }
    }

    private void setupTab(final View view, final String tag, Bundle b, int tabId) {
        View tabview = createTabView(mTabHost.getContext(), tag, tabId);

        TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {return view;}
        });

        mTabHost.addTab(setContent, CoinListFragment.class, b);

    }

    private static View createTabView(final Context context, final String text, int tabId) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        // The Tab id is necessary to update the tab context in the ScheduleListFragment, we find it by tabId.
        tv.setId(tabId);
        tv.setText(text);
        return view;
    }

    private void generateTabs() {

//        TextView title = (TextView) findViewById(R.id.title);
//        title.setText(scheduleManager.getTitle());

//        app.prosku.models.ScheduleList pickingList = scheduleManager.getPickingList();

        if (mTabHost != null) {
            mTabHost.clearAllTabs();
        }

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        try {
            Bundle b = new Bundle();
//            b.putSerializable(ScheduleListFragment.SCHEDULE_LIST, pickingList);
            b.putSerializable(CoinListFragment.COIN_LIST, coinManager.getCoinList());
            setupTab(new TextView(this), "Coins", b, R.id.coinTab);

            Bundle b2 = new Bundle();
            CoinList favCoinList = new CoinList();
            favCoinList.setFavCoins((ArrayList<FavoriteCoin>) db.favoriteCoinDao().getAll());
            b2.putSerializable(CoinListFragment.FAVORITE_LIST, favCoinList);
//            for (FavoriteCoin favCoin : {
////                coinList.add
//            }
            setupTab(new TextView(this), "Favorites", b2, R.id.favoriteTab);

            mTabHost.setCurrentTab(currentTab);

        } catch (Exception e) {
            e.printStackTrace();
        }


//         Set a tab listener to assign the current tab to know on which tab to come when you process a schedule line
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
            System.out.println("LISTENER" + Integer.toString(mTabHost.getCurrentTab()));
            MainActivity.currentTab = mTabHost.getCurrentTab();
            }
        });
    }

//    public static int getScheduleTab(Schedule schedule){
//        String type = schedule.getMovementType();
//        int tabValue = 0;
//        switch (type){
//            case Schedule.PUTAWAY_TYPE:
//                tabValue = 1;
//                break;
//            case Schedule.TRANSFER_TYPE:
//                tabValue = 2;
//                break;
//        }
//        return  tabValue;
//    }
}
