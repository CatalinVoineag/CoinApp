package catalin.coinnews.fragments;

import android.accounts.NetworkErrorException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import catalin.coinnews.AppDatabase;
import catalin.coinnews.R;
import catalin.coinnews.adapters.CoinListAdapter;
import catalin.coinnews.models.Coin;
import catalin.coinnews.models.CoinList;
import catalin.coinnews.models.FavoriteCoin;

/**
 * Created by catalin on 10/02/18.
 */

public class CoinListFragment extends android.support.v4.app.Fragment {

    public static final String COIN_LIST = "coins";
    public static final String FAVORITE_LIST = "favorites";

    private Activity mActivity;
    private Context context;
    private int tabId;
    private CoinList list;
    private View view;
    private RecyclerView lv;
    private Coin coins;
    private String tabTag;
    private AppDatabase db;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabTag = getTag();
        if (tabTag.equals("Coins")) {
            list = (catalin.coinnews.models.CoinList) getArguments().getSerializable(COIN_LIST);
//        } else {
//            list = (catalin.coinnews.models.CoinList) getArguments().getSerializable(FAVORITE_LIST);
        }

        tabId = getArguments().getInt("tabId");
        context = getContext();
        mActivity = getActivity();
        setRetainInstance(true);
        db = Room.databaseBuilder(context, AppDatabase.class, "production").allowMainThreadQueries().build();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.coin_list, container, false);
//        loadMore = (Button) view.findViewById(R.id.loadMore);


