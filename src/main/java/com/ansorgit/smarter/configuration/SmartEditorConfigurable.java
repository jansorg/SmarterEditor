/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: SmartEditorConfigurable.java, Class: SmartEditorConfigurable
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

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * User: jansorg
 * Date: Mar 22, 2010
 * Time: 9:09:19 PM
 */
public class SmartEditorConfigurable implements ApplicationComponent, Configurable {
    private SmartEditorSettings settings;

    @NotNull
    public String getComponentName() {
        return "SmarterEditorConfigurable";
    }

    public void initComponent() {
    }

    public void disposeComponent() {
        settings = null;
    }

    @Nls
    public String getDisplayName() {
        return "SmarterEditor";
    }

    public Icon getIcon() {
        return null;
    }

    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        if (settings == null) {
            settings = new SmartEditorSettings();
        }

        return settings.getRootPanel();
    }

    public boolean isModified() {
        return settings.isModified(SettingsManager.storedSettings());
    }

    public void apply() throws ConfigurationException {
        settings.storeIn(SettingsManager.storedSettings());
    }

    public void reset() {
        settings.reset(SettingsManager.storedSettings());
    }

    public void disposeUIResources() {
        settings = null;
    }
}
