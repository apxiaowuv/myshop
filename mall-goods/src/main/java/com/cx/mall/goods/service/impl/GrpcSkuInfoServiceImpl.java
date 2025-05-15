package com.cx.mall.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.cx.mall.goods.model.Stock;
import com.cx.mall.goods.service.ISkuInfoService;
import com.cx.mall.proto.Cart;
import com.cx.mall.proto.RequestParam;
import com.cx.mall.proto.ResponseResult;
import com.cx.mall.proto.RpcSimpleGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class GrpcSkuInfoServiceImpl extends RpcSimpleGrpc.RpcSimpleImplBase {
    @Autowired
    private ISkuInfoService skuInfoService;
    @Override
    public void dcount(RequestParam request, StreamObserver<ResponseResult> responseObserver)  {
        // 提取请求参数
        List<Cart> cartsList = request.getCartsList();
        // 将proto类Cart对象转换成Stock对象
        List<Stock> stocks = cartsList.stream().map(e -> JSON.parseObject(JSON.toJSONString(e), Stock.class))
                .collect(Collectors.toList());
        /*
         * 调用库存修改方法，对库存进行修改，并封装响应数据
         */
        try {
            skuInfoService.dcount(stocks);
            // 构建返回值
            ResponseResult response = ResponseResult.newBuilder()
                    .setCode(2000)
                    .build();
            // 填入返回内容
            responseObserver.onNext(response);
        } catch (Exception e) {
            ResponseResult response = ResponseResult.newBuilder()
                    .setCode(1000)
                    .setMessage(e.getMessage())
                    .build();
            responseObserver.onNext(response);
        }
        // gRPC服务完成
        responseObserver.onCompleted();
    }
}
