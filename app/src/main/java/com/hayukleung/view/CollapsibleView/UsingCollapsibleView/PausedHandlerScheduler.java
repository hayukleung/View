package com.hayukleung.view.CollapsibleView.UsingCollapsibleView;

import android.os.Handler;
import android.os.Message;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.android.plugins.RxAndroidPlugins;
import rx.functions.Action0;
import rx.internal.schedulers.ScheduledAction;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * A {@link Scheduler} backed by a {@link PausedHandler}.
 */
public final class PausedHandlerScheduler extends Scheduler {
  private final PausedHandler handler;

  PausedHandlerScheduler(PausedHandler handler) {
    this.handler = handler;
  }

  /**
   * Create a {@link Scheduler} which uses {@code PausedHandler} to execute actions.
   */
  public static PausedHandlerScheduler from(PausedHandler handler) {
    if (handler == null) throw new NullPointerException("handler == null");
    return new PausedHandlerScheduler(handler);
  }

  @Override public Worker createWorker() {
    return new HandlerWorker(handler);
  }

  static class HandlerWorker extends Worker {

    private final Handler handler;

    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    HandlerWorker(Handler handler) {
      this.handler = handler;
    }

    @Override public void unsubscribe() {
      compositeSubscription.unsubscribe();
    }

    @Override public boolean isUnsubscribed() {
      return compositeSubscription.isUnsubscribed();
    }

    @Override public Subscription schedule(final Action0 action) {
      return schedule(action, 0, TimeUnit.MILLISECONDS);
    }

    @Override public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
      if (compositeSubscription.isUnsubscribed()) {
        return Subscriptions.unsubscribed();
      }

      action = RxAndroidPlugins.getInstance().getSchedulersHook().onSchedule(action);

      final ScheduledAction scheduledAction = new ScheduledAction(action);
      scheduledAction.addParent(compositeSubscription);
      compositeSubscription.add(scheduledAction);

      Message message = handler.obtainMessage(PausedHandler.RX_MESSAGE_ID);
      message.obj = scheduledAction;
      handler.sendMessageDelayed(message, unit.toMillis(delayTime));
      scheduledAction.add(Subscriptions.create(new Action0() {
        @Override public void call() {
          handler.removeMessages(PausedHandler.RX_MESSAGE_ID, scheduledAction);
        }
      }));

      return scheduledAction;
    }
  }
}
