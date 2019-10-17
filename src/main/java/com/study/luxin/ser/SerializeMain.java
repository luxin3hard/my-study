package com.study.luxin.ser;

import com.study.luxin.ser.model.*;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

/**
 * 参考的地址
 * https://www.oschina.net/translate/serialization-in-java
 * <p>总结:
 * - 序列化是java对象的values/states转化为字节并在网络中传输或者保存它的过程。另外反序列化是把字节码翻译为对应的对象的过程。
 * <p>
 * - 序列化的好处是，JVM的独立性，也就是说，一个对象可以在一个平台中被序列化，然后在另外一个不同的平台反序列化。
 * <p>
 * - 如果你需要序列化任何对象，你必须实现标记接口Serializable。
 * <p>
 * - Java中的标记接口（Marker interface）就是没有字段或者方法的接口，或者更简单的说，空接口
 * <p>
 * - serialVersionUID 是用于保证同一个对象（在序列化中会被用到）可以在Deserialization过程中被载入。serialVersionUID 是用于对象的版本控制。
 * <p>
 * - 当你需要序列化任何包含引用对象的对象，那么Java会自动序列化该对象的整个对象图。
 * <p>
 * - 如果你不希望序列化某个字段，你可以标记它为 transient
 * <p>
 * - 如果父类为可序列化，那么它的继承类也将是可序列化的。
 * <p>
 * - 如果父类为非可序列化，那么在反序列化过程中，所有继承于父类的实例变量值将会通过调用非可序列化的构造器来初始化。
 * <p>
 * - 如果你需希望子类为不能序列化的，那么你需要实现writeObject() 和 readObject() 方法，并在这两个方法中抛出NotSerializableException异常
 * <p>
 * - 你不能序列化静态变量。
 */

/**
 * (1)如果父类是可序列化的,那么所有子类都是可以序列化的
 */
public class SerializeMain {

    public static void encode(Object object, File file) throws IOException {
        FileOutputStream fileOut = null;
        ObjectOutputStream outStream = null;
        try {
            fileOut = new FileOutputStream(file);
            outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (fileOut != null) {
                    fileOut.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static <T> T decode(File file) {
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (T) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public <T> T encodeAndDecode(T object) throws URISyntaxException, IOException {
        URL serializeTest = this.getClass().getClassLoader().getResource("serializeTest");

        File file = new File(Objects.requireNonNull(serializeTest).toURI());

        if (file.exists()) {
            if (file.delete()) {
                file.createNewFile();
            }
        }

        encode(object, file);
        return decode(file);
    }


    @Test
    public void test() throws URISyntaxException, IOException {
        User user = new User();
        user.setAge(19);
        user.setName("luxin");


        User decodedUser = encodeAndDecode(user);
        assert decodedUser.getAge() == 19;
    }


    @Test
    /**
     * 数据不为null的时候,才会序列化. 为null,不会序列化
     */
    public void test01() {
        User1HaveNotSerializableField user1HaveNotSerializableField = new User1HaveNotSerializableField();
        user1HaveNotSerializableField.setAge(18);

        // 如果为空,不会导致报错. 如果不为空,会发生序列化,对象没有 Serializable会报错
        user1HaveNotSerializableField.setNotSerializableField(new Object());
        //user1HaveNotSerializableField.setNotSerializableField(null);
        try {
            User1HaveNotSerializableField serializableField = encodeAndDecode(user1HaveNotSerializableField);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Test
    /**
     * transient 标识,不会序列化
     */
    public void test02() throws IOException, URISyntaxException {
        UserWithTransient withTransient = new UserWithTransient();
        withTransient.setAge(19);
        withTransient.setTransientField(new Object());

        UserWithTransient userWithTransient = encodeAndDecode(withTransient);
        assert userWithTransient.getTransientField() == null;
        assert userWithTransient.getAge() == 19;
    }


    @Test
    /**
     * transient 标识,特殊处理来序列化
     */
    public void test03() throws IOException, URISyntaxException {
        UserWithTransientNeedSerialize userWithTransientNeedSerialize = new UserWithTransientNeedSerialize();
        userWithTransientNeedSerialize.setAge(19);
        NotSerializeName notSerializeName = new NotSerializeName();
        notSerializeName.setNameLength(6);
        userWithTransientNeedSerialize.setTransientField(notSerializeName);

        UserWithTransientNeedSerialize userWithTransient = encodeAndDecode(userWithTransientNeedSerialize);
        assert userWithTransient.getTransientField().getNameLength() == 5;
        assert userWithTransient.getAge() == 19;
    }


    @Test
    /**
     * son 继承 father,但是father没有实现 Serializable,继承自父类的域,就是初始化的值
     */
    public void test04() throws IOException, URISyntaxException {

        // w为static,不会序列化,反序列化的值,还是 static的值,不会改变
        Son son = new Son(1, 2, 3, 4, 20);

        System.out.println(Son.f);

        Son userWithTransient = encodeAndDecode(son);
        // 父类的值都是 初始化的值,不是付值之后的值
        System.out.println(userWithTransient);


        // f这个static相同的原因是在同一个jvm里面
        System.out.println(userWithTransient.f);
    }


    /**
     * 如果父类序列化了,但是子类不想序列化,那么你需要实现 writeObject() 和readObject() 方法，并且需要抛出NotSerializableException 异常。
     */

    @Test
    public void test05() throws URISyntaxException {
        URL serializeTest = this.getClass().getClassLoader().getResource("serializeTest");

        File file = new File(Objects.requireNonNull(serializeTest).toURI());
        Son son = decode(file);

        // 如果从另个jvm取static,返回的值是是老的值 10,不是改变后的值
        System.out.println(son);
    }

}
