package com.szl.runtime;

/**
 * ���Թ��ӳ������
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
	
	//��ӹ��ӳ����ڽ���ֹͣ����һЩ����
	private void doShutDownWork() {
        Runtime run=Runtime.getRuntime();//��ǰ Java Ӧ�ó�����ص�����ʱ����  
        run.addShutdownHook(new Thread(){ //ע���µ���������رչ���  
            @Override  
            public void run() {
                //�������ʱ���еĲ���  
                System.out.println("�����������" + params);  
            }  
        });  
    } 
}
