package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.domain.repository.ItemRepository;
import com.rviewer.skeletons.domain.repository.SafeboxRepository;
import com.rviewer.skeletons.domain.service.ItemService;
import com.rviewer.skeletons.domain.service.SafeboxService;
import com.rviewer.skeletons.domain.service.impl.ItemServiceImpl;
import com.rviewer.skeletons.domain.service.impl.SafeboxServiceImpl;
import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.Produces;

@Dependent
public class BeanConfig {

    @Produces
    public SafeboxService safeboxService(SafeboxRepository safeboxRepository) {
        return new SafeboxServiceImpl(safeboxRepository);
    }

    @Produces
    public ItemService itemService(ItemRepository itemRepository, SafeboxService safeboxService) {
        return new ItemServiceImpl(itemRepository, safeboxService);
    }
}
