/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: FormatElementHandler.java, Class: FormatElementHandler
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

package com.ansorgit.smarter.formatFavourite;

import com.ansorgit.smarter.AbstractEditorHandler;
import com.ansorgit.smarter.SearchDirection;
import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

/**
 * User: jansorg
 * Date: Mar 5, 2010
 * Time: 9:38:15 PM
 */
public class FormatElementHandler extends AbstractEditorHandler {
    private boolean select = true;

    public FormatElementHandler() {
        super(false, SearchDirection.Backwards);
    }

    @Override
    protected void elementAction(Project project, PsiElement psiElement, Editor editor) {
        TextRange r = psiElement.getTextRange();
        if (select) {
            editor.getSelectionModel().setSelection(r.getStartOffset(), r.getEndOffset());
        }

        new ReformatCodeProcessor(project, psiElement.getContainingFile(), r, false).run();
    }
}
