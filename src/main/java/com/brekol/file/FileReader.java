package com.brekol.file;

import com.brekol.model.Answer;
import com.brekol.model.Entry;
import com.brekol.model.Question;
import com.brekol.util.Console;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * Created by brekol on 08.07.15.
 */
public class FileReader {


    private static final String DEFAULT_FILE_PATH = "questions.txt";

    public FileReader() {
    }

    public List<Entry> readQuestionFile() {

        final List<String> fileLines = getFileLines();
        return convert(fileLines);
    }



    private List<Entry> convert(final List<String> fileLines) {
        List<Entry> result = new ArrayList<Entry>();


        int numberOfCorrectAnswers =0;

        Entry entry = null;
        for (String fileLine : fileLines) {

            if (isQuestion(fileLine)) {
                entry = new Entry();
                result.add(entry);
                entry.setQuestion(new Question(fileLine));
            } else if (isAnswer(fileLine)) {
                boolean answerCorrect = isAnswerCorrect(fileLine);
                if(answerCorrect){
                    numberOfCorrectAnswers++;
                }
                Answer answer = new Answer(StringUtils.uncapitalize(fileLine), answerCorrect);
                entry.addAnswer(answer);
            }
        }
        Console.log("Number of questions: " + result.size());
        Console.log("Number of correctAnswers: " + numberOfCorrectAnswers);

        return result;
    }

    private boolean isAnswerCorrect(String fileLine) {
        return Character.isUpperCase(fileLine.toCharArray()[0]);
    }

    private boolean isAnswer(String fileLine) {
        return Character.isLetter(fileLine.toCharArray()[0]);
    }

    private boolean isQuestion(String fileLine) {
        return Character.isDigit(fileLine.toCharArray()[0]);
    }

    private List<String> getFileLines() {
        try {
            Console.log(">> Reading file started with path: " + DEFAULT_FILE_PATH);
            List result = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(DEFAULT_FILE_PATH));
            Console.log("<< Reading file finished");
            return result;
        } catch (IOException e) {
            Console.logRed("Error reading the file");
        }
        return emptyList();
    }

}
