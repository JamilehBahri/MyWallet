package ir.phgint.mapper.converter;


import ir.phgint.domain.TransactionType;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("TransactionTypeConverter")
@Scope("singleton")
public class TransactionTypeConverter extends BidirectionalConverter<String, TransactionType> {

    @Override
    public TransactionType convertTo(String s, Type<TransactionType> type) {
        return TransactionType.valueOf(s);
    }

    @Override
    public String convertFrom(TransactionType transactionType, Type<String> type) {
        return transactionType.name();
    }
}
