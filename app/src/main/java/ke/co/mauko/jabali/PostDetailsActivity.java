package ke.co.mauko.jabali;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        //Init transferred data
        Bundle extras = getIntent().getExtras();

        //Display only if not null
        if(extras != null)

        collapsingToolbar.setTitle(extras.getString("EXTRA_NAME"));
        loadBackdrop();

        //Bio section
        WebView bio = (WebView) findViewById(R.id.webViewbio);
        bio.loadData(extras.getString("EXTRA_BIO"),"text/html",null);

        //Category section
        TextView category = (TextView) findViewById(R.id.category);
        category.setText(extras.getString("EXTRA_CAT"));


        //Creation Section
        TextView created = (TextView) findViewById(R.id.created);
        created.setText(extras.getString("EXTRA_CREATED"));


    }


    private void loadBackdrop() {
        //Load backdrop as clicked image
        Bundle extras = getIntent().getExtras();
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(extras.getString("EXTRA_AVATAR")).centerCrop().into(imageView);
    }

}
