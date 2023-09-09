package com.example.adsonline.controllers;

import com.example.adsonline.DTOs.*;
import com.example.adsonline.entity.Ads;
import com.example.adsonline.exception.NotFoundInDataBaseException;
import com.example.adsonline.services.AdsService;
import com.example.adsonline.services.CommentService;
import com.example.adsonline.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
/**
 * Класс - контроллер для работы с объявлениями и комментариями, содержащий набор API endpoints
 *
 * @see AdsService
 * @see CommentService
 * @see ImageService
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Объявления", description = "Объявления")
public class AdsController {

    private final AdsService adsService;
    private final CommentService commentService;
    private final ImageService imageService;


    /**
     * Получение списка всех объявлений
     */
    @Operation(summary = "Получить все объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponsesWrapperAdsDTO.class)
                            )
                    )
            })
    @GetMapping()
    public ResponseEntity<ResponsesWrapperAdsDTO> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    /**
     * Получение объявлений авторизованного пользователя
     */
    @Operation(summary = "Получить объявления авторизованного пользователя", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponsesWrapperAdsDTO.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping(value = "/me",
            produces = {"application/json"})
    ResponseEntity<ResponsesWrapperAdsDTO> getAdsMe(Principal principal) {
        return ResponseEntity.ok(adsService.getAllAdsMe(principal));
    }

    /**
     * Получение объявления по идентификатору
     */
    @Operation(summary = "Полученить объявления по идентификатору", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping("/{id}")
    ResponseEntity<FullAdsDTO> getAds(@Parameter(in = ParameterIn.PATH, description = "Идентификатор", required = true, schema = @Schema())
                                      @PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.getFullAdsDTO(id));
    }

    /**
     * Добавление нового объявления
     */
    @Operation(summary = "Добавить объявление", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDTO.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<AdsDTO> addAds(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema(implementation = CreateAdsDTO.class))
                                  @RequestPart(value = "properties", required = false) String createAdsDTO,
                                  @Parameter(description = "file detail")

                                  @RequestPart MultipartFile image,
                                  Principal principal) throws IOException {
        AdsDTO adsDTO = adsService.createAds(createAdsDTO, principal);
        imageService.updateAdImages(adsDTO.getPk(), image);
        return ResponseEntity.ok(adsDTO);
    }

    /**
     * Обновление информации о конкретном объявлении
     */

    @Operation(
            summary = "Обновить информацию об объявлении",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
            })
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDTO> updateAds(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id,
            @Parameter(required = true)
            @RequestBody CreateAdsDTO createAdsDTO) {
        return ResponseEntity.ok(adsService.updateAdsDTO(id, createAdsDTO));
    }

    /**
     * Удаление конкретного объявления
     */
    @Operation(
            summary = "Удалить объявление",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id) {
        adsService.deleteAdsDTO(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Получение списка комментариев для конкретного объявления
     */
    @GetMapping("/{ad_pk}/comments")
    @Operation(
            summary = "Полученить список комментариев для конкретного объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperCommentDTO.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    public ResponseEntity<ResponseWrapperCommentDTO> getCommentsForAd(@PathVariable("ad_pk") int adId) {

        List<CommentDTO> comments = commentService.getCommentsByAdId(adId);
        int totalComments = comments.size();

        ResponseWrapperCommentDTO response = new ResponseWrapperCommentDTO();
        response.setCount(totalComments);
        response.setResults(comments);

        return ResponseEntity.ok(response);
    }

    /**
     * Добавление комментария к объявлению
     */
    @PostMapping("/{ad_pk}/comments")
    @Operation(
            summary = "Добавить комментарий к объявлению",
            tags = "Объявления",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDTO.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDTO.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content()),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content())
            }
    )
    public ResponseEntity<CommentDTO> addComment(
            @PathVariable("ad_pk") int adId,
            @Valid @RequestBody CommentDTO comment,
            Principal principal) {

        try {
            CommentDTO newComment = commentService.addComment(adId, comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
        } catch (NotFoundInDataBaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Получение информации о конкретном комментарии
     *
     */

    @GetMapping("/{ad_pk}/comments/{id}")
    @Operation(
            summary = "Получить информацию о конкретном комментарии",
            tags = "Объявления",
            parameters = {
                    @Parameter(name = "ad_pk", description = "Идентификатор объявления", in = ParameterIn.PATH, required = true),
                    @Parameter(name = "id", description = "Идентификатор комментария", in = ParameterIn.PATH, required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDTO.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            }
    )
    public ResponseEntity<CommentDTO> getComment(
            @PathVariable("ad_pk") int adId,
            @PathVariable int id) {

        try {
            CommentDTO comment = commentService.getCommentById(adId, id);
            return ResponseEntity.ok(comment);
        } catch (NotFoundInDataBaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Обновление информации о конкретном комментарии
     *
     */

    @PatchMapping("/{ad_pk}/comments/{id}")
    @Operation(
            summary = "Обновить информацию о конкретном комментарии",
            tags = "Объявления",
            parameters = {
                    @Parameter(name = "ad_pk", description = "Идентификатор объявления", in = ParameterIn.PATH, required = true),
                    @Parameter(name = "id", description = "Идентификатор комментария", in = ParameterIn.PATH, required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDTO.class)
                    )),
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDTO.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            }
    )
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable("ad_pk") int adId,
            @PathVariable int id,
            @RequestBody CommentDTO commentUpdate) {

        try {
            CommentDTO updatedComment = commentService.updateComment(adId, id, commentUpdate);
            return ResponseEntity.ok(updatedComment);
        } catch (NotFoundInDataBaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Удаление конкретного комментария
     *
     */
    @DeleteMapping("/{ad_pk}/comments/{id}")
    @Operation(
            summary = "Удалить конкретный комментарий",
            tags = "Объявления",
            parameters = {
                    @Parameter(name = "ad_pk", description = "Идентификатор объявления", in = ParameterIn.PATH, required = true),
                    @Parameter(name = "id", description = "Идентификатор комментария", in = ParameterIn.PATH, required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            }
    )
    public ResponseEntity<Void> deleteComment(
            @PathVariable("ad_pk") int adId,
            @PathVariable int id) {

        try {
            commentService.deleteComment(adId, id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundInDataBaseException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
