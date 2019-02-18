package com.nuaa.wjyyy.markdownreader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import cn.dmrfcoder.markdownreader.MarkdownView;
import cn.dmrfcoder.markdownreader.css.InternalStyleSheet;
import cn.dmrfcoder.markdownreader.css.styles.Github;


public class ReaderActivity extends Activity implements View.OnClickListener {

    private ImageView shareImageView;
    private LinearLayout markLinearLayout;


    private MarkdownView mainMarkdownView;
    private InternalStyleSheet mainStyle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_read);
        mainStyle = new Github();

        initView();


        mainMarkdownView.loadMarkdownFromAsset("What.md");
    }

    @SuppressLint("WrongViewCast")
    private void initView() {

        markLinearLayout = findViewById(R.id.id_mark_alert);
        markLinearLayout.setVisibility(View.GONE);

        MyBean myBean = new MyBean();
        myBean.setHello("Olá");
        myBean.setDiasDaSemana(MyBean.DiasDaSemana.DOMINGO);

        mainMarkdownView = findViewById(R.id.mark_view);
        mainMarkdownView.addStyleSheet(mainStyle);
        mainStyle.addMedia("screen and (min-width: 320px)");
        mainStyle.addRule("h1", "color: green");
        mainStyle.endMedia();
        mainStyle.addMedia("screen and (min-width: 481px)");
        mainStyle.addRule("h1", "color: red");
        mainStyle.endMedia();
        mainStyle.addMedia("screen and (min-width: 641px)");
        mainStyle.addRule("h1", "color: blue");
        mainStyle.endMedia();
        mainStyle.addMedia("screen and (min-width: 961px)");
        mainStyle.addRule("h1", "color: yellow");
        mainStyle.endMedia();
        mainStyle.addMedia("screen and (min-width: 1025px)");
        mainStyle.addRule("h1", "color: gray");
        mainStyle.endMedia();
        mainStyle.addMedia("screen and (min-width: 1281px)");
        mainStyle.addRule("h1", "color: orange");
        mainStyle.endMedia();
        mainMarkdownView.setBean(myBean);


      /*  Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        if (path != null) {

            File file = new File(path);
            mainMarkdownView.loadMarkdownFromFile(file);
        }

        Uri uri = (Uri) intent.getData();
        if (uri != null) {
            mainMarkdownView.loadMarkdownFromUrl(uri.toString());
        }
*/
        shareImageView = findViewById(R.id.id_share);
        shareImageView.setOnClickListener(this);

        findViewById(R.id.id_mark).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_share:
                showShareDialog();
                break;
            case R.id.id_mark:
                markLinearLayout.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public static class MyBean {

        public enum DiasDaSemana {
            DOMINGO,
            SEGUNDA,
            TERCA,
            QUARTA,
            QUINTA,
            SEXTA,
            SABADO
        }

        private String hello;
        private DiasDaSemana diasDaSemana;

        public String getHello() {
            return hello;
        }

        public void setHello(String hello) {
            this.hello = hello;
        }

        public DiasDaSemana getDiasDaSemana() {
            return diasDaSemana;
        }

        public void setDiasDaSemana(DiasDaSemana diasDaSemana) {
            this.diasDaSemana = diasDaSemana;
        }
    }

    private void showShareDialog() {

        View view = LayoutInflater.from(this).inflate(R.layout.share, null);
        final Dialog dialog = new Dialog(this, R.style.Theme_AppCompat);
        dialog.setContentView(view);
        dialog.show();

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.share_iconw:
                        // 分享到微信
                        break;

                    case R.id.share_iconq:
                        // 分享到qq

                        break;

                    case R.id.share_cancel_btn:
                        // 取消
                        break;

                }

                dialog.dismiss();
            }

        };
        ImageView mViewWeixin = (ImageView) view.findViewById(R.id.share_iconw);
        ImageView mViewPengyou = (ImageView) view.findViewById(R.id.share_iconq);
        Button mBtnCancel = (Button) view.findViewById(R.id.share_cancel_btn);
        mViewWeixin.setOnClickListener(listener);
        mViewPengyou.setOnClickListener(listener);
        mBtnCancel.setOnClickListener(listener);

        // 设置相关位置，一定要在 show()之后
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);

    }

}
