package com.gamebooster2.booster;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ContentResolver;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.provider.Settings;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.location.LocationRequestCompat;
import com.gamebooster2.monitor.FrameMonitor;
import com.gamebooster2.monitor.RamInfo;
import com.gamebooster2.monitor.RamMonitor;
import com.gamebooster2.monitor.ThermalInfo;
import com.gamebooster2.monitor.ThermalMonitor;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: compiled from: BoosterEngine.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010S\u001a\u00020\u00142\u0006\u0010T\u001a\u00020\u0006J\u000e\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00060VH\u0002J\b\u0010W\u001a\u00020\u0014H\u0002J\u0010\u0010X\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020'H\u0002J\u0006\u0010Z\u001a\u00020\u0014J\u0006\u0010[\u001a\u00020\u0014J\u0010\u0010\\\u001a\u00020'2\u0006\u0010]\u001a\u00020\u0006H\u0002J\u0006\u0010^\u001a\u00020\u0014J\b\u0010_\u001a\u000201H\u0002J\b\u0010`\u001a\u00020\u0014H\u0002J\u0006\u0010a\u001a\u00020\u0014J\b\u0010b\u001a\u00020\u0014H\u0002J\u0010\u0010c\u001a\u00020\u00142\u0006\u0010T\u001a\u00020\u0006H\u0002J\u0006\u0010d\u001a\u00020\u0014J\u0006\u0010e\u001a\u00020\u0014J\b\u0010f\u001a\u00020\u0014H\u0002J\u0016\u0010g\u001a\u00020\u00142\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00140\u001aH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR(\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R(\u0010#\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0016\"\u0004\b%\u0010\u0018R(\u0010&\u001a\u0010\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0016\"\u0004\b)\u0010\u0018R.\u0010*\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u0014\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R(\u00100\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0016\"\u0004\b3\u0010\u0018R(\u00104\u001a\u0010\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0016\"\u0004\b7\u0010\u0018R(\u00108\u001a\u0010\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0016\"\u0004\b;\u0010\u0018R(\u0010<\u001a\u0010\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0016\"\u0004\b>\u0010\u0018R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010A\u001a\u00020B¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0010\u0010E\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010F\u001a\u00020G¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR \u0010J\u001a\b\u0012\u0004\u0012\u00020\u00060KX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u0014\u0010P\u001a\b\u0018\u00010QR\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006i"}, d2 = {"Lcom/gamebooster2/booster/BoosterEngine;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "activeGamePackage", "am", "Landroid/app/ActivityManager;", "engineScope", "Lkotlinx/coroutines/CoroutineScope;", "frameMonitor", "Lcom/gamebooster2/monitor/FrameMonitor;", "getFrameMonitor", "()Lcom/gamebooster2/monitor/FrameMonitor;", "isBoostActive", "", "onBoostActivated", "Lkotlin/Function1;", "", "getOnBoostActivated", "()Lkotlin/jvm/functions/Function1;", "setOnBoostActivated", "(Lkotlin/jvm/functions/Function1;)V", "onBoostDeactivated", "Lkotlin/Function0;", "getOnBoostDeactivated", "()Lkotlin/jvm/functions/Function0;", "setOnBoostDeactivated", "(Lkotlin/jvm/functions/Function0;)V", "onCpuTempUpdate", "", "getOnCpuTempUpdate", "setOnCpuTempUpdate", "onCpuUpdate", "getOnCpuUpdate", "setOnCpuUpdate", "onFrameLagUpdate", "", "getOnFrameLagUpdate", "setOnFrameLagUpdate", "onPingUpdate", "Lkotlin/Function2;", "getOnPingUpdate", "()Lkotlin/jvm/functions/Function2;", "setOnPingUpdate", "(Lkotlin/jvm/functions/Function2;)V", "onRamFreed", "", "getOnRamFreed", "setOnRamFreed", "onRamUpdate", "Lcom/gamebooster2/monitor/RamInfo;", "getOnRamUpdate", "setOnRamUpdate", "onThermalCritical", "Lcom/gamebooster2/monitor/ThermalInfo;", "getOnThermalCritical", "setOnThermalCritical", "onThermalUpdate", "getOnThermalUpdate", "setOnThermalUpdate", "pingJob", "Lkotlinx/coroutines/Job;", "ramMonitor", "Lcom/gamebooster2/monitor/RamMonitor;", "getRamMonitor", "()Lcom/gamebooster2/monitor/RamMonitor;", "reKillJob", "thermalMonitor", "Lcom/gamebooster2/monitor/ThermalMonitor;", "getThermalMonitor", "()Lcom/gamebooster2/monitor/ThermalMonitor;", "userKillList", "", "getUserKillList", "()Ljava/util/List;", "setUserKillList", "(Ljava/util/List;)V", "wifiLock", "Landroid/net/wifi/WifiManager$WifiLock;", "Landroid/net/wifi/WifiManager;", "activateBoostForGame", "gamePackage", "buildKillList", "", "deactivateBoost", "handleFrameLag", "frameMs", "manualBoost", "manualKillAll", "measurePing", "host", "optimizeForGaming", "performKill", "releaseNetworkOptimizations", "start", "startPingMonitor", "startReKillLoop", "stop", "stopPingMonitor", "stopReKillLoop", "withMain", "block", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BoosterEngine {
    private final String TAG;
    private String activeGamePackage;
    private final ActivityManager am;
    private final Context context;
    private CoroutineScope engineScope;
    private final FrameMonitor frameMonitor;
    private boolean isBoostActive;
    private Function1<? super String, Unit> onBoostActivated;
    private Function0<Unit> onBoostDeactivated;
    private Function1<? super Float, Unit> onCpuTempUpdate;
    private Function1<? super Float, Unit> onCpuUpdate;
    private Function1<? super Long, Unit> onFrameLagUpdate;
    private Function2<? super String, ? super Long, Unit> onPingUpdate;
    private Function1<? super Integer, Unit> onRamFreed;
    private Function1<? super RamInfo, Unit> onRamUpdate;
    private Function1<? super ThermalInfo, Unit> onThermalCritical;
    private Function1<? super ThermalInfo, Unit> onThermalUpdate;
    private Job pingJob;
    private final RamMonitor ramMonitor;
    private Job reKillJob;
    private final ThermalMonitor thermalMonitor;
    private List<String> userKillList;
    private WifiManager.WifiLock wifiLock;

    public BoosterEngine(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "BoosterEngine";
        this.ramMonitor = new RamMonitor(this.context);
        this.thermalMonitor = new ThermalMonitor(this.context);
        this.frameMonitor = new FrameMonitor(new Function1<Long, Unit>() { // from class: com.gamebooster2.booster.BoosterEngine$frameMonitor$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long frameMs) {
                this.this$0.handleFrameLag(frameMs);
            }
        });
        Object systemService = this.context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        this.am = (ActivityManager) systemService;
        this.engineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        this.activeGamePackage = "";
        this.userKillList = CollectionsKt.emptyList();
    }

    public final RamMonitor getRamMonitor() {
        return this.ramMonitor;
    }

    public final ThermalMonitor getThermalMonitor() {
        return this.thermalMonitor;
    }

    public final FrameMonitor getFrameMonitor() {
        return this.frameMonitor;
    }

    public final List<String> getUserKillList() {
        return this.userKillList;
    }

    public final void setUserKillList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.userKillList = list;
    }

    public final Function1<Float, Unit> getOnCpuUpdate() {
        return this.onCpuUpdate;
    }

    public final void setOnCpuUpdate(Function1<? super Float, Unit> function1) {
        this.onCpuUpdate = function1;
    }

    public final Function1<RamInfo, Unit> getOnRamUpdate() {
        return this.onRamUpdate;
    }

    public final void setOnRamUpdate(Function1<? super RamInfo, Unit> function1) {
        this.onRamUpdate = function1;
    }

    public final Function1<Float, Unit> getOnCpuTempUpdate() {
        return this.onCpuTempUpdate;
    }

    public final void setOnCpuTempUpdate(Function1<? super Float, Unit> function1) {
        this.onCpuTempUpdate = function1;
    }

    public final Function1<ThermalInfo, Unit> getOnThermalUpdate() {
        return this.onThermalUpdate;
    }

    public final void setOnThermalUpdate(Function1<? super ThermalInfo, Unit> function1) {
        this.onThermalUpdate = function1;
    }

    public final Function1<ThermalInfo, Unit> getOnThermalCritical() {
        return this.onThermalCritical;
    }

    public final void setOnThermalCritical(Function1<? super ThermalInfo, Unit> function1) {
        this.onThermalCritical = function1;
    }

    public final Function1<String, Unit> getOnBoostActivated() {
        return this.onBoostActivated;
    }

    public final void setOnBoostActivated(Function1<? super String, Unit> function1) {
        this.onBoostActivated = function1;
    }

    public final Function0<Unit> getOnBoostDeactivated() {
        return this.onBoostDeactivated;
    }

    public final void setOnBoostDeactivated(Function0<Unit> function0) {
        this.onBoostDeactivated = function0;
    }

    public final Function1<Integer, Unit> getOnRamFreed() {
        return this.onRamFreed;
    }

    public final void setOnRamFreed(Function1<? super Integer, Unit> function1) {
        this.onRamFreed = function1;
    }

    public final Function1<Long, Unit> getOnFrameLagUpdate() {
        return this.onFrameLagUpdate;
    }

    public final void setOnFrameLagUpdate(Function1<? super Long, Unit> function1) {
        this.onFrameLagUpdate = function1;
    }

    public final Function2<String, Long, Unit> getOnPingUpdate() {
        return this.onPingUpdate;
    }

    public final void setOnPingUpdate(Function2<? super String, ? super Long, Unit> function2) {
        this.onPingUpdate = function2;
    }

    public final void start() {
        Log.d(this.TAG, "BoosterEngine starting");
        this.ramMonitor.start(this.engineScope);
        this.thermalMonitor.start(this.engineScope, new Function1<ThermalInfo, Unit>() { // from class: com.gamebooster2.booster.BoosterEngine.start.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ThermalInfo thermalInfo) {
                invoke2(thermalInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ThermalInfo info) {
                Intrinsics.checkNotNullParameter(info, "info");
                Function1<ThermalInfo, Unit> onThermalCritical = BoosterEngine.this.getOnThermalCritical();
                if (onThermalCritical != null) {
                    onThermalCritical.invoke(info);
                }
                if (BoosterEngine.this.isBoostActive) {
                    BoosterEngine.this.deactivateBoost();
                }
            }
        });
        BuildersKt__Builders_commonKt.launch$default(this.engineScope, null, null, new C00582(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.engineScope, null, null, new AnonymousClass3(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.engineScope, null, null, new AnonymousClass4(null), 3, null);
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$start$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$start$2", f = "BoosterEngine.kt", i = {}, l = {LockFreeTaskQueueCore.FROZEN_SHIFT}, m = "invokeSuspend", n = {}, s = {})
    static final class C00582 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00582(Continuation<? super C00582> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BoosterEngine.this.new C00582(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00582) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$start$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: BoosterEngine.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "info", "Lcom/gamebooster2/monitor/RamInfo;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$start$2$1", f = "BoosterEngine.kt", i = {0}, l = {LockFreeTaskQueueCore.CLOSED_SHIFT}, m = "invokeSuspend", n = {"info"}, s = {"L$0"})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<RamInfo, Continuation<? super Unit>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ BoosterEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(BoosterEngine boosterEngine, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = boosterEngine;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(RamInfo ramInfo, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(ramInfo, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$start$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: BoosterEngine.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$start$2$1$1", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class C00141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ RamInfo $info;
                int label;
                final /* synthetic */ BoosterEngine this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00141(BoosterEngine boosterEngine, RamInfo ramInfo, Continuation<? super C00141> continuation) {
                    super(2, continuation);
                    this.this$0 = boosterEngine;
                    this.$info = ramInfo;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00141(this.this$0, this.$info, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            Function1<RamInfo, Unit> onRamUpdate = this.this$0.getOnRamUpdate();
                            if (onRamUpdate == null) {
                                return null;
                            }
                            onRamUpdate.invoke(this.$info);
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                AnonymousClass1 anonymousClass1;
                RamInfo info;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        RamInfo info2 = (RamInfo) this.L$0;
                        this.L$0 = info2;
                        this.label = 1;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new C00141(this.this$0, info2, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        anonymousClass1 = this;
                        info = info2;
                        break;
                        break;
                    case 1:
                        anonymousClass1 = this;
                        info = (RamInfo) anonymousClass1.L$0;
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                if (anonymousClass1.this$0.isBoostActive && info.getAvailableMb() < 200) {
                    Log.w(anonymousClass1.this$0.TAG, "RAM critical: " + info.getAvailableMb() + "MB — re-killing");
                    anonymousClass1.this$0.performKill();
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (FlowKt.collectLatest(BoosterEngine.this.getRamMonitor().getRamInfo(), new AnonymousClass1(BoosterEngine.this, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$start$3, reason: invalid class name */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$start$3", f = "BoosterEngine.kt", i = {}, l = {70}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BoosterEngine.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$start$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: BoosterEngine.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "info", "Lcom/gamebooster2/monitor/ThermalInfo;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$start$3$1", f = "BoosterEngine.kt", i = {}, l = {71}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<ThermalInfo, Continuation<? super Unit>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ BoosterEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(BoosterEngine boosterEngine, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = boosterEngine;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(ThermalInfo thermalInfo, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(thermalInfo, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$start$3$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: BoosterEngine.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$start$3$1$1", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class C00151 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ ThermalInfo $info;
                int label;
                final /* synthetic */ BoosterEngine this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00151(BoosterEngine boosterEngine, ThermalInfo thermalInfo, Continuation<? super C00151> continuation) {
                    super(2, continuation);
                    this.this$0 = boosterEngine;
                    this.$info = thermalInfo;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00151(this.this$0, this.$info, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00151) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            Function1<Float, Unit> onCpuTempUpdate = this.this$0.getOnCpuTempUpdate();
                            if (onCpuTempUpdate != null) {
                                onCpuTempUpdate.invoke(Boxing.boxFloat(this.$info.getCpuTempC()));
                            }
                            Function1<ThermalInfo, Unit> onThermalUpdate = this.this$0.getOnThermalUpdate();
                            if (onThermalUpdate != null) {
                                onThermalUpdate.invoke(this.$info);
                            }
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        ThermalInfo info = (ThermalInfo) this.L$0;
                        this.label = 1;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new C00151(this.this$0, info, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (FlowKt.collectLatest(BoosterEngine.this.getThermalMonitor().getThermalInfo(), new AnonymousClass1(BoosterEngine.this, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$start$4, reason: invalid class name */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$start$4", f = "BoosterEngine.kt", i = {}, l = {79}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BoosterEngine.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$start$4$1, reason: invalid class name */
        /* JADX INFO: compiled from: BoosterEngine.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "ms", ""}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$start$4$1", f = "BoosterEngine.kt", i = {0}, l = {81, 83}, m = "invokeSuspend", n = {"ms"}, s = {"J$0"})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<Long, Continuation<? super Unit>, Object> {
            /* synthetic */ long J$0;
            int label;
            final /* synthetic */ BoosterEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(BoosterEngine boosterEngine, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = boosterEngine;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.J$0 = ((Number) obj).longValue();
                return anonymousClass1;
            }

            public final Object invoke(long j, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(Long.valueOf(j), continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Long l, Continuation<? super Unit> continuation) {
                return invoke(l.longValue(), continuation);
            }

            /* JADX WARN: Code duplicated, block: B:15:0x0072 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:16:0x0073  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                AnonymousClass1 anonymousClass1;
                long ms;
                float estimatedCpu;
                AnonymousClass1 anonymousClass2;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        anonymousClass1 = this;
                        ms = anonymousClass1.J$0;
                        if (ms > 0) {
                            anonymousClass1.J$0 = ms;
                            anonymousClass1.label = 1;
                            if (BuildersKt.withContext(Dispatchers.getMain(), new C00161(anonymousClass1.this$0, ms, null), anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            estimatedCpu = RangesKt.coerceIn(((ms - 16.0f) / 32.0f) * 100.0f, 0.0f, 100.0f);
                            anonymousClass1.label = 2;
                            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(anonymousClass1.this$0, estimatedCpu, null), anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass2 = anonymousClass1;
                        }
                        return Unit.INSTANCE;
                    case 1:
                        anonymousClass1 = this;
                        ms = anonymousClass1.J$0;
                        ResultKt.throwOnFailure($result);
                        estimatedCpu = RangesKt.coerceIn(((ms - 16.0f) / 32.0f) * 100.0f, 0.0f, 100.0f);
                        anonymousClass1.label = 2;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(anonymousClass1.this$0, estimatedCpu, null), anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        anonymousClass2 = anonymousClass1;
                        return Unit.INSTANCE;
                    case 2:
                        anonymousClass2 = this;
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$start$4$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: BoosterEngine.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$start$4$1$1", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class C00161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ long $ms;
                int label;
                final /* synthetic */ BoosterEngine this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00161(BoosterEngine boosterEngine, long j, Continuation<? super C00161> continuation) {
                    super(2, continuation);
                    this.this$0 = boosterEngine;
                    this.$ms = j;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00161(this.this$0, this.$ms, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            Function1<Long, Unit> onFrameLagUpdate = this.this$0.getOnFrameLagUpdate();
                            if (onFrameLagUpdate == null) {
                                return null;
                            }
                            onFrameLagUpdate.invoke(Boxing.boxLong(this.$ms));
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$start$4$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: BoosterEngine.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$start$4$1$2", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ float $estimatedCpu;
                int label;
                final /* synthetic */ BoosterEngine this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass2(BoosterEngine boosterEngine, float f, Continuation<? super AnonymousClass2> continuation) {
                    super(2, continuation);
                    this.this$0 = boosterEngine;
                    this.$estimatedCpu = f;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass2(this.this$0, this.$estimatedCpu, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            Function1<Float, Unit> onCpuUpdate = this.this$0.getOnCpuUpdate();
                            if (onCpuUpdate == null) {
                                return null;
                            }
                            onCpuUpdate.invoke(Boxing.boxFloat(this.$estimatedCpu));
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (FlowKt.collectLatest(BoosterEngine.this.getFrameMonitor().getFrameMs(), new AnonymousClass1(BoosterEngine.this, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void stop() {
        stopReKillLoop();
        stopPingMonitor();
        this.frameMonitor.stop();
        CoroutineScopeKt.cancel$default(this.engineScope, null, 1, null);
        this.ramMonitor.stop();
        this.thermalMonitor.stop();
        this.engineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFrameLag(long frameMs) {
        if (this.isBoostActive) {
            Log.w(this.TAG, "Frame lag: " + frameMs + "ms — boosting!");
            BuildersKt__Builders_commonKt.launch$default(this.engineScope, Dispatchers.getIO(), null, new C00531(null), 2, null);
        }
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$handleFrameLag$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$handleFrameLag$1", f = "BoosterEngine.kt", i = {}, l = {LocationRequestCompat.QUALITY_LOW_POWER}, m = "invokeSuspend", n = {}, s = {})
    static final class C00531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00531(Continuation<? super C00531> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BoosterEngine.this.new C00531(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    int freed = BoosterEngine.this.performKill();
                    this.label = 1;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new C00111(freed, BoosterEngine.this, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$handleFrameLag$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BoosterEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$handleFrameLag$1$1", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00111 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $freed;
            int label;
            final /* synthetic */ BoosterEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00111(int i, BoosterEngine boosterEngine, Continuation<? super C00111> continuation) {
                super(2, continuation);
                this.$freed = i;
                this.this$0 = boosterEngine;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00111(this.$freed, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00111) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Function1<Integer, Unit> onRamFreed;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        if (this.$freed > 0 && (onRamFreed = this.this$0.getOnRamFreed()) != null) {
                            onRamFreed.invoke(Boxing.boxInt(this.$freed));
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int performKill() {
        int killed = 0;
        for (String pkg : buildKillList()) {
            try {
                this.am.killBackgroundProcesses(pkg);
                killed++;
                Log.d(this.TAG, "Killed: " + pkg);
            } catch (Exception e) {
            }
        }
        this.ramMonitor.trimMemory();
        int mb = killed * 40;
        Log.d(this.TAG, "Killed " + killed + " apps, ~" + mb + "MB freed");
        return mb;
    }

    private final Set<String> buildKillList() {
        Set neverKill = SetsKt.setOf((Object[]) new String[]{"android", "com.android.systemui", "com.android.phone", "com.google.android.gms", "com.google.android.gsf", "com.android.inputmethod.latin", "com.samsung.android.inputmethod", "com.google.android.inputmethod.latin", this.activeGamePackage, this.context.getPackageName()});
        Iterable $this$filter$iv = this.userKillList;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (it.length() > 0) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return SetsKt.minus(SetsKt.plus(CollectionsKt.toSet((List) destination$iv$iv), (Iterable) SmartKillList.INSTANCE.getTargets()), (Iterable) neverKill);
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$optimizeForGaming$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$optimizeForGaming$1", f = "BoosterEngine.kt", i = {}, l = {247}, m = "invokeSuspend", n = {}, s = {})
    static final class C00561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00561(Continuation<? super C00561> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BoosterEngine.this.new C00561(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            C00561 c00561;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Log.d(BoosterEngine.this.TAG, "Optimizing for gaming...");
                    int freed = BoosterEngine.this.performKill();
                    try {
                        Object systemService = BoosterEngine.this.context.getSystemService("notification");
                        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                        NotificationManager nm = (NotificationManager) systemService;
                        if (nm.isNotificationPolicyAccessGranted()) {
                            nm.setInterruptionFilter(3);
                            Log.d(BoosterEngine.this.TAG, "DND enabled");
                        }
                    } catch (Exception e) {
                        Log.w(BoosterEngine.this.TAG, "DND failed: " + e.getMessage());
                    }
                    boolean z = false;
                    try {
                        ContentResolver.setMasterSyncAutomatically(false);
                        Log.d(BoosterEngine.this.TAG, "Auto sync disabled");
                    } catch (Exception e2) {
                        Log.w(BoosterEngine.this.TAG, "Sync disable failed: " + e2.getMessage());
                    }
                    try {
                        Object systemService2 = BoosterEngine.this.context.getApplicationContext().getSystemService("wifi");
                        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.net.wifi.WifiManager");
                        WifiManager wifiManager = (WifiManager) systemService2;
                        WifiManager.WifiLock wifiLock = BoosterEngine.this.wifiLock;
                        if (wifiLock != null) {
                            wifiLock.release();
                        }
                        BoosterEngine.this.wifiLock = wifiManager.createWifiLock(3, "GameBooster_WiFiLock");
                        WifiManager.WifiLock wifiLock2 = BoosterEngine.this.wifiLock;
                        if (wifiLock2 != null) {
                            wifiLock2.setReferenceCounted(false);
                        }
                        WifiManager.WifiLock wifiLock3 = BoosterEngine.this.wifiLock;
                        if (wifiLock3 != null) {
                            wifiLock3.acquire();
                        }
                        Log.d(BoosterEngine.this.TAG, "WiFi high performance lock acquired");
                    } catch (Exception e3) {
                        Log.w(BoosterEngine.this.TAG, "WiFi lock failed: " + e3.getMessage());
                    }
                    try {
                        Object systemService3 = BoosterEngine.this.context.getApplicationContext().getSystemService("wifi");
                        Intrinsics.checkNotNull(systemService3, "null cannot be cast to non-null type android.net.wifi.WifiManager");
                        WifiManager wm = (WifiManager) systemService3;
                        boolean it = wm.isScanAlwaysAvailable();
                        String str = BoosterEngine.this.TAG;
                        StringBuilder sbAppend = new StringBuilder().append("WiFi scan always available: ");
                        if (it) {
                            z = true;
                        }
                        Log.d(str, sbAppend.append(z).toString());
                        Log.d(BoosterEngine.this.TAG, "WiFi scanning stopped");
                    } catch (Exception e4) {
                        Log.w(BoosterEngine.this.TAG, "WiFi scan stop failed: " + e4.getMessage());
                    }
                    try {
                        Object systemService4 = BoosterEngine.this.context.getSystemService("bluetooth");
                        Intrinsics.checkNotNull(systemService4, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
                        BluetoothManager bm = (BluetoothManager) systemService4;
                        BluetoothAdapter bt = bm.getAdapter();
                        if (bt == null || !bt.isDiscovering()) {
                            Log.d(BoosterEngine.this.TAG, "Bluetooth not discovering — nothing to cancel");
                        } else {
                            bt.cancelDiscovery();
                            Log.d(BoosterEngine.this.TAG, "Bluetooth discovery cancelled");
                        }
                    } catch (Exception e5) {
                        Log.w(BoosterEngine.this.TAG, "BT cancel failed: " + e5.getMessage());
                    }
                    try {
                        Object systemService5 = BoosterEngine.this.context.getSystemService("location");
                        Intrinsics.checkNotNull(systemService5, "null cannot be cast to non-null type android.location.LocationManager");
                        Log.d(BoosterEngine.this.TAG, "Location scan optimized");
                    } catch (Exception e6) {
                        Log.w(BoosterEngine.this.TAG, "Location stop failed: " + e6.getMessage());
                    }
                    try {
                        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(BoosterEngine.this.context);
                        if (nfc != null && nfc.isEnabled()) {
                            Context context = BoosterEngine.this.context;
                            Activity activity = context instanceof Activity ? (Activity) context : null;
                            if (activity != null) {
                                nfc.disableReaderMode(activity);
                                Log.d(BoosterEngine.this.TAG, "NFC reader mode disabled");
                            } else {
                                return Unit.INSTANCE;
                            }
                        }
                    } catch (Exception e7) {
                        Log.w(BoosterEngine.this.TAG, "NFC disable failed (needs Activity context): " + e7.getMessage());
                    }
                    try {
                        Settings.Global.putString(BoosterEngine.this.context.getContentResolver(), "private_dns_mode", "hostname");
                        Settings.Global.putString(BoosterEngine.this.context.getContentResolver(), "private_dns_specifier", "1dot1dot1dot1.cloudflare-dns.com");
                        Log.d(BoosterEngine.this.TAG, "DNS set to Cloudflare 1.1.1.1");
                    } catch (Exception e8) {
                        Log.w(BoosterEngine.this.TAG, "DNS change needs WRITE_SECURE_SETTINGS: " + e8.getMessage());
                    }
                    this.label = 1;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(freed, BoosterEngine.this, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    c00561 = this;
                    break;
                    break;
                case 1:
                    c00561 = this;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            BoosterEngine.this.startPingMonitor();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$optimizeForGaming$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: BoosterEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$optimizeForGaming$1$2", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $freed;
            int label;
            final /* synthetic */ BoosterEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(int i, BoosterEngine boosterEngine, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$freed = i;
                this.this$0 = boosterEngine;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$freed, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Function1<Integer, Unit> onRamFreed;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        if (this.$freed > 0 && (onRamFreed = this.this$0.getOnRamFreed()) != null) {
                            onRamFreed.invoke(Boxing.boxInt(this.$freed));
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final void optimizeForGaming() {
        BuildersKt__Builders_commonKt.launch$default(this.engineScope, Dispatchers.getIO(), null, new C00561(null), 2, null);
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$startPingMonitor$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$startPingMonitor$1", f = "BoosterEngine.kt", i = {0, 1}, l = {267, 270}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"})
    static final class C00591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C00591(Continuation<? super C00591> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00591 c00591 = BoosterEngine.this.new C00591(continuation);
            c00591.L$0 = obj;
            return c00591;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x003c  */
        /* JADX WARN: Code duplicated, block: B:13:0x0062 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:16:0x0073 A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0071 -> B:9:0x0036). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                switch(r1) {
                    case 0: goto L23;
                    case 1: goto L1a;
                    case 2: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L11:
                r1 = r9
                java.lang.Object r2 = r1.L$0
                kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
                kotlin.ResultKt.throwOnFailure(r10)
                goto L74
            L1a:
                r1 = r9
                java.lang.Object r2 = r1.L$0
                kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
                kotlin.ResultKt.throwOnFailure(r10)
                goto L63
            L23:
                kotlin.ResultKt.throwOnFailure(r10)
                r1 = r9
                java.lang.Object r2 = r1.L$0
                kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
                com.gamebooster2.booster.BoosterEngine r3 = com.gamebooster2.booster.BoosterEngine.this
                java.lang.String r3 = com.gamebooster2.booster.BoosterEngine.access$getTAG$p(r3)
                java.lang.String r4 = "Ping monitor started"
                android.util.Log.d(r3, r4)
            L36:
                boolean r3 = kotlinx.coroutines.CoroutineScopeKt.isActive(r2)
                if (r3 == 0) goto L75
                com.gamebooster2.booster.BoosterEngine r3 = com.gamebooster2.booster.BoosterEngine.this
                java.lang.String r4 = "1.1.1.1"
                long r3 = com.gamebooster2.booster.BoosterEngine.access$measurePing(r3, r4)
                kotlinx.coroutines.MainCoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getMain()
                kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
                com.gamebooster2.booster.BoosterEngine$startPingMonitor$1$1 r6 = new com.gamebooster2.booster.BoosterEngine$startPingMonitor$1$1
                com.gamebooster2.booster.BoosterEngine r7 = com.gamebooster2.booster.BoosterEngine.this
                r8 = 0
                r6.<init>(r7, r3, r8)
                kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                r7 = r1
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r1.L$0 = r2
                r8 = 1
                r1.label = r8
                java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r5, r6, r7)
                if (r3 != r0) goto L63
                return r0
            L63:
                r3 = r1
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r1.L$0 = r2
                r4 = 2
                r1.label = r4
                r4 = 3000(0xbb8, double:1.482E-320)
                java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r4, r3)
                if (r3 != r0) goto L74
                return r0
            L74:
                goto L36
            L75:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.gamebooster2.booster.BoosterEngine.C00591.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$startPingMonitor$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BoosterEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$startPingMonitor$1$1", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00171 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ long $ping;
            int label;
            final /* synthetic */ BoosterEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00171(BoosterEngine boosterEngine, long j, Continuation<? super C00171> continuation) {
                super(2, continuation);
                this.this$0 = boosterEngine;
                this.$ping = j;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00171(this.this$0, this.$ping, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00171) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        Function2<String, Long, Unit> onPingUpdate = this.this$0.getOnPingUpdate();
                        if (onPingUpdate == null) {
                            return null;
                        }
                        onPingUpdate.invoke("1.1.1.1", Boxing.boxLong(this.$ping));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPingMonitor() {
        Job job = this.pingJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.pingJob = BuildersKt__Builders_commonKt.launch$default(this.engineScope, Dispatchers.getIO(), null, new C00591(null), 2, null);
    }

    public final void stopPingMonitor() {
        Job job = this.pingJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.pingJob = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long measurePing(String host) {
        try {
            long start = System.currentTimeMillis();
            boolean reachable = InetAddress.getByName(host).isReachable(1500);
            long elapsed = System.currentTimeMillis() - start;
            if (reachable) {
                return elapsed;
            }
            return -1L;
        } catch (Exception e) {
            return -1L;
        }
    }

    private final void releaseNetworkOptimizations() {
        try {
            WifiManager.WifiLock wifiLock = this.wifiLock;
            if (wifiLock != null) {
                wifiLock.release();
            }
            this.wifiLock = null;
        } catch (Exception e) {
        }
        try {
            Settings.Global.putString(this.context.getContentResolver(), "private_dns_mode", "opportunistic");
        } catch (Exception e2) {
        }
        try {
            ContentResolver.setMasterSyncAutomatically(true);
        } catch (Exception e3) {
        }
        stopPingMonitor();
        Log.d(this.TAG, "Network optimizations released");
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$manualBoost$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$manualBoost$1", f = "BoosterEngine.kt", i = {}, l = {309}, m = "invokeSuspend", n = {}, s = {})
    static final class C00541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00541(Continuation<? super C00541> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BoosterEngine.this.new C00541(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    int freed = BoosterEngine.this.performKill();
                    this.label = 1;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new C00121(BoosterEngine.this, freed, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$manualBoost$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BoosterEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$manualBoost$1$1", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $freed;
            int label;
            final /* synthetic */ BoosterEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00121(BoosterEngine boosterEngine, int i, Continuation<? super C00121> continuation) {
                super(2, continuation);
                this.this$0 = boosterEngine;
                this.$freed = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00121(this.this$0, this.$freed, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00121) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        Function1<Integer, Unit> onRamFreed = this.this$0.getOnRamFreed();
                        if (onRamFreed != null) {
                            onRamFreed.invoke(Boxing.boxInt(this.$freed));
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final void manualBoost() {
        BuildersKt__Builders_commonKt.launch$default(this.engineScope, Dispatchers.getIO(), null, new C00541(null), 2, null);
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$manualKillAll$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$manualKillAll$1", f = "BoosterEngine.kt", i = {}, l = {TypedValues.AttributesType.TYPE_PATH_ROTATE}, m = "invokeSuspend", n = {}, s = {})
    static final class C00551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00551(Continuation<? super C00551> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BoosterEngine.this.new C00551(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00551) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    int freed = BoosterEngine.this.performKill();
                    this.label = 1;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new C00131(BoosterEngine.this, freed, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$manualKillAll$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BoosterEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$manualKillAll$1$1", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $freed;
            int label;
            final /* synthetic */ BoosterEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00131(BoosterEngine boosterEngine, int i, Continuation<? super C00131> continuation) {
                super(2, continuation);
                this.this$0 = boosterEngine;
                this.$freed = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00131(this.this$0, this.$freed, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        Function1<Integer, Unit> onRamFreed = this.this$0.getOnRamFreed();
                        if (onRamFreed != null) {
                            onRamFreed.invoke(Boxing.boxInt(this.$freed));
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final void manualKillAll() {
        BuildersKt__Builders_commonKt.launch$default(this.engineScope, Dispatchers.getIO(), null, new C00551(null), 2, null);
    }

    public final void activateBoostForGame(final String gamePackage) {
        Intrinsics.checkNotNullParameter(gamePackage, "gamePackage");
        this.isBoostActive = true;
        this.activeGamePackage = gamePackage;
        this.thermalMonitor.setGameRunning(true);
        withMain(new Function0<Unit>() { // from class: com.gamebooster2.booster.BoosterEngine.activateBoostForGame.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                Function1<String, Unit> onBoostActivated = BoosterEngine.this.getOnBoostActivated();
                if (onBoostActivated != null) {
                    onBoostActivated.invoke(gamePackage);
                }
            }
        });
        BuildersKt__Builders_commonKt.launch$default(this.engineScope, Dispatchers.getIO(), null, new AnonymousClass2(null), 2, null);
        startReKillLoop(gamePackage);
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$activateBoostForGame$2, reason: invalid class name */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$activateBoostForGame$2", f = "BoosterEngine.kt", i = {}, l = {327}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BoosterEngine.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    int freed = BoosterEngine.this.performKill();
                    this.label = 1;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(BoosterEngine.this, freed, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$activateBoostForGame$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: BoosterEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$activateBoostForGame$2$1", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $freed;
            int label;
            final /* synthetic */ BoosterEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(BoosterEngine boosterEngine, int i, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = boosterEngine;
                this.$freed = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$freed, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        Function1<Integer, Unit> onRamFreed = this.this$0.getOnRamFreed();
                        if (onRamFreed != null) {
                            onRamFreed.invoke(Boxing.boxInt(this.$freed));
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deactivateBoost() {
        this.isBoostActive = false;
        this.activeGamePackage = "";
        this.thermalMonitor.setGameRunning(false);
        stopReKillLoop();
        releaseNetworkOptimizations();
        withMain(new Function0<Unit>() { // from class: com.gamebooster2.booster.BoosterEngine.deactivateBoost.1
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
                Function0<Unit> onBoostDeactivated = BoosterEngine.this.getOnBoostDeactivated();
                if (onBoostDeactivated != null) {
                    onBoostDeactivated.invoke();
                }
            }
        });
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$startReKillLoop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$startReKillLoop$1", f = "BoosterEngine.kt", i = {0, 1, 2}, l = {344, 348, 349}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$0"})
    static final class C00601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C00601(Continuation<? super C00601> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00601 c00601 = BoosterEngine.this.new C00601(continuation);
            c00601.L$0 = obj;
            return c00601;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x004e  */
        /* JADX WARN: Code duplicated, block: B:17:0x0061  */
        /* JADX WARN: Code duplicated, block: B:19:0x007f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:23:0x008f A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x008d -> B:13:0x0048). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 60000(0xea60, double:2.9644E-319)
                switch(r1) {
                    case 0: goto L30;
                    case 1: goto L27;
                    case 2: goto L1e;
                    case 3: goto L14;
                    default: goto Lc;
                }
            Lc:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L14:
                r1 = r10
                java.lang.Object r4 = r1.L$0
                kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
                kotlin.ResultKt.throwOnFailure(r11)
                goto L90
            L1e:
                r1 = r10
                java.lang.Object r4 = r1.L$0
                kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
                kotlin.ResultKt.throwOnFailure(r11)
                goto L80
            L27:
                r1 = r10
                java.lang.Object r4 = r1.L$0
                kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
                kotlin.ResultKt.throwOnFailure(r11)
                goto L47
            L30:
                kotlin.ResultKt.throwOnFailure(r11)
                r1 = r10
                java.lang.Object r4 = r1.L$0
                kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r1.L$0 = r4
                r6 = 1
                r1.label = r6
                java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r2, r5)
                if (r5 != r0) goto L47
                return r0
            L47:
            L48:
                boolean r5 = kotlinx.coroutines.CoroutineScopeKt.isActive(r4)
                if (r5 == 0) goto L91
                com.gamebooster2.booster.BoosterEngine r5 = com.gamebooster2.booster.BoosterEngine.this
                java.lang.String r5 = com.gamebooster2.booster.BoosterEngine.access$getTAG$p(r5)
                java.lang.String r6 = "Re-kill loop tick"
                android.util.Log.d(r5, r6)
                com.gamebooster2.booster.BoosterEngine r5 = com.gamebooster2.booster.BoosterEngine.this
                int r5 = com.gamebooster2.booster.BoosterEngine.access$performKill(r5)
                if (r5 <= 0) goto L81
                kotlinx.coroutines.MainCoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getMain()
                kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
                com.gamebooster2.booster.BoosterEngine$startReKillLoop$1$1 r7 = new com.gamebooster2.booster.BoosterEngine$startReKillLoop$1$1
                com.gamebooster2.booster.BoosterEngine r8 = com.gamebooster2.booster.BoosterEngine.this
                r9 = 0
                r7.<init>(r8, r5, r9)
                kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
                r8 = r1
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r1.L$0 = r4
                r9 = 2
                r1.label = r9
                java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r6, r7, r8)
                if (r5 != r0) goto L80
                return r0
            L80:
            L81:
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r1.L$0 = r4
                r6 = 3
                r1.label = r6
                java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r2, r5)
                if (r5 != r0) goto L90
                return r0
            L90:
                goto L48
            L91:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.gamebooster2.booster.BoosterEngine.C00601.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$startReKillLoop$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BoosterEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$startReKillLoop$1$1", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $freed;
            int label;
            final /* synthetic */ BoosterEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00181(BoosterEngine boosterEngine, int i, Continuation<? super C00181> continuation) {
                super(2, continuation);
                this.this$0 = boosterEngine;
                this.$freed = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00181(this.this$0, this.$freed, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        Function1<Integer, Unit> onRamFreed = this.this$0.getOnRamFreed();
                        if (onRamFreed == null) {
                            return null;
                        }
                        onRamFreed.invoke(Boxing.boxInt(this.$freed));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    private final void startReKillLoop(String gamePackage) {
        stopReKillLoop();
        this.reKillJob = BuildersKt__Builders_commonKt.launch$default(this.engineScope, Dispatchers.getIO(), null, new C00601(null), 2, null);
    }

    /* JADX INFO: renamed from: com.gamebooster2.booster.BoosterEngine$withMain$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BoosterEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.booster.BoosterEngine$withMain$1", f = "BoosterEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00611(Function0<Unit> function0, Continuation<? super C00611> continuation) {
            super(2, continuation);
            this.$block = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00611(this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.$block.invoke();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final void stopReKillLoop() {
        Job job = this.reKillJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.reKillJob = null;
    }

    private final void withMain(Function0<Unit> block) {
        BuildersKt__Builders_commonKt.launch$default(this.engineScope, Dispatchers.getMain(), null, new C00611(block, null), 2, null);
    }
}
