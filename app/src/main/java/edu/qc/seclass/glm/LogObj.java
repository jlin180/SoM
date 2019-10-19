package edu.qc.seclass.glm;

import java.text.DateFormat;
import java.util.Date;

public class LogObj {
    String log;
    String DateTime;
    Double Happy;

    public LogObj(){}

    public LogObj(String l, Double h){
        log = l;
        DateTime = DateFormat.getDateTimeInstance().format(new Date());
        Happy = h;
    }
}
