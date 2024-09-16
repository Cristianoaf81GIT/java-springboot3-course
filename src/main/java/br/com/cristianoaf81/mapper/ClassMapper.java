package br.com.cristianoaf81.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

public class ClassMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = origin.stream().map(obj -> mapper.map(obj, destination))
                .collect(Collectors.toList());
        return destinationObjects;
    }

}
