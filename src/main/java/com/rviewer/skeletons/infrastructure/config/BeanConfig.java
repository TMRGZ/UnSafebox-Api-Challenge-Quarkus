package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.domain.repository.ItemRepository;
import com.rviewer.skeletons.domain.repository.SafeboxRepository;
import com.rviewer.skeletons.domain.service.ItemService;
import com.rviewer.skeletons.domain.service.PasswordManager;
import com.rviewer.skeletons.domain.service.SafeboxService;
import com.rviewer.skeletons.domain.service.impl.ItemServiceImpl;
import com.rviewer.skeletons.domain.service.impl.SafeboxServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SafeboxService safeboxService(SafeboxRepository safeboxRepository, PasswordManager passwordManager) {
        return new SafeboxServiceImpl(safeboxRepository, passwordManager);
    }

    @Bean
    public ItemService itemService(ItemRepository itemRepository) {
        return new ItemServiceImpl(itemRepository);
    }
}
