package SammyStuff.SammyOpVersions.Interfaces;public interface SammyLinOpFunctions {
    void HardwareMap();

    void reverse();

    void use_encoder();

    void DontUseEncoders();

    void telemetryInit();

    void telemetryAfterInit();

    void brake();

    void MoveChassis();

    void imuInit();

    void displaySmallOnDS(String Heading, double Data);

    void displayBigOnDS(String Heading);

    double getYaw();

    double getPitch();

    double getRoll();

    void SetTargetPosChassis(int flt, int frt, int blt, int brt);

    void setChassisPwr(double flp, double frp, double blp, double brp);

    void Movefsls1Pwr(double pwr);

    void Movefsls1TargetPos(int TargetPos, double pwr);

    void Movefsls2Pwr(double pwr);

    void Movefsls2TargetPos(int TargetPos, double pwr);
}
