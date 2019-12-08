package com.example.calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
private GestureOverlayView gestureOverlayView2;
private ArrayList<String> list = new ArrayList<String>();
private Button button1,button2,button3,button4;
private TextView equation;//算式
private TextView history;//历史记录
String results[] = new String[50];//存放历史记录的数组
int results_id=0;//历史记录的id号
int is_result=0; //0为没有计算过，1位计算过
int offset;//历史记录行数
int is_negative=0;//判断是否对一个负数进行运算

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        
        equation = (TextView)findViewById(R.id.textView2);
        history = (TextView)findViewById(R.id.textView3);
        history.setMovementMethod(ScrollingMovementMethod.getInstance());  //滚动框
        
        gestureOverlayView2=(GestureOverlayView)findViewById(R.id.gestureOverlayView2);
        final GestureLibrary library = (GestureLibrary)GestureLibraries.fromRawResource(MainActivity.this, R.raw.gestures);
        library.load();
        
        gestureOverlayView2.addOnGesturePerformedListener(new OnGesturePerformedListener() {
			
			@Override
			public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
				// TODO Auto-generated method stub
				ArrayList<Prediction>predictions=library.recognize(gesture);//识别手势: 通过 library 读取手势文件 ，在这里读取
	   			Prediction prediction=predictions.get(0);//取到第一个
	   			
	   			if(prediction.score>=2.0){//更加相似度 来 取得 区间（0.0~10.0 大致区间）
	   				if(prediction.name.equals("1")){//通过name来判断值
	   					Toast.makeText(MainActivity.this, "您输入了数字 1！", Toast.LENGTH_SHORT).show();
	   					list.add("1");
	   				}
	   				else if(prediction.name.equals("2")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 2！", Toast.LENGTH_SHORT).show();
	   					list.add("2");
	   				}
	   				else if(prediction.name.equals("3")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 3！", Toast.LENGTH_SHORT).show();
	   					list.add("3");
	   				}
	   				else if(prediction.name.equals("4")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 4！", Toast.LENGTH_SHORT).show();
	   					list.add("4");
	   				}
	   				else if(prediction.name.equals("5")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 5！", Toast.LENGTH_SHORT).show();
	   					list.add("5");
	   				}
	   				else if(prediction.name.equals("6")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 6！", Toast.LENGTH_SHORT).show();
	   					list.add("6");
	   				}
	   				else if(prediction.name.equals("7")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 7！", Toast.LENGTH_SHORT).show();
	   					list.add("7");
	   				}
	   				else if(prediction.name.equals("8")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 8！", Toast.LENGTH_SHORT).show();
	   					list.add("8");
	   				}
	   				else if(prediction.name.equals("9")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 9！", Toast.LENGTH_SHORT).show();
	   					list.add("9");
	   				}
	   				else if(prediction.name.equals("0")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 0！", Toast.LENGTH_SHORT).show();
	   					list.add("0");
	   				}
	   				else if(prediction.name.equals("+")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 +！", Toast.LENGTH_SHORT).show();
	   					list.add("+");
	   				}
	   				else if(prediction.name.equals("-")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 -！", Toast.LENGTH_SHORT).show();
	   					list.add("-");
	   				}
	   				else if(prediction.name.equals("x")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 x！", Toast.LENGTH_SHORT).show();
	   					list.add("x");
	   				}
	   				else if(prediction.name.equals("*")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 *！", Toast.LENGTH_SHORT).show();
	   					list.add("*");
	   				}
	   				else if(prediction.name.equals("÷")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 ÷！", Toast.LENGTH_SHORT).show();
	   					list.add("÷");
	   				}
	   				else if(prediction.name.equals("/")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 /！", Toast.LENGTH_SHORT).show();
	   					list.add("/");
	   				}   				
	   				else{
	   					Toast.makeText(MainActivity.this, "请输入手势！", Toast.LENGTH_SHORT).show();
	   				}
	   				equation.setText(list.toString().replace("[","").replace("]","").replace(",","").replace(" ", ""));
	   			}	   
			}			
		});
    }
	
	
	class MyStack { 
		 public LinkedList ll=new LinkedList();
		 int len=0;
		 public void push(Object o){
			 ll.addFirst(o);
			 len++;
		 }
		 public Object pop(){
			 len=len-1;
			 return ll.removeFirst();
		 }
		 public Object peek(){
			 return ll.getFirst();
		 }
		 public boolean empty(){
			 return ll.isEmpty();
		 }
	}
	
	public void onClick_calculate(View view){//计算
		double[] num = new double[50];
		int flag=0;
		int sign=0;
		is_result=1;
		double number=0;
		MyStack s = new MyStack();
		MyStack s2 = new MyStack();
		double result=0;
		list.add("=");

		for(int i=0; i<list.size(); i++){
			if(list.get(i)!="+"&&list.get(i)!="-"&&list.get(i)!="x"&&list.get(i)!="*"&&list.get(i)!="÷"&&list.get(i)!="/"&&list.get(i)!="="){//计算成每个数
				
				num[i]=Integer.parseInt(list.get(i));
				flag++;	
				
			}
			else{
				
				for(int j=i-flag; j<i; j++){
					number+=num[j]*Math.pow(10, i-j-1);
				}
				
				s.push(number);//把每一位数字变成一个十进制数入栈
				//Toast.makeText(MainActivity.this, s.peek().toString(), Toast.LENGTH_SHORT).show();
				
				if(sign==3){//乘直接计算
					if(s.peek().toString()=="-"){
						Toast.makeText(MainActivity.this, "!", Toast.LENGTH_SHORT).show();
						break;
					}
					else{
					double a=Double.parseDouble(s.peek().toString());
					//Toast.makeText(MainActivity.this, "a:"+s.peek().toString(), Toast.LENGTH_SHORT).show();
					s.pop();					
					s.pop();
					double b=Double.parseDouble(s.peek().toString());
					//Toast.makeText(MainActivity.this, "b:"+s.peek().toString(), Toast.LENGTH_SHORT).show();
					s.pop();
					result=a*b;
					s.push(result);//计算结果入栈
					//Toast.makeText(MainActivity.this, s.peek().toString(), Toast.LENGTH_SHORT).show();
					}
				}
				if(sign==4){//除直接计算
					if(Double.parseDouble(s.peek().toString())==0){
						Toast.makeText(MainActivity.this, "除数不能为0！", Toast.LENGTH_SHORT).show();
						break;
					}
					else{
						double a=Double.parseDouble(s.peek().toString());
						s.pop();
						s.pop();
						double b=Double.parseDouble(s.peek().toString());
						s.pop();
						result=b/a;
						s.push(result);//计算结果入栈
						//Toast.makeText(MainActivity.this, s.peek().toString(), Toast.LENGTH_SHORT).show();
					}
				}
				
				s.push(list.get(i));//将符号入栈
				//Toast.makeText(MainActivity.this, s.peek().toString(), Toast.LENGTH_SHORT).show();
				switch (s.peek().toString()) {
					case "+":
						sign=1;
						break;
					case "-":
						sign=2;
						break;
					case "x":
						sign=3;
						break;
					case "*":
						sign=3;
						break;
					case "÷":
						sign=4;
						break;
					case "/":
						sign=4;
						break;
					case "=":
						sign=5;
						break;
					default:
						break;
				}
				
				if(sign==5){
					break;
				}
				
				flag=0;
				number=0;
			}
		}
		
		//Toast.makeText(MainActivity.this, "该加减啦", Toast.LENGTH_SHORT).show();
		
		s.pop();
		if(s.len!=1){

			for(int l=s.len;l>0;l--){
				String top = s.peek().toString();
				s.pop();
				s2.push(top);
			}
			
			for(int len=0;len<s2.len+1;len++){
				double a=Double.parseDouble(s2.peek().toString());
				//Toast.makeText(MainActivity.this, "a:"+s2.peek().toString(), Toast.LENGTH_SHORT).show();
				s2.pop();
				if(s2.peek().equals("+")){
					//Toast.makeText(MainActivity.this, "+", Toast.LENGTH_SHORT).show();
				 	s2.pop();
					double b=Double.parseDouble(s2.peek().toString());
					//Toast.makeText(MainActivity.this, s2.peek().toString(), Toast.LENGTH_SHORT).show();
					s2.pop();
					result=a+b;
					if(s2.len==0){
						break;
					}
					s2.push(result);//计算结果入栈
					//Toast.makeText(MainActivity.this, s2.peek().toString(), Toast.LENGTH_SHORT).show();
				}
				if(s2.peek().equals("-")){
					//Toast.makeText(MainActivity.this, "-", Toast.LENGTH_SHORT).show();
				 	s2.pop();
					double b=Double.parseDouble(s2.peek().toString());
					//Toast.makeText(MainActivity.this, "b:"+s2.peek().toString(), Toast.LENGTH_SHORT).show();
					s2.pop();
					result=a-b;
					if(s2.len==0){
						break;
					}
					s2.push(result);//计算结果入栈
					//Toast.makeText(MainActivity.this, s2.peek().toString(), Toast.LENGTH_SHORT).show();
				}
			}
		}
		else{
			result=Double.parseDouble(s.peek().toString());
		}
		
		
		Toast.makeText(MainActivity.this, "结果为："+result, Toast.LENGTH_SHORT).show();
		list.add(""+result);
		equation.setText(list.toString().replace("[","").replace("]","").replace(",","").replace(" ", ""));
	}
	
	public void onClick_back(View view){//删掉list最后一个元素
		if(list.size()!=0){
			int i = list.size()-1;
			list.remove(i);
			equation.setText(list.toString().replace("[","").replace("]","").replace(",","").replace(" ", ""));
		}
	}
	
	public void onClick_empty(View view){//清空list
		if(is_result==1){
			results[results_id]=list.toString().replace("[","").replace("]","").replace(",","").replace(" ", "");
			if(results_id==0){ //判断是否为第一行
				history.append(results[results_id]);
				offset = history.getLineCount()*history.getLineHeight();
				if(offset>history.getHeight()){
					history.scrollTo(0, offset-history.getHeight());
				}
			}
			else{ //除第一行外都要先换行再输出
				history.append("\n"+results[results_id]);
				offset = history.getLineCount()*history.getLineHeight();
				if(offset>history.getHeight()){
					history.scrollTo(0, offset-history.getHeight());
				}
			}
			//history.setText(results[results_id]);
			results_id++;
			is_result=0;
		}
		list.clear();
		equation.setText(null);
	}
	
	public void onClick_emptyHistory(View view){
		results=new String[50];//清空索引
		is_result=0;
		results_id=0;
		history.setText(null);
	}
}
