# annotationRuntime
运行时注释，范例；

使用 annotation RunTime 实现 BufferKnifer类似功能； 
Annotation 可以实现一些简单的初始化，让Code更简单；
### 已完成
* @JFindView(<ViewId>)

支持 findViewById 
* @JFindViewOnClick

支持 findViewById and  onClick
> Activity 需要 implement View.OnClickListener.class;
* @JIntent(<key>)

支持Intent.getStringExtra(<String>) 等

````
     
   public class DemoActivity implement View.OnClickListener{
     //Android 代码

      private String testString;
      private boolean testBoolean;
      private int testInt;
      private long testLong;
      private Button btnHello;
      private Button btn_test;
    

      //传值
      public static void startActivity(Context context) {
          PersonalActivity.mListener = listener;
          Intent intent = new Intent(context, PersonalActivity.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
          intent.putExtra("testString", testString);
          intent.putExtra("testInt", testInt);
          intent.putExtra("testBoolean", testBoolean);
          intent.putExtra("testLong", testLong);
          context.startActivity(intent);
      }

      //取值
      private void getIntentData() {
          Intent intent = getIntent();
          testString = intent.getIntExtra("testString", 0);
          testInt = intent.getStringExtra("testInt");
          testBoolean = intent.getStirngExtra("testBoolean");
          testLong = intent.getBooleanExtra("testLong");
      }

      //用值
      public void onCreate(Bundle savedInstanceState){
        getIntentData();
        btnHello= findViewById(R.layout.btn_hello);
        btn_test= findViewById(R.layout.btn_test);
        btnHello.setOnClick(this);
        Log.i("old",test1);
      }
      
      public void onClick(View v){
          int id= v.getId;
          if(id== hello_world){
            //TODO Do Something
          }
      }

      //TODO:如果需要取参的越来越多，代码会是什么样的呢？逻辑上是不是很复杂，你还要考虑getIntent
````
使用 注解实现：
````
    @JIntent("testString")
    private String testString;
    @JIntent("testBoolean")
    private boolean testBoolean;
    @JIntent("testInt")
    private int testInt;
    @JIntent("testLong")
    private long testLong;
    @JFindViwOnClick("btn_hello")
    private Button btnHello;
    @JFindViw("btn_test")
    private Button btn_test;
      
      //用值
      public void onCreate(Bundle savedInstanceState){
        getIntentData();
        btnHello= findViewById(R.layout.btn_hello);
        btnHello.setOnClick(this);
        Log.i("old",test1);
      }
      
      public void onClick(View v){
          int id= v.getId;
          if(id== hello_world){
            //TODO Do Something
          }
      }

````
### 待实现

### 常见问题
