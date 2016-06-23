/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: AbstractEditorHandler.java, Class: AbstractEditorHandler
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

package com.ansorgit.smarter;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * User: jansorg
 * Date: Mar 7, 2010
 * Time: 11:17:20 PM
 */
public abstract class AbstractEditorHandler extends EditorActionHandler {
    private boolean useSelection = false;
    private SearchDirection preferredDirection;

    protected AbstractEditorHandler(boolean useSelection, SearchDirection preferredDirection) {
        this.useSelection = useSelection;
        this.preferredDirection = preferredDirection;
    }

    protected void placeCaretAt(@NotNull Editor editor, int offset) {
        editor.getCaretModel().moveToOffset(offset);
        editor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
    }

    @Override
    public void execute(Editor editor, DataContext dataContext) {
        final DocumentEx doc = (DocumentEx) editor.getDocument();
        final Project project = PlatformDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext(editor.getContentComponent()));

        final PsiDocumentManager docManager = PsiDocumentManager.getInstance(project);
        PsiFile psiFile = docManager.getPsiFile(doc);
        if (psiFile == null) {
            return;
        }

        docManager.commitDocument(doc);

        if (useSelection && editor.getSelectionModel().hasSelection()) {
            selectionAction(editor, editor.getSelectionModel());
            editor.getSelectionModel().removeSelection();
        } else {
            PsiElement psiElement = findFavouriteParentElement(editor, psiFile, useSelection, preferredDirection);

            if (psiElement != null) {
                elementAction(project, psiElement, editor);
            }
        }
    }

    protected void selectionAction(Editor editor, SelectionModel selectionModel) {
        //default is no op
    }

    protected abstract void elementAction(Project project, PsiElement psiElement, Editor editor);

    protected static PsiElement findElementAt(PsiFile psiFile, int offset, SearchDirection direction) {
        //we give an element ending at offset a higher precedence than the element starting at offset
        if (direction == SearchDirection.Backwards && offset > 0) {
            PsiElement prev = psiFile.findElementAt(offset - 1);

            //return an element if it ends at the current offset and no other element was found
            if (prev != null && prev.getTextRange().getEndOffset() == offset) {
                return prev;
            }
        }

        PsiElement element = psiFile.findElementAt(offset);
        int moveOffset = direction == SearchDirection.Backwards ? -1 : 1;
        int checkOffset = offset;
        while (element instanceof PsiWhiteSpace) {
            checkOffset = checkOffset + moveOffset;
            element = psiFile.findElementAt(checkOffset);
        }

        return element;
    }

    protected PsiElement findFavouriteParentElement(Editor editor, PsiFile psiFile, boolean useSelection, SearchDirection direction) {
        List<PsiClassname> types = PsiClassnames.getConfiguredTypes();

        SelectionModel selectionModel = editor.getSelectionModel();

        boolean hasSelection = selectionModel.hasSelection();
        if (useSelection && hasSelection) {
            TextRange range = TextRange.from(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd());
            return PsiUtils.findFavouriteSelectionParentElement(psiFile, range, types);
        }

        TextRange selection = TextRange.from(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd() - selectionModel.getSelectionStart());

        int offset = editor.getCaretModel().getOffset();
        PsiElement referenceElement = hasSelection
                ? PsiUtils.findFavouriteSelectionParentElement(psiFile, selection, types)
                : findElementAt(psiFile, offset, direction);
        PsiElement element = PsiUtils.findFavouriteParentElement(referenceElement, types);

        if (element != null && hasSelection) {
            if (element.getTextRange().equals(selection)) {
                //element in selection found, try with parent
                return PsiUtils.findFavouriteParentElement(element.getContext(), types);
            }
        }

        return element;
    }

    protected Project findProject(Editor editor) {
        return PlatformDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext(editor.getContentComponent()));
    }
}
