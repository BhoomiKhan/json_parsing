package com.autobad.jsonparsing;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class MainActivity extends AppCompatActivity {

    private String ip, type, continent_code, continent_name, country_code, country_name, region_code, region_name, city, zip, latitude, longitude, geoname_id, capital, lang_code, lang_name, native_lang, country_flag, country_flag_emoji, country_flag_emoji_unicode, calling_code, is_eu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callAPI();
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
                loadingdialog.dismiss();
                jsonParsing(result);
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






            TextView ip, hostname, type, continent_code, continent_name, country_code, country_name, region_code, region_name, city, zip, latitude, geoname_id, code;
            ip = findViewById(R.id.ip);
            hostname = findViewById(R.id.hostname);
            type = findViewById(R.id.type);
            continent_code = findViewById(R.id.continent_code);
            continent_name = findViewById(R.id.continent_name);
            country_code = findViewById(R.id.country_code);
            country_name = findViewById(R.id.country_name);
            region_code = findViewById(R.id.region_code);
            region_name = findViewById(R.id.region_name);
            city = findViewById(R.id.city);
            zip = findViewById(R.id.zip);
            latitude = findViewById(R.id.latitude);
//        geoname_id=findViewById(R.id.geoname_id);
//        code=findViewById(R.id.code);


//            JSONObject reader = new JSONObject(json);
//            for (int i = 0; i < reader.length(); i++) {
//                Log.d("jsonarray", reader.getString("hostname"));
//            }

//            ip.setText(reader.getString("ip"));
//            hostname.setText(reader.getString("hostname"));
//            Log.d("hostname", reader.getString("hostname"));
//            Log.d("hostname", reader.getString("type"));
//            Log.d("hostname", reader.getString("country_code"));
//            type.setText(reader.getString("type"));
//            continent_code.setText(reader.getString("continent_code"));
//            continent_name.setText(reader.getString("continent_name"));
//            country_code.setText(reader.getString("country_code"));
//            country_name.setText(reader.getString("country_name"));
//            region_code.setText(reader.getString("region_code"));
//            region_name.setText(reader.getString("region_name"));
//            city.setText(reader.getString("city"));
//            zip.setText(reader.getString("zip"));
//            latitude.setText(reader.getString("latitude"));

//        geoname_id.setText(reader.getString("geoname_id"));
//        code.setText(reader.getString("code"));

//                JSONObject reader = new JSONObject(json);
//        String ip=reader.getString("ip");
//        Log.d("ipaddress",ip);
//        String hostname=reader.getString("hostname");
//        Toast.makeText(this, hostname, Toast.LENGTH_SHORT).show();
//        String type=reader.getString("type");
//        Toast.makeText(this, type, Toast.LENGTH_SHORT).show();
//        String continent_code=reader.getString("continent_code");
//        Toast.makeText(this, continent_code, Toast.LENGTH_SHORT).show();
//        String continent_name=reader.getString("continent_name");
//        Toast.makeText(this, continent_name, Toast.LENGTH_SHORT).show();
//        String country_code=reader.getString("country_code");
//        Log.d("countrycode",country_code);
//        Toast.makeText(this, country_code, Toast.LENGTH_SHORT).show();
//        String country_name=reader.getString("country_name");
//        Toast.makeText(this, country_name, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("exception", e.toString());
        }
    }
}