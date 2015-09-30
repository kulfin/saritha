package fi.hut.soberit.agilefant.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.ImmutableMap;

public class BeanCopier {

    private static final ConcurrentMap<Class<?>, ImmutableMap<String, PropertyDescriptor>> CACHE = new ConcurrentHashMap<Class<?>, ImmutableMap<String, PropertyDescriptor>>();

    private static Map<String, PropertyDescriptor> getDescriptors(Class<?> clazz) {
        ImmutableMap<String, PropertyDescriptor> descriptors = CACHE.get(clazz);
        if (descriptors != null)
            return descriptors;

        ImmutableMap.Builder<String, PropertyDescriptor> builder = ImmutableMap.builder();
        BeanInfo sourceInfo;
        try {
            sourceInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new RuntimeException("Failed to introspect bean properties of " + clazz.getName());
        }

        for (PropertyDescriptor descriptor : sourceInfo.getPropertyDescriptors()) {
            if (!"class".equals(descriptor.getName()))
                builder.put(descriptor.getName(), descriptor);
        }
        descriptors = builder.build();
        CACHE.putIfAbsent(clazz, descriptors);
        return descriptors;
    }

    public static <T extends Object> void copy(T source, T destination) {
        Map<String, PropertyDescriptor> readDescriptors = getDescriptors(source.getClass());
        Map<String, PropertyDescriptor> writeDescriptors = getDescriptors(destination.getClass());

        for (String name : readDescriptors.keySet()) {
            PropertyDescriptor read = readDescriptors.get(name);
            PropertyDescriptor write = writeDescriptors.get(name);
            if (read == null || write == null)
                continue;

            Method readMethod = read.getReadMethod();
            Method writeMethod = write.getWriteMethod();
            if (readMethod == null || writeMethod == null)
                continue;

            try {
                Object value = readMethod.invoke(source);
                writeMethod.invoke(destination, value);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Failed to copy bean property %s.%s to %s.%s", source.getClass().getName(), name, destination
                        .getClass().getName(), name), e);
            }
        }
    }

}
