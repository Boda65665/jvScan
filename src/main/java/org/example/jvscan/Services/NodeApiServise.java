package org.example.jvscan.Services;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import node.api.grc.*;
import node.entity.Entity;

public class NodeApiServise {
    ManagedChannel managedChannel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
    NodeAPIServiseGrpc.NodeAPIServiseBlockingStub stub = NodeAPIServiseGrpc.newBlockingStub(managedChannel);

    public Entity.Block getBlock(int number){
        return getBlock(node.api.grc.NodeApi.GetBlockRequest.newBuilder().setNumber(number).build());
    }
    public Entity.Block getBlock(String hash){
        return getBlock(node.api.grc.NodeApi.GetBlockRequest.newBuilder().setHash(hash).build());
    }
    public Entity.Block getBlock(node.api.grc.NodeApi.GetBlockRequest getBlockRequest){
        try{
            return stub.getBlock(getBlockRequest).getBlock();
        }
        catch (Exception er){
            return null;
        }
    }

    public Entity.Transaction getTransaction(String hash) {

        try{
            return stub.getTransaction(NodeApi.GetTransactionRequest.newBuilder().setHash(hash).build()).getTransaction();
        }
        catch (Exception er){
            return null;
        }
    }
    public Entity.Address getAddress(String address){
        try{
            return stub.getAddress(NodeApi.GetAddressRequest.newBuilder().setAddress(address).build()).getAddress();
        }
        catch (Exception er){
            return null;
        }
    }
}
