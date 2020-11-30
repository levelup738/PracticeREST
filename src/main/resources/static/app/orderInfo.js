$(document).ready(function () {
    const id = $('#order_id').val();
    //console.log(id);
    const url = "/api/user/"+id+"/orderInfo";
    //console.log(url);
    $.ajax({
        type : "GET",
        url : url,
        success:function (result){
            //console.log(result.data);
            const orderList = result.data.user_api_response.order_group_api_response_list;
            //console.log(orderList);
            let html = "";
            if(orderList.length > 0){
                for(let i = 0; i < orderList.length; i++){
                    html += "<tr role=\"row\" class=\"odd\">";
                    html += '<td class="text-center">'+orderList[i].id+'</td>';
                    html += '<td class="text-center">'+orderList[i].status+'</td>';
                    html += '<td class="text-center">'+orderList[i].order_type+'</td>';
                    html += '<td class="text-center">'+orderList[i].rev_address+'</td>';
                    html += '<td class="text-center">'+orderList[i].rev_name+'</td>';
                    html += '<td class="text-center">'+orderList[i].payment_type+'</td>';
                    html += '<td class="text-center">'+orderList[i].total_price+'</td>';
                    html += '<td class="text-center">'+orderList[i].total_quantity+'</td>';
                    html += '<td class="text-center">'+orderList[i].order_at+'</td>';
                }
            }else{
                html += '<p>주문이 없습니다.</p>'
            }
            $('#itemList').html(html);
        },
        error:function (){
            console.log("orderInfo error!");
        }
    })
});