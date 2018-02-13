package catalin.coinnews.managers;

import java.io.Serializable;

import catalin.coinnews.models.CoinList;

/**
 * Created by catalin on 10/02/18.
 */

public class CoinManager implements Serializable {

    private CoinList coinList;

    private String title;
    private String tab1;
    private String tab2;

    private int scrollTo = 0;

    public CoinList getCoinList() {
        return coinList;
    }

    public void setCoinList(CoinList coinList) {
        this.coinList = coinList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTab1() {
        return tab1;
    }

    public void setTab1(String tab1) {
        this.tab1 = tab1;
    }

    public String getTab2() {
        return tab2;
    }

    public void setTab2(String tab2) {
        this.tab2 = tab2;
    }

    public int getScrollTo() {
        return scrollTo;
    }

    public void setScrollTo(int scrollTo) {
        this.scrollTo = scrollTo;
    }
}
