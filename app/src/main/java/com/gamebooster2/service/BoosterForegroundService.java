package com.gamebooster2.service;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.IBinder;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.lifecycle.LifecycleService;
import com.gamebooster2.booster.BoosterEngine;
import com.gamebooster2.ui.MainActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BoosterForegroundService.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\"\u0010\u0011\u001a\u00020\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/gamebooster2/service/BoosterForegroundService;", "Landroidx/lifecycle/LifecycleService;", "()V", "engine", "Lcom/gamebooster2/booster/BoosterEngine;", "buildNotif", "Landroid/app/Notification;", "text", "", "createChannel", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "updateNotif", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BoosterForegroundService extends LifecycleService {
    public static final String ACTION_STOP = "com.gamebooster2.STOP";
    public static final String CHANNEL_ID = "gb2_channel";
    public static final int NOTIF_ID = 1;
    private BoosterEngine engine;

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onCreate() {
        super.onCreate();
        createChannel();
        startForeground(1, buildNotif("GameBooster running..."));
        this.engine = new BoosterEngine(this);
        BoosterEngine boosterEngine = this.engine;
        BoosterEngine boosterEngine2 = null;
        if (boosterEngine == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine = null;
        }
        boosterEngine.setOnBoostActivated(new Function1<String, Unit>() { // from class: com.gamebooster2.service.BoosterForegroundService.onCreate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String pkg) {
                Intrinsics.checkNotNullParameter(pkg, "pkg");
                BoosterForegroundService.this.updateNotif("Boosting: " + StringsKt.substringAfterLast$default(pkg, '.', (String) null, 2, (Object) null));
            }
        });
        BoosterEngine boosterEngine3 = this.engine;
        if (boosterEngine3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine3 = null;
        }
        boosterEngine3.setOnBoostDeactivated(new Function0<Unit>() { // from class: com.gamebooster2.service.BoosterForegroundService.onCreate.2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                BoosterForegroundService.this.updateNotif("GameBooster running...");
            }
        });
        BoosterEngine boosterEngine4 = this.engine;
        if (boosterEngine4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine4 = null;
        }
        boosterEngine4.setOnRamFreed(new Function1<Integer, Unit>() { // from class: com.gamebooster2.service.BoosterForegroundService.onCreate.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int mb) {
                BoosterForegroundService.this.updateNotif("Freed " + mb + "MB RAM");
            }
        });
        BoosterEngine boosterEngine5 = this.engine;
        if (boosterEngine5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
        } else {
            boosterEngine2 = boosterEngine5;
        }
        boosterEngine2.start();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, ACTION_STOP)) {
            stopSelf();
            return 1;
        }
        return 1;
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        BoosterEngine boosterEngine = this.engine;
        if (boosterEngine == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine = null;
        }
        boosterEngine.stop();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onBind(intent);
        return null;
    }

    private final void createChannel() {
        NotificationChannel ch = new NotificationChannel(CHANNEL_ID, "GameBooster", 2);
        ch.setShowBadge(false);
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(ch);
    }

    private final Notification buildNotif(String text) {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        Intent $this$buildNotif_u24lambda_u240 = new Intent(this, (Class<?>) BoosterForegroundService.class);
        $this$buildNotif_u24lambda_u240.setAction(ACTION_STOP);
        Unit unit = Unit.INSTANCE;
        PendingIntent stopPi = PendingIntent.getService(this, 1, $this$buildNotif_u24lambda_u240, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        Notification notificationBuild = new Notification.Builder(this, CHANNEL_ID).setContentTitle("GameBooster").setContentText(text).setSmallIcon(R.drawable.ic_media_play).setContentIntent(pi).addAction(R.drawable.ic_delete, "Stop", stopPi).setOngoing(true).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        return notificationBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNotif(String text) {
        ((NotificationManager) getSystemService(NotificationManager.class)).notify(1, buildNotif(text));
    }
}
