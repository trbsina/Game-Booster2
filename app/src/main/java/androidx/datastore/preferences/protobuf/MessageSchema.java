package androidx.datastore.preferences.protobuf;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
final class MessageSchema<T> implements Schema<T> {
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int INTS_PER_FIELD = 3;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_MASK = 268435456;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    private MessageSchema(int[] buffer, Object[] objects, int minFieldNumber, int maxFieldNumber, MessageLite defaultInstance, boolean proto3, boolean useCachedSizeField, int[] intArray, int checkInitialized, int mapFieldPositions, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = buffer;
        this.objects = objects;
        this.minFieldNumber = minFieldNumber;
        this.maxFieldNumber = maxFieldNumber;
        this.lite = defaultInstance instanceof GeneratedMessageLite;
        this.proto3 = proto3;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(defaultInstance);
        this.useCachedSizeField = useCachedSizeField;
        this.intArray = intArray;
        this.checkInitializedCount = checkInitialized;
        this.repeatedFieldOffsetStart = mapFieldPositions;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
        this.mapFieldSchema = mapFieldSchema;
    }

    static <T> MessageSchema<T> newSchema(Class<T> messageClass, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int objectsPosition;
        int maxFieldNumber;
        int hasBitsCount;
        int numEntries;
        int mapFieldCount;
        int mapFieldCount2;
        int[] intArray;
        int oneofCount;
        int i;
        int next;
        int i2;
        int next2;
        int i3;
        int next3;
        int i4;
        int next4;
        int i5;
        int next5;
        int i6;
        int next6;
        int i7;
        int next7;
        int i8;
        int next8;
        int length;
        int i9;
        int next9;
        int objectsPosition2;
        int next10;
        int minFieldNumber;
        String info;
        int presenceFieldOffset;
        int i10;
        int presenceMaskShift;
        int result;
        int next11;
        int fieldOffset;
        int next12;
        java.lang.reflect.Field hasBitsField;
        int i11;
        int next13;
        java.lang.reflect.Field oneofField;
        java.lang.reflect.Field oneofCaseField;
        int i12;
        int next14;
        int i13;
        int next15;
        int i14;
        int next16;
        int i15;
        int next17;
        int i16;
        int next18;
        boolean isProto3 = messageInfo.getSyntax() == ProtoSyntax.PROTO3;
        String info2 = messageInfo.getStringInfo();
        int length2 = info2.length();
        int i17 = 0 + 1;
        int next19 = info2.charAt(0);
        int i18 = 55296;
        if (next19 >= 55296) {
            int result2 = next19 & 8191;
            int shift = 13;
            while (true) {
                i16 = i17 + 1;
                next18 = info2.charAt(i17);
                if (next18 < 55296) {
                    break;
                }
                result2 |= (next18 & 8191) << shift;
                shift += 13;
                i17 = i16;
            }
            next19 = result2 | (next18 << shift);
            i17 = i16;
        }
        int flags = next19;
        int i19 = i17 + 1;
        int next20 = info2.charAt(i17);
        if (next20 >= 55296) {
            int result3 = next20 & 8191;
            int shift2 = 13;
            while (true) {
                i15 = i19 + 1;
                next17 = info2.charAt(i19);
                if (next17 < 55296) {
                    break;
                }
                result3 |= (next17 & 8191) << shift2;
                shift2 += 13;
                i19 = i15;
            }
            next20 = result3 | (next17 << shift2);
            i19 = i15;
        }
        int fieldCount = next20;
        if (fieldCount == 0) {
            maxFieldNumber = 0;
            int[] intArray2 = EMPTY_INT_ARRAY;
            oneofCount = 0;
            hasBitsCount = 0;
            numEntries = 0;
            mapFieldCount = 0;
            mapFieldCount2 = 0;
            intArray = intArray2;
            objectsPosition = 0;
        } else {
            int i20 = i19 + 1;
            int next21 = info2.charAt(i19);
            if (next21 >= 55296) {
                int result4 = next21 & 8191;
                int shift3 = 13;
                while (true) {
                    i8 = i20 + 1;
                    next8 = info2.charAt(i20);
                    if (next8 < 55296) {
                        break;
                    }
                    result4 |= (next8 & 8191) << shift3;
                    shift3 += 13;
                    i20 = i8;
                }
                next21 = result4 | (next8 << shift3);
                i20 = i8;
            }
            int result5 = next21;
            int i21 = i20 + 1;
            int next22 = info2.charAt(i20);
            if (next22 >= 55296) {
                int result6 = next22 & 8191;
                int shift4 = 13;
                while (true) {
                    i7 = i21 + 1;
                    next7 = info2.charAt(i21);
                    if (next7 < 55296) {
                        break;
                    }
                    result6 |= (next7 & 8191) << shift4;
                    shift4 += 13;
                    i21 = i7;
                }
                next22 = result6 | (next7 << shift4);
                i21 = i7;
            }
            int result7 = next22;
            int i22 = i21 + 1;
            int next23 = info2.charAt(i21);
            if (next23 >= 55296) {
                int result8 = next23 & 8191;
                int shift5 = 13;
                while (true) {
                    i6 = i22 + 1;
                    next6 = info2.charAt(i22);
                    if (next6 < 55296) {
                        break;
                    }
                    result8 |= (next6 & 8191) << shift5;
                    shift5 += 13;
                    i22 = i6;
                }
                next23 = result8 | (next6 << shift5);
                i22 = i6;
            }
            int result9 = next23;
            int i23 = i22 + 1;
            int next24 = info2.charAt(i22);
            if (next24 >= 55296) {
                int result10 = next24 & 8191;
                int shift6 = 13;
                while (true) {
                    i5 = i23 + 1;
                    next5 = info2.charAt(i23);
                    if (next5 < 55296) {
                        break;
                    }
                    result10 |= (next5 & 8191) << shift6;
                    shift6 += 13;
                    i23 = i5;
                }
                next24 = result10 | (next5 << shift6);
                i23 = i5;
            }
            int result11 = next24;
            int i24 = i23 + 1;
            int next25 = info2.charAt(i23);
            if (next25 >= 55296) {
                int result12 = next25 & 8191;
                int shift7 = 13;
                while (true) {
                    i4 = i24 + 1;
                    next4 = info2.charAt(i24);
                    if (next4 < 55296) {
                        break;
                    }
                    result12 |= (next4 & 8191) << shift7;
                    shift7 += 13;
                    i24 = i4;
                }
                next25 = result12 | (next4 << shift7);
                i24 = i4;
            }
            int result13 = next25;
            int i25 = i24 + 1;
            int next26 = info2.charAt(i24);
            if (next26 >= 55296) {
                int result14 = next26 & 8191;
                int shift8 = 13;
                while (true) {
                    i3 = i25 + 1;
                    next3 = info2.charAt(i25);
                    if (next3 < 55296) {
                        break;
                    }
                    result14 |= (next3 & 8191) << shift8;
                    shift8 += 13;
                    i25 = i3;
                }
                next26 = result14 | (next3 << shift8);
                i25 = i3;
            }
            int result15 = next26;
            int i26 = i25 + 1;
            int next27 = info2.charAt(i25);
            if (next27 >= 55296) {
                int result16 = next27 & 8191;
                int shift9 = 13;
                while (true) {
                    i2 = i26 + 1;
                    next2 = info2.charAt(i26);
                    if (next2 < 55296) {
                        break;
                    }
                    result16 |= (next2 & 8191) << shift9;
                    shift9 += 13;
                    i26 = i2;
                }
                next27 = result16 | (next2 << shift9);
                i26 = i2;
            }
            int result17 = next27;
            int i27 = i26 + 1;
            next20 = info2.charAt(i26);
            if (next20 >= 55296) {
                int result18 = next20 & 8191;
                int shift10 = 13;
                while (true) {
                    i = i27 + 1;
                    next = info2.charAt(i27);
                    if (next < 55296) {
                        break;
                    }
                    result18 |= (next & 8191) << shift10;
                    shift10 += 13;
                    i27 = i;
                }
                next20 = result18 | (next << shift10);
                i27 = i;
            }
            int result19 = next20;
            int[] intArray3 = new int[result19 + result15 + result17];
            objectsPosition = (result5 * 2) + result7;
            maxFieldNumber = result9;
            hasBitsCount = result11;
            numEntries = result13;
            mapFieldCount = result15;
            mapFieldCount2 = result19;
            intArray = intArray3;
            oneofCount = result5;
            i19 = i27;
        }
        Unsafe unsafe = UNSAFE;
        Object[] messageInfoObjects = messageInfo.getObjects();
        Class<?> messageClass = messageInfo.getDefaultInstance().getClass();
        int[] buffer = new int[numEntries * 3];
        Object[] objects = new Object[numEntries * 2];
        int mapFieldIndex = mapFieldCount2;
        int repeatedFieldIndex = mapFieldCount2 + mapFieldCount;
        int objectsPosition3 = objectsPosition;
        int mapFieldIndex2 = mapFieldIndex;
        int repeatedFieldIndex2 = repeatedFieldIndex;
        int bufferIndex = 0;
        int next28 = i19;
        int checkInitializedPosition = 0;
        while (next28 < length2) {
            int i28 = next28 + 1;
            int next29 = info2.charAt(next28);
            if (next29 >= i18) {
                int result20 = next29 & 8191;
                int shift11 = 13;
                while (true) {
                    i14 = i28 + 1;
                    next16 = info2.charAt(i28);
                    if (next16 < i18) {
                        break;
                    }
                    result20 |= (next16 & 8191) << shift11;
                    shift11 += 13;
                    i28 = i14;
                }
                next29 = result20 | (next16 << shift11);
                i28 = i14;
            }
            int result21 = next29;
            int i29 = i28 + 1;
            int next30 = info2.charAt(i28);
            if (next30 < i18) {
                length = length2;
                i9 = i29;
            } else {
                int result22 = next30 & 8191;
                int i30 = i29;
                int i31 = 13;
                while (true) {
                    i13 = i30 + 1;
                    next15 = info2.charAt(i30);
                    length = length2;
                    if (next15 < 55296) {
                        break;
                    }
                    result22 |= (next15 & 8191) << i31;
                    i31 += 13;
                    i30 = i13;
                    length2 = length;
                }
                next30 = result22 | (next15 << i31);
                i9 = i13;
            }
            int fieldTypeWithExtraBits = next30;
            int fieldType = fieldTypeWithExtraBits & 255;
            int oneofCount2 = oneofCount;
            int oneofCount3 = fieldTypeWithExtraBits & 1024;
            if (oneofCount3 != 0) {
                intArray[checkInitializedPosition] = bufferIndex;
                checkInitializedPosition++;
            }
            Object[] objects2 = objects;
            if (fieldType >= 51) {
                int i32 = i9 + 1;
                int next31 = info2.charAt(i9);
                if (next31 >= 55296) {
                    int result23 = next31 & 8191;
                    int shift12 = 13;
                    while (true) {
                        i12 = i32 + 1;
                        next14 = info2.charAt(i32);
                        if (next14 < 55296) {
                            break;
                        }
                        result23 |= (next14 & 8191) << shift12;
                        shift12 += 13;
                        i32 = i12;
                    }
                    next31 = result23 | (next14 << shift12);
                    i32 = i12;
                }
                int oneofIndex = next31;
                int oneofFieldType = fieldType - 51;
                int next32 = next31;
                if (oneofFieldType == 9 || oneofFieldType == 17) {
                    objects2[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition3];
                    objectsPosition3++;
                } else if (oneofFieldType != 12) {
                    i32 = i32;
                } else {
                    i32 = i32;
                    if ((flags & 1) == 1) {
                        objects2[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition3];
                        objectsPosition3++;
                    }
                }
                int index = oneofIndex * 2;
                Object o = messageInfoObjects[index];
                if (o instanceof java.lang.reflect.Field) {
                    oneofField = (java.lang.reflect.Field) o;
                } else {
                    oneofField = reflectField(messageClass, (String) o);
                    messageInfoObjects[index] = oneofField;
                }
                int fieldOffset2 = (int) unsafe.objectFieldOffset(oneofField);
                int index2 = index + 1;
                Object o2 = messageInfoObjects[index2];
                if (o2 instanceof java.lang.reflect.Field) {
                    oneofCaseField = (java.lang.reflect.Field) o2;
                } else {
                    oneofCaseField = reflectField(messageClass, (String) o2);
                    messageInfoObjects[index2] = oneofCaseField;
                }
                presenceFieldOffset = (int) unsafe.objectFieldOffset(oneofCaseField);
                presenceMaskShift = 0;
                minFieldNumber = maxFieldNumber;
                next28 = i32;
                fieldOffset = fieldOffset2;
                info = info2;
                next11 = next32;
                next10 = hasBitsCount;
            } else {
                int objectsPosition4 = objectsPosition3 + 1;
                java.lang.reflect.Field field = reflectField(messageClass, (String) messageInfoObjects[objectsPosition3]);
                if (fieldType == 9 || fieldType == 17) {
                    next9 = 1;
                    objects2[((bufferIndex / 3) * 2) + 1] = field.getType();
                    objectsPosition2 = objectsPosition4;
                } else if (fieldType == 27 || fieldType == 49) {
                    objects2[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition4];
                    objectsPosition2 = objectsPosition4 + 1;
                    next9 = 1;
                } else if (fieldType == 12 || fieldType == 30 || fieldType == 44) {
                    next30 = next30;
                    if ((flags & 1) == 1) {
                        objects2[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition4];
                        objectsPosition2 = objectsPosition4 + 1;
                        next9 = 1;
                    } else {
                        next9 = 1;
                        objectsPosition2 = objectsPosition4;
                    }
                } else if (fieldType != 50) {
                    next30 = next30;
                    next9 = 1;
                    objectsPosition2 = objectsPosition4;
                } else {
                    int mapFieldIndex3 = mapFieldIndex2 + 1;
                    intArray[mapFieldIndex2] = bufferIndex;
                    int objectsPosition5 = objectsPosition4 + 1;
                    objects2[(bufferIndex / 3) * 2] = messageInfoObjects[objectsPosition4];
                    int mapFieldIndex4 = fieldTypeWithExtraBits & 2048;
                    if (mapFieldIndex4 == 0) {
                        mapFieldIndex2 = mapFieldIndex3;
                        objectsPosition2 = objectsPosition5;
                        next30 = next30;
                        next9 = 1;
                    } else {
                        objects2[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition5];
                        objectsPosition2 = objectsPosition5 + 1;
                        mapFieldIndex2 = mapFieldIndex3;
                        next30 = next30;
                        next9 = 1;
                    }
                }
                next10 = hasBitsCount;
                minFieldNumber = maxFieldNumber;
                int fieldOffset3 = (int) unsafe.objectFieldOffset(field);
                if ((flags & 1) != next9 || fieldType > 17) {
                    info = info2;
                    presenceFieldOffset = 0;
                    i10 = i9;
                    presenceMaskShift = 0;
                    result = next30;
                } else {
                    int i33 = i9 + 1;
                    int next33 = info2.charAt(i9);
                    if (next33 < 55296) {
                        info = info2;
                        result = next33;
                        next12 = i33;
                    } else {
                        int result24 = next33 & 8191;
                        int shift13 = 13;
                        while (true) {
                            i11 = i33 + 1;
                            next13 = info2.charAt(i33);
                            info = info2;
                            if (next13 < 55296) {
                                break;
                            }
                            result24 |= (next13 & 8191) << shift13;
                            shift13 += 13;
                            i33 = i11;
                            info2 = info;
                        }
                        result = result24 | (next13 << shift13);
                        next12 = i11;
                    }
                    int i34 = result;
                    int index3 = (oneofCount2 * 2) + (i34 / 32);
                    Object o3 = messageInfoObjects[index3];
                    i10 = next12;
                    if (o3 instanceof java.lang.reflect.Field) {
                        hasBitsField = (java.lang.reflect.Field) o3;
                    } else {
                        hasBitsField = reflectField(messageClass, (String) o3);
                        messageInfoObjects[index3] = hasBitsField;
                    }
                    int presenceFieldOffset2 = (int) unsafe.objectFieldOffset(hasBitsField);
                    int presenceMaskShift2 = i34 % 32;
                    presenceFieldOffset = presenceFieldOffset2;
                    presenceMaskShift = presenceMaskShift2;
                }
                if (fieldType >= 18 && fieldType <= 49) {
                    intArray[repeatedFieldIndex2] = fieldOffset3;
                    repeatedFieldIndex2++;
                    next11 = result;
                    fieldOffset = fieldOffset3;
                    next28 = i10;
                    objectsPosition3 = objectsPosition2;
                } else {
                    next11 = result;
                    fieldOffset = fieldOffset3;
                    next28 = i10;
                    objectsPosition3 = objectsPosition2;
                }
            }
            int fieldOffset4 = bufferIndex + 1;
            buffer[bufferIndex] = result21;
            int bufferIndex2 = fieldOffset4 + 1;
            buffer[fieldOffset4] = ((fieldTypeWithExtraBits & 512) != 0 ? ENFORCE_UTF8_MASK : 0) | ((fieldTypeWithExtraBits & 256) != 0 ? REQUIRED_MASK : 0) | (fieldType << 20) | fieldOffset;
            bufferIndex = bufferIndex2 + 1;
            buffer[bufferIndex2] = (presenceMaskShift << 20) | presenceFieldOffset;
            maxFieldNumber = minFieldNumber;
            length2 = length;
            oneofCount = oneofCount2;
            objects = objects2;
            hasBitsCount = next10;
            info2 = info;
            i18 = 55296;
        }
        return new MessageSchema<>(buffer, objects, maxFieldNumber, hasBitsCount, messageInfo.getDefaultInstance(), isProto3, false, intArray, mapFieldCount2, mapFieldCount2 + mapFieldCount, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static java.lang.reflect.Field reflectField(Class<?> messageClass, String fieldName) {
        try {
            return messageClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            java.lang.reflect.Field[] fields = messageClass.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + fieldName + " for " + messageClass.getName() + " not found. Known fields are " + Arrays.toString(fields));
        }
    }

    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int minFieldNumber;
        int maxFieldNumber;
        int[] mapFieldPositions;
        int[] repeatedFieldOffsets;
        int[] repeatedFieldOffsets2;
        boolean isProto3 = messageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fis = messageInfo.getFields();
        if (fis.length == 0) {
            minFieldNumber = 0;
            maxFieldNumber = 0;
        } else {
            int minFieldNumber2 = fis[0].getFieldNumber();
            minFieldNumber = minFieldNumber2;
            maxFieldNumber = fis[fis.length - 1].getFieldNumber();
        }
        int numEntries = fis.length;
        int[] buffer = new int[numEntries * 3];
        Object[] objects = new Object[numEntries * 2];
        int mapFieldCount = 0;
        int repeatedFieldCount = 0;
        for (FieldInfo fi : fis) {
            if (fi.getType() == FieldType.MAP) {
                mapFieldCount++;
            } else if (fi.getType().id() >= 18 && fi.getType().id() <= 49) {
                repeatedFieldCount++;
            }
        }
        int[] mapFieldPositions2 = mapFieldCount > 0 ? new int[mapFieldCount] : null;
        int[] repeatedFieldOffsets3 = repeatedFieldCount > 0 ? new int[repeatedFieldCount] : null;
        int mapFieldCount2 = 0;
        int[] checkInitialized = messageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int repeatedFieldCount2 = 0;
        int checkInitializedIndex = 0;
        int checkInitializedIndex2 = 0;
        int fieldIndex = 0;
        while (checkInitializedIndex2 < fis.length) {
            FieldInfo fi2 = fis[checkInitializedIndex2];
            int fieldNumber = fi2.getFieldNumber();
            storeFieldData(fi2, buffer, fieldIndex, isProto3, objects);
            FieldInfo[] fis2 = fis;
            if (checkInitializedIndex < checkInitialized.length && checkInitialized[checkInitializedIndex] == fieldNumber) {
                checkInitialized[checkInitializedIndex] = fieldIndex;
                checkInitializedIndex++;
            }
            int numEntries2 = numEntries;
            if (fi2.getType() == FieldType.MAP) {
                mapFieldPositions2[mapFieldCount2] = fieldIndex;
                mapFieldCount2++;
                repeatedFieldOffsets2 = repeatedFieldOffsets3;
            } else if (fi2.getType().id() < 18 || fi2.getType().id() > 49) {
                repeatedFieldOffsets2 = repeatedFieldOffsets3;
            } else {
                repeatedFieldOffsets2 = repeatedFieldOffsets3;
                repeatedFieldOffsets2[repeatedFieldCount2] = (int) UnsafeUtil.objectFieldOffset(fi2.getField());
                repeatedFieldCount2++;
            }
            checkInitializedIndex2++;
            fieldIndex += 3;
            repeatedFieldOffsets3 = repeatedFieldOffsets2;
            fis = fis2;
            numEntries = numEntries2;
        }
        int[] repeatedFieldOffsets4 = repeatedFieldOffsets3;
        if (mapFieldPositions2 != null) {
            mapFieldPositions = mapFieldPositions2;
        } else {
            mapFieldPositions = EMPTY_INT_ARRAY;
        }
        if (repeatedFieldOffsets4 != null) {
            repeatedFieldOffsets = repeatedFieldOffsets4;
        } else {
            repeatedFieldOffsets = EMPTY_INT_ARRAY;
        }
        int[] combined = new int[checkInitialized.length + mapFieldPositions.length + repeatedFieldOffsets.length];
        System.arraycopy(checkInitialized, 0, combined, 0, checkInitialized.length);
        System.arraycopy(mapFieldPositions, 0, combined, checkInitialized.length, mapFieldPositions.length);
        System.arraycopy(repeatedFieldOffsets, 0, combined, checkInitialized.length + mapFieldPositions.length, repeatedFieldOffsets.length);
        MessageLite defaultInstance = messageInfo.getDefaultInstance();
        int length = checkInitialized.length;
        int length2 = checkInitialized.length;
        int checkInitializedIndex3 = mapFieldPositions.length;
        int fieldIndex2 = length2 + checkInitializedIndex3;
        return new MessageSchema<>(buffer, objects, minFieldNumber, maxFieldNumber, defaultInstance, isProto3, true, combined, length, fieldIndex2, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static void storeFieldData(FieldInfo fi, int[] buffer, int bufferIndex, boolean proto3, Object[] objects) {
        int fieldOffset;
        int typeId;
        int typeId2;
        int presenceFieldOffset;
        OneofInfo oneof = fi.getOneof();
        if (oneof != null) {
            typeId = fi.getType().id() + 51;
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            typeId2 = (int) UnsafeUtil.objectFieldOffset(oneof.getCaseField());
            presenceFieldOffset = 0;
        } else {
            FieldType type = fi.getType();
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(fi.getField());
            int typeId3 = type.id();
            if (!proto3 && !type.isList() && !type.isMap()) {
                int presenceFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(fi.getPresenceField());
                typeId = typeId3;
                typeId2 = presenceFieldOffset2;
                presenceFieldOffset = Integer.numberOfTrailingZeros(fi.getPresenceMask());
            } else if (fi.getCachedSizeField() == null) {
                typeId = typeId3;
                typeId2 = 0;
                presenceFieldOffset = 0;
            } else {
                int presenceFieldOffset3 = (int) UnsafeUtil.objectFieldOffset(fi.getCachedSizeField());
                typeId = typeId3;
                typeId2 = presenceFieldOffset3;
                presenceFieldOffset = 0;
            }
        }
        buffer[bufferIndex] = fi.getFieldNumber();
        buffer[bufferIndex + 1] = (fi.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0) | (fi.isRequired() ? REQUIRED_MASK : 0) | (typeId << 20) | fieldOffset;
        buffer[bufferIndex + 2] = (presenceFieldOffset << 20) | typeId2;
        Object messageFieldClass = fi.getMessageFieldClass();
        if (fi.getMapDefaultEntry() != null) {
            objects[(bufferIndex / 3) * 2] = fi.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
                return;
            } else {
                if (fi.getEnumVerifier() != null) {
                    objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        if (messageFieldClass != null) {
            objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
        } else if (fi.getEnumVerifier() != null) {
            objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
        }
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public boolean equals(T message, T other) {
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            if (!equals(message, other, pos)) {
                return false;
            }
        }
        Object messageUnknown = this.unknownFieldSchema.getFromMessage(message);
        Object otherUnknown = this.unknownFieldSchema.getFromMessage(other);
        if (!messageUnknown.equals(otherUnknown)) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(message).equals(this.extensionSchema.getExtensions(other));
        }
        return true;
    }

    private boolean equals(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        switch (type(typeAndOffset)) {
            case 0:
                return arePresentForEquals(message, other, pos) && Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(other, offset));
            case 1:
                return arePresentForEquals(message, other, pos) && Float.floatToIntBits(UnsafeUtil.getFloat(message, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat(other, offset));
            case 2:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 3:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 4:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 5:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 6:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 7:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getBoolean(message, offset) == UnsafeUtil.getBoolean(other, offset);
            case 8:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 9:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 10:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 11:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 12:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 13:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 14:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 15:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 16:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 17:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
            case 30:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 51:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
            case 56:
            case 57:
            case 58:
            case 59:
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
            case 62:
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
            case 64:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
            case 68:
                return isOneofCaseEqual(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            default:
                return true;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int hashCode(T message) {
        int hashCode = 0;
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int entryNumber = numberAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)));
                    break;
                case 1:
                    hashCode = (hashCode * 53) + Float.floatToIntBits(UnsafeUtil.getFloat(message, offset));
                    break;
                case 2:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 3:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 4:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 5:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 6:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 7:
                    hashCode = (hashCode * 53) + Internal.hashBoolean(UnsafeUtil.getBoolean(message, offset));
                    break;
                case 8:
                    int protoHash = hashCode * 53;
                    int hashCode2 = protoHash + ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                    hashCode = hashCode2;
                    break;
                case 9:
                    int protoHash2 = 37;
                    Object submessage = UnsafeUtil.getObject(message, offset);
                    if (submessage != null) {
                        protoHash2 = submessage.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash2;
                    break;
                case 10:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 11:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 12:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 13:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 14:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 15:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 16:
                    int protoHash3 = hashCode * 53;
                    int hashCode3 = protoHash3 + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    hashCode = hashCode3;
                    break;
                case 17:
                    int protoHash4 = 37;
                    Object submessage2 = UnsafeUtil.getObject(message, offset);
                    if (submessage2 != null) {
                        protoHash4 = submessage2.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 50:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 51:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(message, offset)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Float.floatToIntBits(oneofFloatAt(message, offset));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case 56:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                    }
                    break;
                case 57:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case 58:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashBoolean(oneofBooleanAt(message, offset));
                    }
                    break;
                case 59:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    }
                    break;
                case 62:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case 64:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                    }
                    break;
                case 68:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    }
                    break;
            }
        }
        int pos2 = hashCode * 53;
        int hashCode4 = pos2 + this.unknownFieldSchema.getFromMessage(message).hashCode();
        if (this.hasExtensions) {
            return (hashCode4 * 53) + this.extensionSchema.getExtensions(message).hashCode();
        }
        return hashCode4;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, T other) {
        if (other == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(message, other, i);
        }
        if (!this.proto3) {
            SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
            if (this.hasExtensions) {
                SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
            }
        }
    }

    private void mergeSingleField(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        int number = numberAt(pos);
        switch (type(typeAndOffset)) {
            case 0:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putDouble(message, offset, UnsafeUtil.getDouble(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 1:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putFloat(message, offset, UnsafeUtil.getFloat(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 2:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 3:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 4:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 5:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 6:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 7:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putBoolean(message, offset, UnsafeUtil.getBoolean(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 8:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 9:
                mergeMessage(message, other, pos);
                break;
            case 10:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 11:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 12:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 13:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 14:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 15:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 16:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 17:
                mergeMessage(message, other, pos);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
            case 30:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                this.listFieldSchema.mergeListsAt(message, other, offset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, message, other, offset);
                break;
            case 51:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, number, pos);
                }
                break;
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                mergeOneofMessage(message, other, pos);
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
            case 62:
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
            case 64:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, number, pos);
                }
                break;
            case 68:
                mergeOneofMessage(message, other, pos);
                break;
        }
    }

    private void mergeMessage(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        if (!isFieldPresent(other, pos)) {
            return;
        }
        Object mine = UnsafeUtil.getObject(message, offset);
        Object theirs = UnsafeUtil.getObject(other, offset);
        if (mine != null && theirs != null) {
            Object merged = Internal.mergeMessage(mine, theirs);
            UnsafeUtil.putObject(message, offset, merged);
            setFieldPresent(message, pos);
        } else if (theirs != null) {
            UnsafeUtil.putObject(message, offset, theirs);
            setFieldPresent(message, pos);
        }
    }

    private void mergeOneofMessage(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        int number = numberAt(pos);
        long offset = offset(typeAndOffset);
        if (!isOneofPresent(other, number, pos)) {
            return;
        }
        Object mine = UnsafeUtil.getObject(message, offset);
        Object theirs = UnsafeUtil.getObject(other, offset);
        if (mine != null && theirs != null) {
            Object merged = Internal.mergeMessage(mine, theirs);
            UnsafeUtil.putObject(message, offset, merged);
            setOneofPresent(message, number, pos);
        } else if (theirs != null) {
            UnsafeUtil.putObject(message, offset, theirs);
            setOneofPresent(message, number, pos);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int getSerializedSize(T message) {
        return this.proto3 ? getSerializedSizeProto3(message) : getSerializedSizeProto2(message);
    }

    private int getSerializedSizeProto2(T message) {
        int size;
        int size2;
        int size3 = 0;
        Unsafe unsafe = UNSAFE;
        int currentPresenceFieldOffset = -1;
        int currentPresenceField = 0;
        for (int i = 0; i < this.buffer.length; i += 3) {
            int typeAndOffset = typeAndOffsetAt(i);
            int number = numberAt(i);
            int fieldType = type(typeAndOffset);
            int presenceMaskAndOffset = 0;
            int presenceMask = 0;
            if (fieldType > 17) {
                if (this.useCachedSizeField && fieldType >= FieldType.DOUBLE_LIST_PACKED.id() && fieldType <= FieldType.SINT64_LIST_PACKED.id()) {
                    presenceMaskAndOffset = this.buffer[i + 2] & OFFSET_MASK;
                }
            } else {
                presenceMaskAndOffset = this.buffer[i + 2];
                int presenceFieldOffset = presenceMaskAndOffset & OFFSET_MASK;
                presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                if (presenceFieldOffset != currentPresenceFieldOffset) {
                    currentPresenceFieldOffset = presenceFieldOffset;
                    currentPresenceField = unsafe.getInt(message, presenceFieldOffset);
                }
            }
            long offset = offset(typeAndOffset);
            switch (fieldType) {
                case 0:
                    size = size3;
                    int size4 = currentPresenceField & presenceMask;
                    if (size4 != 0) {
                        size3 = size + CodedOutputStream.computeDoubleSize(number, 0.0d);
                    }
                    break;
                case 1:
                    size = size3;
                    int size5 = currentPresenceField & presenceMask;
                    if (size5 != 0) {
                        size3 = size + CodedOutputStream.computeFloatSize(number, 0.0f);
                    }
                    break;
                case 2:
                    size = size3;
                    int size6 = currentPresenceField & presenceMask;
                    if (size6 != 0) {
                        size3 = size + CodedOutputStream.computeInt64Size(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 3:
                    size = size3;
                    int size7 = currentPresenceField & presenceMask;
                    if (size7 != 0) {
                        size3 = size + CodedOutputStream.computeUInt64Size(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 4:
                    size = size3;
                    int size8 = currentPresenceField & presenceMask;
                    if (size8 != 0) {
                        size3 = size + CodedOutputStream.computeInt32Size(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 5:
                    size = size3;
                    int size9 = currentPresenceField & presenceMask;
                    if (size9 != 0) {
                        size3 = size + CodedOutputStream.computeFixed64Size(number, 0L);
                    }
                    break;
                case 6:
                    size = size3;
                    int size10 = currentPresenceField & presenceMask;
                    if (size10 != 0) {
                        size3 = size + CodedOutputStream.computeFixed32Size(number, 0);
                    }
                    break;
                case 7:
                    size = size3;
                    int size11 = currentPresenceField & presenceMask;
                    if (size11 != 0) {
                        size3 = size + CodedOutputStream.computeBoolSize(number, true);
                    }
                    break;
                case 8:
                    size = size3;
                    int size12 = currentPresenceField & presenceMask;
                    if (size12 != 0) {
                        Object value = unsafe.getObject(message, offset);
                        if (value instanceof ByteString) {
                            size2 = size + CodedOutputStream.computeBytesSize(number, (ByteString) value);
                        } else {
                            size2 = size + CodedOutputStream.computeStringSize(number, (String) value);
                        }
                        size3 = size2;
                    }
                    break;
                case 9:
                    size = size3;
                    int size13 = currentPresenceField & presenceMask;
                    if (size13 != 0) {
                        size3 = size + SchemaUtil.computeSizeMessage(number, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case 10:
                    size = size3;
                    int size14 = currentPresenceField & presenceMask;
                    if (size14 != 0) {
                        size3 = size + CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(message, offset));
                    }
                    break;
                case 11:
                    size = size3;
                    int size15 = currentPresenceField & presenceMask;
                    if (size15 != 0) {
                        size3 = size + CodedOutputStream.computeUInt32Size(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 12:
                    size = size3;
                    int size16 = currentPresenceField & presenceMask;
                    if (size16 != 0) {
                        size3 = size + CodedOutputStream.computeEnumSize(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 13:
                    size = size3;
                    int size17 = currentPresenceField & presenceMask;
                    if (size17 != 0) {
                        size3 = size + CodedOutputStream.computeSFixed32Size(number, 0);
                    }
                    break;
                case 14:
                    size = size3;
                    int size18 = currentPresenceField & presenceMask;
                    if (size18 != 0) {
                        size3 = size + CodedOutputStream.computeSFixed64Size(number, 0L);
                    }
                    break;
                case 15:
                    size = size3;
                    int size19 = currentPresenceField & presenceMask;
                    if (size19 != 0) {
                        size3 = size + CodedOutputStream.computeSInt32Size(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 16:
                    size = size3;
                    int size20 = currentPresenceField & presenceMask;
                    if (size20 != 0) {
                        size3 = size + CodedOutputStream.computeSInt64Size(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 17:
                    size = size3;
                    int size21 = currentPresenceField & presenceMask;
                    if (size21 != 0) {
                        size3 = size + CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case 18:
                    size3 += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 19:
                    size3 += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 20:
                    size3 += SchemaUtil.computeSizeInt64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 21:
                    size3 += SchemaUtil.computeSizeUInt64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 22:
                    size3 += SchemaUtil.computeSizeInt32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 23:
                    size3 += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 24:
                    size3 += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 25:
                    size3 += SchemaUtil.computeSizeBoolList(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 26:
                    size3 += SchemaUtil.computeSizeStringList(number, (List) unsafe.getObject(message, offset));
                    continue;
                    break;
                case 27:
                    size3 += SchemaUtil.computeSizeMessageList(number, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    continue;
                    break;
                case 28:
                    size3 += SchemaUtil.computeSizeByteStringList(number, (List) unsafe.getObject(message, offset));
                    continue;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    size3 += SchemaUtil.computeSizeUInt32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 30:
                    size3 += SchemaUtil.computeSizeEnumList(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                    size3 += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 32:
                    size3 += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 33:
                    size3 += SchemaUtil.computeSizeSInt32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 34:
                    size3 += SchemaUtil.computeSizeSInt64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                    break;
                case 35:
                    size = size3;
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                    }
                    break;
                case 36:
                    size = size3;
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize2);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                    }
                    break;
                case 37:
                    size = size3;
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize3);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                    }
                    break;
                case 38:
                    size = size3;
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize4 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize4);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                    }
                    break;
                case 39:
                    size = size3;
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize5 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize5);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                    }
                    break;
                case 40:
                    size = size3;
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize6 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize6);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                    }
                    break;
                case 41:
                    size = size3;
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize7 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize7);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                    }
                    break;
                case 42:
                    size = size3;
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize8 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize8);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                    }
                    break;
                case 43:
                    size = size3;
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize9 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize9);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                    }
                    break;
                case 44:
                    size = size3;
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize10 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize10);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                    }
                    break;
                case 45:
                    size = size3;
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize11 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize11);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                    }
                    break;
                case 46:
                    size = size3;
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize12 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize12);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                    }
                    break;
                case 47:
                    size = size3;
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize13 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize13);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    size = size3;
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize14 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize14);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    size3 += SchemaUtil.computeSizeGroupList(number, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    continue;
                    break;
                case 50:
                    size3 += this.mapFieldSchema.getSerializedSize(number, unsafe.getObject(message, offset), getMapFieldDefaultEntry(i));
                    continue;
                    break;
                case 51:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeDoubleSize(number, 0.0d);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeFloatSize(number, 0.0f);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
                    }
                    break;
                case 56:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeFixed64Size(number, 0L);
                    }
                    break;
                case 57:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeFixed32Size(number, 0);
                    }
                    break;
                case 58:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeBoolSize(number, true);
                    }
                    break;
                case 59:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        Object value2 = unsafe.getObject(message, offset);
                        if (value2 instanceof ByteString) {
                            size3 += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                        } else {
                            size3 += CodedOutputStream.computeStringSize(number, (String) value2);
                        }
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += SchemaUtil.computeSizeMessage(number, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(message, offset));
                    }
                    break;
                case 62:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
                    }
                    break;
                case 64:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeSFixed32Size(number, 0);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeSFixed64Size(number, 0L);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
                    }
                    break;
                case 68:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                    } else {
                        size3 += CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                default:
                    size = size3;
                    break;
            }
            size3 = size;
        }
        int size22 = size3 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
        if (this.hasExtensions) {
            return size22 + this.extensionSchema.getExtensions(message).getSerializedSize();
        }
        return size22;
    }

    private int getSerializedSizeProto3(T message) {
        Unsafe unsafe = UNSAFE;
        int size = 0;
        for (int i = 0; i < this.buffer.length; i += 3) {
            int typeAndOffset = typeAndOffsetAt(i);
            int fieldType = type(typeAndOffset);
            int number = numberAt(i);
            long offset = offset(typeAndOffset);
            int cachedSizeOffset = (fieldType < FieldType.DOUBLE_LIST_PACKED.id() || fieldType > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.buffer[i + 2] & OFFSET_MASK;
            switch (fieldType) {
                case 0:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                    }
                    break;
                case 1:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                    }
                    break;
                case 2:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeInt64Size(number, UnsafeUtil.getLong(message, offset));
                    }
                    break;
                case 3:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeUInt64Size(number, UnsafeUtil.getLong(message, offset));
                    }
                    break;
                case 4:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeInt32Size(number, UnsafeUtil.getInt(message, offset));
                    }
                    break;
                case 5:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                    }
                    break;
                case 6:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                    }
                    break;
                case 7:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeBoolSize(number, true);
                    }
                    break;
                case 8:
                    if (isFieldPresent(message, i)) {
                        Object value = UnsafeUtil.getObject(message, offset);
                        if (value instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value);
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value);
                        }
                    }
                    break;
                case 9:
                    if (isFieldPresent(message, i)) {
                        size += SchemaUtil.computeSizeMessage(number, UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case 10:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) UnsafeUtil.getObject(message, offset));
                    }
                    break;
                case 11:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeUInt32Size(number, UnsafeUtil.getInt(message, offset));
                    }
                    break;
                case 12:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeEnumSize(number, UnsafeUtil.getInt(message, offset));
                    }
                    break;
                case 13:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                    }
                    break;
                case 14:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                    }
                    break;
                case 15:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSInt32Size(number, UnsafeUtil.getInt(message, offset));
                    }
                    break;
                case 16:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSInt64Size(number, UnsafeUtil.getLong(message, offset));
                    }
                    break;
                case 17:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case 18:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 19:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 20:
                    size += SchemaUtil.computeSizeInt64List(number, listAt(message, offset), false);
                    break;
                case 21:
                    size += SchemaUtil.computeSizeUInt64List(number, listAt(message, offset), false);
                    break;
                case 22:
                    size += SchemaUtil.computeSizeInt32List(number, listAt(message, offset), false);
                    break;
                case 23:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 24:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 25:
                    size += SchemaUtil.computeSizeBoolList(number, listAt(message, offset), false);
                    break;
                case 26:
                    size += SchemaUtil.computeSizeStringList(number, listAt(message, offset));
                    break;
                case 27:
                    size += SchemaUtil.computeSizeMessageList(number, listAt(message, offset), getMessageFieldSchema(i));
                    break;
                case 28:
                    size += SchemaUtil.computeSizeByteStringList(number, listAt(message, offset));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    size += SchemaUtil.computeSizeUInt32List(number, listAt(message, offset), false);
                    break;
                case 30:
                    size += SchemaUtil.computeSizeEnumList(number, listAt(message, offset), false);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 32:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 33:
                    size += SchemaUtil.computeSizeSInt32List(number, listAt(message, offset), false);
                    break;
                case 34:
                    size += SchemaUtil.computeSizeSInt64List(number, listAt(message, offset), false);
                    break;
                case 35:
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                    }
                    break;
                case 36:
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize2);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                    }
                    break;
                case 37:
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize3);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                    }
                    break;
                case 38:
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize4 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize4);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                    }
                    break;
                case 39:
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize5 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize5);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                    }
                    break;
                case 40:
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize6 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize6);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                    }
                    break;
                case 41:
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize7 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize7);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                    }
                    break;
                case 42:
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize8 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize8);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                    }
                    break;
                case 43:
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize9 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize9);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                    }
                    break;
                case 44:
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize10 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize10);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                    }
                    break;
                case 45:
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize11 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize11);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                    }
                    break;
                case 46:
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize12 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize12);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                    }
                    break;
                case 47:
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize13 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize13);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize14 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize14);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    size += SchemaUtil.computeSizeGroupList(number, listAt(message, offset), getMessageFieldSchema(i));
                    break;
                case 50:
                    size += this.mapFieldSchema.getSerializedSize(number, UnsafeUtil.getObject(message, offset), getMapFieldDefaultEntry(i));
                    break;
                case 51:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
                    }
                    break;
                case 56:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                    }
                    break;
                case 57:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                    }
                    break;
                case 58:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeBoolSize(number, true);
                    }
                    break;
                case 59:
                    if (isOneofPresent(message, number, i)) {
                        Object value2 = UnsafeUtil.getObject(message, offset);
                        if (value2 instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value2);
                        }
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (isOneofPresent(message, number, i)) {
                        size += SchemaUtil.computeSizeMessage(number, UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) UnsafeUtil.getObject(message, offset));
                    }
                    break;
                case 62:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
                    }
                    break;
                case 64:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
                    }
                    break;
                case 68:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
            }
        }
        return size + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        UT unknowns = schema.getFromMessage(message);
        return schema.getSerializedSize(unknowns);
    }

    private static List<?> listAt(Object message, long offset) {
        return (List) UnsafeUtil.getObject(message, offset);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(message, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(message, writer);
        } else {
            writeFieldsInAscendingOrderProto2(message, writer);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void writeFieldsInAscendingOrderProto2(T message, Writer writer) throws IOException {
        int i;
        Map.Entry<?, ?> entry;
        int currentPresenceField;
        int bufferLength;
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry entry2 = null;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.iterator();
                Map.Entry nextExtension = extensionIterator.next();
                entry2 = nextExtension;
            }
        }
        int currentPresenceFieldOffset = -1;
        int currentPresenceField2 = 0;
        int bufferLength2 = this.buffer.length;
        Unsafe unsafe = UNSAFE;
        int pos = 0;
        while (pos < bufferLength2) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int number = numberAt(pos);
            int fieldType = type(typeAndOffset);
            int presenceMask = 0;
            Iterator<? extends Map.Entry<?, ?>> extensionIterator2 = extensionIterator;
            if (!this.proto3 && fieldType <= 17) {
                int presenceMaskAndOffset = this.buffer[pos + 2];
                int presenceFieldOffset = OFFSET_MASK & presenceMaskAndOffset;
                if (presenceFieldOffset != currentPresenceFieldOffset) {
                    currentPresenceFieldOffset = presenceFieldOffset;
                    i = 1;
                    currentPresenceField2 = unsafe.getInt(message, presenceFieldOffset);
                } else {
                    i = 1;
                }
                presenceMask = i << (presenceMaskAndOffset >>> 20);
                entry = entry2;
            } else {
                i = 1;
                entry = entry2;
            }
            while (entry != null && this.extensionSchema.extensionNumber(entry) <= number) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = extensionIterator2.hasNext() ? (Map.Entry) extensionIterator2.next() : null;
            }
            Map.Entry<?, ?> entry3 = entry;
            long offset = offset(typeAndOffset);
            int currentPresenceFieldOffset2 = currentPresenceFieldOffset;
            switch (fieldType) {
                case 0:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeDouble(number, doubleAt(message, offset));
                    }
                    break;
                case 1:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeFloat(number, floatAt(message, offset));
                    }
                    break;
                case 2:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeInt64(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 3:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeUInt64(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 4:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeInt32(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 5:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeFixed64(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 6:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeFixed32(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 7:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeBool(number, booleanAt(message, offset));
                    }
                    break;
                case 8:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writeString(number, unsafe.getObject(message, offset), writer);
                    }
                    break;
                case 9:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        Object value = unsafe.getObject(message, offset);
                        writer.writeMessage(number, value, getMessageFieldSchema(pos));
                    }
                    break;
                case 10:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeBytes(number, (ByteString) unsafe.getObject(message, offset));
                    }
                    break;
                case 11:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeUInt32(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 12:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeEnum(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 13:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeSFixed32(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 14:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeSFixed64(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 15:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeSInt32(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 16:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeSInt64(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 17:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeGroup(number, unsafe.getObject(message, offset), getMessageFieldSchema(pos));
                    }
                    break;
                case 18:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField3 = numberAt(pos);
                    SchemaUtil.writeDoubleList(currentPresenceField3, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 19:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField4 = numberAt(pos);
                    SchemaUtil.writeFloatList(currentPresenceField4, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 20:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField5 = numberAt(pos);
                    SchemaUtil.writeInt64List(currentPresenceField5, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 21:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField6 = numberAt(pos);
                    SchemaUtil.writeUInt64List(currentPresenceField6, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 22:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField7 = numberAt(pos);
                    SchemaUtil.writeInt32List(currentPresenceField7, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 23:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField8 = numberAt(pos);
                    SchemaUtil.writeFixed64List(currentPresenceField8, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 24:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField9 = numberAt(pos);
                    SchemaUtil.writeFixed32List(currentPresenceField9, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 25:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField10 = numberAt(pos);
                    SchemaUtil.writeBoolList(currentPresenceField10, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 26:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeStringList(numberAt(pos), (List) unsafe.getObject(message, offset), writer);
                    break;
                case 27:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeMessageList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(pos));
                    break;
                case 28:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeBytesList(numberAt(pos), (List) unsafe.getObject(message, offset), writer);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField11 = numberAt(pos);
                    SchemaUtil.writeUInt32List(currentPresenceField11, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 30:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField12 = numberAt(pos);
                    SchemaUtil.writeEnumList(currentPresenceField12, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField13 = numberAt(pos);
                    SchemaUtil.writeSFixed32List(currentPresenceField13, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 32:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField14 = numberAt(pos);
                    SchemaUtil.writeSFixed64List(currentPresenceField14, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 33:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField15 = numberAt(pos);
                    SchemaUtil.writeSInt32List(currentPresenceField15, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 34:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField16 = numberAt(pos);
                    SchemaUtil.writeSInt64List(currentPresenceField16, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 35:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 36:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeFloatList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 37:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 38:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 39:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 40:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 41:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 42:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeBoolList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 43:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 44:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeEnumList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 45:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 46:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 47:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeGroupList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(pos));
                    break;
                case 50:
                    currentPresenceField = currentPresenceField2;
                    writeMapHelper(writer, number, unsafe.getObject(message, offset), pos);
                    bufferLength = bufferLength2;
                    break;
                case 51:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeDouble(number, oneofDoubleAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeFloat(number, oneofFloatAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeInt64(number, oneofLongAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeUInt64(number, oneofLongAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeInt32(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case 56:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeFixed64(number, oneofLongAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case 57:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeFixed32(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case 58:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeBool(number, oneofBooleanAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case 59:
                    currentPresenceField = currentPresenceField2;
                    if (isOneofPresent(message, number, pos)) {
                        writeString(number, unsafe.getObject(message, offset), writer);
                        bufferLength = bufferLength2;
                    } else {
                        bufferLength = bufferLength2;
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        Object value2 = unsafe.getObject(message, offset);
                        writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                        bufferLength = bufferLength2;
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeBytes(number, (ByteString) unsafe.getObject(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case 62:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeUInt32(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeEnum(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case 64:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeSFixed32(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeSFixed64(number, oneofLongAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeSInt32(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                    } else {
                        writer.writeSInt64(number, oneofLongAt(message, offset));
                        bufferLength = bufferLength2;
                    }
                    break;
                case 68:
                    if (!isOneofPresent(message, number, pos)) {
                        currentPresenceField = currentPresenceField2;
                        bufferLength = bufferLength2;
                    } else {
                        currentPresenceField = currentPresenceField2;
                        writer.writeGroup(number, unsafe.getObject(message, offset), getMessageFieldSchema(pos));
                        bufferLength = bufferLength2;
                    }
                    break;
                default:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    break;
            }
            pos += 3;
            entry2 = entry3;
            extensionIterator = extensionIterator2;
            currentPresenceFieldOffset = currentPresenceFieldOffset2;
            currentPresenceField2 = currentPresenceField;
            bufferLength2 = bufferLength;
        }
        Iterator<? extends Map.Entry<?, ?>> extensionIterator3 = extensionIterator;
        while (entry2 != null) {
            this.extensionSchema.serializeExtension(writer, entry2);
            entry2 = extensionIterator3.hasNext() ? (Map.Entry) extensionIterator3.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    private void writeFieldsInAscendingOrderProto3(T message, Writer writer) throws IOException {
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry entry = null;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.iterator();
                Map.Entry nextExtension = extensionIterator.next();
                entry = nextExtension;
            }
        }
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int number = numberAt(pos);
            while (entry != null && this.extensionSchema.extensionNumber(entry) <= number) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = extensionIterator.hasNext() ? (Map.Entry) extensionIterator.next() : null;
            }
            switch (type(typeAndOffset)) {
                case 0:
                    if (isFieldPresent(message, pos)) {
                        writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 1:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 2:
                    if (isFieldPresent(message, pos)) {
                        writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 3:
                    if (isFieldPresent(message, pos)) {
                        writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 4:
                    if (isFieldPresent(message, pos)) {
                        writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 5:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 6:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 7:
                    if (isFieldPresent(message, pos)) {
                        writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 8:
                    if (isFieldPresent(message, pos)) {
                        writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    }
                    break;
                case 9:
                    if (isFieldPresent(message, pos)) {
                        Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
                        writer.writeMessage(number, value, getMessageFieldSchema(pos));
                    }
                    break;
                case 10:
                    if (isFieldPresent(message, pos)) {
                        writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                    }
                    break;
                case 11:
                    if (isFieldPresent(message, pos)) {
                        writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 12:
                    if (isFieldPresent(message, pos)) {
                        writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 13:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 14:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 15:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 16:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 17:
                    if (isFieldPresent(message, pos)) {
                        writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                    }
                    break;
                case 18:
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                    break;
                case 50:
                    writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
                    break;
                case 51:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 56:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 57:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 58:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 59:
                    if (isOneofPresent(message, number, pos)) {
                        writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (isOneofPresent(message, number, pos)) {
                        Object value2 = UnsafeUtil.getObject(message, offset(typeAndOffset));
                        writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                    }
                    break;
                case 62:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 64:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 68:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                    }
                    break;
            }
        }
        while (entry != null) {
            this.extensionSchema.serializeExtension(writer, entry);
            entry = extensionIterator.hasNext() ? (Map.Entry) extensionIterator.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    private void writeFieldsInDescendingOrder(T message, Writer writer) throws IOException {
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry entry = null;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.descendingIterator();
                Map.Entry nextExtension = extensionIterator.next();
                entry = nextExtension;
            }
        }
        int pos = this.buffer.length;
        while (true) {
            pos -= 3;
            if (pos >= 0) {
                int typeAndOffset = typeAndOffsetAt(pos);
                int number = numberAt(pos);
                while (entry != null && this.extensionSchema.extensionNumber(entry) > number) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = extensionIterator.hasNext() ? (Map.Entry) extensionIterator.next() : null;
                }
                switch (type(typeAndOffset)) {
                    case 0:
                        if (isFieldPresent(message, pos)) {
                            writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 1:
                        if (isFieldPresent(message, pos)) {
                            writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 2:
                        if (isFieldPresent(message, pos)) {
                            writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 3:
                        if (isFieldPresent(message, pos)) {
                            writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 4:
                        if (isFieldPresent(message, pos)) {
                            writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 5:
                        if (isFieldPresent(message, pos)) {
                            writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 6:
                        if (isFieldPresent(message, pos)) {
                            writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 7:
                        if (isFieldPresent(message, pos)) {
                            writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 8:
                        if (isFieldPresent(message, pos)) {
                            writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        }
                        break;
                    case 9:
                        if (isFieldPresent(message, pos)) {
                            Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
                            writer.writeMessage(number, value, getMessageFieldSchema(pos));
                        }
                        break;
                    case 10:
                        if (isFieldPresent(message, pos)) {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                        }
                        break;
                    case 11:
                        if (isFieldPresent(message, pos)) {
                            writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 12:
                        if (isFieldPresent(message, pos)) {
                            writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 13:
                        if (isFieldPresent(message, pos)) {
                            writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 14:
                        if (isFieldPresent(message, pos)) {
                            writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 15:
                        if (isFieldPresent(message, pos)) {
                            writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 16:
                        if (isFieldPresent(message, pos)) {
                            writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 17:
                        if (isFieldPresent(message, pos)) {
                            writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        }
                        break;
                    case 18:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 19:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 20:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 21:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 22:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 23:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 24:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 25:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 26:
                        SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    case 27:
                        SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 28:
                        SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 30:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 32:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 33:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 34:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 35:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 36:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 37:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 38:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 39:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 40:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 41:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 42:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 43:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 44:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 45:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 46:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 47:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                        SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 50:
                        writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
                        break;
                    case 51:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 56:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 57:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 58:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 59:
                        if (isOneofPresent(message, number, pos)) {
                            writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        }
                        break;
                    case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                        if (isOneofPresent(message, number, pos)) {
                            Object value2 = UnsafeUtil.getObject(message, offset(typeAndOffset));
                            writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                        }
                        break;
                    case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                        }
                        break;
                    case 62:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 64:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 68:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        }
                        break;
                }
            } else {
                while (entry != null) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = extensionIterator.hasNext() ? (Map.Entry) extensionIterator.next() : null;
                }
                return;
            }
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int number, Object mapField, int pos) throws IOException {
        if (mapField != null) {
            writer.writeMap(number, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos)), this.mapFieldSchema.forMapData(mapField));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> schema, T message, Writer writer) throws IOException {
        schema.writeTo(schema.getFromMessage(message), writer);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 21701. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    private <UT, UB, ET extends androidx.datastore.preferences.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema<UT, UB> r18, androidx.datastore.preferences.protobuf.ExtensionSchema<ET> r19, T r20, androidx.datastore.preferences.protobuf.Reader r21, androidx.datastore.preferences.protobuf.ExtensionRegistryLite r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 2170
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema, androidx.datastore.preferences.protobuf.ExtensionSchema, java.lang.Object, androidx.datastore.preferences.protobuf.Reader, androidx.datastore.preferences.protobuf.ExtensionRegistryLite):void");
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object message) {
        UnknownFieldSetLite unknownFields = ((GeneratedMessageLite) message).unknownFields;
        if (unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite unknownFields2 = UnknownFieldSetLite.newInstance();
            ((GeneratedMessageLite) message).unknownFields = unknownFields2;
            return unknownFields2;
        }
        return unknownFields;
    }

    /* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.MessageSchema$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = new int[WireFormat.FieldType.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private int decodeMapEntryValue(byte[] data, int position, int limit, WireFormat.FieldType fieldType, Class<?> messageType, ArrayDecoders.Registers registers) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int position2 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return position2;
            case 2:
                return ArrayDecoders.decodeBytes(data, position, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(data, position));
                return position + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(data, position));
                return position + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(data, position));
                return position + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(data, position));
                return position + 4;
            case 9:
            case 10:
            case 11:
                int position3 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return position3;
            case 12:
            case 13:
                int position4 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return position4;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) messageType), data, position, limit, registers);
            case 15:
                int position5 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return position5;
            case 16:
                int position6 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return position6;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] data, int position, int limit, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map, ArrayDecoders.Registers registers) throws IOException {
        int tag;
        byte[] bArr = data;
        int position2 = ArrayDecoders.decodeVarint32(bArr, position, registers);
        int length = registers.int1;
        if (length < 0 || length > limit - position2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int end = position2 + length;
        K key = metadata.defaultKey;
        Object obj = key;
        Object obj2 = metadata.defaultValue;
        while (position2 < end) {
            int position3 = position2 + 1;
            int tag2 = bArr[position2];
            if (tag2 >= 0) {
                tag = tag2;
            } else {
                position3 = ArrayDecoders.decodeVarint32(tag2, bArr, position3, registers);
                tag = registers.int1;
            }
            int fieldNumber = tag >>> 3;
            int wireType = tag & 7;
            switch (fieldNumber) {
                case 1:
                    if (wireType != metadata.keyType.getWireType()) {
                        bArr = data;
                        position2 = ArrayDecoders.skipField(tag, bArr, position3, limit, registers);
                    } else {
                        bArr = data;
                        int position4 = decodeMapEntryValue(bArr, position3, limit, metadata.keyType, null, registers);
                        obj = registers.object1;
                        position2 = position4;
                    }
                    break;
                case 2:
                    if (wireType != metadata.valueType.getWireType()) {
                        bArr = data;
                        position2 = ArrayDecoders.skipField(tag, bArr, position3, limit, registers);
                    } else {
                        int position5 = decodeMapEntryValue(bArr, position3, limit, metadata.valueType, metadata.defaultValue.getClass(), registers);
                        obj2 = registers.object1;
                        bArr = data;
                        position2 = position5;
                    }
                    break;
                default:
                    position2 = ArrayDecoders.skipField(tag, bArr, position3, limit, registers);
                    break;
            }
        }
        if (position2 != end) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        map.put(obj, obj2);
        return end;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int parseRepeatedField(T t, byte[] data, int position, int limit, int tag, int number, int wireType, int bufferPosition, long typeAndOffset, int fieldType, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Internal.ProtobufList<?> list;
        int position2;
        Internal.ProtobufList<?> list2 = (Internal.ProtobufList) UNSAFE.getObject(t, fieldOffset);
        if (list2.isModifiable()) {
            list = list2;
        } else {
            int size = list2.size();
            Internal.ProtobufList<?> list3 = list2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            UNSAFE.putObject(t, fieldOffset, list3);
            list = list3;
        }
        switch (fieldType) {
            case 18:
            case 35:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedDoubleList(data, position, list, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeDoubleList(tag, data, position, limit, list, registers);
                }
                return position;
            case 19:
            case 36:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFloatList(data, position, list, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFloatList(tag, data, position, limit, list, registers);
                }
                return position;
            case 20:
            case 21:
            case 37:
            case 38:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint64List(data, position, list, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint64List(tag, data, position, limit, list, registers);
                }
                return position;
            case 22:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
            case 39:
            case 43:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint32List(data, position, list, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint32List(tag, data, position, limit, list, registers);
                }
                return position;
            case 23:
            case 32:
            case 40:
            case 46:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed64List(data, position, list, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeFixed64List(tag, data, position, limit, list, registers);
                }
                return position;
            case 24:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
            case 41:
            case 45:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed32List(data, position, list, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFixed32List(tag, data, position, limit, list, registers);
                }
                return position;
            case 25:
            case 42:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedBoolList(data, position, list, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeBoolList(tag, data, position, limit, list, registers);
                }
                return position;
            case 26:
                if (wireType != 2) {
                    return position;
                }
                if ((typeAndOffset & 536870912) == 0) {
                    return ArrayDecoders.decodeStringList(tag, data, position, limit, list, registers);
                }
                return ArrayDecoders.decodeStringListRequireUtf8(tag, data, position, limit, list, registers);
            case 27:
                if (wireType == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, list, registers);
                }
                return position;
            case 28:
                if (wireType == 2) {
                    return ArrayDecoders.decodeBytesList(tag, data, position, limit, list, registers);
                }
                return position;
            case 30:
            case 44:
                if (wireType == 2) {
                    position2 = ArrayDecoders.decodePackedVarint32List(data, position, list, registers);
                } else if (wireType == 0) {
                    position2 = ArrayDecoders.decodeVarint32List(tag, data, position, limit, list, registers);
                } else {
                    return position;
                }
                UnknownFieldSetLite unknownFields = ((GeneratedMessageLite) t).unknownFields;
                if (unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFields = null;
                }
                UnknownFieldSetLite unknownFields2 = (UnknownFieldSetLite) SchemaUtil.filterUnknownEnumList(number, (List<Integer>) list, getEnumFieldVerifier(bufferPosition), unknownFields, (UnknownFieldSchema<UT, UnknownFieldSetLite>) this.unknownFieldSchema);
                if (unknownFields2 != null) {
                    ((GeneratedMessageLite) t).unknownFields = unknownFields2;
                    return position2;
                }
                return position2;
            case 33:
            case 47:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt32List(data, position, list, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt32List(tag, data, position, limit, list, registers);
                }
                return position;
            case 34:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt64List(data, position, list, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt64List(tag, data, position, limit, list, registers);
                }
                return position;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                if (wireType == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, list, registers);
                }
                return position;
            default:
                return position;
        }
    }

    private <K, V> int parseMapField(T message, byte[] data, int position, int limit, int bufferPosition, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Object mapField;
        Unsafe unsafe = UNSAFE;
        Object mapDefaultEntry = getMapFieldDefaultEntry(bufferPosition);
        Object mapField2 = unsafe.getObject(message, fieldOffset);
        if (!this.mapFieldSchema.isImmutable(mapField2)) {
            mapField = mapField2;
        } else {
            Object mapField3 = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField3, mapField2);
            unsafe.putObject(message, fieldOffset, mapField3);
            mapField = mapField3;
        }
        return decodeMapEntry(data, position, limit, this.mapFieldSchema.forMapMetadata(mapDefaultEntry), this.mapFieldSchema.forMutableMapData(mapField), registers);
    }

    private int parseOneofField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int typeAndOffset, int fieldType, long fieldOffset, int bufferPosition, ArrayDecoders.Registers registers) throws IOException {
        long j;
        Object oldValue;
        Unsafe unsafe = UNSAFE;
        long oneofCaseOffset = this.buffer[bufferPosition + 2] & OFFSET_MASK;
        switch (fieldType) {
            case 51:
                if (wireType != 1) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Double.valueOf(ArrayDecoders.decodeDouble(data, position)));
                int position2 = position + 8;
                unsafe.putInt(message, oneofCaseOffset, number);
                return position2;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                if (wireType != 5) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Float.valueOf(ArrayDecoders.decodeFloat(data, position)));
                int position3 = position + 4;
                unsafe.putInt(message, oneofCaseOffset, number);
                return position3;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                if (wireType != 0) {
                    return position;
                }
                int position4 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Long.valueOf(registers.long1));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position4;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
            case 62:
                if (wireType != 0) {
                    return position;
                }
                int position5 = ArrayDecoders.decodeVarint32(data, position, registers);
                unsafe.putObject(message, fieldOffset, Integer.valueOf(registers.int1));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position5;
            case 56:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                if (wireType != 1) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Long.valueOf(ArrayDecoders.decodeFixed64(data, position)));
                int position6 = position + 8;
                unsafe.putInt(message, oneofCaseOffset, number);
                return position6;
            case 57:
            case 64:
                if (wireType != 5) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)));
                int position7 = position + 4;
                unsafe.putInt(message, oneofCaseOffset, number);
                return position7;
            case 58:
                if (wireType != 0) {
                    return position;
                }
                int position8 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Boolean.valueOf(registers.long1 != 0));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position8;
            case 59:
                if (wireType != 2) {
                    return position;
                }
                int position9 = ArrayDecoders.decodeVarint32(data, position, registers);
                int length = registers.int1;
                if (length == 0) {
                    unsafe.putObject(message, fieldOffset, "");
                } else {
                    if ((typeAndOffset & ENFORCE_UTF8_MASK) != 0 && !Utf8.isValidUtf8(data, position9, position9 + length)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    String value = new String(data, position9, length, Internal.UTF_8);
                    unsafe.putObject(message, fieldOffset, value);
                    position9 += length;
                }
                unsafe.putInt(message, oneofCaseOffset, number);
                return position9;
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                if (wireType != 2) {
                    return position;
                }
                int position10 = ArrayDecoders.decodeMessageField(getMessageFieldSchema(bufferPosition), data, position, limit, registers);
                Object oldValue2 = unsafe.getInt(message, oneofCaseOffset) == number ? unsafe.getObject(message, fieldOffset) : null;
                if (oldValue2 == null) {
                    unsafe.putObject(message, fieldOffset, registers.object1);
                } else {
                    unsafe.putObject(message, fieldOffset, Internal.mergeMessage(oldValue2, registers.object1));
                }
                unsafe.putInt(message, oneofCaseOffset, number);
                return position10;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                if (wireType != 2) {
                    return position;
                }
                int position11 = ArrayDecoders.decodeBytes(data, position, registers);
                unsafe.putObject(message, fieldOffset, registers.object1);
                unsafe.putInt(message, oneofCaseOffset, number);
                return position11;
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                if (wireType != 0) {
                    return position;
                }
                int position12 = ArrayDecoders.decodeVarint32(data, position, registers);
                int enumValue = registers.int1;
                Internal.EnumVerifier enumVerifier = getEnumFieldVerifier(bufferPosition);
                if (enumVerifier == null || enumVerifier.isInRange(enumValue)) {
                    unsafe.putObject(message, fieldOffset, Integer.valueOf(enumValue));
                    unsafe.putInt(message, oneofCaseOffset, number);
                } else {
                    getMutableUnknownFields(message).storeField(tag, Long.valueOf(enumValue));
                }
                return position12;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                if (wireType != 0) {
                    return position;
                }
                int position13 = ArrayDecoders.decodeVarint32(data, position, registers);
                unsafe.putObject(message, fieldOffset, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position13;
            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                if (wireType != 0) {
                    return position;
                }
                int position14 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position14;
            case 68:
                if (wireType != 3) {
                    return position;
                }
                int endTag = (tag & (-8)) | 4;
                int position15 = ArrayDecoders.decodeGroupField(getMessageFieldSchema(bufferPosition), data, position, limit, endTag, registers);
                if (unsafe.getInt(message, oneofCaseOffset) == number) {
                    j = fieldOffset;
                    oldValue = unsafe.getObject(message, j);
                } else {
                    j = fieldOffset;
                    oldValue = null;
                }
                if (oldValue == null) {
                    unsafe.putObject(message, j, registers.object1);
                } else {
                    unsafe.putObject(message, j, Internal.mergeMessage(oldValue, registers.object1));
                }
                unsafe.putInt(message, oneofCaseOffset, number);
                return position15;
            default:
                return position;
        }
    }

    private Schema getMessageFieldSchema(int pos) {
        int index = (pos / 3) * 2;
        Schema schema = (Schema) this.objects[index];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaSchemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[index + 1]);
        this.objects[index] = schemaSchemaFor;
        return schemaSchemaFor;
    }

    private Object getMapFieldDefaultEntry(int pos) {
        return this.objects[(pos / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int pos) {
        return (Internal.EnumVerifier) this.objects[((pos / 3) * 2) + 1];
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x008f. Please report as an issue. */
    int parseProto2Message(T message, byte[] data, int position, int limit, int endGroup, ArrayDecoders.Registers registers) throws IOException {
        int i;
        T t;
        Unsafe unsafe;
        MessageSchema<T> messageSchema;
        int currentPresenceFieldOffset;
        int position2;
        int tag;
        int pos;
        int position3;
        int currentPresenceFieldOffset2;
        int position4;
        int typeAndOffset;
        int tag2;
        int tag3;
        int position5;
        int currentPresenceFieldOffset3;
        int currentPresenceField;
        ArrayDecoders.Registers registers2;
        Unsafe unsafe2;
        int position6;
        int position7;
        int wireType;
        MessageSchema<T> messageSchema2 = this;
        T t2 = message;
        byte[] bArr = data;
        int currentPresenceField2 = limit;
        ArrayDecoders.Registers registers3 = registers;
        Unsafe unsafe3 = UNSAFE;
        int tag4 = 0;
        int wireType2 = 0;
        int number = 0;
        int oldNumber = -1;
        int oldNumber2 = -1;
        int position8 = position;
        while (true) {
            if (position8 >= currentPresenceField2) {
                i = endGroup;
                t = t2;
                unsafe = unsafe3;
                int currentPresenceFieldOffset4 = oldNumber2;
                messageSchema = messageSchema2;
                currentPresenceFieldOffset = currentPresenceFieldOffset4;
            } else {
                int position9 = position8 + 1;
                int tag5 = bArr[position8];
                if (tag5 >= 0) {
                    position2 = tag5;
                    tag = position9;
                } else {
                    int position10 = ArrayDecoders.decodeVarint32(tag5, bArr, position9, registers3);
                    int tag6 = registers3.int1;
                    position2 = tag6;
                    tag = position10;
                }
                int number2 = position2 >>> 3;
                int wireType3 = position2 & 7;
                if (number2 > oldNumber) {
                    int pos2 = messageSchema2.positionForFieldNumber(number2, wireType2 / 3);
                    pos = pos2;
                } else {
                    int pos3 = messageSchema2.positionForFieldNumber(number2);
                    pos = pos3;
                }
                oldNumber = number2;
                if (pos == -1) {
                    position3 = tag;
                    unsafe = unsafe3;
                    currentPresenceFieldOffset2 = oldNumber2;
                    position4 = number;
                    messageSchema = messageSchema2;
                    typeAndOffset = 0;
                    tag2 = position2;
                } else {
                    int typeAndOffset2 = messageSchema2.buffer[pos + 1];
                    int fieldType = type(typeAndOffset2);
                    long fieldOffset = offset(typeAndOffset2);
                    int position11 = tag;
                    if (fieldType <= 17) {
                        int presenceMaskAndOffset = messageSchema2.buffer[pos + 2];
                        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                        int presenceFieldOffset = presenceMaskAndOffset & OFFSET_MASK;
                        if (presenceFieldOffset != oldNumber2) {
                            if (oldNumber2 != -1) {
                                unsafe3.putInt(t2, oldNumber2, number);
                            }
                            oldNumber2 = presenceFieldOffset;
                            number = unsafe3.getInt(t2, presenceFieldOffset);
                        }
                        switch (fieldType) {
                            case 0:
                                position4 = number;
                                currentPresenceField = position11;
                                currentPresenceFieldOffset2 = oldNumber2;
                                T t3 = t2;
                                currentPresenceFieldOffset3 = wireType3;
                                registers2 = registers3;
                                unsafe2 = unsafe3;
                                if (currentPresenceFieldOffset3 == 1) {
                                    UnsafeUtil.putDouble(t3, fieldOffset, ArrayDecoders.decodeDouble(data, currentPresenceField));
                                    position8 = currentPresenceField + 8;
                                    number = position4 | presenceMask;
                                    bArr = data;
                                    currentPresenceField2 = limit;
                                    t2 = t3;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    unsafe3 = unsafe2;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                } else {
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            case 1:
                                position4 = number;
                                currentPresenceField = position11;
                                currentPresenceFieldOffset2 = oldNumber2;
                                T t4 = t2;
                                currentPresenceFieldOffset3 = wireType3;
                                registers2 = registers3;
                                unsafe2 = unsafe3;
                                if (currentPresenceFieldOffset3 == 5) {
                                    UnsafeUtil.putFloat(t4, fieldOffset, ArrayDecoders.decodeFloat(data, currentPresenceField));
                                    position8 = currentPresenceField + 4;
                                    number = position4 | presenceMask;
                                    bArr = data;
                                    currentPresenceField2 = limit;
                                    t2 = t4;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    unsafe3 = unsafe2;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                } else {
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            case 2:
                            case 3:
                                position4 = number;
                                currentPresenceField = position11;
                                Unsafe unsafe4 = unsafe3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                registers2 = registers3;
                                if (currentPresenceFieldOffset3 == 0) {
                                    int position12 = ArrayDecoders.decodeVarint64(data, currentPresenceField, registers2);
                                    unsafe4.putLong(message, fieldOffset, registers2.long1);
                                    int currentPresenceField3 = position4 | presenceMask;
                                    number = currentPresenceField3;
                                    position8 = position12;
                                    bArr = data;
                                    currentPresenceField2 = limit;
                                    t2 = message;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    unsafe3 = unsafe4;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                } else {
                                    unsafe2 = unsafe4;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            case 4:
                            case 11:
                                position4 = number;
                                currentPresenceField = position11;
                                Unsafe unsafe5 = unsafe3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                registers2 = registers3;
                                if (currentPresenceFieldOffset3 == 0) {
                                    position8 = ArrayDecoders.decodeVarint32(data, currentPresenceField, registers2);
                                    unsafe5.putInt(t2, fieldOffset, registers2.int1);
                                    number = position4 | presenceMask;
                                    unsafe3 = unsafe5;
                                    currentPresenceField2 = limit;
                                    bArr = data;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                } else {
                                    unsafe2 = unsafe5;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            case 5:
                            case 14:
                                Unsafe unsafe6 = unsafe3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                position4 = number;
                                registers2 = registers3;
                                if (currentPresenceFieldOffset3 != 1) {
                                    currentPresenceField = position11;
                                    unsafe2 = unsafe6;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                } else {
                                    T t5 = t2;
                                    unsafe6.putLong(t5, fieldOffset, ArrayDecoders.decodeFixed64(data, position11));
                                    t2 = t5;
                                    position8 = position11 + 8;
                                    number = position4 | presenceMask;
                                    unsafe3 = unsafe6;
                                    currentPresenceField2 = limit;
                                    bArr = data;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                }
                                break;
                            case 6:
                            case 13:
                                Unsafe unsafe7 = unsafe3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                position4 = number;
                                registers2 = registers3;
                                if (currentPresenceFieldOffset3 == 5) {
                                    unsafe7.putInt(t2, fieldOffset, ArrayDecoders.decodeFixed32(data, position11));
                                    position8 = position11 + 4;
                                    number = position4 | presenceMask;
                                    unsafe3 = unsafe7;
                                    currentPresenceField2 = limit;
                                    bArr = data;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                } else {
                                    currentPresenceField = position11;
                                    unsafe2 = unsafe7;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            case 7:
                                Unsafe unsafe8 = unsafe3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                position4 = number;
                                registers2 = registers3;
                                if (currentPresenceFieldOffset3 == 0) {
                                    position8 = ArrayDecoders.decodeVarint64(data, position11, registers2);
                                    UnsafeUtil.putBoolean(t2, fieldOffset, registers2.long1 != 0);
                                    number = position4 | presenceMask;
                                    unsafe3 = unsafe8;
                                    currentPresenceField2 = limit;
                                    bArr = data;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                } else {
                                    currentPresenceField = position11;
                                    unsafe2 = unsafe8;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            case 8:
                                Unsafe unsafe9 = unsafe3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                position4 = number;
                                registers2 = registers3;
                                if (currentPresenceFieldOffset3 == 2) {
                                    if ((typeAndOffset2 & ENFORCE_UTF8_MASK) == 0) {
                                        position6 = ArrayDecoders.decodeString(data, position11, registers2);
                                    } else {
                                        position6 = ArrayDecoders.decodeStringRequireUtf8(data, position11, registers2);
                                    }
                                    position8 = position6;
                                    unsafe9.putObject(t2, fieldOffset, registers2.object1);
                                    number = position4 | presenceMask;
                                    unsafe3 = unsafe9;
                                    currentPresenceField2 = limit;
                                    bArr = data;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                } else {
                                    currentPresenceField = position11;
                                    unsafe2 = unsafe9;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            case 9:
                                Unsafe unsafe10 = unsafe3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                position4 = number;
                                registers2 = registers3;
                                if (currentPresenceFieldOffset3 == 2) {
                                    position8 = ArrayDecoders.decodeMessageField(messageSchema2.getMessageFieldSchema(pos), data, position11, limit, registers2);
                                    if ((position4 & presenceMask) == 0) {
                                        unsafe10.putObject(t2, fieldOffset, registers2.object1);
                                    } else {
                                        unsafe10.putObject(t2, fieldOffset, Internal.mergeMessage(unsafe10.getObject(t2, fieldOffset), registers2.object1));
                                    }
                                    number = position4 | presenceMask;
                                    unsafe3 = unsafe10;
                                    currentPresenceField2 = limit;
                                    bArr = data;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                } else {
                                    currentPresenceField = position11;
                                    unsafe2 = unsafe10;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            case 10:
                                Unsafe unsafe11 = unsafe3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                position4 = number;
                                registers2 = registers3;
                                if (currentPresenceFieldOffset3 == 2) {
                                    position8 = ArrayDecoders.decodeBytes(data, position11, registers2);
                                    unsafe11.putObject(t2, fieldOffset, registers2.object1);
                                    number = position4 | presenceMask;
                                    unsafe3 = unsafe11;
                                    currentPresenceField2 = limit;
                                    bArr = data;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                } else {
                                    currentPresenceField = position11;
                                    unsafe2 = unsafe11;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            case 12:
                                Unsafe unsafe12 = unsafe3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                position4 = number;
                                registers2 = registers3;
                                if (currentPresenceFieldOffset3 == 0) {
                                    int position13 = ArrayDecoders.decodeVarint32(data, position11, registers2);
                                    int position14 = registers2.int1;
                                    Internal.EnumVerifier enumVerifier = messageSchema2.getEnumFieldVerifier(pos);
                                    if (enumVerifier == null || enumVerifier.isInRange(position14)) {
                                        unsafe12.putInt(t2, fieldOffset, position14);
                                        number = position4 | presenceMask;
                                        unsafe3 = unsafe12;
                                        currentPresenceField2 = limit;
                                        position8 = position13;
                                        bArr = data;
                                        wireType2 = pos;
                                        tag4 = position2;
                                        registers3 = registers2;
                                        oldNumber2 = currentPresenceFieldOffset2;
                                    } else {
                                        getMutableUnknownFields(t2).storeField(position2, Long.valueOf(position14));
                                        unsafe3 = unsafe12;
                                        currentPresenceField2 = limit;
                                        position8 = position13;
                                        bArr = data;
                                        wireType2 = pos;
                                        tag4 = position2;
                                        registers3 = registers2;
                                        number = position4;
                                        oldNumber2 = currentPresenceFieldOffset2;
                                    }
                                } else {
                                    currentPresenceField = position11;
                                    unsafe2 = unsafe12;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            case 15:
                                Unsafe unsafe13 = unsafe3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                position4 = number;
                                registers2 = registers3;
                                if (currentPresenceFieldOffset3 != 0) {
                                    unsafe2 = unsafe13;
                                    currentPresenceField = position11;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                } else {
                                    position8 = ArrayDecoders.decodeVarint32(data, position11, registers2);
                                    int position15 = registers2.int1;
                                    unsafe13.putInt(t2, fieldOffset, CodedInputStream.decodeZigZag32(position15));
                                    int currentPresenceField4 = position4 | presenceMask;
                                    unsafe3 = unsafe13;
                                    currentPresenceField2 = limit;
                                    number = currentPresenceField4;
                                    bArr = data;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers2;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                }
                                break;
                            case 16:
                                ArrayDecoders.Registers registers4 = registers3;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                position4 = number;
                                if (currentPresenceFieldOffset3 != 0) {
                                    registers2 = registers4;
                                    unsafe2 = unsafe3;
                                    currentPresenceField = position11;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                } else {
                                    int position16 = ArrayDecoders.decodeVarint64(data, position11, registers4);
                                    T t6 = t2;
                                    Unsafe unsafe14 = unsafe3;
                                    unsafe14.putLong(t6, fieldOffset, CodedInputStream.decodeZigZag64(registers4.long1));
                                    t2 = t6;
                                    int currentPresenceField5 = position4 | presenceMask;
                                    unsafe3 = unsafe14;
                                    currentPresenceField2 = limit;
                                    number = currentPresenceField5;
                                    bArr = data;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    registers3 = registers4;
                                    position8 = position16;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                }
                                break;
                            case 17:
                                if (wireType3 == 3) {
                                    int endTag = (number2 << 3) | 4;
                                    int currentPresenceFieldOffset5 = oldNumber2;
                                    int position17 = number;
                                    int position18 = ArrayDecoders.decodeGroupField(messageSchema2.getMessageFieldSchema(pos), data, position11, limit, endTag, registers3);
                                    ArrayDecoders.Registers registers5 = registers3;
                                    if ((position17 & presenceMask) == 0) {
                                        unsafe3.putObject(t2, fieldOffset, registers5.object1);
                                    } else {
                                        unsafe3.putObject(t2, fieldOffset, Internal.mergeMessage(unsafe3.getObject(t2, fieldOffset), registers5.object1));
                                    }
                                    int currentPresenceField6 = position17 | presenceMask;
                                    position8 = position18;
                                    bArr = data;
                                    registers3 = registers5;
                                    number = currentPresenceField6;
                                    wireType2 = pos;
                                    tag4 = position2;
                                    oldNumber2 = currentPresenceFieldOffset5;
                                    currentPresenceField2 = limit;
                                } else {
                                    currentPresenceFieldOffset2 = oldNumber2;
                                    currentPresenceFieldOffset3 = wireType3;
                                    position4 = number;
                                    currentPresenceField = position11;
                                    registers2 = registers3;
                                    unsafe2 = unsafe3;
                                    position3 = currentPresenceField;
                                    typeAndOffset = pos;
                                    tag2 = position2;
                                    registers3 = registers2;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                }
                                break;
                            default:
                                position4 = number;
                                currentPresenceField = position11;
                                currentPresenceFieldOffset2 = oldNumber2;
                                currentPresenceFieldOffset3 = wireType3;
                                registers2 = registers3;
                                unsafe2 = unsafe3;
                                position3 = currentPresenceField;
                                typeAndOffset = pos;
                                tag2 = position2;
                                registers3 = registers2;
                                messageSchema = messageSchema2;
                                unsafe = unsafe2;
                                break;
                        }
                    } else {
                        position4 = number;
                        currentPresenceFieldOffset2 = oldNumber2;
                        T t7 = t2;
                        ArrayDecoders.Registers registers6 = registers3;
                        Unsafe unsafe15 = unsafe3;
                        int i2 = currentPresenceField2;
                        if (fieldType == 27) {
                            if (wireType3 != 2) {
                                position7 = position11;
                                typeAndOffset = pos;
                                int tag7 = position2;
                                wireType = tag7;
                                unsafe = unsafe15;
                                registers3 = registers;
                                position3 = position7;
                                tag2 = wireType;
                                messageSchema = this;
                            } else {
                                Internal.ProtobufList<?> list = (Internal.ProtobufList) unsafe15.getObject(t7, fieldOffset);
                                if (!list.isModifiable()) {
                                    int size = list.size();
                                    list = list.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                    unsafe15.putObject(t7, fieldOffset, list);
                                }
                                int tag8 = position2;
                                int position19 = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(pos), tag8, data, position11, i2, list, registers6);
                                bArr = data;
                                currentPresenceField2 = limit;
                                registers3 = registers;
                                position8 = position19;
                                tag4 = tag8;
                                t2 = t7;
                                number = position4;
                                oldNumber2 = currentPresenceFieldOffset2;
                                wireType2 = pos;
                                unsafe3 = unsafe15;
                            }
                        } else {
                            position7 = position11;
                            typeAndOffset = pos;
                            int tag9 = position2;
                            if (fieldType <= 49) {
                                unsafe = unsafe15;
                                position8 = messageSchema2.parseRepeatedField(t7, data, position7, limit, tag9, number2, wireType3, typeAndOffset, typeAndOffset2, fieldType, fieldOffset, registers);
                                if (position8 != position7) {
                                    messageSchema2 = this;
                                    t2 = message;
                                    bArr = data;
                                    currentPresenceField2 = limit;
                                    registers3 = registers;
                                    wireType2 = typeAndOffset;
                                    tag4 = tag9;
                                    unsafe3 = unsafe;
                                    number = position4;
                                    oldNumber2 = currentPresenceFieldOffset2;
                                } else {
                                    registers3 = registers;
                                    position3 = position8;
                                    tag2 = tag9;
                                    messageSchema = this;
                                }
                            } else {
                                wireType = tag9;
                                unsafe = unsafe15;
                                if (fieldType == 50) {
                                    if (wireType3 == 2) {
                                        position8 = parseMapField(message, data, position7, limit, typeAndOffset, fieldOffset, registers);
                                        if (position8 != position7) {
                                            typeAndOffset = typeAndOffset;
                                            messageSchema2 = this;
                                            t2 = message;
                                            bArr = data;
                                            currentPresenceField2 = limit;
                                            registers3 = registers;
                                            wireType2 = typeAndOffset;
                                            tag4 = wireType;
                                            unsafe3 = unsafe;
                                            number = position4;
                                            oldNumber2 = currentPresenceFieldOffset2;
                                        } else {
                                            typeAndOffset = typeAndOffset;
                                            registers3 = registers;
                                            position3 = position8;
                                            tag2 = wireType;
                                            messageSchema = this;
                                        }
                                    } else {
                                        registers3 = registers;
                                        position3 = position7;
                                        tag2 = wireType;
                                        messageSchema = this;
                                    }
                                } else {
                                    tag2 = wireType;
                                    position8 = parseOneofField(message, data, position7, limit, tag2, number2, wireType3, typeAndOffset2, fieldType, fieldOffset, typeAndOffset, registers);
                                    typeAndOffset = typeAndOffset;
                                    messageSchema = this;
                                    registers3 = registers;
                                    if (position8 == position7) {
                                        position3 = position8;
                                    } else {
                                        t2 = message;
                                        bArr = data;
                                        currentPresenceField2 = limit;
                                        tag4 = tag2;
                                        wireType2 = typeAndOffset;
                                        messageSchema2 = messageSchema;
                                        unsafe3 = unsafe;
                                        number = position4;
                                        oldNumber2 = currentPresenceFieldOffset2;
                                    }
                                }
                            }
                        }
                    }
                }
                i = endGroup;
                if (tag2 == i && i != 0) {
                    t = message;
                    currentPresenceField2 = limit;
                    position8 = position3;
                    tag4 = tag2;
                    number = position4;
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                } else {
                    if (messageSchema.hasExtensions && registers3.extensionRegistry != ExtensionRegistryLite.getEmptyRegistry()) {
                        tag3 = tag2;
                        position5 = ArrayDecoders.decodeExtensionOrUnknownField(tag3, data, position3, limit, message, messageSchema.defaultInstance, messageSchema.unknownFieldSchema, registers3);
                        message = message;
                        currentPresenceField2 = limit;
                    } else {
                        tag3 = tag2;
                        position5 = ArrayDecoders.decodeUnknownField(tag3, data, position3, limit, getMutableUnknownFields(message), registers);
                        currentPresenceField2 = limit;
                    }
                    position8 = position5;
                    bArr = data;
                    tag4 = tag3;
                    t2 = message;
                    wireType2 = typeAndOffset;
                    messageSchema2 = messageSchema;
                    unsafe3 = unsafe;
                    number = position4;
                    oldNumber2 = currentPresenceFieldOffset2;
                    registers3 = registers;
                }
            }
        }
        if (currentPresenceFieldOffset != -1) {
            unsafe.putInt(t, currentPresenceFieldOffset, number);
        }
        UnknownFieldSetLite unknownFields = null;
        for (int i3 = messageSchema.checkInitializedCount; i3 < messageSchema.repeatedFieldOffsetStart; i3++) {
            unknownFields = (UnknownFieldSetLite) messageSchema.filterMapUnknownEnumValues(t, messageSchema.intArray[i3], unknownFields, messageSchema.unknownFieldSchema);
        }
        if (unknownFields != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(t, unknownFields);
        }
        if (i == 0) {
            if (position8 != currentPresenceField2) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (position8 > currentPresenceField2 || tag4 != i) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return position8;
    }

    private int parseProto3Message(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        int tag;
        int position2;
        int pos;
        Unsafe unsafe;
        int tag2;
        int oldNumber;
        int pos2;
        int position3;
        int position4;
        int wireType;
        int position5;
        MessageSchema<T> messageSchema = this;
        byte[] bArr = data;
        int wireType2 = limit;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe2 = UNSAFE;
        int tag3 = 0;
        int oldNumber2 = -1;
        int tag4 = position;
        while (tag4 < wireType2) {
            int position6 = tag4 + 1;
            int tag5 = bArr[tag4];
            if (tag5 >= 0) {
                tag = tag5;
                position2 = position6;
            } else {
                int position7 = ArrayDecoders.decodeVarint32(tag5, bArr, position6, registers2);
                int tag6 = registers2.int1;
                tag = tag6;
                position2 = position7;
            }
            int number = tag >>> 3;
            int wireType3 = tag & 7;
            if (number > oldNumber2) {
                pos = messageSchema.positionForFieldNumber(number, tag3 / 3);
            } else {
                int pos3 = messageSchema.positionForFieldNumber(number);
                pos = pos3;
            }
            if (pos == -1) {
                unsafe = unsafe2;
                tag2 = tag;
                oldNumber = number;
                pos2 = 0;
                position3 = position2;
            } else {
                int typeAndOffset = messageSchema.buffer[pos + 1];
                int fieldType = type(typeAndOffset);
                long fieldOffset = offset(typeAndOffset);
                if (fieldType <= 17) {
                    switch (fieldType) {
                        case 0:
                            oldNumber = number;
                            Unsafe unsafe3 = unsafe2;
                            if (wireType3 != 1) {
                                unsafe = unsafe3;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                UnsafeUtil.putDouble(message, fieldOffset, ArrayDecoders.decodeDouble(bArr, position2));
                                unsafe2 = unsafe3;
                                tag4 = position2 + 8;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 1:
                            oldNumber = number;
                            Unsafe unsafe4 = unsafe2;
                            if (wireType3 != 5) {
                                unsafe = unsafe4;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                UnsafeUtil.putFloat(message, fieldOffset, ArrayDecoders.decodeFloat(bArr, position2));
                                unsafe2 = unsafe4;
                                tag4 = position2 + 4;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 2:
                        case 3:
                            oldNumber = number;
                            Unsafe unsafe5 = unsafe2;
                            if (wireType3 != 0) {
                                unsafe = unsafe5;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                int position8 = ArrayDecoders.decodeVarint64(bArr, position2, registers2);
                                unsafe5.putLong(message, fieldOffset, registers2.long1);
                                unsafe2 = unsafe5;
                                tag4 = position8;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 4:
                        case 11:
                            oldNumber = number;
                            Unsafe unsafe6 = unsafe2;
                            if (wireType3 != 0) {
                                unsafe = unsafe6;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                int position9 = ArrayDecoders.decodeVarint32(bArr, position2, registers2);
                                unsafe6.putInt(message, fieldOffset, registers2.int1);
                                unsafe2 = unsafe6;
                                tag4 = position9;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 5:
                        case 14:
                            oldNumber = number;
                            Unsafe unsafe7 = unsafe2;
                            if (wireType3 != 1) {
                                unsafe = unsafe7;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                unsafe7.putLong(message, fieldOffset, ArrayDecoders.decodeFixed64(bArr, position2));
                                unsafe2 = unsafe7;
                                tag4 = position2 + 8;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 6:
                        case 13:
                            oldNumber = number;
                            Unsafe unsafe8 = unsafe2;
                            if (wireType3 != 5) {
                                unsafe = unsafe8;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                unsafe8.putInt(message, fieldOffset, ArrayDecoders.decodeFixed32(bArr, position2));
                                unsafe2 = unsafe8;
                                tag4 = position2 + 4;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 7:
                            oldNumber = number;
                            Unsafe unsafe9 = unsafe2;
                            if (wireType3 != 0) {
                                unsafe = unsafe9;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                int position10 = ArrayDecoders.decodeVarint64(bArr, position2, registers2);
                                UnsafeUtil.putBoolean(message, fieldOffset, registers2.long1 != 0);
                                unsafe2 = unsafe9;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                                tag4 = position10;
                            }
                            break;
                        case 8:
                            oldNumber = number;
                            Unsafe unsafe10 = unsafe2;
                            if (wireType3 != 2) {
                                unsafe = unsafe10;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                if ((ENFORCE_UTF8_MASK & typeAndOffset) == 0) {
                                    position5 = ArrayDecoders.decodeString(bArr, position2, registers2);
                                } else {
                                    position5 = ArrayDecoders.decodeStringRequireUtf8(bArr, position2, registers2);
                                }
                                unsafe10.putObject(message, fieldOffset, registers2.object1);
                                unsafe2 = unsafe10;
                                tag4 = position5;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 9:
                            oldNumber = number;
                            Unsafe unsafe11 = unsafe2;
                            if (wireType3 != 2) {
                                unsafe = unsafe11;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                int position11 = ArrayDecoders.decodeMessageField(messageSchema.getMessageFieldSchema(pos), bArr, position2, wireType2, registers2);
                                Object oldValue = unsafe11.getObject(message, fieldOffset);
                                if (oldValue == null) {
                                    unsafe11.putObject(message, fieldOffset, registers2.object1);
                                } else {
                                    unsafe11.putObject(message, fieldOffset, Internal.mergeMessage(oldValue, registers2.object1));
                                }
                                unsafe2 = unsafe11;
                                tag4 = position11;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 10:
                            oldNumber = number;
                            Unsafe unsafe12 = unsafe2;
                            if (wireType3 != 2) {
                                unsafe = unsafe12;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                int position12 = ArrayDecoders.decodeBytes(bArr, position2, registers2);
                                unsafe12.putObject(message, fieldOffset, registers2.object1);
                                unsafe2 = unsafe12;
                                tag4 = position12;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 12:
                            oldNumber = number;
                            Unsafe unsafe13 = unsafe2;
                            if (wireType3 != 0) {
                                unsafe = unsafe13;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                int position13 = ArrayDecoders.decodeVarint32(bArr, position2, registers2);
                                unsafe13.putInt(message, fieldOffset, registers2.int1);
                                unsafe2 = unsafe13;
                                tag4 = position13;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 15:
                            oldNumber = number;
                            Unsafe unsafe14 = unsafe2;
                            if (wireType3 != 0) {
                                unsafe = unsafe14;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                int position14 = ArrayDecoders.decodeVarint32(bArr, position2, registers2);
                                unsafe14.putInt(message, fieldOffset, CodedInputStream.decodeZigZag32(registers2.int1));
                                unsafe2 = unsafe14;
                                tag4 = position14;
                                tag3 = pos;
                                oldNumber2 = oldNumber;
                            }
                            break;
                        case 16:
                            if (wireType3 != 0) {
                                oldNumber = number;
                                unsafe = unsafe2;
                                position4 = position2;
                                wireType = wireType3;
                                pos2 = pos;
                                position3 = position4;
                                tag2 = tag;
                            } else {
                                int position15 = ArrayDecoders.decodeVarint64(bArr, position2, registers2);
                                unsafe2.putLong(message, fieldOffset, CodedInputStream.decodeZigZag64(registers2.long1));
                                unsafe2 = unsafe2;
                                tag4 = position15;
                                tag3 = pos;
                                oldNumber2 = number;
                            }
                            break;
                        default:
                            oldNumber = number;
                            unsafe = unsafe2;
                            position4 = position2;
                            wireType = wireType3;
                            pos2 = pos;
                            position3 = position4;
                            tag2 = tag;
                            break;
                    }
                } else {
                    oldNumber = number;
                    Unsafe unsafe15 = unsafe2;
                    if (fieldType == 27) {
                        if (wireType3 != 2) {
                            unsafe = unsafe15;
                            position4 = position2;
                            wireType = wireType3;
                            pos2 = pos;
                            position3 = position4;
                            tag2 = tag;
                        } else {
                            Internal.ProtobufList<?> list = (Internal.ProtobufList) unsafe15.getObject(message, fieldOffset);
                            if (!list.isModifiable()) {
                                int size = list.size();
                                list = list.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                unsafe15.putObject(message, fieldOffset, list);
                            }
                            int position16 = ArrayDecoders.decodeMessageList(messageSchema.getMessageFieldSchema(pos), tag, bArr, position2, wireType2, list, registers2);
                            bArr = data;
                            wireType2 = limit;
                            registers2 = registers;
                            tag4 = position16;
                            tag3 = pos;
                            oldNumber2 = oldNumber;
                            unsafe2 = unsafe15;
                        }
                    } else {
                        unsafe = unsafe15;
                        int tag7 = tag;
                        position4 = position2;
                        if (fieldType <= 49) {
                            int wireType4 = pos;
                            int position17 = messageSchema.parseRepeatedField(message, data, position4, limit, tag7, number, wireType3, wireType4, typeAndOffset, fieldType, fieldOffset, registers);
                            pos2 = wireType4;
                            if (position17 != position4) {
                                messageSchema = this;
                                bArr = data;
                                wireType2 = limit;
                                registers2 = registers;
                                tag4 = position17;
                                tag3 = pos2;
                                oldNumber2 = oldNumber;
                                unsafe2 = unsafe;
                            } else {
                                position3 = position17;
                                tag2 = tag7;
                            }
                        } else {
                            tag = tag7;
                            wireType = wireType3;
                            pos2 = pos;
                            if (fieldType == 50) {
                                if (wireType == 2) {
                                    int position18 = parseMapField(message, data, position4, limit, pos2, fieldOffset, registers);
                                    if (position18 != position4) {
                                        messageSchema = this;
                                        bArr = data;
                                        wireType2 = limit;
                                        registers2 = registers;
                                        tag4 = position18;
                                        tag3 = pos2;
                                        oldNumber2 = oldNumber;
                                        unsafe2 = unsafe;
                                    } else {
                                        position3 = position18;
                                        tag2 = tag;
                                    }
                                } else {
                                    position3 = position4;
                                    tag2 = tag;
                                }
                            } else {
                                tag2 = tag;
                                int position19 = parseOneofField(message, data, position4, limit, tag2, number, wireType, typeAndOffset, fieldType, fieldOffset, pos2, registers);
                                if (position19 == position4) {
                                    position3 = position19;
                                } else {
                                    messageSchema = this;
                                    bArr = data;
                                    wireType2 = limit;
                                    registers2 = registers;
                                    tag4 = position19;
                                    tag3 = pos2;
                                    oldNumber2 = oldNumber;
                                    unsafe2 = unsafe;
                                }
                            }
                        }
                    }
                }
            }
            tag4 = ArrayDecoders.decodeUnknownField(tag2, data, position3, limit, getMutableUnknownFields(message), registers);
            wireType2 = limit;
            messageSchema = this;
            bArr = data;
            registers2 = registers;
            tag3 = pos2;
            oldNumber2 = oldNumber;
            unsafe2 = unsafe;
        }
        if (tag4 != wireType2) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return tag4;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        if (this.proto3) {
            parseProto3Message(message, data, position, limit, registers);
        } else {
            parseProto2Message(message, data, position, limit, 0, registers);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void makeImmutable(T message) {
        for (int i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++) {
            long offset = offset(typeAndOffsetAt(this.intArray[i]));
            Object mapField = UnsafeUtil.getObject(message, offset);
            if (mapField != null) {
                UnsafeUtil.putObject(message, offset, this.mapFieldSchema.toImmutable(mapField));
            }
        }
        int length = this.intArray.length;
        for (int i2 = this.repeatedFieldOffsetStart; i2 < length; i2++) {
            this.listFieldSchema.makeImmutableListAt(message, this.intArray[i2]);
        }
        this.unknownFieldSchema.makeImmutable(message);
        if (this.hasExtensions) {
            this.extensionSchema.makeImmutable(message);
        }
    }

    private final <K, V> void mergeMap(Object message, int pos, Object mapDefaultEntry, ExtensionRegistryLite extensionRegistry, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(pos));
        Object mapField = UnsafeUtil.getObject(message, offset);
        if (mapField == null) {
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            UnsafeUtil.putObject(message, offset, mapField);
        } else if (this.mapFieldSchema.isImmutable(mapField)) {
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField, mapField);
            UnsafeUtil.putObject(message, offset, mapField);
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(mapField), this.mapFieldSchema.forMapMetadata(mapDefaultEntry), extensionRegistry);
    }

    private final <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        Internal.EnumVerifier enumFieldVerifier;
        int iNumberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        if (object == null || (enumFieldVerifier = getEnumFieldVerifier(i)) == null) {
            return ub;
        }
        return (UB) filterUnknownEnumMap(i, iNumberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema);
    }

    private final <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        MapEntryLite.Metadata<?, ?> metadataForMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.newBuilder();
                }
                ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(metadataForMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(codedBuilderNewCodedBuilder.getCodedOutput(), metadataForMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i2, codedBuilderNewCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public final boolean isInitialized(T message) {
        int currentPresenceFieldOffset = -1;
        int currentPresenceField = 0;
        for (int i = 0; i < this.checkInitializedCount; i++) {
            int pos = this.intArray[i];
            int number = numberAt(pos);
            int typeAndOffset = typeAndOffsetAt(pos);
            int presenceMask = 0;
            if (!this.proto3) {
                int presenceMaskAndOffset = this.buffer[pos + 2];
                int presenceFieldOffset = OFFSET_MASK & presenceMaskAndOffset;
                presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                if (presenceFieldOffset != currentPresenceFieldOffset) {
                    currentPresenceFieldOffset = presenceFieldOffset;
                    currentPresenceField = UNSAFE.getInt(message, presenceFieldOffset);
                }
            }
            if (isRequired(typeAndOffset) && !isFieldPresent(message, pos, currentPresenceField, presenceMask)) {
                return false;
            }
            switch (type(typeAndOffset)) {
                case 9:
                case 17:
                    if (isFieldPresent(message, pos, currentPresenceField, presenceMask) && !isInitialized(message, typeAndOffset, getMessageFieldSchema(pos))) {
                        return false;
                    }
                    break;
                    break;
                case 27:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    if (!isListInitialized(message, typeAndOffset, pos)) {
                        return false;
                    }
                    break;
                    break;
                case 50:
                    if (!isMapInitialized(message, typeAndOffset, pos)) {
                        return false;
                    }
                    break;
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                case 68:
                    if (isOneofPresent(message, number, pos) && !isInitialized(message, typeAndOffset, getMessageFieldSchema(pos))) {
                        return false;
                    }
                    break;
                    break;
            }
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(message).isInitialized();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object message, int typeAndOffset, Schema schema) {
        Object nested = UnsafeUtil.getObject(message, offset(typeAndOffset));
        return schema.isInitialized(nested);
    }

    private <N> boolean isListInitialized(Object message, int typeAndOffset, int pos) {
        List<N> list = (List) UnsafeUtil.getObject(message, offset(typeAndOffset));
        if (list.isEmpty()) {
            return true;
        }
        Schema schema = getMessageFieldSchema(pos);
        for (int i = 0; i < list.size(); i++) {
            N nested = list.get(i);
            if (!schema.isInitialized(nested)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.datastore.preferences.protobuf.Schema] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> mapForMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t, offset(i)));
        if (mapForMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? SchemaFor = 0;
        for (Object obj : mapForMapData.values()) {
            if (SchemaFor == 0) {
                SchemaFor = SchemaFor;
                SchemaFor = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            SchemaFor = SchemaFor;
            if (!SchemaFor.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writer.writeString(fieldNumber, (String) value);
        } else {
            writer.writeBytes(fieldNumber, (ByteString) value);
        }
    }

    private void readString(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readString());
        } else {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
        }
    }

    private void readStringList(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        }
    }

    private <E> void readMessageList(Object message, int typeAndOffset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        long offset = offset(typeAndOffset);
        reader.readMessageList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private <E> void readGroupList(Object message, long offset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private int numberAt(int pos) {
        return this.buffer[pos];
    }

    private int typeAndOffsetAt(int pos) {
        return this.buffer[pos + 1];
    }

    private int presenceMaskAndOffsetAt(int pos) {
        return this.buffer[pos + 2];
    }

    private static int type(int value) {
        return (FIELD_TYPE_MASK & value) >>> 20;
    }

    private static boolean isRequired(int value) {
        return (REQUIRED_MASK & value) != 0;
    }

    private static boolean isEnforceUtf8(int value) {
        return (ENFORCE_UTF8_MASK & value) != 0;
    }

    private static long offset(int value) {
        return OFFSET_MASK & value;
    }

    private static <T> double doubleAt(T message, long offset) {
        return UnsafeUtil.getDouble(message, offset);
    }

    private static <T> float floatAt(T message, long offset) {
        return UnsafeUtil.getFloat(message, offset);
    }

    private static <T> int intAt(T message, long offset) {
        return UnsafeUtil.getInt(message, offset);
    }

    private static <T> long longAt(T message, long offset) {
        return UnsafeUtil.getLong(message, offset);
    }

    private static <T> boolean booleanAt(T message, long offset) {
        return UnsafeUtil.getBoolean(message, offset);
    }

    private static <T> double oneofDoubleAt(T message, long offset) {
        return ((Double) UnsafeUtil.getObject(message, offset)).doubleValue();
    }

    private static <T> float oneofFloatAt(T message, long offset) {
        return ((Float) UnsafeUtil.getObject(message, offset)).floatValue();
    }

    private static <T> int oneofIntAt(T message, long offset) {
        return ((Integer) UnsafeUtil.getObject(message, offset)).intValue();
    }

    private static <T> long oneofLongAt(T message, long offset) {
        return ((Long) UnsafeUtil.getObject(message, offset)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T message, long offset) {
        return ((Boolean) UnsafeUtil.getObject(message, offset)).booleanValue();
    }

    private boolean arePresentForEquals(T message, T other, int pos) {
        return isFieldPresent(message, pos) == isFieldPresent(other, pos);
    }

    private boolean isFieldPresent(T message, int pos, int presenceField, int presenceMask) {
        if (this.proto3) {
            return isFieldPresent(message, pos);
        }
        return (presenceField & presenceMask) != 0;
    }

    private boolean isFieldPresent(T message, int pos) {
        if (this.proto3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    return UnsafeUtil.getDouble(message, offset) != 0.0d;
                case 1:
                    return UnsafeUtil.getFloat(message, offset) != 0.0f;
                case 2:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 3:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 4:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 5:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 6:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 7:
                    return UnsafeUtil.getBoolean(message, offset);
                case 8:
                    Object value = UnsafeUtil.getObject(message, offset);
                    if (value instanceof String) {
                        return true ^ ((String) value).isEmpty();
                    }
                    if (value instanceof ByteString) {
                        return true ^ ByteString.EMPTY.equals(value);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return UnsafeUtil.getObject(message, offset) != null;
                case 10:
                    return !ByteString.EMPTY.equals(UnsafeUtil.getObject(message, offset));
                case 11:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 12:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 13:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 14:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 15:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 16:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 17:
                    return UnsafeUtil.getObject(message, offset) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
        return (UnsafeUtil.getInt(message, (long) (OFFSET_MASK & presenceMaskAndOffset)) & presenceMask) != 0;
    }

    private void setFieldPresent(T message, int pos) {
        if (this.proto3) {
            return;
        }
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
        long presenceFieldOffset = OFFSET_MASK & presenceMaskAndOffset;
        UnsafeUtil.putInt(message, presenceFieldOffset, UnsafeUtil.getInt(message, presenceFieldOffset) | presenceMask);
    }

    private boolean isOneofPresent(T message, int fieldNumber, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt(message, (long) (OFFSET_MASK & presenceMaskAndOffset)) == fieldNumber;
    }

    private boolean isOneofCaseEqual(T message, T other, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt(message, (long) (presenceMaskAndOffset & OFFSET_MASK)) == UnsafeUtil.getInt(other, (long) (OFFSET_MASK & presenceMaskAndOffset));
    }

    private void setOneofPresent(T message, int fieldNumber, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        UnsafeUtil.putInt(message, OFFSET_MASK & presenceMaskAndOffset, fieldNumber);
    }

    private int positionForFieldNumber(int number) {
        if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(number, 0);
        }
        return -1;
    }

    private int positionForFieldNumber(int number, int min) {
        if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(number, min);
        }
        return -1;
    }

    private int slowPositionForFieldNumber(int number, int min) {
        int max = (this.buffer.length / 3) - 1;
        while (min <= max) {
            int mid = (max + min) >>> 1;
            int pos = mid * 3;
            int midFieldNumber = numberAt(pos);
            if (number == midFieldNumber) {
                return pos;
            }
            if (number < midFieldNumber) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    int getSchemaSize() {
        return this.buffer.length * 3;
    }
}
