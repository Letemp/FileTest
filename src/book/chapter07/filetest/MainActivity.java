package book.chapter07.filetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button write,read;
	private EditText writeText,readText;
	private String fileName="content.txt";//���ñ�����ļ���
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		write=(Button)this.findViewById(R.id.write);//��ȡ��д�����ݡ���ť
		read=(Button)this.findViewById(R.id.read);//��ȡ����ȡ���ݡ���ť
		writeText=(EditText)this.findViewById(R.id.writeText);
		readText=(EditText)this.findViewById(R.id.readText);
		
		//��Ӱ�ť�¼�����
		write.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				write(writeText.getText().toString());//���ı��༭�������д���ļ�
				
			}

			//�÷������ַ�������д���ļ�
			private void write(String content) {
				// TODO Auto-generated method stub
				try{
					FileOutputStream fos=openFileOutput(fileName,Context.MODE_APPEND);
					PrintStream ps=new PrintStream(fos);
					ps.print(content);
					ps.close();
					fos.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		});
		read.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				readText.setText(read());//����ȡ��������ʾ���ı��༭����
				
			}

			//�÷������ڶ�ȡ�ļ���Ϣ�������ַ�������
			private String read() {
				// TODO Auto-generated method stub
				StringBuilder sbBuilder=new StringBuilder("");
				try {
					FileInputStream is=openFileInput(fileName);//��ȡ�ļ�������
					byte[]buffer=new byte[64];//���建������С
					int hasRead;//��¼ÿ�ζ�ȡ���ֽ���
					while((hasRead=is.read(buffer))!=-1){
						sbBuilder.append(new String(buffer,0,hasRead));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return sbBuilder.toString();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
