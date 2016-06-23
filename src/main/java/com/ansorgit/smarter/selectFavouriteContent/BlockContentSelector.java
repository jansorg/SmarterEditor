package com.ansorgit.smarter.selectFavouriteContent;

import com.ansorgit.smarter.Instance;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: jansorg
 * Date: 13.06.11
 * Time: 10:44
 */
public abstract class BlockContentSelector {
    private final Instance type;

    public BlockContentSelector(Instance type) {
        this.type = type;
    }

    @NotNull
    public Instance getType() {
        return type;
    }

    @Nullable
    public abstract PsiElement findFirst(PsiElement psi);

    @Nullable
    public abstract PsiElement findLast(PsiElement psi);
}
