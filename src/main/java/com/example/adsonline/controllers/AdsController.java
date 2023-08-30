package com.example.adsonline.controllers;

import com.example.adsonline.DTOs.*;
import com.example.adsonline.entity.Ads;
import com.example.adsonline.exception.NotFoundInDataBaseException;
import com.example.adsonline.services.AdsService;
import com.example.adsonline.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    private final AdsService adsService;
    private final CommentService commentService;

    public AdsController(AdsService adsService, CommentService commentService) {

        this.adsService = adsService;
        this.commentService = commentService;
    }

    // Получение списка всех объявлений
    @Operation(
            summary = "Получить все объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponsesWrapperAdsDTO.class))
                    })
            })
    @GetMapping()
    public ResponseEntity<ResponsesWrapperAdsDTO> getAllAdsDTO() {
        return ResponseEntity.ok(adsService.getAllAdsDTO());
    }

    // Добавление нового объявления
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
        return ResponseEntity.ok(adsService.getAllAdsDTO());
    }

    @Operation(summary = "Добавить объявление",
            tags = "Объявления",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = CreateAdsDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDTO> addAd(@RequestPart("createAdsDTO") CreateAdsDTO createAdsDTO,
                                        @RequestPart("image") MultipartFile image) throws IOException {
        return ResponseEntity.ok(adsService.createAds(createAdsDTO, image));
    }

    // Обновление информации о конкретном объявлении
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

    // Удаление конкретного объявления
    @Operation(
            summary = "Удалить объявление",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id) {
        adsService.deleteAdsDTO(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Получение списка комментариев для конкретного объявления
    @GetMapping("/{ad_pk}/comments")
    @Operation(
            summary = "Получение списка комментариев для конкретного объявления",
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

    // Добавление комментария к объявлению
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
            @Valid @RequestBody CommentDTO comment) {

        try {
            CommentDTO newComment = commentService.addComment(adId, comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
        } catch (NotFoundInDataBaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Получение информации о конкретном комментарии
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

    // Обновление информации о конкретном комментарии
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

    // Удаление конкретного комментария
    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Void> deleteComments(@PathVariable("ad_pk") int adId,
                                               @PathVariable int id) {

        commentService.deleteComment(adId, id);
        return ResponseEntity.ok().build();
    }
}
