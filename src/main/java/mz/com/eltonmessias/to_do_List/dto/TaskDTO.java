package mz.com.eltonmessias.to_do_List.dto;

import java.util.UUID;

public record TaskDTO(UUID id, String name, String description, boolean completed) {

}
