package com.ycyoes.demos.aliyun.mldn.basic;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        IMessage temp = MessageUtil.getMessage().get();
        MessageUtil.useMessage(temp);
    }
}

class MessageUtil {
    private MessageUtil() {}
    public static Optional<IMessage> getMessage() {
        return Optional.ofNullable(new MessageImpl());
    }
    public static void useMessage(IMessage msg) {
        System.out.println(msg.getContent());    //有可能出现空指针
    }
}

interface IMessage {
    public String getContent();
}

class MessageImpl implements IMessage {
    @Override
    public String getContent() {
        return null;
//        return "www.ycyoes.com";
    }
}