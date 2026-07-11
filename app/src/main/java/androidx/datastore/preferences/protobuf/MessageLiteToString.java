package androidx.datastore.preferences.protobuf;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes.dex */
final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final String LIST_SUFFIX = "List";
    private static final String MAP_SUFFIX = "Map";

    MessageLiteToString() {
    }

    static String toString(MessageLite messageLite, String commentString) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("# ").append(commentString);
        reflectivePrintWithIndent(messageLite, buffer, 0);
        return buffer.toString();
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 6021. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    private static void reflectivePrintWithIndent(androidx.datastore.preferences.protobuf.MessageLite r18, java.lang.StringBuilder r19, int r20) {
        /*
            Method dump skipped, instruction units count: 602
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageLiteToString.reflectivePrintWithIndent(androidx.datastore.preferences.protobuf.MessageLite, java.lang.StringBuilder, int):void");
    }

    private static boolean isDefaultValue(Object o) {
        if (o instanceof Boolean) {
            return !((Boolean) o).booleanValue();
        }
        if (o instanceof Integer) {
            return ((Integer) o).intValue() == 0;
        }
        if (o instanceof Float) {
            return ((Float) o).floatValue() == 0.0f;
        }
        if (o instanceof Double) {
            return ((Double) o).doubleValue() == 0.0d;
        }
        if (o instanceof String) {
            return o.equals("");
        }
        if (o instanceof ByteString) {
            return o.equals(ByteString.EMPTY);
        }
        if (o instanceof MessageLite) {
            return o == ((MessageLite) o).getDefaultInstanceForType();
        }
        return (o instanceof java.lang.Enum) && ((java.lang.Enum) o).ordinal() == 0;
    }

    static final void printField(StringBuilder buffer, int indent, String name, Object object) {
        if (object instanceof List) {
            List<?> list = (List) object;
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                printField(buffer, indent, name, it.next());
            }
            return;
        }
        if (object instanceof Map) {
            Map<?, ?> map = (Map) object;
            Iterator<Map.Entry<?, ?>> it2 = map.entrySet().iterator();
            while (it2.hasNext()) {
                printField(buffer, indent, name, it2.next());
            }
            return;
        }
        buffer.append('\n');
        for (int i = 0; i < indent; i++) {
            buffer.append(' ');
        }
        buffer.append(name);
        if (object instanceof String) {
            buffer.append(": \"").append(TextFormatEscaper.escapeText((String) object)).append(Typography.quote);
            return;
        }
        if (object instanceof ByteString) {
            buffer.append(": \"").append(TextFormatEscaper.escapeBytes((ByteString) object)).append(Typography.quote);
            return;
        }
        if (object instanceof GeneratedMessageLite) {
            buffer.append(" {");
            reflectivePrintWithIndent((GeneratedMessageLite) object, buffer, indent + 2);
            buffer.append("\n");
            for (int i2 = 0; i2 < indent; i2++) {
                buffer.append(' ');
            }
            buffer.append("}");
            return;
        }
        if (object instanceof Map.Entry) {
            buffer.append(" {");
            Map.Entry<?, ?> entry = (Map.Entry) object;
            printField(buffer, indent + 2, "key", entry.getKey());
            printField(buffer, indent + 2, "value", entry.getValue());
            buffer.append("\n");
            for (int i3 = 0; i3 < indent; i3++) {
                buffer.append(' ');
            }
            buffer.append("}");
            return;
        }
        buffer.append(": ").append(object.toString());
    }

    private static final String camelCaseToSnakeCase(String camelCase) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                builder.append("_");
            }
            builder.append(Character.toLowerCase(ch));
        }
        return builder.toString();
    }
}
