package com.szl.scripteRun;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;


/**
 * 测试java执行script脚本或js文件
 * @author shangzhiliang
 *
 */
public class TestScriptRunner {

	@Test
	public void test() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");

//		File script = new File("source/ajava.js");
//		try {   
//			Reader reader = new FileReader(script);
//			engine.eval(reader);
////			String obj = (String)engine.get("obj");
//			
//			String obj2 = (String)engine.get("dc");
//			System.out.println("\r\n"+obj2.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		init();
		
		
	}

	  private Context cx;
	  private Scriptable scope;
	  
	  public void init() {
		     cx = ContextFactory.getGlobal().enterContext();
		     scope = cx.initStandardObjects(null);
		     cx.setOptimizationLevel(-1);
		     cx.setLanguageVersion(Context.VERSION_1_7);
		     String[] file = { "source/ajava.js"};
		     for (String f : file) {
		
		         evaluateJs(f);
		
		     }
		
//		     scope.put("util", scope, util);
		
	  }	
	  
	  
	  protected void evaluateJs(String f) {
			     try {
			
			         FileReader in = null;
			
			         in = new FileReader(f);
			
			         cx.evaluateReader(scope, in, f, 1, null);
			
			     } catch (Exception e1) {
			
			         e1.printStackTrace();
			
			     } 
		
	  }
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
}
