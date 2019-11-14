package classloader;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = null;
        try {
            bytes = getClassData(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(bytes, 0, bytes.length);
    }

    ///Users/luxin/IdeaProjects/fs-crm/fs-crm-sales/target/classes/com/facishare/crm/sfa/predefine/enums
    private byte[] getClassData(String name) throws IOException {
        String fileAllPath = name + "/ActionCodeEnum.class";
        try (InputStream in = new FileInputStream(fileAllPath); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[1024];
            int num;
            while ((num = in.read(buffer)) != -1) {
                out.write(buffer, 0, num);
            }
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }


    @Test
    public void test() throws ClassNotFoundException {
        Class<?> aClass = findClass("/Users/luxin/IdeaProjects/fs-crm/fs-crm-sales/target/classes/com/facishare/crm/sfa/predefine/enums");

        System.out.println(aClass);
    }
}
