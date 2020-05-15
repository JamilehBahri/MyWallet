package ir.phgint.service;

import ir.phgint.domain.MerchantProfile;
import ir.phgint.domain.dto.MerchantProfileDto;
import ir.phgint.domain.repository.MerchantProfileDaoImpl;
import ir.phgint.mapper.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
 public class MerchantServicesImpl implements MerchantServices {


    @Autowired
    MerchantProfileDaoImpl merchantProfileDaoImpl;

    @Autowired
    private ObjectMapper<MerchantProfile, MerchantProfileDto> merchantProfileMapper;


    @Override
    public List<MerchantProfileDto> getAllMerchants() {
        List<MerchantProfileDto> merchantProfileDtos = new ArrayList<>();
        List<MerchantProfile> merchantProfiles =(List<MerchantProfile>) merchantProfileDaoImpl.findAll();

        for (MerchantProfile merchantProfile : merchantProfiles)
        {

            merchantProfileDtos.add( merchantProfileMapper.map(merchantProfile));
        }
        return merchantProfileDtos;
    }

    public MerchantProfileDto saveMerchant(MerchantProfileDto merchantProfileDto) {

        MerchantProfile merchantProfile = merchantProfileMapper.mapRevers(merchantProfileDto);
        MerchantProfile savedMerchantProfile = merchantProfileDaoImpl.save(merchantProfile);
        MerchantProfileDto merchant = merchantProfileMapper.map(savedMerchantProfile);

        return merchant;
    }

    @Override
    public MerchantProfileDto findMerchantById(long id) {

        MerchantProfile merchantProfile = merchantProfileDaoImpl.findOne(id);
        MerchantProfileDto merchantProfileDto = merchantProfileMapper.map(merchantProfile);
        return merchantProfileDto;
    }

    @Override
    public MerchantProfileDto updateMerchant(MerchantProfileDto merchantProfileDto) {

        MerchantProfile merchantProfile = merchantProfileMapper.mapRevers(merchantProfileDto);
        MerchantProfile savedMerchantProfile = merchantProfileDaoImpl.save(merchantProfile);
        MerchantProfileDto merchant = merchantProfileMapper.map(savedMerchantProfile);

        return merchant;
    }

    @Override
    public void deleteMerchant(MerchantProfileDto merchantProfileDto) {

        MerchantProfile merchantProfile = merchantProfileMapper.mapRevers(merchantProfileDto);
        merchantProfileDaoImpl.delete(merchantProfile);

    }

//    public MerchantProfile convertMerchantProfile(MerchantProfileDto merchantProfileDto) {
//        MerchantProfile merchantProfile= new MerchantProfile();
//        if (merchantProfileDto!= null) {
//            merchantProfile.setName(merchantProfileDto.getName());
//            merchantProfile.setFamily(merchantProfileDto.getFamily());
//            merchantProfile.setAddress(merchantProfileDto.getAddress());
//            merchantProfile.setPhone(merchantProfileDto.getPhone());
//            merchantProfile.setMobile(merchantProfileDto.getMobile());
//            merchantProfile.setEmail(merchantProfileDto.getEmail());
//            merchantProfile.setNationalId(merchantProfileDto.getNationalId());
//            merchantProfile.setDebitCardPan(merchantProfileDto.getDebitCardPan());
//            merchantProfile.setMerchantId(merchantProfileDto.getMerchantId());
//            merchantProfile.setWalletInvoices(merchantProfileDto.getWalletInvoices());
//            merchantProfile.setRegistrationTimestamp(merchantProfileDto.getRegistrationTimestamp());
//        }
//        return merchantProfile;
//    }
//
//    public MerchantProfileDto convertMerchantProfileDto(MerchantProfile merchantProfile) {
//        MerchantProfileDto merchantProfileDto= new MerchantProfileDto();
//        if (merchantProfile!= null) {
//            merchantProfileDto.setName(merchantProfile.getName());
//            merchantProfileDto.setFamily(merchantProfile.getFamily());
//            merchantProfileDto.setAddress(merchantProfile.getAddress());
//            merchantProfileDto.setPhone(merchantProfile.getPhone());
//            merchantProfileDto.setMobile(merchantProfile.getMobile());
//            merchantProfileDto.setEmail(merchantProfile.getEmail());
//            merchantProfileDto.setNationalId(merchantProfile.getNationalId());
//            merchantProfileDto.setDebitCardPan(merchantProfile.getDebitCardPan());
//            merchantProfileDto.setMerchantId(merchantProfile.getMerchantId());
//            merchantProfileDto.setWalletInvoices(merchantProfile.getWalletInvoices());
//            merchantProfileDto.setRegistrationTimestamp(merchantProfile.getRegistrationTimestamp());
//        }
//        return merchantProfileDto;
//    }


}
