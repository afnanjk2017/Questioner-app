package com.example.questioner;

public class Result {

    String name , topic , mark;

    public Result() {
    }

    public Result(String name, String topic, String mark) {
        this.name = name;
        this.topic = topic;
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
