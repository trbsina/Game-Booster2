package com.gamebooster2.booster;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: SmartKillList.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/gamebooster2/booster/SmartKillList;", "", "()V", "targets", "", "", "getTargets", "()Ljava/util/Set;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SmartKillList {
    public static final SmartKillList INSTANCE = new SmartKillList();
    private static final Set<String> targets = SetsKt.setOf((Object[]) new String[]{"com.facebook.katana", "com.facebook.orca", "com.instagram.android", "com.twitter.android", "com.snapchat.android", "com.zhiliaoapp.musically", "com.whatsapp", "org.telegram.messenger", "com.viber.voip", "com.linkedin.android", "com.pinterest", "com.reddit.frontpage", "com.android.chrome", "org.mozilla.firefox", "com.opera.browser", "com.UCMobile.intl", "com.brave.browser", "com.microsoft.emmx", "com.sec.android.app.sbrowser", "com.mi.globalbrowser", "com.google.android.googlequicksearchbox", "com.google.android.youtube", "com.google.android.apps.photos", "com.google.android.gm", "com.google.android.apps.maps", "com.google.android.videos", "com.android.vending", "com.IDO.MeatBall", "com.ido.meatball", "com.miui.analytics", "com.miui.msa.global", "com.samsung.android.app.tips", "com.flipkart.android", "com.amazon.mShop.android.shopping"});

    private SmartKillList() {
    }

    public final Set<String> getTargets() {
        return targets;
    }
}
