package com.study.luxin.ser.model;

import lombok.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Data
public class UserWithTransientNeedSerialize implements Serializable {
    private String name;
    private int age;

    private transient NotSerializeName transientField;

    /**
     * ObjectInputStream读取数据的顺序和ObjectOutputStream写入数据的顺序是一致的
     *
     * @param objectOutputStream
     */
    private void writeObject(ObjectOutputStream objectOutputStream) {
        try {
            objectOutputStream.defaultWriteObject(); // 非静态和非transient标记的字段write

            objectOutputStream.writeObject("lu");
            objectOutputStream.writeInt(5);
            objectOutputStream.writeObject("XXXXXX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
        try {
            is.defaultReadObject();

            String lastName = (String) is.readObject();
            int nameLength = is.readInt();
            transientField = new NotSerializeName();
            transientField.setLastName(lastName);
            transientField.setNameLength(nameLength);

            String xxx = (String) is.readObject();
            assert xxx.equals("XXXXXX");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}




