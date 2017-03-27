package com.hayukleung.view.CollapsibleView.UsingCollapsibleView;

import android.os.Handler;
import android.os.Message;
import java.util.Vector;

/**
 * Message Handler class that supports buffering up of messages when the
 * activity is paused i.e. in the background.
 */
public abstract class PausedHandler extends Handler {
  public static final int RX_MESSAGE_ID = Integer.MAX_VALUE;
  public static final int RUN_MESSAGE_ID = Integer.MAX_VALUE - 1;

  /**
   * Message Queue Buffer
   */
  final Vector<Message> messageQueueBuffer = new Vector<Message>();

  /**
   * Flag indicating the pause state
   */
  private boolean paused;

  /**
   * Resume the handler
   */
  final public void resume() {
    paused = false;

    while (messageQueueBuffer.size() > 0) {
      final Message msg = messageQueueBuffer.elementAt(0);
      messageQueueBuffer.removeElementAt(0);
      sendMessage(msg);
    }
  }

  /**
   * Pause the handler
   */
  final public void pause() {
    paused = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override final public void handleMessage(Message msg) {
    if (paused) {
      if (storeMessage(msg)) {
        Message msgCopy = new Message();
        msgCopy.copyFrom(msg);
        messageQueueBuffer.add(msgCopy);
      }
    } else {
      if (msg.what == RX_MESSAGE_ID && msg.obj instanceof Runnable) {
        ((Runnable) msg.obj).run();
      } else if (msg.what == RUN_MESSAGE_ID && msg.obj instanceof Runnable) {
        ((Runnable) msg.obj).run();
      } else {
        processMessage(msg);
      }
    }
  }

  /**
   * Notification that the message is about to be stored as the activity is
   * paused. If not handled the message will be saved and replayed when the
   * activity resumes.
   *
   * @param message the message which optional can be handled
   * @return true if the message is to be stored
   */
  protected boolean storeMessage(Message message) {
    return true;
  }

  /**
   * Notification message to be processed. This will either be directly from
   * handleMessage or played back from a saved message when the activity was
   * paused.
   *
   * @param message the message to be handled
   */
  protected abstract void processMessage(Message message);

  public void sendAction(Runnable runnable) {
    sendAction(runnable, 0);
  }

  public void sendAction(Runnable runnable, long delayMillis) {
    Message msg = obtainMessage(RUN_MESSAGE_ID);
    msg.obj = runnable;
    sendMessageDelayed(msg, delayMillis);
  }
}