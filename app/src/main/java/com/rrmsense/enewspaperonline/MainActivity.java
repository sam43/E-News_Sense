package com.rrmsense.enewspaperonline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rrmsense.enewspaperonline.DrawerFragments.AboutFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.BusinessNewsFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.EntertainmentFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.Models.SelectNewspaper;
import com.rrmsense.enewspaperonline.DrawerFragments.NewsDetails.BusinessNewsDetails;
import com.rrmsense.enewspaperonline.DrawerFragments.NewsDetails.NewsDetailsFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.OddNewsFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.PoliticsFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.BDNews24Fragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.BanglaNewspaperFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.FinancialExpressFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.IttefaqFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.JugantorFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.KalerKanthoFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.ObserverbdFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.ProthomAloFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.SamakalFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.TheindependentbdFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments.VorerKagojFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.SportsFragment;
import com.rrmsense.enewspaperonline.DrawerFragments.TechnologyFragment;
import com.testfairy.TestFairy;

/**
 * Created by sam43 on 1/28/17.
 */

public class MainActivity extends AppCompatActivity{
    public AppConfig appConfig;
    public int CURRENT_FRAGMENT;
    public boolean doubleBackToExit = false;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    Toolbar toolbar;
    //public Context cxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getAppConfig();

        TestFairy.begin(this, "58be2cfb694001bf6c46ed8c644dfdb936be7b29");

        //int orientation = this.getResources().getConfiguration().orientation;

        //Log.d("Check", String.valueOf(orientation));

        /** setting up spinner on toolbar*/

        toolbar = new Toolbar(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /**
         *Setup the DrawerLayout and NavigationView
         */

             mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
             mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */
            mFragmentManager = getSupportFragmentManager();


        OpenFragments(getCurrentFragment());



             //mFragmentTransaction = mFragmentManager.beginTransaction();
             //mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

             mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                 if (menuItem.getItemId() == R.id.nav_item_bangla) {
                        OpenFragments(SelectNewspaper.nav_item_bangla);
                 }
                if (menuItem.getItemId() == R.id.nav_item_news) {
                    OpenFragments(SelectNewspaper.nav_item_news);

                }
                 if (menuItem.getItemId() == R.id.nav_item_media) {
                     OpenFragments(SelectNewspaper.nav_item_media);
                 }

                 if (menuItem.getItemId() == R.id.nav_item_techs) {
                     OpenFragments(SelectNewspaper.nav_item_techs);
                 }

                 if (menuItem.getItemId() == R.id.nav_item_odd) {
                     OpenFragments(SelectNewspaper.nav_item_odd);
                 }

                 if (menuItem.getItemId() == R.id.nav_item_sports) {
                     OpenFragments(SelectNewspaper.nav_item_sports);

                 }

                 if (menuItem.getItemId() == R.id.recyclerview_entertainment) {
                     OpenFragments(SelectNewspaper.nav_item_bangla);

                 }

                 if (menuItem.getItemId() == R.id.nav_item_politics) {
                     OpenFragments(SelectNewspaper.nav_item_politics);

                 }

/*                 if (menuItem.getItemId() == R.id.nav_item_breaking) {
                     OpenFragments(SelectNewspaper.nav_item_breaking);

                 }*/

                 if (menuItem.getItemId() == R.id.nav_item_about) {
                     OpenFragments(SelectNewspaper.nav_item_about);

                 }

                 return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

                android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
                ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

                mDrawerLayout.setDrawerListener(mDrawerToggle);

                mDrawerToggle.syncState();




    }

    private void getAppConfig(int x) {

        appConfig = new AppConfig();
        appConfig.setWIDTH(Resources.getSystem().getDisplayMetrics().widthPixels);
        //appConfig.setHEIGHT(Resources.getSystem().getDisplayMetrics().heightPixels);
        appConfig.CalculateColumn(x);
        //Log.d("Cxt",String.valueOf(appConfig.getCOLOMN()));
        //appConfig.Image_Setter();
        //Toast.makeText(this,"Clm;"+appConfig.getCOLOMN()+","+Resources.getSystem().getDisplayMetrics().widthPixels,Toast.LENGTH_LONG).show();

    }

