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

        Class<?> class1 = obj1.getClass();
        Class<?> class2 = obj2.getClass();
        Set<String> excludedFieldsNames = (fieldsNamesToExclude == null) ? new HashSet<>() : fieldsNamesToExclude;

        if (class1.equals(class2)) {
            return areObjectsWithSameClassWithTheSameFields(obj1, obj2, excludedFieldsNames);
        } else {
            return areObjectsWithDifferentClassesWithTheSameFields(obj1, obj2, excludedFieldsNames);
        }
    }

    private static boolean areObjectsWithSameClassWithTheSameFields(Object obj1, Object obj2, Set<String> excludedFieldsNames) throws IllegalAccessException {
        Class<?> clazz = obj1.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = field.getName();
            if (excludedFieldsNames.contains(fieldName)) {
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

    private static boolean areObjectsWithDifferentClassesWithTheSameFields(Object obj1, Object obj2, Set<String> excludedFieldsNames) throws IllegalAccessException {
        Map<String, Object> metadataFieldsObj1 = extractFieldsMetadata(obj1, excludedFieldsNames);
        Map<String, Object> metadataFieldsObj2 = extractFieldsMetadata(obj2, excludedFieldsNames);
        return metadataFieldsObj1.equals(metadataFieldsObj2);
    }

    private static Map<String, Object> extractFieldsMetadata(Object obj1, Set<String> excludedFieldsNames) throws IllegalAccessException {
        Field[] fields = obj1.getClass().getDeclaredFields();

        Map<String, Object> fieldsMetadata = new HashMap<>();

        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = field.getName();
            if (excludedFieldsNames.contains(fieldName)) {
                continue;
            }

            Object fieldValue = field.get(obj1);
            fieldsMetadata.put(fieldName, fieldValue);
        }

        return fieldsMetadata;
    }
}