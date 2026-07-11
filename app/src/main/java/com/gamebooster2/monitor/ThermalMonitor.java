package com.gamebooster2.monitor;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: ThermalMonitor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\"\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00180\u001cJ\u0006\u0010\u001d\u001a\u00020\u0018R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/gamebooster2/monitor/ThermalMonitor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "_info", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/gamebooster2/monitor/ThermalInfo;", "isGameRunning", "", "()Z", "setGameRunning", "(Z)V", "job", "Lkotlinx/coroutines/Job;", "thermalInfo", "Lkotlinx/coroutines/flow/StateFlow;", "getThermalInfo", "()Lkotlinx/coroutines/flow/StateFlow;", "readBatteryTemp", "", "start", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "onCritical", "Lkotlin/Function1;", "stop", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ThermalMonitor {
    private final String TAG;
    private final MutableStateFlow<ThermalInfo> _info;
    private final Context context;
    private boolean isGameRunning;
    private Job job;
    private final StateFlow<ThermalInfo> thermalInfo;

    public ThermalMonitor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "ThermalMonitor";
        this._info = StateFlowKt.MutableStateFlow(new ThermalInfo(0.0f, 0.0f, ThermalLevel.COOL));
        this.thermalInfo = this._info;
    }

    public final StateFlow<ThermalInfo> getThermalInfo() {
        return this.thermalInfo;
    }

    /* JADX INFO: renamed from: isGameRunning, reason: from getter */
    public final boolean getIsGameRunning() {
        return this.isGameRunning;
    }

    public final void setGameRunning(boolean z) {
        this.isGameRunning = z;
    }

    /* JADX INFO: renamed from: com.gamebooster2.monitor.ThermalMonitor$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: ThermalMonitor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.gamebooster2.monitor.ThermalMonitor$start$1", f = "ThermalMonitor.kt", i = {0, 1}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX, ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<ThermalInfo, Unit> $onCritical;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super ThermalInfo, Unit> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$onCritical = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = ThermalMonitor.this.new AnonymousClass1(this.$onCritical, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0033  */
        /* JADX WARN: Code duplicated, block: B:13:0x0040  */
        /* JADX WARN: Code duplicated, block: B:14:0x0043  */
        /* JADX WARN: Code duplicated, block: B:16:0x004b  */
        /* JADX WARN: Code duplicated, block: B:17:0x004e  */
        /* JADX WARN: Code duplicated, block: B:20:0x0061  */
        /* JADX WARN: Code duplicated, block: B:21:0x0064  */
        /* JADX WARN: Code duplicated, block: B:23:0x006a  */
        /* JADX WARN: Code duplicated, block: B:24:0x006d  */
        /* JADX WARN: Code duplicated, block: B:26:0x0073  */
        /* JADX WARN: Code duplicated, block: B:27:0x0076  */
        /* JADX WARN: Code duplicated, block: B:30:0x008b  */
        /* JADX WARN: Code duplicated, block: B:32:0x00a9 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:36:0x00bb A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00b9 -> B:9:0x002d). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:24:0x006d
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                Method dump skipped, instruction units count: 204
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.gamebooster2.monitor.ThermalMonitor.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.gamebooster2.monitor.ThermalMonitor$start$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ThermalMonitor.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.gamebooster2.monitor.ThermalMonitor$start$1$1", f = "ThermalMonitor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ThermalInfo $info;
            final /* synthetic */ Function1<ThermalInfo, Unit> $onCritical;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00191(Function1<? super ThermalInfo, Unit> function1, ThermalInfo thermalInfo, Continuation<? super C00191> continuation) {
                super(2, continuation);
                this.$onCritical = function1;
                this.$info = thermalInfo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00191(this.$onCritical, this.$info, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        this.$onCritical.invoke(this.$info);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final void start(CoroutineScope scope, Function1<? super ThermalInfo, Unit> onCritical) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(onCritical, "onCritical");
        this.job = BuildersKt__Builders_commonKt.launch$default(scope, Dispatchers.getIO(), null, new AnonymousClass1(onCritical, null), 2, null);
    }

    public final void stop() {
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float readBatteryTemp() {
        try {
            Intent intent = this.context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int tenths = intent != null ? intent.getIntExtra("temperature", 0) : 0;
            return tenths / 10.0f;
        } catch (Exception e) {
            Log.e(this.TAG, "Battery temp failed: " + e.getMessage());
            return 0.0f;
        }
    }
}
