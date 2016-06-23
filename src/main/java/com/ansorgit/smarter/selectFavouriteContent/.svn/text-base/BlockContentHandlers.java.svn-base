package com.ansorgit.smarter.selectFavouriteContent;

import com.ansorgit.smarter.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * User: jansorg
 * Date: 13.06.11
 * Time: 10:47
 */
public class BlockContentHandlers {
    private BlockContentHandlers() {
        List<BlockContentSelector> selectors = Lists.newArrayList(
                JAVA_BLOCK_STATEMENT, JAVA_BLOCK, JAVA_METHOD, JAVA_LOOP, JAVA_CLASS, JAVA_IF
        );

        for (BlockContentSelector selector : selectors) {
            typeMapping.put(selector.getType(), selector);
        }
    }

    private final Map<Instance, BlockContentSelector> typeMapping = Maps.newConcurrentMap();

    @NotNull
    public PsiElement selectFirst(PsiElement psi) {
        PsiClassname type = PsiClassnames.findConfiguredType(psi);
        if (type != null && typeMapping.containsKey(type)) {
            return typeMapping.get(type).findFirst(psi);
        }

        return PsiTreeUtil.getDeepestFirst(psi);
    }

    @NotNull
    public PsiElement selectFirstAny(PsiElement psi) {
        PsiClassname type = PsiClassnames.findAnyType(psi);
        if (type != null && typeMapping.containsKey(type)) {
            return typeMapping.get(type).findFirst(psi);
        }

        return PsiTreeUtil.getDeepestFirst(psi);
    }

    @NotNull
    public PsiElement selectLast(PsiElement psi) {
        PsiClassname type = PsiClassnames.findConfiguredType(psi);
        if (type != null && typeMapping.containsKey(type)) {
            return typeMapping.get(type).findLast(psi);
        }

        return PsiTreeUtil.getDeepestLast(psi);
    }

    @NotNull
    public PsiElement selectLastAny(PsiElement psi) {
        PsiClassname type = PsiClassnames.findAnyType(psi);
        if (type != null && typeMapping.containsKey(type)) {
            return typeMapping.get(type).findLast(psi);
        }

        return PsiTreeUtil.getDeepestLast(psi);
    }

    public static final BlockContentHandlers instance = new BlockContentHandlers();

    private final PsiClassnames types = PsiClassnames.getInstance();

    public final BlockContentSelector JAVA_BLOCK = new BlockContentSelector(types.JAVA_CODE_BLOCK) {
        @Override
        public PsiElement findFirst(PsiElement psi) {
            PsiElement first = Reflective.create(psi, getType()).invoke("getFirstBodyElement");

            return first != null ? first : psi;
        }

        @Override
        public PsiElement findLast(PsiElement psi) {
            PsiElement first = Reflective.create(psi, getType()).invoke("getLastBodyElement");

            return first != null ? first : psi;
        }
    };

    public final BlockContentSelector JAVA_BLOCK_STATEMENT = new BlockContentSelector(types.JAVA_BLOCK_STATEMENT) {
        @Override
        public PsiElement findFirst(PsiElement psi) {
            PsiElement block = Reflective.create(psi, getType()).invoke("getCodeBlock");

            return block != null ? selectFirstAny(block) : psi;
        }

        @Override
        public PsiElement findLast(PsiElement psi) {
            PsiElement block = Reflective.create(psi, getType()).invoke("getCodeBlock");

            return block != null ? selectLastAny(block) : psi;
        }
    };


    public final BlockContentSelector JAVA_METHOD = new BlockContentSelector(types.JAVA_METHOD) {
        @Override
        public PsiElement findFirst(PsiElement psi) {
            PsiElement body = Reflective.create(psi).invoke("getBody");
            return selectFirstAny(body != null ? body : psi);
        }

        @Override
        public PsiElement findLast(PsiElement psi) {
            PsiElement body = Reflective.create(psi).invoke("getBody");
            return selectLastAny(body != null ? body : psi);
        }
    };

    public final BlockContentSelector JAVA_LOOP = new BlockContentSelector(types.JAVA_LOOP) {
        @Override
        public PsiElement findFirst(PsiElement psi) {
            PsiElement body = Reflective.create(psi).invoke("getBody");
            return body != null ? selectFirstAny(body) : psi;
        }

        @Override
        public PsiElement findLast(PsiElement psi) {
            PsiElement body = Reflective.create(psi).invoke("getBody");
            return body != null ? selectLastAny(body) : selectLast(psi);
        }
    };

    public final BlockContentSelector JAVA_CLASS = new BlockContentSelector(types.JAVA_CLASS) {
        @Override
        public PsiElement findFirst(PsiElement psi) {
            PsiElement body = Reflective.create(psi).invoke("getLBrace");
            return body != null ? body.getNextSibling() : psi;
        }

        @Override
        public PsiElement findLast(PsiElement psi) {
            PsiElement body = Reflective.create(psi).invoke("getRBrace");
            return body != null ? body.getPrevSibling() : selectLast(psi);
        }
    };

    public final BlockContentSelector JAVA_IF = new BlockContentSelector(types.JAVA_IF) {
        @Override
        public PsiElement findFirst(PsiElement psi) {
            return psi;
        }

        @Override
        public PsiElement findLast(PsiElement psi) {
            return PsiTreeUtil.getDeepestLast(psi);
        }
    };
}
