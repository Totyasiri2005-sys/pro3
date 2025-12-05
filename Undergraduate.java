package project3;

public class Undergraduate extends Student {
    public Undergraduate(String kkuId, String name, int numCourses) {
        super(kkuId, name, numCourses);
    }

    @Override
    public double getPassingMark() {
        return 60;
    }

    @Override
    public String getType() {
        return "U";
    }
}