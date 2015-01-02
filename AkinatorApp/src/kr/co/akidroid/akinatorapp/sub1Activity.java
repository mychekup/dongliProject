//package kr.co.akidroid.akinatorapp;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import kr.co.akidroid.session.SessionControl;
//import kr.co.akidroid.thread.CalcThread;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.cookie.Cookie;
//import org.apache.http.impl.client.BasicResponseHandler;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.protocol.HTTP;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//public class sub1Activity extends Activity {
//	loadJsp task;
//	CalcThread calc;
//	GridView gv;
//	private String result, resultPlus;
//	private String[] resultToArray;
//	private String[] allArray;
//	private String quantity;	// warning 총 개수 나중에 int형으로 바꿔야 함.
//	private String[] productNum, productImage, price, subject, regDate;
//	private ArrayList sellerName; 
//	private String imageUrl = "http://192.168.10.13:8080/AkinatorApp/Mokhwan/upload/"; // 이미지를 파싱해올 URL, 여기에 받아온 이미지명 붙이기.
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//	
//	 	setContentView(R.layout.activity_sub1);
//	 	gv=(GridView)findViewById(R.id.gridview1);
//	 	Log.i("탭1옴","ㅎㅎ");
//	 	// 요거 실행되면 그리드뷰 그림 띄움
//	 	//gv.setAdapter(new ImageAdapter(this));
//	 	
//	 	calc = new CalcThread(m_MainHandler);
//	    //앱 종료 시키기
//	    calc.setDaemon(true);
//	    calc.start();
//	    
//	    task = new loadJsp();
//        task.execute();
//	    
//	    
//	}
//	
//	public Handler m_MainHandler = new Handler(){
//
//		@Override
//		public void handleMessage(Message msg) {
//			switch(msg.what){
//			case 0:
//				Toast.makeText(getBaseContext(), "로그인성공", 3000).show();
//
//				// 로그인 세션문제
//				SessionControl.cookies = SessionControl.httpclient.getCookieStore().getCookies();//추가 
//				
//				//읽어온 쿠키값을 한번 출력 하여 보자. 
//				Cookie cookie;
//				if (!SessionControl.cookies.isEmpty()){
//				     for (int i = 0; i < SessionControl.cookies.size(); i++) {
//				          cookie = SessionControl.cookies.get(i);
//				          Log.v("TAG","===>>>"+ cookie.toString() );
//				      }
//				}
//
//				Log.i("리스트뷰 값 불러오기 성공","불러는 와지네");
//				//Toast.makeText(getApplicationContext(), "리스트뷰 값 불러오기 성공", 3000).show();
//				
//				//msg.obj 여기 모든 그림 이름
//				
//				//Log.i("","불러는 와지네");
//				allArray=(String[]) msg.obj;
//				
//				// warning 시작을 i =1로 함. 0으로 해야 할수도 .. 그리고 밑에 i-1 주의
//				for(int i=1;i<=allArray.length;i++){
//					Log.i("for문","하핫");
//					if(i%6==1){
//						Log.i("for문","하핫");
//						//sellerName = allArray[i-1];
//					}
//					else if(i%6==2){
//						productNum[i-1] = allArray[i-1];	
//					}
//					else if(i%6==3){
//						productImage[i-1] = allArray[i-1];	//그리드뷰에 넣을 이미지
//					}
//					else if(i%6==4){
//						price[i-1] = allArray[i-1];			//그리드뷰에 넣을 가격
//					}
//					else if(i%6==5){
//						subject[i-1] = allArray[i-1];			//그리드뷰에 넣을 제목
//					}
//					else if(i%6==0){
//						regDate[i-1] = allArray[i-1];
//					}
//					
//					Log.i("for문","끼룩");
//					if(i==(allArray.length)){
//						quantity=allArray[i-1]; //물품개수
//					}
//					Log.i("for문","히익");
//				}
//				// 데이터 모두 받은 부분.
//				// 이제 받은 것들 묶어 주고,  
//				// ImageAdapter에 묶어준것 그리드에 넣으면 끝
//				
//				// 요거 실행되면 그리드뷰 그림 띄움
//			 	//gv.setAdapter(new ImageAdapter(getBaseContext()));
//				
//				//Intent intent = new Intent(getBaseContext(), TabActivity.class);
//				//startActivity(intent);
//				break;
//				
//			// warning what 1번 아예 보내지도 않았음. 여기서 에러 났을 수도.
//			case 1:
//				Toast.makeText(getApplicationContext(), "리스트뷰 생성실패", 3000).show();
//				Log.i("리스트뷰 생성실패","말이 안돼");
//			}
//			
//		}
//	};
//	
//
//	/*class ImageAdapter extends BaseAdapter{
//		Integer  []images = {R.drawable.img1,R.drawable.img10,
//				R.drawable.img11, R.drawable.img12, R.drawable.img13,
//				R.drawable.img14, R.drawable.img15,  R.drawable.img16,  
//				R.drawable.img17, R.drawable.img2, R.drawable.img3, 
//				R.drawable.img4, R.drawable.img5,  R.drawable.img6,  
//				R.drawable.img7, R.drawable.img8,  R.drawable.img9,
//				R.drawable.img18, R.drawable.img19,  R.drawable.img20,
//				R.drawable.img21, R.drawable.img22,  R.drawable.img23,
//				R.drawable.img24};
//		
//		Context context; //객체의 주소를 받기위한 변수
//		
//		public ImageAdapter(Context c){
//			context=c; //생성자를 통하여 GridViewActivity의  주소값를 받는다			
//		}
//		@Override
//		public int getCount() {//가지고있는 데이터의 갯수
//			return images.length;
//		}
//
//		@Override
//		public Object getItem(int position) { 
//			return null;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return 0;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {//convertView 한번만들어진 뷰를 계속 가져다 쓴다(재활용)
//			ImageView imgView;
//			
//			if(convertView == null){//이미지 뷰 객체를 처음 만듬
//				imgView=new ImageView(context);
//				imgView.setLayoutParams(new GridView.LayoutParams(75,75));
//				Log.i("ImageAdapter", position+"번째 뷰 생성");
//			}
//			else{ //이미지 뷰 객체가 이미 한번 만들어졌다면 
//				Log.i("ImageAdapter", position+"번째 뷰 재사용");
//				imgView=(ImageView)convertView; //만들어진 이미지뷰를 가져다씀
//			}
//				imgView.setImageResource(images[position]);
//			
//			return imgView;
//		} 
//		
//		@Override
//		public ImageButton getView(int position, View convertView, ViewGroup parent) {//convertView 한번만들어진 뷰를 계속 가져다 쓴다(재활용)
//			ImageButton btnView=null;
//			
//			if(convertView == null){//이미지 뷰 객체를 처음 만듬
//				btnView=new ImageButton(context);
//				btnView.setLayoutParams(new GridView.LayoutParams(75,75));
//				Log.i("ImageAdapter", position+"번째 뷰 생성");
//			}
//			else{ //이미지 뷰 객체가 이미 한번 만들어졌다면 
//				Log.i("ImageAdapter", position+"번째 뷰 재사용");
//				btnView=(ImageButton)convertView; //만들어진 이미지뷰를 가져다씀
//			}
//				btnView.setBackgroundResource(images[position]);
//			
//			btnView.setOnClickListener(
//				new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						Log.d("된다","되");
//					}
//				}
//			);
//			return btnView;
//		} 
//		
//	}*/
//	class loadJsp extends AsyncTask<Void,String,Void> {
//        @Override
//        protected Void doInBackground(Void... param) {
//            // TODO Auto-generated method stub     
//            try{
//            	//세션 필요없을땐 요거 
//	            //HttpClient client = new DefaultHttpClient();
//	            
//	            //세션유지할떈 이거쓰려나?
//	            HttpClient httpclient = SessionControl.getHttpclient();//변경
//
//	            // jsp 주소
//	            String postURL = "http://192.168.10.13:8080/AkinatorApp/android/listViewAndroid.jsp";
//	            HttpPost post = new HttpPost(postURL);
//	            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//	            
//	            //파싱?
//	            params.add(new BasicNameValuePair("list","gogo"));
//	            
//	            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params,HTTP.UTF_8);
//	            post.setEntity(ent);
//	            
////	            HttpResponse responsePOST = client.execute(post);    
////	            HttpEntity resEntity = responsePOST.getEntity();   
//	            
//	            // jsp에서 out.println을 받아오는곳
//	            ResponseHandler<String> reshandler = new BasicResponseHandler();
//	    		result = httpclient.execute(post, reshandler).trim();	//이상하게 계속 공백도 같이 저장된다. 공백제거를 위해 trim().
//	    			    		
//	    		Message msg = Message.obtain();
//	    		if (result != null) 
//	            {
//	    			resultToArray = result.split("%");
////	                Log.i("RESPONSE", EntityUtils.toString(resEntity));  
//	                Log.i("RESPONSE2", result);
//	                
//	                // 배열 핸들러로 넘기는 처리
//    	    		msg.what = 0;
//    	    		msg.obj = resultToArray;
//	            }
//	    		calc.m_BackHandler.sendMessage(msg);
//	            
//            
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//	}
//}

