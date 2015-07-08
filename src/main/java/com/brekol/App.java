package com.brekol;

import com.brekol.console.TestRunner;
import com.brekol.file.FileReader;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {
        final App app = new App();
        app.run();
    }



    private void run() {
        TestRunner testRunner = new TestRunner();
        testRunner.runTest();
        
    }
}
