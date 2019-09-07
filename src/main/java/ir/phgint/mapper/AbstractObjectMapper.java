package ir.phgint.mapper;

import ir.phgint.mapper.converter.*;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;

/**
 *
 *
 */
@Component
public abstract class AbstractObjectMapper<S, D>  implements ObjectMapper<S,D> {

    @Autowired
    private MapperFactory mapperFactory;
    private BoundMapperFacade<S, D> mapperFacade = null;
    private boolean initMapperFacade = true;

    @Autowired
    private BirthdayConverter birthdayConverter;

    @Autowired
    private IdConverter idConverter;

    @Autowired
    private GenderConverter genderConverter;

    @Autowired
    private TransactionTypeConverter transactionTypeConverter;

    @Autowired
    private TimestampConverter timestampConverter;

    @Autowired
    private RoleConverter roleConverter;

    public AbstractObjectMapper() {
    }

    /**
     * SuppressWarnings("unchecked") because of unchecked cast of types of Source and Destination
     * to BoundMapperFacade<S, D>
     */
    @PostConstruct
    private void init() {
        ConverterFactory converterFactory = getMapperFactory().getConverterFactory();
        converterFactory.registerConverter("BirthdayConverter", birthdayConverter);
        converterFactory.registerConverter("IdConverter",idConverter);
        converterFactory.registerConverter("GenderConverter",genderConverter);
        converterFactory.registerConverter("TransactionTypeConverter",transactionTypeConverter);
        converterFactory.registerConverter("TimestampConverter",timestampConverter);
        converterFactory.registerConverter("RoleConverter",roleConverter);
        setupMapperFactory();
    }

    @SuppressWarnings("unchecked")
    private void initMapperFacade() {
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();

        mapperFacade = (BoundMapperFacade<S, D>)mapperFactory.
                getMapperFacade((Class<?>)type.getActualTypeArguments()[0],
                        (Class<?>)type.getActualTypeArguments()[1]);
    }
    /**
     * This method must be implemented by every ObjectMapper
     * for mapping between two types
     */
    protected abstract void setupMapperFactory();


    public MapperFactory getMapperFactory() {
        return mapperFactory;
    }

    @Override
    public BoundMapperFacade<S, D> getBoundMapper() {
        if(initMapperFacade) {
            initMapperFacade();
            initMapperFacade = false;
        }
        return mapperFacade;
    }


    @Override
    public S mapRevers(D dstObject) {
        return getBoundMapper().mapReverse(dstObject);
    }

    @Override
    public D map(S srcObject) {
        return getBoundMapper().map(srcObject);
    }
}
