package com.gamebooster2.monitor;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: RamMonitor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0013J\u0006\u0010\u0017\u001a\u00020\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lcom/gamebooster2/monitor/RamMonitor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "_ramInfo", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/gamebooster2/monitor/RamInfo;", "am", "Landroid/app/ActivityManager;", "job", "Lkotlinx/coroutines/Job;", "ramInfo", "Lkotlinx/coroutines/flow/StateFlow;", "getRamInfo", "()Lkotlinx/coroutines/flow/StateFlow;", "start", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "stop", "trimMemory", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class RamMonitor {
    private final String TAG;
    private final MutableStateFlow<RamInfo> _ramInfo;
    private final ActivityManager am;
    private final Context context;
    private Job job;
    private final StateFlow<RamInfo> ramInfo;

    public RamMonitor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "RamMonitor";
        this._ramInfo = StateFlowKt.MutableStateFlow(new RamInfo(0L, 0L, 0L, 0.0f));
        this.ramInfo = this._ramInfo;
        Object systemService = this.context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        this.am = (ActivityManager) systemService;
    }

    public final StateFlow<RamInfo> getRamInfo() {
        return this.ramInfo;
    }

    /* JADX INFO: renamed from: com.gamebooster2.monitor.RamMonitor$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: RamMonitor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.monitor.RamMonitor$start$1", f = "RamMonitor.kt", i = {0}, l = {35}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = RamMonitor.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass1 anonymousClass1;
            CoroutineScope $this$launch;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    anonymousClass1 = this;
                    $this$launch = (CoroutineScope) anonymousClass1.L$0;
                    break;
                case 1:
                    anonymousClass1 = this;
                    $this$launch = (CoroutineScope) anonymousClass1.L$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            while (CoroutineScopeKt.isActive($this$launch)) {
                ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
                RamMonitor.this.am.getMemoryInfo(mi);
                long j = 1048576;
                long total = mi.totalMem / j;
                long avail = mi.availMem / j;
                long used = total - avail;
                RamMonitor.this._ramInfo.setValue(new RamInfo(total, avail, used, (used / total) * 100.0f));
                anonymousClass1.L$0 = $this$launch;
                anonymousClass1.label = 1;
                if (DelayKt.delay(2000L, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void start(CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.job = BuildersKt__Builders_commonKt.launch$default(scope, Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
    }

    public final void stop() {
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    public final void trimMemory() {
        try {
            this.am.getClass().getMethod("sendTrimMemory", new Class[0]).invoke(this.am, new Object[0]);
        } catch (Exception e) {
            Log.d(this.TAG, "sendTrimMemory not available: " + e.getMessage());
        }
    }
}
