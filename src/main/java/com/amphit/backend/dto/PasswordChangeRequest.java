package com.amphit.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordChangeRequest {
    @NotBlank
    private String currentPassword;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String newPassword;
}
