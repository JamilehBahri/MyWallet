//package ir.phgint.mapper;
//
//import ma.glasnost.orika.MapperFactory;
//import ma.glasnost.orika.impl.DefaultMapperFactory;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.stereotype.Component;
//
///**
// * Default Orika MapperFactory of spring bean
// * Use the MapperFactory as a singleton One of the most expensive pieces of Orika
// * is the instantiation and initialization of the MapperFactory, and
// * the MapperFacade which is obtained from it.
// * The MapperFactory and MapperFacade are thread-safe objects which can safely be shared
// * as singletons within your own code.
// *
// * @author S.T
// */
//@Component("mapperFactoryBean")
//public class MapperFactoryBean implements FactoryBean<MapperFactory> {
//
//    @Override
//    public MapperFactory getObject() throws Exception {
//        return new DefaultMapperFactory.Builder().build();
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return MapperFactory.class;
//    }
//
//    @Override
//    public boolean isSingleton() {
//        return true;
//    }
//
//}
