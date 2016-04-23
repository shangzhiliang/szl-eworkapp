package org.swork.common.tree;

import java.util.List;

public abstract interface TreeNode extends Comparable<TreeNode>{
    public abstract String getId();

    public abstract String getParentId();

    public abstract Integer getLayer();

    public abstract boolean isRoot();

    public abstract boolean isLeaf();

    public abstract List<TreeNode> getChildren();

    public abstract String convertTreeToString(TreeConverter paramTreeConverter);
}
