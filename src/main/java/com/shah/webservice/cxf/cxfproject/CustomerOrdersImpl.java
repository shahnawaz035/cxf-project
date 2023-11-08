package com.shah.webservice.cxf.cxfproject;

import org.apache.cxf.feature.Features;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class CustomerOrdersImpl implements CustomerOrdersPortType {

    Map<BigInteger, List<Order>> customerOrders = new HashMap<>();
    int currentId;

    public CustomerOrdersImpl() {
        init();
    }

    public void init() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(BigInteger.valueOf(1));
        Product product = new Product();
        product.setId("1");
        product.setDescription("IPhone");
        product.setQuantity(BigInteger.TWO);
        order.getProduct().add(product);
        orders.add(order);
        customerOrders.put(BigInteger.valueOf(++currentId), orders);
    }

    @Override
    public GetOrdersResponse getOrders(GetOrdersRequest request) {
        GetOrdersResponse response = new GetOrdersResponse();
        response.getOrder().addAll(customerOrders.get(request.getCustomerId()));
        return response;
    }

    @Override
    public CreateOrdersResponse createOrders(CreateOrdersRequest request) {
        CreateOrdersResponse response = new CreateOrdersResponse();
        response.setResult(customerOrders.get(new BigInteger(request.getCustomerId())).add(request.getOrder()));
        return response;
    }
}
