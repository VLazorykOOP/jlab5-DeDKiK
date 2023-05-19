import java.io.*;
import java.util.*;

abstract class Body implements Comparable<Body> {
    abstract double getSurfaceArea();
    abstract double getVolume();

    @Override
    public int compareTo(Body other) {
        double thisVolume = this.getVolume();
        double otherVolume = other.getVolume();
        if (thisVolume < otherVolume) {
            return -1;
        } else if (thisVolume > otherVolume) {
            return 1;
        } else {
            return 0;
        }
    }
}

class Parallelepiped extends Body {
    private double a, b, c;

    public Parallelepiped(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getSurfaceArea() {
        return 2 * (a * b + b * c + a * c);
    }

    @Override
    public double getVolume() {
        return a * b * c;
    }

    @Override
    public String toString() {
        return "Parallelepiped: a = " + a + ", b = " + b + ", c = " + c +
                ", surface area = " + getSurfaceArea() + ", volume = " + getVolume();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Parallelepiped) {
            Parallelepiped other = (Parallelepiped) obj;
            return this.a == other.a && this.b == other.b && this.c == other.c;
        }
        return false;
    }
}

class Ball extends Body {
    private double radius;
    private static final double PI = 3.14159;

    public Ball(double radius) {
        this.radius = radius;
    }

    @Override
    public double getSurfaceArea() {
        return 4 * PI * radius * radius;
    }

    @Override
    public double getVolume() {
        return 4 * PI * radius * radius * radius / 3;
    }

    @Override
    public String toString() {
        return "Ball: radius = " + radius + ", surface area = " + getSurfaceArea() + ", volume = " + getVolume();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ball) {
            Ball other = (Ball) obj;
            return this.radius == other.radius;
        }
        return false;
    }
}

public class RecordManager {
    public static void main(String[] args) {
        ArrayList<Body> parallelepipeds = new ArrayList<>();
        ArrayList<Body> balls = new ArrayList<>();
        try (BufferedReader parallelepipedReader = new BufferedReader(new FileReader("parallelepipeds.txt"));
             BufferedReader ballReader = new BufferedReader(new FileReader("balls.txt"))) {
            String line;
            while ((line = parallelepipedReader.readLine()) != null) {
                String[] dimensions = line.split(",");
                double a = Double.parseDouble(dimensions[0]);
                double b = Double.parseDouble(dimensions[1]);
                double c = Double.parseDouble(dimensions[2]);
                if (a > 0 && b > 0 && c > 0) {
                    Parallelepiped p = new Parallelepiped(a, b, c);
                    parallelepipeds.add(p);
                } else {
                    System.out.println("Invalid dimensions found in parallelepipeds.txt. Please enter the dimensions manually:");
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter 'a' dimension: ");
                    a = scanner.nextDouble();
                    System.out.print("Enter 'b' dimension: ");
                    b = scanner.nextDouble();
                    System.out.print("Enter 'c' dimension: ");
                    c = scanner.nextDouble();
                    Parallelepiped p = new Parallelepiped(a, b, c);
                    parallelepipeds.add(p);
                }
            }
            while ((line = ballReader.readLine()) != null) {
                double radius = Double.parseDouble(line);
                if (radius > 0) {
                    Ball b = new Ball(radius);
                    balls.add(b);
                } else {
                    System.out.println("Invalid radius found in balls.txt. Please enter the radius manually:");
                    Scanner scanner = new Scanner(System.in);
                    radius = scanner.nextDouble();
                    Ball b = new Ball(radius);
                    balls.add(b);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        Collections.sort(parallelepipeds);
        System.out.println("Sorted parallelepipeds:");
        for (Body p : parallelepipeds) {
            System.out.println(p.toString());
        }

        Collections.sort(balls);
        System.out.println("Sorted balls:");
        for (Body b : balls) {
            System.out.println(b.toString());
        }
    }
}
