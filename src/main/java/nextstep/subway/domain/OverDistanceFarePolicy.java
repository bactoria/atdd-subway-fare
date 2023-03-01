package nextstep.subway.domain;

import java.util.Arrays;

public enum OverDistanceFarePolicy {
    OVER_TEN_FARE(10, 50, 100, 5),
    OVER_FIFTY_FARE(50, Integer.MAX_VALUE, 100, 8);

    private int minKm;
    private int maxKm;
    private int farePer;
    private int perKm;

    OverDistanceFarePolicy(int minKm, int maxKm, int farePer, int perKm) {
        this.minKm = minKm;
        this.maxKm = maxKm;
        this.farePer = farePer;
        this.perKm = perKm;
    }

    public static int calculateOverDistanceFare(int distance) {
        return Arrays.stream(values())
                .mapToInt(it -> calculateOverDistanceFare(it, distance))
                .sum();
    }

    private static int calculateOverDistanceFare(OverDistanceFarePolicy policy, int totalDistance) {
        int overDistance = Math.max(Math.min(totalDistance, policy.maxKm) - policy.minKm, 0);
        return calculateOverDistanceFare(overDistance, policy.perKm, policy.farePer);
    }

    private static int calculateOverDistanceFare(int overDistance, int perKm, int farePer) {
        return (int) (Math.ceil((overDistance + perKm - 1) / perKm) * farePer);
    }
}