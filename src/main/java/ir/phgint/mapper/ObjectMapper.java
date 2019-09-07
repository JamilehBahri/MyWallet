package ir.phgint.mapper;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;

/**
 *
 *
 */
public interface ObjectMapper<S, D>  {

    MapperFactory getMapperFactory();

    BoundMapperFacade<S, D> getBoundMapper();

    D map(S srcObject);

    S mapRevers(D dstObject);
}
