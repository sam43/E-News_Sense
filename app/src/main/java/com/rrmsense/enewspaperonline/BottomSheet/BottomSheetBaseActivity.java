package com.rrmsense.enewspaperonline.BottomSheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;

import com.rrmsense.enewspaperonline.R;

import ch.tutti.android.bottomsheet.BottomSheetChooserActivity;

public class BottomSheetBaseActivity extends BottomSheetChooserActivity {

    String link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();

        Bundle bundle = getIntent().getExtras();
        link = bundle.getString("Link","");

        startActivity(BottomSheetChooserActivity.create(this)
                .forIntent(getShareIntent())
                .title("Share with...")
                .icon(R.drawable.ic_menu_share)
                .history(false)
                .priority("com.whatsapp", "com.facebook.katana", "com.facebook.orca",
                        "com.google.android.gm", "com.google.android.talk",
                        "com.google.android.apps.plus")
                .getIntent());
    }


    public Intent getShareIntent() {
        return ShareCompat.IntentBuilder.from(this)
                .setText(link)
                .setType("text/plain")
                .getIntent();
    }

}
