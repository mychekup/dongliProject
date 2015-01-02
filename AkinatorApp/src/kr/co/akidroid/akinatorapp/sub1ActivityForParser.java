//package kr.co.akidroid.akinatorapp;
//
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//
//import kr.co.akidroid.akinatorapp.sub1Activity.loadJsp;
//import kr.co.akidroid.parser.XmlData;
//import kr.co.akidroid.thread.CalcThread;
//

//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserFactory;
//
//import android.app.ListActivity;
//import android.content.Context;
//import android.os.Bundle;
//import android.text.Html;
//import android.text.method.LinkMovementMethod;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.GridLayout;
//import android.widget.GridView;
//import android.widget.TextView;
//
//public class sub1ActivityForParser extends ListActivity {
//	loadJsp task;
//	CalcThread calc;
//	GridLayout gl;
//	private String result, resultPlus;
//	private String[] resultToArray;
//	private String[] allArray;
//	private String quantity; // warning 총 개수 나중에 int형으로 바꿔야 함.
//	private String[] productNum, productImage, price, subject, regDate;
//	private ArrayList sellerName;
//	private String imageUrl = "http://192.168.10.13:8080/AkinatorApp/Mokhwan/upload/"; // 이미지를 파싱해올 URL, 여기에 받아온 이미지명 붙이기.
//
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		setContentView(R.layout.activity_sub1test);
//		gl = (GridLayout) findViewById(R.id.gridview2);
//		Log.i("탭1옴", "ㅎㅎ");
//		// 요거 실행되면 그리드뷰 그림 띄움
//		// gv.setAdapter(new ImageAdapter(this));
//
//		searchXml();
//
//	}
//
//	public void searchXml() {
//		
//		String m_sConnectUrl = "http://192.168.10.13:8080/AkinatorApp/android/listViewAndroid.jsp";
//		XmlData xmlData = null;
//		ArrayList<XmlData> m_xmlData = new ArrayList<XmlData>();
//		String sTag;
//		try {
//
//			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//			factory.setNamespaceAware(true);
//			XmlPullParser xpp = factory.newPullParser();
//
//			URL u = new URL(m_sConnectUrl);
//			// InputStream in = u.openConnection().getInputStream();
//			InputStream in = u.openStream();
//			xpp.setInput(in, "utf-8");
//
//			int eventType = xpp.getEventType();
//
//			while (eventType != XmlPullParser.END_DOCUMENT) {
//				if (eventType == XmlPullParser.START_DOCUMENT) {
//					// System.out.println("Start document");
//				} else if (eventType == XmlPullParser.END_DOCUMENT) {
//					// System.out.println("End document");
//				} else if (eventType == XmlPullParser.START_TAG) {
//
//					Log.e("START_TAG", xpp.getName());
//					sTag = xpp.getName();
//
//					if (sTag.equals("sellername")) {
//						// Log.e("title_getText",xpp.nextText());
//						xmlData = new XmlData();
//						xmlData.sellername = xpp.nextText();
//					}
//					if (sTag.equals("productnum")) {
//						xmlData.productnum = xpp.nextText();
//					}
//					if (sTag.equals("productimage")) {
//						// Log.e("title_getText",xpp.nextText());
//						xmlData.productimage = xpp.nextText();
//					}
//					if (sTag.equals("price")) {
//						// Log.e("title_getText",xpp.nextText());
//						xmlData.price = xpp.nextText();
//					}
//					if (sTag.equals("subject")) {
//						// Log.e("title_getText",xpp.nextText());
//						xmlData.subject = xpp.nextText();
//					}
//					if (sTag.equals("regdate")) {
//						// Log.e("title_getText",xpp.nextText());
//						xmlData.regdate = xpp.nextText();
//					}
//					 Log.e("title_getText","quantity 나와야함");
//					if (sTag.equals("quantity")) {
//						Log.e("quantity","quantity");
//						// Log.e("title_getText",xpp.nextText());
//						xmlData.quantity = xpp.nextText();
//					}
//
//					// System.out.println("Start tag "+xpp.getName());
//				} else if (eventType == XmlPullParser.END_TAG) {
//					// System.out.println("End tag "+xpp.getName());
//					sTag = xpp.getName();
//					if (sTag.equals("item")) {
//						m_xmlData.add(xmlData);
//						xmlData = null;
//					}
//				} else if (eventType == XmlPullParser.TEXT) {
//					// System.out.println("Text "+xpp.getText());
//				}
//				eventType = xpp.next();
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		XmlListAdapter adapter = new XmlListAdapter(this, R.layout.gridtest_activity, m_xmlData);
//		setListAdapter(adapter);
//	}
//
//	// List의 row를 변형하기 위해 Adapter 오버라이딩
//	private class XmlListAdapter extends ArrayAdapter {
//
//		private ArrayList items;
//
//		public XmlListAdapter(Context context, int textViewResourceId, ArrayList items) {
//			super(context, textViewResourceId, items);
//			this.items = items;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//
//			View v = convertView;
//
//			if (v == null) {
//				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//				v = vi.inflate(R.layout.gridtest_activity, null);
//			}
//
//			XmlData xmlData = (XmlData) items.get(position);
//			if (xmlData != null) {
//				TextView tv1 = (TextView) v.findViewById(R.id.sellername);
//				TextView tv2 = (TextView) v.findViewById(R.id.subject);
//
//				if (tv1 != null) {
//					tv1.setText(Html.fromHtml("<a href='" + xmlData.sellername + "'>" + xmlData.subject + "</a>"));
//					tv1.setMovementMethod(LinkMovementMethod.getInstance());
//				}
//				if (tv2 != null) {
//					tv2.setText(xmlData.subject);
//				}
//			}
//
//			return v;
//		}
//
//	}
//}
