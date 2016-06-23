/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: SmartSplitHandler.java, Class: SmartSplitHandler
 * Last modified: 2010-03-28
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

package com.ansorgit.smarter.smartSplit;

import com.ansorgit.smarter.PsiClassnames;
import com.ansorgit.smarter.PsiUtils;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * This editor handler splits a string literal into two parts seperated by + operators.
 * It works with JavaScript and Java literals.
 * <p/>
 * Position the caret inside a string literal and press alt+PLUS, a string "ab|c" is turned
 * into "ab" + | + "c".
 * <p/>
 * User: jansorg
 * Date: Mar 17, 2010
 * Time: 9:26:14 PM
 */
public class SmartSplitHandler extends EditorActionHandler {
    private static List<? extends LanguageSplitHandler> splitHandlers = Arrays.asList(
            new JavaSplitHandler(), new PhpSplitHandler(), new JavascriptSplitHandler());

    @Override
    public void execute(final Editor editor, DataContext dataContext) {
        final DocumentEx doc = (DocumentEx) editor.getDocument();
        final Project project = PlatformDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext(editor.getContentComponent()));

        final PsiDocumentManager docManager = PsiDocumentManager.getInstance(project);
        final PsiFile psiFile = docManager.getPsiFile(doc);
        if (psiFile == null) {
            return;
        }

        docManager.commitDocument(doc);

        final int currentOffset = editor.getCaretModel().getOffset();
        final PsiElement element = psiFile.findElementAt(currentOffset);
        if (element == null) {
            return;
        }

        final boolean hasSelection = editor.getSelectionModel().hasSelection();

        for (final LanguageSplitHandler handler : splitHandlers) {
            if (handler.supportsElement(element)) {
                final int newOffset = ApplicationManager.getApplication().runWriteAction(new Computable<Integer>() {
                    public Integer compute() {
                        if (hasSelection) {
                            return splitAtSelection(editor, handler, element);
                        }

                        return splitAt(editor, currentOffset, handler, element);
                    }
                });

                docManager.commitDocument(doc);

                //format the element
                ApplicationManager.getApplication().runWriteAction(new Computable<Object>() {
                    public Object compute() {
                        PsiElement newElement = psiFile.findElementAt(newOffset);
                        PsiElement parent = PsiUtils.findFavouriteParentElement(newElement, PsiClassnames.getConfiguredTypes());
                        if (parent != null) {
                            TextRange textRange = parent.getTextRange();
                            CodeStyleManager.getInstance(project).reformatText(psiFile, textRange.getStartOffset(), textRange.getEndOffset());
                        }

                        return null;
                    }
                });

                break;
            }
        }
    }

    private int splitAtSelection(Editor editor, LanguageSplitHandler handler, PsiElement element) {
        String text = editor.getSelectionModel().getSelectedText();
        int start = editor.getSelectionModel().getSelectionStart();
        int end = editor.getSelectionModel().getSelectionEnd();

        String prefix = handler.getStringEnd(element) + handler.getConcatOperator(element) + " " + handler.getStringStart(element);
        String suffix = handler.getStringEnd(element) + " " + handler.getConcatOperator(element) + handler.getStringEnd(element);

        editor.getDocument().replaceString(start, end, prefix + text + suffix);

        int newOffset = start + prefix.length();

        editor.getSelectionModel().removeSelection();
        editor.getCaretModel().moveToOffset(newOffset);

        return newOffset;
    }

    private static int splitAt(Editor editor, int startOffset, LanguageSplitHandler handler, PsiElement element) {
        //first append the suffix at the current caret position
        String suffix = " " + handler.getConcatOperator(element) + handler.getStringStart(element);
        editor.getDocument().insertString(startOffset, suffix);

        //then append the prefix at the caret position
        String prefix = handler.getStringEnd(element) + handler.getConcatOperator(element) + " ";
        editor.getDocument().insertString(startOffset, prefix);
        editor.getCaretModel().moveToOffset(startOffset + prefix.length());

        //check dummy
        String dummy = handler.getDummyPlaceholder(element);
        boolean hasDummy = StringUtils.isNotEmpty(dummy);
        if (hasDummy) {
            int currentOffset = editor.getCaretModel().getOffset();
            editor.getDocument().insertString(currentOffset, dummy);
            editor.getSelectionModel().setSelection(currentOffset, currentOffset + dummy.length());
        }

        return startOffset + prefix.length();
    }
}
