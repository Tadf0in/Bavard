package model;

import java.util.EventListener;

public interface PapotageListener extends EventListener {
    void onPapotage(PapotageEvent event);
}
