package ir.phgint.service;

import ir.phgint.domain.dto.MerchantProfileDto;

import java.util.List;

public interface MerchantServices {

     MerchantProfileDto saveMerchant(MerchantProfileDto merchantProfileDto);
     MerchantProfileDto findMerchantById(long id);
     MerchantProfileDto updateMerchant(MerchantProfileDto merchantProfileDto);
     void deleteMerchant(MerchantProfileDto merchantProfileDto);

     List<MerchantProfileDto> getAllMerchants();

}
