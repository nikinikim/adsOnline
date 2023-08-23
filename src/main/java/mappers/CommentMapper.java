package mappers;

import DTOs.CommentDTO;
import entity.Comment;
import entity.User;
import org.mapstruct.*;


@Mapper(
        componentModel = "spring"
)
public abstract class CommentMapper implements GeneralMapper<Comment, CommentDTO>{
    @Override
    @Mappings({
            @Mapping(source = "commentDTO.author", target = "author",
                    qualifiedByName = "authorMapper")
    })
    public abstract Comment fromDto(CommentDTO commentDTO);

    @Override
    @Mapping(source = "author.id", target = "author")
    public abstract CommentDTO toDto(Comment comment);

    @Named("authorMapper")
    User authorMapper(Integer authorId) {
        if (authorId != null) {
            User user = new User();
            user.setId(Long.valueOf(authorId));
            return user;
        }
        return null;
    }
}
