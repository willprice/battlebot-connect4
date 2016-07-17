package org.willprice.connect4.gameserver;

public class GameServer implements GameListener {
    public static final int WAIT_TIME_BETWEEN_CHECKS_MS = 1000;
    private boolean waitingBetweenChecks = false;
    private GameCoordinator coordinator;

    public GameServer(GameCoordinator coordinator) {

        this.coordinator = coordinator;
    }

    public void serve() throws InterruptedException {
        while(true) {
            if (isWaitingBetweenChecks()) {
                Thread.sleep(WAIT_TIME_BETWEEN_CHECKS_MS);
            }
            coordinator.check();
        }
    }

    public boolean isWaitingBetweenChecks() {
        return waitingBetweenChecks;
    }

    public void noGameStarted() {
        waitingBetweenChecks = true;
    }

    @Override
    public void gameStarted() {
        waitingBetweenChecks = false;
    }
}
