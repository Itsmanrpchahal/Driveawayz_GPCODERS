package com.driveawayz.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Utility {

    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isConnectingToInternet(Context _context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void relative_snackbar(View relativeLayout, String title, String button_text) {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, title, Snackbar.LENGTH_LONG).setAction(button_text, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        snackbar.show();
    }

    public static String changeDateTimeToDateTime(String time)
    {
        String date = time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy hh:mm a");
        String newFormat = formatter.format(testDate);
        System.out.println(".....Date..."+newFormat);

        return newFormat;
    }




    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return PASSWORD_PATTERN.matches(password);

    }

    //convert image to multipart
    public static MultipartBody.Part sendImageFileToserver(File filesDir, Bitmap bitMap, String image) throws IOException {


        File file = new File(filesDir, image + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData(image, file.getName(), reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), image);

        return body;
    }
}

