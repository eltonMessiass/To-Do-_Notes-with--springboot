package mz.com.eltonmessias.to_do_List.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

public record TaskDTO(UUID id, String title, String description, boolean completed) {

}
