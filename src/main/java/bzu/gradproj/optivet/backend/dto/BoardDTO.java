package bzu.gradproj.optivet.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long boardId;

    @NotNull(message = "Role cant be null")
    private String role;

    @Size(max = 100, message = "Board name must be less than 100 characters")
    private String name;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
