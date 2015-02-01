package com.thilko.java8;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;

public class E_Nashorn {

    // run standalone javascript applications with jrunscript (jjs)


    @Test
    public void evaluateJavaScript() throws ScriptException, FileNotFoundException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        // as text
        engine.eval("print('Hello World!');");

        // as io stream
        engine.eval(new java.io.FileReader("script.js"));
    }

    @Test
    public void defineGlobalVars() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        File f = new File(".gitignore");

        // define global var here
        engine.put("file", f);

        // evaluate JavaScript code and access the variable
        engine.eval("print(file.getAbsolutePath())");
    }

    @Test
    public void invocable() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        // evaluate JavaScript code that defines a function with one parameter
        engine.eval("function hello(name) { print('Hello, ' + name) }");

        // create an Invocable object by casting the script engine object
        Invocable inv = (Invocable) engine;

        // invoke the function named "hello" with "Scripting!" as the argument
        inv.invokeFunction("hello", "Scripting!");
    }

    @Test
    public void invocableAndRunnable() throws InterruptedException, ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        // evaluate JavaScript code that defines a function with one parameter
        engine.eval("var obj = new Object()");
        engine.eval("obj.run = function() { print('obj.run() method called') }");

        // expose object defined in the script to the Java application
        Object obj = engine.get("obj");

        // create an Invocable object by casting the script engine object
        Invocable inv = (Invocable) engine;

        // get Runnable interface object
        Runnable r = inv.getInterface(obj, Runnable.class);

        // start a new thread that runs the script
        Thread th = new Thread(r);
        th.start();
        th.join();
    }

}
