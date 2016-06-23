/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: LanguageSplitHandler.java, Class: LanguageSplitHandler
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

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Encapsulates the string splitting for a certain type of strings (e.g. Java, JavaScript, PHP)
 * <p/>
 * User: jansorg
 * Date: Mar 28, 2010
 * Time: 2:04:51 PM
 */
public interface LanguageSplitHandler {
    /**
     * Returns true if a split action is support by this handler inside of the given element.
     *
     * @param element The checked element
     * @return True if the element is supported
     */
    boolean supportsElement(@NotNull PsiElement element);

    /**
     * Returns the concatenation operator as string, e.g. for Java the + operator.
     *
     * @param element The split element
     * @return The operator used for the concatenation
     */
    String getConcatOperator(@NotNull PsiElement element);

    /**
     * The string end marker for the given literal.
     *
     * @param element The split element
     * @return The string start marker for the given element.
     */
    String getStringStart(@NotNull PsiElement element);

    /**
     * The string end marker for the given psi element.
     *
     * @param element The split element
     * @return The end marker for the given string psi element.
     */
    String getStringEnd(@NotNull PsiElement element);

    /**
     * Returns the text which should be inserted as a dummy placeholder if a
     * string is split without a selection.
     * This is necessary to force a properly formatted split string.
     *
     * @param element The split element
     * @return The dummy placeholder string. Null if not placeholder should be inserted.
     */
    @Nullable
    String getDummyPlaceholder(@NotNull PsiElement element);
}
