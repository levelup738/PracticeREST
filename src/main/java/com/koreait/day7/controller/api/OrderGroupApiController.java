package com.koreait.day7.controller.api;

import com.koreait.day7.controller.CrudController;
import com.koreait.day7.ifs.CrudInterface;
import com.koreait.day7.model.entity.OrderGroup;
import com.koreait.day7.model.entity.Users;
import com.koreait.day7.model.network.Header;
import com.koreait.day7.model.network.request.OrderGroupApiRequest;
import com.koreait.day7.model.network.request.UserApiRequest;
import com.koreait.day7.model.network.response.OrderGroupApiResponse;
import com.koreait.day7.model.network.response.UserApiResponse;
import com.koreait.day7.service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderGroup")
@RequiredArgsConstructor
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

}