package com.example.downloadFromUrl;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String url="https://raw.githubusercontent.com/rajnishsubedi0/directdownloadlink/main/app-release.apk";
        DownloadManager.Request dwnldRequest=new DownloadManager.Request(Uri.parse(url));
        String title= URLUtil.guessFileName(url,null,null);
        dwnldRequest.setTitle(title);
        dwnldRequest.setDescription("Downloading");
        String cookie= CookieManager.getInstance().getCookie(url);
        dwnldRequest.addRequestHeader("cookie",cookie);
        dwnldRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        dwnldRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);
        DownloadManager downloadManager=(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(dwnldRequest);
        Toast.makeText(this, "DownloadingStarted", Toast.LENGTH_SHORT).show();
    }
}