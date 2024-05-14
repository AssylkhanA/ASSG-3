public class MyTestingClass {
    private int id;
    private String name;
    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public int hashCode() {
        final int prime = 37;
        int res = 1;
        res = prime * res + id;
        res = prime * res + (name == null ? 0 : name.hashCode());
        return res;
    }
}
