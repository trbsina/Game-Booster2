package com.gamebooster2.monitor;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: RamMonitor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/gamebooster2/monitor/RamInfo;", "", "totalMb", "", "availableMb", "usedMb", "usedPercent", "", "(JJJF)V", "getAvailableMb", "()J", "getTotalMb", "getUsedMb", "getUsedPercent", "()F", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class RamInfo {
    private final long availableMb;
    private final long totalMb;
    private final long usedMb;
    private final float usedPercent;

    public static /* synthetic */ RamInfo copy$default(RamInfo ramInfo, long j, long j2, long j3, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            j = ramInfo.totalMb;
        }
        long j4 = j;
        if ((i & 2) != 0) {
            j2 = ramInfo.availableMb;
        }
        long j5 = j2;
        if ((i & 4) != 0) {
            j3 = ramInfo.usedMb;
        }
        long j6 = j3;
        if ((i & 8) != 0) {
            f = ramInfo.usedPercent;
        }
        return ramInfo.copy(j4, j5, j6, f);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getTotalMb() {
        return this.totalMb;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getAvailableMb() {
        return this.availableMb;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getUsedMb() {
        return this.usedMb;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getUsedPercent() {
        return this.usedPercent;
    }

    public final RamInfo copy(long totalMb, long availableMb, long usedMb, float usedPercent) {
        return new RamInfo(totalMb, availableMb, usedMb, usedPercent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RamInfo)) {
            return false;
        }
        RamInfo ramInfo = (RamInfo) other;
        return this.totalMb == ramInfo.totalMb && this.availableMb == ramInfo.availableMb && this.usedMb == ramInfo.usedMb && Float.compare(this.usedPercent, ramInfo.usedPercent) == 0;
    }

    public int hashCode() {
        return (((((Long.hashCode(this.totalMb) * 31) + Long.hashCode(this.availableMb)) * 31) + Long.hashCode(this.usedMb)) * 31) + Float.hashCode(this.usedPercent);
    }

    public String toString() {
        return "RamInfo(totalMb=" + this.totalMb + ", availableMb=" + this.availableMb + ", usedMb=" + this.usedMb + ", usedPercent=" + this.usedPercent + ')';
    }

    public RamInfo(long totalMb, long availableMb, long usedMb, float usedPercent) {
        this.totalMb = totalMb;
        this.availableMb = availableMb;
        this.usedMb = usedMb;
        this.usedPercent = usedPercent;
    }

    public final long getTotalMb() {
        return this.totalMb;
    }

    public final long getAvailableMb() {
        return this.availableMb;
    }

    public final long getUsedMb() {
        return this.usedMb;
    }

    public final float getUsedPercent() {
        return this.usedPercent;
    }
}
