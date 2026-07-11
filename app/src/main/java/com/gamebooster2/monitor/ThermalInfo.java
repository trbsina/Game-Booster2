package com.gamebooster2.monitor;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThermalMonitor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/gamebooster2/monitor/ThermalInfo;", "", "cpuTempC", "", "batteryTempC", "level", "Lcom/gamebooster2/monitor/ThermalLevel;", "(FFLcom/gamebooster2/monitor/ThermalLevel;)V", "getBatteryTempC", "()F", "getCpuTempC", "getLevel", "()Lcom/gamebooster2/monitor/ThermalLevel;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class ThermalInfo {
    private final float batteryTempC;
    private final float cpuTempC;
    private final ThermalLevel level;

    public static /* synthetic */ ThermalInfo copy$default(ThermalInfo thermalInfo, float f, float f2, ThermalLevel thermalLevel, int i, Object obj) {
        if ((i & 1) != 0) {
            f = thermalInfo.cpuTempC;
        }
        if ((i & 2) != 0) {
            f2 = thermalInfo.batteryTempC;
        }
        if ((i & 4) != 0) {
            thermalLevel = thermalInfo.level;
        }
        return thermalInfo.copy(f, f2, thermalLevel);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getCpuTempC() {
        return this.cpuTempC;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getBatteryTempC() {
        return this.batteryTempC;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ThermalLevel getLevel() {
        return this.level;
    }

    public final ThermalInfo copy(float cpuTempC, float batteryTempC, ThermalLevel level) {
        Intrinsics.checkNotNullParameter(level, "level");
        return new ThermalInfo(cpuTempC, batteryTempC, level);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ThermalInfo)) {
            return false;
        }
        ThermalInfo thermalInfo = (ThermalInfo) other;
        return Float.compare(this.cpuTempC, thermalInfo.cpuTempC) == 0 && Float.compare(this.batteryTempC, thermalInfo.batteryTempC) == 0 && this.level == thermalInfo.level;
    }

    public int hashCode() {
        return (((Float.hashCode(this.cpuTempC) * 31) + Float.hashCode(this.batteryTempC)) * 31) + this.level.hashCode();
    }

    public String toString() {
        return "ThermalInfo(cpuTempC=" + this.cpuTempC + ", batteryTempC=" + this.batteryTempC + ", level=" + this.level + ')';
    }

    public ThermalInfo(float cpuTempC, float batteryTempC, ThermalLevel level) {
        Intrinsics.checkNotNullParameter(level, "level");
        this.cpuTempC = cpuTempC;
        this.batteryTempC = batteryTempC;
        this.level = level;
    }

    public final float getCpuTempC() {
        return this.cpuTempC;
    }

    public final float getBatteryTempC() {
        return this.batteryTempC;
    }

    public final ThermalLevel getLevel() {
        return this.level;
    }
}
