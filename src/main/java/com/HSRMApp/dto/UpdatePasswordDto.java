package com.HSRMApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordDto {
    CredentialsDto oldCredentials;
    CredentialsDto newCredentials;
}
