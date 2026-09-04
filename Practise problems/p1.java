import java.util.Random;

public class p1 {
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        String[] options = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        int wins = 0, losses = 0, draws = 0;
        
        for (int i = 0; i < 5; i++) {
            String pMove = playerMoves[i];
            String cMove = options[random.nextInt(3)];
            String result = playRound(pMove, cMove);
            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;
            System.out.println("Round " + (i + 1));
            System.out.println("Player: " + pMove + ", Computer: " + cMove + "\n" + result + "\n");
        }
        double winPercentage = ((double) wins / 5) * 100;
        System.out.printf("Final Summary (after 5 rounds)\nWins: %d | Losses: %d | Draws: %d | Win %%=%.1f%%\n", wins, losses, draws, winPercentage);
    }
}