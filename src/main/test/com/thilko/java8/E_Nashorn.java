package com.thilko.java8;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class E_Nashorn {

    // run standalone javascript applications with jrunscript (jjs)


    @Test
    public void getTheEngine() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello World!');");
    }

}
