package kr.co.akidroid.akinatorapp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;

public class testActivity extends Activity {
	private ImageView imView;
	private Bitmap bmImg; // ��Ʈ����ó���� ������ �����մϴ�.
	String imageUrl = "http://192.168.10.13:8080/AkinatorApp/Mokhwan/upload/"; // �̹����� �Ľ��ؿ� URL, ���⿡ �޾ƿ� �̹����� ���̱�.
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testgo);
        
        // ��Ʈ��ũ �������δ� ���� �����忡�� �۾����� ���Ѵ�.
        // ������ ��Ʈ��ũ ó���ϱ� ���Ͽ� strictmode ����.
        // �̹��� �� �ҷ� �ý� ���� �����ش�.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        imView = (ImageView)findViewById(R.id.imview); // �̹����� �о���� �̹����並 �������ݴϴ�.
        bmImg = getImageFromURL("http://192.168.10.13:8080/AkinatorApp/Mokhwan/upload/aa.png");
        imView.setImageBitmap(bmImg); // imView�� �̹����� �����մϴ�.
    }
    
	public static Bitmap getImageFromURL(String imageURL){
        Bitmap imgBitmap = null;
        HttpURLConnection conn = null;
        BufferedInputStream bis = null;
        
        try
        {
            URL url = new URL(imageURL);
            conn = (HttpURLConnection)url.openConnection();
            conn.connect();
            int nSize = conn.getContentLength();
            bis = new BufferedInputStream(conn.getInputStream(), nSize);
            imgBitmap = BitmapFactory.decodeStream(bis);
        }
        catch (Exception e){
            e.printStackTrace();
        } finally{
            if(bis != null) {
                try {bis.close();} catch (IOException e) {}
            }
            if(conn != null ) {
                conn.disconnect();
            }
        }
        
        return imgBitmap;
    }
}
