/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: JavaSplitHandler.java, Class: JavaSplitHandler
 * Last modified: 2016-12-28
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

import com.ansorgit.smarter.PsiClassname;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

/**
 * User: jansorg
 * Date: Mar 28, 2010
 * Time: 2:14:28 PM
 */
public class JavaSplitHandler implements LanguageSplitHandler {
    private static final PsiClassname STRING_LITERAL = PsiClassname.create("com.intellij.psi.PsiJavaToken");

    public boolean supportsElement(@NotNull PsiElement element) {
        return STRING_LITERAL.isInstance(element) && element.getNode().getElementType().toString().equals("STRING_LITERAL");
    }

    public String getConcatOperator(@NotNull PsiElement element) {
        return "+";
    }

    public String getStringStart(@NotNull PsiElement element) {
        return "\"";
    }

    public String getStringEnd(@NotNull PsiElement element) {
        return "\"";
    }

    @Override
    public String getDummyPlaceholder(@NotNull PsiElement element) {
        return getStringStart(element) + getStringEnd(element);
    }
}
