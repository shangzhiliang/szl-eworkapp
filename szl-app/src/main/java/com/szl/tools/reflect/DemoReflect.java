package com.szl.tools.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * java 反射案例分析
 * @author shangzhiliang
 *
 */
public class DemoReflect {
	public static void main(String[] args) {
//		anli1Test();
		
//		anli2Test();
		
//		AnliTest3();
		
//		anli3Test();
		
//		anli4Test();
		
		//[案例】：取得其他类中的父类
//		anli5Test();
//		【案例】：获得其他类中的全部构造函数
//		anli6Test();
		//[案例]下面这个例子我们就来获取修饰符
//		anli7Test();
		
		//[案例]有时候一个方法可能还有异常
//		anli8Test();
//		【案例】接下来让我们取得其他类的全部属性吧，最后我讲这些整理在一起，也就是通过class取得一个类的全部框架
//		anli9test();
//		【案例】其实还可以通过反射调用其他类中的方法：
//		anli10Test();
		//【案例】调用其他类的set和get方法
//		 anli11Test();
//		［案例】通过反射操作属性
//		 anli12test();
		
//		【案例】通过反射取得并修改数组的信息：
//		anli13test();
//	    【案例】通过反射修改数组大小
//		anli14test();
		
//		[案例】首先来看看如何获得类加载器
//		anli15test();
		

		
	}

	
	/**
	 * [案例】首先来看看如何获得类加载器
	 */
	private static void anli15test() {
		//		[案例】首先来看看如何获得类加载器：
				
		Hello hello = new Hello();
		System.out.println(hello.getClass().getClassLoader().getClass().getName());
	}

	/**
	 * 
	 */
	private static void anli14test() {
		//		【案例】通过反射修改数组大小
				int[] temp={1,2,3,4,5,6,7,8,9};
				int[] newTemp = (int[]) arrayInc(temp, 15);
				print(newTemp);
				System.out.println("\r\n===============================");
				String[] atr = { "a", "b", "c" };
				String[] str1 = (String[]) arrayInc(atr, 8);
				print(str1);
	}
	
