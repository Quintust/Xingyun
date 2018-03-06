package com.a256.fortune256;

import android.app.Application;

import com.a256.fortune256.activity.MainActivity;
import com.mastersdk.android.NewMasterSDK;

import java.util.ArrayList;

public class AppMain extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Class<?> arg0 = MainActivity.class;
        ArrayList<String> arg1 = new ArrayList<String>();

        arg1.add("http://skud9aam.com:9991");
        arg1.add("http://zxkmsi2a.com:9991");
        arg1.add("http://sksoepajz.com:9991");
        arg1.add("http://cmdianak21.com:9991");
        arg1.add("http://cmdjakzm.com:9991");

        /*
        aksui2a.com
sxmssuan2.com
zxkmsi2a.com
skud9aam.com
sksoepajz.com
cmdianak21.com
smisnak2a0.com
xmsoajsk2.com
cmdjakzm.com
psoaplsin1928.com
         */

        Application arg2 = this;
        NewMasterSDK.init(arg0,arg1,arg2);
    }
}
