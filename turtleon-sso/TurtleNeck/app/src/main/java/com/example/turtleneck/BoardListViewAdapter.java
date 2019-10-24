package com.example.turtleneck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class BoardListViewAdapter extends BaseAdapter {

    public ArrayList<ListVO> listVO = new ArrayList<ListVO>();

    public BoardListViewAdapter() { }

    @Override
    public int getCount() {
        return listVO.size();
    }

    // 리스트뷰에 데이터 넣어주는 부분
    public View getView(int position, View convertView, ViewGroup parent) {
        // position = ListView의 위치
        // 0부터 시작
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview1, parent, false);
        }

        // 내용을 띄울 텍스트뷰
        TextView Number = (TextView) convertView.findViewById(R.id.number);
        TextView Date = (TextView) convertView.findViewById(R.id.date);
        TextView TItle = (TextView) convertView.findViewById(R.id.title);
        TextView Content = (TextView) convertView.findViewById(R.id.content);
        ImageView DeleteImg = (ImageView) convertView.findViewById(R.id.deleteBtn);

        final ListVO listViewItem = listVO.get(position);

        // 데이터 반영
        Number.setText(listViewItem.getNumber());
        Date.setText(listViewItem.getDate());
        TItle.setText(listViewItem.getTitle());
        Content.setText(listViewItem.getContent());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listVO.get(position);
    }

    // BoardManageActivity
    // 데이터 값 넣어주기
    // 게시글 번호, 제목, 내용, 날짜
    public void BoardManageaddVO(String number, String date, String title, String content) {
        ListVO item = new ListVO();

        item.setNumber(number);
        item.setDate(date);
        item.setTitle(title);
        item.setContent(content);

        listVO.add(item);
    }
}
