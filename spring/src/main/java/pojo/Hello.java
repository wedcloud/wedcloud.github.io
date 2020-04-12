package pojo;

/**
 * @Author 许海斌
 * @create 2020/4/12 0012 14:57
 */
public class Hello {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hello() {
    System.out.println("无参构造");
    }

    @Override
    public String toString() {
        return "Hello{" +
                "name='" + name + '\'' +
                '}';
    }
}
