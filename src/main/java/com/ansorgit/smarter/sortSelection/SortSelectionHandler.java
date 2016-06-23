/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: SortSelectionHandler.java, Class: SortSelectionHandler
 * Last modified: 2010-06-03
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ansorgit.smarter.sortSelection;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.util.ArrayUtil;

import java.util.Arrays;

/**
 * User: jansorg
 * Date: 03.06.2010
 * Time: 15:49:30
 */
public class SortSelectionHandler extends EditorActionHandler {
    @Override
    public boolean isEnabled(Editor editor, DataContext dataContext) {
        return editor.getSelectionModel().hasSelection() || editor.getSelectionModel().hasBlockSelection();
    }

    @Override
    public void execute(final Editor editor, DataContext dataContext) {
        final SelectionModel selectionModel = editor.getSelectionModel();
        if (!isEnabled(editor, dataContext)) {
            return;
        }

        String selectedText = selectionModel.getSelectedText();
        String[] lines = selectedText.split("\\n");

        String[] sortedLines = Arrays.copyOf(lines, lines.length);
        Arrays.sort(sortedLines);

        if (Arrays.equals(lines, sortedLines)) {
            //already sorted
            sortedLines = ArrayUtil.reverseArray(sortedLines);
        }

        final StringBuilder result = new StringBuilder();
        for (int i = 0, sortedLinesLength = sortedLines.length; i < sortedLinesLength - 1; i++) {
            String line = sortedLines[i];
            result.append(line).append('\n');
        }

        result.append(sortedLines[sortedLines.length - 1]);

        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            public void run() {
                editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), result.toString());
            }
        });
    }
}
