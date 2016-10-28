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
	private String fileName="content.txt";//设置保存的文件名
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		write=(Button)this.findViewById(R.id.write);//获取“写入内容”按钮
		read=(Button)this.findViewById(R.id.read);//获取“读取内容”按钮
		writeText=(EditText)this.findViewById(R.id.writeText);
		readText=(EditText)this.findViewById(R.id.readText);
		
		//添加按钮事件处理
		write.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				write(writeText.getText().toString());//将文本编辑框的内容写入文件
				
			}

			//该方法将字符串内容写入文件
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
				readText.setText(read());//将读取的内容显示在文本编辑框上
				
			}

			//该方法用于读取文件信息，并以字符串返回
			private String read() {
				// TODO Auto-generated method stub
				StringBuilder sbBuilder=new StringBuilder("");
				try {
					FileInputStream is=openFileInput(fileName);//获取文件输入流
					byte[]buffer=new byte[64];//定义缓冲区大小
					int hasRead;//记录每次读取的字节数
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
