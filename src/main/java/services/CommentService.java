package services;

<<<<<<< HEAD:src/main/java/com/example/adsonline/services/CommentService.java
import com.example.adsonline.DTOs.CommentDTO;
import com.example.adsonline.entity.Comment;
=======
import DTOs.CommentDTO;
>>>>>>> Kristina_feature_23.08:src/main/java/services/CommentService.java

import java.util.List;

public interface CommentService {
    //CommentDTO mapToDTO(Comment comment);

    //Comment mapToEntity(CommentDTO commentDTO);

    List<CommentDTO> getCommentsByAdId(int adId);
    CommentDTO addComment(int adId, CommentDTO comment);
    CommentDTO updateComment(int adId, int commentId, CommentDTO comment);
    void deleteComment(int adId, int commentId);

    CommentDTO getCommentById(int adId, int commentId);
}
