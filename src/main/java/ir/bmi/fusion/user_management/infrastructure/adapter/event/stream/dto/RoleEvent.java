package ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.dto;


public record RoleEvent(Long id,
                        String name,
                        String code) {
}
