package org.example;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {
    @Test
    public void testUuid(){
        for (int i = 0; i < 10000; i++) {
            System.out.println(UUID.randomUUID().toString());
        }
        /*
            4793fd87-1a70-4457-b0c9-3c08dc10179b
            27a23012-d367-43a2-b919-7fd4b774d48f
            96daaf77-8ed8-4da9-8633-265b289dffab
            e25aacaa-5eed-4068-adaa-a771560862fe
            ac2ec1fd-c261-4004-a7fa-31f8bc931407
            e3d8147d-c57c-451c-acb3-70e7a7ef0c9e
            3b3e03e2-4a77-4710-a5de-a6fcc273c71d
            1b2a51ce-e257-415d-b4a2-3086916c4d9a
         */
    }
}
