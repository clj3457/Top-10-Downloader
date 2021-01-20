package com.craigjackson.top10downloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<FeedEntry> applications;

    public FeedAdapter(Context context, int resource, List<FeedEntry> applications) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvArtist = (TextView) convertView.findViewById(R.id.tvArtist);
        TextView tvSummary = (TextView) convertView.findViewById(R.id.tvSummary);
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView tvReleaseDate = (TextView) convertView.findViewById(R.id.tvReleaseDate);

        FeedEntry currentApp = applications.get(position);

        viewHolder.tvName.setText(currentApp.getName());
        viewHolder.tvArtist.setText(currentApp.getArtist());
        viewHolder.tvSummary.setText(currentApp.getSummary());
//        Log.d(TAG, "getView: " + currentApp.getImageURL().toString());
        Picasso.get().load(currentApp.getImageURL()).into(viewHolder.ivImage);
        String releaseDate = "Release Date: " + currentApp.getReleaseDate();
//        viewHolder.tvReleaseDate.setText(currentApp.getReleaseDate());
        viewHolder.tvReleaseDate.setText(releaseDate);

        return convertView;
    }

    private class ViewHolder {
        final TextView tvName;
        final TextView tvArtist;
        final TextView tvSummary;
        final ImageView ivImage;
        final TextView tvReleaseDate;

        ViewHolder(View v) {
            this.tvName = (TextView) v.findViewById(R.id.tvName);
            this.tvArtist = (TextView) v.findViewById(R.id.tvArtist);
            this.tvSummary = (TextView) v.findViewById(R.id.tvSummary);
            this.ivImage = (ImageView) v.findViewById(R.id.ivImage);
            this.tvReleaseDate = (TextView) v.findViewById(R.id.tvReleaseDate);
        }
    }
}
