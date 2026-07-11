package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.sync.Mutex;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: SingleProcessDataStore.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001JD\u0010\u0002\u001a\u00028\u000021\u0010\u0003\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"androidx/datastore/core/SingleProcessDataStore$readAndInit$api$1", "Landroidx/datastore/core/InitializerApi;", "updateData", "transform", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "t", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SingleProcessDataStore$readAndInit$api$1<T> implements InitializerApi<T> {
    final /* synthetic */ Ref.ObjectRef<T> $initData;
    final /* synthetic */ Ref.BooleanRef $initializationComplete;
    final /* synthetic */ Mutex $updateLock;
    final /* synthetic */ SingleProcessDataStore<T> this$0;

    SingleProcessDataStore$readAndInit$api$1(Mutex $updateLock, Ref.BooleanRef $initializationComplete, Ref.ObjectRef<T> objectRef, SingleProcessDataStore<T> singleProcessDataStore) {
        this.$updateLock = $updateLock;
        this.$initializationComplete = $initializationComplete;
        this.$initData = objectRef;
        this.this$0 = singleProcessDataStore;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00b1 A[Catch: all -> 0x00fd, TRY_LEAVE, TryCatch #2 {all -> 0x00fd, blocks: (B:28:0x00ad, B:30:0x00b1, B:46:0x00f5, B:47:0x00fc), top: B:56:0x00ad }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00c7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:33:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:37:0x00d7 A[Catch: all -> 0x0062, TRY_LEAVE, TryCatch #0 {all -> 0x0062, blocks: (B:18:0x0059, B:35:0x00cf, B:37:0x00d7), top: B:52:0x0059 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00e6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:40:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:46:0x00f5 A[Catch: all -> 0x00fd, TRY_ENTER, TryCatch #2 {all -> 0x00fd, blocks: (B:28:0x00ad, B:30:0x00b1, B:46:0x00f5, B:47:0x00fc), top: B:56:0x00ad }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.datastore.core.InitializerApi
    public Object updateData(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) throws Throwable {
        SingleProcessDataStore$readAndInit$api$1$updateData$1 singleProcessDataStore$readAndInit$api$1$updateData$1;
        Mutex mutex;
        Ref.BooleanRef booleanRef;
        Ref.ObjectRef<T> objectRef;
        Object obj;
        SingleProcessDataStore singleProcessDataStore;
        int i;
        Throwable th;
        Object obj2;
        Object objInvoke;
        SingleProcessDataStore singleProcessDataStore2;
        Ref.ObjectRef<T> objectRef2;
        int i2;
        T t;
        if (continuation instanceof SingleProcessDataStore$readAndInit$api$1$updateData$1) {
            singleProcessDataStore$readAndInit$api$1$updateData$1 = (SingleProcessDataStore$readAndInit$api$1$updateData$1) continuation;
            if ((singleProcessDataStore$readAndInit$api$1$updateData$1.label & Integer.MIN_VALUE) != 0) {
                singleProcessDataStore$readAndInit$api$1$updateData$1.label -= Integer.MIN_VALUE;
            } else {
                singleProcessDataStore$readAndInit$api$1$updateData$1 = new SingleProcessDataStore$readAndInit$api$1$updateData$1(this, continuation);
            }
        } else {
            singleProcessDataStore$readAndInit$api$1$updateData$1 = new SingleProcessDataStore$readAndInit$api$1$updateData$1(this, continuation);
        }
        Object obj3 = singleProcessDataStore$readAndInit$api$1$updateData$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (singleProcessDataStore$readAndInit$api$1$updateData$1.label) {
            case 0:
                ResultKt.throwOnFailure(obj3);
                mutex = this.$updateLock;
                booleanRef = this.$initializationComplete;
                objectRef = this.$initData;
                SingleProcessDataStore singleProcessDataStore3 = this.this$0;
                singleProcessDataStore$readAndInit$api$1$updateData$1.L$0 = function2;
                singleProcessDataStore$readAndInit$api$1$updateData$1.L$1 = mutex;
                singleProcessDataStore$readAndInit$api$1$updateData$1.L$2 = booleanRef;
                singleProcessDataStore$readAndInit$api$1$updateData$1.L$3 = objectRef;
                singleProcessDataStore$readAndInit$api$1$updateData$1.L$4 = singleProcessDataStore3;
                singleProcessDataStore$readAndInit$api$1$updateData$1.label = 1;
                if (mutex.lock(null, singleProcessDataStore$readAndInit$api$1$updateData$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = null;
                singleProcessDataStore = singleProcessDataStore3;
                i = 0;
                try {
                    if (!booleanRef.element) {
                        throw new IllegalStateException("InitializerApi.updateData should not be called after initialization is complete.");
                    }
                    T t2 = objectRef.element;
                    singleProcessDataStore$readAndInit$api$1$updateData$1.L$0 = mutex;
                    singleProcessDataStore$readAndInit$api$1$updateData$1.L$1 = objectRef;
                    singleProcessDataStore$readAndInit$api$1$updateData$1.L$2 = singleProcessDataStore;
                    singleProcessDataStore$readAndInit$api$1$updateData$1.L$3 = null;
                    singleProcessDataStore$readAndInit$api$1$updateData$1.L$4 = null;
                    singleProcessDataStore$readAndInit$api$1$updateData$1.label = 2;
                    objInvoke = function2.invoke(t2, singleProcessDataStore$readAndInit$api$1$updateData$1);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Ref.ObjectRef<T> objectRef3 = objectRef;
                    singleProcessDataStore2 = singleProcessDataStore;
                    objectRef2 = objectRef3;
                    obj2 = obj;
                    i2 = i;
                    if (!Intrinsics.areEqual(objInvoke, objectRef2.element)) {
                        singleProcessDataStore$readAndInit$api$1$updateData$1.L$0 = mutex;
                        singleProcessDataStore$readAndInit$api$1$updateData$1.L$1 = objectRef2;
                        singleProcessDataStore$readAndInit$api$1$updateData$1.L$2 = objInvoke;
                        singleProcessDataStore$readAndInit$api$1$updateData$1.label = 3;
                        if (singleProcessDataStore2.writeData$datastore_core(objInvoke, singleProcessDataStore$readAndInit$api$1$updateData$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        t = (T) objInvoke;
                        objectRef2.element = t;
                    }
                    T t3 = objectRef2.element;
                    mutex.unlock(obj2);
                    return t3;
                } catch (Throwable th2) {
                    Object obj4 = obj;
                    th = th2;
                    obj2 = obj4;
                    mutex.unlock(obj2);
                    throw th;
                }
            case 1:
                i = 0;
                singleProcessDataStore = (SingleProcessDataStore) singleProcessDataStore$readAndInit$api$1$updateData$1.L$4;
                objectRef = (Ref.ObjectRef) singleProcessDataStore$readAndInit$api$1$updateData$1.L$3;
                booleanRef = (Ref.BooleanRef) singleProcessDataStore$readAndInit$api$1$updateData$1.L$2;
                obj = null;
                mutex = (Mutex) singleProcessDataStore$readAndInit$api$1$updateData$1.L$1;
                function2 = (Function2) singleProcessDataStore$readAndInit$api$1$updateData$1.L$0;
                ResultKt.throwOnFailure(obj3);
                if (!booleanRef.element) {
                    throw new IllegalStateException("InitializerApi.updateData should not be called after initialization is complete.");
                }
                T t4 = objectRef.element;
                singleProcessDataStore$readAndInit$api$1$updateData$1.L$0 = mutex;
                singleProcessDataStore$readAndInit$api$1$updateData$1.L$1 = objectRef;
                singleProcessDataStore$readAndInit$api$1$updateData$1.L$2 = singleProcessDataStore;
                singleProcessDataStore$readAndInit$api$1$updateData$1.L$3 = null;
                singleProcessDataStore$readAndInit$api$1$updateData$1.L$4 = null;
                singleProcessDataStore$readAndInit$api$1$updateData$1.label = 2;
                objInvoke = function2.invoke(t4, singleProcessDataStore$readAndInit$api$1$updateData$1);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Ref.ObjectRef<T> objectRef4 = objectRef;
                singleProcessDataStore2 = singleProcessDataStore;
                objectRef2 = objectRef4;
                obj2 = obj;
                i2 = i;
                if (!Intrinsics.areEqual(objInvoke, objectRef2.element)) {
                    singleProcessDataStore$readAndInit$api$1$updateData$1.L$0 = mutex;
                    singleProcessDataStore$readAndInit$api$1$updateData$1.L$1 = objectRef2;
                    singleProcessDataStore$readAndInit$api$1$updateData$1.L$2 = objInvoke;
                    singleProcessDataStore$readAndInit$api$1$updateData$1.label = 3;
                    if (singleProcessDataStore2.writeData$datastore_core(objInvoke, singleProcessDataStore$readAndInit$api$1$updateData$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    t = (T) objInvoke;
                    objectRef2.element = t;
                }
                T t5 = objectRef2.element;
                mutex.unlock(obj2);
                return t5;
            case 2:
                i2 = 0;
                SingleProcessDataStore singleProcessDataStore4 = (SingleProcessDataStore) singleProcessDataStore$readAndInit$api$1$updateData$1.L$2;
                Ref.ObjectRef<T> objectRef5 = (Ref.ObjectRef) singleProcessDataStore$readAndInit$api$1$updateData$1.L$1;
                obj2 = null;
                mutex = (Mutex) singleProcessDataStore$readAndInit$api$1$updateData$1.L$0;
                try {
                    ResultKt.throwOnFailure(obj3);
                    singleProcessDataStore2 = singleProcessDataStore4;
                    objectRef2 = objectRef5;
                    objInvoke = obj3;
                    if (!Intrinsics.areEqual(objInvoke, objectRef2.element)) {
                        singleProcessDataStore$readAndInit$api$1$updateData$1.L$0 = mutex;
                        singleProcessDataStore$readAndInit$api$1$updateData$1.L$1 = objectRef2;
                        singleProcessDataStore$readAndInit$api$1$updateData$1.L$2 = objInvoke;
                        singleProcessDataStore$readAndInit$api$1$updateData$1.label = 3;
                        if (singleProcessDataStore2.writeData$datastore_core(objInvoke, singleProcessDataStore$readAndInit$api$1$updateData$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        t = (T) objInvoke;
                        objectRef2.element = t;
                    }
                    T t6 = objectRef2.element;
                    mutex.unlock(obj2);
                    return t6;
                } catch (Throwable th3) {
                    th = th3;
                    mutex.unlock(obj2);
                    throw th;
                }
            case 3:
                t = (T) singleProcessDataStore$readAndInit$api$1$updateData$1.L$2;
                objectRef2 = (Ref.ObjectRef) singleProcessDataStore$readAndInit$api$1$updateData$1.L$1;
                obj2 = null;
                mutex = (Mutex) singleProcessDataStore$readAndInit$api$1$updateData$1.L$0;
                try {
                    ResultKt.throwOnFailure(obj3);
                    objectRef2.element = t;
                    T t7 = objectRef2.element;
                    mutex.unlock(obj2);
                    return t7;
                } catch (Throwable th4) {
                    th = th4;
                    mutex.unlock(obj2);
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
