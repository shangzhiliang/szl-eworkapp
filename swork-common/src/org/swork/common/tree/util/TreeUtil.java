package org.swork.common.tree.util;

import java.util.HashMap;
import java.util.List;

import org.swork.common.tree.TreeNode;

public class TreeUtil
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static TreeNode makeTree(List<TreeNode> treeNodes)
  {
    TreeNode root = null;
    if ((treeNodes != null) && (treeNodes.size() > 0)) {
      HashMap tmpMap = new HashMap();
      
      for (TreeNode treeNode : treeNodes) {
        tmpMap.put(treeNode.getId(), treeNode);
      }
      
      for (TreeNode treeNode : treeNodes) {
        if (treeNode.isRoot()) {
          root = treeNode;
        } else {
          TreeNode parent = (TreeNode)tmpMap.get(treeNode.getParentId());
          parent.getChildren().add(treeNode);
        }
      }
      
    }
    
    return root;
    
  }
}