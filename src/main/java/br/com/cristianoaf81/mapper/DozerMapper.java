package br.com.cristianoaf81.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parserObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parserListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = origin.stream().map(obj -> mapper.map(obj, destination))
                .collect(Collectors.toList());
        return destinationObjects;
    }

}
