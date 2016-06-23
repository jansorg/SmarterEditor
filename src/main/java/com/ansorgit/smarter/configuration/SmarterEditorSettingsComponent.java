/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: SmarterEditorSettingsComponent.java, Class: SmarterEditorSettingsComponent
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
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

/**
 * User: jansorg
 * Date: Oct 30, 2009
 * Time: 9:12:54 PM
 */
@State(
        name = "SmarterEditorSettings",
        storages = {
                @Storage(id = "default",
                        file = "$APP_CONFIG$/smartereditor.xml")
        }
)
public class SmarterEditorSettingsComponent implements PersistentStateComponent<Settings>, ApplicationComponent {
    private Settings settings = SettingsManager.defaultSettings();

    public Settings getState() {
        return settings;
    }

    public void loadState(Settings state) {
        this.settings = state;
    }

    @NotNull
    public String getComponentName() {
        return "SmarterEditorSettings";
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }
}
