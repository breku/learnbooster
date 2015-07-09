package com.brekol.console;

import com.brekol.model.Answer;
import com.brekol.model.Entry;
import com.brekol.util.Console;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static java.lang.Character.toLowerCase;
import static java.lang.Thread.currentThread;

/**
 * Created by brekol on 08.07.15.
 */
public class TerminalRunner {

    private static final int X_START_POSITION = 0;
    private int x = X_START_POSITION;
    private static final int Y_START_POSITION = 0;
    private int y = Y_START_POSITION;
    private static final List<Character> ANSWERING_CHARACTERS = Arrays.asList('a', 'b', 'c', 'd', 'A', 'B', 'C', 'D');
    private Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
    private Entry currentQuestion;

    private int numberOfCorrectAnswers = 0;
    private int numberOfQuestionsAnswered = 0;


    public void runTerminal(List<Entry> entries) {
        terminal.enterPrivateMode();
        printInfo();


        final Key key = getKey();
        if (quit(key)) {
            terminal.exitPrivateMode();
            return;
        }

        while (true) {
            if(entries.size() > 0){
                boolean qClicked = startTest(entries);
                if (qClicked) {
                    getKey();
                    break;
                }
            }else {
                print("No more incorrect questions");
                getKey();
                break;
            }

        }

        terminal.exitPrivateMode();
    }

    private boolean startTest(List<Entry> entries) {
        numberOfCorrectAnswers = 0;
        numberOfQuestionsAnswered = 0;
        ListIterator<Entry> iterator = entries.listIterator();
        printNextQuestion(iterator);

        while (true) {

            final Key key = getKey();


            if (quit(key)) {
                printEnd();
                return true;
            } else if (answering(key) && currentQuestion != null) {
                if (correct(key)) {
                    printCorrect();
                    waitSomeMiliSec(500);
                    numberOfCorrectAnswers++;
                    iterator.remove();
                } else {
                    printWrong();
                    getKey();

                }
                numberOfQuestionsAnswered++;
                printNextQuestion(iterator);
            } else if (currentQuestion == null) {
                return false;
            }

        }
    }

    private void printInfo() {
        print("To start click any key, if you want to quit during the test click q");
    }

    private void printNextQuestion(ListIterator<Entry> iterator) {
        currentQuestion = getNextQuestion(iterator);
        if (currentQuestion != null) {
            printEntry(currentQuestion);
        } else {
            printEnd();
            print("To run again with questions you answered incorrectly press any key");
        }
    }

    private void clearScreen() {
        x = X_START_POSITION;
        y = Y_START_POSITION;
        terminal.clearScreen();
        terminal.moveCursor(x, y);
    }

    private void printEnd() {
        print("Koniec pytan");
        print("Liczba poprawnych odpowiedzi: " + numberOfCorrectAnswers + "/" + numberOfQuestionsAnswered);
        double result = 100.0d * (double) numberOfCorrectAnswers / (double) numberOfQuestionsAnswered;
        print("Procentowy wynik: " + result + "%");

    }

    private Entry getNextQuestion(ListIterator<Entry> iterator) {
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    private void waitSomeMiliSec(long milisec) {
        try {
            currentThread().sleep(milisec);
        } catch (InterruptedException e) {
            terminal.exitPrivateMode();
            Console.logRed("Error during waiting");
            System.exit(-1);
        }
    }


    private void printCorrect() {
        terminal.applyForegroundColor(Terminal.Color.GREEN);
        print("Poprawnie");
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
    }

    private void printWrong() {
        terminal.applyForegroundColor(Terminal.Color.RED);
        print("Błąd - poprawna odpowiedź to " + getCorrectAnswer().getLeadingCharacter());
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
    }

    private boolean correct(Key key) {
        return getCorrectAnswer().getLeadingCharacter() == key.getCharacter();
    }

    private Answer getCorrectAnswer() {
        for (Answer answer : currentQuestion.getAnswers()) {
            if (answer.isCorrect()) {
                return answer;
            }
        }
        return null;
    }

    private boolean answering(Key key) {
        return key != null && ANSWERING_CHARACTERS.contains(key.getCharacter());
    }

    private void printEntry(Entry entry) {
        clearScreen();
        print(entry.getQuestion().getText());
        for (Answer answer : entry.getAnswers()) {
            print(answer.getText());
        }
    }


    private void print(String text) {
        terminal.moveCursor(x, y);
        for (char c : text.toCharArray()) {
            terminal.putCharacter(c);
            terminal.moveCursor(++x, y);
        }
        terminal.putCharacter('\n');
        x = X_START_POSITION;
        y++;
    }

    private Key getKey() {
        Key key = terminal.readInput();
        while (key == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                terminal.applyForegroundColor(Terminal.Color.RED);
                terminal.putCharacter('E');
                terminal.applyForegroundColor(Terminal.Color.DEFAULT);
            }
            key = terminal.readInput();
        }
        return key;
    }

    private boolean quit(Key key) {
        return key != null && toLowerCase(key.getCharacter()) == 'q';
    }
}
