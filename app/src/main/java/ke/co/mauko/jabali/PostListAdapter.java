package ke.co.mauko.jabali;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import ke.co.mauko.jabali.app.AppController;

import java.util.List;

/**
 * Created by Kevin Barassa on 01-Dec-16.
 */

public class PostListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Post> postItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public PostListAdapter(Activity activity, List<Post> postItems) {
        this.activity = activity;
        this.postItems = postItems;
    }

    @Override
    public int getCount() {
        return postItems.size();
    }

    @Override
    public Object getItem(int location) {
        return postItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.post_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView avatar = (NetworkImageView) convertView
                .findViewById(R.id.avatar);
        TextView title = (TextView) convertView.findViewById(R.id.name);
        TextView reg = (TextView) convertView.findViewById(R.id.reg);
        TextView category = (TextView) convertView.findViewById(R.id.category);

        // getting post data for the row
        Post m = postItems.get(position);

        // Avatar
        avatar.setImageUrl(m.getAvatar(), imageLoader);

        // Person Name
        title.setText(m.getName());

        // Registration
        reg.setText("Author: " + String.valueOf(m.getAuthor()));

        // Post Category
        category.setText(m.getCategory());

        // post bounties
       // bounties.setText(String.valueOf(m.getBounties()));

        return convertView;
    }

}