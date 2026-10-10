import java.util.*;

// ---------- Problem 1: Movie Ticket Counter ----------
abstract class Ticket {
    int count;
    static final double FEE = 20; // one place only
    Ticket(int count) { this.count = count; }
    abstract double basePrice();
    double totalAmount() { return count * (basePrice() + FEE); }
    abstract String getType();
}

class RegularTicket extends Ticket {
    RegularTicket(int count) { super(count); }
    double basePrice() { return 150; }
    String getType() { return "REGULAR"; }
}

class PremiumTicket extends Ticket {
    PremiumTicket(int count) { super(count); }
    double basePrice() { return 250; }
    String getType() { return "PREMIUM"; }
}

class ReclinerTicket extends Ticket {
    ReclinerTicket(int count) { super(count); }
    double basePrice() { return 400; }
    String getType() { return "RECLINER"; }
}

// ---------- Problem 2: Parcel Shipping Desk ----------
abstract class Parcel {
    double weight, value;
    Parcel(double weight, double value) { this.weight = weight; this.value = value; }
    abstract double charge();
    double insurance() { return 0; } // default
    double total() { return charge() + insurance(); }
    abstract String getType();
}

class StandardParcel extends Parcel {
    StandardParcel(double w, double v) { super(w,v); }
    double charge() { return 40 + 10*weight; }
    String getType() { return "STANDARD"; }
}

class ExpressParcel extends Parcel {
    ExpressParcel(double w, double v) { super(w,v); }
    double charge() { return 80 + 15*weight; }
    double insurance() { return value * 0.02; }
    String getType() { return "EXPRESS"; }
}

class FragileParcel extends Parcel {
    FragileParcel(double w, double v) { super(w,v); }
    double charge() { return 40 + 10*weight + 50; }
    double insurance() { return value * 0.02; }
    String getType() { return "FRAGILE"; }
}

// ---------- Problem 3: College Fee Counter ----------
abstract class Student {
    String name;
    Student(String name) { this.name = name; }
    abstract double fee();
}

class DayScholar extends Student {
    DayScholar(String name) { super(name); }
    double fee() { return 40000 + 12000; }
}

class Hosteller extends Student {
    Hosteller(String name) { super(name); }
    double fee() { return 40000 + 60000; }
}

class Scholar extends Student {
    Scholar(String name) { super(name); }
    double fee() { return 20000 + 12000; }
}

// ---------- Problem 4: City Cab Fare Meter ----------
abstract class Cab {
    double km;
    String time;
    Cab(double km, String time) { this.km = km; this.time = time; }
    abstract double rate();
    abstract String getType();
    boolean supportsNight() { return false; }
    double fare() {
        double f = Math.max(100, km * rate());
        if (time.equals("NIGHT")) {
            if (!supportsNight()) return -1; // rejected
            f *= 1.2;
        }
        return f;
    }
}

class MiniCab extends Cab {
    MiniCab(double km, String time) { super(km,time); }
    double rate() { return 10; }
    String getType() { return "MINI"; }
}

class SedanCab extends Cab {
    SedanCab(double km, String time) { super(km,time); }
    double rate() { return 14; }
    String getType() { return "SEDAN"; }
    boolean supportsNight() { return true; }
}

class SuvCab extends Cab {
    SuvCab(double km, String time) { super(km,time); }
    double rate() { return 18; }
    String getType() { return "SUV"; }
    boolean supportsNight() { return true; }
}

// ---------- Problem 5: Home Appliance Energy Report ----------
abstract class Appliance {
    double hours;
    boolean saver;
    Appliance(double hours, boolean saver) { this.hours = hours; this.saver = saver; }
    abstract double power();
    abstract String getType();
    boolean supportsSaver() { return false; }
    String report() {
        if (saver && !supportsSaver()) return getType() + ": saver mode not supported";
        double units = (power() * hours)/1000.0;
        if (saver) units *= 0.75;
        double cost = units * 8;
        return String.format("%s: Units=%.2f Cost=%.2f", getType(), units, cost);
    }
    double cost() {
        if (saver && !supportsSaver()) return 0;
        double units = (power() * hours)/1000.0;
        if (saver) units *= 0.75;
        return units * 8;
    }
}

class Fridge extends Appliance {
    Fridge(double h, boolean s) { super(h,s); }
    double power() { return 150; }
    String getType() { return "FRIDGE"; }
}

class AC extends Appliance {
    AC(double h, boolean s) { super(h,s); }
    double power() { return 1500; }
    String getType() { return "AC"; }
    boolean supportsSaver() { return true; }
}

class TV extends Appliance {
    TV(double h, boolean s) { super(h,s); }
    double power() { return 100; }
    String getType() { return "TV"; }
}

class Washer extends Appliance {
    Washer(double h, boolean s) { super(h,s); }
    double power() { return 500; }
    String getType() { return "WASHER"; }
    boolean supportsSaver() { return true; }
}

// ---------- Main Demo ----------
public class week9hw {
    public static void main(String[] args) {
        // Problem 1
        Ticket[] tickets = {
            new RegularTicket(3),
            new PremiumTicket(2),
            new ReclinerTicket(1)
        };
        double totalTickets = 0;
        for (Ticket t : tickets) {
            double amt = t.totalAmount();
            System.out.printf("%s: %.2f%n", t.getType(), amt);
            totalTickets += amt;
        }
        System.out.printf("Total: %.2f%n%n", totalTickets);

        // Problem 2
        Parcel[] parcels = {
            new StandardParcel(3,500),
            new ExpressParcel(2,1000),
            new FragileParcel(4,2000)
        };
        double grandTotal = 0;
        for (Parcel p : parcels) {
            double c = p.charge(), i = p.insurance(), t = p.total();
            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n", p.getType(), c,i,t);
            grandTotal += t;
        }
        System.out.printf("Grand Total: %.2f%n%n", grandTotal);

        // Problem 3
        Student[] students = {
            new DayScholar("Asha"),
            new Hosteller("Ravi"),
            new Scholar("Neha")
        };
        double totalFees = 0;
        for (Student s : students) {
            double f = s.fee();
            System.out.printf("%s: %.2f%n", s.name, f);
            totalFees += f;
        }
        System.out.printf("Total Collected: %.2f%n%n", totalFees);

        // Problem 4
        Cab[] trips = {
            new MiniCab(8,"DAY"),
            new SedanCab(10,"NIGHT"),
            new SuvCab(20,"DAY"),
            new MiniCab(5,"NIGHT")
        };
        double totalFare = 0;
        for (Cab c : trips) {
            double f = c.fare();
            if (f < 0) System.out.printf("%s: night service not available%n", c.getType());
            else {
                System.out.printf("%s: %.2f%n", c.getType(), f);
                totalFare += f;
            }
        }
        System.out.printf("Total: %.2f%n%n", totalFare);

        // Problem 5
        Appliance[] apps = {
            new Fridge(24,false),
            new AC(8,true),
            new TV(5,false),
            new Washer(2,true)
        };
        double totalCost = 0;
        for (Appliance a : apps) {
            System.out.println(a.report());
            totalCost += a.cost();
        }
        System.out.printf("Total Cost: %.2f%n", totalCost);
    }
}

