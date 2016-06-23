/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: PsiClassname.java, Class: PsiClassname
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

import com.intellij.psi.PsiElement;

/**
 * A wrapper around a fully qualified classname. SmarterEditor works with class names
 * to be useful with all kinds of IntelliJ plattform based products.
 * <p/>
 * SmarterEditor must not depend on each of the supported language plugin, these are usually only
 * available in the ultimate edition.
 * <p/>
 * User: jansorg
 * Date: Mar 12, 2010
 * Time: 7:08:21 PM
 */
@SuppressWarnings({"UnusedDeclaration"})
public final class PsiClassname implements Instance {
    private String className;

    public PsiClassname() {
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    private PsiClassname(String className) {
        this.className = className;
    }

    public static PsiClassname create(String className) {
        return new PsiClassname(className);
    }

    @Override
    public boolean isInstance(PsiElement e) {
        return isInstance(e.getClass());
    }

    private boolean isInstance(Class<?> clazz) {
        if (clazz == null || className == null) {
            return false;
        }

        if (className.equals(clazz.getName())) {
            return true;
        }

        //check hierarchy types
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> i : interfaces) {
            if (isInstance(i)) {
                return true;
            }
        }

        //check superclass
        return isInstance(clazz.getSuperclass());
    }

    @Override
    public int compareTo(PsiClassname o) {
        return o == this ? 0 : this.className.compareTo(o.className);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PsiClassname that = (PsiClassname) o;

        return !(className != null ? !className.equals(that.className) : that.className != null);

    }

    @Override
    public int hashCode() {
        return className != null ? className.hashCode() : 0;
    }

    @Override
    public String toString() {
        return className;
    }
}
