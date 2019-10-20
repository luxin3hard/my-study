package com.study.luxin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PersonHandler<Person> implements InvocationHandler {

    private Person person;

    public PersonHandler(Person person) {
        this.person = person;
    }

    public Person getPersonProxy() {
        return (Person) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(person, args);
        return result;
    }
}
