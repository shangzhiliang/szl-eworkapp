package com.szl.tools.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

//[案例] 动态代理处理
public class InvocationHandlerTest {

	public static void main(String[] args) {
//		MyInvocationHandler hanlder = new MyInvocationHandler();
//		
//		Subject obj = (Subject)hanlder.bind(new RealSubject());
//		String info = obj.say("jim", 20);
//		System.out.println(info);
		
		try {
			Properties pro = Init.getPro();
			Fruit obj = Factory.getInstance(pro.getProperty("apple"));
			obj.eat();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


/**
 * 定义项目接口
 * @author shangzhiliang
 *
 */
interface Subject{
	public String say(String name, int age);
}

/**
 * // 定义真实项目
 * @author shangzhiliang
 *
 */
class RealSubject implements Subject{

	@Override
	public String say(String name, int age) {
		return name +"   "+age;
	}

}

class MyInvocationHandler  implements InvocationHandler{

	private Object obj = null;
	 
    public Object bind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
                .getClass().getInterfaces(), this);
    }
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object obj = method.invoke(this.obj, args);
		return obj;
	}
}
 
/**
 * 工厂模式－接口定义
 * @author shangzhiliang
 *
 */
interface Fruit{
    public abstract void eat();
}
 
/**
 * 工厂模式－子类实现父接口
 * @author shangzhiliang
 *
 */
class Apple implements Fruit{
    public void eat(){
        System.out.println("Apple");
    }
}
 

/**
* 工厂模式－子类实现父接口
* @author shangzhiliang
*
*/
class Orange implements Fruit{
    public void eat(){
        System.out.println("Orange");
    }
}
 
//操作属性文件类
class Init{
    public static Properties getPro() throws FileNotFoundException, IOException{
        Properties pro=new Properties();
        File f=new File("classpath:fruit.properties");
        if(f.exists()){
            pro.load(new FileInputStream(f));
        }else{
            pro.setProperty("apple", "com.szl.tools.reflect.Apple");
            pro.setProperty("orange", "com.szl.tools.reflect.Orange");
            pro.store(new FileOutputStream(f), "FRUIT CLASS");
        }
        return pro;
    }
}
 
/**
 * 结合属性配置文件的方式配置工厂的具体子类全路径类名，可以简化工厂类判断子类名称。
 * 当然更好的方式可以将类全路径名称存入数据库中，每次启动应用程序时加载类信息并初始化。
 * 
 */
class Factory{
    public static Fruit getInstance(String ClassName){
        Fruit f=null;
        try{
            f=(Fruit)Class.forName(ClassName).newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
