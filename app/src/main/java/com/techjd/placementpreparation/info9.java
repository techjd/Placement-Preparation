package com.techjd.placementpreparation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class info9 extends AppCompatActivity {
    PDFView pdfView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info9);

        pdfView = findViewById(R.id.wvinfo9);
        progressBar = findViewById(R.id.pbinfo9);

        new RetrievePDFStream().execute("https://firebasestorage.googleapis.com/v0/b/placement-preparation.appspot.com/o/Infosys%2FCopy%20of%20Infosys%20Online%20Test.pdf?alt=media&token=6e17fdf8-d427-45cc-8fc0-94595741a500");
    }
    class RetrievePDFStream extends AsyncTask<String,Void, InputStream> implements OnLoadCompleteListener
    {




        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if (urlConnection.getResponseCode() == 200)
                {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e)
            {
                return null;
            }
            return inputStream;
        }



        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).swipeHorizontal(false).onLoad(this).load();
        }


        @Override
        public void loadComplete(int nbPages) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
