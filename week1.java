import java.util.*;

public class week1 {
    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) return "Draw";
        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int wins = 0, losses = 0, draws = 0;
        int N = 5;
        System.out.println("Rock-Paper-Scissors Game\n");

        for (int round = 1; round <= N; round++) {
            System.out.print("Round " + round + " — Enter move (Rock/Paper/Scissors): ");
            String playerMove = sc.next();
            String computerMove = moves[rand.nextInt(3)];
            String result = playRound(playerMove, computerMove);

            System.out.println("Player: " + playerMove + ", Computer: " + computerMove + " → " + result);

            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;
        }

        double winPercent = (wins * 100.0) / N;
        System.out.println("\nFinal Summary: Wins=" + wins + " | Losses=" + losses +
                           " | Draws=" + draws + " | Win %=" + winPercent);
    }
}

