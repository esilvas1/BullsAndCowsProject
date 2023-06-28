package bullscows;

import java.awt.desktop.SystemSleepEvent;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private int cow;
    private int bull;

    private int turn;

    public final int maxNumberOfDigits = 36;

    public Main(int cow, int bull, int turn) {
        this.cow = cow;
        this.bull = bull;
        this.turn = turn;
    }

    public int getCow() {
        return cow;
    }

    public void setCow(int cow) {
        this.cow = cow;
    }

    public int getBull() {
        return bull;
    }

    public void setBull(int bull) {
        this.bull = bull;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean guessCode(StringBuilder code, Main m) {

        Scanner scanner = new Scanner(System.in);
        String ans = scanner.nextLine();

        StringBuilder answer = new StringBuilder(ans);

        for (int i = 0; i < code.length(); i++)
            if (code.charAt(i) == answer.charAt(i))
                m.setBull(m.getBull() + 1);


        for (int i = 0; i < code.length(); i++)
            for (int j = 0; j < answer.length(); j++)
                if (code.charAt(i) == answer.charAt(j) && i != j)
                    m.setCow(m.getCow() +  1);

        if (m.getBull() < code.length()) {
            if (m.getBull() == 0 && m.getCow() > 0) {
                System.out.print("Grade: " + m.getCow() + " cow");
                if(m.getCow() > 1) {System.out.println("s");} else {System.out.println();}
            } else if (m.getBull() > 0 && m.getCow() == 0) {
                System.out.print("Grade: " + m.getBull() + " bull");
                if(m.getBull() > 1) {System.out.println("s");} else {System.out.println();}
            } else if (m.getBull() > 0 && m.getCow() > 0) {
                System.out.print("Grade: " + m.getBull() + " bull");
                if(m.getBull() > 1) {System.out.print("s");}
                System.out.print(" and " + cow + " cow");
                if(m.getCow() > 1) {System.out.println("s");} else {System.out.println();}
            } else if (m.getBull() == 0 && m.getCow() == 0) {
                System.out.println("Grade: None");
            }
        } else if (m.getBull() == code.length()) {
            System.out.print("Grade: " + m.getBull() + " bull");
            if(m.getBull() > 1) {System.out.println("s");} else {System.out.println();}
            System.out.println("Congratulations! You guessed the secret code.");
            return true;
        }
        return false;
    }

    public StringBuilder getSecretCode(int numberOfDigits, int numberOfPossibleSymbols){

        StringBuilder sb = new StringBuilder();

        if (numberOfDigits <= maxNumberOfDigits) {

            Random random = new Random();
            int lower = 0;
            int upper = numberOfPossibleSymbols;

            int next = random.nextInt(upper - lower + 1) + lower;
            sb.append(Integer.toUnsignedString(next,numberOfPossibleSymbols));


            //add digits to the string
            boolean exists;
            if(numberOfDigits > sb.length()) {
                for (int i = 0; i <= numberOfPossibleSymbols; i++) {
                    next = random.nextInt(upper - lower + 1) + lower;
                    String str = Integer.toUnsignedString(next, numberOfPossibleSymbols);
                    exists = false;
                    for (int j = 0; j < sb.length(); j++) {
                        if (str.equals(String.valueOf(sb.charAt(j))) ) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        sb.append(str);
                    }
                }
                sb.delete(numberOfDigits, sb.length());
            }
            return sb;

        } else {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            return sb;
        }
    }

    public void printStart(int numberOfDigits, int numberOfPossibleSymbols){
        StringBuilder asterisk = new StringBuilder();
        String symbols[] = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

        for(int i = 0; i < numberOfDigits; i++) asterisk.append("*");

        System.out.print("The secret is prepared: ");
        System.out.print(asterisk.toString());
        System.out.print(" (");
        System.out.print(symbols[0]);
        System.out.print("-");
        if(numberOfPossibleSymbols <= 10) {
            System.out.print(symbols[numberOfPossibleSymbols - 1]);
            System.out.println(").");
        }
        else if (numberOfPossibleSymbols == 11){
            System.out.print(symbols[9]);
            System.out.print(", ");
            System.out.print(symbols[10]);
            System.out.print("-");
            System.out.print(symbols[numberOfPossibleSymbols - 1]);
            System.out.println(").");
        } else {
            System.out.print(symbols[9]);
            System.out.print(", ");
            System.out.print(symbols[10]);
            System.out.print("-");
            System.out.print(symbols[numberOfPossibleSymbols - 1]);
            System.out.println(").");
        };
    }

    public boolean isCheckInOne(String numberOfDigits) {

        String[] vectorNumberOfDigits = numberOfDigits.split("");

        if(vectorNumberOfDigits[0].equals("0")){
            System.out.println("Error: \"" + numberOfDigits + "\" isn't a valid number.");
            return false;
        }

        for(int i = 0; i < vectorNumberOfDigits.length; i++) {
            char caracter = vectorNumberOfDigits[i].charAt(0);
            int codigoAscii = Character.codePointAt(String.valueOf(caracter), 0);
            if (!(codigoAscii >= 48 && codigoAscii <= 57)) {
                System.out.println("Error: \"" + numberOfDigits + "\" isn't a valid number.");
                return false;
            }
        }

         return true;
    }


    public boolean isCheckIn(String numberOfDigits, String numberOfPossibleSymbols) {

        String[] vectorNumberOfDigits = numberOfDigits.split("");
        String[] vectorNumberOfPossibleSymbols = numberOfPossibleSymbols.split("");

        if(vectorNumberOfDigits[0].equals("0")){
            System.out.println("Error: \"" + numberOfDigits + "\" isn't a valid number.");
            return false;
        }

        for(int i = 0; i < vectorNumberOfDigits.length; i++) {
            char caracter = vectorNumberOfDigits[i].charAt(0);
            int codigoAscii = Character.codePointAt(String.valueOf(caracter), 0);
            if (!(codigoAscii >= 48 && codigoAscii <= 57)) {
                System.out.println("Error: \"" + numberOfDigits + "\" isn't a valid number.");
                return false;
            }
        }

        if(vectorNumberOfPossibleSymbols[0].equals("0")){
            System.out.println("Error: \"" + numberOfPossibleSymbols + "\" isn't a valid number.");
            return false;
        }

        for(int i = 0; i < vectorNumberOfPossibleSymbols.length; i++) {
            char caracter = vectorNumberOfPossibleSymbols[i].charAt(0);
            int codigoAscii = Character.codePointAt(String.valueOf(caracter), 0);
            if (!(codigoAscii >= 48 && codigoAscii <= 57)) {
                System.out.println("Error: \"" + numberOfPossibleSymbols + "\" isn't a valid number.");
                return false;
            }
        }

        if (Integer.valueOf(numberOfDigits) > Integer.valueOf(numberOfPossibleSymbols)) {
            System.out.println("Error: it's not possible to generate a code with a length of " + numberOfDigits + " with " + numberOfPossibleSymbols + " unique symbols.");
            return false;
        }

        if(Integer.valueOf(numberOfPossibleSymbols) > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        String numberOfDigits,numberOfPossibleSymbols;
        Main m = new Main(0,0,0);

        System.out.println("Input the length of the secret code:");
        numberOfDigits = scanner.nextLine();

        if(!m.isCheckInOne(numberOfDigits)) { return; };

        System.out.println("Input the number of possible symbols in the code:");
        numberOfPossibleSymbols = scanner.nextLine();

        if(!m.isCheckIn(numberOfDigits, numberOfPossibleSymbols)) { return; };

        m.printStart(Integer.valueOf(numberOfDigits),Integer.valueOf(numberOfPossibleSymbols));
        sb = m.getSecretCode(Integer.valueOf(numberOfDigits),Integer.valueOf(numberOfPossibleSymbols));
        System.out.println("Okay, let's start a game!");

        do {
            m.setTurn(m.getTurn() +  1);
            System.out.println("Turn " + m.getTurn() + ":");
            m.setCow(0);
            m.setBull(0);
        } while (!m.guessCode(sb,m));

    }

}