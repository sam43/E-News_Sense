package com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaRssFeeds;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rrmsense.enewspaperonline.AppConfig;
import com.rrmsense.enewspaperonline.TopNews.TopFeedItem;
import com.rrmsense.enewspaperonline.TopNews.TopNewsAdapter;

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

public class BdNews24Rss extends AsyncTask<Void, Void, Void> {
    public ProgressDialog progressDialog;
    Context context;
    String address = "http://bdnews24.com/?widgetName=rssfeed&widgetId=1150&getXmlFeed=true";
    AppConfig appConfig;
    ArrayList<TopFeedItem> feedItems;
    RecyclerView recyclerView;
    URL url;

    public BdNews24Rss(Context context, RecyclerView recyclerView, AppConfig appConfig) {
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

        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
            progressDialog = null;
        }
        //progressDialog.dismiss();
        TopNewsAdapter adapter = new TopNewsAdapter(context, feedItems,appConfig);
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
                    TopFeedItem item = new TopFeedItem();
                    NodeList itemchilds = cureentchild.getChildNodes();
                    for (int j = 0; j < itemchilds.getLength(); j++) {
                        Node cureent = itemchilds.item(j);
                        if (cureent.getNodeName().equalsIgnoreCase("title")) {
                            item.setTitle(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("description")) {
                            item.setDescription(cureent.getTextContent());

                        } else if (cureent.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("image")) {
/*                            String url=cureent.getAttributes().item(0).getTextContent(); //item(index of the attribute)
                            item.setThumbnailUrl(url);*/
                            item.setThumbnailUrl(cureent.getTextContent());
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
