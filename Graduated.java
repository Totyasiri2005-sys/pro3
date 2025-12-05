package project3;
public class Graduated extends Student {

    public Graduated(String id, String name, int numCourses) {
        super(id, name, numCourses);
    }

    @Override
    public double getPassingMark() {
        return 80;
    }

    @Override
    public String getType() {
        return "Graduate";
    }
}