package com.hayukleung.view.GroupingTagView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * View
 * com.hayukleung.view.GroupingTagView
 * GroupingViewTest.java
 *
 * by hayukleung
 * at 2017-03-22 15:46
 */

public class GroupingViewTest {

  // GroupingTag 个数
  private static final int GROUPING_TAG_COUNT = 50;

  // 单行所能容纳的最大长度
  private static final int MAX_LENGTH = 30;

  public static void main(String[] args) {

    // 随机生成 GROUPING_TAG_COUNT 个 GroupingTag
    List<GroupingTag> groupingTagList = new ArrayList<>(GROUPING_TAG_COUNT);
    for (int i = 0; i < GROUPING_TAG_COUNT; i++) {
      GroupingTag groupingTag = new GroupingTag();
      groupingTag.setLength(new Random().nextInt(10) + 1);
      groupingTagList.add(groupingTag);
    }

    // 按长度从小到大排序
    Collections.sort(groupingTagList);

    // 计算所有 GroupingTag 的总长度
    int totalLength = 0;
    for (GroupingTag groupingTag : groupingTagList) {
      totalLength += groupingTag.getLength();
    }

    // 计算行数，向上取整
    final int lineCount = (int) Math.ceil((double) totalLength / (double) MAX_LENGTH);

    // 分组
    // 顺序 - 从小到大
    // 逆序 - 从大到小
    List<GroupingLine> group = new ArrayList<>(lineCount);
    for (int i = 0; i < lineCount; i++) {
      group.add(new GroupingLine());
    }

    // 0 - 4 效果逐步提升
    switch (3) {
      case 0: {
        // 等数分组 - 顺序分组
        do {
          for (int i = 0; i < lineCount; i++) {
            if (0 < groupingTagList.size()) {
              group.get(i).addGroupingTag(groupingTagList.remove(0));
            }
          }
        } while (0 < groupingTagList.size());
        break;
      }
      case 1: {
        // 等数分组 - 顺序逆序交替分组
        int flag = 0;
        do {
          for (int i = 0; i < lineCount; i++) {
            int size = groupingTagList.size();
            if (0 < size) {
              group.get(i).addGroupingTag(groupingTagList.remove((0 == flag % 2) ? 0 : size - 1));
            }
          }
          flag++;
        } while (0 < groupingTagList.size());
        break;
      }
      case 2: {
        // 等数分组 - 根据所在行剩余容量顺序分组
        do {
          for (int i = 0; i < lineCount; i++) {
            int size = groupingTagList.size();
            if (0 < size) {
              group.get(i).addGroupingTag(groupingTagList.remove(0));
            }
          }
          Collections.sort(group);
          Collections.reverse(group);
        } while (0 < groupingTagList.size());
        break;
      }
      case 3: {
        // 等数分组 - 根据所在行剩余容量顺序逆序交替分组
        int flag = 0;
        do {
          for (int i = 0; i < lineCount; i++) {
            int size = groupingTagList.size();
            if (0 < size) {
              group.get(i).addGroupingTag(groupingTagList.remove((0 == flag % 2) ? 0 : size - 1));
            }
          }
          Collections.sort(group);
          if (0 == flag % 2) {
            // 顺序
            // do nothing
          } else {
            // 逆序
            Collections.reverse(group);
          }
          flag++;
        } while (0 < groupingTagList.size());
        break;
      }
      case 4: {
        // 不等数分组
        break;
      }
    }

    // 打印结果
    for (int i = 0; i < lineCount; i++) {
      System.out.println(i + " --> " + group.get(i).toString());
    }
  }
}
