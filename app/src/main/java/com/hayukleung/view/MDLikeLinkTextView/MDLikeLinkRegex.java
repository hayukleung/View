package com.hayukleung.view.MDLikeLinkTextView;

/**
 * View
 * com.hayukleung.view.MDLikeLinkTextView
 * MDLikeLinkRegex.java
 *
 * by hayukleung
 * at 2017-01-24 17:46
 */

/**
 * [0] get markdown like link string from text
 * - \[{1}([^\[\]\(\)]+)\]\({1}([^\[\]\(\)]+)\)
 * @see MDLikeLinkRegex#REGEX
 *
 * [1] get text from markdown like link string
 * - \[{1}([^\[\]\(\)]+)\]
 * @see MDLikeLinkRegex#REGEX_TEXT
 *
 * [2] get link from markdown like link string
 * - \({1}([^\[\]\(\)]+)\)
 * @see MDLikeLinkRegex#REGEX_LINK
 */
public class MDLikeLinkRegex {

  /**
   * [0]
   */
  public static final String REGEX = "\\[{1}([^\\[\\]\\(\\)]+)\\]\\({1}([^\\[\\]\\(\\)]+)\\)";
  /**
   * [1]
   */
  public static final String REGEX_TEXT = "\\[{1}([^\\[\\]\\(\\)]+)\\]";
  /**
   * [2]
   */
  public static final String REGEX_LINK = "\\({1}([^\\[\\]\\(\\)]+)\\)";
}
