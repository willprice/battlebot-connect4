package org.willprice.connect4.queue;

import com.rabbitmq.client.*;
import org.willprice.connect4.gameserver.PlayerPair;
import org.willprice.connect4.gameserver.Queue;
import org.willprice.connect4.players.HTTPPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class RabbitMQQueue implements Queue<HTTPPlayer> {
    public static final String PLAYER_QUEUE_NAME = "HTTP Players";
    private final ConnectionFactory factory;
    private Channel channel;
    private Connection connection;
    private final int numberOfPlayers = 2;

    public RabbitMQQueue(String host, int port) {
        factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
    }

    @Override
    public PlayerPair get() {
        try {
            openQueue();
            List<HTTPPlayer> players = readPlayers();
            closeQueue();
            return new PlayerPair(players.get(0), players.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(HTTPPlayer player) {
        try {
            openQueue();
            writePlayer(player);
            closeQueue();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    private void writePlayer(HTTPPlayer player) throws IOException {
        channel.basicPublish("", PLAYER_QUEUE_NAME, null, formatMessage(player).getBytes());
    }

    String formatMessage(HTTPPlayer player) {
        return player.getAddress();
    }

    private List<HTTPPlayer> readPlayers() throws IOException {
        List<HTTPPlayer> players = Collections.synchronizedList(new ArrayList<HTTPPlayer>());
        for (int i = 0; i < numberOfPlayers; i++) {
            GetResponse message = channel.basicGet(PLAYER_QUEUE_NAME, true);
            String playerAddress = new String(message.getBody(), "UTF-8");
            String HTTP_PROTOCOL = "http://";
            players.add(new HTTPPlayer(new URL(HTTP_PROTOCOL + playerAddress)));
        }
        return players;
    }

    private void closeQueue() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    private void openQueue() throws IOException, TimeoutException {
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(PLAYER_QUEUE_NAME, false, false, false, null);
    }
}
