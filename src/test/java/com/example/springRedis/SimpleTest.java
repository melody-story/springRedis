package com.example.springRedis;

import com.example.springRedis.service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class SimpleTest {

    @Autowired
    private RankingService rankingService;

    @Test
    void getRanks() {
        /*
            redis를 sorted set 적용한 성능 테스트
        */
        rankingService.getTopRank(1);  //  의미없는 호출
        Instant before = Instant.now();     //  특정 시점의 시간
        /*
            1) user_100 의 랭킹 조회 성능 테스트
                > Rank(446237) - Took 6 ms
         */
        Long userRank = rankingService.getUserRanking("user_100");
        Duration elapsed = Duration.between(before, Instant.now());
        System.out.println(String.format("Rank(%d) - Took %d ms", userRank, elapsed.getNano() / 1000000));


        /*
            2) 상위 10명의 랭커들 조회
                > TopRankers - Took 3 ms
        */
        before = Instant.now();
        List<String> topRankers = rankingService.getTopRank(10);
        elapsed = Duration.between(before, Instant.now());
        System.out.println(String.format("TopRankers - Took %d ms", elapsed.getNano() / 1000000));
    }

    @Test
    void insertScore() {
        for (int i = 0; i < 1000000; i++) {
            int score = (int)(Math.random() * 1000000); // 0 ~ 999999
            String userId = "user_" + i;

            rankingService.setUserScore(userId, score);
        }
    }

    @Test
    void inMemorySortPerformance() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            int score = (int) (Math.random() * 1000000); // 0 ~ 999999
            list.add(score);
        }
        /*
            퀵솔트 정렬 알고리즘을 통한 오름차순 정렬 성능테스트
                > 347ms
        * */

        Instant before = Instant.now(); //  특정 시점의 시간
        Collections.sort(list);
        Duration elapsed = Duration.between(before, Instant.now());
        System.out.println((elapsed.getNano() / 1000000) + "ms"); // nm를 ms로 변환하기 위해

    }
}
