package androidx.datastore.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.core.handlers.NoOpCorruptionHandler;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: SingleProcessDataStore.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 F*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0003FGHB\u007f\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012?\b\u0002\u0010\b\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\n0\t\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001f\u0010+\u001a\u00020\u00102\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010.J\u001f\u0010/\u001a\u00020\u00102\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u000001H\u0082@ø\u0001\u0000¢\u0006\u0002\u00102J\u0011\u00103\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u00105\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u00106\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u00107\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u00108\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104JL\u00109\u001a\u00028\u000021\u0010:\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(;\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\n2\u0006\u0010<\u001a\u00020=H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010>JD\u0010?\u001a\u00028\u000021\u0010:\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(;\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\nH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010@J\u001b\u0010A\u001a\u00020\u00102\u0006\u0010B\u001a\u00028\u0000H\u0080@ø\u0001\u0000¢\u0006\u0004\bC\u0010DJ\f\u0010E\u001a\u00020\u0010*\u00020\u0005H\u0002R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR \u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\"0!X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b#\u0010$R\u001b\u0010%\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010'RJ\u0010*\u001a;\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\n\u0018\u00010\tX\u0082\u000eø\u0001\u0000¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006I"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore;", "T", "Landroidx/datastore/core/DataStore;", "produceFile", "Lkotlin/Function0;", "Ljava/io/File;", "serializer", "Landroidx/datastore/core/Serializer;", "initTasksList", "", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", "name", "api", "Lkotlin/coroutines/Continuation;", "", "", "corruptionHandler", "Landroidx/datastore/core/CorruptionHandler;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlin/jvm/functions/Function0;Landroidx/datastore/core/Serializer;Ljava/util/List;Landroidx/datastore/core/CorruptionHandler;Lkotlinx/coroutines/CoroutineScope;)V", "SCRATCH_SUFFIX", "", "actor", "Landroidx/datastore/core/SimpleActor;", "Landroidx/datastore/core/SingleProcessDataStore$Message;", "data", "Lkotlinx/coroutines/flow/Flow;", "getData", "()Lkotlinx/coroutines/flow/Flow;", "downstreamFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/datastore/core/State;", "getDownstreamFlow$annotations", "()V", "file", "getFile", "()Ljava/io/File;", "file$delegate", "Lkotlin/Lazy;", "initTasks", "handleRead", "read", "Landroidx/datastore/core/SingleProcessDataStore$Message$Read;", "(Landroidx/datastore/core/SingleProcessDataStore$Message$Read;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleUpdate", "update", "Landroidx/datastore/core/SingleProcessDataStore$Message$Update;", "(Landroidx/datastore/core/SingleProcessDataStore$Message$Update;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAndInit", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAndInitOrPropagateAndThrowFailure", "readAndInitOrPropagateFailure", "readData", "readDataOrHandleCorruption", "transformAndWrite", "transform", "t", "callerContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateData", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeData", "newData", "writeData$datastore_core", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createParentDirectories", "Companion", "Message", "UncloseableOutputStream", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SingleProcessDataStore<T> implements DataStore<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<String> activeFiles = new LinkedHashSet();
    private static final Object activeFilesLock = new Object();
    private final String SCRATCH_SUFFIX;
    private final SimpleActor<Message<T>> actor;
    private final CorruptionHandler<T> corruptionHandler;
    private final Flow<T> data;
    private final MutableStateFlow<State<T>> downstreamFlow;

    /* JADX INFO: renamed from: file$delegate, reason: from kotlin metadata */
    private final Lazy file;
    private List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasks;
    private final Function0<File> produceFile;
    private final CoroutineScope scope;
    private final Serializer<T> serializer;

    /* JADX INFO: renamed from: androidx.datastore.core.SingleProcessDataStore$handleUpdate$1, reason: invalid class name */
    /* JADX INFO: compiled from: SingleProcessDataStore.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.datastore.core.SingleProcessDataStore", f = "SingleProcessDataStore.kt", i = {1, 1}, l = {276, 281, 284}, m = "handleUpdate", n = {"update", "$this$handleUpdate_u24lambda_u2d0"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SingleProcessDataStore<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SingleProcessDataStore<T> singleProcessDataStore, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = singleProcessDataStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.handleUpdate(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.SingleProcessDataStore$readAndInit$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SingleProcessDataStore.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.datastore.core.SingleProcessDataStore", f = "SingleProcessDataStore.kt", i = {0, 0, 1, 1, 1, 2}, l = {322, 348, TypedValues.PositionType.TYPE_SIZE_PERCENT}, m = "readAndInit", n = {"updateLock", "initData", "updateLock", "initData", "initializationComplete", "$this$withLock_u24default$iv"}, s = {"L$1", "L$2", "L$1", "L$2", "L$3", "L$3"})
    static final class C00361 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SingleProcessDataStore<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00361(SingleProcessDataStore<T> singleProcessDataStore, Continuation<? super C00361> continuation) {
            super(continuation);
            this.this$0 = singleProcessDataStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readAndInit(this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateAndThrowFailure$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SingleProcessDataStore.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.datastore.core.SingleProcessDataStore", f = "SingleProcessDataStore.kt", i = {0}, l = {302}, m = "readAndInitOrPropagateAndThrowFailure", n = {"this"}, s = {"L$0"})
    static final class C00371 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SingleProcessDataStore<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00371(SingleProcessDataStore<T> singleProcessDataStore, Continuation<? super C00371> continuation) {
            super(continuation);
            this.this$0 = singleProcessDataStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readAndInitOrPropagateAndThrowFailure(this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateFailure$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SingleProcessDataStore.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.datastore.core.SingleProcessDataStore", f = "SingleProcessDataStore.kt", i = {0}, l = {311}, m = "readAndInitOrPropagateFailure", n = {"this"}, s = {"L$0"})
    static final class C00381 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SingleProcessDataStore<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00381(SingleProcessDataStore<T> singleProcessDataStore, Continuation<? super C00381> continuation) {
            super(continuation);
            this.this$0 = singleProcessDataStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readAndInitOrPropagateFailure(this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.SingleProcessDataStore$readData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SingleProcessDataStore.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.datastore.core.SingleProcessDataStore", f = "SingleProcessDataStore.kt", i = {0}, l = {381}, m = "readData", n = {"this"}, s = {"L$0"})
    static final class C00391 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SingleProcessDataStore<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00391(SingleProcessDataStore<T> singleProcessDataStore, Continuation<? super C00391> continuation) {
            super(continuation);
            this.this$0 = singleProcessDataStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readData(this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.SingleProcessDataStore$readDataOrHandleCorruption$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SingleProcessDataStore.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.datastore.core.SingleProcessDataStore", f = "SingleProcessDataStore.kt", i = {0, 1, 2, 2}, l = {359, 362, 365}, m = "readDataOrHandleCorruption", n = {"this", "ex", "ex", "newData"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C00401 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SingleProcessDataStore<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00401(SingleProcessDataStore<T> singleProcessDataStore, Continuation<? super C00401> continuation) {
            super(continuation);
            this.this$0 = singleProcessDataStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readDataOrHandleCorruption(this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.SingleProcessDataStore$transformAndWrite$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SingleProcessDataStore.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.datastore.core.SingleProcessDataStore", f = "SingleProcessDataStore.kt", i = {0, 0, 0}, l = {TypedValues.CycleType.TYPE_VISIBILITY, 410}, m = "transformAndWrite", n = {"this", "curDataAndHash", "curData"}, s = {"L$0", "L$1", "L$2"})
    static final class C00411 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SingleProcessDataStore<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00411(SingleProcessDataStore<T> singleProcessDataStore, Continuation<? super C00411> continuation) {
            super(continuation);
            this.this$0 = singleProcessDataStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.transformAndWrite(null, null, this);
        }
    }

    private static /* synthetic */ void getDownstreamFlow$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SingleProcessDataStore(Function0<? extends File> produceFile, Serializer<T> serializer, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasksList, CorruptionHandler<T> corruptionHandler, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(produceFile, "produceFile");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(initTasksList, "initTasksList");
        Intrinsics.checkNotNullParameter(corruptionHandler, "corruptionHandler");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.produceFile = produceFile;
        this.serializer = serializer;
        this.corruptionHandler = corruptionHandler;
        this.scope = scope;
        this.data = FlowKt.flow(new SingleProcessDataStore$data$1(this, null));
        this.SCRATCH_SUFFIX = ".tmp";
        this.file = LazyKt.lazy(new Function0<File>(this) { // from class: androidx.datastore.core.SingleProcessDataStore$file$2
            final /* synthetic */ SingleProcessDataStore<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                File file = (File) ((SingleProcessDataStore) this.this$0).produceFile.invoke();
                String it = file.getAbsolutePath();
                synchronized (SingleProcessDataStore.INSTANCE.getActiveFilesLock$datastore_core()) {
                    if (SingleProcessDataStore.INSTANCE.getActiveFiles$datastore_core().contains(it)) {
                        throw new IllegalStateException(("There are multiple DataStores active for the same file: " + file + ". You should either maintain your DataStore as a singleton or confirm that there is no two DataStore's active on the same file (by confirming that the scope is cancelled).").toString());
                    }
                    Set<String> activeFiles$datastore_core = SingleProcessDataStore.INSTANCE.getActiveFiles$datastore_core();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    activeFiles$datastore_core.add(it);
                }
                return file;
            }
        });
        this.downstreamFlow = StateFlowKt.MutableStateFlow(UnInitialized.INSTANCE);
        this.initTasks = CollectionsKt.toList(initTasksList);
        this.actor = new SimpleActor<>(this.scope, new Function1<Throwable, Unit>(this) { // from class: androidx.datastore.core.SingleProcessDataStore$actor$1
            final /* synthetic */ SingleProcessDataStore<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                if (it != null) {
                    ((SingleProcessDataStore) this.this$0).downstreamFlow.setValue(new Final(it));
                }
                Object activeFilesLock$datastore_core = SingleProcessDataStore.INSTANCE.getActiveFilesLock$datastore_core();
                SingleProcessDataStore<T> singleProcessDataStore = this.this$0;
                synchronized (activeFilesLock$datastore_core) {
                    SingleProcessDataStore.INSTANCE.getActiveFiles$datastore_core().remove(singleProcessDataStore.getFile().getAbsolutePath());
                    Unit unit = Unit.INSTANCE;
                }
            }
        }, new Function2<Message<T>, Throwable, Unit>() { // from class: androidx.datastore.core.SingleProcessDataStore$actor$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object p1, Throwable th) {
                invoke((SingleProcessDataStore.Message) p1, th);
                return Unit.INSTANCE;
            }

            public final void invoke(SingleProcessDataStore.Message<T> msg, Throwable ex) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                if (msg instanceof SingleProcessDataStore.Message.Update) {
                    ((SingleProcessDataStore.Message.Update) msg).getAck().completeExceptionally(ex == null ? new CancellationException("DataStore scope was cancelled before updateData could complete") : ex);
                }
            }
        }, new SingleProcessDataStore$actor$3(this, null));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ SingleProcessDataStore(Function0 function0, Serializer serializer, List list, CorruptionHandler corruptionHandler, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        CoroutineScope CoroutineScope;
        List listEmptyList = (i & 4) != 0 ? CollectionsKt.emptyList() : list;
        CorruptionHandler noOpCorruptionHandler = (i & 8) != 0 ? new NoOpCorruptionHandler() : corruptionHandler;
        if ((i & 16) != 0) {
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        } else {
            CoroutineScope = coroutineScope;
        }
        this(function0, serializer, listEmptyList, noOpCorruptionHandler, CoroutineScope);
    }

    @Override // androidx.datastore.core.DataStore
    public Flow<T> getData() {
        return this.data;
    }

    @Override // androidx.datastore.core.DataStore
    public Object updateData(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        CompletableDeferred ack = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        Message.Update updateMsg = new Message.Update(function2, ack, this.downstreamFlow.getValue(), continuation.getContext());
        this.actor.offer(updateMsg);
        return ack.await(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getFile() {
        return (File) this.file.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SingleProcessDataStore.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002:\u0002\b\tB\u0007\b\u0004¢\u0006\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore$Message;", "T", "", "()V", "lastState", "Landroidx/datastore/core/State;", "getLastState", "()Landroidx/datastore/core/State;", "Read", "Update", "Landroidx/datastore/core/SingleProcessDataStore$Message$Read;", "Landroidx/datastore/core/SingleProcessDataStore$Message$Update;", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static abstract class Message<T> {
        public /* synthetic */ Message(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract State<T> getLastState();

        private Message() {
        }

        /* JADX INFO: compiled from: SingleProcessDataStore.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005R\u001c\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore$Message$Read;", "T", "Landroidx/datastore/core/SingleProcessDataStore$Message;", "lastState", "Landroidx/datastore/core/State;", "(Landroidx/datastore/core/State;)V", "getLastState", "()Landroidx/datastore/core/State;", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Read<T> extends Message<T> {
            private final State<T> lastState;

            @Override // androidx.datastore.core.SingleProcessDataStore.Message
            public State<T> getLastState() {
                return this.lastState;
            }

            public Read(State<T> state) {
                super(null);
                this.lastState = state;
            }
        }

        /* JADX INFO: compiled from: SingleProcessDataStore.kt */
        @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002Ba\u00121\u0010\u0003\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u000b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016RA\u0010\u0003\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore$Message$Update;", "T", "Landroidx/datastore/core/SingleProcessDataStore$Message;", "transform", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "t", "Lkotlin/coroutines/Continuation;", "", "ack", "Lkotlinx/coroutines/CompletableDeferred;", "lastState", "Landroidx/datastore/core/State;", "callerContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function2;Lkotlinx/coroutines/CompletableDeferred;Landroidx/datastore/core/State;Lkotlin/coroutines/CoroutineContext;)V", "getAck", "()Lkotlinx/coroutines/CompletableDeferred;", "getCallerContext", "()Lkotlin/coroutines/CoroutineContext;", "getLastState", "()Landroidx/datastore/core/State;", "getTransform", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Update<T> extends Message<T> {
            private final CompletableDeferred<T> ack;
            private final CoroutineContext callerContext;
            private final State<T> lastState;
            private final Function2<T, Continuation<? super T>, Object> transform;

            public final Function2<T, Continuation<? super T>, Object> getTransform() {
                return this.transform;
            }

            public final CompletableDeferred<T> getAck() {
                return this.ack;
            }

            @Override // androidx.datastore.core.SingleProcessDataStore.Message
            public State<T> getLastState() {
                return this.lastState;
            }

            public final CoroutineContext getCallerContext() {
                return this.callerContext;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public Update(Function2<? super T, ? super Continuation<? super T>, ? extends Object> transform, CompletableDeferred<T> ack, State<T> state, CoroutineContext callerContext) {
                super(null);
                Intrinsics.checkNotNullParameter(transform, "transform");
                Intrinsics.checkNotNullParameter(ack, "ack");
                Intrinsics.checkNotNullParameter(callerContext, "callerContext");
                this.transform = transform;
                this.ack = ack;
                this.lastState = state;
                this.callerContext = callerContext;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object handleRead(Message.Read<T> read, Continuation<? super Unit> continuation) {
        State<T> value = this.downstreamFlow.getValue();
        if (!(value instanceof Data)) {
            if (value instanceof ReadException) {
                if (value == read.getLastState()) {
                    Object andInitOrPropagateFailure = readAndInitOrPropagateFailure(continuation);
                    return andInitOrPropagateFailure == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? andInitOrPropagateFailure : Unit.INSTANCE;
                }
            } else {
                if (Intrinsics.areEqual(value, UnInitialized.INSTANCE)) {
                    Object andInitOrPropagateFailure2 = readAndInitOrPropagateFailure(continuation);
                    return andInitOrPropagateFailure2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? andInitOrPropagateFailure2 : Unit.INSTANCE;
                }
                if (value instanceof Final) {
                    throw new IllegalStateException("Can't read in final state.".toString());
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:46:0x00ce A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:47:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object handleUpdate(Message.Update<T> update, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        CompletableDeferred<T> completableDeferred;
        Object objM162constructorimpl;
        CompletableDeferred<T> ack;
        SingleProcessDataStore<T> $this$handleUpdate_u24lambda_u2d0;
        int i;
        Object objTransformAndWrite;
        CompletableDeferred<T> completableDeferred2;
        Object objTransformAndWrite2;
        int i2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(this, continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(this, continuation);
        }
        Object $result = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (anonymousClass1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    ack = update.getAck();
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        $this$handleUpdate_u24lambda_u2d0 = this;
                        State<T> value = $this$handleUpdate_u24lambda_u2d0.downstreamFlow.getValue();
                        boolean z = true;
                        if (value instanceof Data) {
                            Function2<T, Continuation<? super T>, Object> transform = update.getTransform();
                            CoroutineContext callerContext = update.getCallerContext();
                            anonymousClass1.L$0 = ack;
                            anonymousClass1.label = 1;
                            objTransformAndWrite = $this$handleUpdate_u24lambda_u2d0.transformAndWrite(transform, callerContext, anonymousClass1);
                            if (objTransformAndWrite == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            completableDeferred2 = ack;
                            completableDeferred = completableDeferred2;
                            objM162constructorimpl = Result.m162constructorimpl(objTransformAndWrite);
                            CompletableDeferredKt.completeWith(completableDeferred, objM162constructorimpl);
                            return Unit.INSTANCE;
                        }
                        if (!(value instanceof ReadException)) {
                            z = value instanceof UnInitialized;
                        }
                        if (!z) {
                            if (value instanceof Final) {
                                throw ((Final) value).getFinalException();
                            }
                            throw new NoWhenBranchMatchedException();
                        }
                        if (value != update.getLastState()) {
                            throw ((ReadException) value).getReadException();
                        }
                        anonymousClass1.L$0 = update;
                        anonymousClass1.L$1 = $this$handleUpdate_u24lambda_u2d0;
                        anonymousClass1.L$2 = ack;
                        anonymousClass1.label = 2;
                        if ($this$handleUpdate_u24lambda_u2d0.readAndInitOrPropagateAndThrowFailure(anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i = 0;
                        Function2<T, Continuation<? super T>, Object> transform2 = update.getTransform();
                        CoroutineContext callerContext2 = update.getCallerContext();
                        anonymousClass1.L$0 = ack;
                        anonymousClass1.L$1 = null;
                        anonymousClass1.L$2 = null;
                        anonymousClass1.label = 3;
                        objTransformAndWrite2 = $this$handleUpdate_u24lambda_u2d0.transformAndWrite(transform2, callerContext2, anonymousClass1);
                        if (objTransformAndWrite2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i2 = i;
                        completableDeferred = ack;
                        objTransformAndWrite = objTransformAndWrite2;
                        objM162constructorimpl = Result.m162constructorimpl(objTransformAndWrite);
                        CompletableDeferredKt.completeWith(completableDeferred, objM162constructorimpl);
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        th = th;
                        completableDeferred = ack;
                        Result.Companion companion2 = Result.INSTANCE;
                        objM162constructorimpl = Result.m162constructorimpl(ResultKt.createFailure(th));
                    }
                    break;
                case 1:
                    completableDeferred2 = (CompletableDeferred) anonymousClass1.L$0;
                    ResultKt.throwOnFailure($result);
                    objTransformAndWrite = $result;
                    completableDeferred = completableDeferred2;
                    objM162constructorimpl = Result.m162constructorimpl(objTransformAndWrite);
                    CompletableDeferredKt.completeWith(completableDeferred, objM162constructorimpl);
                    return Unit.INSTANCE;
                case 2:
                    i = 0;
                    CompletableDeferred<T> completableDeferred3 = (CompletableDeferred) anonymousClass1.L$2;
                    SingleProcessDataStore<T> $this$handleUpdate_u24lambda_u2d1 = (SingleProcessDataStore) anonymousClass1.L$1;
                    update = (Message.Update) anonymousClass1.L$0;
                    ResultKt.throwOnFailure($result);
                    $this$handleUpdate_u24lambda_u2d0 = $this$handleUpdate_u24lambda_u2d1;
                    ack = completableDeferred3;
                    Function2<T, Continuation<? super T>, Object> transform3 = update.getTransform();
                    CoroutineContext callerContext3 = update.getCallerContext();
                    anonymousClass1.L$0 = ack;
                    anonymousClass1.L$1 = null;
                    anonymousClass1.L$2 = null;
                    anonymousClass1.label = 3;
                    objTransformAndWrite2 = $this$handleUpdate_u24lambda_u2d0.transformAndWrite(transform3, callerContext3, anonymousClass1);
                    if (objTransformAndWrite2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    i2 = i;
                    completableDeferred = ack;
                    objTransformAndWrite = objTransformAndWrite2;
                    objM162constructorimpl = Result.m162constructorimpl(objTransformAndWrite);
                    CompletableDeferredKt.completeWith(completableDeferred, objM162constructorimpl);
                    return Unit.INSTANCE;
                case 3:
                    i2 = 0;
                    completableDeferred = (CompletableDeferred) anonymousClass1.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        objTransformAndWrite = $result;
                        objM162constructorimpl = Result.m162constructorimpl(objTransformAndWrite);
                        break;
                    } catch (Throwable th2) {
                        th = th2;
                        Result.Companion companion3 = Result.INSTANCE;
                        objM162constructorimpl = Result.m162constructorimpl(ResultKt.createFailure(th));
                    }
                    CompletableDeferredKt.completeWith(completableDeferred, objM162constructorimpl);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th3) {
            th = th3;
            completableDeferred = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object readAndInitOrPropagateAndThrowFailure(Continuation<? super Unit> continuation) throws Throwable {
        C00371 c00371;
        Throwable throwable;
        SingleProcessDataStore singleProcessDataStore;
        if (continuation instanceof C00371) {
            c00371 = (C00371) continuation;
            if ((c00371.label & Integer.MIN_VALUE) != 0) {
                c00371.label -= Integer.MIN_VALUE;
            } else {
                c00371 = new C00371(this, continuation);
            }
        } else {
            c00371 = new C00371(this, continuation);
        }
        Object $result = c00371.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c00371.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    c00371.L$0 = this;
                    c00371.label = 1;
                    return readAndInit(c00371) == coroutine_suspended ? coroutine_suspended : Unit.INSTANCE;
                } catch (Throwable th) {
                    throwable = th;
                    singleProcessDataStore = this;
                    singleProcessDataStore.downstreamFlow.setValue(new ReadException(throwable));
                    throw throwable;
                }
            case 1:
                singleProcessDataStore = (SingleProcessDataStore) c00371.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                } catch (Throwable th2) {
                    throwable = th2;
                    singleProcessDataStore.downstreamFlow.setValue(new ReadException(throwable));
                    throw throwable;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object readAndInitOrPropagateFailure(Continuation<? super Unit> continuation) {
        C00381 c00381;
        Throwable throwable;
        SingleProcessDataStore singleProcessDataStore;
        if (continuation instanceof C00381) {
            c00381 = (C00381) continuation;
            if ((c00381.label & Integer.MIN_VALUE) != 0) {
                c00381.label -= Integer.MIN_VALUE;
            } else {
                c00381 = new C00381(this, continuation);
            }
        } else {
            c00381 = new C00381(this, continuation);
        }
        Object $result = c00381.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c00381.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    c00381.L$0 = this;
                    c00381.label = 1;
                    return readAndInit(c00381) == coroutine_suspended ? coroutine_suspended : Unit.INSTANCE;
                } catch (Throwable th) {
                    throwable = th;
                    singleProcessDataStore = this;
                    singleProcessDataStore.downstreamFlow.setValue(new ReadException(throwable));
                }
                break;
            case 1:
                singleProcessDataStore = (SingleProcessDataStore) c00381.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                } catch (Throwable th2) {
                    throwable = th2;
                    singleProcessDataStore.downstreamFlow.setValue(new ReadException(throwable));
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:29:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:30:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:33:0x00da  */
    /* JADX WARN: Code duplicated, block: B:40:0x0116 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:41:0x0117  */
    /* JADX WARN: Code duplicated, block: B:46:0x012c  */
    /* JADX WARN: Code duplicated, block: B:57:0x00f5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:? A[LOOP:0: B:31:0x00d4->B:59:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.datastore.core.SingleProcessDataStore, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [androidx.datastore.core.SingleProcessDataStore] */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r12v0, types: [androidx.datastore.core.SingleProcessDataStore, androidx.datastore.core.SingleProcessDataStore<T>, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [androidx.datastore.core.SingleProcessDataStore] */
    /* JADX WARN: Type inference failed for: r8v9 */
    public final Object readAndInit(Continuation<? super Unit> continuation) {
        C00361 c00361;
        Mutex mutexMutex$default;
        Ref.ObjectRef objectRef;
        ?? r8;
        Ref.ObjectRef objectRef2;
        Ref.BooleanRef booleanRef;
        SingleProcessDataStore$readAndInit$api$1 singleProcessDataStore$readAndInit$api$1;
        List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> list;
        ?? r10;
        Ref.ObjectRef objectRef3;
        SingleProcessDataStore$readAndInit$api$1 singleProcessDataStore$readAndInit$api$2;
        Iterator<T> it;
        Mutex mutex;
        Ref.BooleanRef booleanRef2;
        Mutex mutex2;
        ?? r0;
        Ref.BooleanRef booleanRef3;
        Function2 function2;
        ?? r1;
        if (continuation instanceof C00361) {
            c00361 = (C00361) continuation;
            if ((c00361.label & Integer.MIN_VALUE) != 0) {
                c00361.label -= Integer.MIN_VALUE;
            } else {
                c00361 = new C00361(this, continuation);
            }
        } else {
            c00361 = new C00361(this, continuation);
        }
        T t = (T) c00361.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c00361.label) {
            case 0:
                ResultKt.throwOnFailure(t);
                if (!(Intrinsics.areEqual(this.downstreamFlow.getValue(), UnInitialized.INSTANCE) || (this.downstreamFlow.getValue() instanceof ReadException))) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                mutexMutex$default = MutexKt.Mutex$default(false, 1, null);
                objectRef = new Ref.ObjectRef();
                c00361.L$0 = this;
                c00361.L$1 = mutexMutex$default;
                c00361.L$2 = objectRef;
                c00361.L$3 = objectRef;
                c00361.label = 1;
                t = (T) readDataOrHandleCorruption(c00361);
                if (t == coroutine_suspended) {
                    return coroutine_suspended;
                }
                r8 = this;
                objectRef2 = objectRef;
                objectRef.element = t;
                booleanRef = new Ref.BooleanRef();
                singleProcessDataStore$readAndInit$api$1 = new SingleProcessDataStore$readAndInit$api$1(mutexMutex$default, booleanRef, objectRef2, r8);
                list = r8.initTasks;
                if (list == null) {
                    mutex2 = mutexMutex$default;
                    booleanRef2 = booleanRef;
                    r0 = r8;
                } else {
                    r10 = r8;
                    objectRef3 = objectRef2;
                    singleProcessDataStore$readAndInit$api$2 = singleProcessDataStore$readAndInit$api$1;
                    it = list.iterator();
                    mutex = mutexMutex$default;
                    booleanRef2 = booleanRef;
                    while (it.hasNext()) {
                        function2 = (Function2) it.next();
                        c00361.L$0 = r10;
                        c00361.L$1 = mutex;
                        c00361.L$2 = objectRef3;
                        c00361.L$3 = booleanRef2;
                        c00361.L$4 = singleProcessDataStore$readAndInit$api$2;
                        c00361.L$5 = it;
                        c00361.label = 2;
                        if (function2.invoke(singleProcessDataStore$readAndInit$api$2, c00361) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    objectRef2 = objectRef3;
                    mutex2 = mutex;
                    r0 = r10;
                }
                r0.initTasks = null;
                c00361.L$0 = r0;
                c00361.L$1 = objectRef2;
                c00361.L$2 = booleanRef2;
                c00361.L$3 = mutex2;
                c00361.L$4 = null;
                c00361.L$5 = null;
                c00361.label = 3;
                if (mutex2.lock(null, c00361) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                booleanRef3 = booleanRef2;
                r1 = r0;
                try {
                    booleanRef3.element = true;
                    Unit unit = Unit.INSTANCE;
                    mutex2.unlock(null);
                    MutableStateFlow<State<T>> mutableStateFlow = r1.downstreamFlow;
                    T t2 = objectRef2.element;
                    T t3 = objectRef2.element;
                    mutableStateFlow.setValue(new Data(t2, t3 != null ? t3.hashCode() : 0));
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    mutex2.unlock(null);
                    throw th;
                }
            case 1:
                objectRef = (Ref.ObjectRef) c00361.L$3;
                objectRef2 = (Ref.ObjectRef) c00361.L$2;
                mutexMutex$default = (Mutex) c00361.L$1;
                SingleProcessDataStore singleProcessDataStore = (SingleProcessDataStore) c00361.L$0;
                ResultKt.throwOnFailure(t);
                r8 = singleProcessDataStore;
                objectRef.element = t;
                booleanRef = new Ref.BooleanRef();
                singleProcessDataStore$readAndInit$api$1 = new SingleProcessDataStore$readAndInit$api$1(mutexMutex$default, booleanRef, objectRef2, r8);
                list = r8.initTasks;
                if (list == null) {
                    mutex2 = mutexMutex$default;
                    booleanRef2 = booleanRef;
                    r0 = r8;
                } else {
                    r10 = r8;
                    objectRef3 = objectRef2;
                    singleProcessDataStore$readAndInit$api$2 = singleProcessDataStore$readAndInit$api$1;
                    it = list.iterator();
                    mutex = mutexMutex$default;
                    booleanRef2 = booleanRef;
                    while (it.hasNext()) {
                        function2 = (Function2) it.next();
                        c00361.L$0 = r10;
                        c00361.L$1 = mutex;
                        c00361.L$2 = objectRef3;
                        c00361.L$3 = booleanRef2;
                        c00361.L$4 = singleProcessDataStore$readAndInit$api$2;
                        c00361.L$5 = it;
                        c00361.label = 2;
                        if (function2.invoke(singleProcessDataStore$readAndInit$api$2, c00361) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    objectRef2 = objectRef3;
                    mutex2 = mutex;
                    r0 = r10;
                }
                r0.initTasks = null;
                c00361.L$0 = r0;
                c00361.L$1 = objectRef2;
                c00361.L$2 = booleanRef2;
                c00361.L$3 = mutex2;
                c00361.L$4 = null;
                c00361.L$5 = null;
                c00361.label = 3;
                if (mutex2.lock(null, c00361) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                booleanRef3 = booleanRef2;
                r1 = r0;
                booleanRef3.element = true;
                Unit unit2 = Unit.INSTANCE;
                mutex2.unlock(null);
                MutableStateFlow<State<T>> mutableStateFlow2 = r1.downstreamFlow;
                T t4 = objectRef2.element;
                T t5 = objectRef2.element;
                mutableStateFlow2.setValue(new Data(t4, t5 != null ? t5.hashCode() : 0));
                return Unit.INSTANCE;
            case 2:
                it = (Iterator) c00361.L$5;
                singleProcessDataStore$readAndInit$api$2 = (SingleProcessDataStore$readAndInit$api$1) c00361.L$4;
                booleanRef2 = (Ref.BooleanRef) c00361.L$3;
                objectRef3 = (Ref.ObjectRef) c00361.L$2;
                mutex = (Mutex) c00361.L$1;
                SingleProcessDataStore singleProcessDataStore2 = (SingleProcessDataStore) c00361.L$0;
                ResultKt.throwOnFailure(t);
                r10 = singleProcessDataStore2;
                while (it.hasNext()) {
                    function2 = (Function2) it.next();
                    c00361.L$0 = r10;
                    c00361.L$1 = mutex;
                    c00361.L$2 = objectRef3;
                    c00361.L$3 = booleanRef2;
                    c00361.L$4 = singleProcessDataStore$readAndInit$api$2;
                    c00361.L$5 = it;
                    c00361.label = 2;
                    if (function2.invoke(singleProcessDataStore$readAndInit$api$2, c00361) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                objectRef2 = objectRef3;
                mutex2 = mutex;
                r0 = r10;
                r0.initTasks = null;
                c00361.L$0 = r0;
                c00361.L$1 = objectRef2;
                c00361.L$2 = booleanRef2;
                c00361.L$3 = mutex2;
                c00361.L$4 = null;
                c00361.L$5 = null;
                c00361.label = 3;
                if (mutex2.lock(null, c00361) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                booleanRef3 = booleanRef2;
                r1 = r0;
                booleanRef3.element = true;
                Unit unit3 = Unit.INSTANCE;
                mutex2.unlock(null);
                MutableStateFlow<State<T>> mutableStateFlow3 = r1.downstreamFlow;
                T t6 = objectRef2.element;
                T t7 = objectRef2.element;
                mutableStateFlow3.setValue(new Data(t6, t7 != null ? t7.hashCode() : 0));
                return Unit.INSTANCE;
            case 3:
                mutex2 = (Mutex) c00361.L$3;
                booleanRef3 = (Ref.BooleanRef) c00361.L$2;
                objectRef2 = (Ref.ObjectRef) c00361.L$1;
                SingleProcessDataStore singleProcessDataStore3 = (SingleProcessDataStore) c00361.L$0;
                ResultKt.throwOnFailure(t);
                r1 = singleProcessDataStore3;
                booleanRef3.element = true;
                Unit unit4 = Unit.INSTANCE;
                mutex2.unlock(null);
                MutableStateFlow<State<T>> mutableStateFlow4 = r1.downstreamFlow;
                T t8 = objectRef2.element;
                T t9 = objectRef2.element;
                mutableStateFlow4.setValue(new Data(t8, t9 != null ? t9.hashCode() : 0));
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v7 */
    public final Object readDataOrHandleCorruption(Continuation<? super T> continuation) throws IOException {
        C00401 c00401;
        Object objHandleCorruption;
        SingleProcessDataStore singleProcessDataStore;
        CorruptionException corruptionException;
        CorruptionException corruptionException2;
        Object data;
        if (continuation instanceof C00401) {
            c00401 = (C00401) continuation;
            if ((c00401.label & Integer.MIN_VALUE) != 0) {
                c00401.label -= Integer.MIN_VALUE;
            } else {
                c00401 = new C00401(this, continuation);
            }
        } else {
            c00401 = new C00401(this, continuation);
        }
        Object obj = c00401.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        SingleProcessDataStore singleProcessDataStore2 = c00401.label;
        try {
            switch (singleProcessDataStore2) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    SingleProcessDataStore singleProcessDataStore3 = this;
                    c00401.L$0 = singleProcessDataStore3;
                    c00401.label = 1;
                    data = singleProcessDataStore3.readData(c00401);
                    singleProcessDataStore2 = singleProcessDataStore3;
                    if (data == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return data;
                case 1:
                    SingleProcessDataStore singleProcessDataStore4 = (SingleProcessDataStore) c00401.L$0;
                    ResultKt.throwOnFailure(obj);
                    data = obj;
                    singleProcessDataStore2 = singleProcessDataStore4;
                    return data;
                case 2:
                    corruptionException = (CorruptionException) c00401.L$1;
                    SingleProcessDataStore singleProcessDataStore5 = (SingleProcessDataStore) c00401.L$0;
                    ResultKt.throwOnFailure(obj);
                    objHandleCorruption = obj;
                    singleProcessDataStore = singleProcessDataStore5;
                    try {
                        c00401.L$0 = corruptionException;
                        c00401.L$1 = objHandleCorruption;
                        c00401.label = 3;
                        return singleProcessDataStore.writeData$datastore_core(objHandleCorruption, c00401) == coroutine_suspended ? coroutine_suspended : objHandleCorruption;
                    } catch (IOException e) {
                        e = e;
                        corruptionException2 = corruptionException;
                        ExceptionsKt.addSuppressed(corruptionException2, e);
                        throw corruptionException2;
                    }
                case 3:
                    Object obj2 = c00401.L$1;
                    corruptionException2 = (CorruptionException) c00401.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        return obj2;
                    } catch (IOException e2) {
                        e = e2;
                        ExceptionsKt.addSuppressed(corruptionException2, e);
                        throw corruptionException2;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (CorruptionException e3) {
            CorruptionHandler<T> corruptionHandler = singleProcessDataStore2.corruptionHandler;
            c00401.L$0 = singleProcessDataStore2;
            c00401.L$1 = e3;
            c00401.label = 2;
            objHandleCorruption = corruptionHandler.handleCorruption(e3, c00401);
            if (objHandleCorruption == coroutine_suspended) {
                return coroutine_suspended;
            }
            singleProcessDataStore = singleProcessDataStore2;
            corruptionException = e3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v11, types: [androidx.datastore.core.SingleProcessDataStore] */
    /* JADX WARN: Type inference failed for: r1v2, types: [androidx.datastore.core.SingleProcessDataStore] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    public final Object readData(Continuation<? super T> continuation) throws IOException {
        C00391 c00391;
        FileInputStream fileInputStream;
        Throwable th;
        Throwable th2;
        Object from;
        if (continuation instanceof C00391) {
            c00391 = (C00391) continuation;
            if ((c00391.label & Integer.MIN_VALUE) != 0) {
                c00391.label -= Integer.MIN_VALUE;
            } else {
                c00391 = new C00391(this, continuation);
            }
        } else {
            c00391 = new C00391(this, continuation);
        }
        Object $result = c00391.result;
        ?? coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c00391.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    try {
                        fileInputStream = new FileInputStream(getFile());
                        th = null;
                        try {
                            FileInputStream stream = fileInputStream;
                            c00391.L$0 = this;
                            c00391.L$1 = fileInputStream;
                            c00391.L$2 = null;
                            c00391.label = 1;
                            from = this.serializer.readFrom(stream, c00391);
                            if (from == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            CloseableKt.closeFinally(fileInputStream, th);
                            return from;
                        } catch (Throwable th3) {
                            th2 = th3;
                            coroutine_suspended = this;
                            try {
                                throw th2;
                            } catch (Throwable th4) {
                                CloseableKt.closeFinally(fileInputStream, th2);
                                throw th4;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        ex = e;
                        coroutine_suspended = this;
                        if (coroutine_suspended.getFile().exists()) {
                            throw ex;
                        }
                        return coroutine_suspended.serializer.getDefaultValue();
                    }
                case 1:
                    th = (Throwable) c00391.L$2;
                    fileInputStream = (Closeable) c00391.L$1;
                    coroutine_suspended = (SingleProcessDataStore) c00391.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        from = $result;
                        CloseableKt.closeFinally(fileInputStream, th);
                        return from;
                    } catch (Throwable th5) {
                        th2 = th5;
                        throw th2;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (FileNotFoundException e2) {
            ex = e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x0083  */
    /* JADX WARN: Code duplicated, block: B:23:0x0092 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x0093  */
    /* JADX WARN: Code duplicated, block: B:27:0x009a  */
    /* JADX WARN: Code duplicated, block: B:28:0x009f  */
    /* JADX WARN: Code duplicated, block: B:31:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object transformAndWrite(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, CoroutineContext coroutineContext, Continuation<? super T> continuation) {
        C00411 c00411;
        Data data;
        Object objWithContext;
        Object obj;
        SingleProcessDataStore singleProcessDataStore;
        SingleProcessDataStore singleProcessDataStore2;
        int iHashCode;
        if (continuation instanceof C00411) {
            c00411 = (C00411) continuation;
            if ((c00411.label & Integer.MIN_VALUE) != 0) {
                c00411.label -= Integer.MIN_VALUE;
            } else {
                c00411 = new C00411(this, continuation);
            }
        } else {
            c00411 = new C00411(this, continuation);
        }
        Object obj2 = c00411.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c00411.label) {
            case 0:
                ResultKt.throwOnFailure(obj2);
                SingleProcessDataStore singleProcessDataStore3 = this;
                data = (Data) singleProcessDataStore3.downstreamFlow.getValue();
                data.checkHashCode();
                Object value = data.getValue();
                SingleProcessDataStore$transformAndWrite$newData$1 singleProcessDataStore$transformAndWrite$newData$1 = new SingleProcessDataStore$transformAndWrite$newData$1(function2, value, null);
                c00411.L$0 = singleProcessDataStore3;
                c00411.L$1 = data;
                c00411.L$2 = value;
                c00411.label = 1;
                objWithContext = BuildersKt.withContext(coroutineContext, singleProcessDataStore$transformAndWrite$newData$1, c00411);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = value;
                singleProcessDataStore = singleProcessDataStore3;
                data.checkHashCode();
                if (Intrinsics.areEqual(obj, objWithContext)) {
                    return obj;
                }
                c00411.L$0 = singleProcessDataStore;
                c00411.L$1 = objWithContext;
                c00411.L$2 = null;
                c00411.label = 2;
                if (singleProcessDataStore.writeData$datastore_core(objWithContext, c00411) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                singleProcessDataStore2 = singleProcessDataStore;
                MutableStateFlow<State<T>> mutableStateFlow = singleProcessDataStore2.downstreamFlow;
                if (objWithContext != null) {
                    iHashCode = objWithContext.hashCode();
                } else {
                    iHashCode = 0;
                }
                mutableStateFlow.setValue(new Data(objWithContext, iHashCode));
                return objWithContext;
            case 1:
                obj = c00411.L$2;
                Data data2 = (Data) c00411.L$1;
                SingleProcessDataStore singleProcessDataStore4 = (SingleProcessDataStore) c00411.L$0;
                ResultKt.throwOnFailure(obj2);
                data = data2;
                singleProcessDataStore = singleProcessDataStore4;
                objWithContext = obj2;
                data.checkHashCode();
                if (Intrinsics.areEqual(obj, objWithContext)) {
                    return obj;
                }
                c00411.L$0 = singleProcessDataStore;
                c00411.L$1 = objWithContext;
                c00411.L$2 = null;
                c00411.label = 2;
                if (singleProcessDataStore.writeData$datastore_core(objWithContext, c00411) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                singleProcessDataStore2 = singleProcessDataStore;
                MutableStateFlow<State<T>> mutableStateFlow2 = singleProcessDataStore2.downstreamFlow;
                if (objWithContext != null) {
                    iHashCode = objWithContext.hashCode();
                } else {
                    iHashCode = 0;
                }
                mutableStateFlow2.setValue(new Data(objWithContext, iHashCode));
                return objWithContext;
            case 2:
                objWithContext = c00411.L$1;
                SingleProcessDataStore singleProcessDataStore5 = (SingleProcessDataStore) c00411.L$0;
                ResultKt.throwOnFailure(obj2);
                singleProcessDataStore2 = singleProcessDataStore5;
                MutableStateFlow<State<T>> mutableStateFlow3 = singleProcessDataStore2.downstreamFlow;
                if (objWithContext != null) {
                    iHashCode = objWithContext.hashCode();
                } else {
                    iHashCode = 0;
                }
                mutableStateFlow3.setValue(new Data(objWithContext, iHashCode));
                return objWithContext;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:28:0x00b8 A[Catch: IOException -> 0x00df, TRY_ENTER, TryCatch #3 {IOException -> 0x00df, blocks: (B:24:0x00a8, B:28:0x00b8, B:29:0x00d6, B:34:0x00db, B:35:0x00de, B:32:0x00d9), top: B:49:0x0021, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v10, types: [androidx.datastore.core.SingleProcessDataStore] */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7, types: [androidx.datastore.core.SingleProcessDataStore] */
    /* JADX WARN: Type inference failed for: r2v1 */
    public final Object writeData$datastore_core(T t, Continuation<? super Unit> continuation) throws IOException {
        SingleProcessDataStore$writeData$1 singleProcessDataStore$writeData$1;
        File file;
        FileOutputStream fileOutputStream;
        Throwable th;
        FileOutputStream fileOutputStream2;
        if (continuation instanceof SingleProcessDataStore$writeData$1) {
            singleProcessDataStore$writeData$1 = (SingleProcessDataStore$writeData$1) continuation;
            if ((singleProcessDataStore$writeData$1.label & Integer.MIN_VALUE) != 0) {
                singleProcessDataStore$writeData$1.label -= Integer.MIN_VALUE;
            } else {
                singleProcessDataStore$writeData$1 = new SingleProcessDataStore$writeData$1(this, continuation);
            }
        } else {
            singleProcessDataStore$writeData$1 = new SingleProcessDataStore$writeData$1(this, continuation);
        }
        Object obj = singleProcessDataStore$writeData$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (singleProcessDataStore$writeData$1.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    createParentDirectories(getFile());
                    file = new File(Intrinsics.stringPlus(getFile().getAbsolutePath(), this.SCRATCH_SUFFIX));
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        th = null;
                        try {
                            FileOutputStream fileOutputStream3 = fileOutputStream;
                            Serializer<T> serializer = this.serializer;
                            UncloseableOutputStream uncloseableOutputStream = new UncloseableOutputStream(fileOutputStream3);
                            singleProcessDataStore$writeData$1.L$0 = this;
                            singleProcessDataStore$writeData$1.L$1 = file;
                            singleProcessDataStore$writeData$1.L$2 = fileOutputStream;
                            singleProcessDataStore$writeData$1.L$3 = null;
                            singleProcessDataStore$writeData$1.L$4 = fileOutputStream3;
                            singleProcessDataStore$writeData$1.label = 1;
                            if (serializer.writeTo(t, uncloseableOutputStream, singleProcessDataStore$writeData$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            t = this;
                            fileOutputStream2 = fileOutputStream3;
                            fileOutputStream2.getFD().sync();
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(fileOutputStream, th);
                            if (file.renameTo(((SingleProcessDataStore) t).getFile())) {
                                return Unit.INSTANCE;
                            }
                            throw new IOException("Unable to rename " + file + ".This likely means that there are multiple instances of DataStore for this file. Ensure that you are only creating a single instance of datastore for this file.");
                        } catch (Throwable th2) {
                            th = th2;
                            t = this;
                            try {
                                throw th;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(fileOutputStream, th);
                                throw th3;
                            }
                        }
                    } catch (IOException e) {
                        e = e;
                        if (file.exists()) {
                            file.delete();
                        }
                        throw e;
                    }
                case 1:
                    fileOutputStream2 = (FileOutputStream) singleProcessDataStore$writeData$1.L$4;
                    th = (Throwable) singleProcessDataStore$writeData$1.L$3;
                    fileOutputStream = (Closeable) singleProcessDataStore$writeData$1.L$2;
                    file = (File) singleProcessDataStore$writeData$1.L$1;
                    t = (T) ((SingleProcessDataStore) singleProcessDataStore$writeData$1.L$0);
                    try {
                        ResultKt.throwOnFailure(obj);
                        t = t;
                        fileOutputStream2.getFD().sync();
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream, th);
                        if (file.renameTo(((SingleProcessDataStore) t).getFile())) {
                            return Unit.INSTANCE;
                        }
                        throw new IOException("Unable to rename " + file + ".This likely means that there are multiple instances of DataStore for this file. Ensure that you are only creating a single instance of datastore for this file.");
                    } catch (Throwable th4) {
                        th = th4;
                        throw th;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (IOException e2) {
            e = e2;
            if (file.exists()) {
                file.delete();
            }
            throw e;
        }
    }

    private final void createParentDirectories(File $this$createParentDirectories) throws IOException {
        File parent = $this$createParentDirectories.getCanonicalFile().getParentFile();
        if (parent != null) {
            parent.mkdirs();
            if (!parent.isDirectory()) {
                throw new IOException(Intrinsics.stringPlus("Unable to create parent directories of ", $this$createParentDirectories));
            }
        }
    }

    /* JADX INFO: compiled from: SingleProcessDataStore.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\n\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore$UncloseableOutputStream;", "Ljava/io/OutputStream;", "fileOutputStream", "Ljava/io/FileOutputStream;", "(Ljava/io/FileOutputStream;)V", "getFileOutputStream", "()Ljava/io/FileOutputStream;", "close", "", "flush", "write", "b", "", "bytes", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "", "len", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class UncloseableOutputStream extends OutputStream {
        private final FileOutputStream fileOutputStream;

        public UncloseableOutputStream(FileOutputStream fileOutputStream) {
            Intrinsics.checkNotNullParameter(fileOutputStream, "fileOutputStream");
            this.fileOutputStream = fileOutputStream;
        }

        public final FileOutputStream getFileOutputStream() {
            return this.fileOutputStream;
        }

        @Override // java.io.OutputStream
        public void write(int b) throws IOException {
            this.fileOutputStream.write(b);
        }

        @Override // java.io.OutputStream
        public void write(byte[] b) throws IOException {
            Intrinsics.checkNotNullParameter(b, "b");
            this.fileOutputStream.write(b);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bytes, int off, int len) throws IOException {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            this.fileOutputStream.write(bytes, off, len);
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
            this.fileOutputStream.flush();
        }
    }

    /* JADX INFO: compiled from: SingleProcessDataStore.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore$Companion;", "", "()V", "activeFiles", "", "", "getActiveFiles$datastore_core", "()Ljava/util/Set;", "activeFilesLock", "getActiveFilesLock$datastore_core", "()Ljava/lang/Object;", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getActiveFiles$datastore_core() {
            return SingleProcessDataStore.activeFiles;
        }

        public final Object getActiveFilesLock$datastore_core() {
            return SingleProcessDataStore.activeFilesLock;
        }
    }
}
