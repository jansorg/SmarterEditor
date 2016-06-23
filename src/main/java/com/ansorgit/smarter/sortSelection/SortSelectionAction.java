/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: SortSelectionAction.java, Class: SortSelectionAction
 * Last modified: 2010-06-03
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

package com.ansorgit.smarter.sortSelection;

import com.intellij.openapi.editor.actionSystem.EditorAction;

/**
 * This action sorts the selected lines, if there are any. If the selected lines are already in a sorted order then
 * the order of the selection is reversed.
 * <p/>
 * <p/>
 * User: jansorg
 * Date: 03.06.2010
 * Time: 15:48:46
 */
public class SortSelectionAction extends EditorAction {
    public SortSelectionAction() {
        super(new SortSelectionHandler());
    }
}
