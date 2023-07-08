package com.rviewer.skeletons.infrastructure.mapper;

import com.rviewer.skeletons.domain.model.Item;
import com.rviewer.skeletons.infrastructure.persistence.dao.ItemDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDao map(Item item);

    Item map(ItemDao itemDao);

}
