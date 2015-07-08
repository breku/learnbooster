package com.brekol.console;

import com.brekol.file.FileReader;
import com.brekol.model.Entry;

import java.util.List;

import static java.util.Collections.shuffle;

/**
 * Created by brekol on 08.07.15.
 */
public class TestRunner {

    public void runTest() {
        final FileReader fileReader = new FileReader();
        final List<Entry> entries = fileReader.readQuestionFile();
        shuffle(entries);
        final TerminalRunner terminalRunner = new TerminalRunner();
        terminalRunner.runTerminal(entries);

    }


}
