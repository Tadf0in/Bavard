package model;

import java.util.EventListener;
import java.util.List;
import java.util.Map;

public interface PapotageListener extends EventListener {
    void onPapotage(PapotageEvent event);
    String getNom();
    List<Map<String, String>> getMessagesRecus();
}
