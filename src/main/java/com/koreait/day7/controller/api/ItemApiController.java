package com.koreait.day7.controller.api;


import com.koreait.day7.controller.CrudController;
import com.koreait.day7.ifs.CrudInterface;
import com.koreait.day7.model.entity.Item;
import com.koreait.day7.model.entity.Users;
import com.koreait.day7.model.network.Header;
import com.koreait.day7.model.network.request.ItemApiRequest;
import com.koreait.day7.model.network.request.UserApiRequest;
import com.koreait.day7.model.network.response.ItemApiResponse;
import com.koreait.day7.model.network.response.UserApiResponse;
import com.koreait.day7.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

}
