package com.example.smartdrive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoriteAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Favorite_item> listItems = new ArrayList<Favorite_item>();

    public FavoriteAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // favorite_item.xml 레이아웃을 inflate해서 참조획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.favorite_item, parent, false);
        }

        // favorite_item.xml 의 참조 획득
        TextView txt_title = (TextView)convertView.findViewById(R.id.txt_title);
        TextView txt_sub = (TextView)convertView.findViewById(R.id.txt_sub);
        Button btn_delete = (Button)convertView.findViewById(R.id.btn_delete);

        Favorite_item listItem = listItems.get(position);

        // 가져온 데이터를 텍스트뷰에 입력
        txt_title.setText(listItem.getTitle());
        txt_sub.setText(listItem.getSub());

        // 리스트 아이템 삭제
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public void addItem(String title, String sub){
        Favorite_item listItem = new Favorite_item();

        listItem.setTitle(title);
        listItem.setSub(sub);

        listItems.add(listItem);
    }
}