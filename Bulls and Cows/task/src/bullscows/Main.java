package bullscows;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static String secretCode;

    public static String secretGenerator(int secretNumLength) {
        TreeMap<Character, Integer> dupliMap;
        String pseudoRandomNumber = "";
        boolean isValid = false;
        while (!isValid) {
            dupliMap = new TreeMap<>();
            pseudoRandomNumber = String.valueOf(Math.random() * 1000000).substring(0, secretNumLength);
            if (secretNumLength > 10) {
                System.out.println("Error: can't generate a secret number with a length of " + secretNumLength + " because there aren't enough unique digits.");
                return null;
            } else {
                for (char each : pseudoRandomNumber.toCharArray()) {
                    Integer count = dupliMap.get(each);
                    dupliMap.put(each, count == null ? 1 : count + 1);
                }
                for (Map.Entry<Character, Integer> each : dupliMap.entrySet()) {
                    if (each.getValue() > 1) {
                        isValid = false;
                        break;
                    } else {
                        isValid = true;
                    }
                }
            }
        }
        return pseudoRandomNumber;
    }

    public static String secretNumDisplay(int secretNumLength) {
        return "*".repeat(Math.max(0, secretNumLength));
    }

    public static String symbolDisplay(int symbolCount) {
        String result = "";
        switch (symbolCount) {
            case 1:
                result = "(0)";
                break;
            case 2:
                result = "(0-1)";
                break;
            case 3:
                result = "(0-2)";
                break;
            case 4:
                result = "(0-3)";
                break;
            case 5:
                result = "(0-4)";
                break;
            case 6:
                result = "(0-5)";
                break;
            case 7:
                result = "(0-6)";
                break;
            case 8:
                result = "(0-7)";
                break;
            case 9:
                result = "(0-8)";
                break;
            case 10:
                result = "(0-9)";
                break;
            case 11:
                result = "(0-9, a)";
                break;
            case 12:
                result = "(0-9, a-b)";
                break;
            case 13:
                result = "(0-9, a-c)";
                break;
            case 14:
                result = "(0-9, a-d)";
                break;
            case 15:
                result = "(0-9, a-e)";
                break;
            case 16:
                result = "(0-9, a-f)";
                break;
            case 17:
                result = "(0-9, a-g)";
                break;
            case 18:
                result = "(0-9, a-h)";
                break;
            case 19:
                result = "(0-9, a-i)";
                break;
            case 20:
                result = "(0-9, a-j)";
                break;
            case 21:
                result = "(0-9, a-k)";
                break;
            case 22:
                result = "(0-9, a-l)";
                break;
            case 23:
                result = "(0-9, a-m)";
                break;
            case 24:
                result = "(0-9, a-n)";
                break;
            case 25:
                result = "(0-9, a-o)";
                break;
            case 26:
                result = "(0-9, a-p)";
                break;
            case 27:
                result = "(0-9, a-q)";
                break;
            case 28:
                result = "(0-9, a-r)";
                break;
            case 29:
                result = "(0-9, a-s)";
                break;
            case 30:
                result = "(0-9, a-t)";
                break;
            case 31:
                result = "(0-9, a-u)";
                break;
            case 32:
                result = "(0-9, a-v)";
                break;
            case 33:
                result = "(0-9, a-w)";
                break;
            case 34:
                result = "(0-9, a-x)";
                break;
            case 35:
                result = "(0-9, a-y)";
                break;
            case 36:
                result = "(0-9, a-z)";
                break;
            default:
                break;
        }
        return result;
    }

    public static void outputExample() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int secretNumLength = 0;
        int symbolCount = 0;
        boolean isErred = false;
        String secretString = scanner.nextLine();
        if (secretString.length() == secretString
                .replaceAll("[^0-9]", "").length()) {
            secretNumLength = Integer.parseInt(secretString);
            System.out.println("Input the number of possible symbols in the code:");
            symbolCount = scanner.nextInt();
        } else {
            System.out.println("Error: " + "\"" +
                    secretString + "\"" + " isn't a valid number.");
            isErred = true;
        }
        if (!isErred && secretNumLength > symbolCount) {
            System.out.println("Error: it's not possible " +
                    "to generate a code with a length of " +
                    "6 with 5 unique symbols.");
            isErred = true;
        }
        if (!isErred && symbolCount > 36) {
            System.out.println("Error: maximum number of " +
                    "possible symbols in the code is 36 (0-9, a-z).");
            isErred = true;
        }
        if (!isErred && secretNumLength < 1) {
            System.out.println("Error: the code is non-existent.");
            isErred = true;
        }
        if (!isErred) {
            System.out.println("The secret is prepared: " +
                    secretNumDisplay(secretNumLength) + " "
                    + symbolDisplay(symbolCount) + ".");
            secretCode = secretGenerator(secretNumLength);
            TreeMap<Character, Integer> dupliNumsOne;
            TreeMap<Character, Integer> dupliNumsTwo;
            int bullCount;
            int cowCount;
            int turnCount = 1;
            boolean isGuessed = false;
            System.out.println("Okay, let's start a game!");
            while (!isGuessed) {
                dupliNumsOne = new TreeMap<>();
                dupliNumsTwo = new TreeMap<>();
                bullCount = 0;
                cowCount = 0;
                System.out.println("Turn " + turnCount + ":");
                String guessAsString = scanner.next();
                String secretAsString = String.valueOf(getSecretCode());
                for (int i = 0; i < guessAsString.length(); i++) {
                    Integer count = dupliNumsOne.get(guessAsString.charAt(i));
                    Integer countTwo = dupliNumsTwo.get(secretAsString.charAt(i));
                    dupliNumsOne.put(guessAsString.charAt(i), count == null ? 1 : count + 1);
                    dupliNumsTwo.put(secretAsString.charAt(i), countTwo == null ? 1 : countTwo + 1);
                }
                int cowTemp = 0;
                for (int i = 0; i < guessAsString.length(); i++) {
                    if (cowTemp > cowCount) {
                        cowCount = cowTemp;
                    }
                    cowTemp = 0;
                    for (int j = 0; j < secretAsString.length(); j++) {
                        if (guessAsString.charAt(i) == secretAsString.charAt(j)) {
                            if (i == j) {
                                bullCount++;
                            } else {
                                cowTemp++;
                            }
                        }
                    }
                }
                if (bullCount == secretNumLength) {
                    if (bullCount == 1) {
                        System.out.println("Grade: " + bullCount + " bull");
                    } else {
                        System.out.println("Grade: " + bullCount + " bulls");
                    }
                    System.out.println("Congratulations! You guessed the secret code.");
                    isGuessed = true;
                } else if (bullCount == 0 && cowCount == 0) {
                    System.out.println("None");
                } else if (bullCount == 0) {
                    System.out.println(cowCount + " cow(s)");
                } else if (bullCount > 1 && cowCount == 0) {
                    System.out.println(bullCount + " bull(s)");
                } else {
                    System.out.println(bullCount + " bull(s) and " + cowCount + " cow(s)");
                }
                turnCount++;
            }
        }
    }

    public static String getSecretCode() {
        return secretCode;
    }

    public static void main(String[] args) {
        outputExample();
    }
}