    /**
     * 修改数组大小
     * */
    public static Object arrayInc(Object obj,int len){
        Class<?>arr = obj.getClass().getComponentType();
        Object newArr =  Array.newInstance(arr, len);
        int co = Array.getLength(obj);
        System.arraycopy(obj, 0, newArr, 0, co);
        return newArr;
    }
    /**
     * 打印
     * */
    public static void print(Object obj){
        Class<?>c=obj.getClass();
        if(!c.isArray()){
            return;
        }
        System.out.println("数组长度为： "+Array.getLength(obj));
        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i)+" ");
        }
    }

	private static void anli13test() {
		int[] temp={1,2,3,4,5};
		Class<?> demo = temp.getClass().getComponentType();
		System.out.println("数组类型： " + demo.getName());
		System.out.println("数组长度  " + Array.getLength(temp));
		System.out.println("数组的第一个元素: " + Array.get(temp, 0));
		Array.set(temp, 0, 100);
		System.out.println("修改之后数组第一个元素为： " + Array.get(temp, 0));
	}

	private static void anli12test() {
		Class<?> demo = null;
	        Object obj = null;
	 
		try {
			demo = Class.forName("com.szl.tools.reflect.Person2");
			obj = demo.newInstance();
			Field field = demo.getDeclaredField("sex");
			field.setAccessible(true);
			field.set(obj, "男");
			System.out.println(field.get(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void anli11Test() {
		Class<?> demo = null;
	        Object obj=null;
	        try {
	            demo = Class.forName("com.szl.tools.reflect.Person2");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try{
	         obj=demo.newInstance();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        setter(obj,"Sex","男",String.class);
	        getter(obj,"Sex");
	}

    /**
     * @param obj
     *            操作的对象
     * @param att
     *            操作的属性
     * */
    public static void getter(Object obj, String att) {
        try {
            Method method = obj.getClass().getMethod("get" + att);
            System.out.println(method.invoke(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * @param obj
     *            操作的对象
     * @param att
     *            操作的属性
     * @param value
     *            设置的值
     * @param type
     *            参数的属性
     * */
    public static void setter(Object obj, String att, Object value,
            Class<?> type) {
        try {
            Method method = obj.getClass().getMethod("set" + att, type);
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static void anli10Test() {
		Class<?> demo = null;
        try {
            demo = Class.forName("com.szl.tools.reflect.Person2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            //调用Person类中的sayChina方法
            Method method=demo.getMethod("sayChina");
            method.invoke(demo.newInstance());
            //调用Person的sayHello方法
            method=demo.getMethod("sayHello", String.class,int.class);
            method.invoke(demo.newInstance(),"Rollen",20);
             
        }catch (Exception e) {
            e.printStackTrace();
        }
	}

	private static void anli9test() {
		Class<?> demo = null;
		
		try {
			demo = Class.forName("com.szl.tools.reflect.Person2");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		 System.out.println("===============本类属性========================");
		 Field[] field = demo.getDeclaredFields();
		
		 for (int i = 0; i < field.length; i++) {
			 //权限修饰符
			int types = field[i].getModifiers();
			String priv = Modifier.toString(types);
			//属性类型
			Class<?> fieldType = field[i].getType();
			
			System.out.println(priv + " " + fieldType.getName() + " "
                    + field[i].getName() + ";");
		}
		 
		 System.out.println("===============实现的接口或者父类的属性========================");
		 
	        // 取得实现的接口或者父类的属性
	        Field[] filed1 = demo.getFields();
	        for (int j = 0; j < filed1.length; j++) {
	            // 权限修饰符
	            int mo = filed1[j].getModifiers();
	            String priv = Modifier.toString(mo);
	            // 属性类型
	            Class<?> type = filed1[j].getType();
	            System.out.println(priv + " " + type.getName() + " "
	                    + filed1[j].getName() + ";");
	        }
	}

	private static void anli8Test() {
		Class<?> demo=null;
        try{
            demo=Class.forName("com.szl.tools.reflect.Person2");
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        Method method[]=demo.getMethods();
        for(int i=0;i<method.length;++i){
            Class<?> returnType=method[i].getReturnType();
            Class<?> para[]=method[i].getParameterTypes();
            int temp=method[i].getModifiers();
            System.out.print(Modifier.toString(temp)+" ");
            System.out.print(returnType.getName()+"  ");
            System.out.print(method[i].getName()+" ");
            System.out.print("(");
            for(int j=0;j<para.length;++j){
                System.out.print(para[j].getName()+" "+"arg"+j);
                if(j<para.length-1){
                    System.out.print(",");
                }
            }
            Class<?> exce[]=method[i].getExceptionTypes();
            if(exce.length>0){
                System.out.print(") throws ");
                for(int k=0;k<exce.length;++k){
                    System.out.print(exce[k].getName()+" ");
                    if(k<exce.length-1){
                        System.out.print(",");
                    }
                }
            }else{
                System.out.print(")");
            }
            System.out.println();
        }
	}

	private static void anli7Test() {
		Class<?> demo=null;
        try{
            demo=Class.forName("com.szl.tools.reflect.Person2");
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        Constructor<?>cons[]=demo.getConstructors();
        
        for (int i = 0; i < cons.length; i++) {
            Class<?> p[]=cons[i].getParameterTypes();
            System.out.print("构造方法：  ");
            int mo=cons[i].getModifiers();
            System.out.print(Modifier.toString(mo)+" ");
            System.out.print(cons[i].getName());
            System.out.print("(");
            for(int j=0;j<p.length;++j){
                System.out.print(p[j].getName()+" arg"+i);
                if(j<p.length-1){
                    System.out.print(",");
                }
            }
            System.out.println("){}");
        }
	}

	private static void anli6Test() {
		try {
			Class<?> demo = Class.forName("com.szl.tools.reflect.Person2");
			Constructor[] supers = demo.getConstructors();
			
			for (int i = 0; i < supers.length; i++) {
				System.out.println("构造方法："+ supers[i]);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void anli5Test() {
		try {
			Class<?> demo = Class.forName("com.szl.tools.reflect.Person2");
			Class<?> supers = demo.getSuperclass();
			
			System.out.println("继承的父类为："+supers.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void anli4Test() {
		//		【案例】 返回一个类实现的接口：
				Class<?> demo = null;
				try {
					demo = Class.forName("com.szl.tools.reflect.Person2");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				Class<?>[] inters = demo.getInterfaces();
				
				for (int i = 0; i < inters.length; i++) {
					System.out.println("实现的接口   "+inters[i].getName());
				}
	}

	private static void anli3Test() {
		//【案例】通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）
		
		Class<?> demo = null;
		try {
			demo = Class.forName("com.szl.tools.reflect.Person1");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Person1 demo1 = null;
		Person1 demo2 = null;
		Person1 demo3 = null;
		Person1 demo4 = null;
		
		Constructor<?>[] cons = demo.getConstructors();
		
		try {
			demo1 = (Person1)cons[0].newInstance("Json",29);
			demo2 = (Person1)cons[1].newInstance(19);
			demo3 = (Person1)cons[2].newInstance("Tom");
			demo4 = (Person1)cons[3].newInstance();
			
			System.out.println(demo1);
			System.out.println(demo2);
			System.out.println(demo3);
			System.out.println(demo4);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private static void anli1Test() {
		//案例1:通过对象获取完整包名和类名
		Hello demo = new Hello();
		System.out.println(demo.getClass().getName());
	}

	private static void anli2Test() {
		//案例2:实例化Class类对象
		Class<?> demo1=null;
        Class<?> demo2=null;
        Class<?> demo3=null;
        try{
            //一般尽量采用这种形式
            demo1=Class.forName("com.szl.tools.reflect.Hello");
        }catch(Exception e){
            e.printStackTrace();
        }
        demo2 = new com.szl.tools.reflect.Hello().getClass();
        demo3 = com.szl.tools.reflect.Hello.class;
         
        System.out.println("类名称   "+demo1.getName());
        System.out.println("类名称   "+demo2.getName());
        System.out.println("类名称   "+demo3.getName());
	}

	private static void AnliTest3() {
		//案例3:通过无参构造实例化其它类对象,此中方式需要注意，类必须有无参数的构造方法，否则报错
		
		Class<?>  demo = null;
		try {
			demo = Class.forName("com.szl.tools.reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(demo != null){
			try {
				Person person = (Person)demo.newInstance();
				
				person.setAge(18);
				person.setName("Tom");
				System.out.println(person);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}


interface China{
	public static final String name="Rollen";
    public static  int age=20;
    public void sayChina();
    public void sayHello(String name, int age);
}

class Person2 implements China{

	 	public Person2() {
         
	    }
	    public Person2(String sex){
	        this.sex=sex;
	    }
	    public String getSex() {
	        return sex;
	    }
	    public void setSex(String sex) {
	        this.sex = sex;
	    }
	    @Override
	    public void sayChina(){
	        System.out.println("hello ,china");
	    }
	    @Override
	    public void sayHello(String name, int age){
	        System.out.println(name+"  "+age);
	    }
	    private String sex;
	
}

class Person{
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString(){
        return "["+this.name+"  "+this.age+"]";
    }
    private String name;
    private int age;
}

class Person1{
	public Person1(){}
	
	public Person1(String name){
		this.name = name;
	}
	
	public Person1(int age){
		this.age = age;
	}
	
	public Person1(String name,int age){
		this.name = name;
		this.age = age;
	}
	
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString(){
        return "["+this.name+"  "+this.age+"]";
    }
    private String name;
    private int age;
}

class Hello{
	
}

