package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.hilbing.androidlibraryjoker.DisplayActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;



import java.io.IOException;

public class JokeAsyncTask extends AsyncTask<Context, Void, String> {
    private static final String TAG = JokeAsyncTask.class.getCanonicalName();
    private Context context;
    private static MyApi myApi = null;
    String mResult;
    private ProgressBar mProgressBar;

    public JokeAsyncTask(Context context, ProgressBar mProgressBar) {
        this.context = context;
        this.mProgressBar = mProgressBar;
    }

    @Override
    protected String doInBackground(Context... params) {
        if (myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(new NetHttpTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApi = builder.build();
        }

        context = params[0];

        try {
            return myApi.printJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onPostExecute(String result) {
        mResult = result;
        startDisplayActivity();
        mProgressBar.setVisibility(View.GONE);

    }

    public void startDisplayActivity(){
        Intent intent = new Intent(context, DisplayActivity.class);
        intent.putExtra(context.getString(R.string.envelope), mResult);
        context.startActivity(intent);
    }
}
