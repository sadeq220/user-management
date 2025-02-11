package ir.bmi.fusion.user_management.domain.port.outbound;

import ir.bmi.fusion.user_management.infrastructure.cryptography.key.ECKeyPair;
import ir.bmi.fusion.user_management.infrastructure.cryptography.key.EdECKeyPair;

public interface KeyProviderPort {
    ECKeyPair provideEllipticCurveKeys();
    EdECKeyPair provideEdwardECKeys();
}
