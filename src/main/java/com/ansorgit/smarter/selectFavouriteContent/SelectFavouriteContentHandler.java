/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: SelectFavouriteHandler.java, Class: SelectFavouriteHandler
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

package com.ansorgit.smarter.selectFavouriteContent;

import com.ansorgit.smarter.AbstractEditorHandler;
import com.ansorgit.smarter.PsiUtils;
import com.ansorgit.smarter.SearchDirection;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

/**
 * User: jansorg
 * Date: Mar 5, 2010
 * Time: 5:30:47 PM
 */
public class SelectFavouriteContentHandler extends AbstractEditorHandler {
    public SelectFavouriteContentHandler() {
        super(false, SearchDirection.Backwards);
    }

    @Override
    protected void elementAction(Project project, PsiElement psiElement, Editor editor) {
        int startOffset = findBlockContentStart(psiElement);
        int endOffset = findBlockContentEnd(psiElement);

        if (startOffset >= 0) {
            //make sure that the caret is repositioned into the selection if the new selection does not contain it
            int caretOffset = editor.getCaretModel().getOffset();
            if (caretOffset < startOffset || caretOffset > endOffset) {
                editor.getCaretModel().moveToOffset(startOffset);
            }

            //now select the block content
            editor.getSelectionModel().setSelection(startOffset, endOffset);
        } else {
            TextRange r = psiElement.getTextRange();
            editor.getSelectionModel().setSelection(r.getStartOffset(), r.getEndOffset());
        }
    }

    private int findBlockContentStart(PsiElement psi) {
        PsiElement first = PsiUtils.eatWhitespace(psi, SearchDirection.Forwards);
        first = BlockContentHandlers.instance.selectFirst(first);
        first = PsiUtils.eatWhitespace(first, SearchDirection.Forwards);

        return first.getTextRange().getStartOffset();
    }


    private int findBlockContentEnd(PsiElement psi) {
        PsiElement last = PsiUtils.eatWhitespace(psi, SearchDirection.Backwards);
        last = BlockContentHandlers.instance.selectLast(last);
        last = PsiUtils.eatWhitespace(last, SearchDirection.Backwards);

        return last.getTextRange().getEndOffset();
    }
}
