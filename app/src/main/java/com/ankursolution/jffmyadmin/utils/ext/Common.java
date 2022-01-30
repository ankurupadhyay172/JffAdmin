package com.ankursolution.jffmyadmin.utils.ext;

import android.util.Log;

import com.ankursolution.jffmyadmin.notification.FCMResponse;
import com.ankursolution.jffmyadmin.notification.FCMSendData;
import com.ankursolution.jffmyadmin.notification.IFCMApi;
import com.ankursolution.jffmyadmin.notification.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Common {

    public static String setPrice(String price)
    {
        return "₹ "+price;
    }

    public static String setTotalPrice(String price,String quantity)
    {
        double total =0;
        try {
            total = Double.parseDouble(price)*Integer.parseInt(quantity);
        }catch (Exception e){e.printStackTrace();}

        return "₹ "+total;

    }



    public static void sendnotificationmethod(String title, String body, CompositeDisposable compositeDisposable, String category) {






        IFCMApi ifcmApi = RetrofitClient.getInstance().create(IFCMApi.class);
        Map<String, String> notiData = new HashMap<>();
        notiData.put("title", title);
        notiData.put("body", body);

        FCMSendData sendData = new FCMSendData();
//        sendData.setTo("/topics/"+school_code+category);

        sendData.setTo("/"+category);
        sendData.setPriority("high");

        sendData.setData(notiData);

        Log.d("mytest","send category");

        compositeDisposable.add(ifcmApi.sendNotification(sendData).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FCMResponse>() {
                    @Override
                    public void accept(FCMResponse fcmResponse) throws Exception {

                        if (fcmResponse.getSuccess() == 1) {
                        } else {
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));


    }

    public static void sendnotificationmethodTopic(String title, String body, CompositeDisposable compositeDisposable, String category) {

        IFCMApi ifcmApi = RetrofitClient.getInstance().create(IFCMApi.class);
        Map<String, String> notiData = new HashMap<>();
        notiData.put("title", title);
        notiData.put("body", body);



        Map<String,String> mynotify = new HashMap<>();
        mynotify.put("title",title);
        mynotify.put("body",body);



        FCMSendData sendData = new FCMSendData();
//        sendData.setTo("/topics/"+school_code+category);

        sendData.setTo("/topics/"+category);
        sendData.setPriority("high");


        sendData.setData(notiData);

        sendData.setNotification(mynotify);
        Log.d("mytest","send category");

        compositeDisposable.add(ifcmApi.sendNotification(sendData).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FCMResponse>() {
                    @Override
                    public void accept(FCMResponse fcmResponse) throws Exception {

                        if (fcmResponse.getSuccess() == 1) {
                        } else {
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));


    }

    }
