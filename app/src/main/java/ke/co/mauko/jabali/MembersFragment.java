package ke.co.mauko.jabali;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import ke.co.mauko.jabali.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MembersFragment extends Fragment {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://sproos.mauko.co.ke/api/user?view=all";
    private ProgressDialog pDialog;
    private List<Member> memberList = new ArrayList<Member>();
    private ListView listView;
    private CustomListAdapter adapter;

    public MembersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_members,null);
        //TextView bt = (TextView) rootView.findViewById(R.id.bounties_text);
        listView = (ListView) rootView.findViewById(R.id.list);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new CustomListAdapter(getActivity(), memberList);
        listView.setAdapter(adapter);
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Fetching Users...");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Member member = new Member();
                                member.setName(obj.getString("h_alias"));
                                member.setAvatar(obj.getString("h_avatar"));
                                member.setReg(obj.getString("h_code"));
                                member.setEmail(obj.getString("h_email"));
                                member.setGender(obj.getString("h_gender"));
                                member.setStatus(obj.getString("h_status"));
                                member.setBio(obj.getString("h_description"));
                                member.setCreatedAt(obj.getString("h_created"));
                                member.setWeb(obj.getString("h_link"));

                                // Genre is json array
//                                JSONArray genreArry = obj.getJSONArray("genre");
//                                ArrayList<String> genre = new ArrayList<String>();
//                                for (int j = 0; j < genreArry.length(); j++) {
//                                    genre.add((String) genreArry.get(j));
//                                }

                                member.setCategory(obj.getString("h_type"));

                                //member.setCategory("category");

                                // adding member to members array
                                memberList.add(member);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);

        //Opens Members detail activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Name
                String h_alias  = (memberList.get(position)).getName();
                //Bio
                String h_description = (memberList.get(position)).getBio();
                //Avatar
                String h_avatar = memberList.get(position).getAvatar();
                //Category
                String h_type = memberList.get(position).getCategory();
                //Email
                String h_email = memberList.get(position).getEmail();
                //Gender
                String h_gender = memberList.get(position).getGender();
                //Website
                String h_link = memberList.get(position).getWeb();
                //Registration
                String h_code = memberList.get(position).getReg();
                //Creation date
                String h_created = memberList.get(position).getCreatedAt();
//                //Bounty points
//                int bounties = memberList.get(position).getBounties();


                //Soft transfer
                Intent intent = new Intent(getActivity(), MemberDetailsActivity.class);
                intent.putExtra("EXTRA_NAME", h_alias);
                intent.putExtra("EXTRA_EMAIL", h_email);
                intent.putExtra("EXTRA_BIO", h_description);
                intent.putExtra("EXTRA_AVATAR", h_avatar);
                intent.putExtra("EXTRA_CAT", h_type);
                intent.putExtra("EXTRA_GENDER",h_gender);
                intent.putExtra("EXTRA_WEB",h_link);
                intent.putExtra("EXTRA_REG",h_code);
                intent.putExtra("EXTRA_CREATED",h_created);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
