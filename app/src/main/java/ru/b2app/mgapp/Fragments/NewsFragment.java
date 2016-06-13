package ru.b2app.mgapp.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ru.b2app.mgapp.Activities.MainActivity;
import ru.b2app.mgapp.Activities.SingleNewsActivity;
import ru.b2app.mgapp.Items.NewsListItemViewAdapter;
import ru.b2app.mgapp.Models.DataManager;
import ru.b2app.mgapp.Models.NewsItem;
import ru.b2app.mgapp.R;
import ru.b2app.mgapp.Utils.JsonParser;

/**
 * Created by acces on 13.06.2016.
 */
public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    NewsListItemViewAdapter newsListItemViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.news_fragment, container, false);

        listView = (ListView) v.findViewById(R.id.news_list);
        listView.setOnItemClickListener(this);

        new AsyncTaskParseJson(getActivity()).execute();
        return v;
    }

    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), SingleNewsActivity.class);
        startActivity(intent);
    }

    public class AsyncTaskParseJson extends AsyncTask<String, Void, List<NewsItem>> {

        private Activity context;

        public AsyncTaskParseJson(Activity context) {
            this.context = context;
        }

        final String TAG = "AsyncTaskParseJson.java";

        // set your json string url here
        String yourJsonStringUrl = "http://isya.oml.ru/news";

        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {}

        @Override
        protected List<NewsItem> doInBackground(String... arg0) {

            List<NewsItem> newsItems = new ArrayList<NewsItem>();

            try {

                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);

                // get the array of users
                dataJsonArr = json.getJSONArray("news");

                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {

                    JSONObject c = dataJsonArr.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString("id");
                    String title = c.getString("title");
                    String date = c.getString("date");
                    String announcement = c.getString("announcement");

                    NewsItem newsItem = new NewsItem();
                    newsItem.setId(id);
                    newsItem.setTitle(title);
                    newsItem.setAnnouncement(announcement);
                    newsItem.setDate(date);
                    newsItems.add(newsItem);

                    // show the values in our logcat
                    Log.e(TAG, "id: " + id
                            + ", title: " + title
                            + ", announcement: " + announcement
                            + ", date: " + date);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return newsItems;
        }

        @Override
        protected void onPostExecute(List<NewsItem> newsItems) {
            newsListItemViewAdapter = new NewsListItemViewAdapter(context, newsItems);
            DataManager.getInstance().setNewsItems(newsItems);
            listView.setAdapter(newsListItemViewAdapter);

        }
    }

}
