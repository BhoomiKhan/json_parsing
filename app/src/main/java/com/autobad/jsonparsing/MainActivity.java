package com.autobad.jsonparsing;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    private String ip, type, continent_code, continent_name, country_code, country_name, region_code, region_name, city, zip, latitude, longitude, geoname_id, capital, lang_code, lang_name, native_lang, country_flag, country_flag_emoji, country_flag_emoji_unicode, calling_code, is_eu;
    private TextView mIP, mType, mContinent_code, mContinent_name, mCountry_code, mCountry_name, mRegion_code, mRegion_name, mCity, mZip, mLatitude, mLongitude, mGeoname_id, mCapital, mLang_code, mLang_name, mNative_lang, mCountry_flag, mCountry_flag_emoji, mCountry_flag_emoji_unicode, mCalling_code, mIs_eu;
    String IPaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetwordDetect();
        callAPI();
        init();
    }

    private void init() {
        mIP=findViewById(R.id.ip);
        mType=findViewById(R.id.type);
        mContinent_code=findViewById(R.id.continent_code);
        mContinent_name=findViewById(R.id.continent_name);
        mCountry_code=findViewById(R.id.country_code);
        mCountry_name=findViewById(R.id.country_name);
        mRegion_code=findViewById(R.id.region_code);
        mRegion_name=findViewById(R.id.region_name);
        mCity=findViewById(R.id.city);
        mZip=findViewById(R.id.zip);
        mLatitude=findViewById(R.id.latitude);
        mLongitude=findViewById(R.id.longitude);
        mCountry_flag=findViewById(R.id.country_flag);
        mCountry_flag_emoji=findViewById(R.id.country_flag_emoji);
        mCountry_flag_emoji_unicode=findViewById(R.id.country_flag_emoji_unicode);
        mCalling_code=findViewById(R.id.calling_code);
    }

    //Check the internet connection.
    private void NetwordDetect() {
        boolean WIFI = false;
        boolean MOBILE = false;

        ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = CM.getAllNetworkInfo();

        for (NetworkInfo netInfo : networkInfo) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    WIFI = true;
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    MOBILE = true;
        }

        if (WIFI == true) {
            IPaddress = GetDeviceipWiFiData();
            Log.d("ipaddressis",IPaddress);
        }
        if (MOBILE == true) {
            IPaddress = GetDeviceipMobileData();
            Log.d("ipaddressis",IPaddress);
        }
    }

    public String GetDeviceipMobileData() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface networkinterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = networkinterface.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("Current IP", ex.toString());
        }
        return null;
    }

    public String GetDeviceipWiFiData() {
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        @SuppressWarnings("deprecation")
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }

    private void callAPI() {
        final ProgressDialog loadingdialog;
        loadingdialog = new ProgressDialog(MainActivity.this);
        loadingdialog.setTitle("Sending");
        loadingdialog.setMessage("Please wait..");
        loadingdialog.show();
        Ion.with(MainActivity.this)
                .load("https://mafzal.website/ipaddress/ipstack.php")
                .setBodyParameter("ip_address", "172.58.23.145")
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                try {
                    Log.d("responsefromserver",result.toString());
                    loadingdialog.dismiss();
                    jsonParsing(result);
                }
                catch (Exception ex){
                    Log.d("myexception", ex.toString());
                }
            }
        });
    }

    //how to get jsonObject from jsonArray, like this
    //json.get(0).getAsJsonObject()
    //
    //how to get jsonArray from a jsonObject
    //jsonObject.get("key").getAsJsonArray()
    //
    //simply to get jsonValue use this code
    //json.get("key").getAsString();


    public void jsonParsing(JsonObject json) {
        try {
            ip = json.get("ip").getAsString();
            Log.d("jsonparseresponse", ip);

            type = json.get("type").getAsString();
            Log.d("jsonparseresponse", type);

            continent_code = json.get("continent_code").getAsString();
            Log.d("jsonparseresponse", continent_code);

            continent_name = json.get("continent_name").getAsString();
            Log.d("jsonparseresponse", continent_name);

            country_code = json.get("country_code").getAsString();
            Log.d("jsonparseresponse", country_code);

            country_name = json.get("country_name").getAsString();
            Log.d("jsonparseresponse", country_name);

            region_code = json.get("region_code").getAsString();
            Log.d("jsonparseresponse", region_code);

            region_name = json.get("region_name").getAsString();
            Log.d("jsonparseresponse", region_name);

            city = json.get("city").getAsString();
            Log.d("jsonparseresponse", city);

            zip = json.get("zip").getAsString();
            Log.d("jsonparseresponse", zip);

            latitude = json.get("latitude").getAsString();
            Log.d("jsonparseresponse", latitude);

            longitude = json.get("longitude").getAsString();
            Log.d("jsonparseresponse", longitude);

            //location object
            JsonObject locationObj = json.getAsJsonObject("location");
            geoname_id = locationObj.get("geoname_id").getAsString();
            Log.d("jsonparseresponse", geoname_id);
            capital = locationObj.get("capital").getAsString();
            Log.d("jsonparseresponse", capital);

            //get nested JSON Object for languages
            JsonObject languagesJSONObj = locationObj.get("languages").getAsJsonArray().get(0).getAsJsonObject();
            lang_code = languagesJSONObj.get("code").getAsString();
            Log.d("langcode", lang_code);

            lang_name = languagesJSONObj.get("name").getAsString();
            Log.d("langname", lang_name);

            native_lang = languagesJSONObj.get("native").getAsString();
            Log.d("langnative", native_lang);

            country_flag = locationObj.get("country_flag").getAsString();
            Log.d("jsonparseresponse", country_flag);

            country_flag_emoji = locationObj.get("country_flag_emoji").getAsString();
            Log.d("jsonparseresponse", country_flag_emoji);

            country_flag_emoji_unicode = locationObj.get("country_flag_emoji_unicode").getAsString();
            Log.d("jsonparseresponse", country_flag_emoji_unicode);

            calling_code = locationObj.get("calling_code").getAsString();
            Log.d("jsonparseresponse", calling_code);

            is_eu = locationObj.get("is_eu").getAsString();
            Log.d("jsonparseresponse", is_eu);

            setText();
        } catch (Exception e) {
            Log.d("exception", e.toString());
        }
    }

    private void setText() {
        mIP.setText(ip);
        mType.setText(type);
        mContinent_code.setText(continent_code);
        mContinent_name.setText(continent_name);
        mCountry_code.setText(country_code);
        mCountry_name.setText(country_name);
        mRegion_code.setText(region_code);
        mRegion_name.setText(region_name);
        mCity.setText(city);
        mZip.setText(zip);
        mLatitude.setText(latitude);
        mLongitude.setText(longitude);
        mCountry_flag.setText(country_flag);
        mCountry_flag_emoji.setText(country_flag_emoji);
        mCountry_flag_emoji_unicode.setText(country_flag_emoji_unicode);
        mCalling_code.setText(calling_code);

    }
}