//        loadMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (recordCounter > schedules.size()) {
//                    if (tab.contains("Picking") && !replenTab) {
//                        pickingPagination++;
//                    } else if ((tab.contains("Picking") && replenTab)){
//                        replenPagination++;
//                    } else if (tab.contains("Putaway")) {
//                        putawayPagination++;
//                    } else if (tab.contains("Transfer")) {
//                        transferPagination++;
//                    }
//                }
//                new LoadMoreData().execute();
//            }
//        });

        return view;
    }


    @Override
    public void onResume(){
        super.onResume();
        if (tabTag.equals("Favorites")) {
            CoinList coinList = new CoinList();
            coinList.setCoins((ArrayList<Coin>) db.coinDao().getAll());
            list = coinList;
        }
        loadListView(list);
    }

    public void loadListView(final CoinList list) {
        this.list = list;

        lv = (RecyclerView) view.findViewById(R.id.coinListRecycle);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        lv.setLayoutManager(mLayoutManager);




        CoinListAdapter adapter = new CoinListAdapter(context, list.getCoins(), tabTag);
        lv.setAdapter(adapter);

//
//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contextOfApplication);
//            recyclerView.setLayoutManager(layoutManager);
//
////            recyclerView.setHasFixedSize(true); ONLY IF THE LIST IS A FIXED SIZED!




//        coins = list.getCoins();
//        final ArrayList<CardView> items = new ArrayList<>();
//
//        for(ScheduleInterface schedule : schedules) {
//            items.add(schedule);
//        }

//        if (loadRecords) {
//            android.support.v4.app.Fragment fragment = getFragmentManager().findFragmentByTag(tab);
//            // Don't need recordCounter if we are on the replen tab
//            // Update recordCounter for each tab.
//            TextView tabTitle = (TextView) ((ScheduleTabsActivity) context).findViewById(tabId);
//            if (!mReplenTab) {
//                if (tab.contains("Picking") && scheduleManager.getPickingCounter() != recordCounter) {
//                    recordCounter = scheduleManager.getPickingCounter();
//                    tabTitle.setText(scheduleManager.getTab1());
//
//                } else if (tab.contains("Putaway") && scheduleManager.getPutawayCounter() != recordCounter) {
//                    tabTitle.setText(scheduleManager.getTab2());
//                    recordCounter = scheduleManager.getPutawayCounter();
//                } else if (tab.contains("Transfer") && scheduleManager.getTransferCounter() != recordCounter) {
//                    tabTitle.setText(scheduleManager.getTab3());
//                    recordCounter = scheduleManager.getTransferCounter();
//
//                }
//            } else if((scheduleManager.getReplenCounter() != recordCounter)) {
//                recordCounter = scheduleManager.getReplenCounter();
//            }
//            // Scroll to the freshly loaded recrods
//            int scrollToPosition = 0;
//            scrollToPosition = scheduleManager.getScrollTo();
//            lv.scrollToPosition(scrollToPosition);
//        }

//        CardViewAdapter adapter = new CardViewAdapter(items);

//        adapter.setOnItemClickListener(new CardViewAdapter.MyClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//
//                final ScheduleInterface scheduleInterface = schedules.get(position);
//
//                if (scheduleInterface.getType().equals("Replen")) {
//                    new replenTask().execute();
//                } else {
//                    new AsyncTask<Object, Object, ArrayList<Schedule>>() {
//
//                        private ProgressDialog progressDialog;
//                        private Exception e;
//
//                        @Override
//                        protected void onPreExecute() {
//                            super.onPreExecute();
//                            lockScreenOrientation();
//                            progressDialog = ProgressDialog.show(context, "Please wait", "Retrieving data...", true);
//                        }
//
//                        @Override
//                        protected ArrayList<Schedule> doInBackground(Object... params) {
//                            ScheduleService ss = new ScheduleServiceImpl();
//                            ArrayList<Schedule> schedules = null;
//                            try {
//                                if (scheduleInterface == null) {
//                                    throw new BadRequestException("There is a problem with the schedule. Please refresh the view");
//                                }
//
//                                if (!scheduleInterface.isGroup()) {
//                                    Schedule schedule = ss.getSchedule(scheduleInterface.getId());
//                                    schedules = new ArrayList<>();
//                                    schedules.add(schedule);
//                                } else {
//                                    ScheduleGroup group = ss.getScheduleGroup(scheduleInterface.getId());
//                                    schedules = group.getSchedules();
//                                }
//                            } catch (BadRequestException | UnauthorizedException | NetworkErrorException | IOException | JSONException | SystemBusyExeception | RecordNotFoundException | InternalServerException | ReservedException | RestrictedException ex) {
//                                System.out.println(ex.getMessage());
//                                ex.printStackTrace();
//                                e = ex;
//                                errorMessage = ex.getMessage();
//                            }
//                            return schedules;
//                        }
//
//                        @Override
//                        protected void onPostExecute(ArrayList<Schedule> schedules) {
//                            super.onPostExecute(schedules);
//                            if (schedules != null) {
//                                if (schedules.size() > 0) {
//                                    Intent intent = new Intent(context, ScheduleItemActivity.class);
//                                    intent.putExtra("schedules", schedules);
//                                    intent.putExtra("reasonCodes", reasonCodes);
//                                    startActivity(intent);
//                                } else {
//                                    Intent intent = new Intent(context, ScheduleTabsActivity.class);
//                                    startActivity(intent);
//                                }
//                            } else {
//                                ErrorDialogFragment.OnErrorDialogInteractionListener listener = null;
//                                if (e instanceof UnauthorizedException) {
//                                    listener = new ErrorDialogFragment.OnErrorDialogInteractionListener() {
//                                        @Override
//                                        public void onPositiveButtonClicked() {
//                                            new LogOffTask() {
//                                                @Override
//                                                protected void onPreExecute() {
//                                                    super.onPreExecute();
//                                                    if (progressDialog == null) {
//                                                        progressDialog = ProgressDialog.show(context, "Logging off", "Please wait...", true);
//                                                    }
//                                                }
//                                                @Override
//                                                protected void onPostExecute(final Boolean success) {
//                                                    if (success) {
//                                                        startActivity(new Intent(context, LoginActivity.class));
//                                                    } else {
//                                                        Toast.makeText(context, "There was a problem logging out", Toast.LENGTH_LONG).show();
//                                                    }
//                                                    if (progressDialog != null && progressDialog.isShowing()) {
//                                                        try {
//                                                            progressDialog.dismiss();
//                                                        } catch (Exception ex) {}
//                                                        progressDialog = null;
//                                                    }
//                                                }
//                                            }.execute();
//                                        }
//
//                                        @Override
//                                        public void onNegativeButtonClicked() {
//                                            // The error Dialog will be dismissed if the user clicks on the cancel button, the code is in the ErrorDialogFragment
//                                        }
//                                    };
//                                } else if (e instanceof NetworkErrorException) {
//                                    errorMessage = "There was a problem with the network.";
//                                    listener = new ErrorDialogFragment.OnErrorDialogInteractionListener() {
//                                        @Override
//                                        public void onPositiveButtonClicked() {
//                                            Intent intent = new Intent(context, MainActivity.class);
//                                            startActivity(intent);
//                                        }
//
//                                        @Override
//                                        public void onNegativeButtonClicked() {
//                                            // The error Dialog will be dismissed if the user clicks on the cancel button, the code is in the ErrorDialogFragment
//                                        }
//                                    };
//                                } else {
//                                    if (TextUtils.isEmpty(errorMessage)) {
//                                        errorMessage = "There was a problem fetching the data.";
//                                    }
//                                    listener = new ErrorDialogFragment.OnErrorDialogInteractionListener() {
//                                        @Override
//                                        public void onPositiveButtonClicked() {
//                                            Intent intent = new Intent(context, ScheduleTabsActivity.class);
//                                            intent.putExtra("Tab", 0);
//                                            startActivity(intent);
//                                        }
//
//                                        @Override
//                                        public void onNegativeButtonClicked() {
//                                            // The error Dialog will be dismissed if the user clicks on the cancel button, the code is in the ErrorDialogFragment
//                                        }
//                                    };
//                                }
//
//                                Bundle args = new Bundle();
//                                args.putString("message", errorMessage);
//                                args.putBoolean("showCancel", false);
//                                args.putString("title", "Error");
//                                ErrorDialogFragment dialog = new ErrorDialogFragment();
//                                dialog.setListener(listener);
//                                dialog.setArguments(args);
//                                dialog.setListener(listener);
//                                dialog.show(getFragmentManager(), "");
//                            }
//
//                            if (progressDialog != null) {
//                                try {
//                                    progressDialog.dismiss();
//                                } catch (Exception ex) {}
//                                progressDialog = null;
//                                if (e == null) {
//                                    unlockScreenOrientation();
//                                }
//                            }
//                        }
//                    }.execute();
//                }
//            }
//        });


//        if (recordCounter > totalSchedules) {
//            loadMore.setVisibility(View.VISIBLE);
//        } else {
//            loadMore.setVisibility(View.GONE);
//        }
//        lv.setAdapter(adapter);
    }



}
