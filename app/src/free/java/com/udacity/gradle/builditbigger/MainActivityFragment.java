package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private String TAG = MainActivityFragment.class.getCanonicalName();

    @BindView(R.id.progressbar)
    ProgressBar progressBar = null;
    @BindView(R.id.joke_button)
    Button joke_button;
    PublisherInterstitialAd publisherInterstitialAd = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Set up interstitial
        publisherInterstitialAd = new PublisherInterstitialAd(getContext());
        publisherInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        publisherInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                progressBar.setVisibility(View.VISIBLE);
                getNewJoke();
                newInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.i(TAG, getString(R.string.ad_failed_to_load));
                newInterstitial();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }
        });

        newInterstitial();

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,root);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        joke_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (publisherInterstitialAd.isLoaded()) {
                    progressBar.setVisibility(View.VISIBLE);
                    publisherInterstitialAd.show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    getNewJoke();
                }

            }
        });

        progressBar.setVisibility(View.GONE);

        return root;
    }

    public void getNewJoke(){
        new JokeAsyncTask().execute(getContext());

    }

    private void newInterstitial(){
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice("EA27D37DF5448BF42AA5F7A6D4F11A9B")
                .build();
        publisherInterstitialAd.loadAd(adRequest);
    }
}
