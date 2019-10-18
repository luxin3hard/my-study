package json;

public class BigDuck implements Duck {

    private String name;
    public int age;

    private int wight;

    @Override
    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public void setAge(int myAge) {
        this.age = myAge;
    }

    public void setWightL(int wight) {
        this.wight = wight;
    }

    public int getWight() {
        return wight;
    }


}
