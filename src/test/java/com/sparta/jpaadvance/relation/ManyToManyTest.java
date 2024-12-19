package com.sparta.jpaadvance.relation;

import com.sparta.jpaadvance.entity.Food;
import com.sparta.jpaadvance.entity.User;
import com.sparta.jpaadvance.repository.FoodRepository;
import com.sparta.jpaadvance.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class ManyToManyTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;


    @Test
    @Rollback(value = false)
    @DisplayName("N대M 단방향 테스트")
    void test1() {

        User user = new User();
        user.setName("Robbie");

        User user2 = new User();
        user2.setName("Robbert");

        Food food = new Food();
        food.setName("후라이드 치킨");
        food.setPrice(15000);
        food.getUserList().add(user);
        food.getUserList().add(user2);

        userRepository.save(user);
        userRepository.save(user2);
        foodRepository.save(food);

        // 자동으로 중간 테이블 orders 가 create 되고 insert 됨을 확인할 수 있습니다.
        /*
            또한 orders 테이블 같은 경우 직접 적으로 테이블을 생성한 것이 아닌 JPA를 통하여 생성된 중간 테이블
            이므로 참조된 컬럼은 외래키로만 구성이 됐다 나중에 중간 테이블에 값을 변경 시에 있어 오류가 생긴다.
         */
    }


}