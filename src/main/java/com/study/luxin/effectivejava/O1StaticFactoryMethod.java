package com.study.luxin.effectivejava;

/**
 * 静态工厂方法代替构造器
 */
public class O1StaticFactoryMethod {


    static class Duck {
        protected int weight;

        public static final Duck tinyDuck = new Duck(1);

        public Duck(int weight) {
            this.weight = weight;
        }


        /**
         * 优点1: 有具体名字; 构造器只能有一个名字,不同的构造器通过参数不同,使人迷惑.
         */
        public static Duck fatDuck() {
            return new Duck(100);
        }


        /**
         * 优点2: 不用每次都新建,使用预先构造好的数据.
         */
        public static Duck ofTiny() {
            return tinyDuck;
        }


        /**
         * 优点3: 可以返回该类的任何子类,构造器只能返回当前类的对象. 提高灵活性,隐藏实现细节
         * <p>
         * 优点4: 可以根据参数不同,返回不同类型的实现.
         */
        public static Duck subDuck(boolean isFat) {
            if (isFat) {
                return fatDuck();
            } else {
                return new ChildDuck(1);
            }
        }

        private static class ChildDuck extends Duck {
            public ChildDuck(int weight) {
                super(weight);
            }

            public void test() {
                System.out.println(weight);
            }
        }

        /**
         * 缺点,类如果没有 公有 或是 protected 不能被子类化
         */


    }


}
