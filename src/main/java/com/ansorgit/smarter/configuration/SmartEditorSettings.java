/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: SmartEditorSettings.java, Class: SmartEditorSettings
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

package com.ansorgit.smarter.configuration;

import com.ansorgit.smarter.PsiClassname;
import com.ansorgit.smarter.PsiClassnames;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * User: jansorg
 * Date: Mar 22, 2010
 * Time: 8:55:45 PM
 */
public class SmartEditorSettings {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane1;
    private JCheckBox javaClasses;
    private JCheckBox javaMethods;
    private JCheckBox javaBlocks;
    private JCheckBox javaIfStatement;
    private JCheckBox javaLoops;
    private JCheckBox javaSwitch;
    private JCheckBox xmlTag;
    private JCheckBox jsClass;
    private JCheckBox jsFunction;
    private JCheckBox jsBlock;
    private JCheckBox phpClass;
    private JCheckBox phpMethod;
    private JCheckBox phpIf;
    private JCheckBox phpWhile;
    private JCheckBox phpFor;
    private JCheckBox phpForeach;
    private JCheckBox sqlStatement;
    private JCheckBox bashFunction;
    private JCheckBox bashLoop;
    private JCheckBox bashIf;
    private JCheckBox cssRuleset;
    private JCheckBox cssRulesetList;
    private JCheckBox cssMedia;
    private JCheckBox jsPackage;
    private JCheckBox jsLoop;

    @Nullable
    private JCheckBox findCheckbox(PsiClassname clazz) {
        if (clazz.equals(PsiClassnames.getInstance().JAVA_CLASS)) {
            return javaClasses;
        }
        if (clazz.equals(PsiClassnames.getInstance().JAVA_IF)) {
            return javaIfStatement;
        }
        if (clazz.equals(PsiClassnames.getInstance().JAVA_LOOP)) {
            return javaLoops;
        }
        if (clazz.equals(PsiClassnames.getInstance().JAVA_CLASS)) {
            return javaClasses;
        }
        if (clazz.equals(PsiClassnames.getInstance().JAVA_BLOCK_STATEMENT)) {
            return javaBlocks;
        }
        if (clazz.equals(PsiClassnames.getInstance().JAVA_METHOD)) {
            return javaMethods;
        }
        if (clazz.equals(PsiClassnames.getInstance().JAVA_SWITCH)) {
            return javaSwitch;
        }

        //javascript
        if (clazz.equals(PsiClassnames.getInstance().JS_PACKAGE)) {
            return jsPackage;
        }
        if (clazz.equals(PsiClassnames.getInstance().JS_CLASS)) {
            return jsClass;
        }
        if (clazz.equals(PsiClassnames.getInstance().JS_FUNCTION)) {
            return jsFunction;
        }
        if (clazz.equals(PsiClassnames.getInstance().JS_BLOCK)) {
            return jsBlock;
        }
        if (clazz.equals(PsiClassnames.getInstance().JS_LOOP_STATEMENT)) {
            return jsLoop;
        }

        //xml
        if (clazz.equals(PsiClassnames.getInstance().XML_TAG)) {
            return xmlTag;
        }

        //css
        if (clazz.equals(PsiClassnames.getInstance().CSS_MEDIA)) {
            return cssMedia;
        }
        if (clazz.equals(PsiClassnames.getInstance().CSS_RULESET)) {
            return cssRuleset;
        }
        if (clazz.equals(PsiClassnames.getInstance().CSS_RULESET_LIST)) {
            return cssRulesetList;
        }

        //SQL
        if (clazz.equals(PsiClassnames.getInstance().SQL_STATEMENT)) {
            return sqlStatement;
        }

        //PHP
        if (clazz.equals(PsiClassnames.getInstance().PHP_CLASS)) {
            return phpClass;
        }
        if (clazz.equals(PsiClassnames.getInstance().PHP_METHOD)) {
            return phpMethod;
        }
        if (clazz.equals(PsiClassnames.getInstance().PHP_FOR)) {
            return phpFor;
        }
        if (clazz.equals(PsiClassnames.getInstance().PHP_FOREACH)) {
            return phpForeach;
        }
        if (clazz.equals(PsiClassnames.getInstance().PHP_IF)) {
            return phpIf;
        }
        if (clazz.equals(PsiClassnames.getInstance().PHP_WHILE)) {
            return phpWhile;
        }

        //Bash
        if (clazz.equals(PsiClassnames.getInstance().BASH_FUNCTION_DEF)) {
            return bashFunction;
        }
        if (clazz.equals(PsiClassnames.getInstance().BASH_IF)) {
            return bashIf;
        }
        if (clazz.equals(PsiClassnames.getInstance().BASH_LOOP)) {
            return bashLoop;
        }

        return null;
    }

    private boolean isEnabled(PsiClassname clazz) {
        JCheckBox checkbox = findCheckbox(clazz);

        return checkbox != null && checkbox.isSelected();
    }

    private void enable(PsiClassname clazz) {
        JCheckBox checkbox = findCheckbox(clazz);

        if (checkbox != null) {
            checkbox.setSelected(true);
        }
    }

    private void disable(PsiClassname clazz) {
        JCheckBox checkbox = findCheckbox(clazz);

        if (checkbox != null) {
            checkbox.setSelected(false);
        }
    }

    public boolean isModified(Settings settings) {
        for (PsiClassname c : PsiClassnames.getInstance().all) {
            if (isEnabled(c) != settings.isClassEnabled(c)) {
                return true;
            }
        }

        return false;
    }

    public void reset(@Nullable Settings settings) {
        for (PsiClassname c : PsiClassnames.getInstance().all) {
            if (settings != null && settings.isClassEnabled(c)) {
                enable(c);
            } else {
                disable(c);
            }
        }
    }

    public void storeIn(Settings settings) {
        for (PsiClassname c : PsiClassnames.getInstance().all) {
            if (isEnabled(c)) {
                settings.enable(c);
            } else {
                settings.disable(c);
            }
        }
    }

    public JComponent getRootPanel() {
        return rootPanel;
    }
}
