package com.study.luxin.netty.recycler;

import io.netty.util.Recycler;
import org.junit.Test;

public class RecycleDemo {


    private static class User {
        private final Recycler.Handle<User> handler;

        User(Recycler.Handle<User> handler) {
            this.handler = handler;
        }

        public void recycle1(){
            handler.recycle(this);
        }
    }



    private static final Recycler<User> RECYCLER = new Recycler<User>() {
        @Override
        protected User newObject(Handle<User> handle) {
            return new User(handle);
        }
    };

    @Test
    public void test(){

        User user = RECYCLER.get();

        System.out.println(user);
        user.recycle1();

        System.out.println(user);

        User user1 = RECYCLER.get();

        assert user==user1;
    }

}
