package ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.dto;

public record PermissionEvent(Long id,
                              String name,
                              String code,
                              String value,
                              Long parentId) {
}
