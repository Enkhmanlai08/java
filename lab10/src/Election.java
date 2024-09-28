import java.util.Scanner;

public class Election {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("N тооны санал өгөгчдийг оруул : ");
        int N = scanner.nextInt();
        int voteA = 0, voteB = 0;
        for (int i = 1; i <= N; i++) {
            System.out.print("Сонгогч " + i + ", саналаа өгнө үү. A эсвэл B : ");
            char vote = scanner.next().toUpperCase().charAt(0);
            if (vote == 'A') voteA++;
            else if (vote == 'B') voteB++;
            else {
                System.out.println("Зөвхөн A эсвэл B нэр дэвшигчид саналаа өгнө үү.");
                i--;
            }
        }
        char win = (voteA > voteB) ? 'A' : (voteB > voteA) ? 'B' : ' ';
        System.out.println("\nСонгуулийн үр дүн : ");
        System.out.println("A нэр дэвшигчийн санaлын тоо : " + voteA);
        System.out.println("B нэр дэвшигчийн санaлын тоо : " + voteB);
        if (win != ' ') System.out.println("Нэр дэвшигч " + win + " яллаа.");
        else System.out.println("Санал тэнцсэн.");
        scanner.close();
    }
}
