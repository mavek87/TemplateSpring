package com.matteoveroni.templatespring.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ObjectComparator {

    public static boolean areObjectsEqual(Object o1, Object o2) {
        return Objects.equals(o1, o2);
    }

    public static boolean areFieldsEqual(Object obj1, Object obj2) throws IllegalAccessException {
        return areFieldsEqualExcludingSome(obj1, obj2, new HashSet<>());
    }

    public static boolean areFieldsEqualExcludingSome(Object obj1, Object obj2, Set<String> fieldsNamesToExclude) throws IllegalAccessException {
        if (obj1 == null || obj2 == null) {
            return false;
        }

        Objects.requireNonNull(fieldsNamesToExclude, "Fields names to exclude set must not be null!");

        Class<?> class1 = obj1.getClass();
        Class<?> class2 = obj2.getClass();

        if (class1.equals(class2)) {
            return areObjectsWithSameClassWithTheSameFields(obj1, obj2, fieldsNamesToExclude, class1);
        } else {
            return areObjectsWithDifferentClassesWithTheSameFields(obj1, obj2, fieldsNamesToExclude);
        }
    }

    private static boolean areObjectsWithSameClassWithTheSameFields(Object obj1, Object obj2, Set<String> fieldsNamesToExclude, Class<?> clazz) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = field.getName();
            if (fieldsNamesToExclude.contains(fieldName)) {
                continue;
            }

            Object value1 = field.get(obj1);
            Object value2 = field.get(obj2);

            if (!Objects.equals(value1, value2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean areObjectsWithDifferentClassesWithTheSameFields(Object obj1, Object obj2, Set<String> fieldsNamesToExclude) throws IllegalAccessException {
        Map<String, Object> metadataFieldsObj1 = extractFieldsMetadata(obj1, fieldsNamesToExclude);
        Map<String, Object> metadataFieldsObj2 = extractFieldsMetadata(obj2, fieldsNamesToExclude);
        return metadataFieldsObj1.equals(metadataFieldsObj2);
    }

    private static Map<String, Object> extractFieldsMetadata(Object obj1, Set<String> fieldsNamesToExclude) throws IllegalAccessException {
        Field[] fields = obj1.getClass().getDeclaredFields();

        Map<String, Object> fieldsMetadata = new HashMap<>();

        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = field.getName();
            if (fieldsNamesToExclude.contains(fieldName)) {
                continue;
            }

            Object fieldValue = field.get(obj1);
            fieldsMetadata.put(fieldName, fieldValue);
        }

        return fieldsMetadata;
    }
}