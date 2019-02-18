package com.nuaa.wjyyy.markdownreader;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wjyyy on 2018/11/21.
 */

public class FileListViewAdapter extends BaseAdapter {
    private Context context;

    public FileListViewAdapter(Context context) {
        this.context=context;
        fileItemBean fileItemBean = new fileItemBean("软件测试", "type1");
        fileItemBean fileItemBean1 = new fileItemBean("软件维护", "type2");
        fileItemBean fileItemBean2 = new fileItemBean("软件项目管理", "type3");
        fileItemBean fileItemBean3 = new fileItemBean("软件配置管理", "type4");
        fileItemBean fileItemBean4 = new fileItemBean("软件设计方法", "type5");

        fileItemBeans.add(fileItemBean);
        fileItemBeans.add(fileItemBean1);
        fileItemBeans.add(fileItemBean2);
        fileItemBeans.add(fileItemBean3);
        fileItemBeans.add(fileItemBean4);
    }

    List<fileItemBean> fileItemBeans = new ArrayList<>();


    @Override
    public int getCount() {
        return fileItemBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return fileItemBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view==null){
            view=View.inflate(context,R.layout.file_item,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.filename.setText(fileItemBeans.get(i).filename);

        return view;
    }

    class ViewHolder {
        TextView filename;

        ImageButton buttonNormal ;

        public ViewHolder(View view) {
            filename = view.findViewById(R.id.item_filename);
            buttonNormal = (ImageButton) view.findViewById(R.id.delete);
            buttonNormal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showNormalDialog();
                }
            });
        }
    }


    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        //normalDialog.setIcon(R.drawable.);
        normalDialog.setTitle("删除文件");
        normalDialog.setMessage("是否确认删除该文件?");
        normalDialog.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

}
