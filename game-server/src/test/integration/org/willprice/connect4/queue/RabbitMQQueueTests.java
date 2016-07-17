package org.willprice.connect4.queue;

import org.junit.Before;
import org.junit.Test;
import org.willprice.connect4.players.HTTPPlayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RabbitMQQueueTests {

    private RabbitMQQueue queue;

    @Before
    public void setup() {
        queue = new RabbitMQQueue("localhost", 5672);
    }

    @Test
    public void whenThereAreTwoPlayersOnTheQueueGetReturnsAPlayerPair() {
        queue.add(new HTTPPlayer("example.com", 100));
        queue.add(new HTTPPlayer("example2.com", 100));

        assertNotNull(queue.get());
    }

    @Test
    public void messageContainsPlayersUrl() {
        HTTPPlayer player = new HTTPPlayer("example", 100);

        assertEquals("example:100", queue.formatMessage(player));
    }

}
