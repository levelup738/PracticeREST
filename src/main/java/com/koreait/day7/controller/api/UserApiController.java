package com.koreait.day7.controller.api;

import com.koreait.day7.controller.CrudController;
import com.koreait.day7.ifs.CrudInterface;
import com.koreait.day7.model.entity.Users;
import com.koreait.day7.model.network.Header;
import com.koreait.day7.model.network.request.UserApiRequest;
import com.koreait.day7.model.network.response.UserApiResponse;
import com.koreait.day7.model.network.response.UserOrderInfoApiResponse;
import com.koreait.day7.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/user")    // localhost:9090/api/user
@RequiredArgsConstructor
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, Users> {

    private final UserApiLogicService userApiLogicService;

    @GetMapping("")
    public Header<List<UserApiResponse>> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable){
        log.info("{}", pageable);
        return userApiLogicService.search(pageable);
    }

    @GetMapping("/{id}/orderInfo")  // localhost:9090/api/user/21/orderInfo
    @Transactional
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
        return userApiLogicService.orderInfo(id);
    }
}
