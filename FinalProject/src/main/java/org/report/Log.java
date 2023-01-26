package org.report;

import java.sql.SQLOutput;
import java.util.logging.Logger;

public class Log {
    public static void logger(String msg){
        ExtentManager.logStep(msg);
        System.out.println(msg);

    }
}
