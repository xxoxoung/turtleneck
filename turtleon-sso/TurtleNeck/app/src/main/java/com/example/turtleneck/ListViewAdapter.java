package com.example.turtleneck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    public ArrayList<ListVO> listVO = new ArrayList<ListVO>();

    public ListViewAdapter() { }

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
            convertView = inflater.inflate(R.layout.custom_listview, parent, false);
        }

        TextView Number = (TextView) convertView.findViewById(R.id.number);
        TextView Date = (TextView) convertView.findViewById(R.id.date);
        TextView User = (TextView) convertView.findViewById(R.id.user);
        TextView TItle = (TextView) convertView.findViewById(R.id.title);
        TextView Content = (TextView) convertView.findViewById(R.id.content);

        ListVO listViewItem = listVO.get(position);

        // 데이터 반영
        Number.setText(listViewItem.getNumber());
        Date.setText(listViewItem.getDate());
        User.setText(listViewItem.getUser());
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

    // MainActivity
    // 데이터 값 넣어주기
    // 게시글 번호, 날짜, 작성자(user), 제목, 내용
    public void MainaddVO(String number, String date, String user, String title, String content) {
        ListVO item = new ListVO();

        item.setNumber(number);
        item.setDate(date);
        item.setUser(user);
        item.setTitle(title);
        item.setContent(content);

        listVO.add(item);
    }
}
