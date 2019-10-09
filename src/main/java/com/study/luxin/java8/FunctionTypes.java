package com.study.luxin.java8;

/**
 *
 接口	方法	示例
 UnaryOperator	T apply(T t)	String::toLowerCase
 BinaryOperator	T apply(T t1, T t2)	BigInteger::add
 Predicate	boolean test(T t)	Collection::isEmpty
 Function<T,R>	R apply(T t)	Arrays::asList
 Supplier	T get()	Instant::now
 Consumer	void accept(T t)	System.out::println
 */
public class FunctionTypes {
}
