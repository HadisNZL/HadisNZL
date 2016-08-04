package com.ubetween.hadisnzl.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ubetween.hadisnzl.R;
import com.ubetween.hadisnzl.model.Subject;
import com.ubetween.hadisnzl.utils.GlideUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author hadis on 16.5.09.
 */
public class SecAdapter extends BaseAdapter {
    private List<Subject> list;
    private Context mContext;
    private LayoutInflater inflater;

    public SecAdapter(List<Subject> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cardview_item_layout, parent, false);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        if (list.size() > 0) {
            vHolder.tvUrl.setText(list.get(position).getTitle());
            vHolder.year.setText("(" + list.get(position).getYear() + ")");
            vHolder.english_name.setText(list.get(position).getOriginal_title());
            vHolder.zhuyan_name.setText(list.get(position).getCasts().get(0).getName());
            vHolder.daoyan_name.setText(list.get(position).getDirectors().get(0).getName());
            int leixing_num = list.get(position).getGenres().size();
            List<String> stringList = list.get(position).getGenres();
            StringBuffer buffer = new StringBuffer();
            for (String ss : stringList) {
                buffer.append(ss + "/");
            }
            String leixing = buffer.substring(0, buffer.length() - 1);
            vHolder.leixing_name.setText(leixing);
            vHolder.pingfen_name.setText(list.get(position).getRating().getAverage() + "分");
            String imgg = list.get(position).getImages().getLarge();
            GlideUtil.loadImgCenterCrop(mContext, imgg, R.color.white, vHolder.imavPic);
        }
        return convertView;
    }


    public static class ViewHolder {
        @Bind(R.id.pic)
        ImageView imavPic;
        @Bind(R.id.name)
        public TextView tvUrl;

        @Bind(R.id.year)
        public TextView year;

        @Bind(R.id.english_name)
        public TextView english_name;

        @Bind(R.id.zhuyan_name)
        public TextView zhuyan_name;//主演

        @Bind(R.id.daoyan_name)
        public TextView daoyan_name;//导演

        @Bind(R.id.leixing_name)
        public TextView leixing_name;//类型

        @Bind(R.id.pingfen_name)
        public TextView pingfen_name;//评分


        public ViewHolder(View rootView) {
            ButterKnife.bind(this, rootView);
        }

    }
}
