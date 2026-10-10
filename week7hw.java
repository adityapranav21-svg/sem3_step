import java.util.*;

class Character {
    private int health;
    private final int maxHealth;

    public Character(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
        System.out.println("Health after damage: " + health);
    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) health = maxHealth;
        System.out.println("Health after healing: " + health);
    }

    public int getHealth() {
        return health;
    }
}

class Playlist {
    private String[] songs;
    private int count;

    public Playlist(int maxSize) {
        songs = new String[maxSize];
        count = 0;
    }

    public void addSong(String title) {
        if (count < songs.length) {
            songs[count++] = title;
        } else {
            System.out.println("Playlist full!");
        }
    }

    public String[] getSongs() {
        return Arrays.copyOf(songs, count); // safe copy
    }

    public int getSongCount() {
        return count;
    }
}

class PasswordChecker {
    private final String password;

    public PasswordChecker(String password) {
        this.password = password;
    }

    public String getStrength() {
        int len = password.length();
        if (len < 6) return "Weak";
        else if (len <= 9) return "Medium";
        else return "Strong";
    }
}

class TrafficLight {
    private String color;
    private final String id;

    public TrafficLight(String id) {
        this.id = id;
        this.color = "RED";
    }

    public void next() {
        if (color.equals("RED")) color = "GREEN";
        else if (color.equals("GREEN")) color = "YELLOW";
        else color = "RED";
        System.out.println("Traffic Light " + id + " -> " + color);
    }

    public String getColor() {
        return color;
    }
}

class Cart {
    private double[] prices;
    private int count;
    private final String cartId;

    public Cart(String cartId, int maxItems) {
        this.cartId = cartId;
        this.prices = new double[maxItems];
        this.count = 0;
    }

    public void addItem(double price) {
        if (count < prices.length) {
            prices[count++] = price;
        } else {
            System.out.println("Cart full!");
        }
    }

    public double getTotal() {
        double sum = 0;
        for (int i = 0; i < count; i++) sum += prices[i];
        return sum;
    }

    public int getItemCount() {
        return count;
    }
}

public class week7hw {
    public static void main(String[] args) {
        // Problem 1: Health Bar
        Character c = new Character(100);
        c.takeDamage(30);
        c.heal(50);
        c.takeDamage(150);

        // Problem 2: Playlist
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");
        String[] copy = p.getSongs();
        copy[0] = "Hacked"; // safe copy
        System.out.println("Playlist songs: " + Arrays.toString(p.getSongs()));

        // Problem 3: PasswordChecker
        PasswordChecker pc1 = new PasswordChecker("abcd");
        System.out.println("Strength: " + pc1.getStrength());
        PasswordChecker pc2 = new PasswordChecker("abcdefghij");
        System.out.println("Strength: " + pc2.getStrength());

        // Problem 4: TrafficLight
        TrafficLight t = new TrafficLight("TL-9");
        System.out.println("Initial color: " + t.getColor());
        t.next(); t.next(); t.next();

        // Problem 5: Cart
        Cart cart = new Cart("CART-5", 20);
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);
        System.out.println("Total: " + cart.getTotal());
        System.out.println("Item count: " + cart.getItemCount());
    }
}
