package com.example.atguigu.config;

public enum BuiltinExceptionType {

Direct("direct"),FANOUT("fanout"),TOPIC("topic"),HEANDER("headers");

private final String typec;

    BuiltinExceptionType(String typec){
        this.typec=typec;
    }
    public String getType(){
        return typec;
    }

}
