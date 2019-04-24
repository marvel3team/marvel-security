package com.marvel.common.redis.serializer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.Objects;

/**
 * @Classname RedisObjectSerializer
 * @Description Redis对象序列化处理类
 * @Date 2019-04-10 14:41
 * @Author zhongjie
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    // 为了方便进行对象与字节数组的转换，所以应该首先准备出两个转换器
    private Converter<Object, byte[]> serializingConverter = new SerializingConverter();
    private Converter<byte[], Object> deserializingConverter = new DeserializingConverter();
    // 做一个空数组，不是null
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (Objects.isNull(o)) {
            return EMPTY_BYTE_ARRAY;
        }
        return serializingConverter.convert(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return deserializingConverter.convert(bytes);
    }
}
