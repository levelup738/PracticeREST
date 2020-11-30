package com.koreait.day7.service;

import com.koreait.day7.ifs.CrudInterface;
import com.koreait.day7.model.entity.OrderGroup;
import com.koreait.day7.model.enumclass.OrderType;
import com.koreait.day7.model.network.Header;
import com.koreait.day7.model.network.request.ItemApiRequest;
import com.koreait.day7.model.network.request.OrderGroupApiRequest;
import com.koreait.day7.model.network.response.ItemApiResponse;
import com.koreait.day7.model.network.response.OrderGroupApiResponse;
import com.koreait.day7.repository.OrderGroupRepository;
import com.koreait.day7.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    private final UsersRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        return Optional.ofNullable(request.getData())
                .map(body ->{
                    OrderGroup orderGroup = OrderGroup.builder()
                            .status(body.getStatus())
                            .orderType(OrderType.valueOf(body.getOrderType()))
                            .revAddress(body.getRevAddress())
                            .revName(body.getRevName())
                            .paymentType(body.getPaymentType())
                            .totalPrice(body.getTotalPrice())
                            .totalQuantity(body.getTotalQuantity())
                            .orderAt(LocalDateTime.now())
                            .user(userRepository.getOne(body.getUserId()))
                            .build();
                    return orderGroup;
                })
                .map(newOrderGroup -> baseRepository.save(newOrderGroup))
                .map(newOrderGroup -> response(newOrderGroup))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> {
                    baseRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(String.valueOf(orderGroup.getOrderType()))
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header.OK(body);
    }
}