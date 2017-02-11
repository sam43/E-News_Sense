package com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.BanglaRssFeeds;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.androidbelieve.drawerwithswipetabs.AppConfig;
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.DrawerAdapter.ProthomAloNewsAdapter;
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.Models.ProthomAloFeedItem;
import com.androidbelieve.drawerwithswipetabs.MainActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by sam43 on 1/28/17.
 */

public class ProthomAloRss extends AsyncTask<Void, Void, Void> {
    Context context;
    String address = "http://www.prothom-alo.com/feed/";
    //MainActivity mainAc = new MainActivity();
    AppConfig appConfig;

    ProgressDialog progressDialog;
    ArrayList<ProthomAloFeedItem> feedItems;
    RecyclerView recyclerView;
    URL url;

    public ProthomAloRss(Context context, RecyclerView recyclerView, AppConfig appConfig) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.appConfig = appConfig;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        ProthomAloNewsAdapter adapter = new ProthomAloNewsAdapter(context, feedItems, appConfig);

        int orientation = context.getResources().getConfiguration().orientation;

        Log.d("Check", String.valueOf(orientation));

   /*     if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.setLayoutManager(new GridLayoutManager(context,3));
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context,2));
            recyclerView.setAdapter(adapter);
        }
*/

        recyclerView.setLayoutManager(new GridLayoutManager(context, appConfig.getCOLOMN()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected Void doInBackground(Void... params) {
        ProcessXml(Getdata());

        return null;
    }

    private void ProcessXml(Document data) {
        if (data != null) {
            feedItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();


            for (int i = 0; i < items.getLength(); i++) {
                Node cureentchild = items.item(i);
                if (cureentchild.getNodeName().equalsIgnoreCase("item")) {
                    ProthomAloFeedItem item = new ProthomAloFeedItem();
                    NodeList itemchilds = cureentchild.getChildNodes();
                    for (int j = 0; j < itemchilds.getLength(); j++) {
                        Node cureent = itemchilds.item(j);
                        if (cureent.getNodeName().equalsIgnoreCase("title")) {
                            item.setTitle(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("image")) {
                            NodeList img = cureent.getChildNodes();
                            for (int k = 0; k < img.getLength(); k++) {
                                Node imgs = img.item(k);

                                if (imgs.getNodeName().equalsIgnoreCase("url")) {
                                    item.setThumbnailUrl(imgs.getTextContent());
                                }
                            }
                        }
                    }
                    feedItems.add(item);
                }
            }
        }
    }

    public Document Getdata() {
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
