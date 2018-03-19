package com.example.gerardo.mapasregresoacasa;

import java.util.List;

/**
 * Created by Gerardo on 17/03/2018.
 */
public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
