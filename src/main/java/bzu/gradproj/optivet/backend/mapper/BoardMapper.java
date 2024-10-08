package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.BoardDTO;
import bzu.gradproj.optivet.backend.model.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    BoardDTO toBoardDTO(Board board);

//    @Mapping(target = "role", ignore = true) // The role will be set in the service
//    Board toBoardEntity(BoardDTO boardDTO);
}
