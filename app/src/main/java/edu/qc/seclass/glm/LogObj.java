package edu.qc.seclass.glm;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class LogObj implements Serializable {
    public String log;
    public String DateTime;
    public Double Happy;

    public LogObj(){}

    public LogObj(String l, Double h){
        log = l;
        DateTime = DateFormat.getDateTimeInstance().format(new Date());
        Happy = h;
    }
}
