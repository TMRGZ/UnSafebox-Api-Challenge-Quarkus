package com.rviewer.skeletons.application.mapper;

import com.rviewer.skeletons.application.model.SafeboxItemDto;
import com.rviewer.skeletons.domain.model.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemDtoMapper {

    Item map(SafeboxItemDto safeboxItemDto);

    SafeboxItemDto map(Item item);

}
