/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: PsiClassnames.java, Class: PsiClassnames
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

import com.ansorgit.smarter.configuration.Settings;
import com.ansorgit.smarter.configuration.SettingsManager;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: jansorg
 * Date: Mar 6, 2010
 * Time: 8:19:02 PM
 */
public class PsiClassnames {
    private static final PsiClassnames instance = new PsiClassnames();

    @NotNull
    public synchronized static PsiClassnames getInstance() {
        return instance;
    }

    public final Set<PsiClassname> all = new HashSet<PsiClassname>();

    public final PsiClassname JAVA_LOOP = addNew("com.intellij.psi.PsiLoopStatement");
    public final PsiClassname JAVA_SWITCH = addNew("com.intellij.psi.PsiSwitchStatement");
    public final PsiClassname JAVA_IF = addNew("com.intellij.psi.PsiIfStatement");
    public final PsiClassname JAVA_METHOD = addNew("com.intellij.psi.PsiMethod");
    public final PsiClassname JAVA_CLASS = addNew("com.intellij.psi.PsiClass");
    public final PsiClassname JAVA_BLOCK_STATEMENT = addNew("com.intellij.psi.PsiBlockStatement");
    public final PsiClassname JAVA_CODE_BLOCK = addNew("com.intellij.psi.PsiCodeBlock");

    public final PsiClassname XML_TAG = addNew("com.intellij.psi.xml.XmlTag");

    public final PsiClassname JS_PACKAGE = addNew("com.intellij.lang.javascript.psi.ecmal4.JSPackage");
    public final PsiClassname JS_CLASS = addNew("com.intellij.lang.javascript.psi.ecmal4.JSClass");
    public final PsiClassname JS_FUNCTION = addNew("com.intellij.lang.javascript.psi.JSFunction");
    public final PsiClassname JS_LOOP_STATEMENT = addNew("com.intellij.lang.javascript.psi.JSLoopStatement");
    public final PsiClassname JS_BLOCK = addNew("com.intellij.lang.javascript.psi.JSBlockStatement");

    public final PsiClassname PHP_FOREACH = addNew("com.jetbrains.php.lang.psi.elements.Foreach");
    public final PsiClassname PHP_FOR = addNew("com.jetbrains.php.lang.psi.elements.For");
    public final PsiClassname PHP_WHILE = addNew("com.jetbrains.php.lang.psi.elements.While");
    public final PsiClassname PHP_IF = addNew("com.jetbrains.php.lang.psi.elements.If");
    public final PsiClassname PHP_METHOD = addNew("com.jetbrains.php.lang.psi.elements.Method");
    public final PsiClassname PHP_CLASS = addNew("com.jetbrains.php.lang.psi.elements.PhpClass");
    public final PsiClassname PHP_STRING_LITERAL = addNew("com.jetbrains.php.lang.psi.elements.StringLiteralExpression");

    public final PsiClassname SQL_STATEMENT = addNew("com.intellij.sql.psi.SqlStatement");
    public final PsiClassname QL_UPDATE = addNew("com.intellij.ql.psi.QlUpdateClause");
    public final PsiClassname QL_SELECT = addNew("com.intellij.ql.psi.QlSelectClause");

    public final PsiClassname CSS_MEDIA = addNew("com.intellij.psi.css.impl.CssMediaImpl");
    public final PsiClassname CSS_RULESET_LIST = addNew("com.intellij.psi.css.CssRulesetList");
    public final PsiClassname CSS_RULESET = addNew("com.intellij.psi.css.CssRuleset");

    public final PsiClassname BASH_IF = addNew("com.ansorgit.plugins.bash.lang.psi.api.shell.BashIf");
    public final PsiClassname BASH_LOOP = addNew("com.ansorgit.plugins.bash.lang.psi.api.loops.BashLoop");
    public final PsiClassname BASH_FUNCTION_DEF = addNew("com.ansorgit.plugins.bash.lang.psi.api.function.BashFunctionDef");

    @NotNull
    private PsiClassname addNew(String className) {
        PsiClassname psiClassname = PsiClassname.create(className);
        all.add(psiClassname);
        return psiClassname;
    }

    @NotNull
    public static List<PsiClassname> getConfiguredTypes() {
        Settings settings = SettingsManager.storedSettings();

        return settings == null ? Collections.<PsiClassname>emptyList() : settings.configuredClasses;
    }

    @Nullable
    public static PsiClassname findConfiguredType(PsiElement element) {
        for (PsiClassname psiClassname : getConfiguredTypes()) {
            if (psiClassname.isInstance(element)) {
                return psiClassname;
            }
        }

        return null;
    }

    @Nullable
    public static PsiClassname findAnyType(PsiElement element) {
        for (PsiClassname psiClassname : getInstance().all) {
            if (psiClassname.isInstance(element)) {
                return psiClassname;
            }
        }

        return null;
    }
}
