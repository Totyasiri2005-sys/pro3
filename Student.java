package project3;

import java.util.Arrays;

public abstract class Student implements MarkCalculations {
    protected String kkuId;
    protected String name;
    protected int numCourses;
    protected double[] marks;

    public Student(String kkuId, String name, int numCourses) {
        this.kkuId = kkuId;
        this.name = name;
        this.numCourses = numCourses;
        this.marks = new double[numCourses];
    }

    public abstract double getPassingMark();
    public abstract String getType();

    public void setMarks(double[] marks) {
        this.marks = marks;
    }

    public double[] getMarks() {
        return marks;
    }

    public String getKkuId() {
        return kkuId;
    }

    public String getName() {
        return name;
    }

    public int getNumCourses() {
        return numCourses;
    }

    @Override
    public double calculateAverage() {
        double sum = 0;
        for (double m : marks) sum += m;
        return sum / marks.length;
    }

    @Override
    public double findMax() {
        double max = marks[0];
        for (double m : marks) if (m > max) max = m;
        return max;
    }

    @Override
    public double findMin() {
        double min = marks[0];
        for (double m : marks) if (m < min) min = m;
        return min;
    }

    @Override
    public boolean isPass() {
        return calculateAverage() >= getPassingMark();
    }

    public void displayStudentDetails() {
        System.out.println("\n===== Student Details =====");
        System.out.println("ID: " + kkuId);
        System.out.println("Name: " + name);
        System.out.println("Courses: " + numCourses);
        System.out.println("Marks: " + Arrays.toString(marks));
        System.out.println("Average: " + calculateAverage());
        System.out.println("Max: " + findMax());
        System.out.println("Min: " + findMin());
        System.out.println(isPass() ? "PASS" : "FAIL");
    }
}