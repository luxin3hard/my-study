package com.study.luxin.proxy;

public class PersonStaticPoxy implements Person {

    private Person person;

    public PersonStaticPoxy(Person person) {
        this.person = person;
    }

    @Override
    public String speak() {
        return person.speak();
    }

    @Override
    public void doSomething() {
        person.doSomething();
    }
}
