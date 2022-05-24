package com.ycyoes.demos.geetime.concurrent.practise.chapter42;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class ActorTest {

    public static void main(String[] args) {
        //创建Actor系统
        ActorSystem system = ActorSystem.create("HelloSystem");
        //创建HelloActor
        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class));
        //发消息给HelloActor
        helloActor.tell("Actor", ActorRef.noSender());
    }




    //该Actor当收到消息message后，//会打印Hello message
    static class HelloActor extends UntypedActor {

        @Override
        public void onReceive(Object message) throws Throwable {
            System.out.println("Hello " + message);
        }
    }
}


