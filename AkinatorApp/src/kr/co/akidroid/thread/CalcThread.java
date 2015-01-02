package kr.co.akidroid.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

//�ڵ鷯 ����� ���� ������
public class CalcThread extends Thread{
	private Handler m_MainHandler;
	
	// �����ڸ� ���Ͽ� ���� ����
	public CalcThread(Handler handler){
		m_MainHandler = handler;
	}
	
	@Override
	public void run() {
		// ������� ���� ������ֱ�
		Looper.prepare();
		Looper.loop();
	}
	
	// �޼��� ��ü�� ���� ���� �޴� �ڵ鷯
	// �̰��� ���ۿ� ���ؼ� �ڵ����� ȣ���� ������ ���ε�
	// ���ο��� �ڵ����� ���۰� ������ 
	// �ٸ� Ŭ���������� ���۸� ���� ����� �־���Ѵ�.
	// ���۸� run()�� �����.
	public Handler m_BackHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// �����忡�� ������� ����Ŭ������ �����ֱ�
			Message rtnMsg = Message.obtain();
			// what���� ������ ó������ ������ �ֱ�
			if(msg.what ==0){
//				//Log.d("CalcThread",String.valueOf(msg.arg1));
//				int result = msg.arg1 * msg.arg1;
				rtnMsg.what=0;
				rtnMsg.obj = msg.obj;
				Log.i("���ڵ鷯","���������?");
			}
			else if(msg.what ==1){
//				double result = Math.sqrt((double)msg.arg1);
				rtnMsg.what=1;
//				// arg�� int���� ���� �� �־ obj�� ������ �Ѵ�.
//				// ���⼭ result�� object���� �ƴϱ� ������ ����Ŭ����(new ~~)�� ���μ� ������ �Ѵ�.
//				rtnMsg.obj=new Double(result);
			}
			
			m_MainHandler.sendMessage(rtnMsg);
			
		}
		
	};
}