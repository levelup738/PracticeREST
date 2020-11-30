package com.koreait.day7.service;

import com.koreait.day7.ifs.CrudInterface;
import com.koreait.day7.model.entity.Item;
import com.koreait.day7.model.entity.Users;
import com.koreait.day7.model.enumclass.ItemStatus;
import com.koreait.day7.model.network.Header;
import com.koreait.day7.model.network.request.ItemApiRequest;
import com.koreait.day7.model.network.request.UserApiRequest;
import com.koreait.day7.model.network.response.ItemApiResponse;
import com.koreait.day7.model.network.response.UserApiResponse;
import com.koreait.day7.repository.ItemRepository;
import com.koreait.day7.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    private final PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(body ->{
                    Item item = Item.builder()
                            .status(ItemStatus.valueOf(body.getStatus()))
                            .name(body.getName())
                            .title(body.getTitle())
                            .content(body.getContent())
                            .price(body.getPrice())
                            .brandName(body.getBrandName())
                            .registeredAt(LocalDateTime.now())
                            .partner(partnerRepository.getOne(body.getPartnerId()))
                            .build();

                    return item;
                })
                .map(newItem -> baseRepository.save(newItem))
                .map(newItem -> response(newItem))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(item -> {
                    baseRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header<ItemApiResponse> response(Item item){
        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(String.valueOf(item.getStatus()))
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();
        return Header.OK(body);
    }
}
