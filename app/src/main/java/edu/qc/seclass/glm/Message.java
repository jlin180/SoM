package edu.qc.seclass.glm;

import java.io.Serializable;

public class Message implements Serializable {
    public String id, message;


    public Message() {
    }

    public Message(String mess){
        this.message = mess;
    }

    public Message(String id, String message, String createdAt) {
        this.id = id;
        this.message = message;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
