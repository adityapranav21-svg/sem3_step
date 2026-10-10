import java.util.*;
import java.time.LocalDate;

// ---------- Problem 1: Payment System Fee Calculation ----------
abstract class Payment {
    double amount;
    Payment(double amount) { this.amount = amount; }
    abstract double calculateFinalAmount();
}

class CardPayment extends Payment {
    CardPayment(double amount) { super(amount); }
    double calculateFinalAmount() { return amount * 1.02; } // 2% fee
}

class WalletPayment extends Payment {
    WalletPayment(double amount) { super(amount); }
    double calculateFinalAmount() { return amount * 1.01; } // 1% fee
}

class BankTransferPayment extends Payment {
    BankTransferPayment(double amount) { super(amount); }
    double calculateFinalAmount() { return amount; } // no fee
}

// ---------- Problem 2: Library Item Due Date Calculator ----------
abstract class LibraryItem {
    String title;
    LibraryItem(String title) { this.title = title; }
    abstract LocalDate calculateDueDate(LocalDate currentDate);
}

class Book extends LibraryItem {
    Book(String title) { super(title); }
    LocalDate calculateDueDate(LocalDate currentDate) { return currentDate.plusDays(14); }
}

class DVD extends LibraryItem {
    DVD(String title) { super(title); }
    LocalDate calculateDueDate(LocalDate currentDate) { return currentDate.plusDays(7); }
}

class Magazine extends LibraryItem {
    Magazine(String title) { super(title); }
    LocalDate calculateDueDate(LocalDate currentDate) { return currentDate.plusDays(3); }
}

// ---------- Problem 3: Delivery Fee Calculator ----------
abstract class Delivery {
    double weight, distance;
    Delivery(double weight, double distance) { this.weight = weight; this.distance = distance; }
    abstract double calculateFee();
}

class StandardDelivery extends Delivery {
    StandardDelivery(double weight, double distance) { super(weight, distance); }
    double calculateFee() { return 5 + 0.5*weight + 0.1*distance; }
}

class ExpressDelivery extends Delivery {
    ExpressDelivery(double weight, double distance) { super(weight, distance); }
    double calculateFee() { return 15 + 1.0*weight + 0.2*distance; }
}

class InternationalDelivery extends Delivery {
    double customsFee;
    InternationalDelivery(double weight, double distance, double customsFee) {
        super(weight, distance); this.customsFee = customsFee;
    }
    double calculateFee() { return 25 + 2.0*weight + 0.5*distance + customsFee; }
}

// ---------- Problem 4: Examination Question Grader ----------
abstract class Question {
    String text, correctAnswer, studentAnswer;
    int points;
    Question(String text, String correctAnswer, String studentAnswer, int points) {
        this.text = text; this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer; this.points = points;
    }
    abstract double grade();
}

class MCQ extends Question {
    MCQ(String text, String correctAnswer, String studentAnswer, int points) {
        super(text, correctAnswer, studentAnswer, points);
    }
    double grade() { return studentAnswer.equals(correctAnswer) ? points : 0; }
}

class TF extends Question {
    TF(String text, String correctAnswer, String studentAnswer, int points) {
        super(text, correctAnswer, studentAnswer, points);
    }
    double grade() { return studentAnswer.equals(correctAnswer) ? points : 0; }
}

class Essay extends Question {
    Essay(String text, String correctAnswer, String studentAnswer, int points) {
        super(text, correctAnswer, studentAnswer, points);
    }
    double grade() {
        String[] keywords = correctAnswer.split(",");
        int matchCount = 0;
        for (String kw : keywords) {
            if (studentAnswer.toLowerCase().contains(kw.trim().toLowerCase())) matchCount++;
        }
        if (matchCount >= 2) return points * 0.75;
        else if (matchCount == 1) return points * 0.5;
        else return 0;
    }
}

// ---------- Problem 5: Public Transport Fare Calculator ----------
abstract class Transport {
    double distance;
    Transport(double distance) { this.distance = distance; }
    abstract double calculateFare();
}

class Bus extends Transport {
    Bus(double distance) { super(distance); }
    double calculateFare() {
        double fare = 2 + 0.1*distance;
        return Math.min(fare, 10); // capped at 10
    }
}

class Train extends Transport {
    Train(double distance) { super(distance); }
    double calculateFare() { return 3 + 0.15*distance; }
}

class Metro extends Transport {
    double peakFactor;
    Metro(double distance, double peakFactor) { super(distance); this.peakFactor = peakFactor; }
    double calculateFare() { return (1.5 + 0.2*distance) * peakFactor; }
}

// ---------- Main Demo ----------
public class week8 {
    public static void main(String[] args) {
        // Problem 1
        Payment[] payments = {
            new CardPayment(1000),
            new WalletPayment(500),
            new BankTransferPayment(2000)
        };
        double totalPayment = 0;
        for (Payment p : payments) {
            double amt = p.calculateFinalAmount();
            System.out.printf("%s: %.2f%n", p.getClass().getSimpleName().replace("Payment",""), amt);
            totalPayment += amt;
        }
        System.out.printf("Total: %.2f%n%n", totalPayment);

        // Problem 2
        LocalDate currentDate = LocalDate.of(2023,10,26);
        LibraryItem[] items = {
            new Book("1984"),
            new DVD("The Matrix"),
            new Magazine("Forbes Issue 500")
        };
        for (LibraryItem item : items) {
            System.out.println(item.title + ": " + item.calculateDueDate(currentDate));
        }
        System.out.println();

        // Problem 3
        Delivery[] deliveries = {
            new StandardDelivery(10,50),
            new ExpressDelivery(5,20),
            new InternationalDelivery(20,100,30)
        };
        double totalFee = 0;
        for (Delivery d : deliveries) {
            double fee = d.calculateFee();
            System.out.printf("%s: %.2f%n", d.getClass().getSimpleName().replace("Delivery",""), fee);
            totalFee += fee;
        }
        System.out.printf("Total: %.2f%n%n", totalFee);

        // Problem 4
        Question[] questions = {
            new MCQ("Capital of France?", "Paris", "Paris", 10),
            new TF("The Earth is flat?", "False", "True", 5),
            new Essay("Name OOP principles", "Inheritance, Polymorphism, Encapsulation", "Polymorphism is one.", 20),
            new Essay("Describe abstraction and composition", "Abstraction, Composition", "I talked about abstraction.", 15)
        };
        double totalScore = 0;
        for (Question q : questions) {
            double score = q.grade();
            System.out.printf("%s: %.2f%n", q.getClass().getSimpleName(), score);
            totalScore += score;
        }
        System.out.printf("Total Score: %.2f%n%n", totalScore);

        // Problem 5
        Transport[] journeys = {
            new Bus(15),
            new Train(50),
            new Metro(10,1.5)
        };
        double totalFare = 0;
        for (Transport t : journeys) {
            double fare = t.calculateFare();
            System.out.printf("%s: %.2f%n", t.getClass().getSimpleName(), fare);
            totalFare += fare;
        }
        System.out.printf("Total: %.2f%n", totalFare);
    }
}
