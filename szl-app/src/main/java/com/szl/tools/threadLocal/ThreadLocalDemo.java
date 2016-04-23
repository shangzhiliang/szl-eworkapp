package com.szl.tools.threadLocal;

/**
 * threadlocal 实现
 * 原理:在每个执行线程生成一个共享资源变量的副本，
 * 应用场景: 获取共享资源，例如：数据库连接，hibernate的session对象。
 * 缺点：创建好多副本，消耗资源
 * @author lx
 *
 */
public class ThreadLocalDemo {

	private ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
		protected Long initialValue() {
			return Thread.currentThread().getId();
		};
	};
	
	private ThreadLocal<String> stringLocal = new ThreadLocal<String>(){
		//初始化即设置local变量，保证get()不报null
		protected String initialValue() {
			return Thread.currentThread().getName();
		};
	};
	
	public void set(){
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}
	
	public Long getLong(){
		return longLocal.get();
	}
	
	public String getString(){
		return stringLocal.get();
	}
	
	public static void main(String[] args) {
		final ThreadLocalDemo tdemo = new ThreadLocalDemo();
//		tdemo.set();
		
		System.out.println(tdemo.getLong());
		System.out.println(tdemo.getString());
		
		Thread thread1 = new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println("---------------------------");
//				tdemo.set();
				System.out.println(tdemo.getLong());
				System.out.println(tdemo.getString());
				System.out.println("---------------------------");
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		thread1.start();
		try {
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		System.out.println(tdemo.getLong());
		System.out.println(tdemo.getString());
	}
}
