package com.rviewer.skeletons.infrastructure.mapper;

import com.rviewer.skeletons.domain.model.Safebox;
import com.rviewer.skeletons.infrastructure.persistence.dao.SafeboxDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SafeboxMapper {

    SafeboxDao map(Safebox safebox);

    Safebox map(SafeboxDao safeboxDao);

}
