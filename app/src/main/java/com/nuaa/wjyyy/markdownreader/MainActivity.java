package com.nuaa.wjyyy.markdownreader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.nuaa.wjyyy.markdownreader.R.id.file;
import static com.nuaa.wjyyy.markdownreader.R.id.user;

public class MainActivity extends Activity {

    private ListView fileListView;
    private FileListViewAdapter fileListViewAdapter;
    private Button chooseFileButton;
    private TextView userButton;
    private TextView fileButton;
    private TextView cloudButton;

    private static final int FILE_SELECT_CODE = 0;
    private static final String TAG = "ChooseFile";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filelist_layout);


        userButton=findViewById(R.id.user);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.user_showinfo);
            }
        });
//        fileButton=findViewById(R.id.file);
//        fileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setContentView(R.layout.filelist_layout);
//            }
//        });
        cloudButton=findViewById(R.id.cloud);
        cloudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.clouds);
            }
        });



//        fileListViewAdapter = new FileListViewAdapter(this);
//
//        fileListView = findViewById(R.id.filelist);
//        fileListView.setAdapter(fileListViewAdapter);
//
//        chooseFileButton = findViewById(R.id.import_file);
//        chooseFileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              //  showFileChooser();
//                Intent intent = new Intent(MainActivity.this, ReaderActivity.class);
//
//               // intent.putExtra("path", p);
//
//                MainActivity.this.startActivity(intent);
//            }
//        });


    }


    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String p = FileUtils.getpath(this, uri);
                    Intent intent = new Intent(MainActivity.this, ReaderActivity.class);

                    intent.putExtra("path", p);

                    MainActivity.this.startActivity(intent);

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
