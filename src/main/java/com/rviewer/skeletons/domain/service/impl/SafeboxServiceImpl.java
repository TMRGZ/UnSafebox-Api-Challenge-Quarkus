package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.exception.SafeboxDoesNotExistException;
import com.rviewer.skeletons.domain.model.Item;
import com.rviewer.skeletons.domain.model.Safebox;
import com.rviewer.skeletons.domain.repository.SafeboxRepository;
import com.rviewer.skeletons.domain.service.ItemService;
import com.rviewer.skeletons.domain.service.PasswordManager;
import com.rviewer.skeletons.domain.service.SafeboxService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SafeboxServiceImpl implements SafeboxService {

    private final SafeboxRepository safeboxRepository;

    private final PasswordManager passwordManager;

    private final ItemService itemService;

    @Override
    public Mono<Long> createSafebox(String safeboxName, String safeboxPassword) {
        return safeboxRepository.save(generateEncodedSafebox(safeboxName, safeboxPassword))
                .map(Safebox::getId);
    }

    private Safebox generateEncodedSafebox(String safeboxName, String safeboxPassword) {
        return Safebox.builder()
                .name(safeboxName)
                .password(passwordManager.encode(safeboxPassword))
                .build();
    }

    @Override
    public Mono<Safebox> openSafebox(String name, String password) {
        return safeboxRepository.findByNameIgnoreCase(name)
                .filter(safebox -> passwordManager.matches(password, safebox.getPassword()))
                .next();
    }


    @Override
    public void saveSafeboxItems(Long safeboxId, Flux<Item> itemsToSave) {
        safeboxRepository.findById(safeboxId)
                .switchIfEmpty(Mono.error(SafeboxDoesNotExistException::new))
                .thenMany(itemsToSave)
                .map(item -> addSafeboxToItem(safeboxId, item))
                .subscribe(itemService::save);
    }

    private Item addSafeboxToItem(Long safeboxId, Item item) {
        return item.toBuilder().safeboxId(safeboxId).build();
    }
}
