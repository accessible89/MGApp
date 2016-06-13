package ru.b2app.mgapp.Items;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.b2app.mgapp.Models.NewsItem;
import ru.b2app.mgapp.R;

/**
 * Created by acces on 13.06.2016.
 */
public class NewsListItemViewAdapter extends BaseAdapter {
    Activity context;
    List<NewsItem> newsItems;

    public NewsListItemViewAdapter(Activity activity, List<NewsItem> newsItems) {
        this.context = activity;
        this.newsItems = newsItems;
    }

    private class ViewHolder {
        TextView dateTextView;
        TextView titleTextView;
        TextView announcementTextView;
    }

    public int getCount() {
        return newsItems.size();
    }

    public Object getItem(int position) {
        return newsItems.get(position);
    }

    public long getItemId(int position) {
        return newsItems.indexOf(getItem(position));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.news_item, null);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) convertView.findViewById(R.id.news_item_title);
            holder.dateTextView = (TextView) convertView.findViewById(R.id.news_item_date);
            holder.announcementTextView = (TextView) convertView.findViewById(R.id.news_item_announcement);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsItem newsItem = (NewsItem) getItem(position);

        holder.titleTextView.setText(newsItem.getTitle());
        holder.announcementTextView.setText(newsItem.getAnnouncement());
        holder.dateTextView.setText(newsItem.getDate());


        return convertView;
    }
}
