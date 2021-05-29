import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private static Scanner scanner = new Scanner(System.in);

    private static Integer repeats(int[] array, int x) {
        int repeats = 0;
        for (int i = 0; i < array.length; i++) {
            if (x == array[i]) {
                repeats++;
            }
        }
        return repeats;
    }

    private static int complexity() {
        while (true) {
            System.out.println("Введите сложноть: 3,4,5");
            int answer = scanner.nextInt();
            if (answer > 2 && answer < 6) {
                return answer;
            } else {
                System.out.println("неправильное число");
            }
        }
    }

    private static int[] getRandomNumbers() {
        int complexity = complexity();
        int length = 0;
        if (complexity == 3) {
            length = 3;
        }
        if (complexity == 4) {
            length = 4;
        }
        if (complexity == 5) {
            length = 5;
        }
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
        int length = randomNumbers.length;
        while (true) {
            int count = 0;
            count++;
            System.out.println("Попытка: " + count);
            System.out.println("Если хотите сдаться, то напишите <сдаюсь> ");
            String endGame = scanner.next();

            if (endGame.equals("сдаюсь")) {
                for (int i = 0; i < randomNumbers.length; i++) {
                    System.out.print(randomNumbers[i] + "");
                }
                System.out.println("");
                System.out.println("Колличество попыток: " + count);
                return;
            }


            System.out.println("Введите " + length + " уникальныйх числа: ");
            int[] userArray = new int[length];
            String[] strings = scanner.next().split("");
            try {
                for (int i = 0; i < length; i++) {
                    userArray[i] = Integer.parseInt(strings[i]);
                }
            } catch (Exception exception) {
                System.out.println("Только уникальные числа");
                startGame();
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
                System.out.println("Колличество ваших попыток равно: " + count);
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
