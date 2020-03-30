class Logic {

    static int gameMode;
    final static int GAME_MODE_BALLS = 1;
    final static int GAME_MODE_SQUARES = 2;
    static GameObject[] gameObjects;
    static int spritesCount;

    static void addGameObject(int x, int y) {
        if (gameMode == GAME_MODE_BALLS) {
            addGameObject(new Ball(x, y));
        } else {
            addGameObject(new Square(x, y));
        }
    }

    static void addGameObject(GameObject gameObject) {
        if (spritesCount == gameObjects.length) {
                GameObject[] temp = new GameObject[gameObjects.length * 2];
                System.arraycopy(gameObjects, 0, temp, 0, gameObjects.length);
                gameObjects = temp;
        }
        gameObjects[spritesCount++] = gameObject;
    }

    static void removeGameObject() {
        if (gameObjects.length == 1) {
            return;
        }
        if (spritesCount > 1) {
            spritesCount--;
        }
    }
}
