package com.rviewer.skeletons.domain.service;

import java.util.List;

public interface SafeboxService {

    String createSafebox(String safeboxName, String safeboxPassword);

    List<String> getSafeboxItems(String safeboxId);

    void saveSafeboxItems(String safeboxId, List<String> itemsToSave);

}
