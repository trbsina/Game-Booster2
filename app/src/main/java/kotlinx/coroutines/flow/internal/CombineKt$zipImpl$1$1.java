package kotlinx.coroutines.flow.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: Combine.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1", f = "Combine.kt", i = {0}, l = {126}, m = "invokeSuspend", n = {"second"}, s = {"L$0"})
final class CombineKt$zipImpl$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<T1> $flow;
    final /* synthetic */ Flow<T2> $flow2;
    final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
    final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CombineKt$zipImpl$1$1(FlowCollector<? super R> flowCollector, Flow<? extends T2> flow, Flow<? extends T1> flow2, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super CombineKt$zipImpl$1$1> continuation) {
        super(2, continuation);
        this.$this_unsafeFlow = flowCollector;
        this.$flow2 = flow;
        this.$flow = flow2;
        this.$transform = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$zipImpl$1$1 combineKt$zipImpl$1$1 = new CombineKt$zipImpl$1$1(this.$this_unsafeFlow, this.$flow2, this.$flow, this.$transform, continuation);
        combineKt$zipImpl$1$1.L$0 = obj;
        return combineKt$zipImpl$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        ReceiveChannel receiveChannelProduce$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        CombineKt$zipImpl$1$1 combineKt$zipImpl$1$1 = this.label;
        try {
            try {
                switch (combineKt$zipImpl$1$1) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        combineKt$zipImpl$1$1 = this;
                        CoroutineScope coroutineScope = (CoroutineScope) combineKt$zipImpl$1$1.L$0;
                        receiveChannelProduce$default = ProduceKt.produce$default(coroutineScope, null, 0, new CombineKt$zipImpl$1$1$second$1(combineKt$zipImpl$1$1.$flow2, null), 3, null);
                        final CompletableJob completableJobJob$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
                        Intrinsics.checkNotNull(receiveChannelProduce$default, "null cannot be cast to non-null type kotlinx.coroutines.channels.SendChannel<*>");
                        final FlowCollector<R> flowCollector = combineKt$zipImpl$1$1.$this_unsafeFlow;
                        ((SendChannel) receiveChannelProduce$default).invokeOnClose(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable it) {
                                if (completableJobJob$default.isActive()) {
                                    completableJobJob$default.cancel((CancellationException) new AbortFlowException(flowCollector));
                                }
                            }
                        });
                        CoroutineContext coroutineContext = coroutineScope.getCoroutineContext();
                        Object objThreadContextElements = ThreadContextKt.threadContextElements(coroutineContext);
                        CoroutineContext coroutineContextPlus = coroutineScope.getCoroutineContext().plus(completableJobJob$default);
                        Unit unit = Unit.INSTANCE;
                        try {
                            combineKt$zipImpl$1$1.L$0 = receiveChannelProduce$default;
                            combineKt$zipImpl$1$1.label = 1;
                            Object objWithContextUndispatched$default = ChannelFlowKt.withContextUndispatched$default(coroutineContextPlus, unit, null, new AnonymousClass2(combineKt$zipImpl$1$1.$flow, coroutineContext, objThreadContextElements, receiveChannelProduce$default, combineKt$zipImpl$1$1.$this_unsafeFlow, combineKt$zipImpl$1$1.$transform, null), combineKt$zipImpl$1$1, 4, null);
                            combineKt$zipImpl$1$1 = combineKt$zipImpl$1$1;
                            if (objWithContextUndispatched$default == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        } catch (AbortFlowException e) {
                            e = e;
                            receiveChannelProduce$default = receiveChannelProduce$default;
                            FlowExceptions_commonKt.checkOwnership(e, combineKt$zipImpl$1$1.$this_unsafeFlow);
                            break;
                        } catch (Throwable th) {
                            th = th;
                            receiveChannelProduce$default = receiveChannelProduce$default;
                            ReceiveChannel.DefaultImpls.cancel$default(receiveChannelProduce$default, (CancellationException) null, 1, (Object) null);
                            throw th;
                        }
                        ReceiveChannel.DefaultImpls.cancel$default(receiveChannelProduce$default, (CancellationException) null, 1, (Object) null);
                        return Unit.INSTANCE;
                    case 1:
                        CombineKt$zipImpl$1$1 combineKt$zipImpl$1$2 = this;
                        receiveChannelProduce$default = (ReceiveChannel) combineKt$zipImpl$1$2.L$0;
                        ResultKt.throwOnFailure(obj);
                        combineKt$zipImpl$1$1 = combineKt$zipImpl$1$2;
                        ReceiveChannel.DefaultImpls.cancel$default(receiveChannelProduce$default, (CancellationException) null, 1, (Object) null);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (AbortFlowException e2) {
            e = e2;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: Combine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "it"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2", f = "Combine.kt", i = {}, l = {WorkQueueKt.MASK}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
        final /* synthetic */ Object $cnt;
        final /* synthetic */ Flow<T1> $flow;
        final /* synthetic */ CoroutineContext $scopeContext;
        final /* synthetic */ ReceiveChannel<Object> $second;
        final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
        final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Flow<? extends T1> flow, CoroutineContext coroutineContext, Object obj, ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$flow = flow;
            this.$scopeContext = coroutineContext;
            this.$cnt = obj;
            this.$second = receiveChannel;
            this.$this_unsafeFlow = flowCollector;
            this.$transform = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$flow, this.$scopeContext, this.$cnt, this.$second, this.$this_unsafeFlow, this.$transform, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: Combine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        static final class AnonymousClass1<T> implements FlowCollector {
            final /* synthetic */ Object $cnt;
            final /* synthetic */ CoroutineContext $scopeContext;
            final /* synthetic */ ReceiveChannel<Object> $second;
            final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
            final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(CoroutineContext coroutineContext, Object obj, ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
                this.$scopeContext = coroutineContext;
                this.$cnt = obj;
                this.$second = receiveChannel;
                this.$this_unsafeFlow = flowCollector;
                this.$transform = function3;
            }

            /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: Combine.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "it"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1", f = "Combine.kt", i = {}, l = {129, 132, 132}, m = "invokeSuspend", n = {}, s = {})
            static final class C00231 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
                final /* synthetic */ ReceiveChannel<Object> $second;
                final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
                final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
                final /* synthetic */ T1 $value;
                Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00231(ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, T1 t1, Continuation<? super C00231> continuation) {
                    super(2, continuation);
                    this.$second = receiveChannel;
                    this.$this_unsafeFlow = flowCollector;
                    this.$transform = function3;
                    this.$value = t1;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00231(this.$second, this.$this_unsafeFlow, this.$transform, this.$value, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
                    return ((C00231) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:14:0x004b  */
                /* JADX WARN: Code duplicated, block: B:16:0x0052  */
                /* JADX WARN: Code duplicated, block: B:18:0x005a  */
                /* JADX WARN: Code duplicated, block: B:20:0x0067  */
                /* JADX WARN: Code duplicated, block: B:23:0x0074 A[RETURN] */
                /* JADX WARN: Code duplicated, block: B:24:0x0075  */
                /* JADX WARN: Code duplicated, block: B:27:0x0087 A[RETURN] */
                /* JADX WARN: Code duplicated, block: B:28:0x0088  */
                /* JADX WARN: Code duplicated, block: B:31:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r4v4, types: [kotlinx.coroutines.flow.FlowCollector] */
                /* JADX WARN: Type inference failed for: r4v5 */
                /* JADX WARN: Type inference failed for: r4v6 */
                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) throws Throwable {
                    C00231 c00231;
                    Object objMo1668receiveCatchingJP2dKIU;
                    FlowCollector flowCollector;
                    Object obj2;
                    Object objInvoke;
                    C00231 c00232;
                    Throwable thM1678exceptionOrNullimpl;
                    ?? r4;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            c00231 = this;
                            c00231.label = 1;
                            objMo1668receiveCatchingJP2dKIU = c00231.$second.mo1668receiveCatchingJP2dKIU(c00231);
                            if (objMo1668receiveCatchingJP2dKIU == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            flowCollector = c00231.$this_unsafeFlow;
                            if (objMo1668receiveCatchingJP2dKIU instanceof ChannelResult.Failed) {
                                thM1678exceptionOrNullimpl = ChannelResult.m1678exceptionOrNullimpl(objMo1668receiveCatchingJP2dKIU);
                                if (thM1678exceptionOrNullimpl == null) {
                                    throw new AbortFlowException(flowCollector);
                                }
                                throw thM1678exceptionOrNullimpl;
                            }
                            obj2 = c00231.$this_unsafeFlow;
                            Function3 function3 = c00231.$transform;
                            Object obj3 = c00231.$value;
                            if (objMo1668receiveCatchingJP2dKIU == NullSurrogateKt.NULL) {
                                objMo1668receiveCatchingJP2dKIU = null;
                            }
                            c00231.L$0 = obj2;
                            c00231.label = 2;
                            objInvoke = function3.invoke(obj3, objMo1668receiveCatchingJP2dKIU, c00231);
                            if (objInvoke == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = objInvoke;
                            c00232 = c00231;
                            r4 = obj2;
                            c00232.L$0 = null;
                            c00232.label = 3;
                            if (r4.emit(obj, c00232) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        case 1:
                            c00231 = this;
                            ResultKt.throwOnFailure(obj);
                            objMo1668receiveCatchingJP2dKIU = ((ChannelResult) obj).getHolder();
                            flowCollector = c00231.$this_unsafeFlow;
                            if (objMo1668receiveCatchingJP2dKIU instanceof ChannelResult.Failed) {
                                thM1678exceptionOrNullimpl = ChannelResult.m1678exceptionOrNullimpl(objMo1668receiveCatchingJP2dKIU);
                                if (thM1678exceptionOrNullimpl == null) {
                                    throw new AbortFlowException(flowCollector);
                                }
                                throw thM1678exceptionOrNullimpl;
                            }
                            obj2 = c00231.$this_unsafeFlow;
                            Function3 function4 = c00231.$transform;
                            Object obj4 = c00231.$value;
                            if (objMo1668receiveCatchingJP2dKIU == NullSurrogateKt.NULL) {
                                objMo1668receiveCatchingJP2dKIU = null;
                            }
                            c00231.L$0 = obj2;
                            c00231.label = 2;
                            objInvoke = function4.invoke(obj4, objMo1668receiveCatchingJP2dKIU, c00231);
                            if (objInvoke == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = objInvoke;
                            c00232 = c00231;
                            r4 = obj2;
                            c00232.L$0 = null;
                            c00232.label = 3;
                            if (r4.emit(obj, c00232) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        case 2:
                            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            r4 = flowCollector2;
                            c00232 = this;
                            c00232.L$0 = null;
                            c00232.label = 3;
                            if (r4.emit(obj, c00232) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        case 3:
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            /* JADX WARN: Code duplicated, block: B:7:0x0014  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(T1 t1, Continuation<? super Unit> continuation) {
                CombineKt$zipImpl$1$1$2$1$emit$1 combineKt$zipImpl$1$1$2$1$emit$1;
                if (continuation instanceof CombineKt$zipImpl$1$1$2$1$emit$1) {
                    combineKt$zipImpl$1$1$2$1$emit$1 = (CombineKt$zipImpl$1$1$2$1$emit$1) continuation;
                    if ((combineKt$zipImpl$1$1$2$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                        combineKt$zipImpl$1$1$2$1$emit$1.label -= Integer.MIN_VALUE;
                    } else {
                        combineKt$zipImpl$1$1$2$1$emit$1 = new CombineKt$zipImpl$1$1$2$1$emit$1(this, continuation);
                    }
                } else {
                    combineKt$zipImpl$1$1$2$1$emit$1 = new CombineKt$zipImpl$1$1$2$1$emit$1(this, continuation);
                }
                Object $result = combineKt$zipImpl$1$1$2$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (combineKt$zipImpl$1$1$2$1$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineContext coroutineContext = this.$scopeContext;
                        Unit unit = Unit.INSTANCE;
                        Object obj = this.$cnt;
                        C00231 c00231 = new C00231(this.$second, this.$this_unsafeFlow, this.$transform, t1, null);
                        combineKt$zipImpl$1$1$2$1$emit$1.label = 1;
                        if (ChannelFlowKt.withContextUndispatched(coroutineContext, unit, obj, c00231, combineKt$zipImpl$1$1$2$1$emit$1) == coroutine_suspended) {
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

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    Flow<T1> flow = this.$flow;
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$scopeContext, this.$cnt, this.$second, this.$this_unsafeFlow, this.$transform);
                    this.label = 1;
                    if (flow.collect(anonymousClass1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure(obj);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }
}
