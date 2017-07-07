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

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Member> memberItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Member> memberItems) {
        this.activity = activity;
        this.memberItems = memberItems;
    }

    @Override
    public int getCount() {
        return memberItems.size();
    }

    @Override
    public Object getItem(int location) {
        return memberItems.get(location);
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
            convertView = inflater.inflate(R.layout.member_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView avatar = (NetworkImageView) convertView
                .findViewById(R.id.avatar);
        TextView title = (TextView) convertView.findViewById(R.id.name);
        TextView reg = (TextView) convertView.findViewById(R.id.reg);
        TextView category = (TextView) convertView.findViewById(R.id.category);

        // getting member data for the row
        Member m = memberItems.get(position);

        // Avatar
        avatar.setImageUrl(m.getAvatar(), imageLoader);

        // Person Name
        title.setText(m.getName());

        // Registration
        reg.setText("Code: " + String.valueOf(m.getReg()));

        // Member Category
        category.setText(m.getCategory());

        // member bounties
       // bounties.setText(String.valueOf(m.getBounties()));

        return convertView;
    }

}