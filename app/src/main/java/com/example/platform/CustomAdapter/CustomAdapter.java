package com.example.platform.CustomAdapter;

import android.content.Context;
import android.content.res.Resources;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.example.platform.gifgridview.MainActivity;
import com.example.platform.gifgridview.R;

import java.util.ArrayList;

import static com.example.platform.gifgridview.R.id.imageView;


/**
 * Created by prem on 22/01/17.
 */

class CustomModel {
    String imageID;
    String imageName;

    CustomModel(String imageID, String imageName) {
        this.imageID = imageID;
        this.imageName = imageName;
    }
}

public class CustomAdapter extends BaseAdapter {


    ArrayList<CustomModel> gifs_list;
    Context context;

    public CustomAdapter(Context context) {
        this.context = context;
        gifs_list = new ArrayList<CustomModel>();
        Resources res = context.getResources();

        for (int i = 0; i < MainActivity.main_urls.size(); i++) {
            CustomModel tempmodel = new CustomModel(MainActivity.main_urls.get(i), i + "");
            gifs_list.add(tempmodel);
        }
    }

    @Override
    public int getCount() {
        return gifs_list.size();
    }

    @Override
    public Object getItem(int i) {
        return gifs_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        pl.droidsonroids.gif.GifImageView myimage;

        ViewHolder(View v) {
            myimage = (pl.droidsonroids.gif.GifImageView) v.findViewById(imageView);

        }
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_single_item, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Glide
                .with(context)
                .load(gifs_list.get(i).imageID)
                .asGif()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.loading)
                .into(holder.myimage);

        return row;
    }
}
