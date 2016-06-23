/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: SettingsManager.java, Class: SettingsManager
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

import com.ansorgit.smarter.PsiClassnames;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.Nullable;

/**
 * User: jansorg
 * Date: Mar 22, 2010
 * Time: 9:06:29 PM
 */
public class SettingsManager {
    @Nullable
    public static Settings storedSettings() {
        return ApplicationManager.getApplication().getComponent(SmarterEditorSettingsComponent.class).getState();
    }

    public static Settings defaultSettings() {
        Settings settings = new Settings();
        settings.enable(PsiClassnames.getInstance().JAVA_CLASS);
        settings.enable(PsiClassnames.getInstance().JAVA_METHOD);
        settings.enable(PsiClassnames.getInstance().JAVA_IF);
        settings.enable(PsiClassnames.getInstance().JAVA_LOOP);

        settings.enable(PsiClassnames.getInstance().JS_FUNCTION);
        settings.enable(PsiClassnames.getInstance().JS_LOOP_STATEMENT);

        settings.enable(PsiClassnames.getInstance().PHP_CLASS);
        settings.enable(PsiClassnames.getInstance().PHP_METHOD);
        settings.enable(PsiClassnames.getInstance().PHP_IF);
        settings.enable(PsiClassnames.getInstance().PHP_WHILE);
        settings.enable(PsiClassnames.getInstance().PHP_FOR);
        settings.enable(PsiClassnames.getInstance().PHP_FOREACH);

        settings.enable(PsiClassnames.getInstance().CSS_MEDIA);
        settings.enable(PsiClassnames.getInstance().CSS_RULESET);
        settings.enable(PsiClassnames.getInstance().CSS_RULESET_LIST);

        settings.enable(PsiClassnames.getInstance().BASH_FUNCTION_DEF);
        settings.enable(PsiClassnames.getInstance().BASH_IF);
        settings.enable(PsiClassnames.getInstance().BASH_LOOP);

        settings.enable(PsiClassnames.getInstance().SQL_STATEMENT);

        settings.enable(PsiClassnames.getInstance().XML_TAG);

        return settings;
    }
}
