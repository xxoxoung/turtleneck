// 지금은 안쓰는 파일

package com.example.turtleneck;

import android.app.Application;
import android.util.Log;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationController extends Application{
    public final static String TAG = "GGGGGGGGG";
    private static ApplicationController instance;
    public static ApplicationController getInstance(){return instance;}

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
    }

    private NetworkService networkService;
    public NetworkService getNetworkService() {return networkService;}

    private String baseUrl;

    public void buildNetworkService(String ip, int port){
        synchronized (ApplicationController.class){
            if (networkService == null){
                baseUrl = String.format("http://%s:%d/", ip, port);
                Log.i(TAG, baseUrl);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                networkService = retrofit.create(NetworkService.class);
            }
        }
    }

    public void buildNetworkService(String ip){
        synchronized (ApplicationController.class){
            if (networkService == null){
                baseUrl = String.format("http://%s/", ip);
                Log.i(TAG, baseUrl);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                networkService = retrofit.create(NetworkService.class);
            }
        }
    }

    public void buildNetworkService(){
        String ip = "a50c3941.ngrok.io";
        int port =8000;

        synchronized (ApplicationController.class){
            if (networkService == null){
                baseUrl = String.format("http://%s:%d/", ip, port);
                Log.i(TAG, baseUrl);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                networkService = retrofit.create(NetworkService.class);
            }
        }
    }
}
