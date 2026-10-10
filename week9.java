import java.util.*;
import java.time.LocalDate;

// ---------- Problem 1: Garden Plot Area ----------
abstract class Plot {
    String owner;
    Plot(String owner) { this.owner = owner; }
    abstract double area();
    abstract String getShape();
}

class CirclePlot extends Plot {
    double radius;
    CirclePlot(String owner, double radius) { super(owner); this.radius = radius; }
    double area() { return Math.PI * radius * radius; }
    String getShape() { return "CIRCLE"; }
}

class RectanglePlot extends Plot {
    double length, width;
    RectanglePlot(String owner, double length, double width) { super(owner); this.length = length; this.width = width; }
    double area() { return length * width; }
    String getShape() { return "RECTANGLE"; }
}

class TrianglePlot extends Plot {
    double base, height;
    TrianglePlot(String owner, double base, double height) { super(owner); this.base = base; this.height = height; }
    double area() { return 0.5 * base * height; }
    String getShape() { return "TRIANGLE"; }
}

// ---------- Problem 2: Weekly Staff Pay ----------
abstract class Staff {
    String name;
    Staff(String name) { this.name = name; }
    abstract double pay();
}

class FullTimeStaff extends Staff {
    double weeklySalary;
    FullTimeStaff(String name, double weeklySalary) { super(name); this.weeklySalary = weeklySalary; }
    double pay() { return weeklySalary; }
}

class HourlyStaff extends Staff {
    int hours; double rate;
    HourlyStaff(String name, int hours, double rate) { super(name); this.hours = hours; this.rate = rate; }
    double pay() {
        if (hours <= 40) return hours * rate;
        else return 40 * rate + (hours - 40) * rate * 1.5;
    }
}

class InternStaff extends Staff {
    double stipend;
    InternStaff(String name, double stipend) { super(name); this.stipend = stipend; }
    double pay() { return stipend; }
}

// ---------- Problem 3: Library Late Fine ----------
abstract class LibraryItem {
    String title; int daysLate;
    LibraryItem(String title, int daysLate) { this.title = title; this.daysLate = daysLate; }
    abstract double fine();
}

class BookItem extends LibraryItem {
    BookItem(String title, int daysLate) { super(title, daysLate); }
    double fine() { return daysLate * 2; }
}

class DvdItem extends LibraryItem {
    DvdItem(String title, int daysLate) { super(title, daysLate); }
    double fine() { return Math.min(50, daysLate * 5); }
}

class MagazineItem extends LibraryItem {
    MagazineItem(String title, int daysLate) { super(title, daysLate); }
    double fine() { return daysLate * 1; }
}

// ---------- Problem 4: Electricity Billing ----------
abstract class Connection {
    int units;
    Connection(int units) { this.units = units; }
    abstract double bill();
    abstract String getType();
}

class HomeConnection extends Connection {
    HomeConnection(int units) { super(units); }
    double bill() {
        if (units <= 100) return units * 5;
        else return 100 * 5 + (units - 100) * 7;
    }
    String getType() { return "HOME"; }
}

class ShopConnection extends Connection {
    ShopConnection(int units) { super(units); }
    double bill() { return units * 8 + 100; }
    String getType() { return "SHOP"; }
}

class FactoryConnection extends Connection {
    FactoryConnection(int units) { super(units); }
    double bill() { return Math.max(1000, units * 6); }
    String getType() { return "FACTORY"; }
}

// ---------- Problem 5: Travel Booking ----------
abstract class Booking {
    double distance;
    static final double BOOKING_FEE = 50; // one place only
    Booking(double distance) { this.distance = distance; }
    abstract double baseFare();
    double totalFare() { return baseFare() + BOOKING_FEE; }
    abstract String getMode();
}

class BusBooking extends Booking {
    BusBooking(double distance) { super(distance); }
    double baseFare() { return distance * 2; }
    String getMode() { return "BUS"; }
}

class TrainBooking extends Booking {
    TrainBooking(double distance) { super(distance); }
    double baseFare() { return distance * 1.5; }
    String getMode() { return "TRAIN"; }
}

class FlightBooking extends Booking {
    FlightBooking(double distance) { super(distance); }
    double baseFare() { return 2500 + distance * 4; }
    String getMode() { return "FLIGHT"; }
}

// ---------- Main Demo ----------
public class week9 {
    public static void main(String[] args) {
        // Problem 1: Garden Plot Area
        Plot[] plots = {
            new CirclePlot("Asha",5),
            new RectanglePlot("Ravi",4,6),
            new TrianglePlot("Neha",10,3)
        };
        double totalArea = 0;
        for (Plot p : plots) {
            double a = p.area();
            System.out.printf("%s (%s): %.2f%n", p.owner, p.getShape(), a);
            totalArea += a;
        }
        System.out.printf("Total Area: %.2f%n%n", totalArea);

        // Problem 2: Weekly Staff Pay
        Staff[] staff = {
            new FullTimeStaff("Asha",12000),
            new HourlyStaff("Ravi",45,200),
            new InternStaff("Neha",5000)
        };
        double totalPayroll = 0;
        for (Staff s : staff) {
            double pay = s.pay();
            System.out.printf("%s: %.2f%n", s.name, pay);
            totalPayroll += pay;
        }
        System.out.printf("Total Payroll: %.2f%n%n", totalPayroll);

        // Problem 3: Library Late Fine
        LibraryItem[] items = {
            new BookItem("Algebra",4),
            new DvdItem("Inception",12),
            new MagazineItem("Sports",3)
        };
        double totalFine = 0;
        for (LibraryItem li : items) {
            double f = li.fine();
            System.out.printf("%s: %.2f%n", li.title, f);
            totalFine += f;
        }
        System.out.printf("Total Fines: %.2f%n%n", totalFine);

        // Problem 4: Electricity Billing
        Connection[] connections = {
            new HomeConnection(150),
            new ShopConnection(90),
            new FactoryConnection(120)
        };
        double totalBill = 0;
        for (Connection c : connections) {
            double b = c.bill();
            System.out.printf("%s: %.2f%n", c.getType(), b);
            totalBill += b;
        }
        System.out.printf("Total: %.2f%n%n", totalBill);

        // Problem 5: Travel Booking
        Booking[] bookings = {
            new BusBooking(200),
            new TrainBooking(300),
            new FlightBooking(500)
        };
        double totalFare = 0;
        for (Booking b : bookings) {
            double fare = b.totalFare();
            System.out.printf("%s: %.2f%n", b.getMode(), fare);
            totalFare += fare;
        }
        System.out.printf("Total: %.2f%n", totalFare);
    }
}
