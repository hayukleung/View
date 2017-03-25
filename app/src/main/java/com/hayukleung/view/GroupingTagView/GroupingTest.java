package com.hayukleung.view.GroupingTagView;

/**
 * View
 * com.hayukleung.view.GroupingTagView
 * GroupingTest.java
 *
 * by hayukleung
 * at 2017-03-22 15:46
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 纯算法测试
 */
public class GroupingTest {

  // Tag 个数
  private static final int GROUPING_TAG_COUNT = 40;

  // 单行所能容纳的最大长度
  private static final int MAX_LENGTH = 28;

  public static void main(String[] args) {

    // 随机生成 GROUPING_TAG_COUNT 个 Tag
    List<Tag> tagList = new ArrayList<>(GROUPING_TAG_COUNT);

    if (false) {
      for (int i = 0; i < GROUPING_TAG_COUNT; i++) {
        Tag tag = new Tag();
        // 1 - 8 随机数
        int length = new Random().nextInt(8) + 1;
        String content = "";
        for (int j = 0; j < length; j++) {
          content += "*";
        }
        tag.setContent(content);
        tagList.add(tag);
      }
    } else {
      Tag tag;
      tag = new Tag();
      tag.setContent("******");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("***************");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("****");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("***");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("*");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("************");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("**");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("***");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("*******");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("*********");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("**");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("*******");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("***");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("************");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("****");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("*****");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("****");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("**********");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("*********");
      tagList.add(tag);

      tag = new Tag();
      tag.setContent("****");
      tagList.add(tag);
    }
    // 按长度从小到大排序
    Collections.sort(tagList);

    // 计算所有 Tag 的总长度
    int totalLength = 0;
    for (Tag tag : tagList) {
      totalLength += tag.getLength();
    }

    // 计算行数，向上取整
    final int lineCount = (int) Math.ceil((double) totalLength / (double) MAX_LENGTH);

    // 分组
    // 顺序 - 从小到大
    // 逆序 - 从大到小
    List<ITagLine> group = new ArrayList<>(lineCount);
    for (int i = 0; i < lineCount; i++) {
      group.add(new ITagLine());
    }

    // 0 - 5 效果逐步提升
    switch (5) {
      case 0: {
        // 等数分组 - 顺序分组
        do {
          for (int i = 0; i < lineCount; i++) {
            if (0 < tagList.size()) {
              group.get(i).addTag(tagList.remove(0));
            }
          }
        } while (0 < tagList.size());
        break;
      }
      case 1: {
        // 等数分组 - 顺序逆序交替分组
        int flag = 0;
        do {
          for (int i = 0; i < lineCount; i++) {
            int size = tagList.size();
            if (0 < size) {
              group.get(i).addTag(tagList.remove((0 == flag % 2) ? 0 : size - 1));
            }
          }
          flag++;
        } while (0 < tagList.size());
        break;
      }
      case 2: {
        // 等数分组 - 根据所在行剩余容量顺序分组
        do {
          for (int i = 0; i < lineCount; i++) {
            int size = tagList.size();
            if (0 < size) {
              group.get(i).addTag(tagList.remove(0));
            }
          }
          Collections.sort(group);
          Collections.reverse(group);
        } while (0 < tagList.size());
        break;
      }
      case 3: {
        // 等数分组 - 根据所在行剩余容量顺序逆序交替分组
        int flag = 0;
        do {
          for (int i = 0; i < lineCount; i++) {
            int size = tagList.size();
            if (0 < size) {
              group.get(i).addTag(tagList.remove((0 == flag % 2) ? 0 : size - 1));
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
        } while (0 < tagList.size());
        break;
      }
      case 4: {
        // 不等数分组 - 根据所在行剩余容量逆序分组
        Collections.reverse(tagList);
        for (Tag tag : tagList) {
          group.get(0).addTag(tag);
          Collections.sort(group);
        }
        break;
      }
      case 5: {
        // 不等数分组 - 根据所在行剩余容量顺序逆序交替分组
        Collections.reverse(tagList);
        int flag = 0;
        do {
          int size = tagList.size();
          if (0 < size) {
            group.get(0).addTag(tagList.remove((0 == flag % 2 ? 0 : size - 1)));
            Collections.sort(group);
          }
          flag++;
        } while (0 < tagList.size());
        break;
      }
    }

    // 打印结果
    for (int i = 0; i < lineCount; i++) {
      System.out.println(" " + group.get(i).toString());
    }
  }
}
