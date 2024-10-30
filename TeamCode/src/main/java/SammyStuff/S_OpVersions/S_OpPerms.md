
# SammyOp functions


| Functions           | Variables involved                              | Additional comments and notes             |
|---------------------|-------------------------------------------------|-------------------------------------------|
| hardwareConfig      | `boolean useEncoder`                            | n/a                                       |
| telemetryInit       | n/a                                             | n/a                                       |
| telemetryAfterInit  | n/a                                             | n/a                                       |
| MoveChassis         | n/a                                             | n/a                                       |
| imuInit             | n/a                                             | n/a                                       |
| SetTargetPosChassis | `int flt, int frt, int blt, int brt`            | n/a                                       |
| setChassisPwr       | `double flp,double frp, double blp, double brp` | n/a                                       |
| Movefsls(1&2)       | `int TrgetPos, double pwr`                      | use Movefsls and Movefsls2 to access both |
| will add more later |                                                 |                                           |




## **Perms**: *vedu(all)*





Copy and paste the stuff below to get access to
all the commands(variables are commented out because I
dont know if you need them. so just in case)


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



 // chassis motors
    //    DcMotor fl,fr,br,bl;
    //   //odometry
    //    DcMotor fle/* Front Left encoder*/,fre /* Front Right encoder*/,bme /* Back Middle encoder*/;
    //move chassis
    //    double drive,turn,strafe;
    //    double flpwr, frpwr, blpwr, brpwr;
    //    double flc,frc,blc,brc;
    //   //Imu
    //    IMU imu;
    //    Telemetry
    //    double YawAngle = functions.getYaw();
    //    double PitchAngle = functions.getPitch();
    //    double RollAngle = functions.getRoll();
    //    double FLOV /*Front Left Odomentry Value*/ = fle.getCurrentPosition();
    //    double FROV /*Front Right Odomentry Value*/= fre.getCurrentPosition();
    //    double BMOV /*Back Middle Odomentry Value*/ = bme.getCurrentPosition();
    //   //servo
    //    Servo servo1;
    //    Servo servo2;
    //
    //    DcMotor fsls1;
    //    DcMotor fsls2;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

To use any of these functions, instead of extending LinearOpMode, extend SammyLinOp