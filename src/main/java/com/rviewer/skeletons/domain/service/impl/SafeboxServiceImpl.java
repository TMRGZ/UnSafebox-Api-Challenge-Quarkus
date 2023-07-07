package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.exception.SafeboxDoesNotExistException;
import com.rviewer.skeletons.domain.model.Safebox;
import com.rviewer.skeletons.domain.repository.SafeboxRepository;
import com.rviewer.skeletons.domain.service.PasswordEncoder;
import com.rviewer.skeletons.domain.service.SafeboxAuthService;
import com.rviewer.skeletons.domain.service.SafeboxService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SafeboxServiceImpl implements SafeboxService, SafeboxAuthService {

    private final SafeboxRepository safeboxRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String createSafebox(String safeboxName, String safeboxPassword) {
        Safebox safebox = Safebox.builder()
                .name(safeboxName)
                .password(passwordEncoder.encode(safeboxPassword))
                .build();

        return safeboxRepository.save(safebox).getId();
    }

    @Override
    public List<String> openSafebox(String name, String password) {
        return safeboxRepository.findByNameIgnoreCase(name).stream()
                .filter(safebox -> passwordEncoder.matches(password, safebox.getPassword()))
                .map(Safebox::getId)
                .toList();
    }

    @Override
    public List<String> getSafeboxItems(String safeboxId) {
        return safeboxRepository.findById(safeboxId)
                .map(Safebox::getItemList)
                .orElseThrow(SafeboxDoesNotExistException::new);
    }

    @Override
    public void saveSafeboxItems(String safeboxId, List<String> itemsToSave) {
        safeboxRepository.findById(safeboxId).ifPresentOrElse(safebox -> {
            List<String> joinedItemLists = Stream.concat(safebox.getItemList().stream(), itemsToSave.stream())
                    .toList();
            safebox.setItemList(joinedItemLists);

            safeboxRepository.save(safebox);
        }, () -> {
            throw new SafeboxDoesNotExistException();
        });
    }
}
