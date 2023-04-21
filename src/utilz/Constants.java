package utilz;

public class Constants {

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{
        public static final int ATTACK_1 = 0;
        public static final int ATTACK_2 = 1;
        public static final int ATTACK_3 = 2;
        public static final int ATTACK_4 = 3;
        public static final int DEAD = 4;
        public static final int HURT = 5;
        public static final int IDLE = 6;
        public static final int JUMP = 7;
        public static final int RUN = 8;
        public static final int WALK = 9;

        public static int GetSpriteAmount(int player_action){
            switch (player_action) {
                case ATTACK_1:
                    return 6;

                case ATTACK_2:
                    return 3;

                case ATTACK_3:
                    return 3;

                case ATTACK_4:
                    return 10;

                case DEAD:
                    return 5;

                case HURT:
                    return 2;

                case IDLE:
                    return 5;

                case JUMP:
                    return 8;

                case RUN:
                    return 8;

                case WALK:
                    return 8;
            
                default:
                    return 0;
            }
        }
    }
}
