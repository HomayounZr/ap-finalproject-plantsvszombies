package appStart;

/**
 * This Class Holds Initial Values of the Game
 * Like The Amount of Speed , Hp , ... OF Different Elements
 *
 */
public class Configurations {

    // different speeds;
    private static final double ZOMBIE_NORMAL_SPEED_EASY = 4;
    private static final double ZOMBIE_CONEHEAD_SPEED_EASY = 3.5;
    private static final double ZOMBIE_BUCKETHEAD_SPEED_EASY = 3.5;
    private static final double ZOMBIE_FOOTBALL_SPEED_EASY = 3;

    private static final double ZOMBIE_NORMAL_SPEED_HARD = 4;
    private static final double ZOMBIE_CONEHEAD_SPEED_HARD = 3;
    private static final double ZOMBIE_BUCKETHEAD_SPEED_HARD = 3;
    private static final double ZOMBIE_FOOTBALL_SPEED_HARD = 2;

    public static double zombieNormalSpeed = ZOMBIE_NORMAL_SPEED_EASY;
    public static double zombieConeHeadSpeed = ZOMBIE_CONEHEAD_SPEED_EASY;
    public static double zombieBucketHeadSpeed = ZOMBIE_BUCKETHEAD_SPEED_EASY;
    public static double zombieFootballSpeed = ZOMBIE_FOOTBALL_SPEED_EASY;

    // different damages per second
    private static final int ZOMBIE_NORMAL_DAMAGE_EASY = 5;
    private static final int ZOMBIE_CONEHEAD_DAMAGE_EASY = 10;
    private static final int ZOMBIE_BUCKETHEAD_DAMAGE_EASY = 20;
    private static final int ZOMBIE_FOOTBALL_DAMAGE_EASY = 25;

    private static final int ZOMBIE_NORMAL_DAMAGE_HARD = 5;
    private static final int ZOMBIE_CONEHEAD_DAMAGE_HARD = 15;
    private static final int ZOMBIE_BUCKETHEAD_DAMAGE_HARD = 25;
    private static final int ZOMBIE_FOOTBALL_DAMAGE_HARD = 30;

    public static int zombieNormalDamage = ZOMBIE_NORMAL_DAMAGE_EASY;
    public static int zombieConeHeadDamage = ZOMBIE_CONEHEAD_DAMAGE_EASY;
    public static int zombieBucketHeadDamage = ZOMBIE_BUCKETHEAD_DAMAGE_EASY;
    public static int zombieFootballDamage = ZOMBIE_FOOTBALL_DAMAGE_EASY;

    // sun producing interval from sky
    private static final int SUN_LOAD_SKY_EASY = 25;
    private static final int SUN_LOAD_SKY_HARD = 30;

    public static int sunLoadSky = SUN_LOAD_SKY_EASY;

    // sun producing interval from sunflower plant
    private static final int SUN_LOAD_PLANT_EASY = 20;
    private static final int SUN_LOAD_PLANT_HARD = 25;

    public static int sunLoadPlant = SUN_LOAD_PLANT_EASY;

    // preferences for cards reloading time
    private static final double REALOAD_SUNFLOWER_EASY = 7.5;
    private static final double REALOAD_SUNFLOWER_HARD = 7.5;

    private static final double REALOAD_PEASHOOTER_EASY = 7.5;
    private static final double REALOAD_PEASHOOTER_HARD = 7.5;

    private static final double REALOAD_SNOWPEASHOOTER_EASY = 7.5;
    private static final double REALOAD_SNOWPEASHOOTER_HARD = 30;

    private static final double REALOAD_WALLNUT_EASY = 30;
    private static final double REALOAD_WALLNUT_HARD = 30;

    private static final double REALOAD_CHERRYBOMB_EASY = 30;
    private static final double REALOAD_CHERRYBOMB_HARD = 45;

    public static double reloadSunFlower = REALOAD_SNOWPEASHOOTER_EASY;
    public static double reloadPeaShooter = REALOAD_PEASHOOTER_EASY;
    public static double reloadSnowPeaShooter = REALOAD_SNOWPEASHOOTER_EASY;
    public static double reloadWallNut = REALOAD_WALLNUT_EASY;
    public static double reloadCherryBomb = REALOAD_CHERRYBOMB_EASY;

    // scores settings
    private static final int WINNING_EASY = 3;
    private static final int WINNING_HARD = 10;

    public static int winningScore = WINNING_EASY;

    private static final int LOSING_EASY = -1;
    private static final int LOSING_HARD = -3;

    public static int losingScore = LOSING_EASY;

    // to determine game has sound or not
    public static boolean hasSound = true;

    /**
     * Change game mode to easy
     */
    public static void changeToEasy(){
        zombieNormalSpeed = ZOMBIE_NORMAL_SPEED_EASY;
        zombieConeHeadSpeed = ZOMBIE_CONEHEAD_SPEED_EASY;
        zombieBucketHeadSpeed = ZOMBIE_BUCKETHEAD_SPEED_EASY;
        zombieFootballSpeed = ZOMBIE_FOOTBALL_SPEED_EASY;

        zombieNormalDamage = ZOMBIE_NORMAL_DAMAGE_EASY;
        zombieConeHeadDamage = ZOMBIE_CONEHEAD_DAMAGE_EASY;
        zombieBucketHeadDamage = ZOMBIE_BUCKETHEAD_DAMAGE_EASY;
        zombieFootballDamage = ZOMBIE_FOOTBALL_DAMAGE_EASY;

        sunLoadSky = SUN_LOAD_SKY_EASY;

        sunLoadPlant = SUN_LOAD_PLANT_EASY;

        reloadSunFlower = REALOAD_SNOWPEASHOOTER_EASY;
        reloadPeaShooter = REALOAD_PEASHOOTER_EASY;
        reloadSnowPeaShooter = REALOAD_SNOWPEASHOOTER_EASY;
        reloadWallNut = REALOAD_WALLNUT_EASY;
        reloadCherryBomb = REALOAD_CHERRYBOMB_EASY;

        winningScore = WINNING_EASY;
        losingScore = LOSING_EASY;
    }

    /**
     * Change game mode to hard
     */
    public static void changeToHard(){
        zombieNormalSpeed = ZOMBIE_NORMAL_SPEED_HARD;
        zombieConeHeadSpeed = ZOMBIE_CONEHEAD_SPEED_HARD;
        zombieBucketHeadSpeed = ZOMBIE_BUCKETHEAD_SPEED_HARD;
        zombieFootballSpeed = ZOMBIE_FOOTBALL_SPEED_EASY;

        zombieNormalDamage = ZOMBIE_NORMAL_DAMAGE_HARD;
        zombieConeHeadDamage = ZOMBIE_CONEHEAD_DAMAGE_HARD;
        zombieBucketHeadDamage = ZOMBIE_BUCKETHEAD_DAMAGE_HARD;
        zombieFootballDamage = ZOMBIE_FOOTBALL_DAMAGE_EASY;

        sunLoadSky = SUN_LOAD_SKY_HARD;

        sunLoadPlant = SUN_LOAD_PLANT_HARD;

        reloadSunFlower = REALOAD_SNOWPEASHOOTER_HARD;
        reloadPeaShooter = REALOAD_PEASHOOTER_HARD;
        reloadSnowPeaShooter = REALOAD_SNOWPEASHOOTER_HARD;
        reloadWallNut = REALOAD_WALLNUT_HARD;
        reloadCherryBomb = REALOAD_CHERRYBOMB_HARD;

        winningScore = WINNING_HARD;
        losingScore = LOSING_HARD;
    }
}
