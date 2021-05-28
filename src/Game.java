import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private static Integer repeats(int[] array, int x) {
        int repeats = 0;
        for (int i = 0; i < array.length; i++) {
            if (x == array[i]) {
                repeats++;
            }
        }
        return repeats;
    }

    private static int[] getRandomNumbers() {
        int length = 4;
        int[] randomArray = new int[length];

        for (int i = 0; i < length; i++) {
            randomArray[i] = (int) (Math.random() * (10) + 0);
            do {
                randomArray[i] = (int) (Math.random() * (10) + 0);
            } while (repeats(randomArray, randomArray[i]) >= 2);
        }
        return randomArray;
    }

    public static void startGame() {
        int[] randomNumbers = getRandomNumbers();
        while (true) {
            System.out.println("Введите 4 уникальныйх числа: ");
            Scanner scanner = new Scanner(System.in);
            int[] userArray = new int[4];
            String[] strings = scanner.next().split("");
            for (int i = 0; i < 4; i++) {
                try {
                    userArray[i] = Integer.parseInt(strings[i]);
                } catch (Exception exception) {
                    System.out.println("Только уникальные числа");
                    startGame();
                }
            }
            for (int number : userArray) {
                if (repeats(userArray, number) >= 2) {
                    System.out.println("Только уникальные числа");
                    startGame();
                }
            }
            searchCowAndBulls(randomNumbers, userArray);
            if (Arrays.equals(randomNumbers, userArray)) {
                System.out.println("Вы ВЫИГРАЛИ!!!");
                System.out.println("Хотите еще раз? Если да введите 1, Если нет то введите 2.");
                int again = scanner.nextInt();
                if (again == 2) {
                    break;
                }
            }
        }
    }

    private static void searchCowAndBulls(int[] randomArray, int[] userArray) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < userArray.length; i++) {
            if (userArray[i] == randomArray[i]) {
                bulls++;
            }
            for (int j = 0; j < userArray.length; j++) {
                if (i != j && userArray[i] == randomArray[j]) {
                    cows++;
                }
            }
        }
        System.out.println("Коровы: " + cows + " " + "Быки: " + bulls);
    }
}
