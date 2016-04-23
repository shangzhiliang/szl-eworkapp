package com.szl.runtime;

/**
 * 测试钩子程序调用
 * @author shangzhiliang
 *
 */
public class RuntimeTest extends Thread{

	String params = null;
	
	public RuntimeTest(){
		doShutDownWork();
	}
	
	public void run(){
		System.out.println("-- method run ....");
		try {
			params = "=========";
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("-- wait over  ....");
	}
	
	//添加钩子程序，在进程停止后做一些操作
	private void doShutDownWork() {
        Runtime run=Runtime.getRuntime();//当前 Java 应用程序相关的运行时对象。  
        run.addShutdownHook(new Thread(){ //注册新的虚拟机来关闭钩子  
            @Override  
            public void run() {
                //程序结束时进行的操作  
                System.out.println("程序结束调用" + params);  
            }  
        });  
    } 
}