package kr.co.akidroid.akinatorapp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import kr.co.akidroid.parser.XmlData;
import kr.co.akidroid.thread.CalcThread;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class sub1Activity extends ListActivity {
	CalcThread calc;
	private String result, resultPlus;
	private String[] resultToArray;
	private String[] allArray;
	private String quantity; // warning 총 개수 나중에 int형으로 바꿔야 함.
	private String[] productNum, productImage, price, subject, regDate;
	private ArrayList sellerName;
	private String imageUrl = "http://192.168.10.13:8080/AkinatorApp/Mokhwan/upload/"; // 이미지를 파싱해올 URL, 여기에 받아온 이미지명 붙이기.

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.i("bug", "c8");
		setContentView(R.layout.activity_sub1test);
		// 요거 실행되면 그리드뷰 그림 띄움
		// gv.setAdapter(new ImageAdapter(this));

		Button btn = (Button)findViewById(R.id.searchBtn);
        final EditText et = (EditText)findViewById(R.id.searchTxt);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

		Log.i("bug", "c9");
		searchXml();  
		Log.i("bug", "c10");

	}

	public void searchXml() {
		
		String m_sConnectUrl = "http://192.168.10.13:8080/AkinatorApp/android/listViewAndroid.jsp";
		XmlData xmlData = null;
		ArrayList<XmlData> m_xmlData = new ArrayList<XmlData>();
		String sTag;
		Log.e("START_TAG", "시작0");
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();

			URL u = new URL(m_sConnectUrl);
			// InputStream in = u.openConnection().getInputStream();
			InputStream in = u.openStream();
			xpp.setInput(in, "utf-8");

			int eventType = xpp.getEventType();

			Log.e("START_TAG", "시작0");
			while (eventType != XmlPullParser.END_DOCUMENT) {
				Log.e("START_TAG", "시작1");
				if (eventType == XmlPullParser.START_DOCUMENT) {
					Log.e("START_TAG", "시작2");
					// System.out.println("Start document");
				} else if (eventType == XmlPullParser.END_DOCUMENT) {
					Log.e("START_TAG", "시작3");
					// System.out.println("End document");
				} else if (eventType == XmlPullParser.START_TAG) {
					Log.e("START_TAG", "시작4");

					sTag = xpp.getName();

					if (sTag.equals("sellername")) {
						// Log.e("title_getText",xpp.nextText());
						xmlData = new XmlData();
						xmlData.sellername = xpp.nextText();
					}
					if (sTag.equals("productnum")) {
						xmlData.productnum = xpp.nextText();
					}
					if (sTag.equals("productimage")) {
						// Log.e("title_getText",xpp.nextText());
						xmlData.productimage = xpp.nextText();
					}
					if (sTag.equals("price")) {
						// Log.e("title_getText",xpp.nextText());
						xmlData.price = xpp.nextText();
					}
					if (sTag.equals("subject")) {
						// Log.e("title_getText",xpp.nextText());
						xmlData.subject = xpp.nextText();
						Log.e("subject","subject");
					}
					if (sTag.equals("regdate")) {
						// Log.e("title_getText",xpp.nextText());
						xmlData.regdate = xpp.nextText();
						Log.e("regdate","regdate");
					}
					if (sTag.equals("quantity")) {
						Log.e("quantity","quantity");
						// Log.e("title_getText",xpp.nextText());
						xmlData.quantity = xpp.nextText();
					}

					// System.out.println("Start tag "+xpp.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					// System.out.println("End tag "+xpp.getName());
					sTag = xpp.getName();
					if (sTag.equals("item")) {
						m_xmlData.add(xmlData);
						xmlData = null;
					}
				} else if (eventType == XmlPullParser.TEXT) {
					// System.out.println("Text "+xpp.getText());
				}
				eventType = xpp.next();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		XmlListAdapter adapter = new XmlListAdapter(this, R.layout.row, m_xmlData);
		setListAdapter(adapter);
	}

	// List의 row를 변형하기 위해 Adapter 오버라이딩
	private class XmlListAdapter extends ArrayAdapter {

		private ArrayList items;

		public XmlListAdapter(Context context, int textViewResourceId, ArrayList items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View v = convertView;

			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.row, null);
			}

			XmlData xmlData = (XmlData) items.get(position);
			if (xmlData != null) {
				TextView tv1 = (TextView) v.findViewById(R.id.sellername1);
				TextView tv2 = (TextView) v.findViewById(R.id.subject1);
				ImageView iv1 = (ImageView)v.findViewById(R.id.img_grid1);
				TextView iv2 = (TextView)v.findViewById(R.id.img_grid2);
				// 비트맵으로 링크의 이미지 불러오는 메소드 사용하여 이미지 뿌리기 
				if (iv1 != null) {
					//iv2.setText(Html.fromHtml("<a href></a>", "<img src='"+imageUrl+xmlData.productimage+"'/>", null));
					//iv2.setImageDrawable(imageUrl+xmlData.productimage);
					iv1.setImageBitmap(getImageFromURL(imageUrl+xmlData.productimage));
				}
				if (tv1 != null) {
					tv1.setText(Html.fromHtml("<a href='" + xmlData.subject + "'>" + xmlData.subject+ "</a>"));
					tv1.setMovementMethod(LinkMovementMethod.getInstance());
				}
				if (tv2 != null) {
					tv2.setText(xmlData.sellername);
				}
				//텍스트 뷰로 이미지 뿌리기 실패
//				if (iv2 != null) {
//					iv2.setText(Html.fromHtml("<img src='"+imageUrl+xmlData.productimage+"'/>",null, null));
//					//iv2.setImageDrawable(imageUrl+xmlData.productimage);
//				}
			}
			return v;
		}
	}
	
	// 비트맵 형식으로 이미지 불러오기
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
