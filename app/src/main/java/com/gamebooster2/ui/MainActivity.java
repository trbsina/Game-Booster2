package com.gamebooster2.ui;

import android.app.AppOpsManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.gamebooster2.booster.BoosterEngine;
import com.gamebooster2.monitor.RamInfo;
import com.gamebooster2.monitor.ThermalInfo;
import com.gamebooster2.service.BoosterForegroundService;
import com.gamebooster2.service.GameBoosterAccessibilityService;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 '2\u00020\u0001:\u0002&'B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0002J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0011H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u000fH\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0016J\u0012\u0010\u001e\u001a\u00020\u000f2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\u000fH\u0014J\b\u0010\"\u001a\u00020\u000fH\u0014J\b\u0010#\u001a\u00020\u000fH\u0002J\b\u0010$\u001a\u00020\u000fH\u0002J\b\u0010%\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/gamebooster2/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "engine", "Lcom/gamebooster2/booster/BoosterEngine;", "prefs", "Landroid/content/SharedPreferences;", "sessionGameName", "", "sessionGamePkg", "sessionStartTime", "", "webView", "Landroid/webkit/WebView;", "checkPermissions", "", "getAllUserApps", "Lorg/json/JSONArray;", "getAppName", "pkg", "getInstalledGames", "getKillableApps", "getSavedKillList", "", "Lorg/json/JSONObject;", "getSavedSessions", "isAccessibilityEnabled", "", "loadSavedDataIntoUI", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "pushGamesToUI", "pushSessionsToUI", "recordSessionEnd", "Bridge", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MainActivity extends AppCompatActivity {
    public static final String K_AGGRO = "aggro";
    public static final String K_AUTO = "auto_boost";
    public static final String K_GAMES = "games_list";
    public static final String K_KILL = "kill_list";
    public static final String K_SESSIONS = "sessions_list";
    public static final String PREFS = "GB2Prefs";
    private BoosterEngine engine;
    private SharedPreferences prefs;
    private String sessionGameName = "";
    private String sessionGamePkg = "";
    private long sessionStartTime;
    private WebView webView;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws JSONException {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.prefs = sharedPreferences;
        this.webView = new WebView(this);
        WebView webView = this.webView;
        WebView webView2 = null;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        setContentView(webView);
        WebView webView3 = this.webView;
        if (webView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView3 = null;
        }
        WebSettings $this$onCreate_u24lambda_u240 = webView3.getSettings();
        $this$onCreate_u24lambda_u240.setJavaScriptEnabled(true);
        $this$onCreate_u24lambda_u240.setDomStorageEnabled(true);
        $this$onCreate_u24lambda_u240.setAllowFileAccess(true);
        WebView webView4 = this.webView;
        if (webView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView4 = null;
        }
        webView4.setWebViewClient(new WebViewClient() { // from class: com.gamebooster2.ui.MainActivity.onCreate.2
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                BoosterEngine boosterEngine = MainActivity.this.engine;
                BoosterEngine boosterEngine2 = null;
                if (boosterEngine == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("engine");
                    boosterEngine = null;
                }
                boosterEngine.start();
                BoosterEngine boosterEngine3 = MainActivity.this.engine;
                if (boosterEngine3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("engine");
                } else {
                    boosterEngine2 = boosterEngine3;
                }
                boosterEngine2.getFrameMonitor().start();
                MainActivity.this.loadSavedDataIntoUI();
            }
        });
        WebView webView5 = this.webView;
        if (webView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView5 = null;
        }
        webView5.addJavascriptInterface(new Bridge(), "GameBooster");
        this.engine = new BoosterEngine(this);
        BoosterEngine boosterEngine = this.engine;
        if (boosterEngine == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine = null;
        }
        Iterable $this$map$iv = getSavedKillList();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            JSONObject it = (JSONObject) item$iv$iv;
            String string = it.getString("pkg");
            if (string == null) {
                string = "";
            } else {
                Intrinsics.checkNotNull(string);
            }
            destination$iv$iv.add(string);
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (it2.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        boosterEngine.setUserKillList((List) destination$iv$iv2);
        BoosterEngine boosterEngine2 = this.engine;
        if (boosterEngine2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine2 = null;
        }
        boosterEngine2.setOnCpuUpdate(new AnonymousClass5());
        BoosterEngine boosterEngine3 = this.engine;
        if (boosterEngine3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine3 = null;
        }
        boosterEngine3.setOnRamUpdate(new AnonymousClass6());
        BoosterEngine boosterEngine4 = this.engine;
        if (boosterEngine4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine4 = null;
        }
        boosterEngine4.setOnCpuTempUpdate(new AnonymousClass7());
        BoosterEngine boosterEngine5 = this.engine;
        if (boosterEngine5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine5 = null;
        }
        boosterEngine5.setOnThermalUpdate(new AnonymousClass8());
        BoosterEngine boosterEngine6 = this.engine;
        if (boosterEngine6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine6 = null;
        }
        boosterEngine6.setOnFrameLagUpdate(new AnonymousClass9());
        BoosterEngine boosterEngine7 = this.engine;
        if (boosterEngine7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine7 = null;
        }
        boosterEngine7.setOnPingUpdate(new AnonymousClass10());
        BoosterEngine boosterEngine8 = this.engine;
        if (boosterEngine8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine8 = null;
        }
        boosterEngine8.setOnBoostActivated(new AnonymousClass11());
        BoosterEngine boosterEngine9 = this.engine;
        if (boosterEngine9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine9 = null;
        }
        boosterEngine9.setOnBoostDeactivated(new AnonymousClass12());
        BoosterEngine boosterEngine10 = this.engine;
        if (boosterEngine10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine10 = null;
        }
        boosterEngine10.setOnRamFreed(new AnonymousClass13());
        BoosterEngine boosterEngine11 = this.engine;
        if (boosterEngine11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine11 = null;
        }
        boosterEngine11.setOnThermalCritical(new AnonymousClass14());
        WebView webView6 = this.webView;
        if (webView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            webView2 = webView6;
        }
        webView2.loadUrl("file:///android_asset/ui/dashboard.html");
        startForegroundService(new Intent(this, (Class<?>) BoosterForegroundService.class));
        checkPermissions();
    }

    /* JADX INFO: renamed from: com.gamebooster2.ui.MainActivity$onCreate$5, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "cpu", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass5 extends Lambda implements Function1<Float, Unit> {
        AnonymousClass5() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Float f) {
            invoke(f.floatValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final float cpu) {
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$onCreate$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass5.invoke$lambda$0(mainActivity2, cpu);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MainActivity this$0, float $cpu) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            WebView webView = this$0.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof updateCpu==='function')updateCpu(" + ((int) $cpu) + ')', null);
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.ui.MainActivity$onCreate$6, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "ram", "Lcom/gamebooster2/monitor/RamInfo;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass6 extends Lambda implements Function1<RamInfo, Unit> {
        AnonymousClass6() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(RamInfo ramInfo) {
            invoke2(ramInfo);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final RamInfo ram) {
            Intrinsics.checkNotNullParameter(ram, "ram");
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$onCreate$6$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass6.invoke$lambda$0(mainActivity2, ram);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MainActivity this$0, RamInfo ram) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(ram, "$ram");
            WebView webView = this$0.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof updateRam==='function')updateRam(" + ram.getAvailableMb() + ',' + ram.getTotalMb() + ')', null);
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.ui.MainActivity$onCreate$7, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "temp", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass7 extends Lambda implements Function1<Float, Unit> {
        AnonymousClass7() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Float f) {
            invoke(f.floatValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final float temp) {
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$onCreate$7$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass7.invoke$lambda$0(mainActivity2, temp);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MainActivity this$0, float $temp) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            WebView webView = this$0.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof updateCpuTemp==='function')updateCpuTemp(" + ((int) $temp) + ')', null);
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.ui.MainActivity$onCreate$8, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "t", "Lcom/gamebooster2/monitor/ThermalInfo;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass8 extends Lambda implements Function1<ThermalInfo, Unit> {
        AnonymousClass8() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ThermalInfo thermalInfo) {
            invoke2(thermalInfo);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final ThermalInfo t) {
            Intrinsics.checkNotNullParameter(t, "t");
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$onCreate$8$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass8.invoke$lambda$0(mainActivity2, t);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MainActivity this$0, ThermalInfo t) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(t, "$t");
            WebView webView = this$0.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof updateBatTemp==='function')updateBatTemp(" + ((int) t.getBatteryTempC()) + ",'" + t.getLevel() + "')", null);
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.ui.MainActivity$onCreate$9, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "ms", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass9 extends Lambda implements Function1<Long, Unit> {
        AnonymousClass9() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
            invoke(l.longValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final long ms) {
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$onCreate$9$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass9.invoke$lambda$0(mainActivity2, ms);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MainActivity this$0, long $ms) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            WebView webView = this$0.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof updateFrameLag==='function')updateFrameLag(" + $ms + ')', null);
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.ui.MainActivity$onCreate$10, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "server", "", "ms", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass10 extends Lambda implements Function2<String, Long, Unit> {
        AnonymousClass10() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(String str, Long l) {
            invoke(str, l.longValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final String server, final long ms) {
            Intrinsics.checkNotNullParameter(server, "server");
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$onCreate$10$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass10.invoke$lambda$0(mainActivity2, server, ms);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MainActivity this$0, String server, long $ms) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(server, "$server");
            WebView webView = this$0.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof updatePing==='function')updatePing('" + server + "'," + $ms + ')', null);
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.ui.MainActivity$onCreate$11, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "pkg", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass11 extends Lambda implements Function1<String, Unit> {
        AnonymousClass11() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(String str) {
            invoke2(str);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final String pkg) {
            Intrinsics.checkNotNullParameter(pkg, "pkg");
            MainActivity.this.sessionStartTime = System.currentTimeMillis();
            MainActivity.this.sessionGamePkg = pkg;
            MainActivity.this.sessionGameName = MainActivity.this.getAppName(pkg);
            final String name = StringsKt.replace$default(MainActivity.this.sessionGameName, "'", "\\'", false, 4, (Object) null);
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$onCreate$11$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass11.invoke$lambda$0(mainActivity2, name, pkg);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MainActivity this$0, String name, String pkg) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(name, "$name");
            Intrinsics.checkNotNullParameter(pkg, "$pkg");
            WebView webView = this$0.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof onBoostActivated==='function')onBoostActivated('" + name + "','" + pkg + "')", null);
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.ui.MainActivity$onCreate$12, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass12 extends Lambda implements Function0<Unit> {
        AnonymousClass12() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() throws JSONException {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() throws JSONException {
            MainActivity.this.recordSessionEnd();
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$onCreate$12$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass12.invoke$lambda$0(mainActivity2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MainActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            WebView webView = this$0.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof onBoostDeactivated==='function')onBoostDeactivated()", null);
            this$0.pushSessionsToUI();
            this$0.pushGamesToUI();
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.ui.MainActivity$onCreate$13, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "mb", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass13 extends Lambda implements Function1<Integer, Unit> {
        AnonymousClass13() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke(num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final int mb) {
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$onCreate$13$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass13.invoke$lambda$0(mainActivity2, mb);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MainActivity this$0, int $mb) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            WebView webView = this$0.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof onRamFreed==='function')onRamFreed(" + $mb + ')', null);
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.ui.MainActivity$onCreate$14, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "t", "Lcom/gamebooster2/monitor/ThermalInfo;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass14 extends Lambda implements Function1<ThermalInfo, Unit> {
        AnonymousClass14() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ThermalInfo thermalInfo) {
            invoke2(thermalInfo);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final ThermalInfo t) {
            Intrinsics.checkNotNullParameter(t, "t");
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$onCreate$14$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass14.invoke$lambda$0(mainActivity2, t);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MainActivity this$0, ThermalInfo t) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(t, "$t");
            WebView webView = this$0.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof onThermalCritical==='function')onThermalCritical(" + ((int) t.getCpuTempC()) + ')', null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordSessionEnd() throws JSONException {
        JSONArray gamesArr;
        String str = "[]";
        if (this.sessionStartTime != 0) {
            if (this.sessionGameName.length() == 0) {
                return;
            }
            long now = System.currentTimeMillis();
            long durationMs = now - this.sessionStartTime;
            int durationSec = (int) (durationMs / ((long) 1000));
            int mins = durationSec / 60;
            int secs = durationSec % 60;
            String durationStr = (mins > 0 ? new StringBuilder().append(mins).append("m ") : new StringBuilder()).append(secs).append('s').toString();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
            Calendar $this$recordSessionEnd_u24lambda_u243 = Calendar.getInstance();
            $this$recordSessionEnd_u24lambda_u243.setTimeInMillis(this.sessionStartTime);
            Calendar $this$recordSessionEnd_u24lambda_u244 = Calendar.getInstance();
            $this$recordSessionEnd_u24lambda_u244.setTimeInMillis(now);
            JSONObject $this$recordSessionEnd_u24lambda_u245 = new JSONObject();
            $this$recordSessionEnd_u24lambda_u245.put("gameName", this.sessionGameName);
            $this$recordSessionEnd_u24lambda_u245.put("gamePkg", this.sessionGamePkg);
            $this$recordSessionEnd_u24lambda_u245.put("date", df.format($this$recordSessionEnd_u24lambda_u243.getTime()));
            $this$recordSessionEnd_u24lambda_u245.put("startTime", tf.format($this$recordSessionEnd_u24lambda_u243.getTime()));
            $this$recordSessionEnd_u24lambda_u245.put("endTime", tf.format($this$recordSessionEnd_u24lambda_u244.getTime()));
            $this$recordSessionEnd_u24lambda_u245.put(TypedValues.TransitionType.S_DURATION, durationStr);
            JSONArray sessions = getSavedSessions();
            sessions.put($this$recordSessionEnd_u24lambda_u245);
            SharedPreferences sharedPreferences = this.prefs;
            SharedPreferences sharedPreferences2 = null;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("prefs");
                sharedPreferences = null;
            }
            sharedPreferences.edit().putString(K_SESSIONS, sessions.toString()).apply();
            try {
                SharedPreferences sharedPreferences3 = this.prefs;
                if (sharedPreferences3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("prefs");
                    sharedPreferences3 = null;
                }
                String string = sharedPreferences3.getString(K_GAMES, "[]");
                if (string != null) {
                    str = string;
                }
                JSONArray gamesArr2 = new JSONArray(str);
                int i = 0;
                int length = gamesArr2.length();
                while (true) {
                    if (i >= length) {
                        gamesArr = gamesArr2;
                        break;
                    }
                    JSONObject g = gamesArr2.getJSONObject(i);
                    int i2 = i;
                    gamesArr = gamesArr2;
                    if (!Intrinsics.areEqual(g.optString("pkg"), this.sessionGamePkg)) {
                        i = i2 + 1;
                        gamesArr2 = gamesArr;
                    } else {
                        g.put("sessions", g.optInt("sessions", 0) + 1);
                        break;
                    }
                }
                SharedPreferences sharedPreferences4 = this.prefs;
                if (sharedPreferences4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("prefs");
                } else {
                    sharedPreferences2 = sharedPreferences4;
                }
                sharedPreferences2.edit().putString(K_GAMES, gamesArr.toString()).apply();
            } catch (Exception e) {
            }
            this.sessionStartTime = 0L;
            this.sessionGameName = "";
            this.sessionGamePkg = "";
        }
    }

    private final JSONArray getSavedSessions() {
        String str = "[]";
        try {
            SharedPreferences sharedPreferences = this.prefs;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("prefs");
                sharedPreferences = null;
            }
            String string = sharedPreferences.getString(K_SESSIONS, "[]");
            if (string != null) {
                str = string;
            }
            return new JSONArray(str);
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pushSessionsToUI() {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            sharedPreferences = null;
        }
        String string = sharedPreferences.getString(K_SESSIONS, "[]");
        String sessions = string != null ? string : "[]";
        String escaped = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(sessions, "\\", "\\\\", false, 4, (Object) null), "'", "\\'", false, 4, (Object) null), "\n", "", false, 4, (Object) null);
        WebView webView = this.webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        webView.evaluateJavascript("if(typeof loadSessions==='function')loadSessions('" + escaped + "')", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pushGamesToUI() {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            sharedPreferences = null;
        }
        String string = sharedPreferences.getString(K_GAMES, "[]");
        String games = string != null ? string : "[]";
        String escaped = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(games, "\\", "\\\\", false, 4, (Object) null), "'", "\\'", false, 4, (Object) null), "\n", "", false, 4, (Object) null);
        WebView webView = this.webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        webView.evaluateJavascript("if(typeof loadGames==='function')loadGames('" + escaped + "')", null);
    }

    private final List<JSONObject> getSavedKillList() {
        String str = "[]";
        try {
            SharedPreferences sharedPreferences = this.prefs;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("prefs");
                sharedPreferences = null;
            }
            String string = sharedPreferences.getString(K_KILL, "[]");
            if (string != null) {
                str = string;
            }
            JSONArray a = new JSONArray(str);
            Iterable $this$map$iv = RangesKt.until(0, a.length());
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            Iterator<Integer> it = $this$map$iv.iterator();
            while (it.hasNext()) {
                int item$iv$iv = ((IntIterator) it).nextInt();
                destination$iv$iv.add(a.getJSONObject(item$iv$iv));
            }
            return (List) destination$iv$iv;
        } catch (Exception e) {
            return CollectionsKt.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getAppName(String pkg) {
        try {
            return getPackageManager().getApplicationLabel(getPackageManager().getApplicationInfo(pkg, 0)).toString();
        } catch (Exception e) {
            return StringsKt.substringAfterLast$default(pkg, '.', (String) null, 2, (Object) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadSavedDataIntoUI() {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            sharedPreferences = null;
        }
        String string = sharedPreferences.getString(K_KILL, "[]");
        if (string == null) {
            string = "[]";
        }
        String kl = string;
        SharedPreferences sharedPreferences2 = this.prefs;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            sharedPreferences2 = null;
        }
        String gm = sharedPreferences2.getString(K_GAMES, "[]");
        if (gm == null) {
            gm = "[]";
        }
        SharedPreferences sharedPreferences3 = this.prefs;
        if (sharedPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            sharedPreferences3 = null;
        }
        String string2 = sharedPreferences3.getString(K_SESSIONS, "[]");
        String sessions = string2 != null ? string2 : "[]";
        SharedPreferences sharedPreferences4 = this.prefs;
        if (sharedPreferences4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            sharedPreferences4 = null;
        }
        int ag = sharedPreferences4.getInt(K_AGGRO, 2);
        SharedPreferences sharedPreferences5 = this.prefs;
        if (sharedPreferences5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            sharedPreferences5 = null;
        }
        boolean ab = sharedPreferences5.getBoolean(K_AUTO, true);
        String klEsc = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(kl, "\\", "\\\\", false, 4, (Object) null), "'", "\\'", false, 4, (Object) null), "\n", "", false, 4, (Object) null);
        String gmEsc = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(gm, "\\", "\\\\", false, 4, (Object) null), "'", "\\'", false, 4, (Object) null), "\n", "", false, 4, (Object) null);
        String sessEsc = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(sessions, "\\", "\\\\", false, 4, (Object) null), "'", "\\'", false, 4, (Object) null), "\n", "", false, 4, (Object) null);
        WebView webView = this.webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        webView.evaluateJavascript("if(typeof loadSavedData==='function')loadSavedData('" + klEsc + "','" + gmEsc + "','" + sessEsc + "'," + ag + ',' + ab + ')', null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONArray getKillableApps() {
        final PackageManager pm = getPackageManager();
        JSONArray arr = new JSONArray();
        Set never = SetsKt.setOf((Object[]) new String[]{"android", "com.android.systemui", "com.android.phone", "com.android.settings", "com.google.android.gms", "com.google.android.gsf", "com.android.inputmethod.latin", "com.samsung.android.inputmethod", "com.google.android.inputmethod.latin", getPackageName()});
        try {
            Iterable installedApplications = pm.getInstalledApplications(128);
            Intrinsics.checkNotNullExpressionValue(installedApplications, "getInstalledApplications(...)");
            Iterable $this$filter$iv = installedApplications;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                ApplicationInfo app = (ApplicationInfo) element$iv$iv;
                if (((app.flags & 1) != 0 || never.contains(app.packageName) || pm.getLaunchIntentForPackage(app.packageName) == null) ? false : true) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            Iterable $this$sortedBy$iv = (List) destination$iv$iv;
            Iterable $this$forEach$iv = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.gamebooster2.ui.MainActivity$getKillableApps$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    ApplicationInfo it = (ApplicationInfo) t;
                    String lowerCase = pm.getApplicationLabel(it).toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    ApplicationInfo it2 = (ApplicationInfo) t2;
                    String lowerCase2 = pm.getApplicationLabel(it2).toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                    return ComparisonsKt.compareValues(lowerCase, lowerCase2);
                }
            });
            for (Object element$iv : $this$forEach$iv) {
                ApplicationInfo app2 = (ApplicationInfo) element$iv;
                try {
                    JSONObject $this$getKillableApps_u24lambda_u2410_u24lambda_u249 = new JSONObject();
                    $this$getKillableApps_u24lambda_u2410_u24lambda_u249.put("name", pm.getApplicationLabel(app2).toString());
                    $this$getKillableApps_u24lambda_u2410_u24lambda_u249.put("pkg", app2.packageName);
                    arr.put($this$getKillableApps_u24lambda_u2410_u24lambda_u249);
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
        }
        return arr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONArray getAllUserApps() {
        return getKillableApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONArray getInstalledGames() {
        return getKillableApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isAccessibilityEnabled() {
        String enabled = Settings.Secure.getString(getContentResolver(), "enabled_accessibility_services");
        if (enabled == null) {
            return false;
        }
        String packageName = getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        return StringsKt.contains$default((CharSequence) enabled, (CharSequence) packageName, false, 2, (Object) null);
    }

    private final void checkPermissions() {
        Object systemService = getSystemService("appops");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AppOpsManager");
        AppOpsManager ao = (AppOpsManager) systemService;
        if (ao.checkOpNoThrow("android:get_usage_stats", Process.myUid(), getPackageName()) != 0) {
            Toast.makeText(this, "Please grant Usage Access permission", 1).show();
            startActivity(new Intent("android.settings.USAGE_ACCESS_SETTINGS"));
        }
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "Please grant Display over other apps", 1).show();
            startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())));
        }
        if (!isAccessibilityEnabled()) {
            Toast.makeText(this, "Tip: Enable Accessibility in Settings for best performance!", 1).show();
        }
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\b\u0010\b\u001a\u00020\u0004H\u0007J\b\u0010\t\u001a\u00020\u0006H\u0007J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u000b\u001a\u00020\u0006H\u0007J\b\u0010\f\u001a\u00020\u0006H\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u0004H\u0007J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0012\u001a\u00020\u0004H\u0007J\b\u0010\u0013\u001a\u00020\u0004H\u0007J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0006H\u0007J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0006H\u0007J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0006H\u0007J\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0006H\u0007J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u000eH\u0007J\u0018\u0010!\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¨\u0006\""}, d2 = {"Lcom/gamebooster2/ui/MainActivity$Bridge;", "", "(Lcom/gamebooster2/ui/MainActivity;)V", "addGame", "", "pkg", "", "profile", "boostNow", "getAllUserApps", "getAppIcon", "getInstalledGames", "getKillableApps", "isAccessibilityEnabled", "", "killAllApps", "killApp", "launchApp", "openAccessibilitySettings", "optimizeForGaming", "resetGameSessions", "saveGames", "json", "saveKillList", "saveSessionsJson", "saveSetting", "key", "value", "setAggro", "l", "", "setAutoBoost", "e", "setGameProfile", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class Bridge {
        public Bridge() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void boostNow$lambda$0(MainActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            BoosterEngine boosterEngine = this$0.engine;
            if (boosterEngine == null) {
                Intrinsics.throwUninitializedPropertyAccessException("engine");
                boosterEngine = null;
            }
            boosterEngine.manualBoost();
        }

        @JavascriptInterface
        public final void boostNow() {
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$Bridge$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.Bridge.boostNow$lambda$0(mainActivity2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void killAllApps$lambda$1(MainActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            BoosterEngine boosterEngine = this$0.engine;
            if (boosterEngine == null) {
                Intrinsics.throwUninitializedPropertyAccessException("engine");
                boosterEngine = null;
            }
            boosterEngine.manualKillAll();
        }

        @JavascriptInterface
        public final void killAllApps() {
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$Bridge$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.Bridge.killAllApps$lambda$1(mainActivity2);
                }
            });
        }

        @JavascriptInterface
        public final void killApp(String pkg) {
            Intrinsics.checkNotNullParameter(pkg, "pkg");
            BoosterEngine boosterEngine = MainActivity.this.engine;
            if (boosterEngine == null) {
                Intrinsics.throwUninitializedPropertyAccessException("engine");
                boosterEngine = null;
            }
            boosterEngine.manualKillAll();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void optimizeForGaming$lambda$2(MainActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            BoosterEngine boosterEngine = this$0.engine;
            if (boosterEngine == null) {
                Intrinsics.throwUninitializedPropertyAccessException("engine");
                boosterEngine = null;
            }
            boosterEngine.optimizeForGaming();
        }

        @JavascriptInterface
        public final void optimizeForGaming() {
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$Bridge$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.Bridge.optimizeForGaming$lambda$2(mainActivity2);
                }
            });
        }

        @JavascriptInterface
        public final void launchApp(final String pkg) {
            Intrinsics.checkNotNullParameter(pkg, "pkg");
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$Bridge$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.Bridge.launchApp$lambda$4(mainActivity2, pkg);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void launchApp$lambda$4(final MainActivity this$0, String pkg) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(pkg, "$pkg");
            try {
                final Intent intent = this$0.getPackageManager().getLaunchIntentForPackage(pkg);
                if (intent != null) {
                    BoosterEngine boosterEngine = this$0.engine;
                    if (boosterEngine == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("engine");
                        boosterEngine = null;
                    }
                    boosterEngine.activateBoostForGame(pkg);
                    new Handler(this$0.getMainLooper()).postDelayed(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$Bridge$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            MainActivity.Bridge.launchApp$lambda$4$lambda$3(intent, this$0);
                        }
                    }, 800L);
                    return;
                }
                Toast.makeText(this$0, "Cannot launch app", 0).show();
            } catch (Exception e) {
                Toast.makeText(this$0, "Launch failed", 0).show();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void launchApp$lambda$4$lambda$3(Intent $intent, MainActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            $intent.addFlags(268435456);
            this$0.startActivity($intent);
        }

        @JavascriptInterface
        public final void saveKillList(String json) {
            BoosterEngine boosterEngine;
            Intrinsics.checkNotNullParameter(json, "json");
            SharedPreferences sharedPreferences = MainActivity.this.prefs;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("prefs");
                sharedPreferences = null;
            }
            sharedPreferences.edit().putString(MainActivity.K_KILL, json).apply();
            try {
                JSONArray arr = new JSONArray(json);
                BoosterEngine boosterEngine2 = MainActivity.this.engine;
                if (boosterEngine2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("engine");
                    boosterEngine2 = null;
                }
                Iterable $this$map$iv = RangesKt.until(0, arr.length());
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                Iterator<Integer> it = $this$map$iv.iterator();
                while (it.hasNext()) {
                    int item$iv$iv = ((IntIterator) it).nextInt();
                    destination$iv$iv.add(arr.getJSONObject(item$iv$iv).getString("pkg"));
                }
                Iterable $this$filter$iv = (List) destination$iv$iv;
                Collection destination$iv$iv2 = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    String it2 = (String) element$iv$iv;
                    Intrinsics.checkNotNull(it2);
                    if (it2.length() > 0) {
                        destination$iv$iv2.add(element$iv$iv);
                    }
                }
                boosterEngine2.setUserKillList((List) destination$iv$iv2);
                GameBoosterAccessibilityService companion = GameBoosterAccessibilityService.INSTANCE.getInstance();
                if (companion != null) {
                    BoosterEngine boosterEngine3 = MainActivity.this.engine;
                    if (boosterEngine3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("engine");
                        boosterEngine = null;
                    } else {
                        boosterEngine = boosterEngine3;
                    }
                    companion.updateGamePackages(CollectionsKt.toSet(boosterEngine.getUserKillList()));
                }
            } catch (Exception e) {
            }
        }

        @JavascriptInterface
        public final void saveGames(String json) {
            Intrinsics.checkNotNullParameter(json, "json");
            SharedPreferences sharedPreferences = MainActivity.this.prefs;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("prefs");
                sharedPreferences = null;
            }
            sharedPreferences.edit().putString(MainActivity.K_GAMES, json).apply();
        }

        @JavascriptInterface
        public final void saveSetting(String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            SharedPreferences sharedPreferences = null;
            if (Intrinsics.areEqual(key, MainActivity.K_AGGRO)) {
                SharedPreferences sharedPreferences2 = MainActivity.this.prefs;
                if (sharedPreferences2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("prefs");
                } else {
                    sharedPreferences = sharedPreferences2;
                }
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                Integer intOrNull = StringsKt.toIntOrNull(value);
                editorEdit.putInt(MainActivity.K_AGGRO, intOrNull != null ? intOrNull.intValue() : 2).apply();
                return;
            }
            if (Intrinsics.areEqual(key, "autoBoost")) {
                SharedPreferences sharedPreferences3 = MainActivity.this.prefs;
                if (sharedPreferences3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("prefs");
                } else {
                    sharedPreferences = sharedPreferences3;
                }
                sharedPreferences.edit().putBoolean(MainActivity.K_AUTO, Intrinsics.areEqual(value, "true")).apply();
            }
        }

        @JavascriptInterface
        public final void saveSessionsJson(String json) {
            Intrinsics.checkNotNullParameter(json, "json");
            SharedPreferences sharedPreferences = MainActivity.this.prefs;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("prefs");
                sharedPreferences = null;
            }
            sharedPreferences.edit().putString(MainActivity.K_SESSIONS, json).apply();
        }

        @JavascriptInterface
        public final void resetGameSessions(String pkg) {
            String str = "[]";
            Intrinsics.checkNotNullParameter(pkg, "pkg");
            try {
                SharedPreferences sharedPreferences = MainActivity.this.prefs;
                SharedPreferences sharedPreferences2 = null;
                if (sharedPreferences == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("prefs");
                    sharedPreferences = null;
                }
                String string = sharedPreferences.getString(MainActivity.K_GAMES, "[]");
                if (string != null) {
                    str = string;
                }
                JSONArray arr = new JSONArray(str);
                int length = arr.length();
                for (int i = 0; i < length; i++) {
                    JSONObject g = arr.getJSONObject(i);
                    if (Intrinsics.areEqual(g.optString("pkg"), pkg)) {
                        g.put("sessions", 0);
                        break;
                    }
                }
                SharedPreferences sharedPreferences3 = MainActivity.this.prefs;
                if (sharedPreferences3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("prefs");
                } else {
                    sharedPreferences2 = sharedPreferences3;
                }
                sharedPreferences2.edit().putString(MainActivity.K_GAMES, arr.toString()).apply();
            } catch (Exception e) {
            }
        }

        @JavascriptInterface
        public final String getAppIcon(String pkg) {
            Bitmap bmp;
            Intrinsics.checkNotNullParameter(pkg, "pkg");
            try {
                Drawable drawable = MainActivity.this.getPackageManager().getApplicationIcon(pkg);
                Intrinsics.checkNotNullExpressionValue(drawable, "getApplicationIcon(...)");
                if (drawable instanceof BitmapDrawable) {
                    bmp = ((BitmapDrawable) drawable).getBitmap();
                } else {
                    Integer numValueOf = Integer.valueOf(drawable.getIntrinsicWidth());
                    int it = numValueOf.intValue();
                    int it2 = it > 0 ? 1 : 0;
                    if (it2 == 0) {
                        numValueOf = null;
                    }
                    int iIntValue = numValueOf != null ? numValueOf.intValue() : 48;
                    Integer numValueOf2 = Integer.valueOf(drawable.getIntrinsicHeight());
                    int it3 = numValueOf2.intValue();
                    int it4 = it3 > 0 ? 1 : 0;
                    Integer num = it4 != 0 ? numValueOf2 : null;
                    bmp = Bitmap.createBitmap(iIntValue, num != null ? num.intValue() : 48, Bitmap.Config.ARGB_8888);
                    Intrinsics.checkNotNullExpressionValue(bmp, "createBitmap(...)");
                    Canvas canvas = new Canvas(bmp);
                    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    drawable.draw(canvas);
                }
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap scaled = Bitmap.createScaledBitmap(bmp, 96, 96, true);
                Intrinsics.checkNotNullExpressionValue(scaled, "createScaledBitmap(...)");
                scaled.compress(Bitmap.CompressFormat.PNG, 90, stream);
                String strEncodeToString = Base64.encodeToString(stream.toByteArray(), 2);
                Intrinsics.checkNotNull(strEncodeToString);
                return strEncodeToString;
            } catch (Exception e) {
                return "";
            }
        }

        @JavascriptInterface
        public final void setAutoBoost(boolean e) {
            SharedPreferences sharedPreferences = MainActivity.this.prefs;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("prefs");
                sharedPreferences = null;
            }
            sharedPreferences.edit().putBoolean(MainActivity.K_AUTO, e).apply();
        }

        @JavascriptInterface
        public final void setAggro(int l) {
            SharedPreferences sharedPreferences = MainActivity.this.prefs;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("prefs");
                sharedPreferences = null;
            }
            sharedPreferences.edit().putInt(MainActivity.K_AGGRO, l).apply();
        }

        @JavascriptInterface
        public final void addGame(String pkg, String profile) {
            Intrinsics.checkNotNullParameter(pkg, "pkg");
            Intrinsics.checkNotNullParameter(profile, "profile");
        }

        @JavascriptInterface
        public final void setGameProfile(String pkg, String profile) {
            Intrinsics.checkNotNullParameter(pkg, "pkg");
            Intrinsics.checkNotNullParameter(profile, "profile");
        }

        @JavascriptInterface
        public final String getInstalledGames() {
            String string = MainActivity.this.getInstalledGames().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }

        @JavascriptInterface
        public final String getKillableApps() {
            String string = MainActivity.this.getKillableApps().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }

        @JavascriptInterface
        public final String getAllUserApps() {
            String string = MainActivity.this.getAllUserApps().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }

        @JavascriptInterface
        public final boolean isAccessibilityEnabled() {
            return MainActivity.this.isAccessibilityEnabled();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void openAccessibilitySettings$lambda$9(MainActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
        }

        @JavascriptInterface
        public final void openAccessibilitySettings() {
            MainActivity mainActivity = MainActivity.this;
            final MainActivity mainActivity2 = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.gamebooster2.ui.MainActivity$Bridge$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.Bridge.openAccessibilitySettings$lambda$9(mainActivity2);
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() throws JSONException {
        super.onResume();
        if (this.sessionStartTime > 0) {
            recordSessionEnd();
            WebView webView = this.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript("if(typeof onBoostDeactivated==='function')onBoostDeactivated()", null);
            pushSessionsToUI();
            pushGamesToUI();
        }
        WebView webView2 = this.webView;
        if (webView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView2 = null;
        }
        webView2.evaluateJavascript("if(typeof checkAccessibilityStatus==='function')checkAccessibilityStatus()", null);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() throws JSONException {
        super.onDestroy();
        if (this.sessionStartTime > 0) {
            recordSessionEnd();
        }
        BoosterEngine boosterEngine = this.engine;
        if (boosterEngine == null) {
            Intrinsics.throwUninitializedPropertyAccessException("engine");
            boosterEngine = null;
        }
        boosterEngine.stop();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        WebView webView = this.webView;
        WebView webView2 = null;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        if (!webView.canGoBack()) {
            super.onBackPressed();
            return;
        }
        WebView webView3 = this.webView;
        if (webView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            webView2 = webView3;
        }
        webView2.goBack();
    }
}
