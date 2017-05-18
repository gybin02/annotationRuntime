package com.seeker.tony.myapplication.process;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.seeker.tony.myapplication.annotation.BindViewMe;
import com.seeker.tony.myapplication.annotation.ContentView;
import com.seeker.tony.myapplication.annotation.MeIntent;
import com.seeker.tony.myapplication.annotation.MeOnClick;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhengxiaobin@xiaoyouzi.com
 * @since 17/5/18
 */

public class ViewInject {

    public static void inject(Activity activity) {
        injectContentView(activity);
        injectFindView(activity);
        injectOnClick(activity);
        injectIntent(activity);
    }

    private static void injectIntent(Activity activity) {
//        Intent intent = activity.getIntent();
//        intent.getBooleanExtra();

    }

    private static void injectOnClick(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            MeOnClick annotation = method.getAnnotation(MeOnClick.class);
            if (annotation != null) {
                int[] value = annotation.value();
                for (int i : value) {
                    View viewById = activity.findViewById(i);
//                    viewById.setOnClickListener(method);

                    //通过InvocationHandler设置代理  
                    DynamicHandler handler = new DynamicHandler(activity);
                    handler.addMethod("onClick", method);
                    Class<View.OnClickListener> listenerType = View.OnClickListener.class;
                    Object listener = Proxy.newProxyInstance(
                            listenerType.getClassLoader(),
                            new Class<?>[]{listenerType}, handler);
                    viewById.setOnClickListener((View.OnClickListener) listener);
                }
            }

        }
    }

    private static void injectFindView(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            BindViewMe annotation = field.getAnnotation(BindViewMe.class);

            try {
                if (annotation != null) {
                    int value = annotation.value();
                    View viewById = activity.findViewById(value);
                    field.set(activity, viewById);
                }

                MeIntent annoIntent = field.getAnnotation(MeIntent.class);
                if (annoIntent != null) {
                    String value = annoIntent.value();
                    Intent intent = activity.getIntent();
                    Class<?> type = field.getType();
                    if (type == String.class) {
                        String stringExtra = intent.getStringExtra(value);
                        field.set(activity, stringExtra);
                    } else if (type == int.class) {
                        int intExtra = intent.getIntExtra(value, 0);
                        field.set(activity, intExtra);
                    } else if (type == boolean.class) {

                    } else if (type == boolean.class) {


                    }

                }
//                Method findViewById = aClass.getMethod("findViewById", int.class);
//                Object invoke = findViewById.invoke(activity, value);
//                field.setAccessible(true);
//                field.set(activity, invoke);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void injectContentView(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        ContentView annotation = aClass.getAnnotation(ContentView.class);
        if (annotation != null) {
            int value = annotation.value();
            try {
                activity.setContentView(value);
//                Method method = aClass.getMethod(METHOD_SET_CONTENTVIEW, int.class);
//                method.setAccessible(true);
//                method.invoke(activity, value);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 注入所有的事件
     *
     * @param activity
     */
//    private static void injectEvents(Activity activity) {
//
//        Class<? extends Activity> clazz = activity.getClass();
//        Method[] methods = clazz.getMethods();
//        //遍历所有的方法  
//        for (Method method : methods) {
//            Annotation[] annotations = method.getAnnotations();
//            //拿到方法上的所有的注解  
//            for (Annotation annotation : annotations) {
//                Class<? extends Annotation> annotationType = annotation
//                        .annotationType();
//                //拿到注解上的注解  
//                EventBase eventBaseAnnotation = annotationType
//                        .getAnnotation(EventBase.class);
//                //如果设置为EventBase  
//                if (eventBaseAnnotation != null) {
//                    //取出设置监听器的名称，监听器的类型，调用的方法名  
//                    String listenerSetter = eventBaseAnnotation
//                            .listenerSetter();
//                    Class<?> listenerType = eventBaseAnnotation.listenerType();
//                    String methodName = eventBaseAnnotation.methodName();
//
//                    try {
//                        //拿到Onclick注解中的value方法  
//                        Method aMethod = annotationType
//                                .getDeclaredMethod("value");
//                        //取出所有的viewId  
//                        int[] viewIds = (int[]) aMethod
//                                .invoke(annotation, null);
//                        //通过InvocationHandler设置代理  
//                        DynamicHandler handler = new DynamicHandler(activity);
//                        handler.addMethod(methodName, method);
//                        Object listener = Proxy.newProxyInstance(
//                                listenerType.getClassLoader(),
//                                new Class<?>[]{listenerType}, handler);
//                        //遍历所有的View，设置事件  
//                        for (int viewId : viewIds) {
//                            View view = activity.findViewById(viewId);
//                            Method setEventListenerMethod = view.getClass()
//                                                                .getMethod(listenerSetter, listenerType);
//                            setEventListenerMethod.invoke(view, listener);
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }
//
//    }

}
