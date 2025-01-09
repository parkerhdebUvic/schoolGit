package LectureFiles.Day3;

public class MathCalculations {
    public static int calcPerimeter(int length, int width) {
        return (2*length) + (2*width);
    }

    public static int calcArea(int length, int width) {
        return length*width;
    }

    public static double calcArea(double radius) {
        return Math.PI * radius * radius;
    }
}
