package utils

trait Delayable {

    @SuppressWarnings('InsecureRandom')
    static randomDelay(def from, def to) {
        Math.abs(new Random().nextInt() % to) + from
    }

    static delay() {
        sleep(randomDelay(1000, 4000))
    }
}
