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
	private Bitmap bmImg; // 비트맵을처리할 변수를 생성합니다.
	String imageUrl = "http://192.168.10.13:8080/AkinatorApp/Mokhwan/upload/"; // 이미지를 파싱해올 URL, 여기에 받아온 이미지명 붙이기.
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testgo);
        
        // 네트워크 관련으로는 메인 스레드에서 작업하지 못한다.
        // 강제로 네트워크 처리하기 위하여 strictmode 선언.
        // 이미지 못 불러 올시 선언 시켜준다.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        imView = (ImageView)findViewById(R.id.imview); // 이미지를 읽어들일 이미지뷰를 연결해줍니다.
        bmImg = getImageFromURL("http://192.168.10.13:8080/AkinatorApp/Mokhwan/upload/aa.png");
        imView.setImageBitmap(bmImg); // imView에 이미지를 셋팅합니다.
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
