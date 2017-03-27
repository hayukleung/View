package com.hayukleung.view.CollapsibleView;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * TODO
 *
 * @author HayukLeung
 */
public class ElementException extends RuntimeException {

  public ElementException() {
  }

  public ElementException(String detailMessage) {
    super(detailMessage);
  }

  public ElementException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public ElementException(Throwable throwable) {
    super(throwable);
  }

  @Override public String getMessage() {
    return super.getMessage();
  }

  @Override public String getLocalizedMessage() {
    return super.getLocalizedMessage();
  }

  @Override public Throwable getCause() {
    return super.getCause();
  }

  @Override public Throwable initCause(Throwable throwable) {
    return super.initCause(throwable);
  }

  @Override public String toString() {
    return super.toString();
  }

  @Override public void printStackTrace() {
    super.printStackTrace();
  }

  @Override public void printStackTrace(PrintStream err) {
    super.printStackTrace(err);
  }

  @Override public void printStackTrace(PrintWriter err) {
    super.printStackTrace(err);
  }

  @Override public Throwable fillInStackTrace() {
    return super.fillInStackTrace();
  }

  @Override public StackTraceElement[] getStackTrace() {
    return super.getStackTrace();
  }

  @Override public void setStackTrace(StackTraceElement[] trace) {
    super.setStackTrace(trace);
  }
}
