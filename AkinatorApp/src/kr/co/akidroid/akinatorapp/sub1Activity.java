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
//	private String quantity;	// warning �� ���� ���߿� int������ �ٲ�� ��.
//	private String[] productNum, productImage, price, subject, regDate;
//	private ArrayList sellerName; 
//	private String imageUrl = "http://192.168.10.13:8080/AkinatorApp/Mokhwan/upload/"; // �̹����� �Ľ��ؿ� URL, ���⿡ �޾ƿ� �̹����� ���̱�.
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//	
//	 	setContentView(R.layout.activity_sub1);
//	 	gv=(GridView)findViewById(R.id.gridview1);
//	 	Log.i("��1��","����");
//	 	// ��� ����Ǹ� �׸���� �׸� ���
//	 	//gv.setAdapter(new ImageAdapter(this));
//	 	
//	 	calc = new CalcThread(m_MainHandler);
//	    //�� ���� ��Ű��
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
//				Toast.makeText(getBaseContext(), "�α��μ���", 3000).show();
//
//				// �α��� ���ǹ���
//				SessionControl.cookies = SessionControl.httpclient.getCookieStore().getCookies();//�߰� 
//				
//				//�о�� ��Ű���� �ѹ� ��� �Ͽ� ����. 
//				Cookie cookie;
//				if (!SessionControl.cookies.isEmpty()){
//				     for (int i = 0; i < SessionControl.cookies.size(); i++) {
//				          cookie = SessionControl.cookies.get(i);
//				          Log.v("TAG","===>>>"+ cookie.toString() );
//				      }
//				}
//
//				Log.i("����Ʈ�� �� �ҷ����� ����","�ҷ��� ������");
//				//Toast.makeText(getApplicationContext(), "����Ʈ�� �� �ҷ����� ����", 3000).show();
//				
//				//msg.obj ���� ��� �׸� �̸�
//				
//				//Log.i("","�ҷ��� ������");
//				allArray=(String[]) msg.obj;
//				
//				// warning ������ i =1�� ��. 0���� �ؾ� �Ҽ��� .. �׸��� �ؿ� i-1 ����
//				for(int i=1;i<=allArray.length;i++){
//					Log.i("for��","����");
//					if(i%6==1){
//						Log.i("for��","����");
//						//sellerName = allArray[i-1];
//					}
//					else if(i%6==2){
//						productNum[i-1] = allArray[i-1];	
//					}
//					else if(i%6==3){
//						productImage[i-1] = allArray[i-1];	//�׸���信 ���� �̹���
//					}
//					else if(i%6==4){
//						price[i-1] = allArray[i-1];			//�׸���信 ���� ����
//					}
//					else if(i%6==5){
//						subject[i-1] = allArray[i-1];			//�׸���信 ���� ����
//					}
//					else if(i%6==0){
//						regDate[i-1] = allArray[i-1];
//					}
//					
//					Log.i("for��","����");
//					if(i==(allArray.length)){
//						quantity=allArray[i-1]; //��ǰ����
//					}
//					Log.i("for��","����");
//				}
//				// ������ ��� ���� �κ�.
//				// ���� ���� �͵� ���� �ְ�,  
//				// ImageAdapter�� �����ذ� �׸��忡 ������ ��
//				
//				// ��� ����Ǹ� �׸���� �׸� ���
//			 	//gv.setAdapter(new ImageAdapter(getBaseContext()));
//				
//				//Intent intent = new Intent(getBaseContext(), TabActivity.class);
//				//startActivity(intent);
//				break;
//				
//			// warning what 1�� �ƿ� �������� �ʾ���. ���⼭ ���� ���� ����.
//			case 1:
//				Toast.makeText(getApplicationContext(), "����Ʈ�� ��������", 3000).show();
//				Log.i("����Ʈ�� ��������","���� �ȵ�");
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
//		Context context; //��ü�� �ּҸ� �ޱ����� ����
//		
//		public ImageAdapter(Context c){
//			context=c; //�����ڸ� ���Ͽ� GridViewActivity��  �ּҰ��� �޴´�			
//		}
//		@Override
//		public int getCount() {//�������ִ� �������� ����
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
//		public View getView(int position, View convertView, ViewGroup parent) {//convertView �ѹ�������� �並 ��� ������ ����(��Ȱ��)
//			ImageView imgView;
//			
//			if(convertView == null){//�̹��� �� ��ü�� ó�� ����
//				imgView=new ImageView(context);
//				imgView.setLayoutParams(new GridView.LayoutParams(75,75));
//				Log.i("ImageAdapter", position+"��° �� ����");
//			}
//			else{ //�̹��� �� ��ü�� �̹� �ѹ� ��������ٸ� 
//				Log.i("ImageAdapter", position+"��° �� ����");
//				imgView=(ImageView)convertView; //������� �̹����並 �����پ�
//			}
//				imgView.setImageResource(images[position]);
//			
//			return imgView;
//		} 
//		
//		@Override
//		public ImageButton getView(int position, View convertView, ViewGroup parent) {//convertView �ѹ�������� �並 ��� ������ ����(��Ȱ��)
//			ImageButton btnView=null;
//			
//			if(convertView == null){//�̹��� �� ��ü�� ó�� ����
//				btnView=new ImageButton(context);
//				btnView.setLayoutParams(new GridView.LayoutParams(75,75));
//				Log.i("ImageAdapter", position+"��° �� ����");
//			}
//			else{ //�̹��� �� ��ü�� �̹� �ѹ� ��������ٸ� 
//				Log.i("ImageAdapter", position+"��° �� ����");
//				btnView=(ImageButton)convertView; //������� �̹����並 �����پ�
//			}
//				btnView.setBackgroundResource(images[position]);
//			
//			btnView.setOnClickListener(
//				new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						Log.d("�ȴ�","��");
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
//            	//���� �ʿ������ ��� 
//	            //HttpClient client = new DefaultHttpClient();
//	            
//	            //���������ҋ� �̰ž�����?
//	            HttpClient httpclient = SessionControl.getHttpclient();//����
//
//	            // jsp �ּ�
//	            String postURL = "http://192.168.10.13:8080/AkinatorApp/android/listViewAndroid.jsp";
//	            HttpPost post = new HttpPost(postURL);
//	            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//	            
//	            //�Ľ�?
//	            params.add(new BasicNameValuePair("list","gogo"));
//	            
//	            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params,HTTP.UTF_8);
//	            post.setEntity(ent);
//	            
////	            HttpResponse responsePOST = client.execute(post);    
////	            HttpEntity resEntity = responsePOST.getEntity();   
//	            
//	            // jsp���� out.println�� �޾ƿ��°�
//	            ResponseHandler<String> reshandler = new BasicResponseHandler();
//	    		result = httpclient.execute(post, reshandler).trim();	//�̻��ϰ� ��� ���鵵 ���� ����ȴ�. �������Ÿ� ���� trim().
//	    			    		
//	    		Message msg = Message.obtain();
//	    		if (result != null) 
//	            {
//	    			resultToArray = result.split("%");
////	                Log.i("RESPONSE", EntityUtils.toString(resEntity));  
//	                Log.i("RESPONSE2", result);
//	                
//	                // �迭 �ڵ鷯�� �ѱ�� ó��
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
	private String quantity; // warning �� ���� ���߿� int������ �ٲ�� ��.
	private String[] productNum, productImage, price, subject, regDate;
	private ArrayList sellerName;
	private String imageUrl = "http://192.168.10.13:8080/AkinatorApp/Mokhwan/upload/"; // �̹����� �Ľ��ؿ� URL, ���⿡ �޾ƿ� �̹����� ���̱�.

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.i("bug", "c8");
		setContentView(R.layout.activity_sub1test);
		// ��� ����Ǹ� �׸���� �׸� ���
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
		Log.e("START_TAG", "����0");
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();

			URL u = new URL(m_sConnectUrl);
			// InputStream in = u.openConnection().getInputStream();
			InputStream in = u.openStream();
			xpp.setInput(in, "utf-8");

			int eventType = xpp.getEventType();

			Log.e("START_TAG", "����0");
			while (eventType != XmlPullParser.END_DOCUMENT) {
				Log.e("START_TAG", "����1");
				if (eventType == XmlPullParser.START_DOCUMENT) {
					Log.e("START_TAG", "����2");
					// System.out.println("Start document");
				} else if (eventType == XmlPullParser.END_DOCUMENT) {
					Log.e("START_TAG", "����3");
					// System.out.println("End document");
				} else if (eventType == XmlPullParser.START_TAG) {
					Log.e("START_TAG", "����4");

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

	// List�� row�� �����ϱ� ���� Adapter �������̵�
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
				// ��Ʈ������ ��ũ�� �̹��� �ҷ����� �޼ҵ� ����Ͽ� �̹��� �Ѹ��� 
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
				//�ؽ�Ʈ ��� �̹��� �Ѹ��� ����
//				if (iv2 != null) {
//					iv2.setText(Html.fromHtml("<img src='"+imageUrl+xmlData.productimage+"'/>",null, null));
//					//iv2.setImageDrawable(imageUrl+xmlData.productimage);
//				}
			}
			return v;
		}
	}
	
	// ��Ʈ�� �������� �̹��� �ҷ�����
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
