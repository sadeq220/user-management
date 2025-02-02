package ir.bmi.fusion.user_management.cryptography.key;

public interface KeyProviderPort {
    ECKeyPair provideEllipticCurveKeys();
    EdECKeyPair provideEdwardECKeys();
}
