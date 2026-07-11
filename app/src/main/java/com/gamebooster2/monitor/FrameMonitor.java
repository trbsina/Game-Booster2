package com.gamebooster2.monitor;

import android.util.Log;
import android.view.Choreographer;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: FrameMonitor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Q\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006*\u0001\u0011\u0018\u00002\u00020\u0001B(\u0012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0002\u0010\tJ\u0006\u0010\u001d\u001a\u00020\bJ\u0006\u0010\u001e\u001a\u00020\bR\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/gamebooster2/monitor/FrameMonitor;", "", "onHighLoad", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "frameMs", "", "(Lkotlin/jvm/functions/Function1;)V", "SLOW_FRAME_THRESHOLD", "", "TAG", "", "_frameMs", "Lkotlinx/coroutines/flow/MutableStateFlow;", "frameCallback", "com/gamebooster2/monitor/FrameMonitor$frameCallback$1", "Lcom/gamebooster2/monitor/FrameMonitor$frameCallback$1;", "Lkotlinx/coroutines/flow/StateFlow;", "getFrameMs", "()Lkotlinx/coroutines/flow/StateFlow;", "frameSamples", "", "isRunning", "", "lastFrameTime", "secondStartTime", "slowFrameCount", "start", "stop", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FrameMonitor {
    private final int SLOW_FRAME_THRESHOLD;
    private final String TAG;
    private final MutableStateFlow<Long> _frameMs;
    private final FrameMonitor$frameCallback$1 frameCallback;
    private final StateFlow<Long> frameMs;
    private final List<Long> frameSamples;
    private boolean isRunning;
    private long lastFrameTime;
    private final Function1<Long, Unit> onHighLoad;
    private long secondStartTime;
    private int slowFrameCount;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.gamebooster2.monitor.FrameMonitor$frameCallback$1] */
    public FrameMonitor(Function1<? super Long, Unit> onHighLoad) {
        Intrinsics.checkNotNullParameter(onHighLoad, "onHighLoad");
        this.onHighLoad = onHighLoad;
        this.TAG = "FrameMonitor";
        this._frameMs = StateFlowKt.MutableStateFlow(0L);
        this.frameMs = this._frameMs;
        this.frameSamples = new ArrayList();
        this.SLOW_FRAME_THRESHOLD = 5;
        this.frameCallback = new Choreographer.FrameCallback() { // from class: com.gamebooster2.monitor.FrameMonitor$frameCallback$1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long frameTimeNanos) {
                if (this.this$0.isRunning) {
                    if (this.this$0.lastFrameTime > 0) {
                        long deltaMs = (frameTimeNanos - this.this$0.lastFrameTime) / 1000000;
                        if (1 <= deltaMs && deltaMs < 501) {
                            this.this$0.frameSamples.add(Long.valueOf(deltaMs));
                            long elapsed = (frameTimeNanos - this.this$0.secondStartTime) / 1000000;
                            if (elapsed >= 1000) {
                                if (!this.this$0.frameSamples.isEmpty()) {
                                    long avgMs = (long) CollectionsKt.averageOfLong(this.this$0.frameSamples);
                                    this.this$0._frameMs.setValue(Long.valueOf(avgMs));
                                    Log.d(this.this$0.TAG, "Avg frame: " + avgMs + "ms over " + this.this$0.frameSamples.size() + " frames");
                                    if (avgMs <= 16) {
                                        this.this$0.slowFrameCount = 0;
                                    } else {
                                        this.this$0.slowFrameCount++;
                                        if (this.this$0.slowFrameCount >= this.this$0.SLOW_FRAME_THRESHOLD) {
                                            Log.w(this.this$0.TAG, "Sustained CPU struggle: " + avgMs + "ms avg");
                                            this.this$0.onHighLoad.invoke(Long.valueOf(avgMs));
                                            this.this$0.slowFrameCount = 0;
                                        }
                                    }
                                }
                                this.this$0.frameSamples.clear();
                                this.this$0.secondStartTime = frameTimeNanos;
                            }
                        }
                    } else {
                        this.this$0.secondStartTime = frameTimeNanos;
                    }
                    this.this$0.lastFrameTime = frameTimeNanos;
                    Choreographer.getInstance().postFrameCallback(this);
                }
            }
        };
    }

    public final StateFlow<Long> getFrameMs() {
        return this.frameMs;
    }

    public final void start() {
        if (this.isRunning) {
            return;
        }
        this.isRunning = true;
        this.lastFrameTime = 0L;
        this.slowFrameCount = 0;
        this.secondStartTime = 0L;
        this.frameSamples.clear();
        Log.d(this.TAG, "FrameMonitor started — updating every 1 second");
        Choreographer.getInstance().postFrameCallback(this.frameCallback);
    }

    public final void stop() {
        this.isRunning = false;
        Choreographer.getInstance().removeFrameCallback(this.frameCallback);
        this._frameMs.setValue(0L);
        this.slowFrameCount = 0;
        this.frameSamples.clear();
        Log.d(this.TAG, "FrameMonitor stopped");
    }
}
