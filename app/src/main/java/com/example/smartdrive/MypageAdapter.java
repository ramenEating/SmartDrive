package com.example.smartdrive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.ArrayList;
public class MypageAdapter extends BaseAdapter{
    private Context mContext;

    private ArrayList<String> array_mountain;



    private ViewHolder mViewHolder;



    public MypageAdapter(Context mContext, ArrayList<String> array_mountain) {

        this.mContext = mContext;

        this.array_mountain = array_mountain;

    }



    @Override

    public int getCount() {

        return array_mountain.size();

    }



    @Override

    public Object getItem(int position) {

        return array_mountain.get(position);

    }



    @Override

    public long getItemId(int position) {

        return position;

    }



    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // ViewHoldr 패턴

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_mypage_item, parent, false);

            mViewHolder = new ViewHolder(convertView);

            convertView.setTag(mViewHolder);

        } else {

            mViewHolder = (ViewHolder) convertView.getTag();

        }



        // View에 Data 세팅

        mViewHolder.txt_name.setText(array_mountain.get(position));



        return convertView;

    }



    public class ViewHolder {

        private TextView txt_name;



        public ViewHolder(View convertView) {

            txt_name = (TextView) convertView.findViewById(R.id.txt_name);

        }

    }
}