    private int getCurrentFragment() {

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int backStackCount = sharedPref.getInt("BACK_STACK_COUNT", 0);
        if(backStackCount==0){
            return SelectNewspaper.nav_item_news;
        }else{
            int i = sharedPref.getInt("FRAGMENT_"+backStackCount, SelectNewspaper.nav_item_news);
        //Log.d("CURRENT_FRAGMENT", String.valueOf(i));
            return i;
        }
    }

    public void setCurrentFragment(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int backStackCount = sharedPref.getInt("BACK_STACK_COUNT", 0);
        if(backStackCount==0){
            editor.putInt("FRAGMENT_"+(backStackCount+1), CURRENT_FRAGMENT);
            editor.putInt("BACK_STACK_COUNT", (backStackCount+1));

        }else{
            int i = sharedPref.getInt("FRAGMENT_"+backStackCount, SelectNewspaper.nav_item_news);
            if(i!=CURRENT_FRAGMENT){
                editor.putInt("FRAGMENT_"+(backStackCount+1), CURRENT_FRAGMENT);
                editor.putInt("BACK_STACK_COUNT", (backStackCount+1));
            }
        }
        editor.apply();
        //Log.d("CURRENT_FRAGMENT", String.valueOf(CURRENT_FRAGMENT));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear().apply();
        //editor.putInt("SELECTED_TAB",0);
        //editor.apply();
        //CURRENT_FRAGMENT = SelectNewspaper.nav_item_news;
        //setCurrentFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //OpenFragments(CURRENT_FRAGMENT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            Intent i = getIntent();
            finish();
            startActivity(i); // for each page

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Log.d("Backpress", String.valueOf(getFragmentManager().getBackStackEntryCount()));
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int backStackCount = sharedPref.getInt("BACK_STACK_COUNT", 0);

        Log.d("BACKSTACK:", String.valueOf(backStackCount));
        for(int i=0;i<=backStackCount;i++){
            Log.d("BACKSTACK:"+i, String.valueOf(sharedPref.getInt("FRAGMENT_"+i,-1)));
        }

        if (backStackCount == 1) {
            //Nothing to do... calling 'doubleBackToExit'
            //getFragmentManager().popBackStack();
        } else if(backStackCount>1){
            //editor.putInt("FRAGMENT_"+backStackCount+1, CURRENT_FRAGMENT);
            editor.putInt("BACK_STACK_COUNT", (backStackCount-1));
            int i = sharedPref.getInt("FRAGMENT_"+(backStackCount-1), SelectNewspaper.nav_item_news);
            OpenFragments(i);
            editor.apply();
            return;

        }
        if(doubleBackToExit) {
            super.onBackPressed();
            editor.clear().apply();
            return;
        }

        this.doubleBackToExit = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExit = false;
            }
        }, 3000);

    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void OpenFragments(int newsPaper){
        String newsPaperLink;
        boolean saveCurrentFragment = true;
        switch (newsPaper){

            case SelectNewspaper.PROTHOM_ALO:
                getAppConfig(400);
                CURRENT_FRAGMENT = SelectNewspaper.PROTHOM_ALO;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                ProthomAloFragment PAFrag = new ProthomAloFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, PAFrag)
                        .commit();
                break;
            case SelectNewspaper.ITTEFAQ:
                getAppConfig(400);
                CURRENT_FRAGMENT = SelectNewspaper.ITTEFAQ;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
/**/
                IttefaqFragment ittefaqFragment = new IttefaqFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, ittefaqFragment)
                        .commit();
                break;
            case SelectNewspaper.AAJKAL:
                CURRENT_FRAGMENT = SelectNewspaper.AAJKAL;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                newsPaperLink ="http://aajkaal.in/";
                setWebviewFragment(newsPaperLink);
                saveCurrentFragment = false;
                break;
            case SelectNewspaper.SAMAKAL:
                CURRENT_FRAGMENT = SelectNewspaper.SAMAKAL;
                getAppConfig(700);
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                SamakalFragment samakalFragment = new SamakalFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, samakalFragment)
                        .commit();
                break;
            case SelectNewspaper.INQILAB:
                CURRENT_FRAGMENT = SelectNewspaper.INQILAB;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                newsPaperLink ="https://www.dailyinqilab.com/";
                setWebviewFragment(newsPaperLink);
                saveCurrentFragment = false;
                break;
            case SelectNewspaper.MANABZAMIN:
                CURRENT_FRAGMENT = SelectNewspaper.MANABZAMIN;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                newsPaperLink ="http://www.mzamin.com/";
                setWebviewFragment(newsPaperLink);
                saveCurrentFragment = false;
                break;
            case SelectNewspaper.JUGANTOR:
                CURRENT_FRAGMENT = SelectNewspaper.JUGANTOR;
                getAppConfig(700);
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                JugantorFragment jugantorFragment = new JugantorFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, jugantorFragment)
                        .commit();
                break;
            case SelectNewspaper.KALER_KANTHA:
                getAppConfig(700);
                CURRENT_FRAGMENT = SelectNewspaper.KALER_KANTHA;
                KalerKanthoFragment kanthoFragment = new KalerKanthoFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, kanthoFragment)
                        .commit();
                break;
            case SelectNewspaper.VORER_KAGOJ:
                getAppConfig(700);
                CURRENT_FRAGMENT = SelectNewspaper.VORER_KAGOJ;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                VorerKagojFragment vorerKagojFragment = new VorerKagojFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, vorerKagojFragment)
                        .commit();
                break;
            case SelectNewspaper.AMADER_SOMOY:
                CURRENT_FRAGMENT = SelectNewspaper.AMADER_SOMOY;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                newsPaperLink ="http://www.amadershomoy.biz/beta/";
                setWebviewFragment(newsPaperLink);
                saveCurrentFragment = false;
                break;
            case SelectNewspaper.INDEPENDENT:
                CURRENT_FRAGMENT = SelectNewspaper.INDEPENDENT;
                getAppConfig(700);
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                TheindependentbdFragment independentbdFrag = new TheindependentbdFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, independentbdFrag)
                        .commit();
                break;
            case SelectNewspaper.NEWAGE:
                CURRENT_FRAGMENT = SelectNewspaper.NEWAGE;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                newsPaperLink ="http://newagebd.net/";
                setWebviewFragment(newsPaperLink);
                saveCurrentFragment = false;
                break;
            case SelectNewspaper.OBSERVER:
                getAppConfig(700);
                CURRENT_FRAGMENT = SelectNewspaper.OBSERVER;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                ObserverbdFragment observerbdFrag = new ObserverbdFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, observerbdFrag)
                        .commit();
                break;
            case SelectNewspaper.NEW_NATION:
                CURRENT_FRAGMENT = SelectNewspaper.NEW_NATION;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                newsPaperLink ="https://thedailynewnation.com/";
                setWebviewFragment(newsPaperLink);
                saveCurrentFragment = false;
                break;
            case SelectNewspaper.BDNEWS_24:
                getAppConfig(700);
                CURRENT_FRAGMENT = SelectNewspaper.BDNEWS_24;
                //Toast.makeText(mContext,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                BDNews24Fragment bdFrag = new BDNews24Fragment();
                       this.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.containerView, bdFrag)
                                .commit();

                break;
            case SelectNewspaper.DHAKA_TRIBUNE:
                CURRENT_FRAGMENT = SelectNewspaper.DHAKA_TRIBUNE;
                newsPaperLink ="http://www.dhakatribune.com/";
                setWebviewFragment(newsPaperLink);
                saveCurrentFragment = false;
            case SelectNewspaper.FINANCIAL_EXPRESS:
                getAppConfig(700);
                CURRENT_FRAGMENT = SelectNewspaper.FINANCIAL_EXPRESS;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                FinancialExpressFragment financialExpressFragment = new FinancialExpressFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, financialExpressFragment)
                        .commit();
                break;
            case SelectNewspaper.DAILY_SUN:
                CURRENT_FRAGMENT = SelectNewspaper.DAILY_SUN;
                //Toast.makeText(this,"PROTHOM ALO", Toast.LENGTH_SHORT).show();
                newsPaperLink ="http://www.daily-sun.com/online/national";
                setWebviewFragment(newsPaperLink);
                saveCurrentFragment = false;
                break;
            case SelectNewspaper.nav_item_bangla:
                getAppConfig(700);
                CURRENT_FRAGMENT = SelectNewspaper.nav_item_bangla;
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView,new BusinessNewsFragment()).commit();

                break;
            case SelectNewspaper.nav_item_breaking:
                getAppConfig(500);
                CURRENT_FRAGMENT = SelectNewspaper.nav_item_breaking;
                FragmentTransaction hfragmentTransaction = mFragmentManager.beginTransaction();
                hfragmentTransaction.replace(R.id.containerView,new BanglaNewspaperFragment()).commit();

                break;
            case SelectNewspaper.nav_item_media:
                getAppConfig(700);
                CURRENT_FRAGMENT = SelectNewspaper.nav_item_media;
                FragmentTransaction afragmentTransaction = mFragmentManager.beginTransaction();
                afragmentTransaction.replace(R.id.containerView,new EntertainmentFragment()).commit();

                break;
            case SelectNewspaper.nav_item_news:
                getAppConfig(800);
                CURRENT_FRAGMENT = SelectNewspaper.nav_item_news;
                FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                xfragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

                break;
            case SelectNewspaper.nav_item_odd:
                getAppConfig(800);
                CURRENT_FRAGMENT = SelectNewspaper.nav_item_odd;
                FragmentTransaction cfragmentTransaction = mFragmentManager.beginTransaction();
                cfragmentTransaction.replace(R.id.containerView,new OddNewsFragment()).commit();

                break;
            case SelectNewspaper.nav_item_politics:
                getAppConfig(700);
                CURRENT_FRAGMENT = SelectNewspaper.nav_item_politics;
                FragmentTransaction ffragmentTransaction = mFragmentManager.beginTransaction();
                ffragmentTransaction.replace(R.id.containerView,new PoliticsFragment()).commit();

                break;
            case SelectNewspaper.nav_item_about:
                getAppConfig(800);
                CURRENT_FRAGMENT = SelectNewspaper.nav_item_about;
                FragmentTransaction jfragmentTransaction = mFragmentManager.beginTransaction();
                jfragmentTransaction.replace(R.id.containerView,new AboutFragment()).commit();
                break;
            case SelectNewspaper.nav_item_sports:
                getAppConfig(800);
                CURRENT_FRAGMENT = SelectNewspaper.nav_item_sports;
                FragmentTransaction dfragmentTransaction = mFragmentManager.beginTransaction();
                dfragmentTransaction.replace(R.id.containerView,new SportsFragment()).commit();

                break;
            case SelectNewspaper.nav_item_techs:
                getAppConfig(700);
                CURRENT_FRAGMENT = SelectNewspaper.nav_item_techs;
                FragmentTransaction bfragmentTransaction = mFragmentManager.beginTransaction();
                bfragmentTransaction.replace(R.id.containerView,new TechnologyFragment()).commit();

                break;
        }
        if(saveCurrentFragment)
            setCurrentFragment();
    }

    private void setWebviewFragment(String newsPaperLink) {

        NewsDetailsFragment nd = new NewsDetailsFragment();
        Bundle b = new Bundle();
        b.putString("Link", newsPaperLink);
        nd.setArguments(b);
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, nd)
                .addToBackStack(null)
                .commit();
    }
}