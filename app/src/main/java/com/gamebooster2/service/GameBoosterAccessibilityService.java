package com.gamebooster2.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameBoosterAccessibilityService.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0014J\u0014\u0010\u0013\u001a\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/gamebooster2/service/GameBoosterAccessibilityService;", "Landroid/accessibilityservice/AccessibilityService;", "()V", "TAG", "", "userGamePackages", "", "isAppInForeground", "", "packageName", "onAccessibilityEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/accessibility/AccessibilityEvent;", "onDestroy", "onGameLaunched", "gamePackage", "onInterrupt", "onServiceConnected", "updateGamePackages", "packages", "", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class GameBoosterAccessibilityService extends AccessibilityService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String currentForegroundPackage = "";
    private static GameBoosterAccessibilityService instance;
    private final String TAG = "GB2Accessibility";
    private Set<String> userGamePackages = new LinkedHashSet();

    /* JADX INFO: compiled from: GameBoosterAccessibilityService.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\"\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/gamebooster2/service/GameBoosterAccessibilityService$Companion;", "", "()V", "<set-?>", "", "currentForegroundPackage", "getCurrentForegroundPackage", "()Ljava/lang/String;", "Lcom/gamebooster2/service/GameBoosterAccessibilityService;", "instance", "getInstance", "()Lcom/gamebooster2/service/GameBoosterAccessibilityService;", "isRunning", "", "()Z", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GameBoosterAccessibilityService getInstance() {
            return GameBoosterAccessibilityService.instance;
        }

        public final String getCurrentForegroundPackage() {
            return GameBoosterAccessibilityService.currentForegroundPackage;
        }

        public final boolean isRunning() {
            return getInstance() != null;
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
        Log.d(this.TAG, "✅ Accessibility service connected!");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = 32;
        info.feedbackType = 16;
        info.flags = 2;
        info.notificationTimeout = 100L;
        setServiceInfo(info);
        Log.d(this.TAG, "Accessibility service configured");
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent event) {
        CharSequence packageName;
        String pkg;
        if (event != null && event.getEventType() == 32 && (packageName = event.getPackageName()) != null && (pkg = packageName.toString()) != null && !Intrinsics.areEqual(pkg, "com.android.systemui") && !Intrinsics.areEqual(pkg, getPackageName()) && !Intrinsics.areEqual(pkg, currentForegroundPackage)) {
            String prev = currentForegroundPackage;
            currentForegroundPackage = pkg;
            Log.d(this.TAG, "Foreground changed: " + prev + " → " + pkg);
            if (this.userGamePackages.contains(pkg)) {
                Log.d(this.TAG, "🎮 Game detected: " + pkg + " — auto boosting!");
                onGameLaunched(pkg);
            }
        }
    }

    private final void onGameLaunched(String gamePackage) {
        Intent intent = new Intent("com.gamebooster2.GAME_LAUNCHED");
        intent.putExtra("package", gamePackage);
        sendBroadcast(intent);
    }

    public final void updateGamePackages(Set<String> packages) {
        Intrinsics.checkNotNullParameter(packages, "packages");
        this.userGamePackages = CollectionsKt.toMutableSet(packages);
        Log.d(this.TAG, "Game packages updated: " + packages.size() + " games");
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
        Log.w(this.TAG, "Accessibility service interrupted");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        instance = null;
        Log.w(this.TAG, "Accessibility service destroyed");
    }

    public final boolean isAppInForeground(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return Intrinsics.areEqual(currentForegroundPackage, packageName);
    }
}
