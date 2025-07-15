package com.aireplye.aiwriter.mongoRepo;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aireplye.aiwriter.mongoEntity.RazorpayOrder;
import com.aireplye.aiwriter.mongoEntity.User;

@Repository
@Profile({"mongodb", "prod"})
public interface RazorpayOrderRepo extends MongoRepository<RazorpayOrder,String> {
    Optional<RazorpayOrder> findByOrderId(String orderId);
}
