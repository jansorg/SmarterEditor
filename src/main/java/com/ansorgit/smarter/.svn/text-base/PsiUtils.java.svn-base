/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: PsiUtils.java, Class: PsiUtils
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

import com.intellij.lang.Language;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

/**
 * Several utility methods.
 * <p/>
 * Contains patched methods of JetBrain's PsiTreeUtil which were changed to work with PsiClassname.
 * <p/>
 * User: jansorg
 * Date: Mar 5, 2010
 * Time: 9:50:16 PM
 */
public class PsiUtils {
    public static PsiElement eatWhitespace(PsiElement element, SearchDirection direction) {
        PsiElement current = element;

        while (current instanceof PsiWhiteSpace) {
            if (direction == SearchDirection.Forwards) {
                current = current.getNextSibling();
            } else if (direction == SearchDirection.Backwards) {
                current = current.getPrevSibling();
            }
        }

        return current;
    }

    /**
     * Finds the next parent element which contains the selection and which matches on the provided types.
     *
     * @param psiFile   The psi file which is being edited
     * @param selection The current selection
     * @param types     The types used for the lookup
     * @return The next available ancestor element which contains the selection and which matches one of the types. May be null.
     */
    @Nullable
    public static PsiElement findFavouriteSelectionParentElement(PsiFile psiFile, @Nullable TextRange selection, @NotNull List<PsiClassname> types) {
        if (selection == null) {
            return null;
        }

        //find element in selection
        PsiElement current = null;
        Iterator<PsiClassname> classIterator = types.iterator();
        while (current == null && classIterator.hasNext()) {
            PsiClassname type = classIterator.next();
            current = findElementOfClassAtRange(psiFile, selection.getStartOffset(), selection.getEndOffset(), type);
        }

        //find the next favourite
        return current != null ? findFavouriteParentElement(current.getContext(), types) : null;
    }

    /**
     * Finds the next favourite psi element in the context of the start element.
     * Only parent elements which match one of the provided types are favourite elements.
     *
     * @param start The start of the lookup process
     * @param types The accepted types.
     * @return The next ancestor element matching one of the types. May be null.
     */
    @Nullable
    public static PsiElement findFavouriteParentElement(@Nullable PsiElement start, @NotNull List<PsiClassname> types) {
        PsiElement e = start;
        while (e != null) {
            //check types
            for (PsiClassname type : types) {
                if (type.isInstance(e)) {
                    return e;
                }
            }

            e = e.getContext();
        }

        return null;
    }

    @Nullable
    //taken from PsiTreeUtil, patched to work with PsiClassname
    private static PsiElement findElementOfClassAtRange(@NotNull PsiFile file, int startOffset, int endOffset, @NotNull PsiClassname clazz) {
        final FileViewProvider viewProvider = file.getViewProvider();
        PsiElement result = null;
        for (Language lang : viewProvider.getLanguages()) {
            PsiElement elementAt = viewProvider.findElementAt(startOffset, lang);
            PsiElement run = getParentOfType(elementAt, clazz, false);
            PsiElement prev = run;
            while (run != null && run.getTextRange().getStartOffset() == startOffset &&
                    run.getTextRange().getEndOffset() <= endOffset) {
                prev = run;
                run = getParentOfType(run, clazz);
            }

            if (prev == null) {
                continue;
            }
            final int elementStartOffset = prev.getTextRange().getStartOffset();
            final int elementEndOffset = prev.getTextRange().getEndOffset();
            if (elementStartOffset != startOffset || elementEndOffset > endOffset) {
                continue;
            }

            if (result == null || result.getTextRange().getEndOffset() < elementEndOffset) {
                result = prev;
            }
        }

        return result;
    }


    @Nullable
    //taken from PsiTreeUtil, patched to work with PsiClassname
    private static PsiElement getParentOfType(@Nullable PsiElement element, @NotNull PsiClassname aClass) {
        return getParentOfType(element, aClass, true);
    }

    @Nullable
    //taken from PsiTreeUtil, patched to work with PsiClassname
    private static <T extends PsiElement> T getParentOfType(@Nullable PsiElement element, @NotNull PsiClassname aClass, boolean strict) {
        if (element == null) {
            return null;
        }

        if (strict) {
            element = element.getParent();
        }

        while (element != null && !aClass.isInstance(element)) {
            if (element instanceof PsiFile) {
                return null;
            }
            element = element.getParent();
        }

        return (T) element;
    }

}
