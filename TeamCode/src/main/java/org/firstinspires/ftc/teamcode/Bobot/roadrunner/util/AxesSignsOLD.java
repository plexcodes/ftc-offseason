package org.firstinspires.ftc.teamcode.Bobot.roadrunner.util;

/**
 * IMU axes signs in the order XYZ (after remapping).
 */
public enum AxesSignsOLD {
    PPP(0b000),
    PPN(0b001),
    PNP(0b010),
    PNN(0b011),
    NPP(0b100),
    NPN(0b101),
    NNP(0b110),
    NNN(0b111);

    public final int bVal;

    AxesSignsOLD(int bVal) {
        this.bVal = bVal;
    }

    public static AxesSignsOLD fromBinaryValue(int bVal) {
        int maskedVal = bVal & 0x07;
        switch (maskedVal) {
            case 0b000:
                return AxesSignsOLD.PPP;
            case 0b001:
                return AxesSignsOLD.PPN;
            case 0b010:
                return AxesSignsOLD.PNP;
            case 0b011:
                return AxesSignsOLD.PNN;
            case 0b100:
                return AxesSignsOLD.NPP;
            case 0b101:
                return AxesSignsOLD.NPN;
            case 0b110:
                return AxesSignsOLD.NNP;
            case 0b111:
                return AxesSignsOLD.NNN;
            default:
                throw new IllegalStateException("Unexpected value for maskedVal: " + maskedVal);
        }
    }
}
