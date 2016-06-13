package ru.b2app.mgapp.Models;

import java.util.List;

/**
 * Created by acces on 13.06.2016.
 */
public class DataManager {
    private static DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
    }

    private List<NewsItem> newsItems;

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }


}
