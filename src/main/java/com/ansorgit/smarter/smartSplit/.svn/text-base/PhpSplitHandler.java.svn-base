/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: PhpSplitHandler.java, Class: PhpSplitHandler
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
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

/**
 * Split handler for the PHP language.
 * <p/>
 * User: jansorg
 * Date: Mar 28, 2010
 * Time: 2:19:55 PM
 */
public class PhpSplitHandler implements LanguageSplitHandler {
    public boolean supportsElement(@NotNull PsiElement element) {
        return isSingleQuoted(element) || isDoubleQuoted(element);

    }

    public String getConcatOperator(@NotNull PsiElement element) {
        return ".";
    }

    public String getStringStart(@NotNull PsiElement element) {
        return isSingleQuoted(element) ? "'" : "\"";
    }

    public String getStringEnd(@NotNull PsiElement element) {
        return getStringStart(element);
    }

    @Override
    public String getDummyPlaceholder(@NotNull PsiElement element) {
        return getStringStart(element) + getStringEnd(element);
    }

    private boolean isSingleQuoted(PsiElement element) {
        return "PsiElement(single quoted string)".equals(element.toString()) &&
                PsiClassnames.getInstance().PHP_STRING_LITERAL.isInstance(element.getParent());
    }

    private boolean isDoubleQuoted(PsiElement element) {
        return "PsiElement(string)".equals(element.toString()) &&
                PsiClassnames.getInstance().PHP_STRING_LITERAL.isInstance(element.getParent());
    }
}